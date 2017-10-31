package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * The type Tbuss 001 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 项目绩效表的实体
 * @title Tbuss001VO
 * @createTime 2017 :08:29 03:08:29.
 */
@Table("TBUSS001")
@View("v_TBUSS001")
public class Tbuss001VO extends ValueObject{

    /**
     * The Ptno.
     *
     * @description 项目编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:29 03:08:50.
     */
    @Name
//    @Prev(@SQL("select 'PT00'||(substr(MAX(t.ptno),5)+1) from TBUSS001 t"))
    private String ptno;
    /**
     * The Dsca.
     *
     * @description 描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:29 03:08:50.
     */
    private String dsca;
    /**
     * The Pdat.
     *
     * @description 月份
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:29 03:08:50.
     */
    private String pdat;
    /**
     * The Usid.
     *
     * @description 创建用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:29 03:08:50.
     */
    private String usid;

    /**
     * The Uname.
     *
     * @description 创建者姓名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 09:09:52.
     */
    @Readonly
    private String unam;

    /**
     * The Udat.
     *
     * @description 创建日期
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:29 03:08:50.
     */
    private Date udat;
    /**
     * The Grop.
     *
     * @description 团队ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:29 03:08:50.
     */
    private String grop;

    /**
     * The Gropnam.
     *
     * @description 团队名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 09:09:18.
     */
    @Readonly
    private String gropnam;

    /**
     * The Sta 1.
     *
     * @description 手动打分可编辑0-否，1-是
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 03:09:27.
     */
    private Integer sta1;

    /**
     * The Sta 2.
     *
     * @description 自动打分可编辑0-否，1-是
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 03:09:24.
     */
    private Integer sta2;

    /**
     * The Cbase 011 vos.
     *
     * @description 关联的所有任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 10:09:36.
     */
    @ManyMany(target = Cbase011VO.class,relation = "TBUSS002",from="ptno",to="pjno")
    private List<Cbase011VO> cbase011VOS;

    /**
     * The Tbuss 003 vos.
     *
     * @description 关联的自动任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 10:09:32.
     */
    @Many(target = Tbuss003VO.class,field = "ptno")
    private List<Tbuss003VO> tbuss003VOS;

    @Many(target = Tbuss005VO.class,field = "ptno")
    private List<Tbuss005VO> tbuss005VOS;

    public Tbuss001VO() {
    }

    public Tbuss001VO(String ptno, String dsca, String pdat, String usid, Date udat, String grop) {
        this.ptno = ptno;
        this.dsca = dsca;
        this.pdat = pdat;
        this.usid = usid;
        this.udat = udat;
        this.grop = grop;
    }

    public Tbuss001VO(String ptno, String dsca, String pdat, String usid, String unam, Date udat, String grop, String gropnam, Integer sta1, Integer sta2) {
        this.ptno = ptno;
        this.dsca = dsca;
        this.pdat = pdat;
        this.usid = usid;
        this.unam = unam;
        this.udat = udat;
        this.grop = grop;
        this.gropnam = gropnam;
        this.sta1 = sta1;
        this.sta2 = sta2;
    }

    public String getUnam() {
        return unam;
    }

    public void setUnam(String unam) {
        this.unam = unam;
    }

    public String getGropnam() {
        return gropnam;
    }

    public void setGropnam(String gropnam) {
        this.gropnam = gropnam;
    }

    public Integer getSta1() {
        return sta1;
    }

    public void setSta1(Integer sta1) {
        this.sta1 = sta1;
    }

    public Integer getSta2() {
        return sta2;
    }

    public void setSta2(Integer sta2) {
        this.sta2 = sta2;
    }

    public List<Tbuss005VO> getTbuss005VOS() {
        return tbuss005VOS;
    }

    public void setTbuss005VOS(List<Tbuss005VO> tbuss005VOS) {
        this.tbuss005VOS = tbuss005VOS;
    }

    public List<Tbuss003VO> getTbuss003VOS() {
        return tbuss003VOS;
    }

    public void setTbuss003VOS(List<Tbuss003VO> tbuss003VOS) {
        this.tbuss003VOS = tbuss003VOS;
    }

    public List<Cbase011VO> getCbase011VOS() {
        return cbase011VOS;
    }

    public void setCbase011VOS(List<Cbase011VO> cbase011VOS) {
        this.cbase011VOS = cbase011VOS;
    }

    public String getPtno() {
        return ptno;
    }

    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getPdat() {
        return pdat;
    }

    public void setPdat(String pdat) {
        this.pdat = pdat;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Date getUdat() {
        return udat;
    }

    public void setUdat(Date udat) {
        this.udat = udat;
    }

    public String getGrop() {
        return grop;
    }

    public void setGrop(String grop) {
        this.grop = grop;
    }
}
