package com.shu.wms.service.impl;

import com.shu.wms.entity.*;
import com.shu.wms.model.RoleModel;
import com.shu.wms.service.RoleService;
import com.shu.wms.util.RcUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {


    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        RoleModel saveModel=null;
       if(!RcUtil.isNull(roleModel.getRoleUuid())){
           saveModel=this.find(RoleModel.class,roleModel.getRoleUuid());
           saveModel.setRoleCode(roleModel.getRoleCode());
           saveModel.setRoleName(roleModel.getRoleName());
       }else{
           saveModel=roleModel;
       }
        return this.save(roleModel);
    }

    @Override
    public DataGridEntity queryRole(RoleQueryCondition condition) {
        BaseQuery query = this.createBaseQuery(RoleModel.class);
        if(RcUtil.isNull(condition.getSearch())){
            if(!RcUtil.isNull(condition.getRoleCode()))
                query.addCondition(Condition.eq("role_code",condition.getRoleCode()));
            if(!RcUtil.isNull(condition.getRoleName()))
                query.addCondition(Condition.eq("role_name",condition.getRoleName()));
            if(condition.getTwoTime()!=null && condition.getTwoTime().length==2){
                String startTime=condition.getTwoTime()[0];
                String endTime=RcUtil.addDate(condition.getTwoTime()[1],1);
                query.addCondition(Condition.between("create_time",startTime,endTime));
            }
        }else{
            query.addCondition(Condition.orLike("role_code",condition.getSearch()))
                    .addCondition(Condition.orLike("role_name",condition.getSearch()));
        }
        return query.list(new PageEntity(condition.getCurrentPage(),condition.getPageSize()));
    }

    @Override
    public int deleteMenu(List<String> uuids) {
        int i=0;
        for(String uuid:uuids){
            RoleModel role = this.find(RoleModel.class,uuid);
            if(role!=null)
                this.remove(role);
            i++;
        }
        return i;
    }

    @Override
    public List<RoleModel> getAllRole(String searchCode) {
        BaseQuery query=this.createBaseQuery(RoleModel.class);
        if(!RcUtil.isNull(searchCode)){
           return query.addCondition(Condition.or("role_code",searchCode))
                    .addCondition(Condition.or("role_name",searchCode))
                    .list();
        }
        return query.list();
    }
}
