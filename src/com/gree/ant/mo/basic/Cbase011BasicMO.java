package com.gree.ant.mo.basic;
import com.gree.ant.vo.Cbase011VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 011 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 绩效评估项实体逻辑操作的MO
 * @title Cbase011BasicMO
 * @createTime 2017 :09:06 06:09:11.
 */
public interface Cbase011BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   过滤字段
     * @param pager 分页
     * @return the list
     * @description 查询所有绩效评估项实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:11.
     */
    List<Cbase011VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Fetch by pjno cbase 011 vo.
     *
     * @param pjno 绩效评估编号
     * @return the cbase 011 vo
     * @description 查询单条绩效评估项的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:11.
     */
    Cbase011VO fetchByPjno(String pjno);

    /**
     * Fetch trans by pjno cbase 011 vo.
     *
     * @param pjno 绩效评估编号
     * @return the cbase 011 vo
     * @description 查询单条绩效评估项数据交互的实体.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:11.
     */
    Cbase011VO fetchTransByPjno(String pjno);

    /**
     * Fetch trans by vo cbase 011 vo.
     *
     * @param vo      the vo
     * @param primary the primary
     * @param cnd     the cnd
     * @return the cbase 011 vo
     * @description 通过实体查询单条绩效评估相数据交互的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:11.
     */
    Cbase011VO fetchTransByVO(Cbase011VO vo,String primary,Condition cnd);

    /**
     * Query all trans list.
     *
     * @param cnd   过滤字段
     * @param pager 分页
     * @return the list
     * @description 查询所有绩效评估项数据交互的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:11.
     */
    List<Cbase011VO> queryAllTrans(Condition cnd, Pager pager);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤字段
     * @return the integer
     * @description 通过Cnd进行计数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:49.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Insert cbase 011 vo.
     *
     * @param cbase011VO the cbase 011 vo
     * @return the cbase 011 vo
     * @description 通过实体插入单条绩效评估项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:26.
     */
    Cbase011VO insert(Cbase011VO cbase011VO);

    /**
     * Update by vo integer.
     *
     * @param cbase011VO 规则VO
     * @return 修改的条数
     * @description 通过实体修改规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 02:10:18.
     */
    Integer updateByVO(Cbase011VO cbase011VO);

    /**
     * Delete by pjno integer.
     *
     * @param pjno the pjno
     * @return the integer
     * @description 通过规则编号删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:08.
     */
    Integer deleteByPjno(String pjno);

    /**
     * Delete by vo integer.
     *
     * @param cbase011VO the cbase 011 vo
     * @return the integer
     * @description 通过规则实体删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:08.
     */
    Integer deleteByVO(Cbase011VO cbase011VO);
}
