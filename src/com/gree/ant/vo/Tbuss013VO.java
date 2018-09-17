package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 目标任务表对应实体
 */
@Table("TBUSS013")
public class Tbuss013VO {

    /**
     * @description 任务ID
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Id
    @Prev(@SQL("select nvl(max(task_id)+1,1) from Tbuss013"))
    private Integer task_id;
    /**
     * @description 关键成果
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String achi;
    /**
     * @description KR完成情况
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Float krprop;
    /**
     * @description 目标ID
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer goal_id;

    public Tbuss013VO() {
    }

    public Tbuss013VO(String achi, Float krprop) {
        this.achi = achi;
        this.krprop = krprop;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
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

    public Integer getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(Integer goal_id) {
        this.goal_id = goal_id;
    }
}
