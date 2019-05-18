package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.Tbuss014VO;
import com.gree.ant.vo.response.FahhVO;
import com.gree.ant.vo.util.TaskUtilVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 003 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务单表实体逻辑操作的MO
 * @title Tbuss003BasicMO
 * @createTime 2017 :09:06 06:09:54.
 */
public interface Tbuss003BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 查询所有任务单表实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:54.
     */
    List<Tbuss003VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤条件
     * @return the integer
     * @description 查询共有多少任务表单
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:54.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Insert by vo tbuss 003 vo.
     *
     * @param tbuss003VO the tbuss 003 vo
     * @return the tbuss 003 vo
     * @description 插入实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:50.
     */
    Tbuss003VO insertByVO(Tbuss003VO tbuss003VO);

    /**
     * Update by vo tbuss 003 vo.
     *
     * @param tbuss003VO the tbuss 003 vo
     * @return the tbuss 003 vo
     * @description 通过实体修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 11:09:29.
     */
    Integer updateByVO(Tbuss003VO tbuss003VO, Integer operate, Cbase000VO cbase000VO, Tbuss014VO tbuss014VO);

    /**
     * Fetch by taid tbuss 003 vo.
     *
     * @param taid 任务编号
     * @return the tbuss 003 vo
     * @description 通过任务编号查询单条任务记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 11:09:29.
     */
    Tbuss003VO fetchByTaid(String taid);

    Tbuss003VO fetchSta1ByTaid(String taid);

    /**
     * Delete by taid integer.
     *
     * @param taid the taid
     * @return the integer
     * @description 通过任务ID删除任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:08 09:09:14.
     */
    Integer deleteByTaid(String taid);

    /**
     * Fetch trans by vo tbuss 003 vo.
     *
     * @param taid    @ID主键
     * @param primary 关联字段
     * @param cnd     过滤器
     * @return the tbuss 003 vo
     * @description 通过ID ，primary,Cnd查询单个数据交互实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:26 06:09:21.
     */
    Tbuss003VO fetchTrans(String taid,String primary,Condition cnd);

    /**
     * Query all grade task list.
     *
     * @param usid     用户ID
     * @param pager    分页字段
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
     * @description 通过USID、Pager、Condition查询个人组的所有任务
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
     * @description 通过CND0,CND1，Pager获取所有任务.
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


    /**
     * Count all task integer.
     *
     * @param cnd0  任务表的过滤字段
     * @param cnd1  绩效表的过滤字段
     * @return the integer
     * @description 通过CND0,CND1，Pager统计所有任务的数量.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 02:10:58.
     */
    Integer countAllTask(Condition cnd0,Condition cnd1);

    Integer countByTaskUtil(TaskUtilVO taskUtilVO);

    List<TaskUtilVO> queryAllByPagerMsg(Pager pager,TaskUtilVO taskUtilVO,String sort,String order);

    List<FahhVO> queryAllProjectFahh(String date);

    List<FahhVO> queryAllNotProjectFahh(String date);

    Integer getUserScoreByPtnoUsid(String ptno, String usid);

    void markScore(String[] taids,Integer stage);
}
