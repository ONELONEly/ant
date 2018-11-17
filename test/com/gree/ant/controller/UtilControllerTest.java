package com.gree.ant.controller;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
@RunWith(MyNutTestRunner.class)
public class UtilControllerTest {

    @Inject
    private UtilController utilController;

    @Test
    public void findTaskVO() {
    }
}