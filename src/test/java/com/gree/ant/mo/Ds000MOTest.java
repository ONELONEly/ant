package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.dao.daoImp.JieKou_Tbuss003DAOImp_Ds;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;
import net.sf.json.JSONObject;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(MyNutTestRunner.class)
@IocBean
public class Ds000MOTest {

    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject("refer:tbuss003DAOImp_Ds")
    private Tbuss003DAOImp_Ds tbuss003DAOImp_Ds;

    @Inject("refer:jieKou_Tbuss003DAOImp_Ds")
    private JieKou_Tbuss003DAOImp_Ds jieKou_Tbuss003DAOImp_Ds;

    @Inject
    private Cbase013MO cbase013MO;

    @Test
public void dateTest()throws Exception{
    String time="2010-11-20 11:10:10";

    Date date=null;
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    date=formatter.parse(time);
}

 @Test
  public void test()throws Exception{

     //tbuss003MO_Ds.insertBugJieKou();
     //tbuss003MO_Ds.insertBugJieKou();
    // tbuss003MO_Ds.update();
     String content="<API_Data_int xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.datacontract.org/2004/07/TechExcel.DTKService.Model.Common\"><Data>15628</Data><Error i:nil=\"true\" /><Success>true</Success></API_Data_int>";

  }

    @Test
    public void test4()throws Exception{
        //String url = "http://10.2.4.175/DevTrackAPI/Api/Task/Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";
        //String url="http://10.2.4.175/DevTrackAPI/Api/POST-api-Task-Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";


        String url = "http://10.3.0.101/DevTrackAPI/api/Task/Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";

        // String params1 = Files.read("test/mock/1.json");
        String params1="{\"ProjectId\":\"417\",\"TemplateId\":\"0\",\"FieldValues\":[{\"FieldId\":\"122\",\"FieldValue\":\"19108\"},{\"FieldId\":\"101\",\"FieldValue\":\"优化采购订单查询时间字段\"},{\"FieldId\":\"103\",\"FieldValue\":\"93\"},{\"FieldId\":\"106\",\"FieldValue\":\"361\"},{\"FieldId\":\"3\",\"FieldValue\":\"3\"},{\"FieldId\":\"104\",\"FieldValue\":\"33\"},{\"FieldId\":\"102\",\"FieldValue\":\"优化采购订单查询时间字段\"},{\"FieldId\":\"13\",\"FieldValue\":\"3\"},{\"FieldId\":\"601\",\"FieldValue\":\"332\"},{\"FieldId\":\"108\",\"FieldValue\":\"1309\"},{\"FieldId\":\"603\",\"FieldValue\":\"1309\"},{\"FieldId\":\"606\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"607\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"620\",\"FieldValue\":\"3.0\"},{\"FieldId\":\"632\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"624\",\"Option\":\"1\",\"FieldValue\":\"2018-04-17\"},{\"FieldId\":\"621\",\"FieldValue\":\"0\"}]}";

        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);

        Header headers = Header.create().set("Content-Type", "application/json");

        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 2000);

//        Response response = Sender.create(Request.create(url, Request.METHOD.POST, params2, headers))
//                .setTimeout(60000)
//                .send();
        String content=response.getContent();

        /*  Document doc= DocumentHelper.parseText(content);
          Element e1=doc.getRootElement();*/
 /*         String result=parserNode(e1);;*/


     /*    if(list!=null){
             for (Iterator iter = list.iterator(); iter.hasNext();) {
                 Map row = (Map) iter.next();
                name=row.get("name").toString();
                value=row.get("value").toString();

             }*/


}

//递归解析x
    public static String parserNode(Element ele){

        List list=new ArrayList();
        String result="";

        if(ele.getName().startsWith("Data")||ele.getName().startsWith("Success")){
            Map map=new HashMap();
            map.put("name",ele.getName());
            map.put("value",ele.getText());
            list.add(map);
            result=ele.getName()+":"+ele.getText().trim();

        }

        //从Users根节点开始遍历，像【属性=值】的形式存为一个Attribute对象存储在List集合中
        List<Attribute> attrList = ele.attributes();
        for(Attribute attr : attrList){
            //每循环一次，解析此节点的一个【属性=值】，没有则输出空
            String name = attr.getName();
            String value = attr.getValue();

        }

        List<Element> eleList = ele.elements();
        //递归遍历父节点下的所有子节点
        for(Element e : eleList){
            parserNode(e);
        }
        return result;
    }

    @Test
    public void updateTest()throws Exception{

        Tbuss003VO tbuss003VO = tbuss003MO.fetchTrans("JK95374982", "tbuss010VOS", null);
        Cbase000VO cbase000VO=new Cbase000VO();
        cbase000VO.setUSID("180304");
        cbase000VO.setACCO("3");

        Cbase013VO cbase013VO= cbase013MO.fetchBySyno(tbuss003VO.getSyno());
        String dsca=cbase000VO.getDSCA();
        //去LanguageCfgLookupValues表通过名称找出id
        String CrntVersionID=tbuss003DAOImp_Ds.findIdByDscaLang(dsca);


        //找出ds系统对应邮箱号的插入任务的人的id
        int PersonID=tbuss003DAOImp_Ds.findPersonIDByLogin(tbuss003VO.getUsid(),tbuss003VO.getUnam());
          /*任务内容从clob转换成String,内容在去除掉ant系统自带的html的内容*/
        String note_ds=tbuss003DAOImp_Ds.StringChange(tbuss003VO.getNote());
        //插入ds系统的任务表Bug

        //jieKou_Tbuss003DAOImp_Ds
        String insertRuleJson=jieKou_Tbuss003DAOImp_Ds.inserRuleBug(tbuss003VO,cbase000VO,PersonID,note_ds,CrntVersionID);
        JSONObject jsonString = JSONObject.fromObject(insertRuleJson);
        String result="";
        if(Boolean.valueOf(jsonString.get("Success").toString())){
            result=jieKou_Tbuss003DAOImp_Ds.updateBugStatus(jsonString.get("Data").toString());
        }
        JSONObject resultJson=JSONObject.fromObject(result);



    }

    @Test
    public void realTest()throws Exception{

         String dsca="[项目]芯片分包生产管理系统";
         String a="";
         if(dsca.indexOf("[项目]")!=-1){
             a=dsca.replace("[项目]","");
         }


/*        Tbuss003VO tbuss003VO = tbuss003MO.fetchTrans("JK81219149", "tbuss010VOS", null);
        Cbase000VO cbase000VO=new Cbase000VO();
        cbase000VO.setUSID("180304");
        cbase000VO.setACCO("3");
        String insertBugCode=tbuss003MO_Ds.insertBugJieKou(tbuss003VO,cbase000VO);*/
    }



    }




