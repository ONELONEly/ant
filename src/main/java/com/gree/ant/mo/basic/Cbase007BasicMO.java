package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase007VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 007 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 角色表的实体逻辑操作VO
 * @title Cbase007BasicMO
 * @createTime 2017 :10:08 10:10:40.
 */
public interface Cbase007BasicMO {

    /**
     * Query all by pager cnd list.
     *
     * @param pager 分页字段
     * @param cnd   过滤器
     * @return 角色VO的集合 list
     * @description 通过Pager, Cnd查询所有角色
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:40.
     */
    List<Cbase007VO> queryAllByPagerCnd(Pager pager, Condition cnd);

    /**
     * Insert by vo cbase 007 vo.
     *
     * @param cbase007VO 角色VO
     * @return 插入的角色VO cbase 007 vo
     * @description 通过角色VO插入单条角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:13.
     */
    Cbase007VO insertByVO(Cbase007VO cbase007VO);

    /**
     * Delete by roid integer.
     *
     * @param roid @Name-角色ID
     * @return 删除的信息条数 integer
     * @description 通过 @Name删除单条角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:13.
     */
    Integer deleteByROID(String roid);

    /**
     * Update by vo integer.
     *
     * @param cbase007VO 角色VO
     * @return 修改的角色条数 integer
     * @description 通过角色VO修改单条角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:13.
     */
    Integer updateByVO(Cbase007VO cbase007VO);

    /**
     * Fetch by roid cbase 007 vo.
     *
     * @param roid @Name-角色ID
     * @return 角色VO cbase 007 vo
     * @description 通过 @Name主键查询单条角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:13.
     */
    Cbase007VO fetchByROID(String roid);

    /**
     * Fetch trans by cnd roid cbase 007 vo.
     *
     * @param cnd  过滤字段
     * @param roid 角色ID
     * @param primary 关联字段
     * @return 角色VO
     * @description 通过CND(过滤关联表),roid查询单个关联实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:08.
     */
    Cbase007VO fetchTransByCndROID(Condition cnd,String roid,String primary);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤器
     * @return 共有多少条记录 integer
     * @description 通过Cnd筛选共有多少条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:13.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Insert check boolean.
     *
     * @param roid 角色ID
     * @param dsca 角色描述
     * @return 当前信息是否存在 。存在-true，不存在-false
     * @description 插入检验
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 11:10:58.
     */
    Boolean insertCheck(String roid,String dsca);
}
