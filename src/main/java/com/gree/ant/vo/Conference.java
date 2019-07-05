package com.gree.ant.vo;
import org.nutz.dao.entity.annotation.*;

import java.sql.Clob;
import java.time.LocalDateTime;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 会议记录表
 * @createTime 2019 -06-11 11:37:52
 */
@Table("conference")
@PK("conference")
public class Conference {

    /**
     * @description 唯一主键
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Name
    @Column(value = "conference_guid")
    private String conference;
    /**
     * @description 标题
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "title")
    private String title;
    /**
     * @description 项目开始时间
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "startDate")
    private LocalDateTime startDate;

    @Readonly
    private String startDateTxt;
    /**
     * @description 项目计划上线时间
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "scheduleDate")
    private LocalDateTime scheduleDate;

    @Readonly
    private String scheduleDateTxt;
    /**
     * @description 项目跟进人
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "follower")
    private String follower;

    /**
     * @description 项目上周计划完成
     * @createTime 2019 -07-05 17:24:13
     * @version 1.0
     */
    @Readonly
    private String preWeekScheduleTxt;
    /**
     * @description 项目上周已完成
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "pre_week_done")
    private Clob preWeekDone;

    @Readonly
    private String preWeekDoneTxt;
    /**
     * @description 项目本周计划完成
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "now_week_schedule")
    private Clob nowWeekSchedule;

    @Readonly
    private String nowWeekScheduleTxt;
    /**
     * @description 其它
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "others")
    private Clob others;

    @Readonly
    private String othersTxt;

    /**
     * @description 周数
     * @createTime 2019 -06-11 15:53:54
     * @version 1.0
     */
    @Column(value = "week")
    private Integer week;

    /**
     * @description 最后修改时间
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "modifyDate")
    private LocalDateTime modifyDate;
    /**
     * @description 最后修改用户
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "modifyUser")
    private String modifyUser;
    /**
     * @description 版本
     * @createTime 2019 -06-11 11:37:52
     * @version 1.0
     */
    @Column(value = "version")
    private Integer version;

    /**
     * @description 创建人
     * @createTime 2019 -06-12 14:49:27
     * @version 1.0
     */
    @Column(value = "creator")
    private String creator;

    /**
     * @description 创建日期
     * @createTime 2019 -06-12 14:49:30
     * @version 1.0
     */
    @Column(value = "create_date")
    private LocalDateTime createDate;

    /**
     * @description 项目编号
     * @createTime 2019 -07-05 15:02:32
     * @version 1.0
     */
    @Column(value = "project_guid")
    private String projectGuid;

    public Conference() {
    }

    public String getPreWeekScheduleTxt() {
        return preWeekScheduleTxt;
    }

    public void setPreWeekScheduleTxt(String preWeekScheduleTxt) {
        this.preWeekScheduleTxt = preWeekScheduleTxt;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public Clob getPreWeekDone() {
        return preWeekDone;
    }

    public void setPreWeekDone(Clob preWeekDone) {
        this.preWeekDone = preWeekDone;
    }

    public Clob getNowWeekSchedule() {
        return nowWeekSchedule;
    }

    public void setNowWeekSchedule(Clob nowWeekSchedule) {
        this.nowWeekSchedule = nowWeekSchedule;
    }

    public Clob getOthers() {
        return others;
    }

    public void setOthers(Clob others) {
        this.others = others;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getPreWeekDoneTxt() {
        return preWeekDoneTxt;
    }

    public void setPreWeekDoneTxt(String preWeekDoneTxt) {
        this.preWeekDoneTxt = preWeekDoneTxt;
    }

    public String getNowWeekScheduleTxt() {
        return nowWeekScheduleTxt;
    }

    public void setNowWeekScheduleTxt(String nowWeekScheduleTxt) {
        this.nowWeekScheduleTxt = nowWeekScheduleTxt;
    }

    public String getOthersTxt() {
        return othersTxt;
    }

    public void setOthersTxt(String othersTxt) {
        this.othersTxt = othersTxt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getStartDateTxt() {
        return startDateTxt;
    }

    public void setStartDateTxt(String startDateTxt) {
        this.startDateTxt = startDateTxt;
    }

    public String getScheduleDateTxt() {
        return scheduleDateTxt;
    }

    public void setScheduleDateTxt(String scheduleDateTxt) {
        this.scheduleDateTxt = scheduleDateTxt;
    }
}
