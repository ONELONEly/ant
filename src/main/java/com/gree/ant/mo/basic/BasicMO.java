package com.gree.ant.mo.basic;

import java.util.List;

/**
 * @param <T>
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description MO层的基本集成类
 * @createTime 2019 -01-09 09:32:17
 */
public interface BasicMO<T> {

    /**
     * @param vo 插入的数据
     * @return 插入后的实体数据
     * @description 插入单条数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 09:32:17
     */
    T insertByVO(T vo);

    /**
     * @param vos 插入的数据
     * @return 插入后的实体数据
     * @description 插入多条条数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 09:32:17
     */
    List<T> insertByVOS(List<T> vos);

    /**
     * @param vo 删除的数据实体
     * @return 删除的数据数量
     * @description 根据实体，深处单条数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 09:32:17
     */
    Integer deleteByVO(T vo);

    /**
     * @param name 删除的数据实体Id
     * @return 删除的数据数量
     * @description 根据实体，深处单条数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 09:32:17
     */
    Integer deleteByName(String name);

    /**
     * @param vo 修改的数据
     * @return 修改的数据数量
     * @description 修改单条数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 09:32:17
     */
    Integer updateByVO(T vo);

    /**
     * @param vos 修改的数据
     * @return 修改的数据数量s
     * @description 修改多条数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 09:32:17
     */
    Integer updateByVOS(List<T> vos);
}
