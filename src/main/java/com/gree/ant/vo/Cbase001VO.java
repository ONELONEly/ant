package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 180365 on 2017/8/3/16:19.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description Cbase001的实体
 * @title Cbase001VO
 * @createTime 2017 :08:12 10:08:05.
 */
@Table("CBASE001")
public class Cbase001VO extends ValueObject{

    /**
     * The Comp.
     *
     * @description 公司代码
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:12 10:08:31.
     */
    @Name
    private String comp;
    /**
     * The Dsca.
     *
     * @description 描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:12 10:08:34.
     */
    private String dsca;
    /**
     * The Sta 1.
     *
     * @description 是否显示0,1
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:12 10:08:38.
     */
    private String sta1;

    public Cbase001VO() {
    }

    public Cbase001VO(String comp) {
        this.comp = comp;
    }

    public Cbase001VO(String comp, String dsca) {
        this.comp = comp;
        this.dsca = dsca;
    }

    public Cbase001VO(String comp, String dsca,String sta1) {
        this.comp = comp;
        this.dsca = dsca;
        this.sta1 = sta1;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getSta1() {
        return sta1;
    }

    public void setSta1(String sta1) {
        this.sta1 = sta1;
    }
}
