package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase006DAOImp;
import com.gree.ant.mo.basic.Cbase006BasicMO;
import com.gree.ant.vo.Cbase006VO;
import com.gree.ant.vo.ValueObject;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase006MO implements Cbase006BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:cbase006DAOImp")
    private Cbase006DAOImp cbase006DAOImp;

    @Override
    public List<Cbase006VO> queryAllByCnd(Condition cnd, Pager pager) {
        return formatC6(baseDAOImp.queryByCndPager(new Cbase006VO(),cnd,pager));
    }

    @Override
    public Cbase006VO insertByVO(Cbase006VO cbase006VO) {
        return (Cbase006VO)baseDAOImp.insert(cbase006VO);
    }

    @Override
    public Integer deleteByDept(String dept) {
        return baseDAOImp.deleteByName(new Cbase006VO(),dept);
    }

    @Override
    public Integer updateByVO(Cbase006VO cbase006VO) {
        return baseDAOImp.update(cbase006VO);
    }

    @Override
    public Cbase006VO fetchByDept(String dept) {
        return (Cbase006VO) baseDAOImp.fetchByName(new Cbase006VO(),dept);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase006VO(),cnd);
    }

    @Override
    public Boolean insertCheck(String dept, String dsca) {
        Cnd cnd = Cnd.where("dept","=",dept).or("dsca","=",dsca);
        return cbase006DAOImp.insertCheck(cnd);
    }

    @Override
    public List<ResultVO> queryAllDD() {
        return cbase006DAOImp.queryAllDD();
    }

    private List<Cbase006VO> formatC6(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase006VO> cbase006VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase006VOS.add((Cbase006VO) iterator.next());
        }
        return cbase006VOS;
    }
}
