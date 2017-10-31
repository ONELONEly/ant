package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase018VO;

/**
 * The interface Cbase 018 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 邮箱组的逻辑操作类
 * @title Cbase018BasicMO
 * @createTime 2017 :10:21 06:10:26.
 */
public interface Cbase018BasicMO {

    /**
     * Query trans by emid cbase 018 vo.
     *
     * @param emid 邮箱组ID
     * @return the cbase 018 vo
     * @description 通过EMID查询邮箱的关联实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 06:10:26.
     */
    Cbase018VO fetchTransByEMID(Integer emid);
}
