package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase003BasicMO;
import com.gree.ant.vo.Cbase003VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase003MO implements Cbase003BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public List<Cbase003VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return formatC3(baseDAOImp.queryByCndPager(new Cbase003VO(),cnd,pager));
    }

    @Override
    public List<Cbase003VO> queryAllTransByCndPager(Condition cnd, Pager pager) {
        return formatC3(baseDAOImp.queryByCndPager(new Cbase003VO(),cnd,pager,"cbase002VO"));
    }

    @Override
    public Cbase003VO insert(Cbase003VO cbase003VO) {
        return (Cbase003VO) baseDAOImp.insert(cbase003VO);
    }

    @Override
    public Integer deleteByFLNO(String flno) {
        return baseDAOImp.deleteByName(new Cbase003VO(),flno);
    }

    @Override
    public Integer updateByVO(Cbase003VO cbase003) {
        return baseDAOImp.update(cbase003);
    }

    @Override
    public Cbase003VO fetchByFLNO(String flno) {
        return (Cbase003VO)baseDAOImp.fetchByName(new Cbase003VO(),flno);
    }

    @Override
    public Cbase003VO fetchTransByFLNO(String flno) {
        return (Cbase003VO)baseDAOImp.fetchTransByNameCnd(new Cbase003VO(),flno,"cbase002VO",null);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase003VO(),cnd);
    }

    private List<Cbase003VO> formatC3(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase003VO> cbase003VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase003VOS.add((Cbase003VO) iterator.next());
        }
        return  cbase003VOS;
    }
}
