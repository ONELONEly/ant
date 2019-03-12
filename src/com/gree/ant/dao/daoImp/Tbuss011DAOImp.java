package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss011DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Tbuss011VO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
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
public class Tbuss011DAOImp extends BaseDAOImp<Tbuss011VO> implements Tbuss011DAO {


    @Override
    public Integer countByGrop(String grop, Condition condition) {
        String sqlStr = "select count(*) from v_tbuss011 $condition and ((asid in (select usid from cbase000 where grop = @grop) " +
                "or (BOSS in (select usid from cbase000 where grop = @grop))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grop",grop);
        sql.setCondition(condition);
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public Integer countByAcco(String acco, Condition condition) {
        String sqlStr = "select count(*) from v_tbuss011 $condition and ((asid in (select usid from cbase000 where acco = @acco) " +
                "or (BOSS in (select usid from cbase000 where acco = @acco))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("acco",acco);
        sql.setCondition(condition);
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public Integer countByComp(String comp, Condition condition) {
        String sqlStr = "select count(*) from v_tbuss011 $condition and ((asid in (select usid from cbase000 where comp = @comp) " +
                "or (BOSS in (select usid from cbase000 where comp = @comp))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grop",comp);
        sql.setCondition(condition);
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public Integer countByDept(String dept, Condition condition) {
        String sqlStr = "select count(*) from v_tbuss011 $condition and ((asid in (select usid from cbase000 where dept = @dept) " +
                "or (BOSS in (select usid from cbase000 where dept = @dept))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("dept",dept);
        sql.setCondition(condition);
        return DAOUtil.getTiCount(sql,this.getDao());
    }

    @Override
    public List<Tbuss011VO> queryByGrop(String grop, Condition condition, Pager pager) {
        String sqlStr = "select * from v_tbuss011 $condition and ((asid in (select usid from cbase000 where grop = @grop) " +
                "or (BOSS in (select usid from cbase000 where grop = @grop))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grop",grop);
        sql.setCondition(condition);
        sql.setPager(pager);
        return getT11Result(sql,this.getDao());
    }

    @Override
    public List<Tbuss011VO> queryByAcco(String acco, Condition condition, Pager pager) {
        String sqlStr = "select * from v_tbuss011 $condition and ((asid in (select usid from cbase000 where acco = @acco) " +
                "or (BOSS in (select usid from cbase000 where acco = @acco))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("acco",acco);
        sql.setCondition(condition);
        sql.setPager(pager);
        return getT11Result(sql,this.getDao());
    }

    @Override
    public List<Tbuss011VO> queryByComp(String comp, Condition condition, Pager pager) {
        String sqlStr = "select * from v_tbuss011 $condition and ((asid in (select usid from cbase000 where comp = @comp) " +
                "or (BOSS in (select usid from cbase000 where comp = @comp))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("comp",comp);
        sql.setCondition(condition);
        sql.setPager(pager);
        return getT11Result(sql,this.getDao());
    }

    @Override
    public List<Tbuss011VO> queryByDept(String dept, Condition condition, Pager pager) {
        String sqlStr = "select * from v_tbuss011 $condition and ((asid in (select usid from cbase000 where dept = @dept) " +
                "or (BOSS in (select usid from cbase000 where dept = @dept))))";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("dept",dept);
        sql.setCondition(condition);
        sql.setPager(pager);
        return getT11Result(sql,this.getDao());
    }

    @Override
    public void pushToLeader(Condition cnd) {
        String sqlStr = "update tbuss011 set stat = 1 $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        this.getDao().execute(sql);
    }

    @Override
    public void backToUser(Condition cnd) {
        String sqlStr = "update tbuss011 set stat = 2 $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(cnd);
        this.getDao().execute(sql);
    }

    private List<Tbuss011VO> getT11Result(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss011VO> tbuss011VOS = new ArrayList<>();
                while(rs.next()){
                    tbuss011VOS.add(new Tbuss011VO(rs.getString("ASID"),rs.getString("ANAM"),
                            rs.getString("BOSS"),rs.getString("BNAM"),rs.getString("MDAT"),
                            rs.getInt("OKID"),rs.getInt("STAT"),rs.getFloat("GRADE"),
                            rs.getInt("TYPE"),rs.getInt("AUTH"),rs.getString("TYPENAM"),
                            rs.getString("AUTHNAM"),rs.getString("ACCONAM")));
                }
                return tbuss011VOS;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss011VO.class);
    }
}
