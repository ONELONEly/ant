package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase015DAO;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase015DAOImp implements Cbase015DAO{

    @Inject("refer:daoFX")
    private Dao dao;
}
