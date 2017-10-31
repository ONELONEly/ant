package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss005VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 005 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 评分记录表的实体逻辑操作
 * @title Tbuss005BasicMO
 * @createTime 2017 :09:13 09:09:44.
 */
public interface Tbuss005BasicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过Cnd, Pager查询全部成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 02:09:55.
     */
    List<Tbuss005VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Insert tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description 插入单条成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:44.
     */
    Tbuss005VO insert(Tbuss005VO tbuss005VO);

    /**
     * Fectch link by vo tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @param primary    the primary
     * @return the tbuss 005 vo
     * @description 通过实体查询相关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 06:09:57.
     */
    Tbuss005VO fectchLinkByVO(Tbuss005VO tbuss005VO,String primary);

    /**
     * Fetch by vo tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description true -为空，false-不为空
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 04:09:58.
     */
    Boolean insertCheck(Tbuss005VO tbuss005VO);

    /**
     * Update by vo tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description 通过实体更新
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 04:09:53.
     */
    Integer updateByVO(Tbuss005VO tbuss005VO);

    /**
     * Delete by ptno integer.
     *
     * @param ptno the ptno
     * @return the integer
     * @description 通过绩效表编号删除多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 02:09:45.
     */
    Integer deleteByPtno(String ptno);
}
