package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss009DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Tbuss009VO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@IocBean
public class Tbuss009DAOImp extends BaseDAOImp<Tbuss009VO> implements Tbuss009DAO{

    @Override
    public List<Tbuss009VO> queryAllDoc(String usid,Condition cnd,String stage,Pager pager) {
        String sqlStr = "select a.DOID,a.CDAT,a.USID,a.CSID,a.CTYP,a.STAT,a.STA2,a.TILT,a.UNAM,a.CNAM,a.CTYPNAM,a.STATNAM,a.STA2NAM " +
                "from V_TBUSS009 a $condition and (a.usid in (select b.usid from CBASE000 b where " +
                "b."+stage+" = (select c."+stage+" from CBASE000 c where c.usid = @usid)) or csid = @csid) order by cdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid).setParam("csid",usid);
        sql.setCondition(cnd);
        sql.setPager(pager);
        return DAOUtil.getT9(sql,this.getDao());
    }

    @Override
    public List<Tbuss009VO> queryAllDocNormal(Condition cnd, Pager pager) {
        String sqlStr = "select a.DOID,a.CDAT,a.USID,a.CSID,a.CTYP,a.STAT,a.STA2,a.TILT,a.UNAM,a.CNAM,a.CTYPNAM,a.STATNAM," +
                "a.STA2NAM from v_tbuss009 a $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        sql.setPager(pager);
        return DAOUtil.getT9(sql,this.getDao());
    }

    @Override
    public Integer countAllDoc(String usid, Condition cnd, String stage) {
        String sqlStr = "select count(*) from V_TBUSS009 a $condition and (a.usid in (select b.usid from CBASE000 b where " +
                "b."+stage+" = (select c."+stage+" from CBASE000 c where c.usid = @usid)) or csid = @csid) order by cdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid).setParam("csid",usid);
        sql.setCondition(cnd);
        return DAOUtil.getTiCount(sql,this.getDao());
    }
}
