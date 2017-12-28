package com.gree.ant.dao.daoImp;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.Cbase015DAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Cbase015DAOImpTest {

    @Inject
    private Cbase015DAOImp cbase015DAOImp;

    @Test
    public void deleteByStaid() throws Exception {
        System.out.println(cbase015DAOImp.queryAll());
    }

}