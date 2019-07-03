package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ContractPayPlanDAO;
import com.gree.ant.vo.util.ContractPayPlanVO;
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
public class ContractPayPlanDAOImp extends BaseDAOImp<ContractPayPlanVO> implements ContractPayPlanDAO {

    @Inject("refer:daoHT")
    private Dao daoHT;

    @Override
    public List<ContractPayPlanVO> queryContractPayPlanInfor(String coid) {

        String sqlStr = "select tc5.pcon, tc5.fpic, tc5.modl, tc5.plva, tc5.fmor, tc5.fdat, tc5.modl from tcont002 tc2, tcont005 tc5 where tc2.cono = tc5.cono and tc2.coid = @coid";
        Sql sql = Sqls.create(sqlStr);
        sql.params().set("coid", coid);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<ContractPayPlanVO> list = new ArrayList<>();
                while (resultSet.next()) {
                    ContractPayPlanVO contractPayPlanVO = new ContractPayPlanVO();
                    contractPayPlanVO.setPcon(resultSet.getString("pcon") == null ? "" : resultSet.getString("pcon").trim());
                    contractPayPlanVO.setFpric(resultSet.getFloat("fpic"));
                    contractPayPlanVO.setPlva(resultSet.getFloat("plva"));
                    contractPayPlanVO.setCurrency(resultSet.getString("fmor") == null ? "" : resultSet.getString("fmor").trim());
                    contractPayPlanVO.setFdate(resultSet.getDate("fdat"));
                    contractPayPlanVO.setModl(resultSet.getString("modl") == null ? "" : resultSet.getString("modl").trim());
                    list.add(contractPayPlanVO);
                }
                return list;
            }
        });
        daoHT.execute(sql);
        return sql.getList(ContractPayPlanVO.class);
    }
}
