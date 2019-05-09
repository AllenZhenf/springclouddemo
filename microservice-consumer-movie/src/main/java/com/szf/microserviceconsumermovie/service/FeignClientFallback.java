package com.szf.microserviceconsumermovie.service;

import com.szf.microserviceconsumermovie.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Author Allen_shen
 * @Date: 2019-04-30 16:48
 * @Description: feign回退
 */
@Component
public class FeignClientFallback implements UserFeignClient {
    @Override
    public User findById(Long id) {
        User user=new User();
        user.setId(-1L);
        user.setName("默认用户");
        return user;
    }
}
