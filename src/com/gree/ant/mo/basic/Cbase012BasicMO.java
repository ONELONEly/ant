package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase012VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 012 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务规则评分项实体的逻辑操作
 * @title Cbase012BasicMO
 * @createTime 2017 :09:13 03:09:15.
 */
public interface Cbase012BasicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有评分项通过Cnd, Pager
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:15.
     */
    List<Cbase012VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Insert cbase 012 vo.
     *
     * @param cbase012VO the cbase 012 vo
     * @return the cbase 012 vo
     * @description 通过实体插入单条评分项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:29.
     */
    Cbase012VO insert(Cbase012VO cbase012VO);

    /**
     * Delete by vo integer.
     *
     * @param cbase012VO the cbase 012 vo
     * @return the integer
     * @description 通过实体删除单条评分项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:29.
     */
    Integer deleteByVO(Cbase012VO cbase012VO);

    /**
     * Insert check boolean.
     *
     * @param pjno the pjno
     * @param opco the opco
     * @return the boolean
     * @description 检查分值是否存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 05:09:44.
     */
    Boolean insertCheck(String pjno,String opco);
}
