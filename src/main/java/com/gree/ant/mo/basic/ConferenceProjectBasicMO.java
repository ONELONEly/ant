package com.gree.ant.mo.basic;

import com.gree.ant.vo.ConferenceProject;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.QueryResult;

import java.util.List;

public interface ConferenceProjectBasicMO {
    QueryResult loadProjectData (Integer pageNumber, Integer pageSize, String acco);
    QueryResult loadProjectUserData (Integer pageNumber, Integer pageSize, String userId);
    ConferenceProject insertProject (ConferenceProject conferenceProject, String[] users);
    Boolean updateProject (ConferenceProject conferenceProject, String[] users);
    Boolean deleteProject (ConferenceProject conferenceProject);
    ConferenceProject fetchProject (String conference_project_id);
    List<ResultVO> queryAllAD ();
}
