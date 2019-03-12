package com.gree.ant.controller;


import com.gree.ant.mo.*;
import com.gree.ant.util.*;
import com.gree.ant.util.excel.GradeExcel;
import com.gree.ant.vo.*;
import com.gree.ant.vo.response.GropUser;
import com.gree.ant.vo.response.UserEva;
import com.gree.ant.vo.util.*;
import jxl.write.WriteException;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ViewModel;
import org.nutz.mvc.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * The type Grade controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 绩效的Controller
 * @title GradeController
 * @createTime 2017 :09:06 06:09:56.
 */
@At("/grade")
@IocBean
public class GradeController {

    private Logger logger = LoggerFactory.getLogger(GradeController.class);

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    @Inject("refer:tbuss001MO")
    private Tbuss001MO tbuss001MO;

    @Inject("refer:cbase009MO")
    private Cbase009MO cbase009MO;

    @Inject("refer:cbase010MO")
    private Cbase010MO cbase010MO;

    @Inject("refer:cbase011MO")
    private Cbase011MO cbase011MO;

    @Inject("refer:tbuss002MO")
    private Tbuss002MO tbuss002MO;

    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject("refer:tbuss005MO")
    private Tbuss005MO tbuss005MO;

    @Inject
    private BussMoFactory bussMoFactory;

    /**
     * Index string.
     *
     * @return the string
     * @description 绩效页面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:02.
     */
    @At
    @Ok("jsp:jsp.grade.grade")
    public String index(){
        return "success!";
    }

    /**
     * Edit string.
     *
     * @return the string
     * @description 绩效修改
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 08:09:08.
     */
    @At
    @Ok("jsp:jsp.grade.edit")
    public Tbuss001VO edit(@Param("ptno")String ptno){
        return tbuss001MO.fectchByName(ptno);
    }

    /**
     * Task rule string.
     *
     * @return the string
     * @description 项目和任务规则绑定入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 11:09:21.
     */
    @At
    @Ok("jsp:jsp.grade.projectRule")
    public String projectRule(@Param("ptno")String ptno){
        return ptno;
    }

    /**
     * Show task string.
     *
     * @param ptno 绩效编号
     * @return the string
     * @description 绩效任务入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 03:10:30.
     */
    @At
    @Ok("jsp:jsp.grade.showTask")
    public Tbuss001VO showTask(@Param("ptno")String ptno){
        return tbuss001MO.fectchByName(ptno);
    }

    /**
     * Mark map.
     *
     * @param grop 团队编号
     * @return the map
     * @description 评分界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:00.
     */
    @At
    @Ok("re:jsp:jsp.grade.mark")
    public String mark(@Param("grop")String grop, @Param("ptno")String ptno, ViewModel model){
        Integer author = tbuss001MO.fectchByName(ptno).getSta1();
        model.setv("grop",grop);
        model.setv("ptno",ptno);
        if(author == 0) {
            return "jsp:jsp.grade.showGrade";
        }else{
            return null;
        }
    }

    /**
     * Count map.
     *
     * @return the map
     * @description  统计查看界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:51.
     */
    @At
    @Ok("jsp:jsp.grade.count")
    public Map<String,Object> count(@Param("grop")String grop,@Param("ptno")String ptno){
        return formatModel(grop,ptno);
    }

    /**
     * Query all map.
     *
     * @param pageNumber 当前查询页
     * @param pageSize   页的大小
     * @return the map
     * @description 绩效界面表格数据的请求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:35.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM'}")
    public Map<String, Object> queryAll(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String date,@Param("group")String group,HttpSession session){
        String usid = session.getAttribute("usid").toString();
        Cbase000VO cbase000VO = cbase000MO.fetchByUsid(usid);
        List<Tbuss001VO> tbuss001VOList = new ArrayList<>();
        Pager pager;
        Integer count = 0;
        Integer author = cbase000VO.getSTA2();
        String acco = cbase000VO.getACCO();
        String dept = cbase000VO.getDEPT();
        String comp = cbase000VO.getCOMP();
        String grop = cbase000VO.getGROP();
        SqlExpressionGroup e1 = null;
        SqlExpressionGroup e2;
        SqlExpressionGroup e3;
        if(date !=null) {
            e1 = Cnd.exps("pdat", "like", "%" + date + "%");
        }else{
            date = "";
        }
        if(group == null){
            group = "";
        }
        if(author == 1){
            List<Cbase010VO> cbase010VOS = cbase010MO.queryByCnd(Cnd.where("usid","=",usid));
            String[] grops = new String[cbase010VOS.size()];
            int i = 0;
            for(Cbase010VO cbase010VO:cbase010VOS){
                grops[i] = cbase010VO.getGrop();
                i++;
            }
            e2 = Cnd.exps("grop","in",grops);
            count = tbuss001MO.countByCnd(Cnd.where(e1).and(e2));
            pager = TableUtil.formatPager(pageSize,pageNumber,count);
            tbuss001VOList = tbuss001MO.queryAllByCnd(Cnd.where(e1).and(e2).desc("pdat"),pager);
        }else if(author == 2){
            count = tbuss001MO.countByAcco(acco,date,group);
            pager = TableUtil.formatPager(pageSize,pageNumber,count);
            tbuss001VOList = tbuss001MO.queryAllByAcco(acco,date,group,pager);
        }else if(author == 3){
            count = tbuss001MO.countByDept(dept,date,group);
            pager = TableUtil.formatPager(pageSize,pageNumber,count);
            tbuss001VOList = tbuss001MO.queryAllByDept(dept,date,group,pager);
        }else if(author == 4){
            count = tbuss001MO.countByComp(comp,date,group);
            pager = TableUtil.formatPager(pageSize,pageNumber,count);
            tbuss001VOList = tbuss001MO.queryAllByComp(comp,date,group,pager);
        }
        return TableUtil.makeJson(0,"成功",count,tbuss001VOList);
    }

    @At
    @Ok("json:{dateFormat:'yyyy-MM'}")
    public Map<String, Object> userEva(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String date,HttpSession session){
        String usid = session.getAttribute("usid").toString();
        List<Tbuss001VO> tbuss001VOList;
        Pager pager;
        Integer count;
        SqlExpressionGroup e1 = null;
        SqlExpressionGroup e2;
        String grop = "";
        List<Cbase010VO> cbase010VOS = cbase010MO.queryByCnd(Cnd.where("usid","=",usid));
        if(cbase010VOS != null && cbase010VOS.size() > 0){
            grop = cbase010VOS.get(0).getGrop();
        }
        if(date !=null) {
            e1 = Cnd.exps("pdat", "like", "%" + date + "%");
        }
        e2 = Cnd.exps("grop","=",grop);
        count = tbuss001MO.countByCnd(Cnd.where(e1).and(e2));
        pager = TableUtil.formatPager(pageSize,pageNumber,count);
        tbuss001VOList = tbuss001MO.queryAllByCnd(Cnd.where(e1).and(e2).desc("pdat"),pager);
        List<UserEva> userEvas = new ArrayList<>();
        for(Tbuss001VO tbuss001VO:tbuss001VOList){
            TBuss017VO tBuss017VO = bussMoFactory.getTbuss017MO().fetchByUsidPdat(usid,tbuss001VO.getPdat());
            userEvas.add(new UserEva(tbuss001VO.getPtno(),tbuss001VO.getDsca(),tbuss001VO.getPdat(),tBuss017VO.getStage()));
        }
        return TableUtil.makeJson(0,"成功",count,userEvas);
    }

    /**
     * Mark template map.
     *
     * @param pager 分页
     * @param grop  团队ID
     * @return the map
     * @description 评分界面的被评用户和分项的请求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:24.
     */
    @At

    @Ok("json")
    public Map<String, Object> markTemplate(@Param("..") Pager pager,@Param("grop")String grop,@Param("ptno")String ptno){
        Map<String,Object> map = new HashMap<>();
        List<Cbase011VO> cbase011VOS = new ArrayList<>();
        map.put("team",null);
        map.put("score",null);
        map.put("mark",null);
        if(ptno!=null) {
            if (grop != null) {
                List<Cbase000VO> cbase000VOS = new ArrayList<>();
                List<GropUser> gropUsers = cbase009MO.fetchC9Tran(grop,Cnd.where("boss","=","组员"));
                for (GropUser gropUser:gropUsers){
                    cbase000VOS.add(new Cbase000VO(gropUser.getUsid(),gropUser.getDsca()));
                }
                map.put("team", cbase000VOS);
            }
            List<Cbase011VO> cbase011VOList = tbuss001MO.fetchTransByNameCnd(ptno,"cbase011VOS",Cnd.where("stat","=","0")).getCbase011VOS();
            if(cbase011VOList != null) {
                List<Tbuss005VO> tbuss005VOS = tbuss001MO.fetchTransByNameCnd(ptno,"tbuss005VOS",null).getTbuss005VOS();
                if(tbuss005VOS != null){
                    List<Tbuss005VO> tbuss005VOList = new ArrayList<>();
                    for(Tbuss005VO tbuss005VO:tbuss005VOS){
                        String cons = cbase011MO.fetchByPjno(tbuss005VO.getPjno()).getCons();
                        double result = DoubleUtil.format_nice(Double.parseDouble(tbuss005VO.getCons())-Double.parseDouble(cons));
                        if(result >= 0.0) {
                            tbuss005VO.setCons("+"+result);
                        }else{
                            tbuss005VO.setCons(result+"");
                        }
                        tbuss005VOList.add(tbuss005VO);
                    }
                    map.put("score",tbuss005VOList);
                }
                for (Cbase011VO cbase011VO : cbase011VOList) {
                    cbase011VOS.add(cbase011MO.fetchTransByVO(cbase011VO, "cbase012VOS",null));
                }
                map.put("mark", cbase011VOS);
            }
        }
        return map;
    }

    @At
    @Ok("json")
    public Map<String, Object> deleteGrade(@Param("ptno")String ptno,@Param("::list")String[] ptnos){
        Integer code = 0;
        String msg;
        if(ptno !=null) {
            tbuss005MO.deleteByPtno(ptno);
            code = tbuss001MO.deleteByName(ptno);
        }else if(ptnos != null){
            for(String PTNO:ptnos){
                tbuss005MO.deleteByPtno(PTNO);
                code = tbuss001MO.deleteByName(PTNO);
            }
        }
        msg = code == 1?"删除成功！":"删除失败!";
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Query all project rule map.
     *
     * @param ptno the ptno
     * @return the map
     * @description 查询所有项目的关联任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 11:09:56.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllProjectRule(@Param("ptno")String ptno){
        Integer code = 1;
        String msg = "查询失败，当前请求未正常调用！";
        List<Cbase011VO> cbase011VOS = new ArrayList<>();
        if(ptno != null) {
            Tbuss001VO tbuss001VO = tbuss001MO.fetchTransByNameCnd(ptno,"cbase011VOS",null);
            cbase011VOS = tbuss001VO.getCbase011VOS();
            code = 0;
            msg="查询成功";
        }
        return TableUtil.makeJson(code,msg,null,cbase011VOS);
    }

    /**
     * Insert project rule map.
     *
     * @param pjno 规则编号
     * @param ptno 项目编号
     * @return the map
     * @description 插入项目规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:11.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertProjectRule(@Param("pjno")String pjno,@Param("ptno")String ptno){
        Integer code = 0;
        String msg = "插入失败";
        if(pjno!=null && !"".equals(pjno) && ptno != null && !"".equals(ptno)){
            Condition cnd = Cnd.where("pjno","=",pjno).and("ptno","=",ptno);
            if(tbuss002MO.queryByCnd(cnd).size() == 0) {
                tbuss002MO.insert(new Tbuss002VO(pjno, ptno));
                code = 1;
                msg = "插入成功";
            }else{
                msg = "已存在";
            }
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Insert project map.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @param request    the request
     * @return the map
     * @description 插入单条绩效信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 04:09:10.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertProject(@Param("..")Tbuss001VO tbuss001VO, HttpServletRequest request){
        Integer code = 0;
        String msg = "插入失败";
        String usid = request.getSession().getAttribute("usid").toString();
        if(tbuss001VO.getPdat() !=null && tbuss001VO.getDsca() != null && tbuss001VO.getGrop() != null){
            if(tbuss001MO.insertCheck(tbuss001VO.getPdat(),tbuss001VO.getGrop())) {
                tbuss001VO.setPtno("PT"+FileUtil.getRandomName());
                tbuss001VO.setUdat(new Date());
                tbuss001VO.setUsid(usid);
                tbuss001VO.setSta1(1);
                tbuss001VO.setSta2(1);
                tbuss001MO.insert(tbuss001VO);
                code = 1;
                msg = "插入成功";
            }else{
                msg = "已存在，请检查后添加！";
            }
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Copy project map.
     *
     * @param ptnos   the ptnos
     * @param request the request
     * @return the map
     * @description 复制绩效项目
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 09:09:59.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> copyProject(@Param("::list")String[] ptnos, HttpServletRequest request){
        Integer code = 0;
        String usid = request.getSession().getAttribute("usid").toString();
        if(ptnos!=null) {
            for (String ptno:ptnos) {
                Tbuss001VO tbuss001VO = tbuss001MO.fetchTransByNameCnd(ptno, "cbase011VOS", null);
                if(tbuss001VO != null) {
                    tbuss001VO.setPtno("PT" + FileUtil.getRandomName());
                    tbuss001VO.setUsid(usid);
                    tbuss001VO.setSta1(1);
                    tbuss001VO.setSta2(1);
                    tbuss001VO.setUdat(new Date());
                    tbuss001MO.insert(tbuss001VO);
                    tbuss001MO.insertRelation(tbuss001VO);
                    code = 1;
                }
            }
        }
        String msg = code == 0?"复制失败":"复制成功";
        return ResultUtil.getResult(code,msg,null);
    }



    /**
     * Update project map.
     *
     * @param pdat 日期
     * @param grop 团队
     * @param dsca 绩效描述-标题
     * @param ptno 绩效编号
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 09:09:48.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateProject(@Param("pdat")String pdat,@Param("grop")String grop,@Param("dsca")String dsca,@Param("ptno")String ptno){
        Integer code = 0;
        if(StringUtil.checkString(ptno) && StringUtil.checkString(pdat,grop,dsca)){
            Tbuss001VO tbuss001VO = tbuss001MO.fectchByName(ptno);
            tbuss001VO.setPdat(pdat);
            tbuss001VO.setGrop(grop);
            tbuss001VO.setDsca(dsca);
            code = tbuss001MO.updateByVO(tbuss001VO);
        }
        String msg = code == 0?"请填入必填项":"修改成功";
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete project rule map.
     *
     * @param pjno   规则编号
     * @param pjnos 多条规则的编号集合
     * @param ptno        项目编号
     * @return the map
     * @description 根据传入条件判断并删除项目规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:10.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteProjectRule(@Param("pjno")String pjno,@Param("::list")String[] pjnos,@Param("ptno")String ptno){
        Integer code = 0;
        String msg = "删除失败";
        String[] param = new String[1];
        if(ptno !=null) {
            if (pjno != null) {
                param[0] = pjno;
                code = tbuss002MO.delete(param,ptno);
            }else if(pjnos !=null){
                code = tbuss002MO.delete(pjnos,ptno);
            }
            msg = code == 1?"删除成功":"删除失败";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Insert score map.
     *
     * @param grade_trans
     * @return the map
     * @description 上传分数情况
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 08:09:38.
     */
    @At
    @POST
    @Ok("json")
    public Map<String, Object> insertScore(@Param("::list") List<Tbuss005_Grade_Trans> grade_trans) {
        int code = 0;
        if (grade_trans != null) {
            for (Tbuss005_Grade_Trans tbuss005_grade_trans : grade_trans) {
                String csid = tbuss005_grade_trans.getCsid();
                String pjno = tbuss005_grade_trans.getPjno();
                String ptno = tbuss005_grade_trans.getPtno();
                String remk = tbuss005_grade_trans.getRemk();
                Double con = tbuss005_grade_trans.getCons();
                if (StringUtil.checkString(csid, pjno, ptno) && remk != null) {
                    double cons = Integer.parseInt(cbase011MO.fetchTransByPjno(pjno).getCons()) + con;
                    Tbuss005VO tbuss005VO = new Tbuss005VO(ptno,csid,pjno,cons+"",remk,new Date(),new Date());
                    if (tbuss005MO.insertCheck(tbuss005VO)) {
                        tbuss005MO.insert(tbuss005VO);
                    } else {
                        tbuss005MO.updateByVO(tbuss005VO);
                    }
                }
            }
            code = 1;
        }
        return ResultUtil.getResult(code, null, null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateSta1(@Param("ptno")String ptno,@Param("sta1")Integer sta1){
        if(ptno!=null && !"".equals(ptno) && sta1 != null){
            Tbuss001VO tbuss001VO = tbuss001MO.fectchByName(ptno);
            tbuss001VO.setSta1(sta1);
            tbuss001MO.updateByVO(tbuss001VO);
        }
        return ResultUtil.getResult(1,"",null);
    }

    /**
     * Inser auto score map.
     *
     * @param ptno 绩效编号
     * @return the map
     * @description 自动打分
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 11:09:13.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertAutoScore(@Param("ptno")String ptno){
        int code = 0;
        String msg = "结算失败";
        if(ptno!=null) {
            Tbuss001VO tbuss001VO = tbuss001MO.fectchByName(ptno);
            List<Cbase000VO> cbase000VOS = new ArrayList<>();
            List<GropUser> gropUsers = cbase009MO.fetchC9Tran(tbuss001MO.fectchByName(ptno).getGrop(),Cnd.where("boss","=","组员"));
            for (GropUser gropUser:gropUsers){
                cbase000VOS.add(new Cbase000VO(gropUser.getUsid(),gropUser.getDsca()));
            }
            for (Cbase000VO cbase000VO : cbase000VOS) {
                Cnd cnd = Cnd.where("stat", "=", 1);
                Tbuss001VO t1 = tbuss001MO.fetchLinksByVO(tbuss001VO,"cbase011VOS",cnd);
                List<Cbase011VO> cbase011VOS = t1.getCbase011VOS();
                for (Cbase011VO cbase011VO : cbase011VOS) {
                    SqlExpressionGroup e = Cnd.exps("ptyp", "=", cbase011VO.getPjno()).and("ptno", "=", ptno).and("csid", "=", cbase000VO.getUSID());
                    Integer count = tbuss003MO.countByCnd(Cnd.where(e));
                    if (count != 0) {
                        double average = DoubleUtil.format_nice(Double.parseDouble(cbase011VO.getCons())/ count);
                        double result = 0;
                        List<Tbuss003VO> tbuss003VOList = tbuss003MO.queryAllByCnd(Cnd.where(e).and("sta1", "=", "11"),null);
                        if(tbuss003VOList != null){
                            for(Tbuss003VO tbuss003VO:tbuss003VOList){
                                Integer stag = tbuss003VO.getStag();
                                if(stag == 0){
                                    result += average*0.6;
                                }else if(stag == 1){
                                    result += average*0.7;
                                }else if(stag == 2){
                                    result += average*0.8;
                                }else if(stag == 3){
                                    result += average*0.9;
                                }else if(stag == 4){
                                    result += average;
                                }
                            }
                        }
                        Tbuss005VO tbuss005VO = new Tbuss005VO(ptno, cbase000VO.getUSID(), cbase011VO.getPjno(), DoubleUtil.format_nice(result) + "", "自动打分", new Date(), new Date());
                        if(tbuss005MO.insertCheck(tbuss005VO)) {
                            tbuss005MO.insert(tbuss005VO);
                        }else{
                            tbuss005MO.updateByVO(tbuss005VO);
                        }
                    }
                }
            }
            tbuss001VO.setSta2(1);
            tbuss001MO.updateByVO(tbuss001VO);
            code = 1;
            msg = "结算成功";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Get total score map.
     *
     * @param grop 团队编号
     * @param ptno 绩效表编号
     * @return the map
     * @description 获取目标绩效表的总成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 02:09:07.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> getTotalScore(@Param("grop")String grop,@Param("ptno")String ptno){
        Integer code = 0;
        String msg = "";
        Map<String,Object> map = new HashMap<>();
        if(grop !=null && ptno!=null) {
            List<Cbase000VO> cbase000VOS = cbase009MO.fetchC9Trans(grop,null).getCbase000VOS();
            if(cbase000VOS!=null) {
                for (Cbase000VO cbase000VO : cbase000VOS) {
                    double cons = 0.0;
                    Condition cnd = Cnd.where("csid", "=", cbase000VO.getUSID()).and("ptno", "=", ptno);
                    List<Tbuss005VO> tbuss005VOS = tbuss005MO.queryAllByCndPager(cnd, null);
                    for (Tbuss005VO tbuss005VO : tbuss005VOS) {
                        cons += Double.parseDouble(tbuss005VO.getCons());
                    }
                    map.put(cbase000VO.getUSID(), cons);
                }
                map.put("user", cbase000VOS);
            }
        }
        return ResultUtil.getResult(code,msg,map);
    }

    /**
     * Query mark grade map.
     *
     * @param ptno    绩效的编号
     * @return the map
     * @description 查询团队的评分成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 05:09:36.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> queryGropMarkGrade(@Param("ptno")String ptno,@Param("grop")String grop){
        int code = 0;
        Map<String,Object> map = new HashMap<>();
        if(ptno != null && grop != null){
            List<Cbase011VO> cbase011VOS = tbuss001MO.fetchTransByNameCnd(ptno,"cbase011VOS",null).getCbase011VOS();
            List<Cbase000VO> cbase000VOS = cbase009MO.fetchC9Trans(grop,null).getCbase000VOS();
            if(cbase000VOS != null && cbase011VOS !=null) {
                map.put("rule",cbase011VOS);
                for(Cbase011VO cbase011VO:cbase011VOS) {
                    List<Tbuss005VO> tbuss005VOList = new ArrayList<>();
                    for (Cbase000VO cbase000VO : cbase000VOS) {
                        List<Tbuss005VO> tbuss005VOS = tbuss001MO.fetchTransByNameCnd(ptno, "tbuss005VOS", Cnd.where("csid", "=", cbase000VO.getUSID())
                                .and("pjno","=",cbase011VO.getPjno())).getTbuss005VOS();
                        for (Tbuss005VO tbuss005VO:tbuss005VOS){
                            tbuss005VO.setCbase000VO(cbase000MO.ftechUserDC(tbuss005VO.getCsid())); //查询到用户的名字
                            tbuss005VOList.add(tbuss005VO);
                        }
                    }
                    map.put(cbase011VO.getPjno(),tbuss005VOList);
                }
            }
            code = 1;
        }
        String msg = code == 1?"查询成功":"查询失败";
        return ResultUtil.getResult(code,msg,map);
    }

    /**
     * Test.
     *
     * @param request  request请求
     * @param response tresponse返回
     * @param ptno     绩效表编号
     * @description 通过ptno生成当月绩效表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 11:10:50.
     */
    @At
    @Ok("void")
    public void printGrade(HttpServletRequest request, HttpServletResponse response, @Param("ptno")String ptno){
        Map<String,Tbuss001VO> tbuss001VOMap = new HashMap<>();
        List<Cbase000VO> cbase000VOS = cbase009MO.fetchC9Trans(tbuss001MO.fectchByName(ptno).getGrop(),null).getCbase000VOS();
        List<Tbuss002VO> tbuss002VOS = tbuss002MO.queryByCnd(Cnd.where("ptno","=",ptno));
        String[] rose = new String[tbuss002VOS.size()];
        for (int i = 0;i < tbuss002VOS.size();i++){
            rose[i] = tbuss002VOS.get(i).getPjno();
        }
        List<Cbase011VO> cbase011VOS = cbase011MO.queryAllByCnd(Cnd.where("pjno","in",rose).and("stat","=",1),null);
        for (Cbase000VO cbase000VO : cbase000VOS) {
            Tbuss001VO tbuss001VO = new Tbuss001VO();
            tbuss001VO.setPtno(ptno);
            tbuss001VO = tbuss001MO.fetchTransByNameCnd(ptno, "cbase011VOS", Cnd.where("stat", "=", 0));
            List<Tbuss003_Grade_Trans> tbuss003VOList = new ArrayList<>();
            List<Cbase011VO> cbase011VOList =tbuss001VO.getCbase011VOS();
            List<Cbase011_Grade_Trans> c11_grade_trans = new ArrayList<>();
            for (Cbase011VO cbase011VO:cbase011VOList){
                List<Tbuss005VO> tbuss005VOS = tbuss005MO.queryAllByCndPager(Cnd.where("csid","=",cbase000VO.getUSID()).
                        and("ptno","=",ptno).and("pjno","=",cbase011VO.getPjno()),null);
                double cons = 0.00;
                if(tbuss005VOS.size() != 0) {
                     cons = DoubleUtil.format_nice(Double.parseDouble(tbuss005VOS.get(0).getCons()));
                }
                c11_grade_trans.add(new Cbase011_Grade_Trans(cbase011VO, cons));
            }
            tbuss001VO.setC11_grade_trans(c11_grade_trans);
            for (Cbase011VO cbase011VO:cbase011VOS) {
                SqlExpressionGroup e = Cnd.exps("ptyp", "=", cbase011VO.getPjno()).and("ptno", "=", ptno).and("csid", "=", cbase000VO.getUSID());
                Integer count = tbuss003MO.countByCnd(Cnd.where(e));
                List<Tbuss003VO> tbuss003VOS = tbuss003MO.queryAllByCnd(Cnd.where(e),null);
                if (tbuss003VOS != null) {
                    for (Tbuss003VO tbuss003VO : tbuss003VOS) {
                        Tbuss003_Grade_Trans grade_trans = new Tbuss003_Grade_Trans(tbuss003MO.fetchTrans(tbuss003VO.getTaid(), "cbase011VO", null),count);
                        tbuss003VOList.add(grade_trans);
                    }
                }
            }
            tbuss001VO.setGrade_trans(tbuss003VOList);
            tbuss001VOMap.put(cbase000VO.getUSID(), tbuss001VO);
        }
        try {
            GradeExcel.export(cbase000VOS,tbuss001VOMap,request,response);
        } catch (SQLException | IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print all grade.
     *
     * @param request  the request
     * @param response the response
     * @param ptno     绩效表编号
     * @description 根据绩效表的编号生成Excel表.(领导点击链接获得)
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:16 02:10:19.
     */
    @At
    @Ok("void")
    @Filters
    public void printAllGrade(HttpServletRequest request, HttpServletResponse response, @Param("ptno")String ptno){
            Tbuss001VO tbuss001VO = tbuss001MO.fetchTransByNameCnd(ptno, "tbuss003VOS",null);
            List<Tbuss003VO> tbuss003VOS = tbuss001VO.getTbuss003VOS();
            List<Tbuss003VO> tbuss003VOList = new ArrayList<>();
            if (tbuss003VOS != null) {
                for (Tbuss003VO tbuss003VO : tbuss003VOS) {
                    tbuss003VOList.add(tbuss003MO.fetchTrans(tbuss003VO.getTaid(), "cbase011VO", null));
                }
                tbuss001VO.setTbuss003VOS(tbuss003VOList);
            }
            tbuss001VO = tbuss001MO.fetchLinksByVO(tbuss001VO,"cbase011VOS",Cnd.where("stat","=",0));
        try {
            GradeExcel.export(tbuss001VO,request,response);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Push grade.
     *
     * @param ptno 绩效编号
     * @description 推送绩效表给领导.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:16 02:10:53.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> pushGrade(@Param("ptno")String ptno, HttpSession session){
        String usid = session.getAttribute("usid").toString();
        String msg = "推送成功！";
        if(StringUtil.checkString(ptno)) {
            String[] csid = {"180043", "180246"};
            String[] copyTo = {usid};
            MailUtil.sendmail(csid, copyTo, ptno, tbuss001MO.fectchByName(ptno).getGropnam());
        }else{
            msg = "发生未知错误！";
        }
        return ResultUtil.getResult(1,msg,null);
    }

    /**
     * Get rank list.
     *
     * @param pdat 日期
     * @return the list
     * @description 得到rank排名.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:32.
     */
    @At
    @POST
    @Ok("json")
    public List<GradeVO> getRank(@Param("pdat")String pdat,@Param("grop")String grop){
        if(!StringUtil.checkString(pdat)){
            pdat = DateUtil.formatYMDate(new Date());
        }
        return cbase000MO.queryAllGradeByPdat(pdat,grop);
    }

    /**
     * @param pdat 考核月份
     * @param S S绩效等级数量
     * @param A A绩效等级数量
     * @param C C绩效等级数量
     * @param request
     * @param response
     * @description 打印出绩效成绩
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 10:36:35
     */
    @At
    @Ok("void")
    public void printGradeOkr(@Param("pdat")String pdat, @Param("S")Integer S, @Param("A")Integer A, @Param("C")Integer C,
                               HttpServletRequest request,HttpServletResponse response){
        List<ExportGradeOkrVO> gradeVOS = cbase000MO.queryAllGradeOkrByPdat(pdat);
        try {
            GradeExcel.export(gradeMarkStage(gradeVOS,S,A,C,pdat),pdat,request,response);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pdat 考核月份
     * @param request
     * @param response
     * @description 打印已有成绩的绩效成绩
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 11:30:07
     */
    @At
    @Ok("void")
    public void printOldGradeOkr(@Param("pdat")String pdat,HttpServletRequest request,HttpServletResponse response){
        try {
            GradeExcel.export(bussMoFactory.getTbuss017MO().queryAllByPdatAcco(pdat,"3"),pdat,request,response);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pdat 考核月份
     * @param S S绩效等级数量
     * @param A A绩效等级数量
     * @param C C绩效等级数量
     * @return 绩效成绩数据的标准返回格式
     * @description 获得绩效成绩的数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 10:36:55
     */
    @At
    @Ok("json")
    public Map<String,Object> gradeOkrData(@Param("pdat")String pdat, @Param("S")Integer S, @Param("A")Integer A, @Param("C")Integer C){
        List<ExportGradeOkrVO> gradeVOS = cbase000MO.queryAllGradeOkrByPdat(pdat);
        return ResultUtil.getResult(1,"",gradeMarkStage(gradeVOS,S,A,C,pdat));
    }

    private Map<String,Object> formatModel(String grop,String ptno){
        Map<String,Object> map = new HashMap<>();
        map.put("grop",grop);
        map.put("ptno",ptno);
        return map;
    }

    /**
     * @param gradeVOS 绩效成绩
     * @param pdat 考核月份
     * @param S S绩效等级数量
     * @param A A绩效等级数量
     * @param C C绩效等级数量
     * @return 返回已经评等级的绩效成绩
     * @description 对绩效成绩进行评分并且存进数据库
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 10:37:31
     */
    private List<ExportGradeOkrVO> gradeMarkStage(List<ExportGradeOkrVO> gradeVOS,Integer S,Integer A,Integer C,String pdat){
        List<ExportGradeOkrVO> gradeOkrVOS = new ArrayList<>();
        int gradeCount = gradeVOS.size();
        if(gradeCount <= S){

            for(ExportGradeOkrVO gradeVO:gradeVOS){
                gradeVO.setCpid("A");
                gradeOkrVOS.add(gradeVO);
            }

        }else if(gradeCount <= (S + A)){

            for (int i = gradeCount-1;i >= 0;i--){
                ExportGradeOkrVO gradeVO = gradeVOS.get(i);
                if(i >= gradeCount - S){
                    gradeVO.setStage("S");
                }else{
                    gradeVO.setStage("A");
                }
                gradeOkrVOS.add(gradeVO);
            }

        }else if(gradeCount >  (S + A) && gradeCount <= (S + A + C)){

            for (int i = gradeCount-1;i >= 0;i--){
                ExportGradeOkrVO gradeVO = gradeVOS.get(i);
                if(i >= gradeCount - S){
                    gradeVO.setStage("S");
                }else if (i >= gradeCount - S - A){
                    gradeVO.setStage("A");
                }else{
                    gradeVO.setStage("C");
                }
                gradeOkrVOS.add(gradeVO);
            }

        }else{

            for (int i = gradeCount-1;i >= 0;i--){
                ExportGradeOkrVO gradeVO = gradeVOS.get(i);
                if (i >= gradeCount - S) {
                    gradeVO.setStage("S");
                } else if (i >= gradeCount - S - A) {
                    gradeVO.setStage("A");
                } else if (i >= C){
                    gradeVO.setStage("B");
                } else{
                    gradeVO.setStage("C");
                }
                gradeOkrVOS.add(gradeVO);
            }

        }

        List<TBuss017VO> tBuss017VOS = new ArrayList<>();
        for(ExportGradeOkrVO gradeOkrVO:gradeVOS){
            tBuss017VOS.add(new TBuss017VO(pdat,"3",gradeOkrVO.getCsid(),gradeOkrVO.getScore()+"",gradeOkrVO.getStage()));
        }
        if(bussMoFactory.getTbuss017MO().countByPdatAcco(pdat,"3") > 0) {
            bussMoFactory.getTbuss017MO().updateByVOS(tBuss017VOS);
        }else{
            bussMoFactory.getTbuss017MO().insertByVOS(tBuss017VOS);
        }

        return gradeOkrVOS;
    }
}
