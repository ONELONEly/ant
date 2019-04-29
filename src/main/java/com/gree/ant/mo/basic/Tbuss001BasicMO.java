package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 001 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 项目绩效实体逻辑操作的MO
 * @title Tbuss001BasicMO
 * @createTime 2017 :09:06 06:09:07.
 */
public interface Tbuss001BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有项目绩效实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:07.
     */
    List<Tbuss001VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Count by cnd integer.
     *
     * @param cnd the cnd
     * @return the integer
     * @description 返回共有多少条项目绩效
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:07.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Delete by vo integer.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the integer
     * @description 通过实体删除单条记录, 0 -失败,1-成功
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:31.
     */
    Integer deleteByVO(Tbuss001VO tbuss001VO);

    /**
     * Delete by name integer.
     *
     * @param ptno the ptno
     * @return the integer
     * @description 通过 @Name删除单条记录, 0 -失败,1-成功
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:58.
     */
    Integer deleteByName(String ptno);

    /**
     * Detele relation integer.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the integer
     * @description 通过实体删除关联表的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:57.
     */
    Tbuss001VO deteleRelation(Tbuss001VO tbuss001VO);

    /**
     * Fetch links by vo tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @param parimay    the parimay
     * @param cnd        the cnd
     * @return 返回该记录和其关联的规则 tbuss 001 vo
     * @description 通过实体查询该记录的规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 11:09:23.
     */
    Tbuss001VO fetchLinksByVO(Tbuss001VO tbuss001VO,String parimay,Condition cnd);

    /**
     * Fetch trans by name tbuss 001 vo.
     *
     * @param ptno    绩效编号
     * @param primary 关联映射关键词
     * @param cnd     过滤条件
     * @return 返回该记录和其关联的规则 tbuss 001 vo
     * @description 通过 @Name主键查询该实体和规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 11:09:23.
     */
    Tbuss001VO fetchTransByNameCnd(String ptno,String primary,Condition cnd);


    /**
     * Fectch by name tbuss 001 vo.
     *
     * @param ptno the ptno
     * @return the tbuss 001 vo
     * @description 通过 @Name主键查询单条信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:50.
     */
    Tbuss001VO fectchByName(String ptno);

    /**
     * Insert relation tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体插入Relation
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 01:09:42.
     */
    Tbuss001VO insertRelation(Tbuss001VO tbuss001VO);

    /**
     * Insert tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体插入单条信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 04:09:41.
     */
    Tbuss001VO insert(Tbuss001VO tbuss001VO);

    /**
     * Insert with tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体插入单条记录和他关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 09:09:58.
     */
    Tbuss001VO insertWith(Tbuss001VO tbuss001VO);

    /**
     * Insert check boolean.
     *
     * @param pdat the 创建日期
     * @param grop the 团队
     * @return the boolean
     * @description 通过创建时间判断是否存在, ，true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 03:09:40.
     */
    Boolean insertCheck(String pdat,String grop);

    /**
     * Update by vo tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 07:09:37.
     */
    Integer updateByVO(Tbuss001VO tbuss001VO);

    /**
     * Fetch name by grop pdat string.
     *
     * @param usid 用户ID
     * @param pdat 绩效表日期
     * @return 绩效表编号 string
     * @description 通过团队编号和绩效表日期查询绩效表编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 11:09:57.
     */
    String fetchNameByUsidPdat(String usid,String pdat);

    /**
     * Query all by acco list.
     *
     * @param acco  科室编号
     * @param pdat  the pdat
     * @param pager 分页字段
     * @return 绩效集合 list
     * @description 通过科室查询所有的绩效
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 10:10:38.
     */
    List<Tbuss001VO> queryAllByAcco(String acco, String pdat,String group,Pager pager);

    /**
     * Query all by dept list.
     *
     * @param dept  部门编号
     * @param pdat  日期
     * @param pager 分页字段
     * @return 绩效集合 list
     * @description 通过科室查询所有的绩效
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 10:10:38.
     */
    List<Tbuss001VO> queryAllByDept(String dept, String pdat,String group,Pager pager);


    /**
     * Query all by comp list.
     *
     * @param comp  公司编号
     * @param pdat  日期
     * @param pager 分页字段
     * @return 绩效集合 list
     * @description 通过科室查询所有的绩效
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 10:10:38.
     */
    List<Tbuss001VO> queryAllByComp(String comp,String pdat,String group,Pager pager);

    /**
     * Count by acco integer.
     *
     * @param acco 科室编号
     * @param pdat 日期
     * @return 条数 integer
     * @description 通过Acco统计条数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 11:10:35.
     */
    Integer countByAcco(String acco, String pdat,String group);

    /**
     * Count by dept integer.
     *
     * @param dept 部门编号
     * @param pdat 日期
     * @return 条数 integer
     * @description 通过Dept统计条数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 11:10:35.
     */
    Integer countByDept(String dept, String pdat,String group);


    /**
     * Count by comp integer.
     *
     * @param comp 公司编号
     * @param pdat 日期
     * @return 条数 integer
     * @description 通过科室统计条数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 11:10:35.
     */
    Integer countByComp(String comp, String pdat,String group);


    /**
     * Query all pdat list.
     *
     * @return the list
     * @description 查询所有有的月份
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 04:10:58.
     */
    List<String> queryAllPdat();

    /**
     * Query all pd list.
     *
     * @return the list
     * @description 查询出所有的ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 06:10:58.
     */
    List<ResultVO> queryAllPD(Condition cnd);

}
