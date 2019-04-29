package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 017 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 科室表实体
 * @title Cbase017VO
 * @createTime 2017 :09:28 11:09:59.
 */
@Table("CBASE017")
public class Cbase017VO extends ValueObject{

    /**
     * The Acco.
     *
     * @description 科室编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 11:09:31.
     */
    @Name
    private String acco;
    /**
     * The Dsca.
     *
     * @description 科室描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 11:09:31.
     */
    private String dsca;

    public Cbase017VO() {
    }

    public Cbase017VO(String acco) {
        this.acco = acco;
    }

    public Cbase017VO(String acco, String dsca) {
        this.acco = acco;
        this.dsca = dsca;
    }

    public String getAcco() {
        return acco;
    }

    public void setAcco(String acco) {
        this.acco = acco;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }
}
