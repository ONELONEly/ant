package com.gree.ant.controller;

import com.gree.ant.mo.BussMoFactory;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;
import com.gree.ant.vo.Tbuss013VO;
import com.gree.ant.vo.request.GoalVO;
import com.gree.ant.vo.request.OkrVO;
import com.gree.ant.vo.request.TaskVO;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import java.util.ArrayList;
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

    @At("/insert")
    @Ok("jsp:jsp.okr.insert")
    public String insert(){
        return "success";
    }

    @At("/taskChoose")
    @Ok("jsp:jsp.okr.taskChoose")
    public String taskChoose(@Param("id")String id){
        return id;
    }

    @At("/read")
    @Ok("jsp:jsp.okr.read")
    public String read(@Param("okid")Integer okid,@Attr("usid")String usid){
        return usid;
    }

    @At("/edit")
    @Ok("jsp:jsp.okr.edit")
    public String edit(@Param("okid")Integer okid,@Attr("usid")String usid){
        return usid;
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
        if(okrVO != null) {
            Tbuss011VO tbuss011VO = okrVO.getOkrManager();
            List<GoalVO> goalVOS = okrVO.getGoals();
            List<Tbuss012VO> tbuss012VOS = new ArrayList<>();
            if(goalVOS != null){
                for(GoalVO goalVO:goalVOS){
                    Tbuss012VO tbuss012VO = goalVO.getFormatGoal();
                    List<Tbuss013VO> tbuss013VOList = new ArrayList<>();
                    for (TaskVO taskVO:goalVO.getTasks()){
                        Tbuss013VO tbuss013VO = taskVO.formatTask();
                        tbuss013VOList.add(tbuss013VO);
                    }
                    tbuss012VO.setTbuss013VOS(tbuss013VOList);
                    tbuss012VOS.add(tbuss012VO);
                }
               tbuss011VO =  bussMoFactory.getTbuss011MO().insert(tbuss011VO,tbuss012VOS);
                if(tbuss011VO.getOKID() != null){
                    code = 1;
                }
            }else{
                msg = "OKR目标为空，请录入有效目标！";
            }
        }else{
            msg = "请求参数为空，请录入有效数据！";
        }
        msg = code == 1 ? "成功添加OKR管理项":msg;
        return ResultUtil.getResult(code,msg,"");
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
        Integer count = bussMoFactory.getTbuss011MO().countByMsg(msg);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        List<Tbuss011VO> tbuss011VOList = bussMoFactory.getTbuss011MO().queryAllByMsgPager(pager,msg);
        return  TableUtil.makeJson(1,"",count,tbuss011VOList);
    }


}
