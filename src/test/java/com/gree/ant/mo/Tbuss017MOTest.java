package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Tbuss017MOTest {

    @Inject
    private BussMoFactory bussMoFactory;

    @Test
    public void fetchByUsidPdat() {
        bussMoFactory.getTbuss017MO().fetchByUsidPdat("180484","2018-09");
    }
}