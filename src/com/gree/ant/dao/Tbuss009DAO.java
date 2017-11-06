package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss009VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Tbuss 009 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文档表的数据层操作.
 * @title Tbuss009DAO
 * @createTime 2017 :11:06 04:11:13.
 */
public interface Tbuss009DAO {

    /**
     * Query all doc list.
     *
     * @param usid  当前用户ID
     * @param cnd   过滤字段
     * @param stage 等级类型('GROP','ACCO','DEPT','COMP')
     * @param pager 分页字段
     * @return 文档集合
     * @description 查询所有的文档
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 04:11:13.
     */
    List<Tbuss009VO> queryAllDoc(String usid,Condition cnd,String stage,Pager pager);

    /**
     * Count all doc integer.
     *
     * @param usid  当前用户ID
     * @param cnd   过滤字段
     * @param stage 等级类型('GROP','ACCO','DEPT','COMP')
     * @return 文档的条数
     * @description 根据条件统计文档的条数.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 04:11:13.
     */
    Integer countAllDoc(String usid,Condition cnd,String stage);
}
