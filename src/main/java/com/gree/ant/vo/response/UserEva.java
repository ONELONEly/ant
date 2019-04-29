package com.gree.ant.vo.response;


/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 个人中心返回调用
 * @createTime 2019 -01-09 14:34:21
 */
public class UserEva {

    /**
     * @description 绩效表编号
     * @createTime 2019 -01-09 14:35:45
     * @version 1.0
     */
    private String ptno;
    /**
     * @description 绩效名称
     * @createTime 2019 -01-09 14:35:45
     * @version 1.0
     */
    private String dsca;
    /**
     * @description 绩效考核日期
     * @createTime 2019 -01-09 14:35:45
     * @version 1.0
     */
    private String pdat;
    /**
     * @description 绩效评定等级
     * @createTime 2019 -01-09 14:35:45
     * @version 1.0
     */
    private String stage;

    public UserEva() {
    }

    public UserEva(String ptno, String dsca, String pdat, String stage) {
        this.ptno = ptno;
        this.dsca = dsca;
        this.pdat = pdat;
        this.stage = stage;
    }

    public String getPtno() {
        return ptno;
    }

    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getPdat() {
        return pdat;
    }

    public void setPdat(String pdat) {
        this.pdat = pdat;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
