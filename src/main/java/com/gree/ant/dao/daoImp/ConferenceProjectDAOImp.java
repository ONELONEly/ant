package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ConferenceProjectDAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.util.DateUtil;
import com.gree.ant.vo.ConferenceProject;
import com.gree.ant.vo.ConferenceProjectUser;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class ConferenceProjectDAOImp extends BaseDAOImp<ConferenceProject> implements ConferenceProjectDAO {

    @Override
    public List<ConferenceProject> queryAllByCndPage(Condition condition, Pager pager) {
        String sqlStr = "select conf.PROJECT_GUID,conf.title,conf.STARTDATE,conf.ONLINEDATE,conf.ORDERNUMBER," +
                "(case when conf.STATE = 0 then '关闭' else '激活' end )status,c0.dsca from conferenceProject conf " +
                "left join cbase000 c0 on conf.creator = c0.usid $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(condition);
        sql.setPager(pager);
        sql.setCallback((conn, rs, sql1) -> {
            List<ConferenceProject> conferenceProjects = new ArrayList<>();
            while (rs.next()) {
                ConferenceProject conferenceProject = new ConferenceProject();
                conferenceProject.setProjectGuid(rs.getString("project_guid"));
                conferenceProject.setTitle(rs.getString("title"));
                conferenceProject.setStartDateTxt(DateUtil.formatYMDDate(rs.getDate("startDate")));
                conferenceProject.setOnlineDateTxt(DateUtil.formatYMDDate(rs.getDate("onlineDate")));
                conferenceProject.setOrderNumber(rs.getInt("orderNumber"));
                conferenceProject.setStateTxt(rs.getString("status"));
                conferenceProject.setCreator(rs.getString("dsca"));
                conferenceProjects.add(conferenceProject);
            }
            return conferenceProjects;
        });
        getDao().execute(sql);
        return sql.getList(ConferenceProject.class);
    }

    @Override
    public List<ResultVO> queryAllAD() {
        String sqlStr = "select PROJECT_GUID,TITLE from CONFERENCEPROJECT order by ORDERNUMBER desc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }

    @Override
    public List<ConferenceProjectUser> queryALLUserById(String projectGuid) {
        String sqlStr = "select conf.USER_ID,c0.DSCA from CONFERENCEPROJECTUSER conf left join CBASE000 c0 on " +
                "conf.USER_ID = c0.USID where project_guid = @projectGuid";
        Sql sql = Sqls.create(sqlStr);
        sql.setParam("projectGuid",projectGuid);
        sql.setCallback((conn, rs, sql1) -> {
            List<ConferenceProjectUser> users = new ArrayList<>();
            while (rs.next()) {
                ConferenceProjectUser user = new ConferenceProjectUser();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("dsca"));
                users.add(user);
            }
            return users;
        });
        getDao().execute(sql);
        return sql.getList(ConferenceProjectUser.class);
    }
}
