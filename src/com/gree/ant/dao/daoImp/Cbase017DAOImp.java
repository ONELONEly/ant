package com.gree.ant.dao.daoImp;


import com.gree.ant.dao.Cbase017DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Cbase017VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class Cbase017DAOImp extends BaseDAOImp<Cbase017VO> implements Cbase017DAO{

    @Override
    public Boolean insertCheck(String dsca) {
        String sqls = "select * from cbase017 $condition";
        Condition cnd = Cnd.where("dsca","like","%"+dsca+"%");
        return DAOUtil.insertCheck(this.getDao(),sqls,cnd);
    }

    @Override
    public List<ResultVO> queryAllAD() {
        String sqlStr = "select acco,dsca from cbase017 order by dsca asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }
}
