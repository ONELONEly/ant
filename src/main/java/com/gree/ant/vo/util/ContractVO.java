package com.gree.ant.vo.util;

import java.util.Date;

public class ContractVO {

    /**
     * 合同编号
     */
    private String htCoid;


    /**
     * 合同总金额
     */
    private Float htPric;

    /**
     * 合同生效日期
     */
    private Date htEdat;

    /**
     * 供应商代码
     */
    private String htPartCode;

    /**
     * 对方单位
     */
    private String htPart;

    /**
     * 供应商银行行号
     */
    private String partBankNo;

    public String getHtCoid() {
        return htCoid;
    }

    public void setHtCoid(String htCoid) {
        this.htCoid = htCoid;
    }

    public Float getHtPric() {
        return htPric;
    }

    public void setHtPric(Float htPric) {
        this.htPric = htPric;
    }

    public Date getHtEdat() {
        return htEdat;
    }

    public void setHtEdat(Date htEdat) {
        this.htEdat = htEdat;
    }

    public String getHtPartCode() {
        return htPartCode;
    }

    public void setHtPartCode(String htPartCode) {
        this.htPartCode = htPartCode;
    }

    public String getHtPartCoid() {
        return htPartCode;
    }

    public void setHtPartCoid(String htPartCoid) {
        this.htPartCode = htPartCoid;
    }

    public String getHtPart() {
        return htPart;
    }

    public void setHtPart(String htPart) {
        this.htPart = htPart;
    }

    public String getPartBankNo() {
        return partBankNo;
    }

    public void setPartBankNo(String partBankNo) {
        this.partBankNo = partBankNo;
    }
}
