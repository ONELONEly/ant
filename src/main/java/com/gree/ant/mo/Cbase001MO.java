package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase001DAOImp;
import com.gree.ant.mo.basic.Cbase001BasicMO;
import com.gree.ant.vo.Cbase001VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase001MO implements Cbase001BasicMO{

    @Inject("refer:cbase001DAOImp")
    private Cbase001DAOImp cbase001DAOImp;

    @Override
    public List<Cbase001VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase001DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Cbase001VO insert(Cbase001VO cbase001VO) {
        return cbase001DAOImp.insert(cbase001VO);
    }

    @Override
    public Integer deleteByComp(String comp) {
        return cbase001DAOImp.deleteByName(comp);
    }

    @Override
    public Integer updateByVO(Cbase001VO cbase001) {
        return cbase001DAOImp.update(cbase001);
    }

    @Override
    public Cbase001VO fetchByComp(String comp) {
        return cbase001DAOImp.fetchByName(comp);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase001DAOImp.countByCnd(cnd);
    }

    @Override
    public List<ResultVO> queryAllCD() {
        return cbase001DAOImp.queryAllCD();
    }
}
