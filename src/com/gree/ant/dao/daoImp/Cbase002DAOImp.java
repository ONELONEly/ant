package com.gree.ant.dao.daoImp;


import com.gree.ant.dao.Cbase002DAO;
import com.gree.ant.vo.Cbase002VO;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
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
public class Cbase002DAOImp implements Cbase002DAO{

    @Inject("refer:daoFX")
    private Dao dao;

    @Override
    public List<Cbase002VO> queryAllMenuByUSID(String usid) {
        String sqlStr = "SELECT a.* from V_CBASE002 a where PONO in (select DISTINCT b.pono from CBASE004 b WHERE  b.ROID in " +
                "(select c.ROID from CBASE005 c where c.USID = @usid))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        return getResult(sql);
    }

    @Override
    public List<Cbase002VO> queryAllMenuByROID(String roid) {
        String sqlStr = "select a.* from V_CBASE002 a where a.pono not in (select b.pono from CBASE004 b where b.roid = @roid)";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("roid",roid);
        return getResult(sql);
    }

    private List<Cbase002VO> getResult(Sql sql){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Cbase002VO> cbase002VOList = new ArrayList<>();
                while(rs.next()){
                    cbase002VOList.add(new Cbase002VO(rs.getString("pono"),rs.getString("dsca"),rs.getInt("styp"),
                            rs.getString("stypnam"),rs.getString("purl")));
                }
                return cbase002VOList;
            }
        });
        dao.execute(sql);
        return sql.getList(Cbase002VO.class);
    }
}
