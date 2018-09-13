package com.gree.ant.vo.request;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description Okr任务实体
 */
public class TaskVO {

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
}
