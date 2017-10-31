package com.gree.ant.dao.daoImp;


import com.gree.ant.dao.Cbase017DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class Cbase017DAOImp implements Cbase017DAO{

    @Inject("refer:daoFX")
    private Dao dao;

    @Override
    public Boolean insertCheck(String dsca) {
        String sqls = "select * from cbase017 $condition";
        Condition cnd = Cnd.where("dsca","like","%"+dsca+"%");
        return DAOUtil.insertCheck(dao,sqls,cnd);
    }

    @Override
    public List<ResultVO> queryAllAD() {
        String sqlStr = "select acco,dsca from cbase017 order by dsca asc";
        return DAOUtil.getResultVO(sqlStr,dao);
    }
}
