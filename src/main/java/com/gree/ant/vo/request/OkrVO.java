package com.gree.ant.vo.request;

import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理表请求实体
 */
public class OkrVO {

    /**
     * @description 管理对象
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String asid;
    /**
     * @description 直系领导
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String boss;
    /**
     * @description 管理周期
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String mdat;

    /**
     * @description 提交类型
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer type;

    /**
     * @description 提交身份
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer auth;

    /**
     * @description 目标集合
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private List<GoalVO> goals;

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getMdat() {
        return mdat;
    }

    public void setMdat(String mdat) {
        this.mdat = mdat;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public List<GoalVO> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalVO> goals) {
        this.goals = goals;
    }

    public Tbuss011VO getOkrManager(){
        return new Tbuss011VO(this.asid,this.boss,this.mdat,this.type,this.auth);
    }
}
