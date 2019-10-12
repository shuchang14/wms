package com.shu.wms.service;

import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.RoleQueryCondition;
import com.shu.wms.model.RoleModel;

import java.util.List;

public interface RoleService extends IBaseService {

    RoleModel saveRole(RoleModel roleModel);

    DataGridEntity queryRole(RoleQueryCondition condition);

    int deleteMenu(List<String> uuids);

    List<RoleModel> getAllRole(String searchCode);
}
