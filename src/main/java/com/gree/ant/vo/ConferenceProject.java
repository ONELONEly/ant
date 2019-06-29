package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 会议项目表
 * @createTime 2019 -06-26 16:26:13
 */
@Table(" ConferenceProject")
@PK("project_guid")
public class ConferenceProject {

    /**
     * @description 项目编号
     * @createTime 2019 -06-26 16:26:13
     * @version 1.0
     */
    @Name
    @Column("project_guid")
    private String projectGuid;
    /**
     * @description 项目标题
     * @createTime 2019 -06-26 16:26:13
     * @version 1.0
     */
    @Column("title")
    private String title;
    /**
     * @description 开始日期
     * @createTime 2019 -06-26 16:26:13
     * @version 1.0
     */
    @Column("startDate")
    private LocalDateTime startDate;
    /**
     * @description 计划上线时间
     * @createTime 2019 -06-26 16:26:13
     * @version 1.0
     */
    @Column("onlineDate")
    private LocalDateTime  onlineDate;
    /**
     * @description 顺序号码
     * @createTime 2019 -06-26 16:26:13
     * @version 1.0
     */
    @Column("orderNumber")
    private Integer orderNumber;
    /**
     * @description 状态
     * @createTime 2019 -06-26 16:26:13
     * @version 1.0
     */
    @Column("state")
    private Integer state;

    /**
     * @description 最后修改日期
     * @createTime 2019 -06-26 18:21:53
     * @version 1.0
     */
    @Column("modifyDate")
    private LocalDateTime modifyDate;
    /**
     * @description 最后修改用户
     * @createTime 2019 -06-26 18:21:56
     * @version 1.0
     */
    @Column("modifyUser")
    private String modifyUser;
    /**
     * @description 版本号
     * @createTime 2019 -06-26 18:21:57
     * @version 1.0
     */
    @Column("version")
    private Integer version;

    @Readonly
    private List<ConferenceProjectUser> projectUsers;

    public ConferenceProject() {
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

    public List<ConferenceProjectUser> getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(List<ConferenceProjectUser> projectUsers) {
        this.projectUsers = projectUsers;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
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

    public LocalDateTime getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(LocalDateTime onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
