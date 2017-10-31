package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase014VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 014 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务阶段表的实体逻辑操作
 * @title Cbase014BasicMO
 * @createTime 2017 :09:11 03:09:53.
 */
public interface Cbase014BasicMO {


    /**
     * Query all by cns list.
     *
     * @param cnd  过滤条件
     * @param pager 分页条件
     * @return the list
     * @description 通过cnd、pager查询所有信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 03:09:53.
     */
    List<Cbase014VO> queryAllByCnd(Condition cnd, Pager pager);
}
