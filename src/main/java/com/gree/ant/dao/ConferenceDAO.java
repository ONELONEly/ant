package com.gree.ant.dao;

import com.gree.ant.vo.Conference;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface ConferenceDAO extends BaseDAO<Conference>{

    List<Conference> queryTableByCndPager (Condition condition, Pager pager);

    List<Conference> queryShowByCnd (Condition condition);
}
