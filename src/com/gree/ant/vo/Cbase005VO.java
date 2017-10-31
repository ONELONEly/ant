package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 005 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用户角色表，关联表
 * @title Cbase005VO
 * @createTime 2017 :09:01 02:09:29.
 */
@Table("CBASE005")
@PK({"usid","roid"})
public class Cbase005VO extends ValueObject{

    /**
     * The Usid.
     *
     * @description 用户编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:54.
     */
    private String usid;
    /**
     * The Roid.
     *
     * @description 角色编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:58.
     */
    private String roid;

    public Cbase005VO() {
    }

    public Cbase005VO(String usid, String roid) {
        this.usid = usid;
        this.roid = roid;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getRoid() {
        return roid;
    }

    public void setRoid(String roid) {
        this.roid = roid;
    }
}
