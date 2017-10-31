package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@RunWith(MyNutTestRunner.class)
@IocBean
public class Cbase015MOTest {

    @Inject
    private Cbase015MO cbase015MO;

    @Test
    public void isnert() throws Exception {
    }

    @Test
    public void deleteByTaid() throws Exception {
        System.out.println(cbase015MO.deleteByTaid("fwerw"));
    }

}