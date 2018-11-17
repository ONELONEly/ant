package com.gree.ant.dao;


import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 001 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 项目绩效表的操作
 * @title Tbuss001DAO
 * @createTime 2017 :08:30 05:08:37.
 */
public interface Tbuss001DAO extends BaseDAO<Tbuss001VO>{

    /**
     * Fetch name by grop pdat string.
     *
     * @param usid 用户ID
     * @param pdat 绩效表日期
     * @return 绩效表编号 string
     * @description 通过USID, PDAT查询绩效表编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 11:09:06.
     */
    String fetchNameByUsidPdat(String usid,String pdat);

    /**
     * Query all by acco list.
     *
     * @param acco  科室编号
     * @param pdat  日期
     * @param pager 分页字段
     * @return 绩效集合 list
     * @description 通过科室查询所有的绩效
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 10:10:38.
     */
    List<Tbuss001VO> queryAllByAcco(String acco, String pdat,String group, Pager pager);

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
    List<Tbuss001VO> queryAllByComp(String comp, String pdat,String group,Pager pager);

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
