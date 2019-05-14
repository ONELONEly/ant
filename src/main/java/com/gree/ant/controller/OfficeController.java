package com.gree.ant.controller;


import com.gree.ant.mo.Cbase017MO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase017VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * The type Office controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 科室的Controller
 * @title OfficeController
 * @createTime 2017 :09:29 10:09:27.
 */
@At("/acco")
@IocBean
public class OfficeController {


    @Inject("refer:cbase017MO")
    private Cbase017MO cbase017MO;

    /**
     * Index string.
     *
     * @return the string
     * @description 科室管理的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:27.
     */
    @At
    @Ok("jsp:jsp.office.manage")
    public String index(){
        return "success";
    }


    /**
     * Query all Acco map.
     *
     * @param pageNumber 当前页数
     * @param pageSize   页的大小
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 11:09:01.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> queryAllAcco(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,@Param("key")String key){
        Cnd cnd = null;
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        Integer count = cbase017MO.countByCnd(cnd);
        List<Cbase017VO> cbase017VOList = cbase017MO.queryAllByCndPager(cnd,pager);
        return TableUtil.makeJson(0,"",count,cbase017VOList);
    }

    /**
     * Delete acco map.
     *
     * @param acco  科室编号
     * @param accos 科室编号集合
     * @return the map
     * @description 删除科室
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 11:09:41.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteAcco(@Param("acco")String acco,@Param("::list")String[] accos){
        Integer code = 0;
        String msg = "";
        if (StringUtil.checkString(acco)){
            code = cbase017MO.deleteByAcco(acco);
        }else if(accos.length != 0){
            for (String office:accos){
                code = cbase017MO.deleteByAcco(office);
            }
        }else{
            msg = "请选中要删除的项！";
        }
        msg = code == 1?"删除成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Insert grop map.
     *
     * @param dsca the dsca
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 04:09:11.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertAcco(@Param("dsca")String dsca){
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(dsca)){
            if(!cbase017MO.insertCheck(dsca)) {
                cbase017MO.insert(new Cbase017VO("KO" + FileUtil.createFileUtil().getRandomName(), dsca));
                code = 1;
            }else{
                msg = "科室已存在！请确认后输入！";
            }
        }else{
            msg = "请求参数为空！";
        }
        msg = code == 1?"添加科室成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update grop map.
     *
     * @param cbase017VO the cbase 017 vo
     * @return the map
     * @description 修改科室
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 04:09:38.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateAcco(@Param("..")Cbase017VO cbase017VO){
        Integer code = 0;
        String msg = "";
        if (StringUtil.checkString(cbase017VO.getAcco()) && StringUtil.checkString(cbase017VO.getDsca())){
            code = cbase017MO.updateByVO(cbase017VO);
        }else{
            msg = "请求错误";
        }
        msg = code == 1?"修改科室成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }
}
