package com.szf.microserviceconsumermovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableHystrixDashboard
@EnableFeignClients
@EnableHystrix
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = FeignConfiguration.class))
public class MicroserviceConsumerMovieApplication {


    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieApplication.class, args);
    }

}
