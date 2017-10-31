package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * The type Cbase 002 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 进程表实体.
 * @title Cbase002VO
 * @createTime 2017 :09:01 10:09:44.
 */
@Table("CBASE002")
@View("V_CBASE002")
public class Cbase002VO extends ValueObject{

    /**
     * The Pono.
     *
     * @description 进程号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    @Name
    private String pono;
    /**
     * The Dsca.
     *
     * @description 描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    private String dsca;
    /**
     * The Styp.
     *
     * @description 节点类型
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    private int styp;
    /**
     * The Stypnam.
     *
     * @description 类型名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 04:10:31.
     */
    @Readonly
    private String stypnam;
    /**
     * The Purl.
     *
     * @description 路径
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:44.
     */
    private String purl;

    @Many(target = Cbase003VO.class,field = "pono")
    private List<Cbase003VO> cbase003VOS;


    @Readonly
    private Cbase003VO cbase003VO;

    public Cbase002VO() {

    }

    public Cbase002VO(String pono, String dsca, int styp, String purl) {
        this.pono = pono;
        this.dsca = dsca;
        this.styp = styp;
        this.purl = purl;
    }

    public Cbase002VO(String pono, String dsca, int styp, String stypnam, String purl) {
        this.pono = pono;
        this.dsca = dsca;
        this.styp = styp;
        this.stypnam = stypnam;
        this.purl = purl;
    }

    public Cbase003VO getCbase003VO() {
        return cbase003VO;
    }

    public void setCbase003VO(Cbase003VO cbase003VO) {
        this.cbase003VO = cbase003VO;
    }

    public String getStypnam() {
        return stypnam;
    }

    public void setStypnam(String stypnam) {
        this.stypnam = stypnam;
    }

    public List<Cbase003VO> getCbase003VOS() {
        return cbase003VOS;
    }

    public void setCbase003VOS(List<Cbase003VO> cbase003VOS) {
        this.cbase003VOS = cbase003VOS;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public int getStyp() {
        return styp;
    }

    public void setStyp(int styp) {
        this.styp = styp;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
