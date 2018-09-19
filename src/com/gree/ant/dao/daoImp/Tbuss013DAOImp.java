package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss013DAO;
import com.gree.ant.vo.Tbuss013VO;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Tbuss013DAOImp extends BaseDAOImp<Tbuss013VO> implements Tbuss013DAO {

    @Override
    public void markTask(Integer task_id, Float grade) {
        String sqlStr = "update tbuss013 set mgrad = @grade where task_id = @task_id";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("grade",grade).setParam("task_id",task_id);
        getDao().execute(sql);
    }
}
