package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase010DAOImp;
import com.gree.ant.mo.basic.Cbase010BasicMO;
import com.gree.ant.vo.Cbase010VO;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase010MO implements Cbase010BasicMO{

    @Inject("refer:cbase010DAOImp")
    private Cbase010DAOImp cbase010DAOImp;

    @Override
    public Cbase010VO insert(Cbase010VO cbase010VO) {
        return cbase010DAOImp.insert(cbase010VO);
    }

    @Override
    public Integer delete(Cbase010VO cbase010VO) {
        return cbase010DAOImp.delete(cbase010VO);
    }

    @Override
    public List<Cbase010VO> queryByCnd(Condition cnd) {
        return cbase010DAOImp.queryByCndPager(cnd,null);
    }
}
