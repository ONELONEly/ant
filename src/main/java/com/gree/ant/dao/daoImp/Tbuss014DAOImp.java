package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss014DAO;
import com.gree.ant.vo.Tbuss014VO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@IocBean
public class Tbuss014DAOImp extends BaseDAOImp<Tbuss014VO> implements Tbuss014DAO{

    @Override
    public List<Tbuss014VO> queryAllByCndPager(Condition condition, Pager pager) {
        String sqlStr = "select raid,cdat,unam,cnam,statnam,sta2nam,sta3nam,titl,synonam,ydat  from v_tbuss014 $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(condition);
        sql.setPager(pager);
        return formatResult(sql,this.getDao());
    }

    private List<Tbuss014VO> formatResult(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss014VO> tbuss014VOList = new ArrayList<>();
                while(rs.next()){
                    tbuss014VOList.add(new Tbuss014VO(rs.getString("raid"),rs.getDate("cdat"),
                            rs.getString("titl"),rs.getString("unam"),rs.getString("cnam"),
                            rs.getString("statnam"),rs.getString("sta2nam"),rs.getString("sta3nam"),
                            rs.getString("synonam"),rs.getDate("ydat")));
                }
                return tbuss014VOList;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss014VO.class);
    }
}
