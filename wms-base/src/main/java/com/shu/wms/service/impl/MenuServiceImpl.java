package com.shu.wms.service.impl;

import com.shu.wms.entity.*;
import com.shu.wms.model.MenuModel;
import com.shu.wms.model.RoleMenuModel;
import com.shu.wms.service.MenuService;
import com.shu.wms.util.RcUtil;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	@Override
	public String saveMenu(MenuModel menu) {
	    if(RcUtil.isNull(menu.getMenuUuid())){
	        //新增
            menu=this.save(menu);
        }else{
	        //编辑
            menu=this.persist(menu);
           // MenuModel model = this.find(MenuModel.class,menu.getMenuUuid());
           // RcUtil.copyProperties(menu,model,true);
            menu=this.save(menu);
        }
		return menu.getMenuUuid();
	}
	
	@Override
	public String saveRose(String roseUuid,String nodeIds) {
		/*String[] nodes=nodeIds.split(",");
		Query query = dao.getSession().createQuery("DELETE from "+RoseMenuModel.class.getName()+" WHERE rose_Uuid='"+roseUuid+"'");
		query.executeUpdate();
    	int len=nodes.length;
		for(int i=0;i<len;i++){
			RoseMenuModel rm=new RoseMenuModel();
			rm.setMenuUuid(nodes[i]);
			rm.setRoseUuid(roseUuid);
			dao.save(rm);
		}*/
		return "";
	}

	
	@Override
	public List<MenuModel> getMenuGroupByMenuName(String menuName) {
		Map<String,String> map=new HashMap<String,String>();
		if(menuName!=null&&menuName.trim()!=""){
			map.put("menuName", menuName);
		}
		String sql="and (preMenuUuid ='' or preMenuUuid is null )";
		return null;//  dao.getModelByParam(MenuModel.class, null, null,map,sql);
	}


	@Override
	public List<MenuModel> getMenuByPreUuid(String preUuid) {
		MenuModel menu=new MenuModel();
		menu.setPreMenuUuid(preUuid);
		BaseQuery baseQuery = this.createBaseQuery(MenuModel.class);
		if(preUuid!=null)
			baseQuery.addCondition(Condition.eq("pre_menu_uuid",preUuid));
		return baseQuery.list();
	}


	@Override
	public List<MenuModel> getMenuListByRoseUuid(String roseUuid) {
		/*RoseModel rose=dao.getModelByUuid(RoseModel.class, roseUuid);
		Set<String> uuids=new HashSet<String>();
		for(RoseMenuModel rm:rose.getRmList()){
			uuids.add(rm.getMenuUuid());
		}
		Map<String,Set<String>> map=new HashMap<>();
		map.put("menuUuid", uuids);*/
		return null;// dao.getModelsInParam(MenuModel.class, map);
	}


	@Override
	public MenuModel getMenuByUuid(String uuid) {
		return null;// dao.getModelByUuid(MenuModel.class, uuid);
	}

	@Override
	public boolean deleteMenu(List<String> uuids) {
		boolean flag=false;
		if(uuids.size()>0){
			for(String uuid:uuids){
				MenuModel menu = this.find(MenuModel.class,uuid);
				if(menu!=null)
				    this.remove(menu);
			}
		}
		flag=true;
		return flag;
	}

	@Override
	public List<MenuModel> getMenus(String type) {
		return this.createBaseQuery(MenuModel.class).addCondition(Condition.eq("type",type)).list();
	}

	@Override
	public DataGridEntity queryMenu(MenuQueryCondition menu) {
		BaseQuery baseQuery = this.createBaseQuery(MenuModel.class);

		PageEntity pageEntity=new PageEntity(menu.getCurrentPage(),menu.getPageSize());
		if(!RcUtil.isNull(menu.getMenuCode()))
			baseQuery.addCondition(Condition.eq("menu_code",menu.getMenuCode()));
		if(!RcUtil.isNull(menu.getMenuName()))
			baseQuery.addCondition(Condition.eq("menu_name",menu.getMenuName()));
		if(!RcUtil.isNull(menu.getTwoTime())&&menu.getTwoTime().length==2) {//["2019-06-10T16:00:00.000Z","2019-06-25T16:00:00.000Z"]
			String start=menu.getTwoTime()[0].substring(0,10);
			String end=RcUtil.addDate(menu.getTwoTime()[1].substring(0,10),1);
			baseQuery.addCondition(Condition.between("create_time",start,end));
		}
		return baseQuery.list(pageEntity);
	}

	@Override
	public List<MenuModel> getMenusList(String userCode) {
		String sql="select * from menu_table m where m.type=:type and EXISTS(select sm.menu_uuid from menu_table sm where sm.pre_menu_uuid=m.menu_uuid) order by m.num";
		Query query=this.createNativeQuery(sql,MenuModel.class);
		query.setParameter("type","group");
		List<MenuModel> groupList = query.getResultList();
		for(MenuModel menuGroup:groupList){
			List<MenuModel> sublist = this.createBaseQuery(MenuModel.class)
					.addCondition(Condition.eq("type","menu"))
					.addCondition(Condition.eq("preMenuUuid",menuGroup.getMenuUuid())).list();
			menuGroup.setSubMenuList(sublist);
		}
		return groupList;
	}

	@Override
	public List<MenuModel> getMenuTreeList() {
		//List<MenuModel> groupList = this.createBaseQuery(MenuModel.class).addCondition(Condition.eq("type","group")).list();
		//StringBuilder sql=new StringBuilder("");
		String sql="select * from menu_table m where m.type=:type and EXISTS(select sm.menu_uuid from menu_table sm where sm.pre_menu_uuid=m.menu_uuid) order by m.num";
		Query query=this.createNativeQuery(sql,MenuModel.class);
		query.setParameter("type","group");
		List<MenuModel> groupList = query.getResultList();
		for(MenuModel menuGroup:groupList){
			List<MenuModel> menuList = this.createBaseQuery(MenuModel.class)
					.addCondition(Condition.eq("pre_menu_uuid",menuGroup.getMenuUuid()))
					.addCondition(Condition.eq("type","menu")).list();
			menuGroup.setSubMenuList(menuList);
		}
		return groupList;
	}

	@Override
	public List<MenuModel> getMenusByPreUuid(String preMenuUuid) {
		return this.createBaseQuery(MenuModel.class)
				.addCondition(Condition.eq("pre_menu_uuid",preMenuUuid)).list();
	}

	@Override
	public List<String> getRoleBtn(String roleUuid, String preUuid) {
		String sql="select m.menu_uuid from role_table r LEFT JOIN role_menu rm on r.role_uuid=rm.role_uuid LEFT JOIN menu_table m on rm.menu_uuid=m.menu_uuid where r.role_uuid=:roleUuid and m.pre_menu_uuid=:preUuid";
		Query query = this.createNativeQuery(sql);
		query.setParameter("roleUuid",roleUuid);
		query.setParameter("preUuid",preUuid);
		List<String> list = query.getResultList();
		return list;
	}

	@Override
	public List<String> getRoleMenus(String roleUuid) {
		String sql="SELECT rm.menu_uuid from role_menu rm where  rm.role_uuid=:roleUuid";
		Query query = this.createNativeQuery(sql);
		query.setParameter("roleUuid",roleUuid);
		List<String> list = query.getResultList();
		return list;
	}

	@Override
	public ResponseData saveRoleMenu(String roleUuid, String preMenuUuid, List<String> menuList) {
		ResponseData res=new ResponseData();
		if(RcUtil.isNull(preMenuUuid)){
			//角色》菜单
			//删除旧的
			List<RoleMenuModel> roleMenuList = this.createBaseQuery(RoleMenuModel.class)
					.addCondition(Condition.eq("role_uuid",roleUuid))
					.addCondition(Condition.eq("type","menu"))
					.list();
			this.removeAll(roleMenuList);
			//保存新的
			for(String menuUuid:menuList){
				RoleMenuModel rmModel=new RoleMenuModel();
				rmModel.setRoleUuid(roleUuid);
				rmModel.setMenuUuid(menuUuid);
				rmModel.setType("menu");
				this.save(rmModel);
			}
			res.setResult(true);
			res.setMsg("保存成功！");
		}else{
			//角色》按钮
			List<RoleMenuModel> preList = this.createBaseQuery(RoleMenuModel.class)
					.addCondition(Condition.eq("role_uuid",roleUuid))
					.addCondition(Condition.eq("menu_uuid",preMenuUuid))
					.addCondition(Condition.eq("type","menu"))
					.list();
			if(preList.size()>0){
				List<RoleMenuModel> btnList = this.createBaseQuery(RoleMenuModel.class)
						.addCondition(Condition.eq("role_uuid",roleUuid))
						.addCondition(Condition.eq("pre_menu_uuid",preMenuUuid))
						.list();
				this.removeAll(btnList);
				for(String menuUuid:menuList){
					RoleMenuModel rmModel=new RoleMenuModel();
					rmModel.setRoleUuid(roleUuid);
					rmModel.setPreMenuUuid(preMenuUuid);
					rmModel.setMenuUuid(menuUuid);
					rmModel.setType("button");
					this.save(rmModel);
				}
			}else{
				res.setResult(false);
				res.setError("请先保存其父级菜单！");
			}
		}
		return res;
	}


}
