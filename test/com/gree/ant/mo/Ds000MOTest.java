package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(MyNutTestRunner.class)
@IocBean
public class Ds000MOTest {


    @Inject("refer:dao1")
    private Dao dao1;

    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject("refer:tbuss003MO_Ds")
    private Tbuss003MO_Ds tbuss003MO_Ds;





    @Test
    public void insertSql() throws Exception {
        List list=tbuss003MO_Ds.findT3DS_jied("19108");
        System.out.println(list.size());

/*        Tbuss003VO tbuss003VO = tbuss003MO.fetchTrans("JK92997406", "tbuss010VOS", null);
        Cbase000VO cbase000VO=new Cbase000VO();
        cbase000VO.setUSID("180304");*/
       // tbuss003MO_ds.insertBug(tbuss003VO,cbase000VO);

    //找出项目最大的BugID
      /*  int BugID=selectMAX();
        System.out.println(BugID);*/
/*        //找出ds系统对应邮箱号的插入任务的人的id
        int PersonID=findPersonIDByLogin(tbuss003VO.getUsid(),tbuss003VO.getUnam());
          *//**//*任务内容从clob转换成String,内容在去除掉ant系统自带的html的内容*//**//*
        String note_ds=StringChange(tbuss003VO.getNote());
        //插入ds系统的任务表Bug
        inserRuleBug(tbuss003VO,BugID,PersonID,note_ds);
        //插入ds系统的表CustomerFieldTrackExt(主要插入软件科室和严重程度['关键2'：'严重1':'一般0'])
        insertRuleCustomerFieldTrackExt(tbuss003VO,BugID);*/


    }
/*

    public void insertRuleCustomerFieldTrackExt(Tbuss003VO tbuss003VO,int BugID){
        Sql sql=Sqls.create("insert into CustomerFieldTrackExt(ProjectID,SequenceNo,BugID,Custom_3,Desc_Custom_1) values(417,1,15382,N'软件应用一室',N'一般')");
      //  Cbase000VO cbase000VO=(Cbase000VO)session.getAttribute("cbase000VO");
      //  sql.params().set("BugID",BugID).set("Custom_3",cbase000VO.getACCO()).set("Desc_Custom_1",tbuss003VO.getSta3());
        dao1.execute(sql);
    }


    public void inserRuleBug(Tbuss003VO tbuss003VO,int BugID,int PersonID,String note_ds){


        Sql sql= Sqls.create("insert into Bug(ProjectID,BugID,ProblemID,BugTitle,CreatedByPerson,ProblemDescription,DateCreated,CurrentOwner,ProgressStatusID,CrntBugTypeID,CrntPriorityID,TimeSpent)\n" +
                "values(417,@BugID,@ProblemID,@BugTitle,@CreatedByPerson,@ProblemDescription,@DateCreated,@CurrentOwner,335,@CrntBugTypeID,@CrntPriorityID,@TimeSpent)");

        sql.params().set("BugID",BugID+1).set("ProblemID","id"+(BugID+1)).set("BugTitle",tbuss003VO.getTitl()).set("CreatedByPerson",PersonID).set("ProblemDescription",note_ds)
                .set("DateCreated",tbuss003VO.getXdat()).set("CurrentOwner",PersonID).set("CrntBugTypeID",tbuss003VO.getPuno()).set("CrntPriorityID",tbuss003VO.getSta2()).set("TimeSpent",tbuss003VO.getFahh());

        dao1.execute(sql);

    }

*/
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
*//*


    @Test
    public  String StringChange(Clob note)throws Exception{
        //字符串从clob转换成String
        String note_ds= StringUtil.ClobToString(note);
        //去除掉ant数据库中的<p><span style=""></span></p>
     String p="<p>";
     String fp="</p>";
     String s="<span style=\"\">";
     String fs="</span>";
     String p1=note_ds.replaceAll(p,"");
     String p2=p1.replaceAll(fp,"");
     String p3=p2.replaceAll(s,"");
     String p4=p3.replace(fs,"");
        return p4;
    }

    public int findPersonIDByLogin(String usid,String unam){
        Sql sql=Sqls.create("select PersonID from LogIn where Login=@Login or FName like @FName");
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
*/
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




