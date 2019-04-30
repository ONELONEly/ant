package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase016DAO;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.Tbuss003VO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@IocBean
public class Cbase016DAOImp extends BaseDAOImp<Cbase016VO> implements Cbase016DAO {


    @Override
    public List<Cbase016VO> queryAllSearch(Condition condition) {
        String sqlStr = "select * from cbase016 $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(condition);
        return queryResultFormat(sql,this.getDao());
    }

    private List<Cbase016VO> queryResultFormat(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Cbase016VO> cbase016VOS = new ArrayList<>();
                while(rs.next()){
                    cbase016VOS.add(new Cbase016VO(rs.getString("ctyp"),rs.getString("dsca")));
                }
                return cbase016VOS;
            }
        });
        dao.execute(sql);
        return sql.getList(Cbase016VO.class);
    }
}
