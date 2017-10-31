package com.gree.ant.controller;


import com.gree.ant.mo.Cbase006MO;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase006VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
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
 * The type Dept controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 部门表的Controller
 * @title DeptController
 * @createTime 2017 :10:10 09:10:19.
 */
@At("/dept")
@IocBean
public class DeptController {

    @Inject("cbase006MO")
    private Cbase006MO cbase006MO;

    /**
     * Index string.
     *
     * @return the string
     * @description 部门管理的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:59.
     */
    @At
    @Ok("jsp:jsp.department.manage")
    public String index(){
        return "success";
    }

    /**
     * Query all dept map.
     *
     * @return 以Table所需格式输出
     * @description 查询部门的信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:05.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllDept(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String key){
        Condition cnd = null;
        if(key != null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("comp","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        List<Cbase006VO> cbase006VOList = cbase006MO.queryAllByCnd(cnd,pager);
        Integer count = cbase006MO.countByCnd(cnd);
        return TableUtil.makeJson(0,"",count,cbase006VOList);
    }

    /**
     * Insert dept map.
     *
     * @return 标准的请求输出
     * @description 添加单条部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:08.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertDept(@Param("..")Cbase006VO cbase006VO){
        String dept = cbase006VO.getDept();
        String dsca = cbase006VO.getDsca();
        String comp = cbase006VO.getComp();
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(dept,dsca,comp) && dept.length()==6){
            if(!cbase006MO.insertCheck(dept,dsca)){
                cbase006MO.insertByVO(cbase006VO);
                code = 1;
            }else{
                msg = "'当前部门ID或部门名陈已存在！";
            }
        }else{
            msg = "请确认信息输入完毕并且部门ID位6位";
        }
        msg = code == 1?"插入部门成功!":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete dept map.
     *
     * @return 标准的请求输出
     * @description 删除单条或多条部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:11.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteDept(@Param("dept")String dept,@Param("::list")String[] depts){
        Integer code = 0;
        String msg = "";
        if(dept!=null){
            code = cbase006MO.deleteByDept(dept);
        }else if(depts != null){
            for (String department:depts){
                code = cbase006MO.deleteByDept(department);
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
     * @description 修改部门信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:13.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateDept(@Param("..")Cbase006VO cbase006VO){
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(cbase006VO.getDept())){
            code = cbase006MO.updateByVO(cbase006VO);
        }else{
            msg = "请求路径不规范！";
        }
        msg = code == 1?"修改成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

}
