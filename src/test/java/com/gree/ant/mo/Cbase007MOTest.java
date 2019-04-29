package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Cbase007MOTest {

    @Inject
    private Cbase007MO cbase007MO;

    @Test
    public void queryAllByPagerCnd() throws Exception {
    }

    @Test
    public void insertByVO() throws Exception {
    }

    @Test
    public void deleteByROID() throws Exception {
    }

    @Test
    public void updateByVO() throws Exception {
    }

    @Test
    public void fetchByROID() throws Exception {
    }

    @Test
    public void fetchTransByCndROID() throws Exception {
    }

    @Test
    public void countByCnd() throws Exception {
    }

    @Test
    public void insertCheck() throws Exception {
    }

}