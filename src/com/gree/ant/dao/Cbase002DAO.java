package com.gree.ant.dao;

import com.gree.ant.vo.Cbase002VO;

import java.util.List;

/**
 * The interface Cbase 002 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 菜单表的数据库操作类
 * @title Cbase002DAO
 * @createTime 2017 :10:13 09:10:38.
 */
public interface Cbase002DAO extends BaseDAO<Cbase002VO> {

    /**
     * Query all menu by usid list.
     *
     * @param usid 用户ID
     * @return 菜单VO集合 list
     * @description 通过USID查询所有的菜单集合
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 09:10:38.
     */
    List<Cbase002VO> queryAllMenuByUSID(String usid);

    /**
     * Query all menu by roid list.
     *
     * @param roid 角色ID
     * @return 菜单VO集合 list
     * @description 通过USID查询所有的菜单集合
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 03:10:32.
     */
    List<Cbase002VO> queryAllMenuByROID(String roid);
}
