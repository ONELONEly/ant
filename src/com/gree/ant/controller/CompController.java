package com.gree.ant.controller;


import com.gree.ant.mo.Cbase001MO;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase001VO;
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
 * The type Comp controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 公司的Controller
 * @title CompController
 * @createTime 2017 :09:29 10:09:15.
 */
@At("/comp")
@IocBean
public class CompController {

    @Inject("cbase001MO")
    private Cbase001MO cbase001MO;

    /**
     * Index string.
     *
     * @return the string
     * @description 公司主页的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:15.
     */
    @At
    @Ok("jsp:jsp.company.manage")
    public String index(){
        return "success";
    }

    /**
     * Query all comp map.
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
    public Map<String,Object> queryAllComp(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,@Param("key")String key){
        Cnd cnd = null;
        if(key!=null){
            cnd = Cnd.where("dsca","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        Integer count = cbase001MO.countByCnd(cnd);
        List<Cbase001VO> cbase001VOList = cbase001MO.queryAllByCndPager(cnd,pager);
        return TableUtil.makeJson(0,"",count,cbase001VOList);
    }

    /**
     * Delete comp map.
     *
     * @param comp  公司编号
     * @param comps 公司编号的数组
     * @return the map
     * @description 删除公司
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 11:09:27.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteComp(@Param("comp")String comp,@Param("::list")String[] comps){
        Integer code = 0;
        String msg = "此功能未开放";
        if(StringUtil.checkString(comp)){
            code = cbase001MO.deleteByComp(comp);
        }else if(comps.length!=0){
            for (String company:comps){
                code = cbase001MO.deleteByComp(company);
            }
        }else{
            msg = "请选中要删除的项！";
        }
        msg = code == 1?"删除成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Insert comp map.
     *
     * @param comp the comp
     * @param dsca the dsca
     * @return the map
     * @description 插入公司
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 05:09:32.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertComp(@Param("comp")String comp,@Param("dsca")String dsca){
        Integer code = 0;
        String msg = "此功能未开放";
        if(StringUtil.checkString(dsca) && StringUtil.checkString(comp) && comp.length()==3){
            if(cbase001MO.fetchByComp(comp) == null) {
                cbase001MO.insert(new Cbase001VO(comp, dsca));
                code = 1;
            }else{
                msg = "公司已存在！请确认后输入！";
            }
        }else{
            msg = "请确认输入参数正确！公司编号为3位";
        }
        msg = code == 1?"添加公司成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Update acco map.
     *
     * @param cbase001VO the cbase 001 vo
     * @return the map
     * @description 修改公司
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 05:09:44.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateComp(@Param("..")Cbase001VO cbase001VO){
        Integer code = 0;
        String msg = "此功能未开放";
        if (StringUtil.checkString(cbase001VO.getComp()) && StringUtil.checkString(cbase001VO.getDsca())){
            code = cbase001MO.updateByVO(cbase001VO);
        }else{
            msg = "请求错误";
        }
        msg = code == 1?"修改公司成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }
}
