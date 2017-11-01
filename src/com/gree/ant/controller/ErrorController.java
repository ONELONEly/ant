package com.gree.ant.controller;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

/**
 * The type Error controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 错误控制器
 * @title ErrorController
 * @createTime 2017 :11:01 02:11:27.
 */
@IocBean
@Filters
public class ErrorController {

    /**
     * Fail.
     *
     * @description 请求失败.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:01 02:11:28.
     */
    @At("/400")
    @Ok("jsp:jsp.error.400")
    public void fail(){
    }

    /**
     * Not find.
     *
     * @description 未发现请求资源.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:01 02:11:28.
     */
    @At("/404")
    @Ok("jsp:jsp.error.404")
    public void notFind(){
    }

    /**
     * Error page.
     *
     * @description 服务器内部错误.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:01 02:11:28.
     */
    @At("/500")
    @Ok("jsp:jsp.error.500")
    public void errorPage(){
    }
}
