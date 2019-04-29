package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Cbase003MOTest {

    @Inject
    private Cbase003MO cbase003MO;

    @Test
    public void queryAllByCndPager() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void deleteByPONO() throws Exception {
    }

    @Test
    public void updateByVO() throws Exception {
    }

    @Test
    public void fetchByPONO() throws Exception {
    }

    @Test
    public void countByCnd() throws Exception {
    }

}