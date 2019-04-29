package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss014VO;
import org.nutz.dao.QueryResult;

public interface Tbuss014BasicMO {

    Tbuss014VO insertByVO(Tbuss014VO tbuss014VO);
    Tbuss014VO fetchByRaid(String raid);
    void copyByRaids(String[] raids,String usid);
    void backByRaids(String[] raids);
    Integer deleteByRaids(String[] raids);
    Integer deleteByRaid(String raid);
    Integer updateByVO(Tbuss014VO tbuss014VO);
    QueryResult queryAllByMsg(Integer pageNumber,Integer pageSize,String msg,Integer stat,String usid,String csid);
}
