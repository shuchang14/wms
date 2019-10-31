package com.shu.wms.service;


import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.UserQueryCondition;
import com.shu.wms.model.UserModel;
import com.shu.wms.query.RoleMenuQueryCondition;
import com.shu.wms.service.IBaseService;

import java.util.List;

public interface UserService extends IBaseService {
	
	//String saveOffice(OfficeModel office);
	
	UserModel getUser(String uuid);

	/**
	 * 保存用户
	 * @param saveUser
	 * @return
	 */
	UserModel saveUser(UserModel saveUser, List<String> roleUuids);

	/**
	 * 删除用户
	 * @param userUuids
	 */
	void delete(List<String> userUuids);

	List<UserModel> query(String userCode);

	DataGridEntity queryUser(UserQueryCondition condition);
}
