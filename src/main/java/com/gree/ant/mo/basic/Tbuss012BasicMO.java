package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss012VO;

import java.util.List;

public interface Tbuss012BasicMO {

    Tbuss012VO insertGoal(Tbuss012VO tbuss012VO);

    void deleteByOKID(Integer okid);

    Tbuss012VO fetchByID(Integer goal_id);

    Tbuss012VO fetchTransByID(Integer goal_id);

    Tbuss012VO fetchTransByVO(Tbuss012VO tbuss012VO);

    void updateOkr(Integer okid, List<Tbuss012VO> tbuss012VOS);
}
