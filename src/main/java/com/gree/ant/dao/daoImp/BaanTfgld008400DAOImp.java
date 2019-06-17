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

//okay
public class BaanTfgld008400DAOImp extends BaseDAOImp<BaanTfgld008400VO> implements BaanTfgld008400DAO {

    @Inject("refer:daoBaan197")
    private Dao daoBaan197;

    public List<BaanTfgld008400VO> queryBaanTfgld008400Infor(){
        String sqlStr = "select t.t$leac, t.t$subl, t.t$desc, t.t$atyp, t.t$dbcr, t.t$dim1, t.t$dim2, t.t$dim3, t.t$dim4, t.t$dim5 from ttfgld008400 t where t.t$subl = 0 and t.t$bloc in ('0', '1') ";
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
                    baanTfgld008400VO.setAtyp(resultSet.getByte("t$atyp"));
                    baanTfgld008400VO.setDbcr(resultSet.getByte("t$dbcr"));
                    baanTfgld008400VO.setDim1(resultSet.getByte("t$dim1"));
                    baanTfgld008400VO.setDim2(resultSet.getByte("t$dim2"));
                    baanTfgld008400VO.setDim3(resultSet.getByte("t$dim3"));
                    baanTfgld008400VO.setDim4(resultSet.getByte("t$dim4"));
                    baanTfgld008400VO.setDim5(resultSet.getByte("t$dim5"));
                    baanTfgld008400VOList.add(baanTfgld008400VO);
                }
                return baanTfgld008400VOList;
            }
        });
        daoBaan197.execute(sql);
        return sql.getList(BaanTfgld008400VO.class);
    }
}
