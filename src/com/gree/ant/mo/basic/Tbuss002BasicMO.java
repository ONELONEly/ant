package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss002VO;
import org.nutz.dao.Condition;

/**
 * The type Tbuss 002 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 绩效表和规则表的关联表
 * @title Tbuss002BasicMO
 * @createTime 2017 :09:14 02:09:59.
 */
public interface Tbuss002BasicMO {

    /**
     * Insert tbuss 002 vo.
     *
     * @param tbuss002VO the tbuss 002 vo
     * @return the tbuss 002 vo
     * @description 插入单条关联信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:51.
     */
    Tbuss002VO insert(Tbuss002VO tbuss002VO);

    /**
     * Delete integer.
     *
     * @param tbuss002VO the tbuss 002 vo
     * @return the integer
     * @description 根据实体删除中间表的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 03:09:03.
     */
    Integer delete(Tbuss002VO tbuss002VO);

    /**
     * Query by cnd boolean.
     *
     * @param cnd the cnd
     * @return the boolean
     * @description 通过过滤器判断该插入是否存在,true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 02:09:52.
     */
    Boolean queryByCnd(Condition cnd);
}
