package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 003 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 进程关系表实体
 * @title Cbase003VO
 * @createTime 2017 :09:01 10:09:29.
 */
@Table("CBASE003")
public class Cbase003VO extends ValueObject{


    /**
     * The Flno.
     *
     * @description 节点号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:29.
     */
    @Name
    private String flno;

    /**
     * The Pono.
     *
     * @description 父节点号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:29.
     */
    private String pono;

    /**
     * The Wezo.
     *
     * @description url地址
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:29.
     */
    private Integer wzno;

    @One(target = Cbase002VO.class,field = "pono")
    private Cbase002VO cbase002VO;

    public Cbase003VO() {
    }

    public Cbase003VO(String flno, String pono) {
        this.flno = flno;
        this.pono = pono;
    }

    public Cbase003VO(String pono, String flno, Integer wzno) {
        this.pono = pono;
        this.flno = flno;
        this.wzno = wzno;
    }

    public Cbase002VO getCbase002VO() {
        return cbase002VO;
    }

    public void setCbase002VO(Cbase002VO cbase002VO) {
        this.cbase002VO = cbase002VO;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getFlno() {
        return flno;
    }

    public void setFlno(String flno) {
        this.flno = flno;
    }

    public Integer getWzno() {
        return wzno;
    }

    public void setWzno(Integer wzno) {
        this.wzno = wzno;
    }
}
