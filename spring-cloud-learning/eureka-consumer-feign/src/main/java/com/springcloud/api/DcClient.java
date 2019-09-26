package com.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhaozhirong on 2019/7/17.
 */

@FeignClient(value = "eureka-client")
public interface DcClient {

    @GetMapping(value = "/dc")
    String consumer();
}
