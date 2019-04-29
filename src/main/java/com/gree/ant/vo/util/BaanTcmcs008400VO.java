package com.gree.ant.vo.util;

import java.util.Date;

public class BaanTcmcs008400VO {

    /**
     * 基本货币
     */
    private String bcur;

    /**
     * 货币
     */
    private String ccur;

    /**
     * 汇率类型
     */
    private String rtyp;

    /**
     * 生效日期
     */
    private Date stdt;

    /**
     * 汇率
     */
    private Double rate;

    public String getBcur() {
        return bcur;
    }

    public void setBcur(String bcur) {
        this.bcur = bcur;
    }

    public String getCcur() {
        return ccur;
    }

    public void setCcur(String ccur) {
        this.ccur = ccur;
    }

    public String getRtyp() {
        return rtyp;
    }

    public void setRtyp(String rtyp) {
        this.rtyp = rtyp;
    }

    public Date getStdt() {
        return stdt;
    }

    public void setStdt(Date stdt) {
        this.stdt = stdt;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
