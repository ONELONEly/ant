package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase004BasicMO;
import com.gree.ant.vo.Cbase004VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase004MO implements Cbase004BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public Cbase004VO insert(Cbase004VO cbase004VO) {
        return (Cbase004VO) baseDAOImp.insert(cbase004VO);
    }

    @Override
    public Integer delete(Cbase004VO cbase004VO) {
        return baseDAOImp.delete(cbase004VO);
    }

    @Override
    public Boolean queryByCnd(Condition cnd) {
        return baseDAOImp.queryByCndPager(new Cbase004VO(),cnd,null).size() == 0;
    }
}
