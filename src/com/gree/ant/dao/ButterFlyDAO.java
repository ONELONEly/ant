package com.gree.ant.dao;

import com.gree.ant.vo.util.ButterFlyVO;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;

public interface ButterFlyDAO extends BaseDAO<ButterFlyVO>{

    QueryResult feiyunQueryAllUser(Condition condition, Pager pager);

    String fetchEmailByCard(String card);

    String fetchDeptByNumber(String deptNumber);

    String fetchPostsByStaff(String staffName,String staffNumber);

    void updateUserPostsNormal();
}
