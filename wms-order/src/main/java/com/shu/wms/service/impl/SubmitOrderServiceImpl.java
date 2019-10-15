package com.shu.wms.service.impl;

import com.shu.wms.service.SubmitOrderService;
import com.shu.wms.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubmitOrderServiceImpl extends BaseServiceImpl implements SubmitOrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String baseTest() {
        String s = restTemplate.getForObject("http://wms-base/user/test",String.class);
        System.out.println(s);
        return null;
    }
}
