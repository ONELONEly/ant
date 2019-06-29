package com.gree.ant.dao;

import com.gree.ant.vo.ConferenceProjectUser;

public interface ConferenceProjectUserDAO extends BaseDAO<ConferenceProjectUser>{

    Boolean deleteByProjectGuid (String projectGuid);
}
