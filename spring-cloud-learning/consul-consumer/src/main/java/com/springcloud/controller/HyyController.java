package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhaozhirong on 2019/7/17.
 */
@RestController
public class HyyController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "hyy")
    public String hyy(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-producer");
        System.out.println("服务地址："+serviceInstance.getUri());
        System.out.println("服务名称："+serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString()+"/zzr",String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }

}
