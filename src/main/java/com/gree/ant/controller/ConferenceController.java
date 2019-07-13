package com.gree.ant.controller;

import com.gree.ant.exception.KellyException;
import com.gree.ant.mo.ConferenceMO;
import com.gree.ant.util.*;
import com.gree.ant.vo.Conference;
import com.gree.ant.vo.enumVO.ResultEnum;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@At("/conference")
@IocBean
public class ConferenceController {

    @Inject
    private ConferenceMO conferenceMO;


    @At
    @Ok("jsp:jsp.conference.index")
    public Integer index () {
        return DateUtil.getWeek() - 1;
    }

    @At
    @Ok("jsp:jsp.conference.add")
    public Conference add () {
        return new Conference();
    }

    @At
    @Ok("jsp:jsp.conference.add")
    public Conference copy (@Param("conference")String conference) {
        FileUtil fileUtil = FileUtil.createFileUtil();
        Conference conferenceVO = conferenceMO.fetchData(conference);
        conferenceVO.setNowWeekScheduleTxt(fileUtil.convertClob(conferenceVO.getNowWeekSchedule()).replaceAll("<br/>","\n"));
        conferenceVO.setNowWeekSchedule(null);
        conferenceVO.setPreWeekDone(null);
        conferenceVO.setOthers(null);
        return conferenceVO;
    }

    @At
    @Ok("jsp:jsp.conference.update")
    public Conference update (@Param("conference")String conference) {
        FileUtil fileUtil = FileUtil.createFileUtil();
        Conference conferenceVO = conferenceMO.fetchData(conference);
        conferenceVO.setPreWeekDoneTxt(fileUtil.convertClob(conferenceVO.getPreWeekDone()).replaceAll("<br/>","\n"));
        conferenceVO.setNowWeekScheduleTxt(fileUtil.convertClob(conferenceVO.getNowWeekSchedule()).replaceAll("<br/>","\n"));
        conferenceVO.setOthersTxt(fileUtil.convertClob(conferenceVO.getOthers()).replaceAll("<br/>","\n"));
        conferenceVO.setNowWeekSchedule(null);
        conferenceVO.setPreWeekDone(null);
        conferenceVO.setOthers(null);
        return conferenceVO;
    }

    @At
    @Ok("jsp:jsp.conference.detail")
    public Conference detail (@Param("conference")String conference) {
        Conference conferenceVO = conferenceMO.fetchData(conference);
        conferenceVO.setNowWeekSchedule(null);
        conferenceVO.setPreWeekDone(null);
        conferenceVO.setOthers(null);
        return conferenceVO;
    }

    @At
    @Ok("jsp:jsp.conference.show")
    public Conference show () {
        return null;
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertData (@Param("..")Conference conference,
        @Param("preWeekDoneTxt")String preWeekDone,@Param("nowWeekScheduleTxt")String nowWeekSchedule
        ,@Param("othersTxt")String othersTxt,@Attr("usid")String usid) {
        if(conferenceMO.checkByWeekProjectId(DateUtil.getWeek(),conference.getProjectGuid())) {
            conference.setConference(SnowFlake.get().nextId() + "");
            FileUtil fileUtil = FileUtil.createFileUtil();
            conference.setPreWeekDone(fileUtil.formatClobByString(preWeekDone));
            conference.setNowWeekSchedule(fileUtil.formatClobByString(nowWeekSchedule));
            if (StringUtil.checkString(othersTxt)) {
                conference.setOthers(fileUtil.formatClobByString(othersTxt));
            }
            conference.setWeek(DateUtil.getWeek());
            conference.setModifyUser(usid);
            conference.setModifyDate(LocalDateTime.now());
            conference.setVersion(1);
            conference.setCreator(usid);
            conference.setCreateDate(LocalDateTime.now());
            conference = conferenceMO.insertByVO(conference);
        } else {
            throw new KellyException(ResultEnum.DATA_WEEK_HAVING);
        }
        Integer code = conference == null ? 0 : 1;
        String msg = code == 1 ? "添加成功" : "服务器异常";
        return ResultUtil.getResult(code, msg, null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> modifyData (@Param("..")Conference conference
    ,@Param("preWeekDoneTxt")String preWeekDone,@Param("nowWeekScheduleTxt")String nowWeekSchedule
    ,@Param("othersTxt")String othersTxt,@Attr("usid")String usid) {
        Conference preConference = conferenceMO.fetchData(conference.getConference());
        if(conference.getProjectGuid().equals(preConference.getProjectGuid()) ||
                conferenceMO.checkByWeekProjectId(DateUtil.getWeek(),conference.getProjectGuid())) {
            FileUtil fileUtil = FileUtil.createFileUtil();
            preConference.setModifyDate(LocalDateTime.now());
            preConference.setModifyUser(usid);
            preConference.setProjectGuid(conference.getProjectGuid());
            preConference.setTitle(conference.getTitle());
            preConference.setWeek(DateUtil.getWeek());
            preConference.setStartDate(conference.getStartDate());
            preConference.setScheduleDate(conference.getScheduleDate());
            preConference.setFollower(conference.getFollower());
            preConference.setPreWeekDone(fileUtil.formatClobByString(preWeekDone));
            preConference.setNowWeekSchedule(fileUtil.formatClobByString(nowWeekSchedule));
            preConference.setOthers(fileUtil.formatClobByString(othersTxt));
        } else {
            throw new KellyException(ResultEnum.DATA_WEEK_HAVING);
        }

        Integer result = conferenceMO.updateByVO(preConference);
        Integer code = result == 0 ? 0 : 1;
        String msg = code == 1 ? "修改成功" : "服务器异常";
        return ResultUtil.getResult(code, msg, null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteData (@Param("::list")String[] conferences,@Param("conference")String conference) {
        AtomicReference<String> msg = new AtomicReference<>("删除成功");
        Trans.exec((Atom) ()->{
        if ( conference != null) {
            conferenceMO.deleteByName(conference);
        } else if (conferences != null) {
            for (String conferenceGuid : conferences) {
                conferenceMO.deleteByName(conferenceGuid);
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
    @Ok("json")
    public Map<String,Object> copyData (@Param("::list")String[] conferences,@Attr("usid")String usid) {
        int code = 0;
        String msg = "传入参数为空";
        if (conferences != null) {
            try {
                Trans.exec((Atom) () -> {
                    for (String conferenceGuid : conferences) {
                        Conference conference = conferenceMO.fetchData(conferenceGuid);
                        if(conferenceMO.checkByWeekProjectId(DateUtil.getWeek(),conference.getProjectGuid())) {
                            conference.setConference(SnowFlake.get().nextId() + "");
                            conference.setWeek(DateUtil.getWeek());
                            conference.setModifyUser(usid);
                            conference.setModifyDate(LocalDateTime.now());
                            conference.setCreator(usid);
                            conference.setCreateDate(LocalDateTime.now());
                            conference.setVersion(1);
                            conferenceMO.insertByVO(conference);
                        } else {
                            throw new KellyException(ResultEnum.DATA_WEEK_HAVING);
                        }
                    }
                });
                code = 1;
            }catch (Exception e) {
                msg = "服务器错误";
            }
        }
        msg = code == 1 ? "复制成功" : msg;
        return ResultUtil.getResult(code,msg,null);
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> fetchData (@Param("conference")String conference) {
        return ResultUtil.getResult(0,"查询成功",conferenceMO.fetchData(conference));
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> loadTableData (@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                             @Param("month")String month, @Param("week")Integer week,
                                             @Attr("usid")String usid) {
        QueryResult queryResult = conferenceMO.loadTableData(usid,month,week,pageNumber,pageSize);
        return TableUtil.makeJson(0, "成功", queryResult.getPager().getRecordCount(), queryResult.getList());
    }

    @At
    @POST
    @Ok("json")
    public Map<String,Object> loadShowData (@Param("acco")String acco) {
        return ResultUtil.getResult(1, "成功",conferenceMO.loadShowData(acco));
    }
}
