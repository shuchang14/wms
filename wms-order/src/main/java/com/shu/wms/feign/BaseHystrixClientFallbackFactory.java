package com.shu.wms.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BaseHystrixClientFallbackFactory implements FallbackFactory<BaseFeign> {
    @Override
    public BaseFeign create(Throwable throwable) {
        return () -> "Base提供者服务挂了";
    }
}
