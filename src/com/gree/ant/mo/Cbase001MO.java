package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase001DAOImp;
import com.gree.ant.mo.basic.Cbase001BasicMO;
import com.gree.ant.vo.Cbase001VO;
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
public class Cbase001MO implements Cbase001BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:cbase001DAOImp")
    private Cbase001DAOImp cbase001DAOImp;

    @Override
    public List<Cbase001VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return formatC1(baseDAOImp.queryByCndPager(new Cbase001VO(),cnd,pager));
    }

    @Override
    public Cbase001VO insert(Cbase001VO cbase001VO) {
        return (Cbase001VO) baseDAOImp.insert(cbase001VO);
    }

    @Override
    public Integer deleteByComp(String comp) {
        return baseDAOImp.deleteByName(new Cbase001VO(),comp);
    }

    @Override
    public Integer updateByVO(Cbase001VO cbase001) {
        return baseDAOImp.update(cbase001);
    }

    @Override
    public Cbase001VO fetchByComp(String comp) {
        return (Cbase001VO) baseDAOImp.fetchByName(new Cbase001VO(),comp);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase001VO(),cnd);
    }

    @Override
    public List<ResultVO> queryAllCD() {
        return cbase001DAOImp.queryAllCD();
    }

    private List<Cbase001VO> formatC1(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase001VO> cbase001VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase001VOS.add((Cbase001VO) iterator.next());
        }
        return  cbase001VOS;
    }
}
