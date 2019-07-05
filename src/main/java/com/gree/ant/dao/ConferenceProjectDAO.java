package com.gree.ant.dao;

import com.gree.ant.vo.ConferenceProject;
import com.gree.ant.vo.ConferenceProjectUser;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface ConferenceProjectDAO extends BaseDAO<ConferenceProject>{

    List<ConferenceProject> queryAllByCndPage (Condition condition, Pager pager);

    /**
     * Query all ad list.
     *
     * @return the list
     * @description 查询所有项目的ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:08.
     */
    List<ResultVO> queryAllAD();

    List<ConferenceProjectUser> queryALLUserById (String projectGuid);
}
