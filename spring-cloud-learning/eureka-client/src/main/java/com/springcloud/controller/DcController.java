package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2019/7/16.
 */
@RestController
@RequestMapping(value = "/dc")
public class DcController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/service")
    public String service(){
        String services = "Services: "+discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @GetMapping(value = "/hello")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }
}
