package com.gree.ant;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.daoImp.JieKou_Tbuss003DAOImp_Ds;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.mo.BaseMoFactory;
import com.gree.ant.mo.Cbase013MO;
import com.gree.ant.mo.Cbase016MO;
import com.gree.ant.mo.Tbuss003MO;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.Tbuss003VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
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
    private Tbuss003DAOImp_Ds tbuss003DAOImp_ds;

    @Inject
    private JieKou_Tbuss003DAOImp_Ds jieKou_tbuss003DAOImp_ds;

    @Inject
    private Tbuss003MO tbuss003MO;

   //cbase018MO

    @Test
    public void testSystem() throws Exception {

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
        Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid("JK83228254");
        Cbase000VO cbase000VO = baseMoFactory.getCbase000MO().fetchUser("180484");
        int PersonID =tbuss003DAOImp_ds.findPersonIDByLogin("180484","赖元杰");
        String result = jieKou_tbuss003DAOImp_ds.inserRuleBug(tbuss003VO,cbase000VO,PersonID,"测试数据","0");
        System.out.println(result);

    }

    @Test
    public void testupdateStatus() throws Exception {
       String result = jieKou_tbuss003DAOImp_ds.updateBugStatus("20499");
        System.out.println(result);

    }

}