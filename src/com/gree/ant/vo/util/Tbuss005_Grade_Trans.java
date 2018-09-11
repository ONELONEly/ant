package com.gree.ant.vo.util;

public class Tbuss005_Grade_Trans {

    private String csid;
    private double cons;
    private String ptno;
    private String pjno;
    private String remk;

    public Tbuss005_Grade_Trans() {
    }

    public Tbuss005_Grade_Trans(String csid, double cons, String ptno, String pjno, String remk) {
        this.csid = csid;
        this.cons = cons;
        this.ptno = ptno;
        this.pjno = pjno;
        this.remk = remk;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public double getCons() {
        return cons;
    }

    public void setCons(double cons) {
        this.cons = cons;
    }

    public String getPtno() {
        return ptno;
    }

    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    public String getPjno() {
        return pjno;
    }

    public void setPjno(String pjno) {
        this.pjno = pjno;
    }

    public String getRemk() {
        return remk;
    }

    public void setRemk(String remk) {
        this.remk = remk;
    }
}
