package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss009VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 009 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 发表文档表的逻辑操作
 * @title Tbuss009BasicMO
 * @createTime 2017 :09:20 05:09:55.
 */
public interface Tbuss009BasicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过Cnd, Pager查询多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    List<Tbuss009VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Fetch by id tbuss 009 vo.
     *
     * @param doid the 文档编号
     * @return the tbuss 009 vo
     * @description 通过 @ID主键查询单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    Tbuss009VO fetchByID(Long doid);

    /**
     * Fetch trans by id primary tbuss 009 vo.
     *
     * @param doid    文档编号
     * @param primary 关联字段
     * @param cnd     过滤字段
     * @return the tbuss 009 vo
     * @description 查询实体和它关联的实体通过 @ID主键和关联字段
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    Tbuss009VO fetchTransByIDPrimary(Long doid,String primary,Condition cnd);

    /**
     * Insert tbuss 009 vo.
     *
     * @param tbuss009VO the tbuss 009 vo
     * @return the tbuss 009 vo
     * @description 通过实体插入单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    Tbuss009VO insert(Tbuss009VO tbuss009VO);

    /**
     * Count integer.
     *
     * @param cnd the cnd
     * @return the integer
     * @description 统计共有多少条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 10:09:33.
     */
    Integer countByCnd(Condition cnd);


    /**
     * Delete by doid integer.
     *
     * @param doid the doid
     * @return the integer
     * @description 通过 @ID删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 01:09:36.
     */
    Integer deleteByDoid(Long doid);

    /**
     * Update by vo integer.
     *
     * @param tbuss009VO the tbuss 009 vo
     * @return the integer
     * @description 通过实体修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 04:09:55.
     */
    Integer updateByVO(Tbuss009VO tbuss009VO);


    List<Tbuss009VO> queryAllGropDoc(String usid, Condition cnd, Pager pager);


}
