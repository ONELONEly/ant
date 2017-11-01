package com.gree;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Main setup.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用一句话描述这个类的作用.
 * @title MainSetup
 * @createTime 2017 :08:28 02:08:19.
 */
public class MainSetup implements Setup{

    private Logger logger = LoggerFactory.getLogger(MainSetup.class);
    /**
     * 启动时，额外逻辑
     *
     * @param nc 配置对象,包含Ioc等你需要的一切资源
     */
    @Override
    public void init(NutConfig nc) {
    }

    /**
     * 关闭时，额外逻辑
     *
     * @param nc 配置对象,包含Ioc等你需要的一切资源
     */
    @Override
    public void destroy(NutConfig nc) {

    }
}
