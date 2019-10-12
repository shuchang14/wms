package com.shu.wms.entity;

import com.shu.wms.model.MenuModel;

import java.util.List;

public class MenuListEntity extends MenuModel {

    private List<MenuModel> subMenus;

    public List<MenuModel> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuModel> subMenus) {
        this.subMenus = subMenus;
    }
}
