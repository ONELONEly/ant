package com.gree.ant.controller;

import com.gree.ant.mo.*;
import com.gree.ant.util.*;
import com.gree.ant.vo.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * The type User controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用户操作的Controller
 * @title UserController
 * @createTime 2017 :09:06 06:09:18.
 */
@IocBean
@At("/user")
public class UserController {


    @Inject("refer:cbase005MO")
    private Cbase005MO cbase005MO;

    @Inject("refer:cbase006MO")
    private Cbase006MO cbase006MO;

    @Inject("refer:cbase009MO")
    private Cbase009MO cbase009MO;

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    @Inject("refer:cbase011MO")
    private Cbase011MO cbase011MO;

    @Inject("refer:tbuss001MO")
    private Tbuss001MO tbuss001MO;

    @Inject("refer:tbuss005MO")
    private Tbuss005MO tbuss005MO;

    /**
     * Modify map.
     *
     * @param request the request
     * @return the map
     * @description 修改用户入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:07.
     */
    @At
    @Ok("jsp:jsp.user.modify")
    public Map<String,Object> modify(HttpServletRequest request,@Param("usid")String usid){
        if(!StringUtil.checkString(usid)){
            usid = request.getSession().getAttribute("usid").toString();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user",cbase000MO.fetchByUsid(usid));
        return map;
    }

    @At
    @Ok("jsp:jsp.user.manageModify")
    public Map<String,Object> manageModify(HttpServletRequest request,@Param("usid")String usid){
        if(!StringUtil.checkString(usid)){
            usid = request.getSession().getAttribute("usid").toString();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user",cbase000MO.fetchByUsid(usid));
        return map;
    }

    /**
     * Insert string.
     *
     * @return the string
     * @description 插入用户入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:56.
     */
    @At
    @Ok("jsp:jsp.user.insert")
    public String insert(){
        return "success!";
    }

    /**
     * Eva string.
     *
     * @return the string
     * @description 用户个人评价入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:55.
     */
    @At
    @Ok("jsp:jsp.user.eva")
    public String eva(){
        return "success!";
    }

    /**
     * Board string.
     *
     * @return the string
     * @description 个人看板入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 02:09:19.
     */
    @At
    @Ok("jsp:jsp.user.board")
    public String board(HttpServletRequest request){
        Map<String,String> tokenMap = TokenUtil.getInstance().makeToken();
        request.getSession().setAttribute("password",tokenMap.get("password"));
        return tokenMap.get("token");
    }

    /**
     * Task string.
     *
     * @return the string
     * @description 个人任务入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 04:09:55.
     */
    @At
    @Ok("jsp:jsp.user.task")
    public String task(){
        return "success!";
    }

    /**
     * Manage string.
     *
     * @return the string
     * @description 用户管理界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:43.
     */
    @At
    @Ok("jsp:jsp.user.manage")
    public String manage(){
        return "success!";
    }

    /**
     * User role string.
     *
     * @param usid 用户ID
     * @return the string
     * @description 用户角色的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:57.
     */
    @At
    @Ok("jsp:jsp.user.userRole")
    public String userRole(@Param("usid")String usid){
        return usid;
    }

    /**
     * Manage string.
     *
     * @return the string
     * @description 个人周报入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 06:09:51.
     */
    @At
    @Ok("jsp:jsp.user.weekNews")
    public Long weekNews(){
        return FileUtil.getSyFileName();
    }

    /**
     * Week news long.
     *
     * @return the long
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:22 09:09:42.
     */
    @At
    @Ok("jsp:jsp.user.weekNewsManage")
    public String weekNewsManage(HttpServletRequest request){
        return request.getSession().getAttribute("usid").toString();
    }

    /**
     * Count map.
     *
     * @param ptno    the ptno
     * @return the map
     * @description 统计查看界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 07:09:10.
     */
    @At
    @Ok("jsp:jsp.user.count")
    public Map<String,Object> count(@Param("ptno")String ptno){
        Map<String,Object> map = new HashMap<>();
        map.put("ptno",ptno);
        return map;
    }

    @At
    @Ok("jsp:jsp.user.showGrade")
    public String showGrade(@Param("ptno")String ptno){
        return ptno;
    }

    /**
     * Query all user map.
     *
     * @param pageNumber 当前查询页
     * @param pageSize   页的大小
     * @param key        过滤字段
     * @return the map
     * @description 以表格形式返回用户信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 03:09:08.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllUser(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String key){
        Condition cnd = null;
        if(key !=null) {
            cnd = Cnd.where("usid", "like", "%" + key + "%").or("dsca", "like", "%" + key + "%");
        }
        Integer count = cbase000MO.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"成功",count,cbase000MO.queryAllUser(cnd,pager));
    }

    /**
     * Get user header output stream.
     *
     * @param response the response
     * @param request  the request
     * @return the output stream
     * @description 得到当前用户的头像.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:49.
     */
    @At
    @Ok("raw:jpg")
    public OutputStream getUserHeader(HttpServletResponse response, HttpServletRequest request,@Attr("usid")String usid){
        Cbase000VO cbase000VO = cbase000MO.fetchByUsid(usid);
        byte[] bytes;
        if (cbase000VO.getBLOB() == null){
            bytes = FileUtil.getNormalHeader(request,"header.gif");
        }else {
            bytes = FileUtil.formatByteByBlob(cbase000VO.getBLOB());
        }
        return FileUtil.getOsByByte(bytes,response);
    }

    /**
     * Insert map.
     *
     * @param cbase000VO the cbase 000 vo
     * @return the map
     * @description 插入用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 02:09:25.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insert(@Param("..")Cbase000VO cbase000VO,HttpServletRequest request){
        String msg = "用户ID已存在";
        Integer code = 0;
        if(cbase000VO !=null && cbase000MO.fetchByUsid(cbase000VO.getUSID()) == null){
            cbase000VO.setBLOB(FileUtil.formatBlobByByte(FileUtil.getNormalHeader(request,"header.jpg")));
            cbase000MO.insert(cbase000VO);
            msg = "插入成功";
            code = 1;
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Modify map.
     *
     * @param cbase000VO the cbase 000 vo
     * @return the map
     * @description 修改用户信息.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 02:09:39.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> modify(@Param("..")Cbase000VO cbase000VO,@Param("rePawd")String rePawd){
        String msg = "请输入必要参数";
        Integer code = 0;
        if(cbase000VO !=null && rePawd !=null){
            if(rePawd.equals(cbase000VO.getPAWD())){
                Cbase000VO cbase000VO1 = cbase000MO.fetchByUsid(cbase000VO.getUSID());
                cbase000VO1.setDSCA(cbase000VO.getDSCA());
                if(StringUtil.checkString(cbase000VO.getPAWD())) {
                    cbase000VO1.setPAWD(cbase000VO.getPAWD());
                }
                if(StringUtil.checkString(cbase000VO.getCPID())) {
                    cbase000VO1.setCPID(cbase000VO.getCPID());
                }
                if(StringUtil.checkString(cbase000VO.getTEL1())) {
                    cbase000VO1.setTEL1(cbase000VO.getTEL1());
                }
                if(StringUtil.checkString(cbase000VO.getMAIL())) {
                    cbase000VO1.setMAIL(cbase000VO.getMAIL());
                }
                if(StringUtil.checkString(cbase000VO.getJWWJ())) {
                    cbase000VO1.setJWWJ(cbase000VO.getJWWJ());
                }
                if(cbase000VO.getSTA3() != null) {
                    cbase000VO1.setSTA3(cbase000VO.getSTA3());
                }
                if(cbase000VO.getSTA2() != null) {
                    cbase000VO1.setSTA2(cbase000VO.getSTA2());
                }
                if(StringUtil.checkString(cbase000VO.getDEPT())){
                    cbase000VO1.setDEPT(cbase000VO.getDEPT());
                }
                if(StringUtil.checkString(cbase000VO.getGROP())){
                    cbase000VO1.setGROP(cbase000VO.getGROP());
                }
                if(StringUtil.checkString(cbase000VO.getACCO())){
                    cbase000VO1.setACCO(cbase000VO.getACCO());
                }
                code = cbase000MO.updateByVO(cbase000VO1);
            }else{
                msg = "密码不正确，请确认密码！";
            }
            msg = code == 1?"修改成功":msg;
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Modify header map.
     *
     * @param file     头像文件
     * @param response the response
     * @param request  the request
     * @return the map
     * @description 修改用户的头像
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 03:09:05.
     */
    @At
    @Ok("json")
    @POST
    @AdaptBy(type = UploadAdaptor.class)
    public Map<String,Object> modifyHeader(@Param("file")TempFile file,@Param("usid")String usid, HttpServletResponse response,HttpServletRequest request){
        response.setHeader("Content-Type"," text/html");
        if(!StringUtil.checkString(usid)) {
            usid = request.getSession().getAttribute("usid").toString();
        }
        String msg = "更换头像失败";
        Integer code = 0;
        Cbase000VO cbase000VO = cbase000MO.fetchByUsid(usid);
        if(file!=null) {
            try {
                cbase000VO.setBLOB(new SerialBlob(FileUtil.formatByteByFile(file)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            code = cbase000MO.updateByVO(cbase000VO);
            msg = code == 1 ? "更换头像成功" : "更换头像失败";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete user map.
     *
     * @param usid        用户ID
     * @param usids       用户ID集合
     * @return the map
     * @description 删除单条用户信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:25.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteUser(@Param("usid")String usid, @Param("::list")String[] usids){
        String msg = "传入参数为空";
        Integer code = 0;
        if(usid!=null){
            code = cbase000MO.deleteByUsid(usid);
            msg = code == 1 ? "删除成功" : "删除失败";
        }else if(usids != null){
            for (String USID : usids) {
                code = cbase000MO.deleteByUsid(USID);
            }
            msg = code == 1 ? "删除成功" : "删除失败";
        }
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Query mark grade map.
     *
     * @param ptno    绩效的编号
     * @param request the request
     * @return the map
     * @description 查询单个用户的评分成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 05:09:36.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> queryUserMarkGrade(@Param("ptno")String ptno,HttpServletRequest request){
        Integer code = 0;
        List<Tbuss005VO> tbuss005VOList = new ArrayList<>();
        String usid = request.getSession().getAttribute("usid").toString();
        if(ptno != null){
            List<Tbuss005VO> tbuss005VOS = tbuss001MO.fetchTransByNameCnd(ptno,"tbuss005VOS",Cnd.where("csid","=",usid)).getTbuss005VOS();
            if(tbuss005VOS != null){
                for(Tbuss005VO tbuss005VO:tbuss005VOS) {
                    tbuss005VOList.add(tbuss005MO.fectchLinkByVO(tbuss005MO.fectchLinkByVO(tbuss005VO,"cbase011VO"),"cbase000VO"));
                }
            }
            code = 1;
        }
        String msg = code == 1?"查询成功":"查询失败";
        return ResultUtil.getResult(code,msg,tbuss005VOList);
    }

    /**
     * Get total score map.
     *
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
    public Map<String,Object> getTotalScore(@Param("ptno")String ptno,HttpServletRequest request){
        Integer code = 0;
        String msg = "";
        Map<String,Object> map = new HashMap<>();
        String usid = request.getSession().getAttribute("usid").toString();
        if(ptno!=null) {
            Cbase000VO cbase000VO = cbase000MO.fetchByUsid(usid);
            Double cons = 0.0;
            Condition cnd = Cnd.where("csid", "=", cbase000VO.getUSID()).and("ptno", "=", ptno);
            List<Tbuss005VO> tbuss005VOS = tbuss005MO.queryAllByCndPager(cnd, null);
            for (Tbuss005VO tbuss005VO : tbuss005VOS) {
                cons += Double.parseDouble(tbuss005VO.getCons());
            }
            map.put(cbase000VO.getUSID(), cons);
            map.put("user",cbase000VO);
        }
        return ResultUtil.getResult(code,msg,map);
    }

    /**
     * Query all user role map.
     *
     * @param usid 用户ID
     * @param key  过滤字段
     * @return t标准表格MAP输出
     * @description 通过USID,key查询所有用户角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:15.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllUserRole(@Param("usid")String usid,@Param("key")String key){
        Cnd cnd = null;
        List<Cbase007VO> cbase007VOList = new ArrayList<>();
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("roid","like","%"+key+"%");
        }
        Cbase000VO cbase000VO = cbase000MO.fetchTranByUsidPRI(usid,"cbase007VOS",cnd);
        if(cbase000VO!=null){
            cbase007VOList = cbase000VO.getCbase007VOS();
        }
        return TableUtil.makeJson(0,"",null,cbase007VOList);
    }

    /**
     * Insert user role map.
     *
     * @param cbase005VO 用户角色实体
     * @return 标注的数据请求返回
     * @description 添加用户角色
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:14.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertUserRole(@Param("..") Cbase005VO cbase005VO){
        Integer code = 0;
        String msg = "";
        String usid = cbase005VO.getUsid();
        String roid = cbase005VO.getRoid();
        if(StringUtil.checkString(usid) && StringUtil.checkString(roid)) {
            if(cbase005MO.queryByCnd(Cnd.where("usid","=",usid).and("roid","=",roid))) {
                cbase005VO = cbase005MO.insert(cbase005VO);
                if (cbase005VO != null) {
                    code = 1;
                }
            }else{
                msg = "当前角色已存在，不可重复添加";
            }
        }else{
            msg = "请求途径不合法！";
        }
        msg = code == 1 ?"添加权限成功":msg;
        return ResultUtil.getResult(code, msg, null);
    }


    /**
     * Delete user role map.
     *
     * @param usid  用户ID
     * @param roid  角色ID
     * @param roids 角色ID集合
     * @return 标注的数据请求返回
     * @description 删除用户角色
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:48.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteUserRole(@Param("usid")String usid,@Param("roid")String roid,@Param("::list")String[] roids){
        Integer code = 0;
        String msg = "删除失败";
        if(StringUtil.checkString(usid)){
            Cbase000VO cbase000VO = cbase000MO.fetchByUsid(usid);
            if(cbase000VO != null) {
                if (StringUtil.checkString(roid)) {
                    code = cbase005MO.delete(new Cbase005VO(usid, roid));
                } else if (roids != null) {
                    for (String ROID : roids) {
                        code = cbase005MO.delete(new Cbase005VO(usid,ROID));
                    }
                } else {
                    msg = "请通过正规途径访问";
                }
            }else{
                msg = "请通过正规途径访问";
            }
        }else{
            msg = "请通过正规途径访问";
        }
        msg = code == 1?"删除权限成功":msg;
        return ResultUtil.getResult(code, msg, null);
    }

}
