package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Tbuss004BasicMO;
import com.gree.ant.vo.Tbuss004VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Tbuss004MO implements Tbuss004BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public List<Tbuss004VO> queryAllByCnd(Condition cnd, Pager pager) {
        return formatt04(baseDAOImp.queryByCndPager(new Tbuss004VO(),cnd,pager));
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Tbuss004VO(),cnd);
    }

    /**
     * Insert tbuss 004 vo.
     *
     * @param tbuss004VO the tbuss 004 vo
     * @return the tbuss 004 vo
     * @description 通过实体插入单条操作记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 04:09:42.
     */
    @Override
    public Tbuss004VO insert(Tbuss004VO tbuss004VO) {
        return (Tbuss004VO)baseDAOImp.insert(tbuss004VO);
    }

    private List<Tbuss004VO> formatt04(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Tbuss004VO> tbuss004VOS = new ArrayList<>();
        while(iterator.hasNext()){
            tbuss004VOS.add((Tbuss004VO) iterator.next());
        }
        return tbuss004VOS;
    }
}
