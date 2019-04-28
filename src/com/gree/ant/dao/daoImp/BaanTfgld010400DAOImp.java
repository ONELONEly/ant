package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.BaanTfgld010400DAO;
import com.gree.ant.vo.util.BaanTfgld010400VO;
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

public class BaanTfgld010400DAOImp extends BaseDAOImp<BaanTfgld010400VO> implements BaanTfgld010400DAO {

    @Inject("refer:daoBaan197")
    private Dao daoBaan197;

    public List<BaanTfgld010400VO> queryBaanTfgld010400Infor(){
        String sqlStr = "select t.t$dimx, t.t$desc from ttfgld010400 t where t.t$dtyp = 3 and t.t$bloc = 1";
        sqlStr = sqlStr.replaceAll("\\$", "\\$\\$");
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<BaanTfgld010400VO> baanTfgld010400VOList = new ArrayList<>();
                while (resultSet.next()){
                    BaanTfgld010400VO baanTfgld010400VO = new BaanTfgld010400VO();
                    baanTfgld010400VO.setDimx(resultSet.getString("t$dimx") == null ? "" : resultSet.getString("t$dimx").trim());
                    baanTfgld010400VO.setSubAccountDesc(resultSet.getString("t$desc") == null ? "" : resultSet.getString("t$desc").trim());
                    baanTfgld010400VOList.add(baanTfgld010400VO);
                }
                return baanTfgld010400VOList;
            }
        });
        daoBaan197.execute(sql);
        return sql.getList(BaanTfgld010400VO.class);
    }
}
