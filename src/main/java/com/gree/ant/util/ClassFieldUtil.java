package com.gree.ant.util;

import com.alibaba.druid.sql.ast.SQLParameter;
import com.gree.ant.vo.Cbase010VO;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ClassFieldUtil<T> {

    Class<T> eleType;

    @Test
    public void test(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType)type;
        System.out.print(p);
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
