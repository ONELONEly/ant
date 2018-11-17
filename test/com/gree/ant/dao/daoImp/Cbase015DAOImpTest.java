package com.gree.ant.dao.daoImp;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.Cbase015DAO;
import com.gree.ant.mo.Tbuss001MO;
import com.gree.ant.vo.Cbase011VO;
import com.gree.ant.vo.Tbuss001VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;


@IocBean
@RunWith(MyNutTestRunner.class)
public class Cbase015DAOImpTest {

    @Inject
    private Tbuss001MO tbuss001MO;

    @Test
    public void deleteByStaid() throws Exception {

        Tbuss001VO tbuss001VO = tbuss001MO.fectchByName("PT68642301");
        Cnd cnd = Cnd.where("stat", "=", 1);
        Tbuss001VO t1 = tbuss001MO.fetchLinksByVO(tbuss001VO,"cbase011VOS",cnd);
        List<Cbase011VO> cbase011VOS = t1.getCbase011VOS();
    }

}