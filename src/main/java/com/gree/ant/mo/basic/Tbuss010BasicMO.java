package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss010VO;
import org.nutz.dao.Condition;

/**
 * The interface Tbuss 010 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务用户表的关联
 * @title Tbuss010BasicMO
 * @createTime 2017 :10:19 03:10:38.
 */
public interface Tbuss010BasicMO {

    /**
     * Insert tbuss 010 vo.
     *
     * @param tbuss010VO the tbuss 010 vo
     * @return tbuss 010 vo
     * @description 插入单条关联信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:51.
     */
    Tbuss010VO insert(Tbuss010VO tbuss010VO);

    /**
     * Delete integer.
     *
     * @param tbuss010VO the tbuss 010 vo
     * @return the integer
     * @description 根据实体删除中间表的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 03:09:03.
     */
    Integer delete(Tbuss010VO tbuss010VO);

    /**
     * Query by cnd boolean.
     *
     * @param cnd the cnd
     * @return the boolean
     * @description 通过过滤器判断该插入是否存在, true -不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 02:09:52.
     */
    Boolean queryByCnd(Condition cnd);
}
