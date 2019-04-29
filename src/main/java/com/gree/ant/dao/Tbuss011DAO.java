package com.gree.ant.dao;

import com.gree.ant.vo.Tbuss011VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

public interface Tbuss011DAO extends BaseDAO<Tbuss011VO>{

    Integer countByGrop(String grop, Condition condition);

    Integer countByAcco(String acco, Condition condition);

    Integer countByComp(String comp, Condition condition);

    Integer countByDept(String dept, Condition condition);

    List<Tbuss011VO> queryByGrop(String grop, Condition condition, Pager pager);

    List<Tbuss011VO> queryByAcco(String acco, Condition condition, Pager pager);

    List<Tbuss011VO> queryByComp(String comp, Condition condition, Pager pager);

    List<Tbuss011VO> queryByDept(String dept, Condition condition, Pager pager);

    void pushToLeader(Condition cnd);

    void backToUser(Condition cnd);
}
