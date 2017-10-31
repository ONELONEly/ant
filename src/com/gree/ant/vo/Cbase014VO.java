package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 14 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务阶段表的实体.
 * @title Cbase14VO
 * @createTime 2017 :09:11 03:09:43.
 */
@Table("CBASE014")
public class Cbase014VO extends ValueObject{

    /**
     * The Puno.
     *
     * @description 阶段编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 03:09:43.
     */
    private String puno;

    /**
     * The Dsca.
     *
     * @description 阶段描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 03:09:43.
     */
    private String dsca;

    public Cbase014VO() {
    }

    public Cbase014VO(String puno) {
        this.puno = puno;
    }

    public Cbase014VO(String puno, String dsca) {
        this.puno = puno;
        this.dsca = dsca;
    }

    public String getPuno() {
        return puno;
    }

    public void setPuno(String puno) {
        this.puno = puno;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }
}
