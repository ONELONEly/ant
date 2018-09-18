package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss012VO;

import java.util.List;
import java.util.Map;

public interface Tbuss012BasicMO {

    Tbuss012VO insertGoal(Tbuss012VO tbuss012VO);

    Tbuss012VO fetchByID(Integer goal_id);

    Tbuss012VO fetchTransByID(Integer goal_id);

    Tbuss012VO fetchTransByVO(Tbuss012VO tbuss012VO);

    void updateOkr(Integer okid, List<Tbuss012VO> tbuss012VOS);

    Integer markGoal(List<Map<String,Float>> scores);
}
