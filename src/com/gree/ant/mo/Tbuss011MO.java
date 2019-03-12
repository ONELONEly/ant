package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss011DAOImp;
import com.gree.ant.mo.basic.TBuss011BasicMO;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;
import com.gree.ant.vo.Tbuss013VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
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
    public Integer delete(final Integer[] okids) {
        final Integer[] res = new Integer[1];
        res[0] = 0;
        Trans.exec(new Atom() {
            @Override
            public void run() {
                for (Integer okid:okids) {
                    if(tbuss011DAOImp.fetchByID(okid).getStat() == 0) {
                        tbuss012MO.deleteByOKID(okid);
                        res[0] = tbuss011DAOImp.deleteByID(okid);
                    }
                }
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
        Tbuss011VO tbuss011VO = new Tbuss011VO();
        if(okid != null) {
            tbuss011VO = tbuss011DAOImp.fetchTransByIdCnd(okid, "tbuss012VOS", null);
        }

        if(tbuss011VO == null){
            tbuss011VO = new Tbuss011VO();
        }

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
    public QueryResult mQueryAllByMsgPager(Integer pageSize, Integer pageNumber, Cbase000VO cbase000VO,
                                           String msg,String mdat,String office) {
        int sta2 = cbase000VO.getSTA2(),count;
        String usid = cbase000VO.getUSID(),comp = cbase000VO.getCOMP(),grop = cbase000VO.getGROP(),
                dept = cbase000VO.getDEPT(),acco = cbase000VO.getACCO();
        Pager pager;
        SqlExpressionGroup e0 = null,e2 = null;
        SqlExpressionGroup e1 = Cnd.exps("asid", "=", usid).or("boss", "=", usid);
        Cnd cnd = Cnd.NEW();
        cnd.and("0","=","0");
        List<Tbuss011VO> tbuss011VOS;
        if(msg != null){
            e0 = Cnd.exps ("asid","like","%"+msg+"%").or("boss","like","%"+msg+"%").
                    or("anam","like","%"+msg+"%").or("bnam","like","%"+msg+"%");
        }
        if(mdat != null){
            e2 = Cnd.exps("mdat","like","%"+mdat+"%");
        }
        if(sta2 == 0){
            cnd = cnd.and(e1).and(e0).and(e2);
            count = tbuss011DAOImp.countByCnd(cnd);
            pager = TableUtil.formatPager(pageSize,pageNumber,count);
            tbuss011VOS = tbuss011DAOImp.queryByCndPager(cnd,pager);
        }else if (sta2 == 1) {
            cnd = cnd.and(e0).and(e2);
            count = tbuss011DAOImp.countByGrop(grop, cnd);
            pager = TableUtil.formatPager(pageSize, pageNumber, count);
            tbuss011VOS = tbuss011DAOImp.queryByGrop(grop,cnd, pager);
        }else if (sta2 == 2) {
            cnd = cnd.and(e0).and(e2);
            count = tbuss011DAOImp.countByAcco(acco, cnd);
            pager = TableUtil.formatPager(pageSize, pageNumber, count);
            tbuss011VOS = tbuss011DAOImp.queryByAcco(acco,cnd, pager);
        }else{
            if(office == null) {
                if (sta2 == 3) {
                    cnd = cnd.and(e0).and(e2);
                    count = tbuss011DAOImp.countByDept(dept, cnd);
                    pager = TableUtil.formatPager(pageSize, pageNumber, count);
                    tbuss011VOS = tbuss011DAOImp.queryByDept(dept, cnd, pager);
                } else {
                    cnd = cnd.and(e0).and(e2);
                    count = tbuss011DAOImp.countByComp(comp, cnd);
                    pager = TableUtil.formatPager(pageSize, pageNumber, count);
                    tbuss011VOS = tbuss011DAOImp.queryByComp(comp, cnd, pager);
                }
            }else{
                cnd = cnd.and(e0).and(e2);
                count = tbuss011DAOImp.countByAcco(office, cnd);
                pager = TableUtil.formatPager(pageSize, pageNumber, count);
                tbuss011VOS = tbuss011DAOImp.queryByAcco(office,cnd, pager);
            }
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setPager(pager);
        queryResult.setList(tbuss011VOS);
        return queryResult;
    }

    @Override
    public List<Tbuss011VO> uQueryAllByMsgPager(Pager pager, String usid,String msg,String mdat) {
        Cnd cnd = Cnd.NEW();
        SqlExpressionGroup e0 = null,e2 = null;
        SqlExpressionGroup e1 = Cnd.exps("asid", "=", usid).or("boss", "=", usid);
        if(msg != null){
            e0 = Cnd.exps ("asid","like","%"+msg+"%").or("boss","like","%"+msg+"%").
                    or("anam","like","%"+msg+"%").or("bnam","like","%"+msg+"%");
        }
        if(mdat != null){
            e2 = Cnd.exps("mdat","like","%"+mdat+"%");
        }
        return tbuss011DAOImp.queryByCndPager(cnd.and(e1).and(e0).and(e2),pager);
    }

    @Override
    public Integer countByMsg(String msg,String usid,String mdat) {
        Cnd cnd = Cnd.NEW();
        SqlExpressionGroup e0 = null,e2 = null;
        SqlExpressionGroup e1 = Cnd.exps("asid", "=", usid).or("boss", "=", usid);
        if(msg != null){
            e0 = Cnd.exps ("asid","like","%"+msg+"%").or("boss","like","%"+msg+"%").
                    or("anam","like","%"+msg+"%").or("bnam","like","%"+msg+"%");
        }
        if(mdat != null){
            e2 = Cnd.exps("mdat","like","%"+mdat+"%");
        }
        return tbuss011DAOImp.countByCnd(cnd.and(e1).and(e0).and(e2));
    }


    @Override
    public void pushToLeader(Integer[] okids) {
        Cnd cnd = Cnd.NEW();
        if (okids != null){
            cnd.and("okid","in",okids);
        }
        tbuss011DAOImp.pushToLeader(cnd);
    }

    @Override
    public void backToUser(Integer[] okids) {
        Cnd cnd = Cnd.NEW();
        if (okids != null){
            cnd.and("okid","in",okids);
        }
        tbuss011DAOImp.backToUser(cnd);
    }

    @Override
    public void copyOkr(final Integer[] okids) {
        Trans.exec(new Atom() {
            @Override
            public void run() {
                for (Integer okid : okids){
                    Tbuss011VO tbuss011VO = fetchTransByOkid(okid);
                    tbuss011VO.setStat(0);
                    insert(tbuss011VO,getNoneGradeTran(tbuss011VO.getTbuss012VOS()));
                }
            }
        });
    }

    private List<Tbuss012VO> getNoneGradeTran(List<Tbuss012VO> tbuss012VOS){
        List<Tbuss012VO> tbuss012VOList = new ArrayList<>();
        for (Tbuss012VO tbuss012VO:tbuss012VOS){
            List<Tbuss013VO> tbuss013VOList = new ArrayList<>();
            List<Tbuss013VO> tbuss013VOS = tbuss012VO.getTbuss013VOS();
            for (Tbuss013VO tbuss013VO:tbuss013VOS){
                tbuss013VO.setZgrad(null);
                tbuss013VO.setMgrad(null);
                tbuss013VOList.add(tbuss013VO);
            }
            tbuss012VO.setTbuss013VOS(tbuss013VOList);
            tbuss012VOList.add(tbuss012VO);
        }
        return tbuss012VOList;
    }
}
