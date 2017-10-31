package com.gree.ant.controller;


import com.gree.ant.mo.Cbase002MO;
import com.gree.ant.mo.Cbase003MO;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase002VO;
import com.gree.ant.vo.Cbase003VO;
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
 * The type Menu controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 菜单的Controller.Cbase002、Cbase003
 * @title MenuController
 * @createTime 2017 :10:08 10:10:53.
 */
@At("/menu")
@IocBean
public class MenuController {

    @Inject("cbase002MO")
    private Cbase002MO cbase002MO;

    @Inject("cbase003MO")
    private Cbase003MO cbase003MO;


    /**
     * Index string.
     *
     * @return the string
     * @description 菜单管理的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 10:10:15.
     */
    @At
    @Ok("jsp:jsp.menu.manage")
    public String index(){
        return "success";
    }

    /**
     * Query all menu map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @param key        筛选字段
     * @return 标准的表格请求输出
     * @description 查询所有菜单
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 10:10:30.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllMenu(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize, @Param("key")String key){
        Condition cnd = null;
        if(key != null){
            cnd = Cnd.where("dsca","like","%"+key+"%").or("pono","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        List<Cbase002VO> cbase002VOList = cbase002MO.queryAllByCndPager(cnd,pager);
        Integer count = cbase002MO.countByCnd(cnd);
        return TableUtil.makeJson(0,"",count,cbase002VOList);
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
    public Map<String,Object> insertMenu(@Param("..")Cbase002VO cbase002VO,@Param("flno")String flno){
        Integer code = 0;
        String msg = "";
        String pono = cbase002VO.getPono();
        String dsca = cbase002VO.getDsca();
        Integer styp = cbase002VO.getStyp();
        if(StringUtil.checkString(flno) && StringUtil.checkString(dsca) && flno.length()==3) {
            if (cbase002MO.fetchByPONO(flno) == null) {
                cbase002VO.setPono(flno);
                if (styp == 2) {
                    if (StringUtil.checkString(pono)) {
                        cbase002MO.insert(cbase002VO);
                        cbase003MO.insert(new Cbase003VO(flno,pono));
                        code = 1;
                    }else{
                        msg = "请选择父节点！";
                    }
                }else{
                    cbase002MO.insert(cbase002VO);
                    code = 1;
                }
            }else{
                msg = "当前菜单号已存在，请重新输入！";
            }
        }else{
            msg = "请求参数输入不完整，请确认已输入所有参数！";
        }
        msg = code == 1?"成功插入菜单！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete dept map.
     *
     * @return 标准的请求输出
     * @description 删除单条或多条菜单信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:11.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteMenu(@Param("pono")String pono,@Param("::list")String[] ponos){
        Integer code = 0;
        String msg = "删除失败";
        if(pono != null) {
            code = cbase002MO.deleteByPONO(pono);
            if(cbase003MO.fetchByFLNO(pono) != null) {
                code = cbase003MO.deleteByFLNO(pono);
            }
        }else if(ponos != null){
            for (String PONO:ponos){
                code = cbase002MO.deleteByPONO(PONO);
                if(cbase003MO.fetchByFLNO(PONO) != null) {
                    cbase003MO.deleteByFLNO(PONO);
                }
            }
        }else{
            msg = "请求参数为空";
        }
        msg = code == 0?msg:"删除成功";
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
    public Map<String,Object> updateMenu(@Param("..")Cbase002VO cbase002VO){
        Integer code = 0;
        String msg = "";
        if(StringUtil.checkString(cbase002VO.getPono())){
            if(cbase002VO.getStyp()==1 && cbase002VO.getPurl()!=null) {
                msg = "二级菜单没有链接地址！";
            }else{
                code = cbase002MO.updateByVO(cbase002VO);
            }
        }else{
            msg = "请求路径不规范！";
        }
        msg = code == 1?"修改成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }
}
