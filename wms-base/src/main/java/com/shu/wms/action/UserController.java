package com.shu.wms.action;

import com.alibaba.fastjson.JSONObject;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.UserEntity;
import com.shu.wms.entity.UserQueryCondition;
import com.shu.wms.model.UserModel;
import com.shu.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController {

  //  @Value("${baseName}")
    private String baseName;
    @Autowired
    private UserService userService;
    @GetMapping("test")
    public String test(){
        UserEntity entity=new UserEntity();
        entity.setGrade(1);
       // userService.saveOffice(null);
        //userService.getUser("");
        return "成功！";
    }
    @PostMapping("save")
    public UserModel save(@RequestBody Map<String,String> map){
        UserModel saveUser = JSONObject.parseObject(map.get("user"),UserModel.class);
        List<String> roleUuids=JSONObject.parseArray(map.get("roleUuids"),String.class);
        UserModel u=null;
        try{
            u = userService.saveUser(saveUser,roleUuids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }
    @PostMapping("delete")
    public String delete(@RequestBody List<String> userUuids){
       // List<String> list=JSONObject.parseObject(userUuids,ArrayList.class);
        userService.delete(userUuids);
        return "true";
    }
    @PostMapping("userList")
    public DataGridEntity userList(@RequestBody UserQueryCondition condition){
        //DataGridEntity entity=userService.queryUser(condition);
        return null;//entity;
    }
}
