package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.BaanTfgld008400DAO;
import com.gree.ant.vo.util.BaanTfgld008400VO;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaanTfgld008400DAOImp extends BaseDAOImp<BaanTfgld008400VO> implements BaanTfgld008400DAO {

    @Inject("refer:daoBaan197")
    private Dao daoBaan197;

    public List<BaanTfgld008400VO> queryBaanTfgld008400Infor(){
        String sqlStr = "select t.t$leac, t.t$subl, t.t$desc from ttfgld008400 t";
        sqlStr = sqlStr.replaceAll("\\$", "\\$\\$");
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<BaanTfgld008400VO> baanTfgld008400VOList = new ArrayList<>();
                while (resultSet.next()){
                    BaanTfgld008400VO baanTfgld008400VO = new BaanTfgld008400VO();
                    baanTfgld008400VO.setLeac(resultSet.getString("t$leac") == null ? "" : resultSet.getString("t$leac").trim());
                    baanTfgld008400VO.setSubl(resultSet.getString("t$subl") == null ? "" : resultSet.getString("t$subl").trim());
                    baanTfgld008400VO.setSubjectDesc(resultSet.getString("t$desc") == null ? "" : resultSet.getString("t$desc").trim());
                    baanTfgld008400VOList.add(baanTfgld008400VO);
                }
                return baanTfgld008400VOList;
            }
        });
        daoBaan197.execute(sql);
        return sql.getList(BaanTfgld008400VO.class);
    }
}
