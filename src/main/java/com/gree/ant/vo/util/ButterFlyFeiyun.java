package com.gree.ant.vo.util;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 飞云对接用户信息
 * @createTime 2019 -02-19 15:24:36
 */
public class ButterFlyFeiyun {


    /**
     * @description 公司编号
     * @createTime 2019 -02-20 17:41:07
     * @version 1.0
     */
    private String companyNumber;

    /**
     * @description 公司名称
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    private String company;

    /**
     * @description 部门编号
     * @createTime 2019 -02-20 17:41:07
     * @version 1.0
     */
    private String departmentNumber;

    /**
     * @description 部门
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    private String department;

    /**
     * @description 岗位
     * @createTime 2019 -02-18 19:07:52
     * @version 1.0
     */
    private String posts;


    /**
     * @description 岗位名称
     * @createTime 2019 -03-01 09:46:03
     * @version 1.0
     */
    private String postsName;

    /**
     * @description 员工编号
     * @createTime 2019 -02-20 17:41:07
     * @version 1.0
     */
    private String number;

    /**
     * @description 员工姓名
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    private String name;
    /**
     * @description 员工性别
     * @createTime 2019 -02-18 19:03:53
     * @version 1.0
     */
    private String sex;

    /**
     * @description 邮箱编号
     * @createTime 2019 -02-21 16:33:59
     * @version 1.0
     */
    private String email;

    /**
     * @description 是否在岗
     * @createTime 2019 -02-19 10:47:22
     * @version 1.0
     */
    private String isInStaff;

    public ButterFlyFeiyun() {
    }

    public ButterFlyFeiyun(String company, String department, String posts, String name, String sex, String isInStaff) {
        this.company = company;
        this.department = department;
        this.posts = posts;
        this.name = name;
        this.sex = sex;
        this.isInStaff = isInStaff;
    }

    public ButterFlyFeiyun(String companyNumber, String company, String departmentNumber, String department, String posts,
                           String postsName, String number, String name, String sex, String email, String isInStaff) {
        this.companyNumber = companyNumber;
        this.company = company;
        this.departmentNumber = departmentNumber;
        this.department = department;
        this.posts = posts;
        this.postsName = postsName;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.isInStaff = isInStaff;
    }

    public String getPostsName() {
        return postsName;
    }

    public void setPostsName(String postsName) {
        this.postsName = postsName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getIsInStaff() {
        return isInStaff;
    }

    public void setIsInStaff(String isInStaff) {
        this.isInStaff = isInStaff;
    }
}
