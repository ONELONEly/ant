package com.gree.ant.mo.basic;
import com.gree.ant.vo.Cbase015VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 015 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务附件表的逻辑操作
 * @createTime 2017 :09:07 04:09:05.
 * @title Cbase015BasicMO
 */
public interface Cbase015BasicMO {


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
    List<Cbase015VO> queryAllByCndPager(Condition cnd, Pager pager);

    /**
     * Isnert cbase 015 vo.
     *
     * @param cbase015VO the cbase 015 vo
     * @return the cbase 015 vo
     * @description 插入一条附件信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 04:09:05.
     */
    Cbase015VO insert(Cbase015VO cbase015VO);

    /**
     * Delete by taid integer.
     *
     * @param taid 任务ID
     * @return the integer
     * @description 通过任务ID删除单条记录和他所属的文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 08:09:31.
     */
    Integer deleteByTaid(String taid);

    /**
     * Delete by name integer.
     *
     * @param duta     文件ID
     * @param fileName 文件名称
     * @return the integer
     * @description 通过 @NAME删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 11:10:14.
     */
    Integer deleteByName(String duta,String fileName);
}
