package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Tbuss003DAO_Ds;
import com.gree.ant.util.DateUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@IocBean
public class Tbuss003DAOImp_Ds implements Tbuss003DAO_Ds {

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

    public String findIdByDscaLang(String  projectName){

        Sql sql= Sqls.create("select FieldLookupValueID from LanguageCfgLookupValues where ProjectID=417 and FieldID=106 and LookupValueName like @LookupValueName ");
        sql.params().set("LookupValueName",projectName.trim());
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                int  result=0;
                if (rs.next())
                    result = rs.getInt(1);
                return result;
            }
        });
        dao1.execute(sql);
        return sql.getString();
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

    public int inserRuleBug(Tbuss003VO tbuss003VO, int BugID, int PersonID, String note_ds, String CrntVersionID)throws SQLException{
        int code=1;
         Sql sql= Sqls.create("insert into Bug(ProjectID,BugID,ProblemID,BugTitle,CreatedByPerson,ProblemDescription,DateCreated,CurrentOwner,ProgressStatusID,CrntBugTypeID,CrntPriorityID,TimeSpent,SubProjectID,CrntVersionID,DateAssigned,PlanedStartDate," +
                "IssueFinishDate,PlanedEndDate,IssueActualStartDate,DateClosed,AssignedByPersonID,ClosedByPerson)\n" +
                "values(417,@BugID,@ProblemID,@BugTitle,@CreatedByPerson,@ProblemDescription,@DateCreated,@CurrentOwner,335,@CrntBugTypeID,@CrntPriorityID,@TimeSpent,@SubProjectID,@CrntVersionID,@DateAssigned,@PlanedStartDate," +
                "@IssueFinishDate,@PlanedEndDate,@IssueActualStartDate,@DateClosed,@AssignedByPersonID,@ClosedByPerson )");

        sql.params().set("BugID",BugID+1).set("ProblemID","id"+(BugID+1)).set("BugTitle",tbuss003VO.getTitl()).set("CreatedByPerson",PersonID).set("ProblemDescription",note_ds)
                .set("DateCreated",tbuss003VO.getXdat()).set("CurrentOwner",PersonID).set("CrntBugTypeID",tbuss003VO.getPuno()).set("CrntPriorityID",tbuss003VO.getSta2()).set("TimeSpent",tbuss003VO.getFahh()*60)
        .set("SubProjectID",tbuss003VO.getJied()).set("CrntVersionID",CrntVersionID).set("DateAssigned", DateUtil.formatYMDDate(tbuss003VO.getCdat())).set("PlanedStartDate", DateUtil.formatYMDDate(tbuss003VO.getCdat())).
                set("IssueActualStartDate", DateUtil.formatYMDDate(tbuss003VO.getCdat())).  set("PlanedEndDate",new Date()).  set("IssueFinishDate", new Date())
                .set("DateClosed",new Date()).set("AssignedByPersonID",PersonID).set("ClosedByPerson",PersonID);
       try {
           dao1.execute(sql);
       }catch (Exception E){
           code=0;
       }
       return code;
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
        Sql sql= Sqls.create("select PersonID from LogIn where Login=@Login");
        sql.params().set("Login",usid);

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



