package com.szf.microserviceconsumermovie.service;

import com.szf.microserviceconsumermovie.config.FeignLogConfiguration;
import com.szf.microserviceconsumermovie.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Allen_shen
 * @Date: 2019-04-28 15:55
 * @Description: java类作用描述
 */
@FeignClient(name="microservice-provider-user")
public interface UserFeignClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
