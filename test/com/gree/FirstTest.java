package com.gree;
import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.util.DoubleUtil;
import com.gree.ant.util.SyncButterFlyData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@RunWith(MyNutTestRunner.class)
@IocBean
public class FirstTest extends Assert{

    @Inject("refer:daoFX")
    private Dao dao;

    @Inject
    private ButterFlyDAOImp butterFlyDAOImp;

    @Test
    public void firstTest() throws Exception {
        System.out.println(DoubleUtil.format_nice(Double.parseDouble("5.5432")));
    }
}
