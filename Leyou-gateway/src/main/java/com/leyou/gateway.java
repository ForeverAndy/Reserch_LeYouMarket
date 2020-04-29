package com.leyou;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 声明网关，声明springboot
 */
@EnableZuulProxy
@SpringBootApplication
public class gateway {
    public static void main(String[] args) {
        SpringApplication.run(gateway.class);
    }
}
