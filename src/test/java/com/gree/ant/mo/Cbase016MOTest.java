package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class Cbase016MOTest {

    @Inject
    private Cbase016MO cbase016MO;

    @Inject
    private ConferenceMO conferenceMO;

    @Test
    public void queryAllSearch() {
        FileUtil fileUtil = FileUtil.createFileUtil();
    }
}