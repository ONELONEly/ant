package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase018BasicMO;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.Cbase018VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase018MO implements Cbase018BasicMO {

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public Cbase018VO fetchTransByEMID(Integer emid) {
        return (Cbase018VO)baseDAOImp.fetchTransByIdCnd(new Cbase018VO(),emid,"cbase000VOS",null);
    }

    public Cbase016VO fetchByName(String ctyp) {
        return (Cbase016VO) baseDAOImp.fetchByName(new Cbase016VO(),ctyp);
    }

}
