package com.shu.wms.action;

import com.alibaba.fastjson.JSONObject;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.MenuQueryCondition;
import com.shu.wms.entity.ResponseData;
import com.shu.wms.model.MenuModel;
import com.shu.wms.service.MenuService;
import com.shu.wms.util.RcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("menuList")
    public DataGridEntity menuList(@RequestBody MenuQueryCondition condition){
       // MenuQueryCondition menuModel=JSONObject.parseObject(condition,MenuQueryCondition.class);
        DataGridEntity entity=menuService.queryMenu(condition);
        return entity;
    }
    @PostMapping("menuListBody")
    public DataGridEntity menuListBody(@RequestBody MenuQueryCondition condition){
        //MenuQueryCondition menuModel=JSONObject.parseObject(condition,MenuQueryCondition.class);
        DataGridEntity entity=menuService.queryMenu(condition);
        return entity;
    }
    @RequestMapping("menus")
    public DataGridEntity menus(){
        DataGridEntity entity=menuService.queryMenu(null);
        return entity;
    }
    @PostMapping("save")
    public String save(@RequestBody MenuModel menuModel){
      //  MenuModel menuModel=JSONObject.parseObject(menu,MenuModel.class);
        String uuid=menuService.saveMenu(menuModel);
        return uuid;
    }
    @PostMapping("delete")
    public String delete(@RequestBody List<String> uuids){
       // List<String> uuids = JSONObject.parseObject(menuUuids,List.class);
        menuService.deleteMenu(uuids);
        return "成功！";
    }
    @GetMapping("getMenus")
    public List<MenuModel> getMenus(String type){
        return menuService.getMenus(type);
    }
    @GetMapping("getMenuList")
    public List<MenuModel> getMenuList(String userCode){
        return menuService.getMenusList(userCode);
    }
    @GetMapping("getMenuss")
    public List<MenuModel> getMenuss(String type, String menu, String name){
        return menuService.getMenus(type);
    }
    @PostMapping("postMenus")
    public List<MenuModel> postMenus(@RequestBody Map<String,Object> map){
        return menuService.getMenus(map.get("type")+"");
    }
    @GetMapping("getMenuTreeList")
    public List<MenuModel> getMenuTreeList(){
        List<MenuModel> list = menuService.getMenuTreeList();
        return list;
    }
    @GetMapping("roleMenus")
    public List<String> roleMenus(String roleUuid){
        return menuService.getRoleMenus(roleUuid);
    }
    @GetMapping("btnList")
    public Map<String,Object> btnList(String roleUuid,String preMenuUuid){
        Map<String,Object> map=new HashMap<>();
        map.put("btnList",menuService.getMenusByPreUuid(preMenuUuid));
        map.put("roleBtnList",menuService.getRoleBtn(roleUuid,preMenuUuid));
        return map;
    }
    @PostMapping("saveRoleMenu")
    public ResponseData saveRoleMenu(@RequestBody Map<String,String> map){
        ResponseData zpj=new ResponseData();
        String roleUuid=map.get("roleUuid");//menuUuids
        String preMenuUuid=map.get("preMenuUuid");
        String menuUuids=map.get("menuUuids");
        if(RcUtil.isNull(roleUuid)){
            zpj.setResult(false);
            zpj.setError("请先点击选择角色");
            return zpj;
        }
        try{
            List<String> menuList = JSONObject.parseArray(menuUuids,String.class);
            zpj = menuService.saveRoleMenu(roleUuid,preMenuUuid,menuList);
        }catch (Exception e){
            e.printStackTrace();
            zpj.setResult(false);
            zpj.setMsg(e.getMessage());
        }
        return zpj;
    }
}
