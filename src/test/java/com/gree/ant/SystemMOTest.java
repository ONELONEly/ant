package com.gree.ant;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.dao.daoImp.JieKou_Tbuss003DAOImp_Ds;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.exception.KellyException;
import com.gree.ant.mo.*;
import com.gree.ant.util.HttpRequest;
import com.gree.ant.util.SyncButterFlyData;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.ConferenceProject;
import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.enumVO.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@IocBean
@RunWith(MyNutTestRunner.class)
public class SystemMOTest {

    private Logger logger = LoggerFactory.getLogger(SystemMOTest.class);

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
    private ConferenceProjectMO conferenceProjectMO;

    @Inject("refer:daoFX")
    private Dao dao;


    @Test
    public void testSystem() throws Exception {
//        ConferenceProject conferenceProject = conferenceProjectMO.fetchProject("1146680637600563200");
//        System.out.println(conferenceProject.getProjectUsers());
        try{
            Cbase000VO cbase000VO = new Cbase000VO();
            Integer.parseInt(cbase000VO.getUSID());
            cbase000VO.getUSID().equals("aaa");
        }catch (Exception e){
            String msg = HttpRequest.getExceptionInformation(e);
            String regEx = "Caused by:(.*)";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(msg);
            boolean rs = matcher.find();
            System.out.println(matcher.group(1));
        }
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
//        String[] args = {"JK67686088","JK85150291","JK92198135","JK83811659"};
//        for(String arg:args) {
//            Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(arg);
//            Cbase000VO cbase000VO = baseMoFactory.getCbase000MO().fetchUser("180389");
//            Integer code = tbuss003MO_Ds.insertBug(tbuss003VO, cbase000VO);
////        Integer code = tbuss003MO_Ds.deleteBug(22111);
//            System.out.println(code);
//        }

        Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid("JK36451061");
        Cbase000VO cbase000VO = baseMoFactory.getCbase000MO().fetchUser("180484");

    }

    @Test
    public void testupdateStatus() throws Exception {
       tbuss003MO_Ds.synchronizationDSSystem();

    }

}