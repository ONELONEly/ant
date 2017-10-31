package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase007DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase007DAOImp implements Cbase007DAO{

    @Inject("refer:daoFX")
    private Dao dao;

    @Override
    public Boolean insertCheck(Condition cnd) {
        String sqlStr = "select * from cbase007 $condition";
        return DAOUtil.insertCheck(dao,sqlStr,cnd);
    }
}
