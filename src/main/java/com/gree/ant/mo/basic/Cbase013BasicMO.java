package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase013VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface Cbase013BasicMO {

    List<Cbase013VO> queryAllByCnd(Condition cnd, Pager pager);

    Integer countByCnd(Condition cnd);

    Cbase013VO fetchBySyno(String syno);

    Integer updateByVO(Cbase013VO cbase013VO);
}
