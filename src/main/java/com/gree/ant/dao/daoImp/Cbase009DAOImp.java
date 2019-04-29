package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase009DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.dao.daoImp.util.Pager;
import com.gree.ant.vo.Cbase009VO;
import com.gree.ant.vo.response.GropUser;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
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
public class Cbase009DAOImp extends BaseDAOImp<Cbase009VO> implements Cbase009DAO{

    @Override
    public Boolean insertCheck(String dsca) {
        String sqls = "select * from cbase009 $condition";
        Condition cnd = Cnd.where("dsca","like","%"+dsca+"%");
        return DAOUtil.insertCheck(this.getDao(),sqls,cnd);
    }

    @Override
    public List<ResultVO> queryAllGD() {
        String sqlStr = "select grop,dsca from cbase009 order by dsca asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }

    @Override
    public List<GropUser> fetchC9Trans(String grop,Condition cnd) {
        String sqlStr = "select * from (select usid,(select c0.dsca from cbase000 c0 where c0.usid = c10.usid)dsca,case when BOSS = 1 then '组长' when BOSS = 0 then '组员' end as boss from cbase010 c10 where c10.grop = @grop)result $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grop",grop);
        sql.setCondition(cnd);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<GropUser> gropUsers = new ArrayList<>();
                while (rs.next()){
                    gropUsers.add(new GropUser(rs.getString("usid"),rs.getString("dsca"),rs.getString("boss")));
                }
                return gropUsers;
            }
        });
        this.getDao().execute(sql);
        return sql.getList(GropUser.class);
    }


}
