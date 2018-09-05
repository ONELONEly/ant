package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MyNutTestRunner.class)
@IocBean
public class Ds000MOInterfaceTest {

    @Test
     public void Test1(){

       String url = "http://10.3.0.101/DevTrackAPI/api/Task/Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";
       JSONObject params = new JSONObject();

       List jsonList=new ArrayList();

       Map<String, String> jm = new HashMap<String, String>();
       jm.put("FieldId","122");
       jm.put("FieldValue","23335");
       jsonList.add(jm);

       Map<String, String> jm1 = new HashMap<String, String>();
       jm1.put("FieldId","101");
       jm1.put("FieldValue","同步DS测试");
       jsonList.add(jm1);

       Map<String, String> jm2 = new HashMap<String, String>();
       jm2.put("FieldId","103");
       jm2.put("FieldValue","118");
       jsonList.add(jm2);

       Map<String, String> jm3 = new HashMap<String, String>();
       jm3.put("FieldId","106");
       jm3.put("FieldValue","360");
       jsonList.add(jm3);

       Map<String, String> jm4 = new HashMap<String, String>();
       jm4.put("FieldId","3");
       jm4.put("FieldValue","8");
       jsonList.add(jm4);

       Map<String, String> jm5 = new HashMap<String, String>();
       jm5.put("FieldId","104");
       jm5.put("FieldValue","37");
       jsonList.add(jm5);

       Map<String, String> jm6 = new HashMap<String, String>();
       jm6.put("FieldId","102");
       jm6.put("FieldValue","同步DS测试");
       jsonList.add(jm6);

       Map<String, String> jm7 = new HashMap<String, String>();
       jm7.put("FieldId","13");
       jm7.put("FieldValue","1");
       jsonList.add(jm7);

       Map<String, String> jm8 = new HashMap<String, String>();
       jm8.put("FieldId","601");
       jm8.put("FieldValue","335");
       jsonList.add(jm8);

       Map<String, String> jm9 = new HashMap<String, String>();
       jm9.put("FieldId","108");
       jm9.put("FieldValue","1309");
       jsonList.add(jm9);



       Map<String, String> jm11 = new HashMap<String, String>();
       jm11.put("FieldId","603");
       jm11.put("FieldValue","1309");
       jsonList.add(jm11);



       params.put("ProjectId", "417");
       params.put("TemplateId", "0");
       params.put("FieldValues", jsonList);

       System.out.println(params);

       Map<String, String> headers = new HashMap<String, String>();

       //headers.put("token", "8F355D74-CF46-4176-BB16-C76619B9E373");
       //headers.put("content-length", "1430");
       headers.put("content-type", "application/json");
       String jsonstr= HttpRequest.postJSON(url,params, headers);
       System.out.println(jsonstr);
     }


    }




