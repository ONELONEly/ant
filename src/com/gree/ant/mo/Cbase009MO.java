package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase009DAOImp;
import com.gree.ant.mo.basic.Cbase009BasicMO;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase009VO;
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
public class Cbase009MO implements Cbase009BasicMO{

    @Inject("refer:cbase009DAOImp")
    private Cbase009DAOImp cbase009DAOImp;

    @Inject("refer:cbase010MO")
    private Cbase010MO cbase010MO;

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public List<Cbase009VO> queryAllByCnd(Condition cnd, Pager pager) {
        return formatC9(baseDAOImp.queryByCndPager(new Cbase009VO(),cnd,pager));
    }

    @Override
    public Cbase009VO fetchByGrop(String grop) {return (Cbase009VO) baseDAOImp.fetchByName(new Cbase009VO(),grop);
    }

    @Override
    public List<Cbase009VO> queryAllTrans(Condition cnd, Pager pager){
        List<Cbase009VO> cbase009VOS = queryAllByCnd(null,null);
        List<Cbase009VO> cbase009VOList = new ArrayList<>();
        for (Cbase009VO cbase009VO:cbase009VOS){
            cbase009VO = fetchByGrop(cbase009VO.getGrop());
            cbase009VOList.add(cbase009VO);
        }
        return cbase009VOList;
    }

    @Override
    public Cbase009VO fetchC9Trans(String grop,Condition cnd){
        Cbase009VO cbase009VO = fetchByGrop(grop);
        return (Cbase009VO) baseDAOImp.fetchLinks(cbase009VO,"cbase000VOS",cnd);
    }

    @Override
    public Cbase009VO fetchOneTrans(String usid){
        Cbase009VO cbase009VO = new Cbase009VO();
        List<Cbase000VO> cbase000VOS = new ArrayList<>();
        cbase000VOS.add((Cbase000VO)baseDAOImp.fetchByName(new Cbase000VO(),usid));
        cbase009VO.setCbase000VOS(cbase000VOS);
        return cbase009VO;
    }

    @Override
    public Boolean insertCheck(String dsca) {
        return cbase009DAOImp.insertCheck(dsca);
    }

    @Override
    public Cbase009VO insert(Cbase009VO cbase009VO) {
        return (Cbase009VO) baseDAOImp.insert(cbase009VO);
    }

    @Override
    public Integer delete(String grop) {
        return baseDAOImp.deleteByName(new Cbase009VO(),grop);
    }

    @Override
    public Integer update(Cbase009VO cbase009VO) {
        return baseDAOImp.update(cbase009VO);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase009VO(),cnd);
    }

    @Override
    public List<ResultVO> queryAllGD() {
        return cbase009DAOImp.queryAllGD();
    }

    private List<Cbase009VO> formatC9(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase009VO> cbase009VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase009VOS.add((Cbase009VO) iterator.next());
        }
        return  cbase009VOS;
    }

}
