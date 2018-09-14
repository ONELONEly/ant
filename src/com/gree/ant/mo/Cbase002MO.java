package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase002DAOImp;
import com.gree.ant.mo.basic.Cbase002BasicMO;
import com.gree.ant.vo.Cbase002VO;
import com.gree.ant.vo.Cbase003VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase002MO implements Cbase002BasicMO {

    @Inject("refer:cbase002DAOImp")
    private Cbase002DAOImp cbase002DAOImp;

    @Override
    public List<Cbase002VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase002DAOImp.queryByCndPager(cnd,pager);
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
        return cbase002DAOImp.queryByCndPager(cnd,pager,"cbase003VOS");
    }

    @Override
    public Cbase002VO insert(Cbase002VO cbase002VO) {
        return cbase002DAOImp.insert(cbase002VO);
    }

    @Override
    public Integer deleteByPONO(String pono) {
        Cbase002VO cbase002VO = fetchByPONO(pono);
        if(cbase002VO!= null && cbase002VO.getStyp() == 2) {
            return cbase002DAOImp.deleteByName(pono);
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
        return cbase002DAOImp.deleteWith(cbase002VO, "cbase003VOS");
    }

    @Override
    public Integer updateByVO(Cbase002VO cbase002) {
        return cbase002DAOImp.update(cbase002);
    }

    @Override
    public Cbase002VO fetchByPONO(String pono) {
        return cbase002DAOImp.fetchByName(pono);
    }

    @Override
    public Cbase002VO fetchTransByPONO(String pono) {
        return cbase002DAOImp.fetchTransByNameCnd(pono,"cbase003VOS",null);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase002DAOImp.countByCnd(cnd);
    }
}
