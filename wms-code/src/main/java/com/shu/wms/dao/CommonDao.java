package com.shu.wms.dao;

import com.shu.wms.entity.Condition;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.PageEntity;
import com.shu.wms.model.BaseModel;
import org.hibernate.Session;

import java.util.List;


public interface CommonDao{

	 Session getSession();

	/**
	 * 保存
	 * @param model
	 * @return
	 */
	 <MODEL extends BaseModel>MODEL save(MODEL model);
	 /**
	  * 删除
	  * @param model
	  */
	 void remove(BaseModel model);
	 /**
	  * 批量删除
	  * @param models
	  * @return
	  */
	 int removeAll(List<BaseModel> models);

    /**
     * 根据uuid查询
     * @param tClass
     * @param uuid
     * @param <T>
     * @return
     */
    <T>T get(Class<T> tClass, String uuid);
	 /**
	  * 实例化查询语句
	  * @param tClass
	  * @return
	  */
	 <T>CommonDao query(Class<T> tClass);
	 /**
	  * 添加条件Condition
	  * @param condition
	  * @return
	  */
	 <T>CommonDao addCondition(Condition condition);
	 /**
	  * 执行查询返回集合
	  * @return
	  */
	 <T>List<T> list();
     DataGridEntity list(PageEntity page);
    /**
     * 根据query xml文件查询
     * @param itemClass   查询返回的实体类
     * @param condition   查询条件实体类
     * @param page        分页对象page
     * @return
     */
    <T>DataGridEntity query(Class<T> itemClass, Object condition, PageEntity page);
    /**
     * 根据query xml文件查询
     * @param itemClass   查询返回的实体类
     * @param condition   查询条件实体类
     * @return
     */
    <T>List<T> query(Class<T> itemClass, Object condition);
}
