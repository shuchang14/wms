package com.shu.wms.action;

import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.ResponseData;
import com.shu.wms.entity.RoleQueryCondition;
import com.shu.wms.model.RoleModel;
import com.shu.wms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping("save")
    public RoleModel save(@RequestBody RoleModel roleModel){
        roleModel=roleService.saveRole(roleModel);
        return roleModel;
    }
    @PostMapping("roleList")
    public DataGridEntity roleList(@RequestBody RoleQueryCondition condition){
        DataGridEntity entity=roleService.queryRole(condition);
        return entity;
    }
    @GetMapping("allRole")
    public List<RoleModel> allRole(String keyword){
        List<RoleModel> list = roleService.getAllRole(keyword);
        DataGridEntity entity=new DataGridEntity();
        entity.setCount(list.size());
        entity.setData(list);
        return list;
    }
    @PostMapping("delete")
    public ResponseData delete(@RequestBody List<String> uuids){
      //  List<String> uuids = JSONObject.parseObject(roleUuids,List.class);
        roleService.deleteMenu(uuids);
        return new ResponseData(true,"删除成功！");
    }
}
