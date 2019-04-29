package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ContractDAO;
import com.gree.ant.vo.util.ContractVO;
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
public class ContractDAOImp extends BaseDAOImp<ContractVO> implements ContractDAO {

    @Inject("refer:daoHT")
    private Dao daoHT;

    /**
     *
     * @param coid 合同编号
     * @return 返回合同编号、对方单位、结算条件、结算币种、结算日期、结算金额、结算方式
     */
    public List<ContractVO> queryContractInfor(String coid){
        String sqlStr = "select t.coid, t.part, tc05.pcon, tc05.fmor, tc05.fdat, tc05.fpic, tc05.modl  from tcont002 t, tcont005 tc05 where t.cono = tc05.cono and t.coid = @coid";
        Sql sql = Sqls.create(sqlStr);
        sql.params().set("coid", coid);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<ContractVO> contractVOList = new ArrayList<>();
                while (resultSet.next()){
                    ContractVO contractVO = new ContractVO();
                    contractVO.setHtCoid(resultSet.getString("coid") == null ? "" : resultSet.getString("coid").trim());
                    contractVO.setHtPart(resultSet.getString("part") == null ? "" : resultSet.getString("part").trim());
                    contractVO.setHtPcon(resultSet.getString("pcon") == null ? "" : resultSet.getString("pcon").trim());
                    contractVO.setHtFmor(resultSet.getString("fmor") == null ? "" : resultSet.getString("fmor").trim());
                    contractVO.setHtFdat(resultSet.getDate("fdat"));
                    contractVO.setHtFpic(resultSet.getDouble("fpic"));
                    contractVO.setHtModl(resultSet.getString("modl") == null ? "" : resultSet.getString("modl").trim());
                    contractVOList.add(contractVO);
                }
                return contractVOList;
            }
        });
        daoHT.execute(sql);
        return sql.getList(ContractVO.class);
    }
}
