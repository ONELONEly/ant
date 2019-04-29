package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss015VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 015 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文档附件表
 * @title Tbuss015BasicMO
 * @createTime 2017 :09:21 02:09:43.
 */
public interface Tbuss015BasicMO {

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤字段
     * @param pager 分页
     * @return the list
     * @description 根据过滤条件和分页查询所有的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 09:09:08.
     */
    List<Tbuss015VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Isnert cbase 015 vo.
     *
     * @param tbuss015VO the tbuss 015 vo
     * @return the cbase 015 vo
     * @description 插入一条附件信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 04:09:05.
     */
    Tbuss015VO insert(Tbuss015VO tbuss015VO);

    /**
     * Delete by taid integer.
     *
     * @param duta     流水编号
     * @param fileName 文件名称
     * @return the integer
     * @description 通过附件编号ID删除单条记录和他所属的文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 08:09:31.
     */
    Integer deleteByDuta(String duta,String fileName);

    /**
     * Delete by doid integer.
     *
     * @param doid the doid
     * @return the integer
     * @description 通过文档编号删除多个文档
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 03:09:19.
     */
    Integer deleteByDoid(Long doid);
}
