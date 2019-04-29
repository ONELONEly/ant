package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase005VO;
import org.nutz.dao.Condition;

/**
 * The interface Cbase 005 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用户角色表的逻辑操作
 * @title Cbase005BasicMO
 * @createTime 2017 :10:13 10:10:40.
 */
public interface Cbase005BasicMO {

    /**
     * Insert cbase 005 vo.
     *
     * @param cbase005VO 用户角色的实体VO
     * @return 被插入的用户角色VO
     * @description 通过VO插入单条用户角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:40.
     */
    Cbase005VO insert(Cbase005VO cbase005VO);

    /**
     * Delete integer.
     *
     * @param cbase005VO 用户角色的实体VO
     * @return 删除的条数
     * @description 通过VO删除单条用户角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:40.
     */
    Integer delete(Cbase005VO cbase005VO);

    /**
     * Query by cnd boolean.
     *
     * @param cnd 过滤器
     * @return 当前数据是否存在
     * @description 当前数据是否存在，true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:40.
     */
    Boolean queryByCnd(Condition cnd);
}
