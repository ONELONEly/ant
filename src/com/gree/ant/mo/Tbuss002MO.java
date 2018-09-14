package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss002DAOImp;
import com.gree.ant.mo.basic.Tbuss002BasicMO;
import com.gree.ant.vo.Tbuss002VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.dao.Condition;
import java.util.List;

@IocBean
public class Tbuss002MO implements Tbuss002BasicMO{



    @Inject("refer:tbuss002DAOImp")
    private Tbuss002DAOImp tbuss002DAOImp;

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
        return tbuss002DAOImp.insert(tbuss002VO);
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
        return tbuss002DAOImp.delete(tbuss002VO);
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
    public List<Tbuss002VO> queryByCnd(Condition cnd) {
        return tbuss002DAOImp.queryByCndPager(cnd,null);
    }
}
