package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 013 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 系统表
 * @title Cbase013VO
 * @createTime 2017 :09:06 10:09:24.
 */
@Table("CBASE013")
public class Cbase013VO extends ValueObject{

    /**
     * The Syno.
     *
     * @description 系统编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:24.
     */
    @Name
    private String syno;
    /**
     * The Dsca.
     *
     * @description 系统描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:24.
     */
    private String dsca;
    /**
     * The Sadd.
     *
     * @description 正式地址
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:25.
     */
    private String sadd;
    /**
     * The Tadd.
     *
     * @description 测试地址
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:25.
     */
    private String tadd;

    public Cbase013VO() {
    }

    public Cbase013VO(String syno) {
        this.syno = syno;
    }

    public Cbase013VO(String syno, String dsca, String sadd, String tadd) {
        this.syno = syno;
        this.dsca = dsca;
        this.sadd = sadd;
        this.tadd = tadd;
    }

    public String getSyno() {
        return syno;
    }

    public void setSyno(String syno) {
        this.syno = syno;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getSadd() {
        return sadd;
    }

    public void setSadd(String sadd) {
        this.sadd = sadd;
    }

    public String getTadd() {
        return tadd;
    }

    public void setTadd(String tadd) {
        this.tadd = tadd;
    }
}
