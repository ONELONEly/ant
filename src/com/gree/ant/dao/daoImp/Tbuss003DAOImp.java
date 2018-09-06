package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss003DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.util.TaskUtilVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
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
public class Tbuss003DAOImp implements Tbuss003DAO{

    @Inject("refer:daoFX")
    private Dao dao;


    @Override
    public List<Tbuss003VO> queryAllByCndPager(Condition cnd, Pager pager) {
        String sqlStr = "SELECT a.taid,a.titl,a.cdat,a.unam,a.cnam,a.sta1nam,a.ptypnam,a.sta2nam,a.pdat,a.knam,a.sta3nam," +
                "a.synonam,a.punonam,a.fdat,a.tdat,a.adat,a.perc,a.csid,a.acconam,a.ptnonam,a.stag from V_TBUSS003 a $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        sql.setPager(pager);
        return queryResultFormat(sql,dao);
    }

    @Override
    public List<Tbuss003VO> queryAllGradeTask(String usid, Pager pager,Condition condition) {
        String sqlStr = "select t.taid,t.titl,t.cdat,t.cnam,t.sta1nam,t.pdat,t.knam,t.synonam,t.punonam,t.fdat,t.tdat,t.adat," +
                "t.perc,(select count(*) from TBUSS010 s where s.USID = @usid AND s.TAID = t.TAID)coun from V_TBUSS003 t $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setPager(pager);
        sql.setCondition(condition);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss003VO> tbuss003VOS = new ArrayList<>();
                while(rs.next()){
                    Tbuss003VO tbuss003VO = new Tbuss003VO(rs.getString("taid"),rs.getString("titl"),
                            rs.getDate("cdat"),rs.getString("cnam"),rs.getString("sta1nam"),
                            rs.getDate("pdat"),rs.getString("knam"),rs.getString("synonam"),
                            rs.getString("punonam"),rs.getDate("fdat"),rs.getDate("tdat"),
                            rs.getDate("adat"));
                    tbuss003VO.setPerc(rs.getDouble("perc"));
                    tbuss003VO.setEye(rs.getInt("coun"));
                    tbuss003VOS.add(tbuss003VO);
                }
                return tbuss003VOS;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss003VO.class);
    }

    @Override
    public List<Tbuss003VO> queryGropAllTask(String usid, Pager pager, Condition condition) {
        String sqlStr = "SELECT a.taid,a.titl,a.cdat,a.unam,a.cnam,a.sta1nam,a.ptypnam,a.sta2nam,a.pdat,a.knam,a.sta3nam," +
                "a.synonam,a.punonam,a.fdat,a.tdat,a.adat,a.perc,a.csid,a.acconam,a.ptnonam,a.stag from V_TBUSS003 a" +
                " $condition and a.PTNO in (select b.PTNO from TBUSS001 b where b.grop = (select c.grop from CBASE000 c WHERE  c.usid = @usid)) " +
                "ORDER BY a.cdat DESC";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setPager(pager);
        sql.setCondition(condition);
        return queryResultFormat(sql,dao);
    }

    @Override
    public List<Tbuss003VO> queryAllTask(Condition cnd0, Condition cnd1, Pager pager) {
        String sqlStr = "SELECT a.taid,a.titl,a.cdat,a.unam,a.cnam,a.sta1nam,a.ptypnam,a.sta2nam,a.pdat,a.knam,a.sta3nam," +
                "a.synonam,a.punonam,a.fdat,a.tdat,a.adat,a.perc,a.csid,a.acconam,a.ptnonam,a.stag from V_TBUSS003 a " +
                "$condition0 and a.PTNO in (select b.PTNO from TBUSS001 b $condition1) ORDER BY cdat DESC";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setVar("condition0",cnd0).setVar("condition1",cnd1);
        return queryResultFormat(sql,dao);
    }

    @Override
    public List<Tbuss003VO> queryGropAllTaskPrint(String usid, Pager pager, Condition condition) {
        String sqlStr = "SELECT a.taid,a.note,a.titl,a.cdat,a.unam,a.cnam,a.sta1nam,a.ptypnam,a.sta2nam,a.pdat,a.knam,a.sta3nam," +
                "a.synonam,a.punonam,a.fdat,a.tdat,a.adat,a.perc,a.csid,a.acconam,a.ptnonam from V_TBUSS003 a " +
                "$condition and a.PTNO in (select b.PTNO from TBUSS001 b where b.grop = (select c.grop from CBASE000 c WHERE  c.usid = @usid)) " +
                "ORDER BY a.cdat DESC";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setPager(pager);
        sql.setCondition(condition);
        return queryResultPrintFormat(sql,dao);
    }

    @Override
    public List<Tbuss003VO> queryAllTaskPrint(Condition cnd0, Condition cnd1, Pager pager) {
        String sqlStr = "SELECT a.taid,a.note,a.titl,a.cdat,a.unam,a.cnam,a.sta1nam,a.ptypnam,a.sta2nam,a.pdat,a.knam,a.sta3nam," +
                "a.synonam,a.punonam,a.fdat,a.tdat,a.adat,a.perc,a.csid,a.acconam,a.ptnonam from V_TBUSS003 a " +
                "$condition0 and a.PTNO in (select b.PTNO from TBUSS001 b $condition1) ORDER BY cdat DESC";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setVar("condition0",cnd0).setVar("condition1",cnd1);
        return queryResultPrintFormat(sql,dao);
    }



    @Override
    public Integer countAllTask(Condition cnd0, Condition cnd1) {
        String sqlStr = "SELECT count(*) from V_TBUSS003 a $condition0 and a.PTNO in (select b.PTNO from TBUSS001 b $condition1)";
        Sql sql = Sqls.create(sqlStr);
        sql.setVar("condition0",cnd0).setVar("condition1",cnd1);
        return DAOUtil.getTiCount(sql,dao);
    }

    @Override
    public Integer countGropTask(String usid, Condition condition) {
        String sqlStr = "SELECT count(*) from V_TBUSS003 $condition and PTNO in (select b.PTNO from TBUSS001 b where b.grop = (select c.grop from CBASE000 c WHERE  c.usid = @usid))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setCondition(condition);
        return DAOUtil.getTiCount(sql,dao);
    }

    @Override
    public Integer countTaskUtilByCnd(Condition cnd) {
        String sqlStr = "select count(*) from (select t3.taid,t3.titl,t3.csid,(select c0.dsca from cbase000 c0 where c0.usid = t3.csid) as cnam," +
                "(select t1.pdat from tbuss001 t1 where t1.ptno = t3.ptno) as pdat from tbuss003 t3) $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        return DAOUtil.getTiCount(sql,dao);
    }

    @Override
    public List<TaskUtilVO> queryAllTaskByPagerCnd(Pager pager, Cnd cnd) {
        String sqlStr = "select * from (select t3.taid,t3.titl,t3.note,t3.csid,(select c0.dsca from cbase000 c0 where c0.usid = t3.csid) as cnam," +
                "(select t1.pdat from tbuss001 t1 where t1.ptno = t3.ptno) as pdat from tbuss003 t3) $condition";
        Sql sql =Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setCondition(cnd);
        return DAOUtil.getTU(sql,dao);
    }

    /**
     * Query result format list.
     *
     * @param sql the sql
     * @param dao the dao
     * @return the list
     * @description 获取并输出标准的打印任务实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 03:10:56.
     */
    private List<Tbuss003VO> queryResultPrintFormat(Sql sql,Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss003VO> tbuss003VOS = new ArrayList<>();
                while(rs.next()){
                    Tbuss003VO tbuss003VO = formatResultVO(rs);
                    tbuss003VO.setNote(FileUtil.formatClobByString(rs.getString("note")));
                    tbuss003VOS.add(tbuss003VO);
                }
                return tbuss003VOS;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss003VO.class);
    }

    /**
     * Query result format list.
     *
     * @param sql the sql
     * @param dao the dao
     * @return the list
     * @description 获取并输出标准的任务实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 03:10:56.
     */
    private List<Tbuss003VO> queryResultFormat(Sql sql,Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss003VO> tbuss003VOS = new ArrayList<>();
                while(rs.next()){
                    Tbuss003VO tbuss003VO = formatResultVO(rs);
                    tbuss003VO.setStag(rs.getInt("stag"));
                    tbuss003VOS.add(tbuss003VO);
                }
                return tbuss003VOS;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss003VO.class);
    }

    /**
     * Format result vo tbuss 003 vo.
     *
     * @param rs the rs
     * @return the tbuss 003 vo
     * @throws SQLException the sql exception
     * @description 将返回集转换成实体.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:05 04:12:58.
     */
    private Tbuss003VO formatResultVO(ResultSet rs) throws SQLException{
        Tbuss003VO tbuss003VO = new Tbuss003VO(rs.getString("taid"),rs.getString("titl"),
                rs.getDate("cdat"),rs.getString("unam"),rs.getString("cnam"),
                rs.getString("sta1nam"),rs.getString("ptypnam"),rs.getString("sta2nam"),
                rs.getDate("pdat"),rs.getString("knam"),rs.getString("sta3nam"),
                rs.getString("synonam"), rs.getString("punonam"),rs.getDate("fdat"),
                rs.getDate("tdat"), rs.getDate("adat"));
        tbuss003VO.setPerc(rs.getDouble("perc"));
        tbuss003VO.setCsid(rs.getString("csid"));
        tbuss003VO.setAcconam(rs.getString("acconam"));
        tbuss003VO.setPtnonam(rs.getString("ptnonam"));
        tbuss003VO.setSynonam(rs.getString("synonam"));
        return tbuss003VO;
    }
}
