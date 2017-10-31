package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss003DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Tbuss003VO;
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
    public List<Tbuss003VO> queryAllGradeTask(String usid, Pager pager,Condition condition) {
        String sqlStr = "select t.*,(select count(*) from TBUSS010 s where s.USID = @usid AND s.TAID = t.TAID)coun from V_TBUSS003 t $condition";
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
        String sqlStr = "SELECT * from V_TBUSS003 $condition and PTNO in (select b.PTNO from TBUSS001 b where b.grop = (select c.grop from CBASE000 c WHERE  c.usid = @usid)) ORDER BY cdat DESC";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("usid",usid);
        sql.setPager(pager);
        sql.setCondition(condition);
        return queryResultFormat(sql,dao);
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
    public List<Tbuss003VO> queryAllTask(Condition cnd0, Condition cnd1, Pager pager) {
        String sqlStr = "SELECT * from V_TBUSS003 a $condition0 and a.PTNO in (select b.PTNO from TBUSS001 b $condition1) ORDER BY cdat DESC";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setVar("condition0",cnd0).setVar("condition1",cnd1);
        return queryResultFormat(sql,dao);
    }

    @Override
    public Integer countAllTask(Condition cnd0, Condition cnd1) {
        String sqlStr = "SELECT count(*) from V_TBUSS003 a $condition0 and a.PTNO in (select b.PTNO from TBUSS001 b $condition1)";
        Sql sql = Sqls.create(sqlStr);
        sql.setVar("condition0",cnd0).setVar("condition1",cnd1);
        return DAOUtil.getTiCount(sql,dao);
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
                    Tbuss003VO tbuss003VO = new Tbuss003VO(rs.getString("taid"),rs.getString("titl"),
                            rs.getDate("cdat"),rs.getString("unam"),rs.getString("cnam"),
                            rs.getString("sta1nam"),rs.getString("ptypnam"),rs.getString("sta2nam"),
                            rs.getDate("pdat"),rs.getString("knam"),rs.getString("sta3nam"),
                            rs.getString("synonam"), rs.getString("punonam"),rs.getDate("fdat"),
                            rs.getDate("tdat"), rs.getDate("adat"));
                    tbuss003VO.setPerc(rs.getDouble("perc"));
                    tbuss003VO.setNote(FileUtil.formatClobByString(rs.getString("note")));
                    tbuss003VO.setCsid(rs.getString("csid"));
                    tbuss003VO.setAcconam(rs.getString("acconam"));
                    tbuss003VO.setPtnonam(rs.getString("ptnonam"));
                    tbuss003VO.setSynonam(rs.getString("synonam"));
                    tbuss003VOS.add(tbuss003VO);
                }
                return tbuss003VOS;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss003VO.class);
    }
}
