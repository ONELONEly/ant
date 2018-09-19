package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss013VO;

public interface Tbuss013DAO extends BaseDAO<Tbuss013VO>{


    void markTask(Integer task_id,Float grade);
}
