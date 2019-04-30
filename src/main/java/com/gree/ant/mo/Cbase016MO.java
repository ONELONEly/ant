package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase016DAOImp;
import com.gree.ant.mo.basic.Cbase016BasicMO;
import com.gree.ant.vo.Cbase016VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;


@IocBean
public class Cbase016MO implements Cbase016BasicMO{


    @Inject("refer:cbase016DAOImp")
    private Cbase016DAOImp cbase016DAOImp;

    /**
     * Query all by cnd pager cbase 016 vo.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the cbase 016 vo
     * @description 通过Cnd, Pager查询多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 06:09:40.
     */
    @Override
    public List<Cbase016VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase016DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public List<Cbase016VO> queryAllSearch() {
        return cbase016DAOImp.queryAllSearch(Cnd.NEW().and("ctyp",">",99));
    }

    public Cbase016VO fetchByName(String ctyp) {
        return cbase016DAOImp.fetchByName(ctyp);
    }
}
