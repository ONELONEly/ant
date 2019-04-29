package com.gree.ant.vo.util;

/**
 * The type Grade vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 统计成绩时使用的实体
 * @title GradeVO
 * @createTime 2017 :10:27 04:10:57.
 */
public class GradeVO {

    /**
     * The Usid.
     *
     * @description 用户ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:57.
     */
    private String usid;
    /**
     * The Dsca.
     *
     * @description y用户描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:57.
     */
    private String dsca;
    /**
     * The Score.
     *
     * @description 用户分数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:57.
     */
    private Double score;

    public GradeVO(String usid, String dsca, Double score) {
        this.usid = usid;
        this.dsca = dsca;
        this.score = score;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
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
}
