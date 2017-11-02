package com.gree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

@RunWith(MyNutTestRunner.class)
@IocBean
public class FirstTest extends Assert{

    @Inject("refer:$ioc")
    private Ioc ioc;

    @Test
    public void firstTest() throws Exception {
//        Mvcs.setLocalizationKey("");
        System.out.println(Mvcs.getLocalizationKey());
        System.out.println(Mvcs.getLocalizationKeySet());
    }
}
