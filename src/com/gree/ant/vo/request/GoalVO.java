package com.gree.ant.vo.request;

import com.gree.ant.vo.Tbuss012VO;

import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR目标实体
 */
public class GoalVO {

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
     * @description 自评分
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float zgrad;
    /**
     * @description 任务集合
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private List<TaskVO> tasks;

    /**
     * @description OKR绩效表ID
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer okid;

    public String getGoal() {
        return goal;
    }

    public GoalVO() {
    }

    public GoalVO(String goal, String ndat, Integer type, Float prop, String perf, String krperf, Float zgrad) {
        this.goal = goal;
        this.ndat = ndat;
        this.type = type;
        this.prop = prop;
        this.perf = perf;
        this.krperf = krperf;
        this.zgrad = zgrad;
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

    public List<TaskVO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskVO> tasks) {
        this.tasks = tasks;
    }

    public Integer getOkid() {
        return okid;
    }

    public void setOkid(Integer okid) {
        this.okid = okid;
    }

    public Tbuss012VO getFormatGoal(){
        return new Tbuss012VO(this.getGoal(),this.getNdat(),this.getType(),this.getProp(),this.getPerf(),this.getKrperf(),this.getZgrad());
    }
}
