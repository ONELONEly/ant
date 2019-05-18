package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase017DAOImp;
import com.gree.ant.mo.basic.Cbase017basicMO;
import com.gree.ant.vo.Cbase017VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase017MO implements Cbase017basicMO{

    @Inject("refer:cbase017DAOImp")
    private Cbase017DAOImp cbase017DAOImp;

    @Override
    public List<Cbase017VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase017DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Cbase017VO insert(Cbase017VO cbase017VO) {
        return cbase017DAOImp.insert(cbase017VO);
    }

    @Override
    public Boolean insertCheck(String dsca) {
        return cbase017DAOImp.insertCheck(dsca);
    }

    @Override
    public Integer deleteByAcco(String acco) {
        return cbase017DAOImp.deleteByName(acco);
    }

    @Override
    public Integer updateByVO(Cbase017VO cbase017VO) {
        return cbase017DAOImp.update(cbase017VO);
    }

    @Override
    public Cbase017VO fetchByAcco(String acco) {
        return cbase017DAOImp.fetchByName(acco);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase017DAOImp.countByCnd(cnd);
    }

    @Override
    public List<ResultVO> queryAllAD() {
        return cbase017DAOImp.queryAllAD();
    }

    @Override
    public List<String> queryAllBoss(String officeNumber) {
        return cbase017DAOImp.queryAllBoos(officeNumber);
    }
}
