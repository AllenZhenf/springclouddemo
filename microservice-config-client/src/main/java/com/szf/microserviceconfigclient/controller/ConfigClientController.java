package com.szf.microserviceconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Allen_shen
 * @Date: 2019-05-06 14:57
 * @Description: java类作用描述
 */
@RestController
public class ConfigClientController {

    @Value("${profile}")
    private String profile;

    @GetMapping(value = "/profile")
    public String hello(){
        return this.profile;
    }
}
