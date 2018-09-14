package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss011DAOImp;
import com.gree.ant.mo.basic.TBuss011BasicMO;
import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.request.OkrVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.sql.Connection;
import java.util.List;

@IocBean
public class Tbuss011MO implements TBuss011BasicMO {



    @Inject("refer:tbuss011DAOImp")
    private Tbuss011DAOImp tbuss011DAOImp;

    @Override
    public Tbuss011VO insert(final OkrVO okrVO) {

        Trans.exec(Connection.TRANSACTION_READ_COMMITTED, new Atom() {
            @Override
            public void run() {
//                Tbuss011VO tbuss011VO = tbuss011DAOImp.insert(okrVO.getOkrManager());
            }
        });
//        return (Tbuss011VO) tbuss011DAOImp.insert(tbuss011VO);
        return null;
    }

    @Override
    public List<Tbuss011VO> queryAllByMsgPager(Pager pager, String msg) {
        Cnd cnd = Cnd.NEW();
        return tbuss011DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Integer countByMsg(String msg) {
        Cnd cnd = Cnd.NEW();
        return tbuss011DAOImp.countByCnd(cnd);
    }
}
