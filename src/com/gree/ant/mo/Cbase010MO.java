package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase010DAOImp;
import com.gree.ant.mo.basic.Cbase010BasicMO;
import com.gree.ant.vo.Cbase010VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase010MO implements Cbase010BasicMO{

    @Inject("refer:cbase010DAOImp")
    private Cbase010DAOImp cbase010DAOImp;

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public Cbase010VO insert(Cbase010VO cbase010VO) {
        return (Cbase010VO) baseDAOImp.insert(cbase010VO);
    }

    @Override
    public Integer delete(Cbase010VO cbase010VO) {
        return baseDAOImp.delete(cbase010VO);
    }

    @Override
    public List<Cbase010VO> queryByCnd(Condition cnd) {
        return formatC10(baseDAOImp.queryByCndPager(new Cbase010VO(),cnd,null));
    }

    private List<Cbase010VO> formatC10(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase010VO> cbase010VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase010VOS.add((Cbase010VO) iterator.next());
        }
        return  cbase010VOS;
    }
}
