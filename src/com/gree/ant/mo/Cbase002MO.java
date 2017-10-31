package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase002DAOImp;
import com.gree.ant.mo.basic.Cbase002BasicMO;
import com.gree.ant.vo.Cbase002VO;
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
public class Cbase002MO implements Cbase002BasicMO {

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:cbase002DAOImp")
    private Cbase002DAOImp cbase002DAOImp;

    @Override
    public List<Cbase002VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return formatC2(baseDAOImp.queryByCndPager(new Cbase002VO(),cnd,pager));
    }

    @Override
    public List<Cbase002VO> queryAllMenuByUSID(String usid) {
        return cbase002DAOImp.queryAllMenuByUSID(usid);
    }

    @Override
    public List<Cbase002VO> queryAllMenuByROID(String roid) {
        return cbase002DAOImp.queryAllMenuByROID(roid);
    }

    @Override
    public List<Cbase002VO> queryAllTransByCndPager(Condition cnd, Pager pager) {
        return formatC2(baseDAOImp.queryByCndPager(new Cbase002VO(),cnd,pager,"cbase003VOS"));
    }

    @Override
    public Cbase002VO insert(Cbase002VO cbase002VO) {
        return (Cbase002VO)baseDAOImp.insert(cbase002VO);
    }

    @Override
    public Integer deleteByPONO(String pono) {
        Cbase002VO cbase002VO = fetchByPONO(pono);
        if(cbase002VO!= null && cbase002VO.getStyp() == 2) {
            return baseDAOImp.deleteByName(new Cbase002VO(), pono);
        }else{
            return deleteWithByPONO(pono);
        }
    }

    @Override
    public Integer deleteWithByPONO(String pono) {
        Cbase002VO cbase002VO = fetchTransByPONO(pono);
        if(cbase002VO != null) {
            List<Cbase003VO> cbase003VOList = cbase002VO.getCbase003VOS();
            if (cbase003VOList != null) {
                for (Cbase003VO cbase003VO : cbase003VOList) {
                    deleteByPONO(cbase003VO.getFlno());
                }
            }
        }
        return baseDAOImp.deleteWith(cbase002VO, "cbase003VOS");
    }

    @Override
    public Integer updateByVO(Cbase002VO cbase002) {
        return baseDAOImp.update(cbase002);
    }

    @Override
    public Cbase002VO fetchByPONO(String pono) {
        return (Cbase002VO) baseDAOImp.fetchByName(new Cbase002VO(),pono);
    }

    @Override
    public Cbase002VO fetchTransByPONO(String pono) {
        return (Cbase002VO) baseDAOImp.fetchTransByNameCnd(new Cbase002VO(),pono,"cbase003VOS",null);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase002VO(),cnd);
    }

    private List<Cbase002VO> formatC2(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase002VO> cbase002VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase002VOS.add((Cbase002VO) iterator.next());
        }
        return  cbase002VOS;
    }
}
