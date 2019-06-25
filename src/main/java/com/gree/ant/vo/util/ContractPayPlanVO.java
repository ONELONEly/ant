package com.gree.ant.vo.util;

import java.util.Date;

public class ContractPayPlanVO {

    /**
     * 付款说明
     */
    private String pcon;

    /**
     * 付款金额
     */
    private float fpric;

    /**
     * 结算日期
     */
    private Date fdate;

    /**
     * 结算比例
     */
    private float plva;

    /**
     * 币种
     * @return
     */
    private String currency;

    public String getPcon() {
        return pcon;
    }

    public void setPcon(String pcon) {
        this.pcon = pcon;
    }

    public float getFpric() {
        return fpric;
    }

    public void setFpric(float fpric) {
        this.fpric = fpric;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public float getPlva() {
        return plva;
    }

    public void setPlva(float plva) {
        this.plva = plva;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
