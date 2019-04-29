package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 012 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 评分项对应分值
 * @title Cbase012VO
 * @createTime 2017 :08:30 01:08:52.
 */
@Table("CBASE012")
public class Cbase012VO extends ValueObject{

    /**
     * The Pjno.
     *
     * @description 评分项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:52.
     */
    private String pjno;
    /**
     * The Opco.
     *
     * @description 分值
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:52.
     */
    private String opco;
    /**
     * The Remk.
     *
     * @description 变量的说明
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:52.
     */
    private String remk;

    public Cbase012VO() {
    }

    public Cbase012VO(String pjno, String opco) {
        this.pjno = pjno;
        this.opco = opco;
    }

    public Cbase012VO(String pjno, String opco, String remk) {
        this.pjno = pjno;
        this.opco = opco;
        this.remk = remk;
    }

    public String getPjno() {
        return pjno;
    }

    public void setPjno(String pjno) {
        this.pjno = pjno;
    }

    public String getOpco() {
        return opco;
    }

    public void setOpco(String opco) {
        this.opco = opco;
    }

    public String getRemk() {
        return remk;
    }

    public void setRemk(String remk) {
        this.remk = remk;
    }
}
