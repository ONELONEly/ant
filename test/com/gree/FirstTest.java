package com.gree;

import com.gree.ant.vo.Cbase005VO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.*;

@RunWith(MyNutTestRunner.class)
@IocBean
public class FirstTest extends Assert{

    @Inject("refer:$ioc")
    private Ioc ioc;

    @Test
    public void firstTest() throws Exception {
        List<Cbase005VO> cbase005VOS = new ArrayList<>();
        List<Cbase005VO> cbase005VOS1 = new ArrayList<>();
        Cbase005VO cbase005VO = new Cbase005VO("110","111");
        Cbase005VO cbase005VO1 = new Cbase005VO("111","112");
        cbase005VOS.add(cbase005VO);
        cbase005VOS.add(cbase005VO1);
        cbase005VOS1.add(cbase005VO);
        System.out.println(cbase005VOS.size());
    }
}
