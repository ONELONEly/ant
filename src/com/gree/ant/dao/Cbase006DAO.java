package com.gree.ant.dao;

import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;

import java.util.List;

/**
 * The interface Cbase 006 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 部门表的书库操作
 * @title Cbase006DAO
 * @createTime 2017 :10:10 09:10:39.
 */
public interface Cbase006DAO {

    /**
     * Insert check boolean.
     *
     * @param cnd 过滤器
     * @return 是否有当前信息.有 -true,无-false
     * @description 插入检验
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:39.
     */
    Boolean insertCheck(Condition cnd);

    /**
     * Query all dd list.
     *
     * @return the list
     * @description 查询所有部门的ID和描述.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:46.
     */
    List<ResultVO> queryAllDD();
}
