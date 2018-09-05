package com.gree;

import org.nutz.dao.Dao;
import org.nutz.integration.shiro.NutShiro;
import org.nutz.ioc.Ioc;
import org.nutz.lang.Encoding;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

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
        NutShiro.DefaultLoginURL = "/login";
        NutShiro.DefaultNoAuthURL = "/403";
        if(!Charset.defaultCharset().name().equalsIgnoreCase(Encoding.UTF8)){
            logger.warn("This project must running in UTF-8,pls add -Dfile.encoding=UTF-8 to JAVA_OPTS!");
        }

        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class,"daoFX");
      // ioc.get(NutQuartzCronJobFactory.class);
       // TimerUtil t=ioc.get(TimerUtil.class);
       // t.timer5();
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
