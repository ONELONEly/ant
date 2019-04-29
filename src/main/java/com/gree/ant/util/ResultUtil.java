package com.gree.ant.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Result util.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description AJAX请求的返回工具
 * @title ResultUtil
 * @createTime 2017 :09:06 03:09:19.
 */
public class ResultUtil {

    /**
     * Get result map.
     *
     * @param code 返回码，0-失败，1-成功
     * @param msg  返回的信息
     * @param data 返回的数据
     * @return the map
     * @description ajax请求统一的返回格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 03:09:54.
     */
    public static Map<String,Object> getResult(Integer code,String msg,Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }

    /**
     * Get upload result map.
     *
     * @param src   上传文件的地址
     * @param title 上传文件的（保存时）文件名
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 02:09:55.
     */
    public static Map<String,Object> getLayEditUploadResult(String src,String title){
        Map<String,Object> map = new HashMap<>();
        map.put("src",src);
        map.put("title",title);
        return map;
    }

    /**
     * Get upload result map.
     *
     * @param src         上传文件的地址
     * @param fileName    文件的新名称
     * @param oldFileName 文件原来的名称
     * @param fileSize    文件的大小，单位KB
     * @param date        文件上传的日期
     * @return the map
     * @description 得到并构造上传文件的结果
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:23.
     */
    public static Map<String,Object> getUploadResult(String src,String fileName,String oldFileName,Integer fileSize, Date date){
        Map<String,Object> map = new HashMap<>();
        map.put("src",src);
        map.put("duta",fileName);
        map.put("title",oldFileName);
        map.put("fileSize",fileSize);
        map.put("date",date);
        return map;
    }

    public static Map<String,Object> detailExceptionResult(Exception e, HttpServletRequest request){
        Map<String,String> tokenUtil = TokenUtil.getInstance().makeToken();
        request.getSession().setAttribute("password",tokenUtil.get("password"));
        Map<String,Object> result = HttpRequest.packageException(e);
        result.put("data",tokenUtil.get("token"));
        return result;
    }

    public static String getBoardBack(HttpServletRequest request,String password){
        Map<String, String> tokenMap = TokenUtil.getInstance().makeToken();
        request.getSession().setAttribute("password", tokenMap.get("password"));
        return  tokenMap.get("token");
    }
}
