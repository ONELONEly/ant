package com.gree.ant.controller;

import com.gree.ant.mo.Cbase013MO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase013VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Require controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 需求的控制器.
 * @title RequireController
 * @createTime 2017 :10:30 10:10:41.
 */
@At("/system")
@IocBean
public class SystemController {


    @Inject("refer:cbase013MO")
    private Cbase013MO cbase013MO;

    @At
    @Ok("jsp:jsp.system.manage")
    public Map<String, Object> tongbuDSSystem() {
        int code = cbase013MO.tongbuDSSystem();
        String msg = "系统同步成功";
        return ResultUtil.getResult(code,msg,null);
    }


    /**
     * Manage string.
     *
     * @return the string
     * @description 需求管理界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @Filters
    @At
    @Ok("jsp:jsp.system.manage")
    public String manage(){
        return "success!";
    }
/*    *//**
     * Query all require map.
     * 从DS数据中找出数据来，插入到ant数据库中
     */


    @At
    @Ok("json")
    public Map<String,Object> queryAllSystem(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize, @Param("key")String key){

        SqlExpressionGroup e = null;
        if(key != null){
            e = Cnd.exps("dsca","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        Cnd cnd = Cnd.where(e);
        return TableUtil.makeJson(0,"",cbase013MO.countByCnd(cnd), cbase013MO.queryAllByCnd(cnd,pager));
    }
    @At
    @Ok("jsp:jsp.system.insert")
    public String insert(){
      return "success!";
    }

    @At
    @Ok("jsp:jsp.system.modify")
    public Map<String, Object> modify(String syno){
       Cbase013VO cbase013VO=cbase013MO.fetchBySyno(syno);
       Map<String,Object> map = new HashMap<>();
       map.put("sys",cbase013VO);
        return map;
    }
    /**
     * Insert rule map.
     *

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
    public Map<String,Object> insertSystem(@Param("..")Cbase013VO cbase013VO, HttpServletRequest request){
        String msg = "";
        Integer code = 0;
        cbase013VO.setSyno("S"+ FileUtil.createFileUtil().getRandomName5());
        if(cbase013VO.getDsca()!=null){
            cbase013MO.insert(cbase013VO);
            code = 1;
       }

       msg=code==1?"系统添加成功":"添加失败";
     return ResultUtil.getResult(code,msg,null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> editSystem(Cbase013VO cbase013VO, HttpServletRequest request){

        String msg="";
        Integer code=0;
        if(StringUtil.checkString(cbase013VO.getDsca())){
            Cbase013VO cbase013PO=new Cbase013VO();
            cbase013PO.setSyno(cbase013VO.getSyno());
            cbase013PO.setDsca(cbase013VO.getDsca());
            cbase013PO.setSadd(cbase013VO.getSadd());
            cbase013PO.setTadd(cbase013VO.getTadd());
            code=cbase013MO.updateByVO(cbase013PO);
            code=1;
            msg ="修改成功";
        }else {
         msg="请确认您已经输入了所有信息！";
        }

        return ResultUtil.getResult(code,msg,null);

    }

    @At
    @POST
    @Ok("json")
    public  Map<String,Object> deleteSystem(String syno,@Param("::synos")String[] synos){
        String msg ="";
        Integer code=0;

        if(StringUtil.checkString(syno)){
            code=cbase013MO.deleteBySyno(syno);
            code=1;
        }else if(synos!=null){
            for (String syno1:synos){
                code=cbase013MO.deleteBySyno(syno1);
                code=1;
            }
        }else{
            msg="请求参数为空";
        }
        msg=code==1?"删除成功":msg;
        return ResultUtil.getResult(code,msg,null);

    }



}
