package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * The type Tbuss 004 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 日志表实体
 * @title Tbuss004VO
 * @createTime 2017 :09:06 10:09:30.
 */
@Table("TBUSS004")
public class Tbuss004VO extends ValueObject{

    /**
     * The Taid.
     *
     * @description 编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:30.
     */
    @Name
    private String taid;
    /**
     * The Sta 1.
     *
     * @description 状态
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:30.
     */
    private Integer sta1;
    /**
     * The Edat.
     *
     * @description 操作时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:30.
     */
    private Date edat;
    /**
     * The Ermk.
     *
     * @description 备注
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:30.
     */
    private String remk;

    public Tbuss004VO() {
    }

    public Tbuss004VO(String taid) {
        this.taid = taid;
    }

    public Tbuss004VO(String taid, Integer sta1, Date edat, String remk) {
        this.taid = taid;
        this.sta1 = sta1;
        this.edat = edat;
        this.remk = remk;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public Integer getSta1() {
        return sta1;
    }

    public void setSta1(Integer sta1) {
        this.sta1 = sta1;
    }

    public Date getEdat() {
        return edat;
    }

    public void setEdat(Date edat) {
        this.edat = edat;
    }

    public String getRemk() {
        return remk;
    }

    public void setRemk(String remk) {
        this.remk = remk;
    }
}
