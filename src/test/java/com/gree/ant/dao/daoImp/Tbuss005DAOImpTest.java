package com.gree.ant.dao.daoImp;

import com.gree.MyNutTestRunner;
import com.gree.ant.mo.Tbuss005MO;
import com.gree.ant.vo.Tbuss005VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;


@RunWith(MyNutTestRunner.class)
@IocBean
public class Tbuss005DAOImpTest {

    @Inject
    private Tbuss005DAOImp tbuss005DAOImp;

    @Inject
    private Tbuss005MO tbuss005MO;

    @Test
    public void updateByVO() throws Exception {
        List<Tbuss005VO> tbuss005VOS = tbuss005MO.queryAllByCndPager(Cnd.where("ptno","=","PT002").and("pjno","=","P01").and("csid","=","180365"),null);
        Tbuss005VO tbuss005VO = tbuss005VOS.get(0);
        tbuss005VO.setCons(102+"");
    }
}