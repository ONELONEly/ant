package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * The type Tbuss 015 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文档表
 * @title Tbuss015VO
 * @createTime 2017 :09:21 02:09:37.
 */
@Table("Tbuss015")
public class Tbuss015VO extends ValueObject{


    /**
     * The Duta.
     *
     * @description 流水号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:24 09:10:59.
     */
    @Name
    private String duta;
    /**
     * The Ffil.
     *
     * @description 文件名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    private String ffil;
    /**
     * The Duta.
     *
     * @description 流水ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    private Long doid;
    /**
     * The Fsiz.
     *
     * @description 文件大小
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    private Integer fsiz;
    /**
     * The Usid.
     *
     * @description 上传用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    private String usid;
    /**
     * The Cdat.
     *
     * @description 上传时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    private Date cdat;

    public Tbuss015VO() {
    }

    public Tbuss015VO(String duta, String ffil, Long doid, Integer fsiz, String usid, Date cdat) {
        this.duta = duta;
        this.ffil = ffil;
        this.doid = doid;
        this.fsiz = fsiz;
        this.usid = usid;
        this.cdat = cdat;
    }

    public String getDuta() {
        return duta;
    }

    public void setDuta(String duta) {
        this.duta = duta;
    }

    public Tbuss015VO(String ffil) {
        this.ffil = ffil;
    }

    public String getFfil() {
        return ffil;
    }

    public void setFfil(String ffil) {
        this.ffil = ffil;
    }

    public Long getDoid() {
        return doid;
    }

    public void setDoid(Long doid) {
        this.doid = doid;
    }

    public Integer getFsiz() {
        return fsiz;
    }

    public void setFsiz(Integer fsiz) {
        this.fsiz = fsiz;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Date getCdat() {
        return cdat;
    }

    public void setCdat(Date cdat) {
        this.cdat = cdat;
    }
}
