package com.gree.ant.controller;

import com.gree.ant.mo.BussMoFactory;
import com.gree.ant.util.*;
import com.gree.ant.vo.Tbuss014VO;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import java.sql.Clob;
import java.util.Date;
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
@At("/require")
@IocBean
public class RequireController {

    @Inject
    private BussMoFactory bussMoFactory;

    /**
     * Index string.
     *
     * @return the string
     * @description 需求指派界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.index")
    public String index(){
        return "success!";
    }

    /**
     * @return TODO
     * @description 个人需求入口处
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At
    @Ok("jsp:jsp.require.user")
    public String user(){
        return "success!";
    }

    /**
     * Insert string.
     *
     * @return the string
     * @description 添加用户需求界面
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.insert")
    public String insert(){
        return "JK"+ FileUtil.getRandomName();
    }

    /**
     * Modify map.
     *
     * @param raid the raid
     * @return the map
     * @description 修改用户需求界面
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.modify")
    public Map<String,Object> modify(@Param("raid")String raid){
        HashMap<String,Object> map = new HashMap<>();
        Tbuss014VO tbuss014VO = bussMoFactory.getTbuss014MO().fetchByRaid(raid);
        map.put("note",FileUtil.convertClob(tbuss014VO.getNote()));
        tbuss014VO.setNote(null);
        map.put("ydat", DateUtil.formatYMDHMSDate(tbuss014VO.getYdat()));
        map.put("task",tbuss014VO);
        return map;
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
    @At
    @Ok("jsp:jsp.require.manage")
    public String manage(){
        return "success!";
    }

    /**
     * Query user require map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @param key        过滤管检测
     * @return 标准的表格请求结果集合
     * @description 获得个人用户的所有需求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("json:{dateFormat:'yy-MM-dd hh:MM:ss'}")
    public Map<String,Object> queryUserRequire(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                               @Param("stat")Integer stat,@Param("key")String key,@Attr("usid")String usid){
        QueryResult queryResult = bussMoFactory.getTbuss014MO().queryAllByMsg(pageNumber,pageSize,key,stat,null,usid);
        return TableUtil.makeJson(0,"",queryResult.getPager().getRecordCount(),queryResult.getList(Tbuss014VO.class));
    }

    /**
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @param key        过滤管检测
     * @return 标准的表格请求结果集合
     * @description 获得个人用户下发的所有需求
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At
    @Ok("json:{dateFormat:'yy-MM-dd hh:MM:ss'}")
    public Map<String,Object> queryDownRequire(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                               @Param("key")String key,@Param("stat")Integer stat,@Attr("usid")String usid){
        QueryResult queryResult = bussMoFactory.getTbuss014MO().queryAllByMsg(pageNumber,pageSize,key,stat,usid,null);
        return TableUtil.makeJson(0,"",queryResult.getPager().getRecordCount(),queryResult.getList(Tbuss014VO.class));
    }


    /**
     * Query all require map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @param key        the key
     * @return 标准的表格请求结果集合
     * @description 获得所有用户需求的结果集
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("json:{dateFormat:'yy-MM-dd hh:MM:ss'}")
    public Map<String,Object> queryAllRequire(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                              @Param("stat")Integer stat,@Param("key")String key){
        QueryResult queryResult = bussMoFactory.getTbuss014MO().queryAllByMsg(pageNumber,pageSize,key,stat,null,null);
        return TableUtil.makeJson(0,"",queryResult.getPager().getRecordCount(),queryResult.getList(Tbuss014VO.class));
    }

    /**
     * Insert require map.
     *
     * @param tbuss014VO 需求实体
     * @param edit       用户需求的内容
     * @return 标准的数据请求结果集合
     * @description 插入单条用户需求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertRequire(@Param("..") Tbuss014VO tbuss014VO, @Param("edit")String edit, @Attr("usid")String usid){
        String msg = "系统异常";
        int code = 0;
        if(StringUtil.checkString(tbuss014VO.getRaid(),tbuss014VO.getCsid(),tbuss014VO.getTitl()) && StringUtil.checkString(edit)){
            Clob note = FileUtil.formatClobByString(edit);
            tbuss014VO.setNote(note);
            tbuss014VO.setStat(0);
            tbuss014VO.setUsid(usid);
            tbuss014VO.setCdat(new Date());
            tbuss014VO = bussMoFactory.getTbuss014MO().insertByVO(tbuss014VO);
            if(tbuss014VO != null){
                MailUtil.sendRequirePushmail(tbuss014VO,tbuss014VO.getCsid());
                code = 1;
            }
        }else{
            msg = "请确认您已经输入了所有信息！";
        }
        msg = code == 1?"添加成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete require map.
     *
     * @param raid  需求ID
     * @return 标准的数据请求结果集合
     * @description 删除用户需求.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRequire(@Param("raid")String raid,@Param("::list")String[] raids){
        String msg = "";
        Integer code = 0;
        if(StringUtil.checkString(raid)){
            code = bussMoFactory.getTbuss014MO().deleteByRaid(raid);
        }else if(raids != null){
            code = bussMoFactory.getTbuss014MO().deleteByRaids(raids);
        }else{
            msg = "请求参数为空！";
        }
        msg = code == 1?"删除成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Edit require map.
     *
     * @param edit       用户需求的内容
     * @return 标准的数据请求结果集合
     * @description 修改用户需求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> editRequire(@Param("..")Tbuss014VO tbuss014VO,@Param("edit")String edit,@Attr("usid")String usid){
        String msg = "服务器异常";
        Integer code = 0;
        String raid = tbuss014VO.getRaid();
        if(StringUtil.checkString(raid) && StringUtil.checkString(tbuss014VO.getSyno(),tbuss014VO.getTitl(),tbuss014VO.getCsid())) {
            Tbuss014VO tbuss014VO1 = bussMoFactory.getTbuss014MO().fetchByRaid(raid);
            tbuss014VO.setNote(FileUtil.formatClobByString(edit));
            tbuss014VO.setStat(0);
            tbuss014VO.setUsid(usid);
            tbuss014VO.setCdat(tbuss014VO1.getCdat());
            code = bussMoFactory.getTbuss014MO().updateByVO(tbuss014VO);
        }else{
            msg = "请确认您已经输入了所有信息！";
        }
        msg = code ==1?"修改成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Copy task map.
     *
     * @param raids 需求ID集合
     * @return 标准的数据请求结果集合
     * @description 复制用户需求.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> copyTask(@Param("::list")String[] raids,@Attr("usid")String usid){
        String msg = "服务器异常";
        int code = 0;
        if(raids!=null){
            bussMoFactory.getTbuss014MO().copyByRaids(raids,usid);
            code = 1;
        }else{
            msg = "请求参数为空！请选中需求！";
        }
        msg = code == 1?"复制成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * @param raids
     * @return TODO
     * @description 驳回需求
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> backRequire(@Param("::list")String[] raids){
        String msg = "服务器异常";
        int code = 0;
        if(raids!=null){
            bussMoFactory.getTbuss014MO().backByRaids(raids);
            code = 1;
        }else{
            msg = "请求参数为空！请选中需求！";
        }
        msg = code == 1?"驳回成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

}
