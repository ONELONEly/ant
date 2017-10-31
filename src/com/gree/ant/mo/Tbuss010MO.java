package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Tbuss010BasicMO;
import com.gree.ant.vo.Tbuss010VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Tbuss010MO implements Tbuss010BasicMO{

    @Inject
    private BaseDAOImp baseDAOImp;

    @Override
    public Tbuss010VO insert(Tbuss010VO tbuss010VO) {
        return (Tbuss010VO)baseDAOImp.insert(tbuss010VO);
    }

    @Override
    public Integer delete(Tbuss010VO tbuss010VO) {
        return baseDAOImp.delete(tbuss010VO);
    }

    @Override
    public Boolean queryByCnd(Condition cnd) {
        return baseDAOImp.queryByCndPager(new Tbuss010VO(),cnd,null).size() == 0;
    }
}
