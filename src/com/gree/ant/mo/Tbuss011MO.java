package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss011DAOImp;
import com.gree.ant.mo.basic.TBuss011BasicMO;
import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class Tbuss011MO implements TBuss011BasicMO {



    @Inject("refer:tbuss011DAOImp")
    private Tbuss011DAOImp tbuss011DAOImp;

    @Inject
    private Tbuss012MO tbuss012MO;

    @Override
    public Tbuss011VO insert(final Tbuss011VO tbuss011VO, final List<Tbuss012VO> tbuss012VOS) {
        final Tbuss011VO[] tbuss011 = new Tbuss011VO[1];
        Trans.exec(new Atom() {
            @Override
            public void run() {
                 tbuss011[0] = tbuss011DAOImp.insert(tbuss011VO);
                for (Tbuss012VO tbuss012VO:tbuss012VOS){
                    tbuss012VO.setOkid(tbuss011[0].getOKID());
                    tbuss012MO.insertGoal(tbuss012VO);
                }
            }
        });
        return tbuss011[0];
    }

    @Override
    public Integer update(final Tbuss011VO tbuss011VO, final List<Tbuss012VO> tbuss012VOS) {
        final int[] res = {0};
        Trans.exec(new Atom() {
            @Override
            public void run() {
                res[0] = tbuss011DAOImp.update(tbuss011VO);
                tbuss012MO.updateOkr(tbuss011VO.getOKID(),tbuss012VOS);
            }
        });
        return res[0];
    }

    @Override
    public Tbuss011VO fetchByOkid(Integer okid) {
        return tbuss011DAOImp.fetchByID(okid);
    }

    @Override
    public Tbuss011VO fetchTransByOkid(Integer okid) {
        Tbuss011VO tbuss011VO = tbuss011DAOImp.fetchTransByIdCnd(okid,"tbuss012VOS",null);
        if(tbuss011VO.getTbuss012VOS() != null){
            List<Tbuss012VO> tbuss012VOS = new ArrayList<>();
            for(Tbuss012VO tbuss012VO:tbuss011VO.getTbuss012VOS()){
                tbuss012VO = tbuss012MO.fetchTransByVO(tbuss012VO);
                tbuss012VOS.add(tbuss012VO);
            }
            tbuss011VO.setTbuss012VOS(tbuss012VOS);
        }
        return tbuss011VO;
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
