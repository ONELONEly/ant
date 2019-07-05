package com.gree.ant.mo.basic;

import com.gree.ant.vo.Conference;
import org.nutz.dao.QueryResult;

import java.util.List;

public interface ConferenceBasicMO {


    QueryResult loadTableData (String usid, String month, Integer week, Integer pageNumber, Integer pageSize);
    List<Conference> loadShowData (String acco);
    Conference fetchData (String conferenceGuid);
    Boolean checkByWeekProjectId (Integer week, String projectId);
}
