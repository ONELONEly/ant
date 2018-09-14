package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.JieKou_Tbuss003DAOImp_Ds;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.mo.basic.Tbuss003BasicMO_Ds;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean
public class Tbuss003MO_Ds implements Tbuss003BasicMO_Ds {

    @Inject("refer:dao1")
    private Dao dao1;

    @Inject("refer:jieKou_Tbuss003DAOImp_Ds")
    private JieKou_Tbuss003DAOImp_Ds jieKou_Tbuss003DAOImp_Ds;

    @Inject("refer:tbuss003DAOImp_Ds")
    private Tbuss003DAOImp_Ds tbuss003DAOImp_Ds;

    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject
    private Cbase013MO cbase013MO;



    //查找出创建任务系统的所有阶段
    @Override
    public List findT3DS_jied(String syno){
        //先查找出一个系统有哪些子阶段
        List<String> jiedStrings=tbuss003DAOImp_Ds.findT3DS_jied(Integer.parseInt(syno));

        List jiedStrings1=new ArrayList();
        for (int i = 0; i < jiedStrings.size() ; i++) {
            Map<String,Object> map=new HashMap();
            String jied=tbuss003DAOImp_Ds.findT3DS_jiedDacaBySyno(Integer.parseInt(jiedStrings.get(i)));
            map.put("SubProjectID",Integer.parseInt(jiedStrings.get(i)));
            map.put("Title",jied);
            jiedStrings1.add(map);
        }
        return jiedStrings1;

    }

    public String findT3DS_jiedDacaBySyno(String syno){
        String jied=tbuss003DAOImp_Ds.findT3DS_jiedDacaBySyno(Integer.parseInt(syno));
        return jied;
    }

    //把任务插入到DS中
    @Override
    public int insertBug (Tbuss003VO tbuss003VO, Cbase000VO cbase000VO)throws Exception{
        int code=0;
        System.out.println("进入insertBug");
        //根据项目名称查找出项目才能通过名称到LanguageCfgLookupValues找出项目id
        Cbase013VO cbase013VO= cbase013MO.fetchBySyno(tbuss003VO.getSyno());
        String dsca=cbase000VO.getDSCA();
        String projectName="";
        if(dsca.indexOf("[项目]")!=-1){
            projectName=dsca.replace("[项目]","");
        }
        //去LanguageCfgLookupValues表通过名称找出id
        String CrntVersionID=tbuss003DAOImp_Ds.findIdByDscaLang(projectName);
        System.out.println("找出最大的id");
        //找出项目最大的BugID
        int BugID=tbuss003DAOImp_Ds.selectMAX();
        //找出ds系统对应邮箱号的插入任务的人的id
        int PersonID=tbuss003DAOImp_Ds.findPersonIDByLogin(tbuss003VO.getUsid(),tbuss003VO.getUnam());
          /*任务内容从clob转换成String,内容在去除掉ant系统自带的html的内容*/
        String note_ds=tbuss003DAOImp_Ds.StringChange(tbuss003VO.getNote());
        //插入ds系统的任务表Bug

        int insertRuleCode=tbuss003DAOImp_Ds.inserRuleBug(tbuss003VO,BugID,PersonID,note_ds,CrntVersionID);

        //jieKou_Tbuss003DAOImp_Ds
        String insertRuleCode1=jieKou_Tbuss003DAOImp_Ds.inserRuleBug(tbuss003VO,cbase000VO,PersonID,note_ds,CrntVersionID);
        //插入ds系统的表CustomerFieldTrackExt(主要插入软件科室和严重程度['关键2'：'严重1':'一般0'])
        int insertRuleCustomerFieldTrackExtCode=tbuss003DAOImp_Ds.insertRuleCustomerFieldTrackExt(tbuss003VO,BugID,cbase000VO);
        if(insertRuleCode==1&&insertRuleCustomerFieldTrackExtCode==1){
            code=1;
        }
        return code;
    }

   //找出Ds数据库中所有的项目，系统,然后删ant系统的数据，把ds系统的数据插入到ant系统
   @Override
    public List<Cbase013VO> synchronizationDSSystem(){
        List<Cbase013VO> systemList=tbuss003DAOImp_Ds.findAllSystemByDs();
        tbuss003DAOImp_Ds.delerefromCbase013();
        for (int i = 0; i <systemList.size() ; i++) {
            Cbase013VO cbase013VO=systemList.get(i);
            cbase013MO.insert(cbase013VO);
        }
        return systemList;
    }


    @Override
    public String insertBugJieKou (Tbuss003VO tbuss003VO,Cbase000VO cbase000VO)throws Exception{

        Cbase013VO cbase013VO= cbase013MO.fetchBySyno(tbuss003VO.getSyno());
        String dsca=cbase013VO.getDsca();

        String projectName="";
        if(dsca.indexOf("[项目]")!=-1){
            projectName=dsca.replace("[项目]","");
        }
        //去LanguageCfgLookupValues表通过名称找出id
        String CrntVersionID=tbuss003DAOImp_Ds.findIdByDscaLang(projectName);
        //找出ds系统对应邮箱号的插入任务的人的id
        int PersonID=tbuss003DAOImp_Ds.findPersonIDByLogin(tbuss003VO.getCsid(),tbuss003VO.getUnam());
          /*任务内容从clob转换成String,内容在去除掉ant系统自带的html的内容*/
        String note_ds=tbuss003DAOImp_Ds.StringChange(tbuss003VO.getNote());
        //插入ds系统的任务表Bug
        //jieKou_Tbuss003DAOImp_Ds
        String insertRuleJson=jieKou_Tbuss003DAOImp_Ds.inserRuleBug(tbuss003VO,cbase000VO,PersonID,note_ds,CrntVersionID);
        String[] results=insertRuleJson.split(",");
        String Sussess=results[1];
        String data=results[0];
        String updateResult="";
        if(results!=null){
            if(Sussess.equals("true")){
                updateResult=jieKou_Tbuss003DAOImp_Ds.updateBugStatus(data);
                JSONObject resultJson=JSONObject.fromObject(updateResult);
                if(Boolean.valueOf(resultJson.get("Success").toString())){
                    return "Success";
                }
            }
        }

        return "false";
    }


    public void test4(){
        String url = "http://10.3.0.101/DevTrackAPI/api/Task/Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";
        //  String params1 = Files.read("test/mock/1.json");
        String params1="{\"ProjectId\":\"417\",\"TemplateId\":\"0\",\"FieldValues\":[{\"FieldId\":\"122\",\"FieldValue\":\"19108\"},{\"FieldId\":\"101\",\"FieldValue\":\"测试插入DS\"},{\"FieldId\":\"103\",\"FieldValue\":\"93\"},{\"FieldId\":\"106\",\"FieldValue\":\"361\"},{\"FieldId\":\"3\",\"FieldValue\":\"3\"},{\"FieldId\":\"104\",\"FieldValue\":\"33\"},{\"FieldId\":\"102\",\"FieldValue\":\"测试插入DS\"},{\"FieldId\":\"13\",\"FieldValue\":\"3\"},{\"FieldId\":\"601\",\"FieldValue\":\"332\"},{\"FieldId\":\"108\",\"FieldValue\":\"1309\"},{\"FieldId\":\"603\",\"FieldValue\":\"1309\"},{\"FieldId\":\"606\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"607\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"620\",\"FieldValue\":\"3.0\"},{\"FieldId\":\"632\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"624\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"621\",\"FieldValue\":\"0\"}]}";

        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);
        System.out.println(Json.toJson(params2, JsonFormat.compact()));

        Header headers = Header.create().set("Content-Type", "application/json");

        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 2000);

//        Response response = Sender.create(Request.create(url, Request.METHOD.POST, params2, headers))
//                .setTimeout(60000)
//                .send();

        System.out.println(response.getHeader());
        System.out.println(response.getContent());
    }

    public void update(){
        jieKou_Tbuss003DAOImp_Ds.updateBugStatus("16337");
    }

}
