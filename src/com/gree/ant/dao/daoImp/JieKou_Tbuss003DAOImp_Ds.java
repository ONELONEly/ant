package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.JieKou_Tbuss003DAO_Ds;
import com.gree.ant.util.HttpRequest;
import com.gree.ant.util.StringUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean
public class JieKou_Tbuss003DAOImp_Ds implements JieKou_Tbuss003DAO_Ds{

    @Inject("refer:dao1")
    private Dao dao1;

    @Inject("refer:daoFX")
    private Dao dao;

    public String findT3DS_jiedDacaBySyno(int SubProjectID){

        Sql sql= Sqls.create("select Title from SubProject where ProjectID=417 AND SubProjectID=@SubProjectID");
        sql.params().set("SubProjectID",SubProjectID);

        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                String Title="";
                if (rs.next())
             Title= rs.getString("Title");
                return Title;
            }
        });
        dao1.execute(sql);
        return sql.getString();
    }

    public  List<String> findT3DS_jied(int ParentID){
        System.out.println("syno"+ParentID);
        Sql sql= Sqls.create("select ChildID from SubProjectTree where ParentID=@ParentID order by DisplayOrder");
        sql.params().set("ParentID",ParentID);
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<String> jiedString = new ArrayList<>();
                while (rs.next())
                    jiedString.add(rs.getString("ChildID"));
                return jiedString;
            }
        });
        dao1.execute(sql);
        return sql.getList(String.class);
    }

    public int findIdByDscaLang(Cbase013VO cbase013VO){
        Sql sql= Sqls.create("select FieldLookupValueID from LanguageCfgLookupValues where ProjectID=417 and FieldID=106 and LookupValueName like @LookupValueName ");
        sql.params().set("LookupValueName",cbase013VO.getDsca());
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                int  result=0;
                if (rs.next())
                    result = rs.getInt(1);
                return result;
            }
        });
        dao1.execute(sql);
        return sql.getInt();
    }

    public void delerefromCbase013(){
        Sql sql= Sqls.create("delete from cbase013");
        dao.execute(sql);
    }


    public List<Cbase013VO> findAllSystemByDs(){
        Sql sql= Sqls.create("select SubProjectID,Title from SubProject where SubProjectType =98 and ProjectID=417");
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List cbase013VOList = new ArrayList<>();
                while(rs.next()){

                    cbase013VOList.add(new Cbase013VO(rs.getString("SubProjectID"),rs.getString("Title")));
                }
                return cbase013VOList;
            }
        });
        dao1.execute(sql);
        return sql.getList(Cbase013VO.class);
    }

    public int insertRuleCustomerFieldTrackExt(Tbuss003VO tbuss003VO, int BugID, Cbase000VO cbase000VO){
        int code=1;
        Sql sql= Sqls.create("insert into CustomerFieldTrackExt(ProjectID,SequenceNo,BugID,Custom_3,Desc_Custom_1) values(417,1,@BugID,@Custom_3,@Desc_Custom_1)");
        //获取严重程度
        String sta3=trantanceString(tbuss003VO.getSta3());
        //获取科室名称
        String dsca=findDscaByAcco(cbase000VO.getACCO());
        sql.params().set("BugID",BugID+1).set("BugID",(BugID+1)).set("Custom_3",dsca).set("Desc_Custom_1",sta3);
        try {
            dao1.execute(sql);
        }catch (Exception e){
            code=0;
        }
      return code;
    }

    public String findDscaByAcco(String acco){
        Sql sql= Sqls.create("select dsca from cbase017 where acco =@acco");
        sql.params().set("acco",acco);
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                String result="";
                if (rs.next())
                    result = rs.getString(1);
                return result;
            }
        });
        dao.execute(sql);
        return sql.getString();
    }

    public String trantanceString(int sta3int){
        String statStr="";
        if(sta3int==0){
            statStr="一般";
        }else if(sta3int==1){
            statStr="严重";
        }else if(sta3int==2){
            statStr="关键";
        }
        return statStr;
    }

    public String inserRuleBug(Tbuss003VO tbuss003VO,Cbase000VO cbase000VO, int PersonID, String note_ds, String CrntVersionID)throws SQLException{
        System.out.println("计划开始时间"+tbuss003VO.getIdat());

        String url = "http://10.2.4.175/DevTrackAPI/Api/Task/Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";
       // String url = "http://10.3.0.101/DevTrackAPI/api/Task/Create?token=8F355D74-CF46-4176-BB16-C76619B9E373";
        JSONObject params = new JSONObject();

        List jsonList=new ArrayList();

        Map<String, String> jm = new HashMap<String, String>();
        jm.put("FieldId","122");
        jm.put("FieldValue",tbuss003VO.getSyno());
        jsonList.add(jm);

        Map<String, String> jm1 = new HashMap<String, String>();
        jm1.put("FieldId","101");
        jm1.put("FieldValue",tbuss003VO.getTitl());
        jsonList.add(jm1);

        Map<String, String> jm2 = new HashMap<String, String>();
        jm2.put("FieldId","103");
        jm2.put("FieldValue",tbuss003VO.getPuno());
        jsonList.add(jm2);

        Map<String, String> jm3 = new HashMap<String, String>();
        jm3.put("FieldId","106");
        jm3.put("FieldValue",CrntVersionID);
        jsonList.add(jm3);

        Map<String, String> jm4 = new HashMap<String, String>();
        jm4.put("FieldId","3");
        jm4.put("FieldValue",cbase000VO.getACCO());
        jsonList.add(jm4);

        Map<String, String> jm5 = new HashMap<String, String>();
        jm5.put("FieldId","104");
        jm5.put("FieldValue",tbuss003VO.getSta2().toString());
        jsonList.add(jm5);

        Map<String, String> jm6 = new HashMap<String, String>();
        jm6.put("FieldId","102");
        jm6.put("FieldValue",note_ds);
        jsonList.add(jm6);

        Map<String, String> jm7 = new HashMap<String, String>();
        jm7.put("FieldId","13");
        jm7.put("FieldValue",tbuss003VO.getSta3().toString());
        jsonList.add(jm7);

        Map<String, String> jm8 = new HashMap<String, String>();
        jm8.put("FieldId","601");
        jm8.put("FieldValue","332");
        jsonList.add(jm8);

        Map<String, String> jm9 = new HashMap<String, String>();
        jm9.put("FieldId","108");
        jm9.put("FieldValue",PersonID+"");
        jsonList.add(jm9);



        Map<String, String> jm11 = new HashMap<String, String>();
        jm11.put("FieldId","603");
        jm11.put("FieldValue",PersonID+"");
        jsonList.add(jm11);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//目标格式
        String pdat = sdf.format(tbuss003VO.getPdat());

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//目标格式
        String idat = sdf1.format(tbuss003VO.getIdat());

        Map<String, String> jm12 = new HashMap<String, String>();
        jm12.put("FieldId","606");
        jm12.put("Option","1");
        jm12.put("FieldValue",idat);
        jsonList.add(jm12);

        Map<String, String> jm13 = new HashMap<String, String>();
        jm13.put("FieldId","607");
        jm13.put("Option","1");
        jm13.put("FieldValue",pdat);
        jsonList.add(jm13);

        Map<String, String> jm14 = new HashMap<String, String>();
        jm14.put("FieldId","620");
        jm14.put("FieldValue",tbuss003VO.getFahh()+"");
        jsonList.add(jm14);

        Map<String, String> jm15 = new HashMap<String, String>();
        jm15.put("FieldId","632");
        jm15.put("Option","1");
        jm15.put("FieldValue",idat);
        jsonList.add(jm15);

        Map<String, String> jm16 = new HashMap<String, String>();
        jm16.put("FieldId","624");
        jm16.put("Option","1");
        jm16.put("FieldValue",pdat);
        jsonList.add(jm16);

        Map<String, String> jm17 = new HashMap<String, String>();
        jm17.put("FieldId","621");
        jm17.put("FieldValue","0");
        jsonList.add(jm17);

        params.put("ProjectId", "417");
        params.put("TemplateId", "0");
        params.put("FieldValues", jsonList);

        System.out.println("传过去的参数"+params);


        String params1=params.toString();
        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);
        System.out.println(Json.toJson(params2, JsonFormat.compact()));

        Header headers = Header.create().set("Content-Type", "application/json");

        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 2000);

        String content=response.getContent();
        System.out.println(content);
        String jsonstr=content.substring(content.indexOf("<Data>")+6,content.indexOf("</Data>"))+","+content.substring(content.indexOf("<Success>")+9,content.indexOf("</Success>"));



        return jsonstr;
    }


   public  String updateBugStatus(String bugId){
        //http://10.2.4.175/DevTrackAPI/Help/Api/POST-api-Task-Create
       String url = "http://10.2.4.175/DevTrackAPI/Api/Task/Update?token=8F355D74-CF46-4176-BB16-C76619B9E373";
       //String url="http://10.2.4.175/DevTrackAPI/Help/Api/POST-api-Task-Update?token=8F355D74-CF46-4176-BB16-C76619B9E373";
       //String url = "http://10.3.0.101/DevTrackAPI/api/Task/Update?token=8F355D74-CF46-4176-BB16-C76619B9E373";
       JSONObject params = new JSONObject();

       List jsonList=new ArrayList();

       Map<String, String> jm = new HashMap<String, String>();
       jm.put("FieldId","601");
       jm.put("Option","0");
       jm.put("FieldValue","335");
       jsonList.add(jm);

       params.put("ProjectId", "417");
       params.put("TaskId", bugId);
       params.put("FieldValues", jsonList);

       Map<String, String> headers = new HashMap<String, String>();

       //headers.put("token", "8F355D74-CF46-4176-BB16-C76619B9E373");
       //headers.put("content-length", "1430");
       headers.put("content-type", "application/json");
       String jsonstr= HttpRequest.postJSON(url,params, headers);
       System.out.println(jsonstr);
       return jsonstr;
    }

/*

    public  String StringChange(Clob note)throws Exception{

        //字符串从clob转换成String
        String note_ds= StringUtil.ClobToString(note);
        //去除掉ant数据库中的<p><span style=""></span></p>
        String removeNote_Ds="<p><span style=\"\">";
        String removeNote_Ds1="</span></p>";
        String note1=StringUtils.remove(note_ds,removeNote_Ds1);
        return StringUtils.remove(note1,removeNote_Ds);
    }
*/

    public  String StringChange(Clob note)throws Exception{
        //字符串从clob转换成String
        String note_ds= StringUtil.ClobToString(note);
        //去除掉ant数据库中的<p><span style=""></span></p>
        String p="<p>";
        String fp="</p>";
        String s="<span style=\"\">";
        String fs="</span>";
        String br="<br>";
        String p1=note_ds.replaceAll(p,"");
        String p2=p1.replaceAll(fp,"");
        String p3=p2.replaceAll(s,"");
        String p4=p3.replace(fs,"");
        String p5=p4.replace(br,"");
        return p5;
    }

    public int findPersonIDByLogin(String usid,String unam){
        Sql sql= Sqls.create("select PersonID from LogIn where Login=@Login or FName like @FName");
        sql.params().set("Login",usid).set("FName",unam);

        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                int  result=0;
                while (rs.next())
                    result = rs.getInt(1);

                return result;
            }
        });
        dao1.execute(sql);
        return sql.getInt();

    }

    public int selectMAX(){
        Sql sql= Sqls.create("select MAX(BugID) from bug where  ProjectID=417");

        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                int  result=0;
                while (rs.next())
                    result = rs.getInt(1);
                return result;
            }
        });
        dao1.execute(sql);
        return sql.getInt();
        }
}



