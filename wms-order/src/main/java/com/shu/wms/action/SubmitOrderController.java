package com.shu.wms.action;

import com.shu.wms.entity.BaseCondition;
import com.shu.wms.feign.BaseFeign;
import com.shu.wms.model.SubmitOrderModel;
import com.shu.wms.service.SubmitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("submitOrder")
public class SubmitOrderController {

    @Autowired
    private BaseFeign baseFeign;
    @Autowired
    private SubmitOrderService submitOrderService;
    @RequestMapping("saveSubmitOrder")
    public String saveSubmitOrder(String submitOrder){
      //  String s = restTemplate.getForObject("http://wms-base/wms/user/test",String.class);
        System.out.println(submitOrder);
        return "hhhhhhh";
    }
    @RequestMapping("getOrder")
    public String getOrder(@RequestBody BaseCondition condition){
        System.out.println(condition.toString());
        return "hhhhhhh";
    }
    @RequestMapping("test")
    public String test(){
        submitOrderService.baseTest();
        String s = baseFeign.test();
       // SubmitOrderModel so = submitOrderService.find(SubmitOrderModel.class,"");
        return "test";
    }

}
