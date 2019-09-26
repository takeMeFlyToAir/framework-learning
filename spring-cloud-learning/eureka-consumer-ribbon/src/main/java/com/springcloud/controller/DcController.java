package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhaozhirong on 2019/7/16.
 */
@RestController
public class DcController {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/dc")
    public String dc(){
        String url = "http://eureka-client/dc";
        System.out.println(url);
        return restTemplate.getForObject(url,String.class);
    }

}
