package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.mo.basic.Tbuss003BasicMO_Ds;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean
public class Tbuss003MO_Ds implements Tbuss003BasicMO_Ds {

    @Inject("refer:dao1")
    private Dao dao1;

    @Inject("refer:tbuss003DAOImp_Ds")
    private Tbuss003DAOImp_Ds tbuss003DAOImp_Ds;

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    //查找出创建任务系统的所有阶段
    @Override
    public List<Map> findT3DS_jied(String syno){
        //先查找出一个系统有哪些子阶段
        List<String> jiedStrings=tbuss003DAOImp_Ds.findT3DS_jied(Integer.parseInt(syno));
        Map<String,Object> map=new HashMap();
        List<Map> jiedStrings1=new ArrayList();
        for (int i = 0; i < jiedStrings.size() ; i++) {
            String jied=tbuss003DAOImp_Ds.findT3DS_jiedDacaBySyno(Integer.parseInt(jiedStrings.get(i)));
            map.put("SubProjectID",Integer.parseInt(jiedStrings.get(i)));
            map.put("Title",jied);
            jiedStrings1.add(map);
        }
        return jiedStrings1;

    }

    //把任务插入到DS钟
    @Override
    public void insertBug (Tbuss003VO tbuss003VO, Cbase000VO cbase000VO)throws Exception{
        System.out.println("进入insertBug");
         //根据项目名称查找出项目才能通过名称到LanguageCfgLookupValues找出项目id
         Cbase013VO cbase013VO= (Cbase013VO) baseDAOImp.fetchByName(new Cbase013VO(),tbuss003VO.getSyno());
         //去LanguageCfgLookupValues表通过名称找出id
        int CrntVersionID=tbuss003DAOImp_Ds.findIdByDscaLang(cbase013VO);
        System.out.println("找出最大的id");
        //找出项目最大的BugID
        int BugID=tbuss003DAOImp_Ds.selectMAX();
        //找出ds系统对应邮箱号的插入任务的人的id
        int PersonID=tbuss003DAOImp_Ds.findPersonIDByLogin(tbuss003VO.getUsid(),tbuss003VO.getUnam());
          /*任务内容从clob转换成String,内容在去除掉ant系统自带的html的内容*/
        String note_ds=tbuss003DAOImp_Ds.StringChange(tbuss003VO.getNote());
        //插入ds系统的任务表Bug

        tbuss003DAOImp_Ds.inserRuleBug(tbuss003VO,BugID,PersonID,note_ds,CrntVersionID);
        //插入ds系统的表CustomerFieldTrackExt(主要插入软件科室和严重程度['关键2'：'严重1':'一般0'])
        tbuss003DAOImp_Ds.insertRuleCustomerFieldTrackExt(tbuss003VO,BugID,cbase000VO);

    }

   //找出Ds数据库中所有的项目，系统,然后删ant系统的数据，把ds系统的数据插入到ant系统
   @Override
    public List<Cbase013VO> synchronizationDSSystem(){
        List<Cbase013VO> systemList=tbuss003DAOImp_Ds.findAllSystemByDs();
        tbuss003DAOImp_Ds.delerefromCbase013();
        for (int i = 0; i <systemList.size() ; i++) {
            Cbase013VO cbase013VO=systemList.get(i);
            baseDAOImp.insert(cbase013VO);
        }
        return systemList;
    }




}
