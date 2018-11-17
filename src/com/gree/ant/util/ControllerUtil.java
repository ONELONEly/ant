package com.gree.ant.util;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

/**
 * The type Controller util.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 不同模块的Controller共用的部分
 * @title ControllerUtil
 * @createTime 2017 :09:13 02:09:13.
 */
@At("/test")
@Filters
@IocBean
public class ControllerUtil {

    @At
    @Ok("json")
    public void first(){
        DocToPdfUtil toPdfUtil = new DocToPdfUtil("宿命-东野圭吾.txt");
        toPdfUtil.run();
    }

}
