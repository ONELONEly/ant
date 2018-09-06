package com.gree.ant.vo.util;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 选择任务的工具类
 */
public class TaskUtilVO {

    /**
     * @description 接收用户
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String csid;
    /**
     * @description 接受用户姓名
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String cnam;
    /**
     * @description 绩效创建日期
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String pdat;
    /**
     * @description 任务标题
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String titl;

    /**
     * @description 任务编号
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String taid;

    private String note;

    public TaskUtilVO() {
    }

    public TaskUtilVO(String csid, String cnam, String pdat, String titl, String taid, String note) {
        this.csid = csid;
        this.cnam = cnam;
        this.pdat = pdat;
        this.titl = titl;
        this.taid = taid;
        this.note = note;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getCnam() {
        return cnam;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
    }

    public String getPdat() {
        return pdat;
    }

    public void setPdat(String pdat) {
        this.pdat = pdat;
    }


    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
