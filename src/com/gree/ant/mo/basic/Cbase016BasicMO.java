package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase016VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 016 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文档类型表
 * @title Cbase016BasicMO
 * @createTime 2017 :09:20 06:09:40.
 */
public interface Cbase016BasicMO {

    /**
     * Query all by cnd pager cbase 016 vo.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the cbase 016 vo
     * @description 通过Cnd,Pager查询多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 06:09:40.
     */
    List<Cbase016VO> queryAllByCndPager(Condition cnd, Pager pager);
}
