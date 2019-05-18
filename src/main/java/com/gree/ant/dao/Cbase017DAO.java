package com.gree.ant.dao;

import com.gree.ant.vo.Cbase017VO;
import com.gree.ant.vo.util.ResultVO;

import java.util.List;

/**
 * The interface Cbase 017 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 科室表的数据库操作
 * @title Cbase017DAO
 * @createTime 2017 :09:30 04:09:09.
 */
public interface Cbase017DAO extends BaseDAO<Cbase017VO>{

    /**
     * Insert check boolean.
     *
     * @param dsca the dsca
     * @return the boolean
     * @description 科室表的插入检查
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 04:09:09.
     */
    Boolean insertCheck(String dsca);

    /**
     * Query all ad list.
     *
     * @return the list
     * @description 查询所有科室表的ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:08.
     */
    List<ResultVO> queryAllAD();

    List<String> queryAllBoos(String officeNumber);
}
