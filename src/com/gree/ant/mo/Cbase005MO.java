package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase005BasicMO;
import com.gree.ant.vo.Cbase005VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase005MO implements Cbase005BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public Cbase005VO insert(Cbase005VO cbase005VO) {
        return (Cbase005VO)baseDAOImp.insert(cbase005VO);
    }

    @Override
    public Integer delete(Cbase005VO cbase005VO) {
        return baseDAOImp.delete(cbase005VO);
    }

    @Override
    public Boolean queryByCnd(Condition cnd) {
        return baseDAOImp.queryByCndPager(new Cbase005VO(),cnd,null).size() == 0;
    }
}
