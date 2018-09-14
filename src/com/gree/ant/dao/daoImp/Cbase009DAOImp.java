package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase009DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.dao.daoImp.util.Pager;
import com.gree.ant.vo.Cbase009VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class Cbase009DAOImp extends BaseDAOImp<Cbase009VO> implements Cbase009DAO{

    @Override
    public Boolean insertCheck(String dsca) {
        String sqls = "select * from cbase009 $condition";
        Condition cnd = Cnd.where("dsca","like","%"+dsca+"%");
        return DAOUtil.insertCheck(this.getDao(),sqls,cnd);
    }

    @Override
    public List<ResultVO> queryAllGD() {
        String sqlStr = "select grop,dsca from cbase009 order by dsca asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }
}
