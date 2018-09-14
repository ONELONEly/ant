package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase007DAOImp;
import com.gree.ant.mo.basic.Cbase007BasicMO;
import com.gree.ant.vo.Cbase007VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase007MO implements Cbase007BasicMO{

    @Inject("refer:cbase007DAOImp")
    private Cbase007DAOImp cbase007DAOImp;

    @Override
    public List<Cbase007VO> queryAllByPagerCnd(Pager pager, Condition cnd) {
        return cbase007DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Cbase007VO insertByVO(Cbase007VO cbase007VO) {
        return cbase007DAOImp.insert(cbase007VO);
    }

    @Override
    public Integer deleteByROID(String roid) {
        return cbase007DAOImp.deleteByName(roid);
    }

    @Override
    public Integer updateByVO(Cbase007VO cbase007VO) {
        return cbase007DAOImp.update(cbase007VO);
    }

    @Override
    public Cbase007VO fetchByROID(String roid) {
        return cbase007DAOImp.fetchByName(roid);
    }

    @Override
    public Cbase007VO fetchTransByCndROID(Condition cnd, String roid,String primary) {
        return cbase007DAOImp.fetchTransByNameCnd(roid,primary,cnd);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase007DAOImp.countByCnd(cnd);
    }

    @Override
    public Boolean insertCheck(String roid, String dsca) {
        Cnd cnd = Cnd.where("roid","=",roid).or("dsca","=",dsca);
        return cbase007DAOImp.insertCheck(cnd);
    }
}
