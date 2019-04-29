package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase014DAOImp;
import com.gree.ant.mo.basic.Cbase014BasicMO;
import com.gree.ant.vo.Cbase014VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase014MO implements Cbase014BasicMO{


    @Inject("refer:cbase014DAOImp")
    private Cbase014DAOImp cbase014DAOImp;

    /**
     * Query all by cns list.
     *
     * @param cnd   过滤条件
     * @param pager 分页条件
     * @return the list
     * @description 通过cnd、pager查询所有信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 03:09:53.
     */
    @Override
    public List<Cbase014VO> queryAllByCnd(Condition cnd, Pager pager) {
        return cbase014DAOImp.queryByCndPager(cnd,pager);
    }
}
