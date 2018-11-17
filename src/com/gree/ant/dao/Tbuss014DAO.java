package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss014VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface Tbuss014DAO extends BaseDAO<Tbuss014VO>{

    List<Tbuss014VO> queryAllByCndPager(Condition condition, Pager pager);
}
