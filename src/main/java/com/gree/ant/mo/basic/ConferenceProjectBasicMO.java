package com.gree.ant.mo.basic;

import com.gree.ant.vo.ConferenceProject;
import org.nutz.dao.QueryResult;

public interface ConferenceProjectBasicMO {
    QueryResult loadProjectData (Integer pageNumber, Integer pageSize, String acco);
    QueryResult loadProjectUserData (Integer pageNumber, Integer pageSize, String userId);
    ConferenceProject insertProject (ConferenceProject conferenceProject, String[] users);
    Boolean updateProject (ConferenceProject conferenceProject, String[] users);
    Boolean deleteProject (ConferenceProject conferenceProject);
}
