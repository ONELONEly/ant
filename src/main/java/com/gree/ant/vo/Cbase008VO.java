package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 008 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 科室表实体
 * @title Cbase008VO
 * @createTime 2017 :09:01 10:09:57.
 */
@Table("CBASE008")
public class Cbase008VO extends ValueObject{

    /**
     * The Cyyo.
     *
     * @description 科室编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:57.
     */
    @Name
    private String cyyo;
    /**
     * The Dsca.
     *
     * @description 科室描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:57.
     */
    private String dsca;

    public Cbase008VO() {
    }

    public Cbase008VO(String cyyo, String dsca) {
        this.cyyo = cyyo;
        this.dsca = dsca;
    }

    public String getCyyo() {
        return cyyo;
    }

    public void setCyyo(String cyyo) {
        this.cyyo = cyyo;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }
}
