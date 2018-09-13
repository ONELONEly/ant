package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;


/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理表对应实体
 */
@Table("TBUSS011")
public class Tbuss011VO extends ValueObject{


    /**
     * @description OKR管理表索引
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Id
    @Prev(@SQL("select nvl(max(OKID)+1,1) from Tbuss011"))
    private Integer OKID;

    /**
     * @description 管理对象
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String ASID;

    /**
     * @description 管理对象名称
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Readonly
    private String ANAM;
    /**
     * @description 直接领导
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String BOSS;

    /**
     * @description 直系领导名称
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Readonly
    private String BNAM;
    /**
     * @description 管理周期 （默认当月）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String MDAT;
    /**
     * @description 目标
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */

    public Tbuss011VO() {

    }

    public Tbuss011VO(String ASID, String BOSS, String MDAT) {
        this.ASID = ASID;
        this.BOSS = BOSS;
        this.MDAT = MDAT;
    }

    public Integer getOKID() {
        return OKID;
    }

    public void setOKID(Integer OKID) {
        this.OKID = OKID;
    }

    public String getASID() {
        return ASID;
    }

    public void setASID(String ASID) {
        this.ASID = ASID;
    }

    public String getANAM() {
        return ANAM;
    }

    public void setANAM(String ANAM) {
        this.ANAM = ANAM;
    }

    public String getBOSS() {
        return BOSS;
    }

    public void setBOSS(String BOSS) {
        this.BOSS = BOSS;
    }

    public String getBNAM() {
        return BNAM;
    }

    public void setBNAM(String BNAM) {
        this.BNAM = BNAM;
    }

    public String getMDAT() {
        return MDAT;
    }

    public void setMDAT(String MDAT) {
        this.MDAT = MDAT;
    }
}
