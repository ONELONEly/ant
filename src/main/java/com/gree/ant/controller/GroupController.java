package com.gree.ant.controller;


import com.gree.ant.mo.Cbase009MO;
import com.gree.ant.mo.Cbase010MO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase009VO;
import com.gree.ant.vo.Cbase010VO;
import com.gree.ant.vo.response.GropUser;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Group controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 团队的Controller
 * @title GroupController
 * @createTime 2017 :09:29 10:09:36.
 */
@At("/grop")
@IocBean
public class GroupController {

    @Inject("refer:cbase009MO")
    private Cbase009MO cbase009MO;

    @Inject("refer:cbase010MO")
    private Cbase010MO cbase010MO;

    /**
     * Index string.
     *
     * @return the string
     * @description 团队管理的主页
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:36.
     */
    @At
    @Ok("jsp:jsp.group.manage")
    public String index(){
        return "success";
    }

    /**
     * Role user string.
     *
     * @param grop 团队ID
     * @return the string
     * @description 团队用户入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:16.
     */
    @At
    @Ok("jsp:jsp.group.gropUser")
    public String gropUser(@Param("grop")String grop){
        return grop;
    }


    /**
     * Query all grop map.
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
    public Map<String,Object> queryAllGrop(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,@Param("key")String key){
        Cnd cnd = null;
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        Integer count = cbase009MO.countByCnd(cnd);
        List<Cbase009VO> cbase009VOList = cbase009MO.queryAllByCnd(cnd,pager);
        return TableUtil.makeJson(0,"",count,cbase009VOList);
    }

    /**
     * Delete grop map.
     *
     * @param grop  团队编号
     * @param grops 团队编号集合
     * @return the map
     * @description 删除团队
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 11:09:42.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteGrop(@Param("grop")String grop,@Param("::list")String[] grops){
        Integer code = 0;
        String msg = "";
        if (StringUtil.checkString(grop)){
            code = cbase009MO.delete(grop);
        }else if(grops.length != 0){
            for(String group:grops){
                code = cbase009MO.delete(group);
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
     * @description 插入团队
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 02:09:21.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertGrop(@Param("dsca")String dsca){
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(dsca)){
            if(!cbase009MO.insertCheck(dsca)) {
                cbase009MO.insert(new Cbase009VO("AN" + FileUtil.getRandomName(), dsca));
                code = 1;
            }else{
                msg = "团队已存在！请确认后输入！";
            }
        }else{
            msg = "请求参数为空！";
        }
        msg = code == 1?"添加团队成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update grop map.
     *
     * @param cbase009VO the cbase 009 vo
     * @return the map
     * @description 修改团队
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 02:09:37.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateGrop(@Param("..")Cbase009VO cbase009VO){
        Integer code = 0;
        String msg = "";
        if (StringUtil.checkString(cbase009VO.getGrop()) && StringUtil.checkString(cbase009VO.getDsca())){
            code = cbase009MO.update(cbase009VO);
        }else{
            msg = "请求错误";
        }
        msg = code == 1?"修改团队成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Query all grop user map.
     *
     * @param grop 团队编号
     * @param key  过滤关键词
     * @return 标准table返回数据
     * @description 彤过GOID,key查询所有团队用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:05.
     */
    @At
    @Ok("json")
    public Map<String,Object>queryAllGropUser(@Param("grop")String grop,@Param("key")String key){
        Cnd cnd = null;
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("usid","like","%"+key+"%");
        }
        return TableUtil.makeJson(0,"",null,cbase009MO.fetchC9Tran(grop,cnd));
    }

    /**
     * Insert grop user map.
     *
     * @param cbase010VO 团队用户实体
     * @return 标准的数据请求返回格式
     * @description 通过VO插入单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:24.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertGropUser(@Param("..") Cbase010VO cbase010VO){
        Integer code = 0;
        String msg = "";
        String grop = cbase010VO.getGrop();
        String usid = cbase010VO.getUsid();
        if(StringUtil.checkString(grop) && StringUtil.checkString(usid)) {
            if(cbase010MO.queryByCnd(Cnd.where("grop","=",grop).and("usid","=",usid)).size() == 0) {
                cbase010VO = cbase010MO.insert(cbase010VO);
                if (cbase010VO != null) {
                    code = 1;
                }
            }else{
                msg = "当前用户已存在，不可重复添加";
            }
        }else{
            msg = "请求途径不合法！";
        }
        msg = code == 1 ?"添加用户成功":msg;
        return ResultUtil.getResult(code, msg, null);
    }

    /**
     * Delete grop user map.
     *
     * @param grop  团队ID
     * @param usid  用户ID
     * @param usids 用户ID集合
     * @return 标注的数据请求返回格式
     * @description 通过goid,usid删除团队用户.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:06.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteGropUser(@Param("grop")String grop,@Param("usid")String usid,@Param("::list")String[] usids){
        Integer code = 0;
        String msg = "删除失败";
        if(StringUtil.checkString(grop)){
            Cbase009VO cbase009VO = cbase009MO.fetchByGrop(grop);
            if(cbase009VO != null) {
                if (StringUtil.checkString(usid)) {
                    code = cbase010MO.delete(new Cbase010VO(usid,grop));
                } else if (usids != null) {
                    for (String USID : usids) {
                        code = cbase010MO.delete(new Cbase010VO(USID, grop));
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
        msg = code == 1?"删除用户成功":msg;
        return ResultUtil.getResult(code, msg, null);
    }

}
