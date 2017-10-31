package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase010VO;
import org.nutz.dao.Condition;

/**
 * The interface Cbase 010 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 团队用户表的逻辑操作
 * @title Cbase010BasicMO
 * @createTime 2017 :10:17 04:10:37.
 */
public interface Cbase010BasicMO {

    /**
     * Insert cbase 010 vo.
     *
     * @param cbase010VO 团队用户表的实体
     * @return 被插入的团队用户返回的VO
     * @description 通过VO插入单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:37.
     */
    Cbase010VO insert(Cbase010VO cbase010VO);

    /**
     * Delete integer.
     *
     * @param cbase010VO 团队用户表的VO
     * @return 删除的数据条数
     * @description 通过VO删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:37.
     */
    Integer delete(Cbase010VO cbase010VO);

    /**
     * Query by cnd boolean.
     *
     * @param cnd 过滤字段
     * @return 当前数据是否存在
     * @description 当前数据是否存在，true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:37.
     */
    Boolean queryByCnd(Condition cnd);
}
