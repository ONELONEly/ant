package com.gree.ant.dao;

import org.nutz.dao.Condition;

/**
 * The interface Cbase 007 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 角色表的数据库操作
 * @title Cbase007DAO
 * @createTime 2017 :10:10 10:10:48.
 */
public interface Cbase007DAO {

    /**
     * Insert check boolean.
     *
     * @param cnd 过滤器
     * @return 是否有当前信息.有-true,无-false
     * @description 角色表的插入检查
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 10:10:48.
     */
    Boolean insertCheck(Condition cnd);
}
