package com.gree.ant.vo.util;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 导出成绩绩效OKR报表时的实体
 * @createTime 2019 -01-07 11:36:18
 */
public class ExportGradeOkrVO {

    /**
     * @description 用户邮箱
     * @createTime 2019 -01-09 09:56:07
     * @version 1.0
     */
    private String csid;
    /**
     * @description 用户编号
     * @createTime 2019 -01-07 11:36:18
     * @version 1.0
     */
    private String cpid;

    /**
     * @description 用户名称
     * @createTime 2019 -01-07 15:10:39
     * @version 1.0
     */
    private String dsca;

    /**
     * @description 用户分数
     * @createTime 2019 -01-07 15:10:39
     * @version 1.0
     */
    private Double score;

    /**
     * @description 绩效评分等级
     * @createTime 2019 -01-07 11:36:18
     * @version 1.0
     */
    private String stage;

    public ExportGradeOkrVO(String cpid, String dsca, Double score, String stage) {
        this.cpid = cpid;
        this.dsca = dsca;
        this.score = score;
        this.stage = stage;
    }

    public ExportGradeOkrVO(String csid, String cpid, String dsca, Double score) {
        this.csid = csid;
        this.cpid = cpid;
        this.dsca = dsca;
        this.score = score;
    }



    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
