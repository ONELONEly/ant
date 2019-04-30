package com.gree.ant.dao;

import com.gree.ant.vo.Cbase016VO;
import org.nutz.dao.Condition;

import java.util.List;

public interface Cbase016DAO extends BaseDAO<Cbase016VO>{


    public List<Cbase016VO> queryAllSearch(Condition condition);
}
