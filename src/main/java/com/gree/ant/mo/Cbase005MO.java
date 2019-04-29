package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase005DAOImp;
import com.gree.ant.mo.basic.Cbase005BasicMO;
import com.gree.ant.vo.Cbase005VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase005MO implements Cbase005BasicMO{

    @Inject
    private Cbase005DAOImp cbase005DAOImp;

    @Override
    public Cbase005VO insert(Cbase005VO cbase005VO) {
        return cbase005DAOImp.insert(cbase005VO);
    }

    @Override
    public Integer delete(Cbase005VO cbase005VO) {
        return cbase005DAOImp.delete(cbase005VO);
    }

    @Override
    public Boolean queryByCnd(Condition cnd) {
        return cbase005DAOImp.queryByCndPager(cnd,null).size() == 0;
    }
}
