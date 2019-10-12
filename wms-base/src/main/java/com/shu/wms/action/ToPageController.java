package com.shu.wms.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToPageController {

    @RequestMapping("home")
    public String home(){
        return "home";
    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("menu")
    public String menu(){
        return "pages/menu";
    }
    @RequestMapping("user")
    public String user(){
        return "pages/user";
    }
    @RequestMapping("role")
    public String role(){
        return "pages/role";
    }
}
