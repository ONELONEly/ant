package com.gree.ant.util;
import java.util.Map;

public class LayEditUtil {

    /**
     * Get edit result map.
     *
     * @param code  0表示成功，其它失败
     * @param msg   提示信息,一般上传失败后返回
     * @param src   图片路径
     * @param title 图片名称
     * @return LayEdit的返回格式
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 05:09:41.
     */
    public static Map<String,Object> getEditResult(Integer code, String msg, String src, String title){
        return ResultUtil.getResult(code,msg,ResultUtil.getLayEditUploadResult(src,title));
    }
}
