package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase003DAOImp;
import com.gree.ant.mo.basic.Cbase003BasicMO;
import com.gree.ant.vo.Cbase003VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase003MO implements Cbase003BasicMO{

    @Inject
    private Cbase003DAOImp cbase003DAOImp;

    @Override
    public List<Cbase003VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase003DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public List<Cbase003VO> queryAllTransByCndPager(Condition cnd, Pager pager) {
        return cbase003DAOImp.queryByCndPager(cnd,pager,"cbase002VO");
    }

    @Override
    public Cbase003VO insert(Cbase003VO cbase003VO) {
        return cbase003DAOImp.insert(cbase003VO);
    }

    @Override
    public Integer deleteByFLNO(String flno) {
        return cbase003DAOImp.deleteByName(flno);
    }

    @Override
    public Integer updateByVO(Cbase003VO cbase003) {
        return cbase003DAOImp.update(cbase003);
    }

    @Override
    public Cbase003VO fetchByFLNO(String flno) {
        return cbase003DAOImp.fetchByName(flno);
    }

    @Override
    public Cbase003VO fetchTransByFLNO(String flno) {
        return cbase003DAOImp.fetchTransByNameCnd(flno,"cbase002VO",null);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase003DAOImp.countByCnd(cnd);
    }
}
