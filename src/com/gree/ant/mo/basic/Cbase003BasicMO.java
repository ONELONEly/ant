package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase003VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 003 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 进程 （菜单）关系表逻辑操作类
 * @title Cbase003BasicMO
 * @createTime 2017 :10:11 08:10:46.
 */
public interface Cbase003BasicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤器
     * @param pager 分页字段
     * @return 查出的所有的菜单关系 list
     * @description 根据Cnd, Pager查询所有菜单
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:46.
     */
    List<Cbase003VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Query all trans by cnd pager list.
     *
     * @param cnd   过滤器
     * @param pager 分页字段
     * @return 查出的所有的菜单关系 list
     * @description 根据Cnd, Pager查询所有菜单关系和关联信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 10:10:50.
     */
    List<Cbase003VO> queryAllTransByCndPager(Condition cnd, Pager pager);

    /**
     * Insert cbase 003 vo.
     *
     * @param cbase003VO 将要插入的菜单关系VO
     * @return 已被插入的菜单关系VO cbase 003 vo
     * @description 通过VO插入单条菜单关系记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:46.
     */
    Cbase003VO insert(Cbase003VO cbase003VO);

    /**
     * Delete by pono integer.
     *
     * @param flno the flno
     * @return 被删除的条数 。1-成功,0-失败
     * @description 通过pono删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:46.
     */
    Integer deleteByFLNO(String flno);

    /**
     * Update by vo integer.
     *
     * @param cbase003 将要修改的菜单VO
     * @return 被修改的条数 。1-成功,0-失败
     * @description 通过VO修改单条菜单记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:46.
     */
    Integer updateByVO(Cbase003VO cbase003);

    /**
     * Fetch by pono cbase 003 vo.
     *
     * @param flno the flno
     * @return 目标查询的菜单关系 cbase 003 vo
     * @description 通过pono查询单条菜单关系记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:46.
     */
    Cbase003VO fetchByFLNO(String flno);

    /**
     * Fetch trans by pono cbase 003 vo.
     *
     * @param flno the flno
     * @return the cbase 003 vo
     * @description 通过菜单号查询单条菜单记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 10:10:50.
     */
    Cbase003VO fetchTransByFLNO(String flno);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤器
     * @return 在条件之下共有多少条数据 integer
     * @description 通过Cnd查询共有多少条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 08:10:46.
     */
    Integer countByCnd(Condition cnd);
}
