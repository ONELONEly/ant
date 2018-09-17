package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss012DAOImp;
import com.gree.ant.mo.basic.Tbuss012BasicMO;
import com.gree.ant.vo.Tbuss012VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Tbuss012MO implements Tbuss012BasicMO {

    @Inject
    private Tbuss012DAOImp tbuss012DAOImp;

    @Inject
    private Tbuss013MO tbuss013MO;


    @Override
    public Tbuss012VO insertGoal(Tbuss012VO tbuss012VO) {
        return tbuss012DAOImp.insertWith(tbuss012VO,"tbuss013VOS");
    }
}
