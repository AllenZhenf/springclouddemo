package com.szf.microservicegatewayzuul;

import com.szf.microservicegatewayzuul.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceGatewayZuulApplication {

    @Bean
    public PreRequestLogFilter preRequestLogFilter(){
        return new PreRequestLogFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulApplication.class, args);
    }

}
