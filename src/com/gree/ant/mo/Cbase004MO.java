package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase004DAOImp;
import com.gree.ant.mo.basic.Cbase004BasicMO;
import com.gree.ant.vo.Cbase004VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase004MO implements Cbase004BasicMO{

    @Inject
    private Cbase004DAOImp cbase004DAOImp;

    @Override
    public Cbase004VO insert(Cbase004VO cbase004VO) {
        return cbase004DAOImp.insert(cbase004VO);
    }

    @Override
    public Integer delete(Cbase004VO cbase004VO) {
        return cbase004DAOImp.delete(cbase004VO);
    }

    @Override
    public Boolean queryByCnd(Condition cnd) {
        return cbase004DAOImp.queryByCndPager(cnd,null).size() == 0;
    }
}
