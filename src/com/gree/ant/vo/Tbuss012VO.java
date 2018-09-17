package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 目标表对应的实体
 */
@Table("TBUSS012")
public class Tbuss012VO {

    /**
     * @description 目标ID
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Id
    @Prev(@SQL("select nvl(max(goal_id)+1,1) from Tbuss012"))
    private Integer goal_id;
    /**
     * @description 目标
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String goal;
    /**
     * @description 周期
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String ndat;
    /**
     * @description 类型
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer type;
    /**
     * @description 比重
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float prop;
    /**
     * @description 完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String perf;
    /**
     * @description KR完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String krperf;
    /**
     * @description 自评成绩
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float zgrad;
    /**
     * @description 领导评分
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float mgrad;
    /**
     * @description 管理表ID
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer okid;

    @Many(target = Tbuss013VO.class,field = "goal_id",key = "goal_id")
    private List<Tbuss013VO> tbuss013VOS;

    public Tbuss012VO() {
    }

    public Tbuss012VO(String goal, String ndat, Integer type, Float prop, String perf, String krperf, Float zgrad) {
        this.goal = goal;
        this.ndat = ndat;
        this.type = type;
        this.prop = prop;
        this.perf = perf;
        this.krperf = krperf;
        this.zgrad = zgrad;
    }

    public Integer getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(Integer goal_id) {
        this.goal_id = goal_id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getNdat() {
        return ndat;
    }

    public void setNdat(String ndat) {
        this.ndat = ndat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getProp() {
        return prop;
    }

    public void setProp(Float prop) {
        this.prop = prop;
    }

    public String getPerf() {
        return perf;
    }

    public void setPerf(String perf) {
        this.perf = perf;
    }

    public String getKrperf() {
        return krperf;
    }

    public void setKrperf(String krperf) {
        this.krperf = krperf;
    }

    public Float getZgrad() {
        return zgrad;
    }

    public void setZgrad(Float zgrad) {
        this.zgrad = zgrad;
    }

    public Float getMgrad() {
        return mgrad;
    }

    public void setMgrad(Float mgrad) {
        this.mgrad = mgrad;
    }

    public Integer getOkid() {
        return okid;
    }

    public void setOkid(Integer okid) {
        this.okid = okid;
    }

    public List<Tbuss013VO> getTbuss013VOS() {
        return tbuss013VOS;
    }

    public void setTbuss013VOS(List<Tbuss013VO> tbuss013VOS) {
        this.tbuss013VOS = tbuss013VOS;
    }
}
