package com.gree;

import com.gree.ant.dao.BaseDAO;
import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.vo.Cbase000VO;
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

    @Test
    public void firstTest() throws Exception {
        BaseDAOImp<Cbase000VO> cbase000VOBaseDAO = new BaseDAOImp<>();
        System.out.println(cbase000VOBaseDAO.getEntryClz().getName());
    }
}
