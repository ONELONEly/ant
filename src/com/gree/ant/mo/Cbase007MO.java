package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase007DAOImp;
import com.gree.ant.mo.basic.Cbase007BasicMO;
import com.gree.ant.vo.Cbase007VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase007MO implements Cbase007BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:cbase007DAOImp")
    private Cbase007DAOImp cbase007DAOImp;

    @Override
    public List<Cbase007VO> queryAllByPagerCnd(Pager pager, Condition cnd) {
        return formatC7(baseDAOImp.queryByCndPager(new Cbase007VO(),cnd,pager));
    }

    @Override
    public Cbase007VO insertByVO(Cbase007VO cbase007VO) {
        return (Cbase007VO)baseDAOImp.insert(cbase007VO);
    }

    @Override
    public Integer deleteByROID(String roid) {
        return baseDAOImp.deleteByName(new Cbase007VO(),roid);
    }

    @Override
    public Integer updateByVO(Cbase007VO cbase007VO) {
        return baseDAOImp.update(cbase007VO);
    }

    @Override
    public Cbase007VO fetchByROID(String roid) {
        return (Cbase007VO) baseDAOImp.fetchByName(new Cbase007VO(),roid);
    }

    @Override
    public Cbase007VO fetchTransByCndROID(Condition cnd, String roid,String primary) {
        return (Cbase007VO)baseDAOImp.fetchTransByNameCnd(new Cbase007VO(),roid,primary,cnd);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase007VO(),cnd);
    }

    @Override
    public Boolean insertCheck(String roid, String dsca) {
        Cnd cnd = Cnd.where("roid","=",roid).or("dsca","=",dsca);
        return cbase007DAOImp.insertCheck(cnd);
    }

    private List<Cbase007VO> formatC7(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase007VO> cbase007VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase007VOS.add((Cbase007VO) iterator.next());
        }
        return cbase007VOS;
    }
}
