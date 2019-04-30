package com.gree.ant.vo;


import com.gree.ant.vo.response.DocVO;
import org.nutz.dao.entity.annotation.*;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

/**
 * The type Tbuss 009 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 发表文档表的操作
 * @title Tbuss009VO
 * @createTime 2017 :09:20 05:09:07.
 */
@Table("Tbuss009")
@View("V_Tbuss009")
public class Tbuss009VO extends ValueObject{

    /**
     * The Number.
     *
     * @description 文档编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    @Id(auto = false)
    private Long doid;
    /**
     * The Note.
     *
     * @description 文档内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private Clob note;
    /**
     * The Cdat.
     *
     * @description 创建时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private Date cdat;
    /**
     * The Usid.
     *
     * @description 创建者
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private String usid;
    /**
     * The Unam.
     *
     * @description 创建者的名字
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    @Readonly
    private String unam;
    /**
     * The Csid.
     *
     * @description 接收者
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private String csid;
    /**
     * The Cnam.
     *
     * @description 接收者的名字
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    @Readonly
    private String cnam;
    /**
     * The Ctyp.
     *
     * @description 文档类型
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private String ctyp;
    /**
     * The Ctypnam.
     *
     * @description 文档类型名字
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    @Readonly
    private String ctypnam;
    /**
     * The Stat.
     *
     * @description 文档的权限。0:私有1:组内 2:科室 3:部门 4:公司 5:完全公开
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private Integer stat;

    @Readonly
    private String statnam;
    /**
     * The Sta 2.
     *
     * @description 文档的状态。0-显示；1-隐藏；默认显示
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private Integer sta2;

    @Readonly
    private String sta2nam;
    /**
     * The Tilt.
     *
     * @description 辩题
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:07.
     */
    private String tilt;

    private String sdat;

    private String week;

    private String grop;

    public void setWeek(String week) {
        this.week = week;
    }

    public void setGrop(String grop) {
        this.grop = grop;
    }

    public String getWeek() {
        return week;
    }

    public String getGrop() {
        return grop;
    }

    public void setSdat(String sdat) {
        this.sdat = sdat;
    }

    public String getSdat() {

        return sdat;
    }

    @Many(target = Tbuss015VO.class,field = "doid")
    private List<Tbuss015VO> tbuss015VOS;

    public Tbuss009VO() {
    }

    public Tbuss009VO(Long doid, Date cdat, String unam, String tilt) {
        this.doid = doid;
        this.cdat = cdat;
        this.unam = unam;
        this.tilt = tilt;
    }

    public Tbuss009VO(Long doid, Date cdat, String usid, String unam, String csid, String cnam, String ctyp, String ctypnam, Integer stat, String statnam, Integer sta2, String sta2nam, String tilt) {
        this.doid = doid;
        this.cdat = cdat;
        this.usid = usid;
        this.unam = unam;
        this.csid = csid;
        this.cnam = cnam;
        this.ctyp = ctyp;
        this.ctypnam = ctypnam;
        this.stat = stat;
        this.statnam = statnam;
        this.sta2 = sta2;
        this.sta2nam = sta2nam;
        this.tilt = tilt;
    }

    public Tbuss009VO(Long doid) {
        this.doid = doid;
    }

    public List<Tbuss015VO> getTbuss015VOS() {
        return tbuss015VOS;
    }

    public void setTbuss015VOS(List<Tbuss015VO> tbuss015VOS) {
        this.tbuss015VOS = tbuss015VOS;
    }

    public Long getDoid() {
        return doid;
    }

    public void setDoid(Long doid) {
        this.doid = doid;
    }

    public Clob getNote() {
        return note;
    }

    public void setNote(Clob note) {
        this.note = note;
    }

    public Date getCdat() {
        return cdat;
    }

    public void setCdat(Date cdat) {
        this.cdat = cdat;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getUnam() {
        return unam;
    }

    public void setUnam(String unam) {
        this.unam = unam;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getCnam() {
        return cnam;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
    }

    public String getCtyp() {
        return ctyp;
    }

    public void setCtyp(String ctyp) {
        this.ctyp = ctyp;
    }

    public String getCtypnam() {
        return ctypnam;
    }

    public void setCtypnam(String ctypnam) {
        this.ctypnam = ctypnam;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getSta2() {
        return sta2;
    }

    public void setSta2(Integer sta2) {
        this.sta2 = sta2;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getStatnam() {
        return statnam;
    }

    public void setStatnam(String statnam) {
        this.statnam = statnam;
    }

    public String getSta2nam() {
        return sta2nam;
    }

    public void setSta2nam(String sta2nam) {
        this.sta2nam = sta2nam;
    }

    public DocVO getDocVO(){
        return new DocVO(this.doid,this.tilt,this.cdat,this.unam);
    }
}
