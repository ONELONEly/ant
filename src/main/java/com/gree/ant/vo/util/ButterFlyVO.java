package com.gree.ant.vo.util;

import org.nutz.dao.entity.annotation.*;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 金蝶用户
 * @createTime 2019 -02-18 19:03:53
 */
@Comment("金蝶用户表")
@Table("ButterFly")
public class ButterFlyVO {

    /**
     * @description 金蝶ID
     * @createTime 2019 -02-19 09:53:21
     * @version 1.0
     */
    @Id
    @Column("butterFly_id")
    @Comment("金蝶用户ID")
    @ColDefine(type = ColType.INT,notNull = true)
    @Prev(@SQL("select nvl(max(butterFly_id)+1,1) from ButterFly"))
    private Integer butterFly_id;

    /**
     * @description 公司编号
     * @createTime 2019 -02-20 17:41:07
     * @version 1.0
     */
    @Column("companyNumber")
    @Comment("公司名称")
    private String companyNumber;

    /**
     * @description 公司名称
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    @Column("company")
    @Comment("公司名称")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String company;

    @Column("departmentNumber")
    @Comment("部门编号")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String departmentNumber;

    /**
     * @description 部门
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    @Column("department")
    @Comment("部门")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String department;

    /**
     * @description 岗位
     * @createTime 2019 -02-18 19:07:52
     * @version 1.0
     */
    @Column("posts")
    @Comment("岗位")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String posts;

    @Column("postsName")
    @Comment("岗位名称")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String postsName;

    /**
     * @description 岗位编码
     * @createTime 2019 -02-21 16:25:01
     * @version 1.0
     */
    @Column("postsNumber")
    @Comment("岗位编码")
    @ColDefine(type = ColType.INT,width = 20,notNull = true)
    private Integer postsNumber;

    /**
     * @description 员工编号
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    @Comment("员工编号")
    @Column("numberCard")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String number;
    /**
     * @description 员工姓名
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    @Column("name")
    @Comment("员工姓名")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String name;
    /**
     * @description 员工性别
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    @Column("sex")
    @Comment("员工性别")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String sex;
    /**
     * @description 身份证号码
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    @Column("card")
    @Comment("身份证号码")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = false)
    private String card;

    @Column("email")
    @Comment("邮箱编号")
    @ColDefine(type = ColType.VARCHAR,width = 6,notNull = false)
    private String email;

    /**
     * @description 是否在岗
     * @createTime 2019 -02-19 10:47:22
     * @version 1.0
     */
    @Column("isInStaff")
    @Comment("是否占编")
    @ColDefine(type = ColType.VARCHAR,width = 40,notNull = true)
    private String isInStaff;

    /**
     * @description 所属组织长编码
     * @createTime 2019 -02-19 16:43:47
     * @version 1.0
     */
    @Column("organizationNumber")
    @Comment("所属组织长编码")
    @ColDefine(type = ColType.VARCHAR,width = 100,notNull = true)
    private String organizationNumber;

    public ButterFlyVO() {
    }

    public ButterFlyVO(String companyNumber, String company, String departmentNumber, String department, String posts,
                       String postsName, Integer postsNumber, String number, String name, String sex, String card,
                       String email, String isInStaff, String organizationNumber) {
        this.companyNumber = companyNumber;
        this.company = company;
        this.departmentNumber = departmentNumber;
        this.department = department;
        this.posts = posts;
        this.postsName = postsName;
        this.postsNumber = postsNumber;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.card = card;
        this.email = email;
        this.isInStaff = isInStaff;
        this.organizationNumber = organizationNumber;
    }

    public String getPostsName() {
        return postsName;
    }

    public void setPostsName(String postsName) {
        this.postsName = postsName;
    }

    public Integer getPostsNumber() {
        return postsNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostsNumber(Integer postsNumber) {
        this.postsNumber = postsNumber;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getOrganizationNumber() {
        return organizationNumber;
    }

    public void setOrganizationNumber(String organizationNumber) {
        this.organizationNumber = organizationNumber;
    }

    public Integer getButterFly_id() {
        return butterFly_id;
    }

    public void setButterFly_id(Integer butterFly_id) {
        this.butterFly_id = butterFly_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getIsInStaff() {
        return isInStaff;
    }

    public void setIsInStaff(String isInStaff) {
        this.isInStaff = isInStaff;
    }
}
