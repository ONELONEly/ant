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
 * @createTime 2017 :09:20 05:09:55.
 * @title Tbuss009BasicMO
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

    /**
     * Count all doc integer.
     *
     * @param usid  当前用户ID
     * @param cnd   过滤字段
     * @param stage 等级类型('GROP','ACCO','DEPT','COMP')
     * @return 文档的条数
     * @description 根据条件统计文档的条数.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 04:11:13.
     */
    Integer countAllDoc(String usid, Condition cnd, String stage);

    /**
     * Query all doc list.
     *
     * @param usid  当前用户ID
     * @param cnd   过滤字段
     * @param stage 等级类型('GROP','ACCO','DEPT','COMP')
     * @param pager 分页字段
     * @return 文档集合
     * @description 查询所有的文档
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 04:11:13.
     */
    List<Tbuss009VO> queryAllDoc(String usid, Condition cnd,String stage,Pager pager);

    /**
     * @param type 文档类型
     * @param key 搜索关键字
     * @return 搜查的文档
     * @description 通过文档类型和文档关键字查找文档
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -04-30 11:18:39
     */
    List<Tbuss009VO> queryAllDOc(String type,String key);

    /**
     * Query all dt list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return 文档集合 list
     * @description 查询文档的详情 ，除去具体内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:05 02:12:34.
     */
    List<Tbuss009VO> queryAllDocNormal(Condition cnd,Pager pager);
}
