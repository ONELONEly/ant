package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ButterFlyDAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.util.ButterFlyFeiyun;
import com.gree.ant.vo.util.ButterFlyVO;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@IocBean
public class ButterFlyDAOImp extends BaseDAOImp<ButterFlyVO> implements ButterFlyDAO {


    @Inject("refer:$ioc")
    private Ioc ioc;

    @Override
    public QueryResult feiyunQueryAllUser(Condition condition, Pager pager) {
        String sqlStr = "select companyNumber,company,departmentNumber,department,posts,postsName,numberCard,name,sex,isInStaff,email from butterfly $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setCondition(condition);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<ButterFlyFeiyun> flyFeiyuns = new ArrayList<>();
                while(rs.next()){
                    String posts = rs.getString("posts");
                    flyFeiyuns.add(new ButterFlyFeiyun(rs.getString("companyNumber"),rs.getString("company"),
                            rs.getString("departmentNumber"),rs.getString("department"),
                            posts,posts.equals("公司领导")?rs.getString("postsName"):"",rs.getString("numberCard"),
                            rs.getString("name"), rs.getString("sex"),
                            rs.getString("email") != null?rs.getString("email"):"" ,
                            rs.getString("isInStaff")));
                }
                return flyFeiyuns;
            }
        });
        this.getDao().execute(sql);
        return TableUtil.formatQueryResult(pager,sql.getList(ButterFlyFeiyun.class));
    }

    @Override
    public String fetchEmailByCard(String card) {
        String sqlStr = "select b.TXT6 from BOPHR_TEMP b where b.txt10 = @card";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("card",card);
        return DAOUtil.getTxtResult(sql,this.getDao());
    }

    @Override
    public String fetchDeptByNumber(String deptNumber) {
        String sqlStr = "select a.ORGANIZATIONNAME From BUTTERFLYORGANIZATION a where a.ORGANIZATIONNUMBER = @deptNumber";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("deptNumber",deptNumber);
        return DAOUtil.getTxtResult(sql,this.getDao());
    }

    @Override
    public String fetchPostsByStaff(String staffName, String staffNumber) {
        String sqlStr = "select a.STAFF from BUTTERFLYSTAFF a where a.STAFFNAME = @staffName and a.STAFFNUMBER = @staffNumber";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("staffName",staffName).setParam("staffNumber",staffNumber);
        return DAOUtil.getTxtResult(sql,this.getDao());
    }

    @Override
    public void updateUserPostsNormal() {
        String sqlStr = "update BUTTERFLY set POSTS = '员工' where POSTS != '公司领导' and POSTS != '中层干部' and POSTS != '主管' and name != '董明珠'";
        Sql sql = Sqls.create(sqlStr);
        this.getDao().execute(sql);
    }
}
