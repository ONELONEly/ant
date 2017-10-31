package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase004VO;
import org.nutz.dao.Condition;

/**
 * The interface Cbase 004 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 角色权限表逻辑操作类
 * @title Cbase004BasicMO
 * @createTime 2017 :10:12 10:10:32.
 */
public interface Cbase004BasicMO{

    /**
     * Insert cbase 004 vo.
     *
     * @param cbase004VO 角色权限VO
     * @return the cbase 004 vo
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:32.
     */
    Cbase004VO insert(Cbase004VO cbase004VO);

    /**
     * Delete integer.
     *
     * @param cbase004VO 角色权限VO
     * @return 删除的数据条数
     * @description 通过角色权限VO删除单个权限
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:32.
     */
    Integer delete(Cbase004VO cbase004VO);

    /**
     * Query by cnd boolean.
     *
     * @param cnd 过滤器
     * @return 判断结果
     * @description 通过过滤器判断该插入是否存在,true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:32.
     */
    Boolean queryByCnd(Condition cnd);
}
