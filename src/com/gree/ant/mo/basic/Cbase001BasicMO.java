package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase001VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 001 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 公司表的操作类
 * @title Cbase001BasicMO
 * @createTime 2017 :09:28 02:09:52.
 */
public interface Cbase001BasicMO {


    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤器
     * @param pager 分页
     * @return the list
     * @description 通过cnd, pager进行查询
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 02:09:52.
     */
    List<Cbase001VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Insert cbase 001 vo.
     *
     * @param cbase001VO the cbase 001 vo
     * @return the cbase 001 vo
     * @description 通过实体插入单条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:52.
     */
    Cbase001VO insert(Cbase001VO cbase001VO);

    /**
     * Delete by comp integer.
     *
     * @param comp 公司编号
     * @return 1-成功,0-失败
     * @description 通过 @NAME插叙单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:52.
     */
    Integer deleteByComp(String comp);

    /**
     * Update by vo integer.
     *
     * @param cbase001 the cbase 001
     * @return the integer
     * @description 通过实体修改单条记录 ，1-成功,0-失败
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:52.
     */
    Integer updateByVO(Cbase001VO cbase001);

    /**
     * Fetch by comp cbase 001 vo.
     *
     * @param comp 公司编号
     * @return the cbase 001 vo
     * @description 通过 @NAME查询单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:52.
     */
    Cbase001VO fetchByComp(String comp);

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
     * Query cd list.
     *
     * @return the list
     * @description 查询公司表的所有ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:10.
     */
    List<ResultVO> queryAllCD();
}
