package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss005DAO;
import com.gree.ant.vo.Tbuss005VO;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Tbuss005DAOImp extends BaseDAOImp<Tbuss005VO> implements Tbuss005DAO{

    @Override
    public Integer updateByVO(Tbuss005VO tbuss005VO) {
        String sqlStr = "update tbuss005 set cons = @cons,remk =@remk where ptno = @ptno and pjno = @pjno and csid = @csid";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("cons",tbuss005VO.getCons()).setParam("ptno",tbuss005VO.getPtno())
                .setParam("pjno",tbuss005VO.getPjno()).setParam("csid",tbuss005VO.getCsid())
                .setParam("remk",tbuss005VO.getRemk());
        this.getDao().execute(sql);
        return 1;
    }

    @Override
    public Integer deleteByPtno(String ptno) {
        String sqlStr = "delete Tbuss005 where ptno = @ptno";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("ptno",ptno);
        this.getDao().execute(sql);
        return 1;
    }

    @Override
    public Integer deleteByPtnoPjno(String ptno, String pjno) {
        String sqlStr = "delete Tbuss005 where ptno = @ptno and pjno = @pjno";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("ptno",ptno).setParam("pjno",pjno);
        this.getDao().execute(sql);
        return 1;
    }
}
