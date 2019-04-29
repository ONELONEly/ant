package com.gree.ant.util;

import com.gree.ant.vo.Cbase010VO;

import java.lang.reflect.Field;

public class ClassFieldUtil {

    public static void main(String[] args) throws Exception {
        Object object = getField(new Cbase010VO());
//        getField(object);
        Cbase010VO cbase010VO = (Cbase010VO)object;
        System.out.println(cbase010VO.getUsid());
    }

    public static Object getField(Object object) throws Exception{
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            field.setAccessible(true);
            String type = field.getGenericType().toString();
            if (type.equals("class java.lang.String")){
                field.set(object,"jinyu");
            }
            System.out.println(field.getName()+":"+field.get(object)+":"+type);
        }
        return clazz;
    }
}
