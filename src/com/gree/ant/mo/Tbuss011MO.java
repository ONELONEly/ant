package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.TBuss011BasicMO;
import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Tbuss011MO implements TBuss011BasicMO {

    @Inject
    private BaseDAOImp baseDAOImp;

    @Override
    public Tbuss011VO insert(Tbuss011VO tbuss011VO) {
        return (Tbuss011VO) baseDAOImp.insert(tbuss011VO);
    }

    @Override
    public List<Tbuss011VO> queryAllByMsgPager(Pager pager, String msg) {
        Cnd cnd = Cnd.NEW();
        return formatt011(baseDAOImp.queryByCndPager(new Tbuss011VO(),cnd,pager));
    }

    @Override
    public Integer countByMsg(String msg) {
        Cnd cnd = Cnd.NEW();
        return baseDAOImp.countByCnd(new Tbuss011VO(),cnd);
    }

    private List<Tbuss011VO> formatt011(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Tbuss011VO> tbuss011VOS = new ArrayList<>();
        while(iterator.hasNext()){
            tbuss011VOS.add((Tbuss011VO) iterator.next());
        }
        return tbuss011VOS;
    }
}
