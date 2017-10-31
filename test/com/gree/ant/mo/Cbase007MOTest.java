package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.vo.Cbase007VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

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
        System.out.println(cbase007MO.fetchTransByCndROID(null,"R00000","cbase002VOS").getCbase002VOS().size());
    }

    @Test
    public void countByCnd() throws Exception {
    }

    @Test
    public void insertCheck() throws Exception {
    }

}