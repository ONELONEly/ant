package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase015DAO;
import com.gree.ant.vo.Cbase015VO;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@IocBean
public class Cbase015DAOImp extends BaseDAOImp<Cbase015VO> implements Cbase015DAO{

    public Object queryAll(){
        String sqlStr = "select * from cbase005";
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                HashMap map = new HashMap();
                while(rs.next()){
                    map.put("rose","heck");
                }
                return map;
            }
        });
        this.getDao().execute(sql);
        return sql.getObject(HashMap.class);
    }
}
