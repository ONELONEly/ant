package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss009VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface Tbuss009DAO {

    List<Tbuss009VO> queryAllGropDoc(String usid, Condition cnd, Pager pager);
}
