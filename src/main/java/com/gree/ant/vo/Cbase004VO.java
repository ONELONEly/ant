package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 004 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 进程角色表实体.中间表
 * @title Cbase004VO
 * @createTime 2017 :09:01 10:09:30.
 */
@Table("CBASE004")
@PK({"pono","roid"})
public class Cbase004VO extends ValueObject{

    /**
     * The Pono.
     *
     * @description 进程号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:30.
     */
    private String pono;
    /**
     * The Roid.
     *
     * @description 角色编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:30.
     */
    private String roid;

    public Cbase004VO() {

    }

    public Cbase004VO(String pono, String roid) {
        this.pono = pono;
        this.roid = roid;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getRoid() {
        return roid;
    }

    public void setRoid(String roid) {
        this.roid = roid;
    }
}
