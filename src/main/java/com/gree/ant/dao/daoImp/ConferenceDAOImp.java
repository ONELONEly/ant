package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.ConferenceDAO;
import com.gree.ant.util.DateUtil;
import com.gree.ant.vo.Conference;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@IocBean
public class ConferenceDAOImp extends BaseDAOImp<Conference> implements ConferenceDAO {

    @Override
    public List<Conference> queryTableByCndPager(Condition condition, Pager pager) {
        String sqlStr = "SELECT conference_guid,title,week,create_date FROM CONFERENCE $condition";
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(pager);
        sql.setCondition(condition);
        return queryResultTableFormat(sql,this.getDao());
    }

    @Override
    public List<Conference> queryShowByCnd(Condition condition) {
        String sqlStr = "SELECT title,startDate,scheduleDate,follower,pre_week_done,now_week_schedule,others FROM CONFERENCE $condition order by creator desc";
        Sql sql = Sqls.create(sqlStr);
        sql.setCondition(condition);
        return queryResultShowFormat(sql,this.getDao());
    }

    private List<Conference> queryResultTableFormat (Sql sql, Dao dao) {
        sql.setCallback((conn, rs, sql1) -> {
            List<Conference> conferences = new ArrayList<>();
            while(rs.next()){
                Conference conference = new Conference();
                conference.setConference(rs.getString("conference_guid"));
                conference.setTitle(rs.getString("title"));
                conference.setWeek(rs.getInt("week"));
                conference.setCreateDate(fromDate(rs.getDate("create_date")));
                conferences.add(conference);
            }
            return conferences;
        });
        dao.execute(sql);
        return sql.getList(Conference.class);
    }

    private List<Conference> queryResultShowFormat (Sql sql, Dao dao) {
        sql.setCallback((conn, rs, sql1) -> {
            List<Conference> conferences = new ArrayList<>();
            while(rs.next()){
                Conference conference = new Conference();
                conference.setTitle(rs.getString("title"));
                conference.setStartDate(fromDate(rs.getDate("startDate")));
                conference.setScheduleDate(fromDate(rs.getDate("scheduleDate")));
                conference.setFollower(rs.getString("follower"));

                conference.setStartDateTxt(DateUtil.formDateToString(conference.getStartDate()));
                conference.setScheduleDateTxt(DateUtil.formDateToString(conference.getScheduleDate()));

                conference.setPreWeekDoneTxt(rs.getString("pre_week_done"));
                conference.setNowWeekScheduleTxt(rs.getString("now_week_schedule"));
                conference.setOthersTxt(rs.getString("others") == null ? "" : rs.getString("others"));
                conferences.add(conference);
            }
            return conferences;
        });
        dao.execute(sql);
        return sql.getList(Conference.class);
    }

    private static  LocalDateTime fromDate (java.sql.Date date) {
        Date utilDate = new Date(date.getTime());
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(utilDate.toInstant(),zone);
    }
}
