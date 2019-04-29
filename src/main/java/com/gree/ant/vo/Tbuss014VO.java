package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

import java.sql.Clob;
import java.util.Date;

@Table("TBUSS014")
@View("V_TBUSS014")
public class Tbuss014VO {

    @Name
    private String raid;
    private Clob note;
    private Date cdat;
    private String usid;
    private String csid;
    private Integer stat;
    private Integer sta2;
    private Integer sta3;
    private String syno;
    private String titl;
    private Date ydat;

    @Readonly
    private String unam;
    @Readonly
    private String cnam;
    @Readonly
    private String statnam;
    @Readonly
    private String sta2nam;
    @Readonly
    private String sta3nam;
    @Readonly
    private String synonam;


    public Tbuss014VO() {
    }

    public Tbuss014VO(String raid, Clob note, Date cdat, String usid, String csid, Integer stat, Integer sta2, Integer sta3, String syno, String titl) {
        this.raid = raid;
        this.note = note;
        this.cdat = cdat;
        this.usid = usid;
        this.csid = csid;
        this.stat = stat;
        this.sta2 = sta2;
        this.sta3 = sta3;
        this.syno = syno;
        this.titl = titl;
    }

    public Tbuss014VO(String raid,Date cdat, String titl, String unam, String cnam, String statnam, String sta2nam, String sta3nam, String synonam,Date ydat) {
        this.raid = raid;
        this.cdat = cdat;
        this.titl = titl;
        this.unam = unam;
        this.cnam = cnam;
        this.statnam = statnam;
        this.sta2nam = sta2nam;
        this.sta3nam = sta3nam;
        this.synonam = synonam;
        this.ydat = ydat;
    }

    public Tbuss014VO(String raid, Clob note, Date cdat, String usid, String csid, Integer stat, Integer sta2, Integer sta3, String syno, String titl, String unam, String cnam, String statnam, String sta2nam, String sta3nam, String synonam) {
        this.raid = raid;
        this.note = note;
        this.cdat = cdat;
        this.usid = usid;
        this.csid = csid;
        this.stat = stat;
        this.sta2 = sta2;
        this.sta3 = sta3;
        this.syno = syno;
        this.titl = titl;
        this.unam = unam;
        this.cnam = cnam;
        this.statnam = statnam;
        this.sta2nam = sta2nam;
        this.sta3nam = sta3nam;
        this.synonam = synonam;
    }

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid;
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

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
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

    public Integer getSta3() {
        return sta3;
    }

    public void setSta3(Integer sta3) {
        this.sta3 = sta3;
    }

    public String getSyno() {
        return syno;
    }

    public void setSyno(String syno) {
        this.syno = syno;
    }

    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public String getUnam() {
        return unam;
    }

    public void setUnam(String unam) {
        this.unam = unam;
    }

    public String getCnam() {
        return cnam;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
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

    public String getSta3nam() {
        return sta3nam;
    }

    public void setSta3nam(String sta3nam) {
        this.sta3nam = sta3nam;
    }

    public String getSynonam() {
        return synonam;
    }

    public void setSynonam(String synonam) {
        this.synonam = synonam;
    }

    public Date getYdat() {
        return ydat;
    }

    public void setYdat(Date ydat) {
        this.ydat = ydat;
    }

    public Tbuss003VO formatTask(){
        return new Tbuss003VO(this.raid,this.note,this.cdat,this.usid,this.csid,this.sta2,this.sta3,this.syno,this.titl,this.cnam,this.synonam);
    }
}
