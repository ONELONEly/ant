package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ConferenceProjectUserDAO;
import com.gree.ant.vo.ConferenceProjectUser;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class ConferenceProjectUserDAOImp extends BaseDAOImp<ConferenceProjectUser> implements ConferenceProjectUserDAO{

    @Override
    public Boolean deleteByProjectGuid(String projectGuid) {
        String sqlStr = "delete conferenceProjectUser where project_guid = @project_guid";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("project_guid",projectGuid);
        getDao().execute(sql);
        return true;
    }
}
