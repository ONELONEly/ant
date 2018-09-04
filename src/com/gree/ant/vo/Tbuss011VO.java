package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;


/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理表实体
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
    private String GOAL;
    /**
     * @description 周期 （多少天）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer NDAT;

    /**
     * @description 周期描述
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Readonly
    private String NNDAT;
    /**
     * @description 类型
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer TYPE;

    /**
     * @description 类型描述
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Readonly
    private String NTYPE;
    /**
     * @description 权重
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer PROP;
    /**
     * @description 完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String PERF;
    /**
     * @description 关键成果
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String ACHI;
    /**
     * @description KRS权重 （全部加起来一定为100）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer KRPROP;
    /**
     * @description KRS完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String KRPERF;

    /**
     * @description 自评分
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer ZGRAD;

    public Tbuss011VO() {

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

    public String getGOAL() {
        return GOAL;
    }

    public void setGOAL(String GOAL) {
        this.GOAL = GOAL;
    }

    public Integer getNDAT() {
        return NDAT;
    }

    public void setNDAT(Integer NDAT) {
        this.NDAT = NDAT;
    }

    public String getNNDAT() {
        return NNDAT;
    }

    public void setNNDAT(String NNDAT) {
        this.NNDAT = NNDAT;
    }

    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public String getNTYPE() {
        return NTYPE;
    }

    public void setNTYPE(String NTYPE) {
        this.NTYPE = NTYPE;
    }

    public Integer getPROP() {
        return PROP;
    }

    public void setPROP(Integer PROP) {
        this.PROP = PROP;
    }

    public String getPERF() {
        return PERF;
    }

    public void setPERF(String PERF) {
        this.PERF = PERF;
    }

    public String getACHI() {
        return ACHI;
    }

    public void setACHI(String ACHI) {
        this.ACHI = ACHI;
    }

    public Integer getKRPROP() {
        return KRPROP;
    }

    public void setKRPROP(Integer KRPROP) {
        this.KRPROP = KRPROP;
    }

    public String getKRPERF() {
        return KRPERF;
    }

    public void setKRPERF(String KRPERF) {
        this.KRPERF = KRPERF;
    }

    public Integer getZGRAD() {
        return ZGRAD;
    }

    public void setZGRAD(Integer ZGRAD) {
        this.ZGRAD = ZGRAD;
    }
}
