package com.szf.microserviceconsumermovie.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Allen_shen
 * @Date: 2019-04-30 11:36
 * @Description: java类作用描述
 */
@Configuration
public class FeignLogConfiguration {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }
}
