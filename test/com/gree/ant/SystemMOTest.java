package com.gree.ant;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.dao.daoImp.JieKou_Tbuss003DAOImp_Ds;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.mo.*;
import com.gree.ant.util.SyncButterFlyData;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.Tbuss003VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
@RunWith(MyNutTestRunner.class)
public class SystemMOTest {

    @Inject
    private BaseMoFactory baseMoFactory;
    @Inject
    private BussMoFactory bussMoFactory;

    @Inject
    private Tbuss003DAOImp_Ds tbuss003DAOImp_ds;

    @Inject
    private JieKou_Tbuss003DAOImp_Ds jieKou_tbuss003DAOImp_ds;

    @Inject
    private Tbuss003MO tbuss003MO;

    @Inject
    private Tbuss003MO_Ds tbuss003MO_Ds;

    @Inject
    private ButterFlyDAOImp butterFlyDAOImp;

    @Inject("refer:daoFX")
    private Dao dao;


    @Test
    public void testSystem() throws Exception {
        SyncButterFlyData syncButterFlyData = SyncButterFlyData.createSyncDataUtil();
        syncButterFlyData.carrySync(dao,butterFlyDAOImp);
    }


    @Test
    public void testProjectID() throws Exception {
        String projectName = "电商平台任务";
        if(projectName.contains("[项目]")){
            projectName=projectName.replace("[项目]","");
        }
        System.out.println(tbuss003DAOImp_ds.findIdByDscaLang(projectName));

    }

    @Test
    public void testPersonMessage() throws Exception {
//        Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid("JK61631277");
//        Cbase000VO cbase000VO = baseMoFactory.getCbase000MO().fetchUser("180484");
//        Integer code = tbuss003MO_Ds.insertBug(tbuss003VO,cbase000VO);
        Integer code = tbuss003MO_Ds.deleteBug(22111);
        System.out.println(code);

    }

    @Test
    public void testupdateStatus() throws Exception {
       tbuss003MO_Ds.synchronizationDSSystem();

    }

}