package com.gree.ant.dao;


import com.gree.ant.vo.Cbase013VO;
import org.nutz.dao.Condition;

/**
 * The interface Cbase 015 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文件记录表的DAO层操作.
 * @title Cbase015DAO
 * @createTime 2017 :09:09 09:09:13.
 */
public interface Cbase013DAO extends BaseDAO<Cbase013VO>{

    Boolean insertCheck(Condition cnd);
}
