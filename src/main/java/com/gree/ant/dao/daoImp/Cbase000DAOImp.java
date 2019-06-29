package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase000DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.util.DoubleUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase010VO;
import com.gree.ant.vo.util.ExportGradeOkrVO;
import com.gree.ant.vo.util.GradeVO;
import com.gree.ant.vo.util.ResultVO;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.TypeDescriptor;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.jdbc.ValueAdaptor;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Boolean updatePawd(String password, String userId) {
        String sqlStr = "update CBASE000 set pawd = @pawd where usid = @usid";
        Sql sql = Sqls.create(sqlStr);
        sql.params().set("usid",userId).set("pawd",password);
        this.getDao().execute(sql);
        return true;
    }

    @Override
    public List<ResultVO> queryAllUD() {
        String sqlStr = "select c.usid,c.dsca from cbase000 c order by c.dsca asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }

    /**
     *
     * @param cnd
     * TODO 此处之后应该引入一个科室主管表，以用来过滤掉该科室主管的特殊角色（USID NOT IN）
     * @return
     */
    @Override
    public List<GradeVO> queryAllGradeByPdat(Condition cnd,Condition condition,String officeNumber) {
        String sqlStr = "SELECT * from(SELECT a.USID,a.DSCA,(SELECT sum(CONS) from TBUSS005 b where b.CSID = a.USID and b.PTNO in\n" +
                "(SELECT c.PTNO from TBUSS001 c $condition))res from CBASE000 a where usid in (select usid from cbase010 $condition1) " +
                "and USID not in (select BossNumber from cbase020 where officeNumber = @officeNumber) and acco = @officeNumber)e ORDER BY res asc NULLS FIRST";
        Sql sql = Sqls.create(sqlStr);
        sql.setVar("condition",cnd).setVar("condition1",condition).setParam("officeNumber",officeNumber);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<GradeVO> gradeVOList = new ArrayList<>();
                while(rs.next()){
                    gradeVOList.add(new GradeVO(rs.getString("USID"),rs.getString("DSCA"), DoubleUtil.format_nice(rs.getDouble("res"))));
                }
                return gradeVOList;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(GradeVO.class);
    }

    @Override
    public List<ExportGradeOkrVO> queryALlGradeOkrByPdat(Condition cnd,String officeNumber) {
        String sqlStr = "SELECT * from(SELECT a.USID,a.CPID,a.DSCA,(SELECT sum(CONS) from TBUSS005 b where b.CSID = a.USID and b.PTNO in" +
                "(SELECT c.PTNO from TBUSS001 c $condition))res from CBASE000 a where usid in (select usid from cbase010) and USID not in " +
                "(select BossNumber from cbase020 where officeNumber = @officeNumber) and acco = @officeNumber)e ORDER BY res asc NULLS FIRST";
        Sql sql = Sqls.create(sqlStr);
        sql.setVar("condition",cnd).setParam("officeNumber",officeNumber);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<ExportGradeOkrVO> gradeOkrVOS = new ArrayList<>();
                while(rs.next()){
                    gradeOkrVOS.add(new ExportGradeOkrVO(rs.getString("USID"),rs.getString("CPID"),rs.getString("DSCA"),rs.getDouble("res")));
                }
                return gradeOkrVOS;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(ExportGradeOkrVO.class);
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

    @Override
    public Cbase000VO findUser(String usid) {
        String sqlStr = "SELECT USID,DSCA,ACCO,COMP,GROP,DEPT,STA2 FROM v_CBASE000 where usid = @usid";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                Cbase000VO cbase000VO = new Cbase000VO();
                while(rs.next()){
                    cbase000VO = new Cbase000VO(rs.getString("USID"),rs.getString("DSCA"),
                            rs.getString("ACCO"),rs.getString("COMP"),
                            rs.getString("DEPT"),rs.getString("GROP"),
                            rs.getInt("STA2"));
                }
                return cbase000VO;
            }
        });
        this.getDao().execute(sql);
        return sql.getObject(Cbase000VO.class);
    }

    @Override
    public Cbase000VO findUserDC(String usid) {
        String sqlStr = "SELECT DSCA,CPID,USID FROM CBASE000 where usid = @usid";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                Cbase000VO cbase000VO = new Cbase000VO();
                while(rs.next()){
                    cbase000VO.setDSCA(rs.getString("DSCA"));
                    cbase000VO.setCPID(rs.getString("CPID"));
                    cbase000VO.setUSID(rs.getString("USID"));
                }
                return cbase000VO;
            }
        });
        this.getDao().execute(sql);
        return sql.getObject(Cbase000VO.class);
    }

    @Override
    public List<Cbase000VO> queryAllUDByGropCnd(String grop, Condition cnd) {
        String sqlStr = "select c0.USID,c0.DSCA from CBASE000 c0 where c0.USID in (select USID from CBASE010 c10 where c10.GROP = @grop) $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grop",grop).setCondition(cnd);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Cbase000VO> cbase000VOS = new ArrayList<>();
                while(rs.next()){
                    cbase000VOS.add(new Cbase000VO(rs.getString("USID"),rs.getString("DSCA")));
                }
                return cbase000VOS;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(Cbase000VO.class);
    }
}
