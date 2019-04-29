package com.gree.ant.vo.request;

import com.gree.ant.vo.Tbuss013VO;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description Okr任务实体
 */
public class TaskVO {

    private Integer task_id;

    /**
     * @description 任务完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String achi;
    /**
     * @description KR比重
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float krprop;

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
     * @description 领导评分
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float mgrad;

    public TaskVO() {

    }

    public TaskVO(Integer task_id, String achi, Float krprop, String krperf, Float zgrad,Float mgrad) {
        this.task_id = task_id;
        this.achi = achi;
        this.krprop = krprop;
        this.krperf = krperf;
        this.zgrad = zgrad;
        this.mgrad = mgrad;
    }

    public String getAchi() {
        return achi;
    }

    public void setAchi(String achi) {
        this.achi = achi;
    }

    public Float getKrprop() {
        return krprop;
    }

    public void setKrprop(Float krprop) {
        this.krprop = krprop;
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

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public Tbuss013VO formatTask(){
        return new Tbuss013VO(this.task_id,this.achi,this.krprop,this.krperf,this.zgrad);
    }
}
