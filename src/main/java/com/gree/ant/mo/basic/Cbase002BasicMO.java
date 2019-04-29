package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase002VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 002 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 进程 （菜单）表逻辑操作
 * @title Cbase002BasicMO
 * @createTime 2017 :10:11 08:10:38.
 */
public interface Cbase002BasicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤器
     * @param pager 分页字段
     * @return 查出的所有的菜单 list
     * @description 根据Cnd, Pager查询所有菜单
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:39.
     */
    List<Cbase002VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Query all menu by usid list.
     *
     * @param usid 用户ID
     * @return 菜单VO集合 list
     * @description 通过USID查询所有的菜单集合(初始化主界面时使用)
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 09:10:38.
     */
    List<Cbase002VO> queryAllMenuByUSID(String usid);

    /**
     * Query all menu by roid list.
     *
     * @param roid 角色ID
     * @return 菜单VO集合 list
     * @description 通过USID查询所有的菜单集合(初始化主界面时使用)
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 03:10:00.
     */
    List<Cbase002VO> queryAllMenuByROID(String roid);

    /**
     * Query all trans by cnd pager list.
     *
     * @param cnd   过滤器
     * @param pager 分页字段
     * @return 包含关联实体的菜单VO集合 list
     * @description 根据Cnd, Pager查询所有菜单
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 10:10:29.
     */
    List<Cbase002VO> queryAllTransByCndPager(Condition cnd, Pager pager);

    /**
     * Insert cbase 002 vo.
     *
     * @param cbase002VO 将要插入的菜单VO
     * @return 已被插入的菜单VO cbase 002 vo
     * @description 通过VO插入单条菜单记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:39.
     */
    Cbase002VO insert(Cbase002VO cbase002VO);

    /**
     * Delete by pono integer.
     *
     * @param pono 菜单号
     * @return 被删除的条数 。1-成功,0-失败
     * @description 通过pono删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:39.
     */
    Integer deleteByPONO(String pono);

    /**
     * Delete with by pono integer.
     *
     * @param pono 菜单号
     * @return 被删除的条数.0 -失败,other-成功
     * @description 通过菜单标号删除他和他关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 11:10:35.
     */
    Integer deleteWithByPONO(String pono);

    /**
     * Update by vo integer.
     *
     * @param cbase002 将要修改的菜单VO
     * @return 被修改的条数 。1-成功,0-失败
     * @description 通过VO修改单条菜单记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:39.
     */
    Integer updateByVO(Cbase002VO cbase002);

    /**
     * Fetch by pono cbase 002 vo.
     *
     * @param pono 菜单号
     * @return 目标查询的菜单 cbase 002 vo
     * @description 通过pono查询单条菜单记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:39.
     */
    Cbase002VO fetchByPONO(String pono);

    /**
     * Fetch trans by pono cbase 002 vo.
     *
     * @param pono 菜单号
     * @return 包含关联实体的菜单VO cbase 002 vo
     * @description 通过pono查询单条菜单记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 10:10:29.
     */
    Cbase002VO fetchTransByPONO(String pono);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤器
     * @return 在条件之下共有多少条数据 integer
     * @description 通过Cnd查询共有多少条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:39.
     */
    Integer countByCnd(Condition cnd);
}
