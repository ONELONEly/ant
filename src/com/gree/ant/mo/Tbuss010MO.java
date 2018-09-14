package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss010DAOImp;
import com.gree.ant.mo.basic.Tbuss010BasicMO;
import com.gree.ant.vo.Tbuss010VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Tbuss010MO implements Tbuss010BasicMO{

    @Inject("refer:tbuss010DAOImp")
    private Tbuss010DAOImp tbuss010DAOImp;

    @Override
    public Tbuss010VO insert(Tbuss010VO tbuss010VO) {
        return tbuss010DAOImp.insert(tbuss010VO);
    }

    @Override
    public Integer delete(Tbuss010VO tbuss010VO) {
        return tbuss010DAOImp.delete(tbuss010VO);
    }

    @Override
    public Boolean queryByCnd(Condition cnd) {
        return tbuss010DAOImp.queryByCndPager(cnd,null).size() == 0;
    }
}
