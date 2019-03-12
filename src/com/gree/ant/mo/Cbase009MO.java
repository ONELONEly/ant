package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase009DAOImp;
import com.gree.ant.mo.basic.Cbase009BasicMO;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase009VO;
import com.gree.ant.vo.Cbase010VO;
import com.gree.ant.vo.response.GropUser;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class Cbase009MO implements Cbase009BasicMO{

    @Inject("refer:cbase009DAOImp")
    private Cbase009DAOImp cbase009DAOImp;

    @Inject
    private Cbase000MO cbase000MO;

    @Inject
    private Cbase010MO cbase010MO;

    @Override
    public List<Cbase009VO> queryAllByCnd(Condition cnd, Pager pager) {
        return cbase009DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Cbase009VO fetchByGrop(String grop) {return cbase009DAOImp.fetchByName(grop);
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
        List<Cbase000VO> cbase000VOS = cbase000MO.queryAllUDByGropCnd(grop,cnd);
        cbase009VO.setCbase000VOS(cbase000VOS);
        return cbase009VO;
    }

    @Override
    public List<GropUser> fetchC9Tran(String grop,Condition cnd) {
        return cbase009DAOImp.fetchC9Trans(grop,cnd);
    }

    @Override
    public Cbase009VO fetchOneTrans(String usid){
        Cbase009VO cbase009VO = new Cbase009VO();
        List<Cbase000VO> cbase000VOS = new ArrayList<>();
        cbase000VOS.add(cbase000MO.fetchByUsid(usid));
        cbase009VO.setCbase000VOS(cbase000VOS);
        return cbase009VO;
    }

    @Override
    public Boolean insertCheck(String dsca) {
        return cbase009DAOImp.insertCheck(dsca);
    }

    @Override
    public Cbase009VO insert(Cbase009VO cbase009VO) {
        return cbase009DAOImp.insert(cbase009VO);
    }

    @Override
    public Integer delete(String grop) {
        return cbase009DAOImp.deleteByName(grop);
    }

    @Override
    public Integer update(Cbase009VO cbase009VO) {
        return cbase009DAOImp.update(cbase009VO);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase009DAOImp.countByCnd(cnd);
    }

    @Override
    public List<ResultVO> queryAllGD() {
        return cbase009DAOImp.queryAllGD();
    }

}
