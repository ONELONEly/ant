package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss001DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.util.ResultVO;
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
public class Tbuss001DAOImp extends BaseDAOImp<Tbuss001VO> implements Tbuss001DAO{

    @Override
    public String fetchNameByUsidPdat(String usid, String pdat) {
        String sqls = "select t.ptno from v_tbuss001 t where t.grop = (select d.grop from cbase000 d where d.usid = @usid) and t.pdat = @pdat";
        Sql sql = Sqls.create(sqls);
        sql.setParam("usid",usid).setParam("pdat",pdat);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                if(rs.next()){
                    return rs.getString("ptno");
                }
                return "";
            }
        });
        this.getDao().execute(sql);
        return sql.getString();
    }

    @Override
    public List<Tbuss001VO> queryAllByAcco(String acco, String pdat,String group, Pager pager) {
        String sqlStr = "select t.* from v_tbuss001 t where t.usid in (select c.usid from cbase000 c where c.acco = @acco)" +
                " and pdat like @pdat and grop like @grop order by t.pdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setParam("acco",acco).setParam("pdat","%"+pdat+"%").setParam("grop","%"+group+"%");
        return DAOUtil.getT1(sql,this.getDao());
    }

    @Override
    public List<Tbuss001VO> queryAllByDept(String dept,String pdat,String group, Pager pager) {
        String sqlStr = "select t.* from v_tbuss001 t where t.usid in (select c.usid from cbase000 c where c.dept = @dept) " +
                "and pdat like @pdat and grop like @grop order by t.pdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setParam("dept",dept).setParam("pdat","%"+pdat+"%").setParam("grop","%"+group+"%");
        return DAOUtil.getT1(sql,this.getDao());
    }

    @Override
    public List<Tbuss001VO> queryAllByComp(String comp, String pdat,String group, Pager pager) {
        String sqlStr = "select t.* from v_tbuss001 t where t.usid in (select c.usid from cbase000 c where c.comp = @comp) " +
                "and pdat like @pdat and grop like @grop order by t.pdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setParam("comp",comp).setParam("pdat","%"+pdat+"%").setParam("grop","%"+group+"%");
        return DAOUtil.getT1(sql,this.getDao());
    }

    @Override
    public Integer countByAcco(String acco, String pdat,String group) {
        String sqlStr = "select count(*) from v_tbuss001 t where t.usid in (select c.usid from cbase000 c where c.acco = @acco) " +
                "and pdat like @pdat and grop like @grop";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("acco",acco).setParam("pdat","%"+pdat+"%").setParam("grop","%"+group+"%");
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public Integer countByDept(String dept, String pdat,String group) {
        String sqlStr = "select count(*) from v_tbuss001 t where t.usid in (select c.usid from cbase000 c where c.dept = @dept) " +
                "and pdat like @pdat and grop like @grop";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("dept",dept).setParam("pdat","%"+pdat+"%").setParam("grop","%"+group+"%");
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public Integer countByComp(String comp, String pdat,String group) {
        String sqlStr = "select count(*) from v_tbuss001 t where t.usid in (select c.usid from cbase000 c where c.comp = @comp) " +
                "and pdat like @pdat and grop like @grop";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("comp",comp).setParam("pdat","%"+pdat+"%").setParam("grop","%"+group+"%");
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public List<String> queryAllPdat() {
        String sqlStr = "select distinct(pdat) from tbuss001 order by pdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<String> res = new ArrayList<>();
                while(rs.next()){
                    res.add(rs.getString(1));
                }
                return res;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(String.class);
    }

    @Override
    public List<ResultVO> queryAllPD(Condition cnd) {
        String sqlStr = "select ptno,dsca from tbuss001 $condition order by pdat desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        return DAOUtil.getResultVO(sql,this.getDao());
    }
}
