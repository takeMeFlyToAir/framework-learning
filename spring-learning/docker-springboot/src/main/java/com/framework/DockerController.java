package com.framework;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2019/8/12.
 */
@RestController
@RequestMapping(value = "/docker")
public class DockerController {

    @RequestMapping(value = "/docker")
    public String docker(){
        return "zzr_docker";
    }

}
