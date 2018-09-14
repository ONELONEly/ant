package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss005VO;

public interface Tbuss005DAO extends BaseDAO<Tbuss005VO>{

    Integer updateByVO(Tbuss005VO tbuss005VO);

    Integer deleteByPtno(String ptno);
}
