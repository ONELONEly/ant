package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase017VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The type Cbase 017 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 科室表的实体逻辑
 * @title Cbase017basicMO
 * @createTime 2017 :09:28 11:09:26.
 */
public interface Cbase017basicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤器
     * @param pager 分页
     * @return the list
     * @description 查询所有的科室信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 11:09:26.
     */
    List<Cbase017VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Insert cbase 017 vo.
     *
     * @param cbase017VO the cbase 017 vo
     * @return the cbase 017 vo
     * @description 通过实体插入单个科室记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:53.
     */
    Cbase017VO insert(Cbase017VO cbase017VO);

    Boolean insertCheck(String dsca);

    /**
     * Delete by acco integer.
     *
     * @param acco the acco
     * @return the integer
     * @description 通过 @NAME删除单条记录，1-成功,0-失败
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:53.
     */
    Integer deleteByAcco(String acco);

    /**
     * Update by vo integer.
     *
     * @param cbase017VO the cbase 017 vo
     * @return the integer
     * @description 通过实体修改单条记录.1-成功,0-失败
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:53.
     */
    Integer updateByVO(Cbase017VO cbase017VO);

    /**
     * Fetch by acco cbase 017 vo.
     *
     * @param acco the acco
     * @return the cbase 017 vo
     * @description 通过科室编号修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:53.
     */
    Cbase017VO fetchByAcco(String acco);

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
     * Query all ad list.
     *
     * @return the list
     * @description 查询所有科室表的ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:08.
     */
    List<ResultVO> queryAllAD();

    List<String> queryAllBoss(String officeNumber);
}
