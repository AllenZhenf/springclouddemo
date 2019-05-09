package com.szf.microserviceconsumermovie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.szf.microserviceconsumermovie.entity.User;
import com.szf.microserviceconsumermovie.service.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {

    private final static Logger LOGGER= LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    private UserFeignClient userFeignClient;

    private UserFeignClient adminUserFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userFeignClient= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","password1")).target(UserFeignClient.class,"http://microservice-provider-user");

        this.adminUserFeignClient=Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2")).target(UserFeignClient.class,"http://microservice-provider-user");

    }

    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id){
        return this.userFeignClient.findById(id);
    }

    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id){
        return this.adminUserFeignClient.findById(id);
    }



    @HystrixCommand(fallbackMethod = "findByIdFallback")//添加熔断机制
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
            return this.userFeignClient.findById(id);
//        return this.restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
    }

    public User findByIdFallback(Long id){
        User user=new User();
        user.setId(-1L);
        user.setName("默认用户");
        return user;
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showinfo(){
        return this.discoveryClient.getInstances("microservice-provider-user");
    }


    /**
     * 打印服务提供者信息
     */
    @GetMapping("/log-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance=this.loadBalancerClient.choose("microservice-provider-user");
        LOGGER.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());

    }

}
