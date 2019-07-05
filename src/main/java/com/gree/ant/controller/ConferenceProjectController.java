package com.gree.ant.controller;

import com.gree.ant.mo.ConferenceProjectMO;
import com.gree.ant.util.DateUtil;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.SnowFlake;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.ConferenceProject;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 会议项目的Controller
 * @createTime 2019 -06-26 17:17:49
 */
@At("/conferenceProject")
@IocBean
public class ConferenceProjectController {

    @Inject
    private ConferenceProjectMO projectMO;

    @At
    @Ok("jsp:jsp.conference.project.add")
    public String add () {
        return "";
    }

    @At
    @Ok("jsp:jsp.conference.project.index")
    public String index () {
        return "";
    }

    @At
    @Ok("jsp:jsp.conference.project.update")
    public ConferenceProject update (@Param("project_guid")String projectGuid) {
        ConferenceProject conferenceProject = projectMO.fetchProject(projectGuid);
        conferenceProject.setStartDateTxt(DateUtil.formDateToString(conferenceProject.getStartDate()));
        conferenceProject.setOnlineDateTxt(DateUtil.formDateToString(conferenceProject.getOnlineDate()));
        return conferenceProject;
    }


    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertData (@Param("..") ConferenceProject project,
                                          @Param("::follower")String[] follower,@Attr("usid")String usid) {
        project.setProjectGuid(SnowFlake.get().nextId()+"");
        project.setCreator(usid);
        project.setModifyUser(usid);
        project.setModifyDate(LocalDateTime.now());
        project = projectMO.insertProject(project, follower);
        Integer code = project == null ? 0 : 1;
        String msg = code == 1 ? "添加成功" : "服务器异常";
        return ResultUtil.getResult(code, msg, null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> updateData (@Param("..") ConferenceProject project,
                                          @Param("::follower")String[] follower,@Attr("usid")String usid) {
        ConferenceProject conferencePro = projectMO.fetchProject(project.getProjectGuid());
        conferencePro.setModifyUser(usid);
        conferencePro.setModifyDate(LocalDateTime.now());
        conferencePro.setTitle(project.getTitle());
        conferencePro.setStartDate(project.getStartDate());
        conferencePro.setOnlineDate(project.getOnlineDate());
        conferencePro.setOrderNumber(project.getOrderNumber());
        conferencePro.setState(project.getState());
        boolean updateStatus = projectMO.updateProject(conferencePro, follower);
        Integer code = updateStatus ? 1 : 0;
        String msg = code == 1 ? "更新成功" : "服务器异常";
        return ResultUtil.getResult(code, msg, null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> fetchData (@Param("projectGuid")String projectGuid) {
        ConferenceProject conferencePro = projectMO.fetchProject(projectGuid);
        if (conferencePro != null) {
            conferencePro.setStartDateTxt(DateUtil.formDateToString(conferencePro.getStartDate()));
            conferencePro.setOnlineDateTxt(DateUtil.formDateToString(conferencePro.getOnlineDate()));
        }
        Integer code = conferencePro != null ? 1 : 0;
        String msg = code == 1 ? "查询成功" : "服务器异常";
        return ResultUtil.getResult(code, msg, conferencePro);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteData (@Param("::list")String[] projectGuids,@Param("projectGuid")String projectGuid) {
        AtomicReference<String> msg = new AtomicReference<>("删除成功");
        Trans.exec((Atom) ()->{
            if ( projectGuid != null) {
                projectMO.deleteByName(projectGuid);
            } else if (projectGuids != null) {
                for (String projectId : projectGuids) {
                    projectMO.deleteByName(projectId);
                }
            }else{
                msg.set("传入参数为空");
            }
        });
        int code = msg.get().equals("删除成功") ? 1 : 0;
        return ResultUtil.getResult(code, msg.get(),null);
    }

    @At
    @POST
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> loadUserTableData (@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                                 @Attr("usid")String usid) {
        QueryResult queryResult = projectMO.loadProjectUserData(pageNumber, pageSize, usid);
        return TableUtil.makeJson(0, "成功", queryResult.getPager().getRecordCount(), queryResult.getList());
    }

    @At
    @POST
    @Ok("json:{dateFormat:'yyyy-MM-dd'}")
    public Map<String,Object> loadTableData (@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                                 @Attr("user") Cbase000VO cbase000VO) {
        QueryResult queryResult = projectMO.loadProjectData(pageNumber, pageSize, cbase000VO.getACCO());
        return TableUtil.makeJson(0, "成功", queryResult.getPager().getRecordCount(), queryResult.getList());
    }
}
