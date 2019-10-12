package com.shu.wms.util;

import com.shu.wms.model.BaseModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class BeanUtil {

	/**
	 * ��ȡ���������ԣ������丸��ģ�
	 * @param c
	 * @return
	 */
	public static List<Field[]> getFields(Class<? extends Object> c){
		List<Field[]> fieldList=new ArrayList<>();
		Field[] fields=c.getDeclaredFields();//��ȡ����
		while(fields.length>0){
			fieldList.add(fields);
			c=c.getSuperclass();
			fields=c.getDeclaredFields();//��ȡ����
		}
		return fieldList;
	}
	
	 /** 
	 * ������������������ֵ ��������������ԣ�
	 */  
    public static void setFieldValueByName(Object obj,String fieldName, Object value) {  
	    // ��ȡobj����ֽ��ļ�����
	    Class<?> c = obj.getClass();
	    // ��ȡ����ĳ�Ա����
	    Field f;
	    boolean isSuch=true;
	    while(isSuch){
			try {
				f = c.getDeclaredField(fieldName);
				 // ȡ�����Է��ʼ��
				f.setAccessible(true);
				// ��������ֵ
				f.set(obj, value);
				isSuch=false;
			} catch (NoSuchFieldException e) {
				//�����Ҳ������ԣ�����������
				c=c.getSuperclass();
				if(c==null){
					e.printStackTrace();
				}
				isSuch=true;
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    }
    }
	
    /** 
	 * ������������ȡ����ֵ 
	 */  
   private static Object getFieldValueByName(Object o,String fieldName) {  
       try {    
           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
           String getter = "get" + firstLetter + fieldName.substring(1);    
           Method method = o.getClass().getMethod(getter, new Class[] {});    
           Object value = method.invoke(o, new Object[] {});    
           return value;    
       } catch (Exception e) {       
           return null;    
       }    
   }
   
   /**
    * ��getMethods��ȡ���е�getter��setter��������ȡֵ��ֵ��
    * ����Ҫ��getDeclaredMethods����ΪgetMethods��ӳ�� Class ����
    * ����ʾ�����ӿڣ�������Щ�ɸ����ӿ��������Լ��ӳ���ͳ��ӿ�
    * �̳е���Щ�����ӿڣ��Ĺ��� member ��������getDeclaredMethods��
    * ӳ�� Class �����ʾ�����ӿ����������з���������������������Ĭ��
    * ���������ʺ�˽�з��������������̳еķ����� ��Ҳ���ǿ��ע������ֵ������setterע���ԭ��ɡ�
    */
	
    /**
	 * �����������ƻ�ȡ����ֵ(��ȡ�������������ֵ)
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object obj,String fieldName){
		Field field;
		Object value=null;
		try {
			field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);  // ȡ�����Է��ʼ��
			value=field.get(obj);
		} catch (NoSuchFieldException e) {
			try {
				field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
				field.setAccessible(true);  // ȡ�����Է��ʼ��
				value=field.get(obj);
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		return value;
	}
	
	
	public static Object copyModelToEntity(BaseModel model, Object obj){
		Class<?> clazz=model.getClass();
		Field[] fields=clazz.getDeclaredFields();//��ȡ����
		while(fields.length>0){
			for(int i=0;i<fields.length;i++){  
				String fieldName=fields[i].getName();
				Object value=getFieldValueByName(model,fieldName);
				//value = getFieldValueByName(fieldName,obj);
				if(value!=null){
					setFieldValueByName(obj,fieldName,value);
				}
		    } 
			clazz=clazz.getSuperclass();
			fields=clazz.getDeclaredFields();
		}
		return obj;
	}
	
	
 
  
   
   
  
  
}
