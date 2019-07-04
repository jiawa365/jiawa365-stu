package com.jiawa365.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by silence-pc on 2019/7/4.
 */
@FeignClient("eureka-provider")
public interface TestService {
    @RequestMapping("/test")
    public String test();
}
