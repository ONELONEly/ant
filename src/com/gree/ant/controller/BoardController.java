package com.gree.ant.controller;

import com.gree.ant.mo.Tbuss003MO;
import com.gree.ant.mo.Tbuss004MO;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.vo.Tbuss004VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Board controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 看板的Controller
 * @title BoardController
 * @createTime 2017 :09:29 10:09:44.
 */
@At("/board")
@IocBean
public class BoardController {


    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject("refer:tbuss004MO")
    private Tbuss004MO tbuss004MO;


    @At
    @Ok("jsp:jsp.board.manage")
    public Map<String, Object> manage(){
        return null;
    }

    /**
     * User string.
     *
     * @return the string
     * @description 用户看板的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:44.
     */
    @At
    @Ok("jsp:jsp.board.user")
    public String user(){
        return "success";
    }

    /**
     * Log nut map.
     *
     * @param taid the taid
     * @return the nut map
     * @description 看板日志的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:44.
     */
    @At
    @Ok("jsp:jsp.board.task")
    public NutMap log(@Param("taid")String taid){
        return new NutMap("taid",taid);
    }


    /**
     * Query all log map.
     *
     * @param taid the taid
     * @return the map
     * @description 查询所有日志数据的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:29 10:09:44.
     */
    @At
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> queryAllLog(@Param("taid")String taid){
        Integer code = 0;
        String msg = "请以正常步骤访问";
        List<Tbuss004VO> tbuss004VOS = new ArrayList<>();
        if(taid !=null){
            Condition cnd = Cnd.where("taid","=",taid);
             tbuss004VOS = tbuss004MO.queryAllByCnd(cnd,null);
            code = 1;
            msg = "查询成功";
        }
        return ResultUtil.getResult(code,msg,tbuss004VOS);
    }
}
