package com.gree.ant.controller;

import com.gree.ant.mo.Tbuss011MO;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Tbuss011VO;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理Controller
 */
@At("/okr")
@IocBean
public class OKRController {

    @Inject
    private Tbuss011MO tbuss011MO;

    @At("/index")
    @Ok("jsp:jsp.okr.index")
    public String index(){
        return "success";
    }

    @At("/insert")
    @Ok("jsp:jsp.okr.insert")
    public String insert(){
        return "success";
    }

    /**
     * @param tbuss011VO 单条OKR记录
     * @return 返回标准的响应结果集
     * @description 插入单条OKR记录
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @POST
    @At("/insert")
    @Ok("json")
    public Map<String,Object> insert(@Param("..")Tbuss011VO tbuss011VO){
        Map<String,Object> resultMap = new HashMap<>();
        int code = 0;
        String msg = "插入失败";
        tbuss011VO = tbuss011MO.insert(tbuss011VO);
        if(tbuss011VO.getASID() != null){
            code = 1;
        }
        msg = code == 1?"成功插入OKR记录":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * @param pageNumber 请求的页码
     * @param pageSize 请求页的大小
     * @param msg 请求时提供的过滤信息
     * @return 标准的表格请求结果集
     * @description 对OKR表的所有数据进行查询
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At("/queryAllOKR")
    @Ok("json")
    public Map<String,Object> queryAllOKR(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,
                                          @Param("msg")String msg){
        Integer count = tbuss011MO.countByMsg(msg);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        List<Tbuss011VO> tbuss011VOList = tbuss011MO.queryAllByMsgPager(pager,msg);
        return  TableUtil.makeJson(1,"",count,tbuss011VOList);
    }


}
