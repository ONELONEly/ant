package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 会议项目用户表
 * @createTime 2019 -06-26 16:37:13
 */
@Table("ConferenceProjectUser")
public class ConferenceProjectUser {

    /**
     * @description 项目用户表主键
     * @createTime 2019 -06-26 16:37:13
     * @version 1.0
     */
    @Id
    @Column("project_user_id")
    @Prev(@SQL("select nvl(max(project_user_id)+1,1) from ConferenceProjectUser"))
    private Integer projectUserId;
    /**
     * @description 项目编号
     * @createTime 2019 -06-26 16:37:13
     * @version 1.0
     */
    @Column("project_guid")
    private String projectGuid;
    /**
     * @description 用户编号
     * @createTime 2019 -06-26 16:37:13
     * @version 1.0
     */
    @Column("user_id")
    private String userId;

    @Readonly
    private String userName;

    public ConferenceProjectUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProjectUserId() {
        return projectUserId;
    }

    public void setProjectUserId(Integer projectUserId) {
        this.projectUserId = projectUserId;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
