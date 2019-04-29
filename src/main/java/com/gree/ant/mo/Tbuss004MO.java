package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss004DAOImp;
import com.gree.ant.mo.basic.Tbuss004BasicMO;
import com.gree.ant.vo.Tbuss004VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Tbuss004MO implements Tbuss004BasicMO{


    @Inject("refer:tbuss004DAOImp")
    private Tbuss004DAOImp tbuss004DAOImp;

    @Override
    public List<Tbuss004VO> queryAllByCnd(Condition cnd, Pager pager) {
        return tbuss004DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return tbuss004DAOImp.countByCnd(cnd);
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
        return tbuss004DAOImp.insert(tbuss004VO);
    }
}
