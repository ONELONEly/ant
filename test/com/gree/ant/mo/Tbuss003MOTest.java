package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Tbuss003VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Tbuss003MOTest {

    @Inject
    private Tbuss003MO tbuss003MO;

    @Test
    public void queryAllByCnd() throws Exception {
        String usid = "180365";
        System.out.println(tbuss003MO.queryGropAllTask(usid,null, Cnd.where("0","=",0)));
    }

    @Test
    public void countByCnd() throws Exception {
    }

    @Test
    public void insertByVO() throws Exception {
    }

    @Test
    public void updateByVO() throws Exception {
    }

    @Test
    public void fetchByTaid() throws Exception {
        System.out.println(tbuss003MO.fetchByTaid("jk42453706").getUnam());
    }

    @Test
    public void deleteByTaid() throws Exception {
    }

}