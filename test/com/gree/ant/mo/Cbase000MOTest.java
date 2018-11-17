package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Cbase000VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.http.HttpServletRequest;

@RunWith(MyNutTestRunner.class)
@IocBean
public class Cbase000MOTest {

    @Inject
    private Cbase000MO cbase000MO;

    @Test
    public void queryAllByCnd() throws Exception {
    }

    @Test
    public void loginCheck() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        Cbase000VO cbase000VO = cbase000MO.fetchUser("180484");
        HttpServletRequest request = null;
        cbase000VO.setBLOB(FileUtil.formatBlobByByte(FileUtil.getNormalHeader(request,"")));
        cbase000MO.updateByVO(cbase000VO);
    }

}