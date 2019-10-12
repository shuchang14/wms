package com.shu.wms.service;

import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.MenuQueryCondition;
import com.shu.wms.entity.ResponseData;
import com.shu.wms.model.MenuModel;

import java.util.List;


public interface MenuService extends IBaseService {

	MenuModel getMenuByUuid(String uuid);
	
	String saveMenu(MenuModel menu);
	
	String saveRose(String roseUuid, String nodeIds);
	
	List<MenuModel> getMenuByPreUuid(String preUuid);
	
	List<MenuModel> getMenuGroupByMenuName(String menuName);
	
	List<MenuModel> getMenuListByRoseUuid(String roseUuid);
	
	boolean deleteMenu(List<String> uuids);

	List<MenuModel> getMenus(String type);

	DataGridEntity queryMenu(MenuQueryCondition menu);

	List<MenuModel> getMenusList(String userCode);

    List<MenuModel> getMenuTreeList();

	List<MenuModel> getMenusByPreUuid(String preMenuUuid);

	List<String> getRoleBtn(String roleUuid, String preUuid);

    List<String> getRoleMenus(String roleUuid);

	ResponseData saveRoleMenu(String roleUuid, String preMenuUuid, List<String> menuList);
}
