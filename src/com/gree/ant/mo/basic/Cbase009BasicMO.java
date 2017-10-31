package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase009VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 009 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 团队实体逻辑操作的MO
 * @title Cbase009BasicMO
 * @createTime 2017 :09:06 06:09:10.
 */
public interface Cbase009BasicMO{

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有团队实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:10.
     */
    List<Cbase009VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Fetch by grop cbase 009 vo.
     *
     * @param grop the grop
     * @return the cbase 009 vo
     * @description 通过团队编号查询单个团队
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:10.
     */
    Cbase009VO fetchByGrop(String grop);

    /**
     * Query all trans list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有团队数据交互的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:10.
     */
    List<Cbase009VO> queryAllTrans(Condition cnd, Pager pager);

    /**
     * Fetch c 9 trans cbase 009 vo.
     *
     * @param grop 团队编号
     * @return the cbase 009 vo
     * @description 查询单条团队数据交互的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:10.
     */
    Cbase009VO fetchC9Trans(String grop,Condition cnd);

    /**
     * Fetch one trans cbase 009 vo.
     *
     * @param usid 用户编号
     * @return the cbase 009 vo
     * @description 查询单条团队交互的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:10.
     */
    Cbase009VO fetchOneTrans(String usid);

    /**
     * Insert cbase 009 vo.
     *
     * @param cbase009VO the cbase 009 vo
     * @return the cbase 009 vo
     * @description 通过实体插入单个团队记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:04.
     */
    Cbase009VO insert(Cbase009VO cbase009VO);

    /**
     * Insert check boolean.
     *
     * @param dsca the dsca
     * @return the boolean
     * @description 插入检测，检测是否可以插入
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 04:09:12.
     */
    Boolean insertCheck(String dsca);

    /**
     * Delete integer.
     *
     * @param grop the grop
     * @return the integer
     * @description 通过 @NAME删除单条记录，1-成功,0-失败
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:04.
     */
    Integer delete(String grop);

    /**
     * Update cbase 009 vo.
     *
     * @param cbase009VO the cbase 009 vo
     * @return the cbase 009 vo
     * @description 通过实体修改单条记录.1 -成功,0-失败
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:04.
     */
    Integer update(Cbase009VO cbase009VO);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤器
     * @return the integer
     * @description 通过cnd获得总共有多少条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 11:09:42.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Query all gd list.
     *
     * @return the list
     * @description 查询所有的团队ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 04:10:09.
     */
    List<ResultVO> queryAllGD();
}
