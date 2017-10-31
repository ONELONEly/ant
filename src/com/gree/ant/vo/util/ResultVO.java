package com.gree.ant.vo.util;

/**
 * The type Result vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description UtilController共用的实体
 * @title ResultVO
 * @createTime 2017 :10:26 04:10:12.
 */
public class ResultVO {

    /**
     * The Id.
     *
     * @description ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 04:10:41.
     */
    private String id;
    /**
     * The Dsca.
     *
     * @description 描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 04:10:43.
     */
    private String dsca;

    public ResultVO(String id, String dsca) {
        this.id = id;
        this.dsca = dsca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }
}
