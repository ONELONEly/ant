package com.gree.ant.dao;

import com.gree.ant.vo.util.ResultVO;

import java.util.List;

/**
 * The interface Cbase 009 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 团队表的操作
 * @title Cbase009DAO
 * @createTime 2017 :08:30 05:08:53.
 */
public interface Cbase009DAO {

    /**
     * Insert check boolean.
     *
     * @param dsca the dsca
     * @return the boolean
     * @description 通过描述检查插入
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 04:09:19.
     */
    Boolean insertCheck(String dsca);

    /**
     * Query all gd list.
     *
     * @return the list
     * @description 查询所有的团队ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 04:10:09.
     */
    List<ResultVO> queryAllGD();
}
