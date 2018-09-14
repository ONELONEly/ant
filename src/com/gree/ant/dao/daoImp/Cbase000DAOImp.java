package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase000DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.util.GradeVO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
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
public class Cbase000DAOImp extends BaseDAOImp<Cbase000VO> implements Cbase000DAO{

    @Override
    public Boolean loginCheck(String usid, String pawd) {
        String sqlStr = "select * from cbase000 c where c.usid = @usid and c.pawd = @pawd";
        Sql sql = Sqls.create(sqlStr);
        sql.params().set("usid",usid).set("pawd",pawd);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                return rs.next();
            }
        });
        this.getDao().execute(sql);
        return sql.getBoolean();
    }

    @Override
    public List<ResultVO> queryAllUD() {
        String sqlStr = "select c.usid,c.dsca from cbase000 c order by c.dsca asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }

    @Override
    public List<GradeVO> queryAllGradeByPdat(Condition cnd,Condition condition) {
        String sqlStr = "SELECT * from(SELECT a.USID,a.DSCA,(SELECT sum(CONS) from TBUSS005 b where b.CSID = a.USID and b.PTNO in\n" +
                "(SELECT c.PTNO from TBUSS001 c $condition))res from CBASE000 a where usid in (select usid from cbase010 $condition1))e ORDER BY res asc NULLS FIRST";
        Sql sql = Sqls.create(sqlStr);
        sql.setVar("condition",cnd).setVar("condition1",condition);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<GradeVO> gradeVOList = new ArrayList<>();
                while(rs.next()){
                    gradeVOList.add(new GradeVO(rs.getString("USID"),rs.getString("DSCA"),rs.getDouble("res")));
                }
                return gradeVOList;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(GradeVO.class);
    }

    @Override
    public List<Cbase000VO> queryAllUser(Condition cnd, Pager pager) {
        String sqlStr = "SELECT USID,DSCA,PAWD,DEPTNAM,ACCONAM,JWWJ,GROPNAM,COMPNAM FROM v_CBASE000 $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        sql.setPager(pager);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Cbase000VO> cbase000VOList = new ArrayList<>();
                while(rs.next()){
                    cbase000VOList.add(new Cbase000VO(rs.getString("USID"),rs.getString("DSCA"),
                            rs.getString("PAWD"),rs.getString("GROPNAM"),rs.getString("DEPTNAM"),
                            rs.getString("COMPNAM"),rs.getString("ACCONAM"),rs.getString("JWWJ")));
                }
                return cbase000VOList;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(Cbase000VO.class);
    }
}
