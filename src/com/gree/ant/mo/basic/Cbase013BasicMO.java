package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase013VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface Cbase013BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有系统信息.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:58.
     */
    List<Cbase013VO> queryAllByCnd(Condition cnd, Pager pager);
}
