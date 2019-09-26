package com.springcloud.controller;

import com.springcloud.api.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2019/7/17.
 */

@RestController
public class DcController {

    @Autowired
    private DcClient dcClient;

    @GetMapping(value = "/consumer")
    public String dc(){
        return dcClient.consumer();
    }

}
