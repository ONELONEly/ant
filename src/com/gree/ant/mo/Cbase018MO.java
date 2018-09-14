package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase018DAOImp;
import com.gree.ant.mo.basic.Cbase018BasicMO;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.Cbase018VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase018MO implements Cbase018BasicMO {


    @Inject("refer:cbase018DAOImp")
    private Cbase018DAOImp cbase018DAOImp;

    @Inject
    private Cbase016MO cbase016MO;

    @Override
    public Cbase018VO fetchTransByEMID(Integer emid) {
        return cbase018DAOImp.fetchTransByIdCnd(emid,"cbase000VOS",null);
    }

    public Cbase016VO fetchByName(String ctyp) {
        return cbase016MO.fetchByName(ctyp);
    }

}
