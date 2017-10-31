package com.gree.ant.dao;

import com.gree.ant.vo.util.ResultVO;

import java.util.List;

/**
 * The interface Cbase 001 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 公司表的数据库操作
 * @title Cbase001DAO
 * @createTime 2017 :09:30 05:09:03.
 */
public interface Cbase001DAO {

    /**
     * Query cd list.
     *
     * @return the list
     * @description 查询公司表的所有ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:10.
     */
    List<ResultVO> queryAllCD();
}
