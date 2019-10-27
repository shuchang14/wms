package com.shu.wms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="wms-base",fallbackFactory=BaseHystrixClientFallbackFactory.class)
public interface BaseFeign {

    @RequestMapping("/user/test")
    String test();
}
