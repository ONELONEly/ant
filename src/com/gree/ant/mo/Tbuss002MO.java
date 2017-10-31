package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Tbuss002BasicMO;
import com.gree.ant.vo.Tbuss002VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import org.nutz.dao.Condition;

@IocBean
public class Tbuss002MO implements Tbuss002BasicMO{

    @Inject
    private BaseDAOImp baseDAOImp;

    /**
     * Insert tbuss 002 vo.
     *
     * @param tbuss002VO the tbuss 002 vo
     * @return the tbuss 002 vo
     * @description 插入单条关联信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:51.
     */
    @Override
    public Tbuss002VO insert(Tbuss002VO tbuss002VO) {
        return (Tbuss002VO)baseDAOImp.insert(tbuss002VO);
    }

    /**
     * Delete integer.
     *
     * @param tbuss002VO the tbuss 002 vo
     * @return the integer
     * @description 根据实体删除中间表的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 03:09:03.
     */
    @Override
    public Integer delete(Tbuss002VO tbuss002VO) {
        return baseDAOImp.delete(tbuss002VO);
    }

    /**
     * Query by cnd boolean.
     *
     * @param cnd the cnd
     * @return the boolean
     * @description 通过过滤器查询,true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 02:09:52.
     */
    @Override
    public Boolean queryByCnd(Condition cnd) {
        return baseDAOImp.queryByCndPager(new Tbuss002VO(),cnd,null).size() == 0;
    }
}
