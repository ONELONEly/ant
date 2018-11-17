package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;


@RunWith(MyNutTestRunner.class)
@IocBean
public class Cbase009MOTest {

    @Inject
    private Cbase009MO cbase009MO;

    @Test
    public void insertCheck() throws Exception {
    }

}