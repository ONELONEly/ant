package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase006DAOImp;
import com.gree.ant.mo.basic.Cbase006BasicMO;
import com.gree.ant.vo.Cbase006VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase006MO implements Cbase006BasicMO{

    @Inject("refer:cbase006DAOImp")
    private Cbase006DAOImp cbase006DAOImp;

    @Override
    public List<Cbase006VO> queryAllByCnd(Condition cnd, Pager pager) {
        return cbase006DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Cbase006VO insertByVO(Cbase006VO cbase006VO) {
        return cbase006DAOImp.insert(cbase006VO);
    }

    @Override
    public Integer deleteByDept(String dept) {
        return cbase006DAOImp.deleteByName(dept);
    }

    @Override
    public Integer updateByVO(Cbase006VO cbase006VO) {
        return cbase006DAOImp.update(cbase006VO);
    }

    @Override
    public Cbase006VO fetchByDept(String dept) {
        return cbase006DAOImp.fetchByName(dept);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase006DAOImp.countByCnd(cnd);
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
}
