package com.shu.wms.query;

import com.shu.wms.model.UserModel;

public class UserRoleQueryItem extends UserModel {
    private String roleUuid;
    private String roleName;

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}