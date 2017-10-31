package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.daoImp.util.Pager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Tbuss001MOTest {

    @Inject
    private Tbuss001MO tbuss001MO;

    @Test
    public void queryAllByCnd() throws Exception {
        System.out.println(tbuss001MO.queryAllByComp("400","2017",new org.nutz.dao.pager.Pager(1,2)));
    }

    @Test
    public void countByCnd() throws Exception {
    }

    @Test
    public void deleteByVO() throws Exception {
    }

    @Test
    public void deleteByName() throws Exception {
    }

    @Test
    public void deteleRelation() throws Exception {
    }

    @Test
    public void fetchLinksByVO() throws Exception {
    }

    @Test
    public void fetchTransByNameCnd() throws Exception {
    }

    @Test
    public void fectchByName() throws Exception {
    }

    @Test
    public void insertRelation() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void insertCheck() throws Exception {
    }

    @Test
    public void maxID() throws Exception {
    }

}