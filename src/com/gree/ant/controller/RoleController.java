package com.gree.ant.controller;

import com.gree.ant.mo.Cbase004MO;
import com.gree.ant.mo.Cbase005MO;
import com.gree.ant.mo.Cbase007MO;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
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
 * The type Role controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 角色表的Controller
 * @title RoleController
 * @createTime 2017 :10:10 10:10:37.
 */
@At("/role")
@IocBean
public class RoleController {

    @Inject("cbase007MO")
    private Cbase007MO cbase007MO;

    @Inject("cbase004MO")
    private Cbase004MO cbase004MO;

    @Inject("cbase005MO")
    private Cbase005MO cbase005MO;

    /**
     * Index string.
     *
     * @return the string
     * @description 角色管理的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:59.
     */
    @At
    @Ok("jsp:jsp.role.manage")
    public String index(){
        return "success";
    }

    /**
     * Role permission string.
     *
     * @param roid 角色ID
     * @return the string
     * @description 角色权限编辑的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 09:10:44.
     */
    @At
    @Ok("jsp:jsp.role.rolePermission")
    public String rolePermission(@Param("roid")String roid){
        return roid;
    }

    /**
     * Role user string.
     *
     * @param roid 角色ID
     * @return the string
     * @description 角色用户入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 02:10:56.
     */
    @At
    @Ok("jsp:jsp.role.roleUser")
    public String roleUser(@Param("roid")String roid){
        return roid;
    }

    /**
     * Query all dept map.
     *
     * @return 以Table所需格式输出
     * @description 查询所有角色的信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:05.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllRole(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize, @Param("key")String key){
        Condition cnd = null;
        if(key != null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("comp","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        List<Cbase007VO> cbase007VOList = cbase007MO.queryAllByPagerCnd(pager,cnd);
        Integer count = cbase007MO.countByCnd(cnd);
        return TableUtil.makeJson(0,"",count,cbase007VOList);
    }

    /**
     * Insert dept map.
     *
     * @return 标准的请求输出
     * @description 添加单条角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:08.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertRole(@Param("..")Cbase007VO cbase006VO){
        String roid = cbase006VO.getRoid();
        String dsca = cbase006VO.getDsca();
        String comp = cbase006VO.getComp();
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(roid,dsca,comp) && roid.length()==6){
            if(!cbase007MO.insertCheck(roid,dsca)){
                cbase007MO.insertByVO(cbase006VO);
                code = 1;
            }else{
                msg = "'当前角色ID或角色名称已存在！";
            }
        }else{
            msg = "请确认信息输入完毕并且角色ID位6位";
        }
        msg = code == 1?"插入角色成功!":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete dept map.
     *
     * @return 标准的请求输出
     * @description 删除单条或多条角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:11.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRole(@Param("roid")String roid,@Param("::list")String[] roids){
        Integer code = 0;
        String msg = "";
        if(roid!=null){
            code = cbase007MO.deleteByROID(roid);
        }else if(roids != null){
            for (String roidd:roids){
                code = cbase007MO.deleteByROID(roidd);
            }
        }else{
            msg = "请选择删除的数据！";
        }
        msg = code == 1?"删除成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update dept map.
     *
     * @return 标准的请求输出
     * @description 修改角色信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:13.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateRole(@Param("..")Cbase007VO cbase007VO){
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(cbase007VO.getRoid())){
            code = cbase007MO.updateByVO(cbase007VO);
        }else{
            msg = "请求路径不规范！";
        }
        msg = code == 1?"修改成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Query all role permission map.
     *
     * @param roid 权限ID
     * @param key  过滤关键词
     * @return 标准表格MAP输出
     * @description 通过ROID,key查询当前角色所有权限
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 09:10:08.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllRolePermission(@Param("roid")String roid,@Param("key")String key){
        Cnd cnd = null;
        List<Cbase002VO> cbase002VOList = new ArrayList<>();
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("pono","like","%"+key+"%");
        }
        Cbase007VO cbase007VO = cbase007MO.fetchTransByCndROID(cnd,roid,"cbase002VOS");
        if(cbase007VO!=null){
            cbase002VOList = cbase007VO.getCbase002VOS();
        }
        return TableUtil.makeJson(0,"",null,cbase002VOList);
    }

    /**
     * Query all role user map.
     *
     * @param roid 权限ID
     * @param key  过滤关键词
     * @return 标准表格MAP输出
     * @description 通过ROID,key查询当前角色所有权限
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 02:10:02.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllRoleUser(@Param("roid")String roid,@Param("key")String key){
        Cnd cnd = null;
        List<Cbase000VO> cbase000VOList = new ArrayList<>();
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("usid","like","%"+key+"%");
        }
        Cbase007VO cbase007VO = cbase007MO.fetchTransByCndROID(cnd,roid,"cbase000VOS");
        if(cbase007VO!=null){
            cbase000VOList = cbase007VO.getCbase000VOS();
        }
        return TableUtil.makeJson(0,"",null,cbase000VOList);
    }

    /**
     * Insert role permission map.
     *
     * @param cbase004VO 角色权限实体
     * @return 标注的数据请求返回
     * @description 通过权限实体添加单个权限
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:38.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertRolePermission(@Param("..") Cbase004VO cbase004VO){
        Integer code = 0;
        String msg = "";
        String pono = cbase004VO.getPono();
        String roid = cbase004VO.getRoid();
        if(StringUtil.checkString(pono) && StringUtil.checkString(roid)) {
            if(cbase004MO.queryByCnd(Cnd.where("pono","=",pono).and("roid","=",roid))) {
                cbase004VO = cbase004MO.insert(cbase004VO);
                if (cbase004VO != null) {
                    code = 1;
                }
            }else{
                msg = "当前权限已存在，不可重复添加";
            }
        }else{
            msg = "请求途径不合法！";
        }
        msg = code == 1 ?"添加权限成功":msg;
        return ResultUtil.getResult(code, msg, null);
    }

    /**
     * Delete role permssion map.
     *
     * @param pono  菜单编号
     * @param roid  角色ID
     * @param ponos 菜单编号集合
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:40.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRolePermssion(@Param("pono")String pono,@Param("roid")String roid,@Param("::list")String[] ponos){
        Integer code = 0;
        String msg = "删除失败";
        if(StringUtil.checkString(roid)){
            Cbase007VO cbase007VO = cbase007MO.fetchByROID(roid);
            if(cbase007VO != null) {
                if (StringUtil.checkString(pono)) {
                    code = cbase004MO.delete(new Cbase004VO(pono, roid));
                } else if (ponos != null) {
                    for (String PONO : ponos) {
                        code = cbase004MO.delete(new Cbase004VO(PONO, roid));
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

    /**
     * Delete role user map.
     *
     * @param usid  用户编号
     * @param roid  角色ID
     * @param usids 用户编号集合
     * @return the map
     * @description 用一句话描述这个方法的作用.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 02:10:15.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRoleUser(@Param("roid")String roid,@Param("usid")String usid,@Param("::list")String[] usids){
        Integer code = 0;
        String msg = "删除失败";
        if(StringUtil.checkString(roid)){
            Cbase007VO cbase007VO = cbase007MO.fetchByROID(roid);
            if(cbase007VO != null) {
                if (StringUtil.checkString(usid)) {
                    Cbase005VO cbase005VO = new Cbase005VO();
                    cbase005VO.setRoid(roid);
                    cbase005VO.setUsid(usid);
                    code = cbase005MO.delete(cbase005VO);
                } else if (usids != null) {
                    for (String USID : usids) {
                        code = cbase005MO.delete(new Cbase005VO(USID, roid));
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
