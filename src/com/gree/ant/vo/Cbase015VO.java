package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * The type Cbase 015 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文件上传表的实体
 * @title Cbase015VO
 * @createTime 2017 :09:07 03:09:04.
 */
@Table("CBASE015")
public class Cbase015VO extends ValueObject{


    /**
     * The Duta.
     *
     * @description 流水ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    @Name
    private String duta;
    /**
     * The Djid.
     *
     * @description 任务ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:11.
     */
    private String djid;

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

    public Cbase015VO() {
    }

    public Cbase015VO(String duta) {
        this.duta = duta;
    }

    public Cbase015VO(String duta, String djid, String ffil, Integer fsiz, String usid, Date cdat) {
        this.duta = duta;
        this.djid = djid;
        this.ffil = ffil;
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

    public String getDjid() {
        return djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }

    public String getFfil() {
        return ffil;
    }

    public void setFfil(String ffil) {
        this.ffil = ffil;
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
