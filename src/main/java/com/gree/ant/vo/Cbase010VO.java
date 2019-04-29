package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 010 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用户团队关联表
 * @title Cbase010VO
 * @createTime 2017 :08:30 10:08:52.
 */
@Table("CBASE010")
@PK({"usid","grop"})
public class Cbase010VO extends ValueObject{

    /**
     * The Usid.
     *
     * @description 用户ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 10:08:52.
     */
    private String usid;

    /**
     * The Grop.
     *
     * @description 组ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 10:08:52.
     */
    private String grop;

    private Integer boss;

    public Cbase010VO() {

    }

    public Cbase010VO(String usid) {
        this.usid = usid;
    }

    public Cbase010VO(String usid, String grop) {
        this.usid = usid;
        this.grop = grop;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getGrop() {
        return grop;
    }

    public void setGrop(String grop) {
        this.grop = grop;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }
}
