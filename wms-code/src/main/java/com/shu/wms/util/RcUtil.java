package com.shu.wms.util;

import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

public class RcUtil {

    public static String cluToPro(String clu){
        if(clu.indexOf("_")>-1){
            clu=clu.toLowerCase();
            String[] ss=clu.split("_");
            StringBuilder aa=new StringBuilder(ss[0]);
            for(int j=1;j<ss.length;j++){
                String b=ss[j].substring(0,1).toUpperCase()+ss[j].substring(1);
                aa.append(b);
            }
            clu=aa.toString();
        }
        return clu;
    }
    public static boolean isNull(Object object){
        if(object==null)
            return true;
        if(object.toString().trim().equals(""))
            return true;
        return false;
    }
    /**
     * 根据注解名返回属性值 kongc
     * @param object
     * @param t
     * @return
     */
    public static Object getObjByAnnotation(Object object, Class t){
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            String id = "";
            String name = "";
            // 在该属性上的注释
            try{
                if (field.getAnnotation(t) != null) {
                    name = field.getName();
                    //设置可访问
                    field.setAccessible(true);
                    return field.get(object);
                }
            }catch (Exception e){

            }

        }
        return null;
    }
    /**
     * 根据属性名称返回属性值 kongc
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getValByField(Object object, String fieldName){
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(fieldName);
            //设置可访问
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void copyProperties(Object source, Object target,boolean isFilterNull){
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source
                        .getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null
                            && ClassUtils.isAssignable(writeMethod
                            .getParameterTypes()[0], readMethod
                            .getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod
                                    .getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if(isFilterNull && value==null)
                                continue;
                            if (!Modifier.isPublic(writeMethod
                                    .getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '"
                                            + targetPd.getName()
                                            + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    public static final String yyyyMMdd="yyyy/MM/dd";
    public static final String yyyyMMddHHmmss="yyyy/MM/dd HH:mm:ss";
    public static final String yyyy_MM_dd="yyyy-MM-dd";
    public static final String yyyy_MM_ddHHmmss="yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_ddTHHmmssZ="yyyy-MM-ddTHH:mm:ssZ";

    /**
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date stringToDate(String dateStr,String pattern){
        SimpleDateFormat sDateFormat=new SimpleDateFormat(pattern); //加上时间
        //必须捕获异常
        try {
            Date date=sDateFormat.parse(dateStr);
            return date;
        } catch(ParseException px) {
            px.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param pattern
     * @return
     */
    public static String dateToString(Date d,String pattern){
        SimpleDateFormat f=new SimpleDateFormat(pattern);
        return f.format(d);
    }
    /**
     * 获取多少天后的日期
     * @param d  日期起点
     * @param i  天数
     * @return
     */
    public static Date addDate(Date d,int i){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.add(calendar.DATE,i);
        return calendar.getTime();
    }
    /**
     * 获取多少天后的日期
     * @param d  日期起点
     * @param i  天数
     * @return
     */
    public static String addDate(String d,int i){
        Date date=stringToDate(d,yyyy_MM_dd);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);
        date=calendar.getTime();
        return dateToString(date,yyyy_MM_dd);
    }
}
