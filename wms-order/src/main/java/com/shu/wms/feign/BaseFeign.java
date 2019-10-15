package com.shu.wms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("wms-base")
public interface BaseFeign {

    @RequestMapping("/user/test")
    String test();
}
