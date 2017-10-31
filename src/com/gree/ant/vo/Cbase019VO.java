package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 019 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 邮件组用户的关联实体
 * @title Cbase019VO
 * @createTime 2017 :10:21 06:10:48.
 */
@Table("CBASE019")
@PK({"emid","usid"})
public class Cbase019VO extends ValueObject{

    /**
     * The Emid.
     *
     * @description 邮箱组ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 06:10:02.
     */
    private Integer emid;
    /**
     * The Usid.
     *
     * @description 用户ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 06:10:05.
     */
    private String usid;

    public Cbase019VO() {
    }

    public Integer getEmid() {
        return emid;
    }

    public void setEmid(Integer emid) {
        this.emid = emid;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }
}
