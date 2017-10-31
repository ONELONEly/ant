package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss004VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 004 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 日志表实体逻辑操作的MO
 * @title Tbuss004BasicMO
 * @createTime 2017 :09:06 06:09:23.
 */
public interface Tbuss004BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有日志实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:23.
     */
    List<Tbuss004VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Count by cnd integer.
     *
     * @param cnd the cnd
     * @return the integer
     * @description 查询共有多少日志
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:23.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Insert tbuss 004 vo.
     *
     * @param tbuss004VO the tbuss 004 vo
     * @return the tbuss 004 vo
     * @description 通过实体插入单条操作记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 04:09:42.
     */
    Tbuss004VO insert(Tbuss004VO tbuss004VO);
}
