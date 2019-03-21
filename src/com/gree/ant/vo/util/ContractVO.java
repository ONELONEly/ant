package com.gree.ant.vo.util;

import java.util.Date;

public class ContractVO {

    /**
     * 合同编号
     */
    private String htCoid;

    /**
     * 对方单位
     */
    private String htPart;

    /**
     * 结算条件
     */
    private String htPcon;

    /**
     * 结算币种
     */
    private String htFmor;

    /**
     * 结算日期
     */
    private Date htFdat;

    /**
     * 结算金额
     */
    private Double htFpic;

    /**
     * 结算方式
     */
    private String htModl;

    public String getHtCoid() {
        return htCoid;
    }

    public void setHtCoid(String htCoid) {
        this.htCoid = htCoid;
    }

    public String getHtPart() {
        return htPart;
    }

    public void setHtPart(String htPart) {
        this.htPart = htPart;
    }

    public String getHtPcon() {
        return htPcon;
    }

    public void setHtPcon(String htPcon) {
        this.htPcon = htPcon;
    }

    public String getHtFmor() {
        return htFmor;
    }

    public void setHtFmor(String htFmor) {
        this.htFmor = htFmor;
    }

    public Date getHtFdat() {
        return htFdat;
    }

    public void setHtFdat(Date htFdat) {
        this.htFdat = htFdat;
    }

    public Double getHtFpic() {
        return htFpic;
    }

    public void setHtFpic(Double htFpic) {
        this.htFpic = htFpic;
    }

    public String getHtModl() {
        return htModl;
    }

    public void setHtModl(String htModl) {
        this.htModl = htModl;
    }
}
