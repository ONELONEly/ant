package com.gree.ant.dao.daoImp;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Tbuss003DAOImpTest {

    @Inject
    private Tbuss003DAOImp tbuss003DAOImp;

    @Inject
    private Cbase000DAOImp cbase000DAOImp;

    @Inject
    private Cbase017DAOImp cbase017DAOImp;

    @Test
    public void getUserScoreByPtnoUsid() {
        System.out.println(cbase017DAOImp.queryAllBoos("3"));
    }
}