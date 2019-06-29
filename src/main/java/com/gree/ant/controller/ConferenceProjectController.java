package com.gree.ant.controller;

import com.gree.ant.mo.ConferenceProjectMO;
import com.gree.ant.util.TableUtil;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import java.util.Map;

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
    public String update (@Param("project_guid")String projectGuid) {
        return "";
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> loadUserTableData (@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                                 @Attr("usid")String usid) {
        QueryResult queryResult = projectMO.loadProjectUserData(pageNumber, pageSize, usid);
        return TableUtil.makeJson(0, "成功", queryResult.getPager().getRecordCount(), queryResult.getList());
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> loadTableData (@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                                 @Attr("usid")String usid) {
        QueryResult queryResult = projectMO.loadProjectData(pageNumber, pageSize, usid);
        return TableUtil.makeJson(0, "成功", queryResult.getPager().getRecordCount(), queryResult.getList());
    }
}
