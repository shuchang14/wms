package com.shu.wms.service.impl;

import com.shu.wms.entity.BaseQuery;
import com.shu.wms.entity.Condition;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.PageEntity;
import com.shu.wms.model.BaseModel;
import com.shu.wms.model.UserModel;
import com.shu.wms.model.UserRoleModel;
import com.shu.wms.query.RoleMenuQueryCondition;
import com.shu.wms.query.RoleMenuQueryItem;
import com.shu.wms.service.MenuService;
import com.shu.wms.service.UserService;
import com.shu.wms.util.RcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private MenuService menuService;
	/*@Override
	public String saveOffice(OfficeModel office) {
		//office=this.save(office);
		//this.dao.save(office);
		office=new OfficeModel();
		office.setOfficeName("11111");
		office.setPassWord("111");
		//this.dao.save(office);
		//office=this.dao.get(OfficeModel.class,"111");
		//this.dao.getEntityManager().merge(office);
		//this.dao.merge(office);
		RoleMenuQueryCondition condition=new RoleMenuQueryCondition();
		condition.setRoleUuid("1111");
		//List<RoleMenuQueryItem> items = this.query(RoleMenuQueryItem.class,condition);
		return null;
	}*/
	@Override
	public UserModel getUser(String uuid) {
		//RoleMenuQueryCondition condition=new RoleMenuQueryCondition();
		//condition.setRoleUuid("222222");
		//DataGridEntity dataGridEntity = this.dao.query(RoleMenuQueryItem.class,condition,null);
	//	OfficeModel officeModel = this.dao.get(OfficeModel.class,"22222");
		Map<String,Object> orPatam=new HashMap<>();
		orPatam.put("pass_word","111");
		orPatam.put("super_name","222");
		//List<OfficeModel> list = this.dao.query(OfficeModel.class)
				//.addCondition(Condition.eq("office_name","11111"))
				//.addCondition(Condition.eq("pass_word","111"))
				//.addCondition(Condition.and(Condition.or("pass_word","1222"),
					//	                    Condition.orLike("office_name","3333")
				    //                       ))
			//	.list();
		//DataGridEntity dataGrid = this.dao.query(OfficeModel.class)
				//.addCondition(Condition.eq("office_name","11111"))
				//.addCondition(Condition.eq("pass_word","111"))
				//.addCondition(Condition.and(Condition.or("pass_word","1222"),
				//	                    Condition.orLike("office_name","3333")
				//                       ))
				//.list(new PageEntity(1,1));
		/*CriteriaBuilder criteriaBuilder=this.getCriteriaBuilder();
		CriteriaQuery<UserModel> criteriaQuery = criteriaBuilder.createQuery(UserModel.class);
		Root<UserModel> employee = criteriaQuery.from(UserModel.class);
		Predicate condition = criteriaBuilder.equal(employee.get("userCode"), "shu");
		criteriaQuery.where(condition);
		TypedQuery<UserModel> typedQuery =this.createQuery(criteriaQuery);
		List<UserModel> result = typedQuery.getResultList();*/
		//List<UserModel> list =this.query(UserModel.class).eq("userCode","shu").list();
		List<BaseModel> list = this.createBaseQuery(UserModel.class)
				.addCondition(Condition.eq("userCode","shu"))
				.addCondition(Condition.and(Condition.eq("userCode","shu"),Condition.or("userName","shu")))
				.list();
				//.query();
		return null;//list.get(0);
	}



	/**
	 * 保存用户
	 *
	 * @param saveUser
	 * @return
	 */
	@Override
	public UserModel saveUser(UserModel saveUser,List<String> roleUuids) {
		saveUser=this.save(saveUser);
		List<UserRoleModel> list = this.createBaseQuery(UserRoleModel.class)
				.addCondition(Condition.eq("user_uuid",saveUser.getUserUuid()))
				.list();
		if(list.size()>0){
			this.removeAll(list);
		}
		if(roleUuids!=null && roleUuids.size()>0){
			for(int i=0;i<roleUuids.size();i++){
				UserRoleModel urModel=new UserRoleModel();
				urModel.setUserUuid(saveUser.getUserUuid());
				urModel.setRoleUuid(roleUuids.get(i));
				this.save(urModel);
			}
		}
		return saveUser;
	}

	/**
	 * 删除用户
	 *
	 * @param userUuids
	 */
	@Override
	public void delete(List<String> userUuids) {
		UserModel user=null;
		for(String uuid:userUuids){
			user=this.find(UserModel.class,uuid);
			if(user!=null)
				this.remove(user);
		}
	}

	@Override
	public List<UserModel> query(String userCode) {
		RoleMenuQueryCondition condition=new RoleMenuQueryCondition();
		condition.setRoleUuid("1111110001");
		List<RoleMenuQueryItem> list = this.query(RoleMenuQueryItem.class,condition);
		return null;//list;
	}

	@Override
	public DataGridEntity queryUser(RoleMenuQueryCondition condition) {
		//return this.query(UserRoleQueryItem.class,condition,pageEntity);
		BaseQuery query = this.createBaseQuery(UserModel.class);
		/*if(!RcUtil.isNull(condition.getUserCode()))
			query.addCondition(Condition.eq("user_code",condition.getUserCode()));
		if(!RcUtil.isNull(condition.getUserName()))
			query.addCondition(Condition.eq("user_name",condition.getUserName()));
		if(condition.getTwoTime()!=null && condition.getTwoTime().length==2){
			String startTime=condition.getTwoTime()[0];
			String endTime=RcUtil.addDate(condition.getTwoTime()[1],1);
			query.addCondition(Condition.between("create_time",startTime,endTime));
		}*/
		return null;// query.list(new PageEntity(condition.getCurrentPage(),condition.getPageSize()));
	}


}
