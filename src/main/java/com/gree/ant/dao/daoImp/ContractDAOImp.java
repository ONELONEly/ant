package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ContractDAO;
import com.gree.ant.util.DateUtil;
import com.gree.ant.vo.util.ContractVO;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.swing.text.DateFormatter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public List<ContractVO> queryContractInfor(String coid, String comp){
        String sqlStr = "select tc2.coid, tc2.pric, tc2.edat, cb.ds00, cb.ds01, cb.ds21 from tcont002 tc2, cbase014 cb where tc2.part = cb.ds01 and tc2.coid = @coid and cb.comp = @comp";
        Sql sql = Sqls.create(sqlStr);
        sql.params().set("coid", coid);
        sql.params().set("comp", comp);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<ContractVO> contractVOList = new ArrayList<>();
                while (resultSet.next()){
                    ContractVO contractVO = new ContractVO();
                    contractVO.setHtCoid(resultSet.getString("coid") == null ? "" : resultSet.getString("coid").trim());
                    contractVO.setHtPric(resultSet.getFloat("pric"));
                    contractVO.setHtEdat(resultSet.getString("edat") == null ? null : DateUtil.formatYMDDate(resultSet.getString("edat").replaceAll("/", "-")));
                    contractVO.setHtPartCoid(resultSet.getString("ds00") == null ? "" : resultSet.getString("ds00").trim());
                    contractVO.setHtPart(resultSet.getString("ds01") == null ? "" : resultSet.getString("ds01").trim());
                    contractVO.setPartBankNo(resultSet.getString("ds21") == null ? "" : resultSet.getString("ds21").trim());
                    contractVOList.add(contractVO);
                }
                return contractVOList;
            }
        });
        daoHT.execute(sql);
        return sql.getList(ContractVO.class);
    }
}
