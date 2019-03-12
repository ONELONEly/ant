package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 绩效标准表
 * @createTime 2019 -01-08 16:20:34
 */
@Table("TBUSS017")
@PK({"pdat","acco","csid"})
public class TBuss017VO {

    /**
     * @description 考核月份
     * @createTime 2019 -01-08 16:20:34
     * @version 1.0
     */
    private String pdat;

    /**
     * @description 科室编号
     * @createTime 2019 -01-09 09:15:07
     * @version 1.0
     */
    private String acco;

    /**
     * @description 考核对象
     * @createTime 2019 -01-09 09:15:07
     * @version 1.0
     */
    private String csid;

    /**
     * @description 考核结果分数
     * @createTime 2019 -01-09 09:15:08
     * @version 1.0
     */
    private String score;

    /**
     * @description 考核等级
     * @createTime 2019 -01-09 09:15:08
     * @version 1.0
     */
    private String stage;

    public TBuss017VO() {
    }

    public TBuss017VO(String pdat, String acco, String csid, String score, String stage) {
        this.pdat = pdat;
        this.acco = acco;
        this.csid = csid;
        this.score = score;
        this.stage = stage;
    }

    public String getPdat() {
        return pdat;
    }

    public void setPdat(String pdat) {
        this.pdat = pdat;
    }

    public String getAcco() {
        return acco;
    }

    public void setAcco(String acco) {
        this.acco = acco;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
