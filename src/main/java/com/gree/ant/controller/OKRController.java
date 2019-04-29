package com.gree.ant.controller;

import com.gree.ant.exception.KellyException;
import com.gree.ant.mo.BussMoFactory;
import com.gree.ant.util.MailUtil;
import com.gree.ant.util.OKRUtil;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.util.excel.OKRExcel;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.enumVO.ResultEnum;
import com.gree.ant.vo.request.OkrVO;
import jxl.write.WriteException;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private BussMoFactory bussMoFactory;

    @At("/index")
    @Ok("jsp:jsp.okr.index")
    public String index(){
        return "success";
    }

    @At("/manage")
    @Ok("jsp:jsp.okr.manager")
    public Integer manage(@Attr("user")Cbase000VO cbase000VO){
        return cbase000VO.getSTA2();
    }

    @At("/insert")
    @Ok("jsp:jsp.okr.insert")
    public Map<String, Object> insert(@Param("isManager")Boolean isManager, @Attr("user")Cbase000VO cbase000VO){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("user",cbase000VO);
        resultMap.put("isManager",isManager);
        return resultMap;
    }

    @At("/taskChoose")
    @Ok("jsp:jsp.okr.taskChoose")
    public String taskChoose(@Param("id")String id){
        return id;
    }

    @At("/read")
    @Ok("jsp:jsp.okr.read")
    public Tbuss011VO read(@Param("okid")Integer okid){
        return bussMoFactory.getTbuss011MO().fetchByOkid(okid);
    }

    @At("/edit")
    @Ok("jsp:jsp.okr.edit")
    public Map<String, Object> edit(@Param("okid")Integer okid, @Param("isManager")Boolean isManager){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("okr",bussMoFactory.getTbuss011MO().fetchByOkid(okid));
        resultMap.put("isManager",isManager);
        return resultMap;
    }

    @At("/mark")
    @Ok("jsp:jsp.okr.mark")
    public Tbuss011VO mark(@Param("okid")Integer okid){
        return bussMoFactory.getTbuss011MO().fetchByOkid(okid);
    }

    @POST
    @At("/getSingleOkr")
    @Ok("json")
    public Map<String,Object> getSingleOkr(@Param("okid") Integer okid){
        int code = 0;
        String msg = "服务器异常";
        OkrVO okrVO = new OkrVO();
        if(okid != null){
            Tbuss011VO tbuss011VO = bussMoFactory.getTbuss011MO().fetchTransByOkid(okid);
            okrVO = OKRUtil.convertToOkr(tbuss011VO);
            code = 1;
        }else{
            msg = "请求参数为空，请确认路径！";
        }
        msg = code == 1 ? "成功添加OKR管理项":msg;
        return ResultUtil.getResult(code,msg,okrVO);
    }

    /**
     * @param okrVO
     * @return 返回标准的响应结果集
     * @description 插入单条OKR记录
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @POST
    @At("/insert")
    @Ok("json")
    public Map<String,Object> insert(@Param("::") OkrVO okrVO){
        int code = 0;
        String msg = "服务器异常";
        OKRUtil okrUtil = new OKRUtil(okrVO);
        if(okrUtil.getMsg() == null) {
            Tbuss011VO t11VO = okrUtil.getT11();
            t11VO.setStat(0);
            Tbuss011VO tbuss011VO = bussMoFactory.getTbuss011MO().insert(t11VO,okrUtil.getT12());
            if(tbuss011VO.getOKID() != null){
                code = 1;
            }
        }else{
            msg = okrUtil.getMsg();
        }
        msg = code == 1 ? "成功添加OKR管理项":msg;
        return ResultUtil.getResult(code,msg,"");
    }

    @POST
    @At("/update")
    @Ok("json")
    public Map<String, Object> update(@Param("::") OkrVO okrVO, @Param("okid")Integer okid){
        int code = 0;
        String msg = "服务器异常";
        OKRUtil okrUtil = new OKRUtil(okrVO);
        if(okrUtil.getMsg() == null && okid != null){
            Tbuss011VO tbuss011VO = okrUtil.getT11();
            tbuss011VO.setOKID(okid);
            tbuss011VO.setStat(0);
//            tbuss011VO.setStat(bussMoFactory.getTbuss011MO().fetchByOkid(tbuss011VO.getOKID()).getStat()); TODO 2018-11-17 晓燕姐提出需求待定状态
            code = bussMoFactory.getTbuss011MO().update(tbuss011VO,okrUtil.getT12());
        }else{
            msg = okrUtil.getMsg();
        }
        msg = code == 1 ? "成功修改OKR管理项":msg;
        return ResultUtil.getResult(code,msg,"");
    }


    @At("/mark")
    @Ok("json")
    @POST
    public Map<String, Object> mark(@Param("::list") List<Map<String,Object>> scores){
        int code = 0;
        String msg = "服务器异常";
        if(scores != null){
            bussMoFactory.getTbuss013MO().markTask(scores);
            code = 1;
        }else{
            msg = "评分项为空，请重新评分！";
        }
        msg = code == 1 ? "成功评分OKR管理项":msg;
        return ResultUtil.getResult(code,msg,"");
    }

    @At("/delete")
    @Ok("json")
    @POST
    public Map<String, Object> delete(@Param("::list")Integer[] okids){
        int code = 0;
        String msg = "选中OKR没有可以删除的";
        if(okids != null){
            code = bussMoFactory.getTbuss011MO().delete(okids);
        }else{
            msg = "请选择目标管理项";
        }
        msg = code == 1 ? "成功删除OKR管理项":msg;
        return ResultUtil.getResult(code,msg,"");
    }


    /**
     * @param pageNumber 请求的页码
     * @param pageSize   请求页的大小
     * @param msg        请求时提供的过滤信息
     * @param mdat       筛选的日期
     * @param acco       筛选的科室字段
     * @param cbase000VO 用户信息
     * @return 标准的表格请求结果集
     * @description 管理员对OKR表的所有数据进行查询
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At("/mQueryAllOKR")
    @Ok("json")
    public Map<String,Object> mQueryAllOKR(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,
                                          @Param("msg")String msg,@Param("mdat")String mdat,@Param("acco")String acco,
                                           @Attr("user")Cbase000VO cbase000VO){
        QueryResult queryResult = bussMoFactory.getTbuss011MO().mQueryAllByMsgPager(pageSize,pageNumber,cbase000VO,msg,mdat,acco);
        return  TableUtil.makeJson(1,"",queryResult.getPager().getRecordCount(),queryResult.getList(Tbuss011VO.class));
    }

    /**
     * @param pageNumber 请求的页码
     * @param pageSize   请求页的大小
     * @param msg        请求时提供的过滤信息
     * @param usid       当前会话的用户ID
     * @return 标准的表格请求结果集
     * @description 用户对OKR表的所有数据进行查询
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At("/uQueryAllOKR")
    @Ok("json")
    public Map<String,Object> uQueryAllOKR(@Param("page")Integer pageNumber,@Param("limit")Integer pageSize,
                                           @Param("msg")String msg,@Param("mdat")String mdat,@Attr("usid")String usid){
        Integer count = bussMoFactory.getTbuss011MO().countByMsg(msg,usid,mdat);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        List<Tbuss011VO> tbuss011VOList = bussMoFactory.getTbuss011MO().uQueryAllByMsgPager(pager,usid,msg,mdat);
        return  TableUtil.makeJson(1,"",count,tbuss011VOList);
    }

    @At
    @Ok("void")
    public void outPutOkr(@Param("okid")Integer okid, HttpServletRequest request, HttpServletResponse response){
        Tbuss011VO tbuss011VO = bussMoFactory.getTbuss011MO().fetchTransByOkid(okid);
        try {
            OKRExcel.export(tbuss011VO,request,response);
        } catch (IOException | WriteException e) {
            throw new KellyException(ResultEnum.EXCEL_ERROR);
        }
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> pushToLeader(@Param("::list")Integer[] okids){
        bussMoFactory.getTbuss011MO().pushToLeader(okids);
        for(Integer okid : okids){
            Tbuss011VO tbuss011VO = bussMoFactory.getTbuss011MO().fetchByOkid(okid);
            MailUtil.sendPushmail(tbuss011VO,tbuss011VO.getBOSS());
        }
        return ResultUtil.getResult(1,"提交成功",null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object>copyOkr(@Param("::list")Integer[] okids){
        bussMoFactory.getTbuss011MO().copyOkr(okids);
        return ResultUtil.getResult(1,"复制成功",null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> backToUser(@Param("::list")Integer[] okids){
        bussMoFactory.getTbuss011MO().backToUser(okids);
        for(Integer okid : okids){
            Tbuss011VO tbuss011VO = bussMoFactory.getTbuss011MO().fetchByOkid(okid);
            MailUtil.sendBackmail(tbuss011VO,tbuss011VO.getASID());
        }
        return ResultUtil.getResult(1,"驳回成功",null);
    }
}
