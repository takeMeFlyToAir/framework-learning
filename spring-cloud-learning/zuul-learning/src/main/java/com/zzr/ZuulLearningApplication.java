package com.zzr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulLearningApplication.class, args);
	}

}
