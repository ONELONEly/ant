package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase017DAOImp;
import com.gree.ant.mo.basic.Cbase017basicMO;
import com.gree.ant.vo.Cbase017VO;
import com.gree.ant.vo.ValueObject;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase017MO implements Cbase017basicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:cbase017DAOImp")
    private Cbase017DAOImp cbase017DAOImp;

    @Override
    public List<Cbase017VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return formatC17(baseDAOImp.queryByCndPager(new Cbase017VO(),cnd,pager));
    }

    @Override
    public Cbase017VO insert(Cbase017VO cbase017VO) {
        return (Cbase017VO) baseDAOImp.insert(cbase017VO);
    }

    @Override
    public Boolean insertCheck(String dsca) {
        return cbase017DAOImp.insertCheck(dsca);
    }

    @Override
    public Integer deleteByAcco(String acco) {
        return baseDAOImp.deleteByName(new Cbase017VO(),acco);
    }

    @Override
    public Integer updateByVO(Cbase017VO cbase017VO) {
        return baseDAOImp.update(cbase017VO);
    }

    @Override
    public Cbase017VO fetchByAcco(String acco) {
        return (Cbase017VO)baseDAOImp.fetchByName(new Cbase017VO(),acco);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase017VO(),cnd);
    }

    @Override
    public List<ResultVO> queryAllAD() {
        return cbase017DAOImp.queryAllAD();
    }

    private List<Cbase017VO> formatC17(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase017VO> cbase017VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase017VOS.add((Cbase017VO) iterator.next());
        }
        return  cbase017VOS;
    }
}
