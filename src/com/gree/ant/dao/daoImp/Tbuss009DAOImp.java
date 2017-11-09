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
public class Tbuss009DAOImp implements Tbuss009DAO{

    @Inject("refer:daoFX")
    private Dao dao;

    @Override
    public List<Tbuss009VO> queryAllDoc(String usid,Condition cnd,String stage,Pager pager) {
        String sqlStr = "select * from V_TBUSS009 a $condition and (a.usid in (select b.usid from CBASE000 b where " +
                "b."+stage+" = (select c."+stage+" from CBASE000 c where c.usid = @usid)) or csid = @csid) order by cdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid).setParam("csid",usid);
        sql.setCondition(cnd);
        sql.setPager(pager);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss009VO> tbuss009VOList = new ArrayList<>();
                while(rs.next()){
                    tbuss009VOList.add(new Tbuss009VO(rs.getLong("doid"),
                            rs.getDate("cdat"),rs.getString("usid"),rs.getString("unam"),rs.getString("csid"),
                            rs.getString("cnam"),rs.getString("ctyp"),rs.getString("ctypnam"),rs.getInt("stat"),
                            rs.getString("statnam"),rs.getInt("sta2"),rs.getString("sta2nam"),rs.getString("tilt")));
                }
                return tbuss009VOList;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss009VO.class);
    }

    @Override
    public Integer countAllDoc(String usid, Condition cnd, String stage) {
        String sqlStr = "select count(*) from V_TBUSS009 a $condition and (a.usid in (select b.usid from CBASE000 b where " +
                "b."+stage+" = (select c."+stage+" from CBASE000 c where c.usid = @usid)) or csid = @csid) order by cdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid).setParam("csid",usid);
        sql.setCondition(cnd);
        return DAOUtil.getTiCount(sql,dao);
    }
}
