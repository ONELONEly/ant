package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.util.TaskUtilVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 003 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务表的数据层操作
 * @title Tbuss003DAO
 * @createTime 2017 :10:21 05:10:14.
 */
public interface Tbuss003DAO extends BaseDAO<Tbuss003VO>{

    List<Tbuss003VO> queryAllByCndPager(Condition cnd,Pager pager);

    /**
     * Query all grade task list.
     *
     * @param usid      用户ID
     * @param pager     分页字段
     * @param condition 过滤字段
     * @return 任务集合 list
     * @description 通过USID和Pager查询绩效任务详情的方法.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 05:10:15.
     */
    List<Tbuss003VO> queryAllGradeTask(String usid, Pager pager,Condition condition);

    /**
     * Query grop all task list.
     *
     * @param usid      用户ID
     * @param pager     分页字段
     * @param condition 过滤字段
     * @return the list
     * @description 通过USID 、Pager、Condition查询个人组内的所有任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 10:10:55.
     */
    List<Tbuss003VO> queryGropAllTask(String usid, Pager pager,Condition condition);

    /**
     * Query all task list.
     *
     * @param cnd0  任务表的过滤字段
     * @param cnd1  绩效表的过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过CND0, CND1 ，Pager获取所有任务.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 02:10:58.
     */
    List<Tbuss003VO> queryAllTask(Condition cnd0,Condition cnd1,Pager pager);

    /**
     * Query grop all task list.
     *
     * @param usid      用户ID
     * @param pager     分页字段
     * @param condition 过滤字段
     * @return the list
     * @description 通过USID 、Pager、Condition查询个人组内的所有任务（打印时使用）
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 10:10:55.
     */
    List<Tbuss003VO> queryGropAllTaskPrint(String usid, Pager pager,Condition condition);

    /**
     * Query all task list.
     *
     * @param cnd0  任务表的过滤字段
     * @param cnd1  绩效表的过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过CND0, CND1 ，Pager获取所有任务.（打印时使用）
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 02:10:58.
     */
    List<Tbuss003VO> queryAllTaskPrint(Condition cnd0,Condition cnd1,Pager pager);

    /**
     * Count all task integer.
     *
     * @param cnd0 任务表的过滤字段
     * @param cnd1 绩效表的过滤字段
     * @return the integer
     * @description 通过CND0, CND1 ，Pager统计所有任务的数量.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 02:10:58.
     */
    Integer countAllTask(Condition cnd0,Condition cnd1);

    /**
     * Count grop task integer.
     *
     * @param usid      用户ID
     * @param condition 过滤字段
     * @return the integer
     * @description 通过USID 、Condition统计个人组内共有多少任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 01:10:51.
     */
    Integer countGropTask(String usid,Condition condition);

    Integer countTaskUtilByCnd(Condition cnd);

    List<TaskUtilVO> queryAllTaskByPagerCnd(Pager pager, Cnd cnd);
}
