package com.gree.ant.controller;


import com.gree.ant.mo.*;
import com.gree.ant.util.*;
import com.gree.ant.vo.*;
import org.nutz.aop.interceptor.async.Async;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * The type Doc controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文档提交的Controller
 * @title DocController
 * @createTime 2017 :09:21 09:09:10.
 */
@At("/doc")
@IocBean
public class DocController {

    @Inject("refer:tbuss009MO")
    private Tbuss009MO tbuss009MO;

    @Inject("refer:tbuss015MO")
    private Tbuss015MO tbuss015MO;

    @Inject("refer:cbase018MO")
    private Cbase018MO cbase018MO;

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    @Inject("refer:cbase016MO")
    private Cbase016MO cbase016MO;


    /**
     * Manage map.
     *
     * @return the map
     * @description 文档管理的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 10:09:26.
     */
    @At
    @Ok("jsp:jsp.doc.manage")
    public Map<String,Object> manage(){
        return null;
    }

    /**
     * Insert map.
     *
     * @return the map
     * @description 文档插入的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 10:09:22.
     */
    @At
    @Ok("jsp:jsp.doc.insert")
    public Long insert(){
        return FileUtil.getSyFileName();
    }

    /**
     * Edit tbuss 009 vo.
     *
     * @param doid the doid
     * @return the tbuss 009 vo
     * @description 文档修改的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 04:09:55.
     */
    @At
    @Ok("jsp:jsp.doc.edit")
    public Map<String,Object> edit(@Param("doid")Long doid,@Param("key")String key){
        Tbuss009VO tbuss009VO = tbuss009MO.fetchByID(doid);
        Boolean primary = false;
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("doc",tbuss009VO);
        resultMap.put("note",FileUtil.convertClob(tbuss009VO.getNote()));
        if(StringUtil.checkString(key)){
            primary = true;
        }
        resultMap.put("key",primary);
        return resultMap;
    }

    /**
     * Doc file string.
     *
     * @param doid 文档ID
     * @return the string
     * @description 文档附件入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:24 10:10:56.
     */
    @At
    @Filters
    @Ok("jsp:jsp.doc.docFile")
    public Long docFile(@Param("doid")Long doid){
        return doid;
    }

    /**
     * Wisdom doc string.
     *
     * @return the string
     * @description 智库的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:22 10:09:24.
     */
    @At
    @Ok("jsp:jsp.doc.wisdomDoc")
    public String wisdomDoc(){
        return "success";
    }

    /**
     * Knowledge doc string.
     *
     * @return the string
     * @description 学习文档的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:22 10:09:17.
     */
    @At
    @Ok("jsp:jsp.doc.knowledgeDoc1")
    @Filters
    public  Map<String, Object> knowledgeDoc(String type){
        Map<String,Object> resultMap = new HashMap<>();
        Cbase016VO cbase016VO=cbase016MO.fetchByName(type);
        resultMap.put("c16",cbase016VO);
        return resultMap;
    }
    /**
     * Show doc string.
     *
     * @param doid the note string
     * @return the string
     * @description 展示文档入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:22 11:09:36.
     */
    @At
    @Filters
    @Ok("jsp:jsp.doc.showDoc")
    public Map<String, Object> showDoc(@Param("doid")Long doid){
        Tbuss009VO tbuss009VO = tbuss009MO.fetchByID(doid);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("note",FileUtil.convertClob(tbuss009VO.getNote()));
        resultMap.put("doc",tbuss009VO);
        return resultMap;
    }

    /**
     * Show doc string.
     *
     * @param doid the note string
     * @return the string
     * @description 展示文档入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:22 11:09:36.
     */
    @At
    @Filters
    @Ok("jsp:jsp.doc.showDoc1")
    public Map<String, Object> showDoc1(@Param("doid")Long doid){
        Tbuss009VO tbuss009VO = tbuss009MO.fetchByID(doid);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("note",FileUtil.convertClob(tbuss009VO.getNote()));
        resultMap.put("doc",tbuss009VO);
        return resultMap;
    }

    /**
     * User week news map.
     *
     * @return the map
     * @description 文档提交
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 09:09:10.
     */
    /**
     * User week news map.
     *
     * @return the map
     * @description 文档提交
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 09:09:10.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertDoc(@Param("note")String note,@Param("doid")Long doid,@Param("tilt")String tilt,@Param("csid")String csid,@Param("ctyp")String ctyp,@Param("grop")String grop,@Param("week")String week,@Param("sdat")String sdat, HttpServletRequest request){

        Integer code = 0;
        String msg = "";
        String usid = request.getSession().getAttribute("usid").toString();
        if(StringUtil.checkString(note,tilt,csid) && doid != null){
            Tbuss009VO tbuss009VO = new Tbuss009VO();
            tbuss009VO.setDoid(doid);
            tbuss009VO.setNote(FileUtil.formatClobByString(note));
            tbuss009VO.setCdat(new Date());
            tbuss009VO.setUsid(usid);
            tbuss009VO.setCsid(csid);
            tbuss009VO.setSdat(sdat);
            tbuss009VO.setWeek(week);
            tbuss009VO.setGrop(grop);
            if(StringUtil.checkString(ctyp)){
                tbuss009VO.setCtyp(ctyp);
            }else {
                tbuss009VO.setCtyp(1 + "");
            }
            tbuss009VO.setStat(5);
            tbuss009VO.setSta2(0);
            tbuss009VO.setTilt(tilt);
            tbuss009MO.insert(tbuss009VO);
            code = 1;
        }else{
            msg = "请求参数为空！";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update doc map.
     *
     * @param tbuss009VO 文档实体VO
     * @param edit       文档的内容
     * @param date       文档的创建日期
     * @return the map
     * @description 修改文档.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 11:11:55.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateDoc(@Param("..")Tbuss009VO tbuss009VO,@Param("edit")String edit,@Param("date")String date){
        Integer code = 0;
        String msg = "";
        if(tbuss009VO.getDoid() != null && StringUtil.checkString(edit) && StringUtil.checkString(date)){
            tbuss009VO.setNote(FileUtil.formatClobByString(edit));
            tbuss009VO.setCdat(DateUtil.formatDate(date));
            code = tbuss009MO.updateByVO(tbuss009VO);
        }else{
            msg = "请按正常步骤进入";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Query all message map.
     *
     * @param ctyp    \文档类型
     * @param session the session
     * @return the map
     * @description 查询文档类（公开信息、智库、学习文档等类型）
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 11:11:13.
     */
    @At
    @Filters
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> queryAllMessage(@Param("ctyp")Integer ctyp,HttpSession session){
        String usid = StringUtil.getUsid(session);
        SqlExpressionGroup e1 = Cnd.exps("sta2","=",0);
        SqlExpressionGroup e2 = null;
        SqlExpressionGroup e3 = null;

        if(usid != null) {
            e3 = Cnd.exps("sta2", "=", 1).and("usid", "=", usid);
        }

        SqlExpressionGroup e4 = Cnd.exps(e1).or(e3);

        if(ctyp != null){
            e2 = Cnd.exps("ctyp","=",ctyp);
        }

        List<Tbuss009VO> tbuss009VOList = tbuss009MO.queryAllDocNormal(Cnd.where(e2).and(e4).desc("cdat"),null);
        return TableUtil.makeJson(0,"成功",null,tbuss009VOList);
    }

    /**
     * Query all user week doc map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的尺寸
     * @param key        过滤关键词
     * @param session    the session
     * @return the map
     * @description 查询用户的所有周报
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 11:11:09.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> queryAllUserWeekDoc(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,
                              @Param("key")String key,HttpSession session){

        String usid = StringUtil.getUsid(session);
        SqlExpressionGroup e0 = Cnd.exps("ctyp","=",1);
        SqlExpressionGroup e1 = Cnd.exps("usid","=",usid);
        SqlExpressionGroup e2 = null;
        if(StringUtil.checkString(key)){
            e2 = Cnd.exps("doid","like","%"+key+"%").or("tilt","like","%"+key+"%");
        }
        Integer count = tbuss009MO.countByCnd(Cnd.where(e0).and(e1).and(e2));
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        List<Tbuss009VO> tbuss009VOList = tbuss009MO.queryAllDocNormal(Cnd.where(e0).and(e1).and(e2).desc("cdat"),pager);
        return TableUtil.makeJson(0,"成功",count,tbuss009VOList);
    }

    /**
     * Query all doc map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的尺寸
     * @param key        过滤关键词
     * @param session    the session
     * @return the map
     * @description 查询所有的文档.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 11:11:04.
     */
    @At
    @Filters
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> queryAllDoc(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,
                                          @Param("key")String key,@Param("ctyp")Integer ctyp,@Param("week")String week,@Param("sdat")String sdat,@Param("grop")String grop,HttpSession session){
        System.out.println("ctyp"+ctyp);
        String usid = StringUtil.getUsid(session);
        Integer sta2 = cbase000MO.fetchByUsid(usid).getSTA2();
        String stage = "";
        List<Tbuss009VO> tbuss009VOS;
        Pager pager;
        Integer count;
        SqlExpressionGroup e = Cnd.exps("stat","=",0).and("usid","=",usid);
        SqlExpressionGroup e0 = Cnd.exps("sta2","=",0);
        SqlExpressionGroup e1 = Cnd.exps("sta2","=",1).and("usid","=",usid);
        SqlExpressionGroup e2 = Cnd.exps(e0).or(e1);
        SqlExpressionGroup e3 = Cnd.exps("stat","=",5).or(e);
        SqlExpressionGroup e4 = null;
        SqlExpressionGroup e5 = null;
        SqlExpressionGroup e6 = null;
        SqlExpressionGroup e7 = null;
        SqlExpressionGroup e8 = null;
        SqlExpressionGroup e9 = null;

        if(StringUtil.checkString(key)){
            e5 = Cnd.exps("doid","like","%"+key+"%").or("tilt","like","%"+key+"%")
                    .or("unam","like","%"+key+"%");
        }
        if(ctyp!=null){
            System.out.println("类型不为空"+ctyp);
            e6 = Cnd.exps("ctyp","=",ctyp);
        }
        if(StringUtil.checkString(week)){
            System.out.println("周数不为空"+week);
            e7 = Cnd.exps("week","=",week);
        }
        if(StringUtil.checkString(sdat)){
            System.out.println("日期不为空"+sdat);
            e8 = Cnd.exps("sdat","=",sdat);
        }
        if(StringUtil.checkString(grop)){
            System.out.println("团队不为空"+grop);
            e9 = Cnd.exps("grop","=",grop);
        }


        if(sta2 == 0){
            e4 = Cnd.exps("stat","=",0).or(e3);
            count = tbuss009MO.countByCnd(Cnd.where(e4).and(e5).and(e6).and("usid","=",usid).and(e7).and(e8).and(e9));
            pager = TableUtil.formatPager(pageSize,pageNumber,count);
            tbuss009VOS = tbuss009MO.queryAllDocNormal(Cnd.where(e4).and(e5).and(e6).and("usid","=",usid).and(e7).and(e8).and(e9),pager);
        }else {
            if (sta2 == 1) {
                e4 = Cnd.exps(e3).or("stat", "=", 1);
                stage = "GROP";}
            else if (sta2 == 2) {
                e4 = Cnd.exps(e3).or("stat", "=", 2);
                stage = "ACCO";
            } else if (sta2 == 3) {
                e4 = Cnd.exps(e3).or("stat", "=", 3);
                stage = "DEPT";
            } else if (sta2 == 4) {
                e4 = Cnd.exps(e3).or("stat", "=", 4);
                stage = "COMP";
            }
            count = tbuss009MO.countAllDoc(usid, Cnd.where("0", "=", 0).and(e5).and(e2).and(e4).and(e6).and(e7).and(e8).and(e9), stage);
            pager = TableUtil.formatPager(pageSize, pageNumber, count);
            tbuss009VOS = tbuss009MO.queryAllDoc(usid, Cnd.where("0", "=", 0).and(e5).and(e2).and(e4).and(e6).and(e7).and(e8).and(e9), stage, pager);
        }
        return TableUtil.makeJson(0,"成功",count,tbuss009VOS);
    }

    /**
     * Delete doc map.
     *
     * @param doid         文档编号
     * @param doids 文档的集合
     * @return the map
     * @description 删除文档
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 01:09:12.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteDoc(@Param("doid")Long doid, @Param("::list")String[] doids){
        Integer code = 0;
        String msg = "";
        if(doid != null){
            tbuss015MO.deleteByDoid(doid);
            code = tbuss009MO.deleteByDoid(doid);
        }else if(doids!=null){
            for(String DOID:doids){
                Long id = Long.parseLong(DOID);
                tbuss015MO.deleteByDoid(id);
                code = tbuss009MO.deleteByDoid(id);
            }
        }else{
            msg = "请求参数为空！";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Upload files map.
     *
     * @param files    文件集合
     * @param doid     任务ID
     * @param response the response
     * @param request  the request
     * @return the map
     * @description 任务上传附件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 01:09:25.
     */
    @At
    @POST
    @Ok("json")
    @AdaptBy(type = UploadAdaptor.class)
    public Map<String,Object> uploadFiles(@Param("file")TempFile[] files, @Param("doid")Long doid, HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Content-Type","text/html");
        String usid = request.getSession().getAttribute("usid").toString();
        Integer code = 0;
        for (TempFile file : files) {
            Map<String,Object> map = FileUtil.upload(file);
            Tbuss015VO tbuss015VO = new Tbuss015VO("ff"+FileUtil.getFileName(map.get("duta").toString()), map.get("title").toString(),doid,
                     Integer.parseInt(map.get("fileSize").toString()), usid,new Date());
            tbuss015MO.insert(tbuss015VO);
            code = 1;
        }
        return ResultUtil.getResult(code,null,null);
    }

    /**
     * Query all file map.
     *
     * @param doid 文档ID
     * @return 标准的数据请求返回模式
     * @description 根据文档id查询他关联的所有文件.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 04:11:07.
     */
    @At
    @Ok("json")
    @Filters
    public Map<String,Object> queryAllFile(@Param("doid")Long doid){
        List<Tbuss015VO> tbuss015VOList = new ArrayList<>();
         Tbuss009VO tbuss009VO = tbuss009MO.fetchTransByIDPrimary(doid,"tbuss015VOS",null);
        if(tbuss009VO != null){
            tbuss015VOList = tbuss009VO.getTbuss015VOS();
        }
        return ResultUtil.getResult(0,"成功！",tbuss015VOList);
    }

    /**
     * Delete file map.
     *
     * @param duta     文档流水号
     * @param fileName 文档的文件名
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:06 04:11:36.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteFile(@Param("duta")String duta,@Param("ffil")String fileName){
        Integer code = 0;
        String msg = "删除成功！";
        if(StringUtil.checkString(duta) && StringUtil.checkString(fileName)){
            code = tbuss015MO.deleteByDuta(duta,fileName);
        }else{
            msg = "请从正常步骤进入！";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Share doc map.
     *
     * @param doid 文档ID
     * @return the map
     * @description 分享文档.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 10:10:32.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> shareDoc(@Param("doid")Long doid, @Param("csid")String csid,@Param("tilt")String tilt,HttpSession session){
        Integer code = 0;
        String msg = "分享成功！";
        String usid = session.getAttribute("usid").toString();
        if(StringUtil.checkString(csid) && StringUtil.checkString(tilt) && doid != null) {
            if(csid.contains(",")){
                String[] csids = csid.split(",");
                for(String CSID:csids){
                    sendShareDocMail(CSID,doid,usid,tilt);
                }
            }else {
                sendShareDocMail(csid, doid, usid, tilt);
            }
            code = 1;
        }else{
            msg = "请求参数错误，请按正常途径访问！";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    @Async
    private void sendShareDocMail(String csid,Long doid,String usid,String tilt){
        if(csid.equals("1866")){
            Cbase018VO cbase018VO = cbase018MO.fetchTransByEMID(1866);
            List<Cbase000VO> cbase000VOList = cbase018VO.getCbase000VOS();
            for(Cbase000VO cbase000VO:cbase000VOList){
                MailUtil.sendmail(cbase000VO.getUSID(),doid,usid,tilt);
            }
        }else {
            MailUtil.sendmail(csid.trim(), doid, usid, tilt);
        }
    }
}


