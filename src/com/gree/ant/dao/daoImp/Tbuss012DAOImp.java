package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss012DAO;
import com.gree.ant.vo.Tbuss012VO;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Tbuss012DAOImp extends BaseDAOImp<Tbuss012VO> implements Tbuss012DAO {

    @Override
    public void markGoal(Integer goal_id, Float grade) {
        String sqlStr = "update tbuss012 set mgrad = @grade where goal_id = @goal_id";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grade",grade).setParam("goal_id",goal_id);
        getDao().execute(sql);
    }
}
