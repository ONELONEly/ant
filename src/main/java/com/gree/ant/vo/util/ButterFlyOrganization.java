package com.gree.ant.vo.util;

import org.nutz.dao.entity.annotation.*;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 金蝶组织架构
 * @createTime 2019 -02-20 09:59:22
 */
@Comment("金蝶用户表")
@Table("ButterFlyOrganization")
public class ButterFlyOrganization {

    /**
     * @description 组织架构ID
     * @createTime 2019 -02-20 10:07:20
     * @version 1.0
     */
    @Id
    @Comment("组织架构ID")
    @ColDefine(type = ColType.INT,notNull = true)
    @Prev(@SQL("select nvl(max(organization_id)+1,1) from ButterFlyOrganization"))
    private Integer organization_id;

    /**
     * @description 组织编号
     * @createTime 2019 -02-20 09:59:22
     * @version 1.0
     */
    @Comment("组织编号")
    @ColDefine(type = ColType.VARCHAR,width = 100,notNull = true)
    private String organizationNumber;
    /**
     * @description 组织主编码
     * @createTime 2019 -02-20 09:59:22
     * @version 1.0
     */
    @Comment("组织主编码")
    @ColDefine(type = ColType.VARCHAR,width = 100,notNull = true)
    private String organizationMainNumber;
    /**
     * @description 组织名称
     * @createTime 2019 -02-20 09:59:22
     * @version 1.0
     */
    @Comment("组织名称")
    @ColDefine(type = ColType.VARCHAR,width = 100,notNull = true)
    private String organizationName;
    /**
     * @description 组织等级
     * @createTime 2019 -02-20 09:59:22
     * @version 1.0
     */
    @Comment("组织等级")
    @ColDefine(type = ColType.VARCHAR,width = 100,notNull = true)
    private String organizationStage;

    public ButterFlyOrganization() {
    }

    public ButterFlyOrganization(String organizationNumber, String organizationMainNumber, String organizationName, String organizationStage) {
        this.organizationNumber = organizationNumber;
        this.organizationMainNumber = organizationMainNumber;
        this.organizationName = organizationName;
        this.organizationStage = organizationStage;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrganizationNumber() {
        return organizationNumber;
    }

    public void setOrganizationNumber(String organizationNumber) {
        this.organizationNumber = organizationNumber;
    }

    public String getOrganizationMainNumber() {
        return organizationMainNumber;
    }

    public void setOrganizationMainNumber(String organizationMainNumber) {
        this.organizationMainNumber = organizationMainNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationStage() {
        return organizationStage;
    }

    public void setOrganizationStage(String organizationStage) {
        this.organizationStage = organizationStage;
    }
}
