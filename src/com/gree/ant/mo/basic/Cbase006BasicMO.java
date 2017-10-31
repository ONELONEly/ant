package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase006VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 006 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 部门表实体逻辑操作的MO
 * @title Cbase006BasicMO
 * @createTime 2017 :09:06 06:09:11.
 */
public interface Cbase006BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   过滤器
     * @param pager 分页字段
     * @return 部门VO集合 list
     * @description 查询所有部门实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:11.
     */
    List<Cbase006VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Insert by vo cbase 006 vo.
     *
     * @param cbase006VO 部门实体
     * @return 插入的部门实体 cbase 006 vo
     * @description 通过实体VO插入单条部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:03.
     */
    Cbase006VO insertByVO(Cbase006VO cbase006VO);

    /**
     * Delete by dept integer.
     *
     * @param dept @Name主键-部门编号
     * @return 删除的部门条数 integer
     * @description 通过 @Name删除单条部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:03.
     */
    Integer deleteByDept(String dept);

    /**
     * Update by vo integer.
     *
     * @param cbase006VO 单条部门信息实体
     * @return 修改的部门条数 integer
     * @description 通过实体VO修改单条部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:03.
     */
    Integer updateByVO(Cbase006VO cbase006VO);

    /**
     * Fetch by dept cbase 006 vo.
     *
     * @param dept @Name主键-部门编号
     * @return 单条部门信息 cbase 006 vo
     * @description 通过 @Name查询单条部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:03.
     */
    Cbase006VO fetchByDept(String dept);

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤器
     * @return 信息条数 integer
     * @description 通过Cnd查询共有多少条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:03.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Insert check boolean.
     *
     * @param dept 部门编号
     * @param dsca 部门描述
     * @return 当前信息是否存在。存在-true，不存在-false
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 10:10:59.
     */
    Boolean insertCheck(String dept,String dsca);

    /**
     * Query all dd list.
     *
     * @return the list
     * @description 查询所有部门的ID和描述.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:46.
     */
    List<ResultVO> queryAllDD();
}
