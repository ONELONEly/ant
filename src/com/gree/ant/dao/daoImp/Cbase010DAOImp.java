package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase010DAO;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase010DAOImp implements Cbase010DAO{

    @Inject("refer:daoFX")
    private Dao dao;
}
