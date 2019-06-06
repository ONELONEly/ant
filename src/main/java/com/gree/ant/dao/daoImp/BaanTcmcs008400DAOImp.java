package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.BaanTcmcs008400DAO;
import com.gree.ant.vo.util.BaanTcmcs008400VO;
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

//okay
public class BaanTcmcs008400DAOImp extends BaseDAOImp<BaanTcmcs008400VO> implements BaanTcmcs008400DAO {

    @Inject("refer:daoBaan197")
    private Dao daoBaan197;

    public List<BaanTcmcs008400VO> queryBaanTcmcs008400Infor(){
        String sqlStr = "select distinct a.t$bcur, a.t$ccur, a.t$rtyp, a.t$stdt, a.t$rate, a.t$apdt from ttcmcs008400 a where  a.t$apdt =  (select max(t$apdt) from ttcmcs008400 b where  b.t$bcur = a.t$bcur and b.t$ccur = a.t$ccur and b.t$rtyp =a.t$rtyp) and a.t$rtyp = 'STD' order by a.t$bcur, a.t$ccur, a.t$rtyp, a.t$apdt desc";
        sqlStr = sqlStr.replaceAll("\\$", "\\$\\$");
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<BaanTcmcs008400VO> baanTcmcs008400VOList = new ArrayList<>();
                while (resultSet.next()){
                    BaanTcmcs008400VO baanTcmcs008400VO = new BaanTcmcs008400VO();
                    baanTcmcs008400VO.setBcur(resultSet.getString("t$bcur") == null ? "" : resultSet.getString("t$bcur").trim());
                    baanTcmcs008400VO.setCcur(resultSet.getString("t$ccur") == null ? "" : resultSet.getString("t$ccur").trim());
                    baanTcmcs008400VO.setRtyp(resultSet.getString("t$rtyp") == null ? "" : resultSet.getString("t$rtyp").trim());
                    baanTcmcs008400VO.setStdt(resultSet.getDate("t$stdt"));
                    baanTcmcs008400VO.setRate(resultSet.getDouble("t$rate"));
                    baanTcmcs008400VOList.add(baanTcmcs008400VO);
                }
                return baanTcmcs008400VOList;
            }
        });
        daoBaan197.execute(sql);
        return sql.getList(BaanTcmcs008400VO.class);
    }
}
