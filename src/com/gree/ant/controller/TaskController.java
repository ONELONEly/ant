package com.gree.ant.controller;

import com.gree.ant.mo.*;
import com.gree.ant.util.*;
import com.gree.ant.util.excel.TaskExcel;
import com.gree.ant.vo.*;
import jxl.write.WriteException;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * The type Task controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务操作的Controller
 * @title TaskController
 * @createTime 2017 :09:06 06:09:45.
 */
@At("/task")
@IocBean
public class TaskController {

    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject("refer:cbase015MO")
    private Cbase015MO cbase015MO;

    @Inject("refer:cbase011MO")
    private Cbase011MO cbase011MO;

    @Inject("refer:cbase012MO")
    private Cbase012MO cbase012MO;

    @Inject("refer:tbuss004MO")
    private Tbuss004MO tbuss004MO;

    @Inject("refer:tbuss010MO")
    private Tbuss010MO tbuss010MO;

    /**
     * Insert string.
     *
     * @return the string
     * @description 任务插入界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:26.
     */
    @At
    @Ok("jsp:jsp.task.insert")
    public String insert(){
        return "JK"+FileUtil.getRandomName();
    }

    /**
     * Manage string.
     *
     * @return the string
     * @description 任务管理界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:41.
     */
    @At
    @Ok("jsp:jsp.task.manage")
    public String manage(){
        return "success!";
    }

    /**
     * Edit string.
     *
     * @param taid the taid
     * @return the string
     * @description 修改任务的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 02:09:23.
     */
    @At
    @Ok("jsp:jsp.task.edit")
    public Map<String,Object> edit(@Param("taid")String taid,@Param("state")String state){
        Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(taid);
        Boolean key = StringUtil.checkString(state);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("task",tbuss003VO);
        resultMap.put("note",FileUtil.convertClob(tbuss003VO.getNote()));
        resultMap.put("key",key);
        return resultMap;
    }

    /**
     * Show task map.
     *
     * @param taid 任务ID
     * @return the map
     * @description 任务详情入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 10:10:46.
     */
    @At
    @Ok("jsp:jsp.task.showTask")
    public Map<String, Object> showTask(@Param("taid")String taid){
        Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(taid);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("note",FileUtil.convertClob(tbuss003VO.getNote()));
        resultMap.put("task",tbuss003VO);
        return resultMap;
    }

    /**
     * Task file string.
     *
     * @param taid 任务ID
     * @return the string
     * @description 任务附件入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 10:10:16.
     */
    @At
    @Ok("jsp:jsp.task.taskFile")
    public String taskFile(@Param("taid")String taid){
        return taid;
    }

    /**
     * Rule string.
     *
     * @return the string
     * @description 任务规则入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:35.
     */
    @At
    @Ok("jsp:jsp.task.rule")
    public String rule(){
        return "success!";
    }

    /**
     * Rule score string.
     *
     * @param pjno the pjno
     * @return the string
     * @description 规则分值入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 09:09:23.
     */
    @At
    @Ok("jsp:jsp.task.ruleScore")
    public String ruleScore(@Param("pjno")String pjno){
        return pjno;
    }

    /**
     * Insert rule string.
     *
     * @return the string
     * @description 添加规则入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 04:09:15.
     */
    @At
    @Ok("jsp:jsp.task.insertRule")
    public String insertRule(){
        return "success!";
    }


    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd HH:mm:ss'}")
    public Map<String,Object> queryAllTask(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("pdat")String pdat,@Param("key")String key,@Param("grop")String grop,@Param("csid")String csid){
        Condition cnd0 = composeCnd(csid,key,null,0,null,false);
        Condition cnd1;
        SqlExpressionGroup e0 = null;
        SqlExpressionGroup e1 = null;
        if(pdat != null){
            e1 = Cnd.exps("pdat","like","%"+pdat+"%");
        }
        if(grop != null){
            e0 = Cnd.exps("grop","like","%"+grop+"%");
        }
        cnd1 = Cnd.where(e0).and(e1);
        Integer count = tbuss003MO.countAllTask(cnd0,cnd1);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"成功",count,tbuss003MO.queryAllTask(cnd0,cnd1,pager));
    }

    /**
     * Query all task map.
     *
     * @param pageNumber 当前查询页
     * @param pageSize   页的大小
     * @param ptno       绩效表编号
     * @param key        过滤字段
     * @param sta        the sta
     * @return the map
     * @description 查询所有任务信息 ，以表格json数据形式返回['task.manage','board.manage']
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:52.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd HH:mm:ss'}")
    public Map<String,Object> queryAllGropTask(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("ptno")String ptno,@Param("key")String key,@Param("sta")Integer sta,HttpSession session){
        String usid = StringUtil.getUsid(session);
        Condition cnd = composeCnd(null,key,sta,null,ptno,false);
        Integer count = tbuss003MO.countGropTask(usid,cnd);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"成功",count,tbuss003MO.queryGropAllTask(usid,pager,cnd));
    }

    /**
     * Query all task map.
     *
     * @param pageNumber 当前查询页
     * @param pageSize   页的大小
     * @param ptno       绩效表编号
     * @param key        过滤字段
     * @param sta        the sta
     * @return the map
     * @description 绩效表任务的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 05:10:56.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd HH:mm:ss'}")
    public Map<String,Object> queryAllGradeTask(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize, @Param("ptno")String ptno, @Param("key")String key, @Param("sta")Integer sta, HttpSession session){
        String usid = session.getAttribute("usid").toString();
        Condition cnd = composeCnd(null,key,sta,null,ptno,true);
        Integer count = tbuss003MO.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"成功",count,tbuss003MO.queryAllGradeTask(usid,pager,cnd));
    }

    /**
     * Query all task map.
     *
     * @param pageNumber 当前查询页
     * @param pageSize   页的大小
     * @param key        过滤字段
     * @param sta        the sta
     * @param ksid       the ksid
     * @param request    the request
     * @return the map
     * @description 查询所有与当前用户有关的任务信息 ，以表格json数据形式返回
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:52.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd HH:mm:ss'}")
    public Map<String,Object> userQueryAllTask(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String key,@Param("ptno")String ptno,@Param("sta")Integer sta,@Param("ksid")String ksid,HttpServletRequest request){
        Integer type = 0;
        if (ksid != null) {
            if("ksid".equals(ksid)) {
                type = 1;
            }else if("usid".equals(ksid)){
                type = 2;
            }
        }
        String usid = request.getSession().getAttribute("usid").toString();
        Condition cnd = composeCnd(usid,key,sta,type,ptno,true);
        Integer count = tbuss003MO.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"成功",count,tbuss003MO.queryAllByCnd(cnd,pager));
    }


    /**
     * Insert image map.
     *
     * @param file     the file
     * @param response the response
     * @return the map
     * @description layedit插入图片 ，以其所需格式返回(此处0-成功，1-失败)
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:44.
     */
    @At
    @POST
    @Ok("json")
    @AdaptBy(type = UploadAdaptor.class)
    public Map<String,Object> insertImage(@Param("file")TempFile file, HttpServletResponse response){
        response.setHeader("Content-Type","text/html");
        return ResultUtil.getResult(0,"上传成功",FileUtil.upload(file));
    }

    /**
     * Insert task map.
     *
     * @param tbuss003VO the tbuss 003 vo
     * @param edit       the edit
     * @param request    the request
     * @return the map
     * @description 插入任务.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:40.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertTask(@Param("..")Tbuss003VO tbuss003VO,@Param("edit") String edit,HttpServletRequest request){
        String usid = request.getSession().getAttribute("usid").toString();
        String msg = "任务添加失败";
        Integer code = 0;
        if(tbuss003VO!=null && StringUtil.checkString(edit)){
            Clob note = FileUtil.formatClobByString(edit);
            tbuss003VO.setNote(note);
            tbuss003VO.setCdat(new Date());
            tbuss003VO.setUsid(usid);
            tbuss003VO.setSta1(0);
            tbuss003VO.setStag(0);
            tbuss003VO.setFahh(0f);
            tbuss003MO.insertByVO(tbuss003VO);
            msg = "任务添加成功！";
            code = 1;
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update task map.
     *
     * @param tbuss003VO the tbuss 003 vo
     * @param edit       the edit
     * @param request    the request
     * @return the map
     * @description 修改任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 03:09:20.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateTask(@Param("..")Tbuss003VO tbuss003VO,@Param("edit") String edit,HttpServletRequest request){
        String msg = "修改任务失败";
        Integer code = 0;
        if(tbuss003VO.getTaid()!=null && edit !=null){
            Tbuss003VO tbuss003VOOld = tbuss003MO.fetchByTaid(tbuss003VO.getTaid());
            Clob note = FileUtil.formatClobByString(edit);
            tbuss003VO.setNote(note);
            tbuss003VO.setCdat(tbuss003VOOld.getCdat());
            tbuss003VO.setSta1(tbuss003VOOld.getSta1());
            tbuss003VO.setTadd(tbuss003VOOld.getTadd());
            tbuss003VO.setPdat(tbuss003VOOld.getPdat());
            tbuss003VO.setXdat(tbuss003VOOld.getXdat());
            tbuss003VO.setFdat(tbuss003VOOld.getFdat());
            tbuss003VO.setTdat(tbuss003VOOld.getTdat());
            tbuss003VO.setAdat(tbuss003VOOld.getAdat());
            tbuss003VO.setStag(tbuss003VOOld.getStag());
            tbuss003VO.setFahh(tbuss003VOOld.getFahh());
            tbuss003MO.updateByVO(tbuss003VO);
            msg = "修改任务成功";
            code = 1;
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Insert rule map.
     *
     * @param cbase011VO the cbase 011 vo
     * @param request    the request
     * @return the map
     * @description 插入一条规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:35.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertRule(@Param("..")Cbase011VO cbase011VO,HttpServletRequest request){
        String usid = request.getSession().getAttribute("usid").toString();
        String msg = "请填入所有内容";
        Integer code = 0;
        if(cbase011VO.getCons() != null && cbase011VO.getPjjp()!=null && cbase011VO.getDeti() != null && cbase011VO.getPlsu() !=null && cbase011VO.getDsca() != null){
            cbase011VO.setPjno("P"+FileUtil.getRandomName());
            cbase011VO.setUsid(usid);
            cbase011VO.setUdat(new Date());
            cbase011MO.insert(cbase011VO);
            msg = "插入规则成功";
            code = 1;
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Insert rule score map.
     *
     * @param cbase012VO the cbase 012 vo
     * @return the map
     * @description 插入规则分值.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 09:09:54.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertRuleScore(@Param("..")Cbase012VO cbase012VO){
        String msg = "分值不能为空";
        Integer code = 0;
        if(cbase012VO.getOpco() != null && cbase012VO.getPjno() != null){
            String pjno = cbase012VO.getPjno();
            String opco = cbase012VO.getOpco();
            String type = opco.substring(0,1);
            Integer num = opco.indexOf(".");
            if("-".equals(type) || "+".equals(type) && num!=-1) {
                if(cbase012MO.insertCheck(pjno,opco)) {
                    cbase012MO.insert(cbase012VO);
                    msg = "插入成功";
                    code = 1;
                }else{
                    msg = "当前分值已存在！";
                }
            }else{
                msg = "请在分值前加上+,或者-.并且加点，如+1.0";
            }
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Upload files map.
     *
     * @param files    文件集合
     * @param taid     任务ID
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
    public Map<String,Object> uploadFiles(@Param("file")TempFile[] files, @Param("taid")String taid, HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Content-Type","text/html");
        String usid = request.getSession().getAttribute("usid").toString();
        Integer code = 0;
        for (TempFile file : files) {
            Map<String,Object> map = FileUtil.upload(file);
            Cbase015VO cbase015VO = new Cbase015VO("ff"+FileUtil.getFileName(map.get("duta").toString()), taid,
                    map.get("title").toString(), Integer.parseInt(map.get("fileSize").toString()), usid,new Date());
            cbase015MO.insert(cbase015VO);
            code = 1;
        }
        return ResultUtil.getResult(code,null,null);
    }

    /**
     * Download file.
     *
     * @param duta     文件流水号
     * @param ffil     文件名称
     * @param response the response
     * @description 下载文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 03:10:29.
     */
    @At
    @Filters
    @Ok("void")
    public void downloadFile(@Param("duta")String duta,@Param("ffil")String ffil,HttpServletResponse response){
        FileUtil.download(response,duta,ffil);
    }

    /**
     * Query all file map.
     *
     * @param taid 任务编号
     * @return 标准的Table表格请求的输出
     * @description 通过Taid查询所拥有的文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 09:10:21.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllFile(@Param("taid")String taid){
        List<Cbase015VO> cbase015VOList = new ArrayList<>();
        Tbuss003VO tbuss003VO = tbuss003MO.fetchTrans(taid,"cbase015VOS",null);
        if(tbuss003VO != null){
            cbase015VOList = tbuss003VO.getCbase015VOS();
        }
        return ResultUtil.getResult(0,"成功！",cbase015VOList);
    }

    /**
     * Delete file map.
     *
     * @param duta 流水号
     * @param ffil 文件名称
     * @return the map
     * @description 删除文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 11:10:53.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteFile(@Param("duta")String duta,@Param("ffil")String ffil){
        Integer code = 0;
        String msg = "删除成功！";
        if(StringUtil.checkString(duta)){
            code = cbase015MO.deleteByName(duta,ffil);
        }else{
            msg = "请从正常步骤进入！";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete rule map.
     *
     * @param pjno        绩效评估编号
     * @param pjnos       绩效评估编号集合
     * @return the map
     * @description 删除规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:25.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRule(@Param("pjno")String pjno, @Param("::list")String[] pjnos){
        String msg;
        Integer code = 0;
        if(pjno!=null){
            code = cbase011MO.deleteByPjno(pjno);
        }else if(pjnos != null){
            for (String PJNO:pjnos) {
                code = cbase011MO.deleteByPjno(PJNO);
            }
        }
        msg = code == 1 ? "删除成功" : "删除失败";
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete rule score map.
     *
     * @param cbase012VO  the cbase 012 vo
     * @param cbase012VOS the cbase 012 vos
     * @return the map
     * @description 删除规则分值
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 10:09:54.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRuleScore(@Param("..")Cbase012VO cbase012VO, @Param("::list")List<Map<String,Object>> cbase012VOS){
        String msg;
        Integer code = 0;
        if(cbase012VO.getPjno()!=null&&cbase012VO.getOpco()!=null){
            code = cbase012MO.deleteByVO(cbase012VO);
        }else if(cbase012VOS != null){
            for (Map c12 : cbase012VOS) {
                cbase012VO = new Cbase012VO();
                cbase012VO.setPjno(c12.get("pjno").toString());
                cbase012VO.setOpco(c12.get("opco").toString());
                code = cbase012MO.deleteByVO(cbase012VO);
            }
        }
        msg = code == 1 ? "删除成功" : "删除失败";
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete task map.
     *
     * @param taid  任务编号
     * @param taids 任务编号集合
     * @return the map
     * @description 通过任务编号删除任务.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 01:09:25.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteTask(@Param("taid")String taid, @Param("::list")String[] taids){
        String msg ;
        Integer code = 0;
        if(StringUtil.checkString(taid)){
            code = tbuss003MO.deleteByTaid(taid);
        }else if(taids != null){
            for (String TAID:taids) {
                code = tbuss003MO.deleteByTaid(TAID);
            }
        }
        msg = code == 1 ? "删除成功" : "删除失败";
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Copy task map.
     *
     * @param taids 任务编号集合
     * @return the map
     * @description 复制选中的任务。（是否添加复制文件待开发）
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 01:09:25.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> copyTask(@Param("::list")String[] taids){
        String msg ;
        Integer code = 0;
        if(taids != null){
            for (String taid:taids) {
               Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(taid);
               tbuss003VO.setTaid("JK"+FileUtil.getRandomName());
               tbuss003VO.setCdat(new Date());
               tbuss003VO.setSta1(0);
               tbuss003VO.setStag(0);
               tbuss003VO.setFahh(0f);
               tbuss003VO.setTadd(null);
               tbuss003VO.setXdat(null);
               tbuss003VO.setFdat(null);
               tbuss003VO.setTdat(null);
               tbuss003VO.setAdat(null);
               tbuss003VO.setPdat(null);
               tbuss003MO.insertByVO(tbuss003VO);
               code = 1;
            }
        }
        msg = code == 1 ? "复制成功" : "复制失败";
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update sta 1 map.
     *
     * @param operate 执行的操作
     * @param taids 任务编号集合
     * @param remk  执行操作备注
     * @return the map
     * @description 修改任务的状态
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 01:09:25.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateSta1(@Param("operate")Integer operate,@Param("stag")Integer stag,
                                         @Param("::list")String[] taids,@Param("remk")String remk,
                                         @Param("date")String date,@Param("fahh")Float fahh, AdaptorErrorContext error){
        StringBuilder msg = new StringBuilder("传入参数为空");
        Integer code = 0;
        String status = "";
        if(error == null) {
            if (operate != null && taids != null && StringUtil.checkString(remk)) {
                for (String TAID : taids) {
                    Tbuss003VO tbuss003VO = tbuss003MO.fetchTrans(TAID, "tbuss010VOS", null);
                    List<Tbuss010VO> tbuss010VOList = tbuss003VO.getTbuss010VOS();
                    String csid = tbuss003VO.getCsid();
                    String cnam = tbuss003VO.getCnam();
                    String ksid = tbuss003VO.getKsid();
                    String knam = tbuss003VO.getKnam();
                    String taid = tbuss003VO.getTaid();
                    String titl = tbuss003VO.getTitl();
                    String sys = tbuss003VO.getSynonam();
                    tbuss003VO.setSta1(operate);
                    if (operate == 1) {
                        MailUtil.sendmail(csid, "牛逼的" + cnam + ",大Boss又给你下发新任务了那，请尽快查看！否则小蚂怕你忘了呐！", taid, titl, sys);
                        MailUtil.sendmail(ksid, "尊敬的" + knam + "，您的任务已下发，小蚂已极力催促了！请耐心等待！", taid, titl, sys);
                        status = "下发";
                        tbuss003VO.setXdat(new Date());
                    } else if (operate == 2) {
                        MailUtil.sendmail(ksid, "尊敬的" + knam + "，您的任务已在努力开发中！计划完成时间：" + DateUtil.formatYMDHMDDate(date), taid, titl, sys);
                        status = "执行中";
                        if (StringUtil.checkString(date)) {
                            tbuss003VO.setAdat(new Date());
                            tbuss003VO.setPdat(DateUtil.formatYMDHMDDate(date));
                        }
                        if (fahh != null) {
                            tbuss003VO.setFahh(fahh);
                        }
                    } else if (operate == 3) {
                        status = "被驳回";
                    } else if (operate == 4) {
                        status = "变更中";
                    } else if (operate == 6) {
                        MailUtil.sendmail(tbuss003VO.getRsid(), "尊敬的" + tbuss003VO.getRnam() + "，您的任务已竣工！请验收！", taid, titl, sys);
                        MailUtil.sendmail(ksid, "尊敬的" + knam + "，您的任务已竣工！请验收！", taid, titl, sys);
                        status = "验收中";
                        tbuss003VO.setFdat(new Date());
                    } else if (operate == 5) {
                        MailUtil.sendmail(tbuss003VO.getTepr(), "亲爱的" + tbuss003VO.getTnam() + "，你知道么，小蚂想告诉你，你又有新的测试任务了！请尽兴的测试吧！", taid, titl, sys);
                        MailUtil.sendmail(ksid, "尊敬的" + knam + "，您的任务已竣工！请测试！", taid, titl, sys);
                        status = "测试中";
                        tbuss003VO.setTdat(new Date());
                    } else if (operate == 8) {
                        status = "测试不通过";
                    } else if (operate == 10) {
                        status = "验收不通过";
                    } else if (operate == 11) {
                        if (stag != null) {
                            tbuss003VO.setStag(stag);
                        } else {
                            return ResultUtil.getResult(0, "等级为空，请重新录入", null);
                        }
                    }
                    sendMail(tbuss010VOList, status, titl);
                    code = tbuss003MO.updateByVO(tbuss003VO);
                    if (code == 1) {
                        tbuss004MO.insert(new Tbuss004VO(TAID, operate, new Date(), remk));
                    }
                }
                msg = new StringBuilder(code == 1 ? "任务操作成功" : msg);
            }
        }else{
            msg = new StringBuilder("请输入正确的工时！如（5.0）");
        }
        return ResultUtil.getResult(code, msg.toString(),null);
    }

    /**
     * Query all rule map.
     *
     * @param pageNumber the 当前页数
     * @param pageSize   the 页的大小
     * @param key        the key
     * @return the map
     * @description 请求查询所有任务规则字段
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:20.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllRule(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String key){
        Condition cnd = null;
        if(key!=null){
            cnd = Cnd.where("pjno","like","%"+key+"%");
        }
        Integer count = cbase011MO.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"成功",count,cbase011MO.queryAllByCnd(cnd,pager));
    }

    /**
     * Update rule map.
     *
     * @param cbase011VO the cbase 011 vo
     * @return the map
     * @description 修改规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 02:10:26.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateRule(@Param("..")Cbase011VO cbase011VO){
        Integer code = 0;
        String msg = "请求错误";
        if(StringUtil.checkString(cbase011VO.getPjno())) {
            code = cbase011MO.updateByVO(cbase011VO);
        }
        msg = code == 1?"修改规则成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Query all rule score map.
     *
     * @param pjno the pjno
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 01:09:25.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllRuleScore(@Param("pjno")String pjno){
        Integer code = 1;
        String msg = "查询失败，当前请求未正常调用！";
        Cbase011VO cbase011VO = new Cbase011VO();
        if(pjno!=null){
            cbase011VO = cbase011MO.fetchTransByPjno(pjno);
            code = 0;
            msg = "查询成功！";
        }
        return TableUtil.makeJson(code,msg,null,cbase011VO.getCbase012VOS());
    }

    /**
     * Eye in map.
     *
     * @param taid    任务编号
     * @param session the session
     * @return the map
     * @description 关注某个任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 04:10:18.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> eyeIn(@Param("taid")String taid, HttpSession session){
        Integer code = 0;
        String msg = "关注成功！";
        String usid = session.getAttribute("usid").toString();
        if(StringUtil.checkString("taid")) {
            if (tbuss010MO.queryByCnd(Cnd.where("taid", "=", taid).and("usid", "=", usid))) {
                Tbuss010VO tbuss010VO = tbuss010MO.insert(new Tbuss010VO(usid, taid));
                if(tbuss010VO!=null) {
                    code = 1;
                }else{
                    msg = "关注失败！";
                }
            }else{
                msg = "您好！您已经关注了！";
            }
        }else{
            msg = "请按照正常步骤调用！";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Eye out map.
     *
     * @param taid    任务编号
     * @param session the session
     * @return the map
     * @description 取消关注某个任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 04:10:41.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> eyeOut(@Param("taid")String taid, HttpSession session){
        Integer code = 0;
        String msg = "取消失败";
        String usid = session.getAttribute("usid").toString();
        if(StringUtil.checkString(taid)){
            if(!tbuss010MO.queryByCnd(Cnd.where("taid", "=", taid).and("usid", "=", usid))) {
                code = tbuss010MO.delete(new Tbuss010VO(usid, taid));
            }else{
                msg = "您没有关注此任务！";
            }
        }else{
            msg = "请按照正常步骤调用！";
        }
        msg = code == 1?"成功取消":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Print task.
     *
     * @param request  the request
     * @param response the response
     * @description 打印任务成Excel
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:23 08:09:21.
     */
    @At
    @POST
    @Ok("void")
    public void printTask(HttpServletRequest request,HttpServletResponse response,@Param("::list")String[] taids){
        List<Tbuss003VO> tbuss003VOS = new ArrayList<>();
        if(taids.length != 0) {
            for (String taid : taids) {
                Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(taid);
                tbuss003VOS.add(tbuss003VO);
            }
        }
        try {
            TaskExcel.export(tbuss003VOS,request,response);
        } catch (ServletException | IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print task.
     *
     * @description 通过条件打印任务Excel
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 11:10:30.
     */
    @At
    @POST
    @Ok("void")
    public void printCndTask(@Param("pdat")String pdat,@Param("key")String key,@Param("grop")String grop,@Param("csid")String csid,
                          HttpServletRequest request,HttpServletResponse response){
        Condition cnd0 = composeCnd(csid,key,null,0,null,false);
        Condition cnd1;
        SqlExpressionGroup e0 = null;
        SqlExpressionGroup e1 = null;
        if(pdat != null){
            e1 = Cnd.exps("pdat","like","%"+pdat+"%");
        }
        if(grop != null){
            e0 = Cnd.exps("grop","like","%"+grop+"%");
        }
        cnd1 = Cnd.where(e0).and(e1);
        List<Tbuss003VO> tbuss003VOS = tbuss003MO.queryAllTaskPrint(cnd0,cnd1,null);
        try {
            TaskExcel.export(tbuss003VOS,request,response);
        } catch (ServletException | IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compose cnd condition.
     *
     * @param usid  用户ID
     * @param key   过滤关键字段
     * @param sta   前端进行操作的类型
     * @param type  判断查询类型
     * @param ptno  绩效表编号
     * @param order 是否进行降序排序
     * @return the condition
     * @description 组成任务的查询过滤语句
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:40.
     */
    private Condition composeCnd(String usid,String key,Integer sta,Integer type,String ptno,Boolean order){
        SqlExpressionGroup e0 = Cnd.exps("puno","!=","PU0007");
        SqlExpressionGroup e1 = null;
        SqlExpressionGroup e2 = null;
        SqlExpressionGroup e3 = null;
        SqlExpressionGroup e4 = null;
        if(key != null) {
            e1 = Cnd.exps("cnam","like","%" + key + "%").or("synonam","like","%" + key + "%")
                    .or("sta2nam","like","%" + key + "%").or("sta3nam","like","%" + key + "%")
                    .or("cnam","like","%"+key+"%").or("titl","like","%" + key + "%");
        }
        if(sta != null){
            if(sta == 0){
                e2 = Cnd.exps("sta1","=","0").or("sta1","=","3");
            }else if(sta == 1){
                e2 = Cnd.exps("sta1","=","1");
            }else if(sta == 2){
                e2 = Cnd.exps("sta1","=","2");
            }
            else if(sta == 3){
                e2 = Cnd.exps("sta1","=","4");
            }
            else if(sta == 4){
                e2 = Cnd.exps("sta1","=","8").or("sta1","=","10");
            }
            else if(sta == 5){
                e2 = Cnd.exps("sta1","=","5");
            }else if(sta == 6){
                e2 = Cnd.exps("sta1","=","6");
            }else if(sta == 7){
                e2 = Cnd.exps("sta1","=","11");
            }else if(sta == 8){
                e2 = Cnd.exps("sta1","<","12");
            }else if(sta == 9){
                e2 = Cnd.exps("sta1","!=","11");
            }
        }
        if(usid!=null){
            if(type == 0) {
                e3 = Cnd.exps("csid", "like", "%"+usid+"%");
            }else if (type == 1){
                e3 =  Cnd.exps("ksid", "like", "%"+usid+"%");
            }else if(type == 2){
                e3 = Cnd.exps("usid","like","%"+usid+"%");
            }
        }
        if(ptno != null){
            e4 = Cnd.exps("ptno","like","%"+ptno+"%");
        }
        if(order) {
            return Cnd.where(e0).and(e1).and(e2).and(e3).and(e4).desc("cdat");
        }else{
            return Cnd.where(e0).and(e1).and(e2).and(e3).and(e4);
        }
    }

    private void sendMail(List<Tbuss010VO> tbuss010VOList,String status,String titl){
        if(tbuss010VOList != null){
            for(Tbuss010VO tbuss010VO:tbuss010VOList){
                MailUtil.sendmail(tbuss010VO.getUsid(),tbuss010VO.getTaid(),titl,status);
            }
        }
    }

}


