package com.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2019/7/17.
 */

@RestController
public class ZzrController {

    @GetMapping(value = "/zzr")
    public String zzr(){
        return "love hyy";
    }
}
