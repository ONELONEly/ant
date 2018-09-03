package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;


/**
 * @description OKR管理表实体
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
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
     * @description 直接领导
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String BOSS;
    /**
     * @description 管理周期（默认当月）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String MDAT;
    /**
     * @description 目标
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String GOAL;
    /**
     * @description 周期（多少天）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer NDAT;
    /**
     * @description 类型
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer TYPE;
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
     * @description KRS权重（全部加起来一定为100）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer KRPROP;
    /**
     * @description KRS完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String KRPERF;

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

    public String getBOSS() {
        return BOSS;
    }

    public void setBOSS(String BOSS) {
        this.BOSS = BOSS;
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

    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public Integer getPROP() {
        return PROP;
    }

    public void setPROP(Integer PROP) {
        this.PROP = PROP;
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

    public String getPERF() {
        return PERF;
    }

    public void setPERF(String PERF) {
        this.PERF = PERF;
    }

    public String getKRPERF() {
        return KRPERF;
    }

    public void setKRPERF(String KRPERF) {
        this.KRPERF = KRPERF;
    }
}
