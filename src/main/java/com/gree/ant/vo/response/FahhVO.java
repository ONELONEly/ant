package com.gree.ant.vo.response;

public class FahhVO {

    private String csid;
    private String dsca;
    private Integer number;
    private Float fahh;

    public FahhVO() {
    }

    public FahhVO(String csid, String dsca, Integer number, Float fahh) {
        this.csid = csid;
        this.dsca = dsca;
        this.number = number;
        this.fahh = fahh;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public Float getFahh() {
        return fahh;
    }

    public void setFahh(Float fahh) {
        this.fahh = fahh;
    }
}
