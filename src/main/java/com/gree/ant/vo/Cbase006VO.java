package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 006 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 部门表实体
 * @title Cbase006VO
 * @createTime 2017 :09:01 10:09:44.
 */
@Table("CBASE006")
public class Cbase006VO extends ValueObject{

    /**
     * The Dept.
     *
     * @description 部门编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    @Name
    private String dept;
    /**
     * The Dsca.
     *
     * @description 部门描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    private String dsca;
    /**
     * The Sta 1.
     *
     * @description 二级审批（0：否，1：是）
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    private Integer sta1;
    /**
     * The Comp.
     *
     * @description 公司编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    private String comp;

    public Cbase006VO() {
    }

    public Cbase006VO(String dept, String dsca, Integer sta1, String comp) {
        this.dept = dept;
        this.dsca = dsca;
        this.sta1 = sta1;
        this.comp = comp;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public Integer getSta1() {
        return sta1;
    }

    public void setSta1(Integer sta1) {
        this.sta1 = sta1;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }
}
