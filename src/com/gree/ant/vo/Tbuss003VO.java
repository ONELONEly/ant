package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

/**
 * The type Tbuss 003 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务单表实体
 * @title Tbuss003VO
 * @createTime 2017 :09:06 10:09:35.
 */
@Table("TBUSS003")
@View("V_TBUSS003")
public class Tbuss003VO extends ValueObject{

    /**
     * The Taid.
     *
     * @description 任务单号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    @Name
    private String taid;

    /**
     * The Titl.
     *
     * @description 标题
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 03:09:29.
     */
    private String titl;
    /**
     * The Note.
     *
     * @description 任务内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private Clob note;
    /**
     * The Cdat.
     *
     * @description 创建时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private Date cdat;
    /**
     * The Usid.
     *
     * @description 创建人
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String usid;

    @Readonly
    private String unam;

    /**
     * The Csid.
     *
     * @description 接收人
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String csid;

    @Readonly
    private String cnam;
    /**
     * The Sta 1.
     *
     * @description 状态:0：保存，1，下发，2，执行，3,驳回，4，变更，5，交付测试，6，交付验收，7，测试通过，8，测试不通过，9，验收通过，10，验收不通过，11，任务结束
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private Integer sta1;

    @Readonly
    private String sta1nam;
    /**
     * The Ptyp.
     *
     * @description 类型
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String ptyp;

    @Readonly
    private String cons;

    @Readonly
    private String ptypnam;
    /**
     * The Sta 2.
     *
     * @description 紧急状态：0,普通，1，急，2，紧急
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private Integer sta2;

    @Readonly
    private String sta2nam;
    /**
     * The Tepr.
     *
     * @description 测试用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String tepr;

    /**
     * The Tnam.
     *
     * @description 测试人姓名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 02:09:29.
     */
    @Readonly
    private String tnam;
    /**
     * The Pdat.
     *
     * @description 计划完成时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private Date pdat;
    /**
     * The Ksid.
     *
     * @description 关键用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String ksid;

    @Readonly
    private String knam;
    /**
     * The Tadd.
     *
     * @description 测试备注
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String tadd;
    /**
     * The Sta 3.
     *
     * @description 重要程度：0-一般，1-重要
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private Integer sta3;

    @Readonly
    private String sta3nam;
    /**
     * The Syno.
     *
     * @description 系统编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:35.
     */
    private String syno;

    @Readonly
    private String synonam;

    /**
     * The Rsid.
     *
     * @description 验收人
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:25.
     */
    private String rsid;

    @Readonly
    private String rnam;

    /**
     * The Puno.
     *
     * @description 任务类型字段
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 03:09:46.
     */
    private String puno;

    @Readonly
    private String punonam;

    /**
     * The Ptno.
     *
     * @description 项目编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:12 04:09:24.
     */
    private String ptno;

    /**
     * The Ptnonam.
     *
     * @description 项目编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 03:09:54.
     */
    @Readonly
    private String ptnonam;

    /**
     * The Acconam.
     *
     * @description 接收人科室名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 11:09:58.
     */
    @Readonly
    private String acconam;

    /**
     * The Kaconam.
     *
     * @description 关键用户科室名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:27 02:09:37.
     */
    @Readonly
    private String kdnam;

    /**
     * The Xdat.
     *
     * @description 下发时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 11:09:22.
     */
    private Date xdat;

    /**
     * The Fdat.
     *
     * @description 交付验收时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 11:09:41.
     */
    private Date fdat;

    /**
     * The Tdat.
     *
     * @description 交付测试时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 11:09:59.
     */
    private Date tdat;

    /**
     * The Adat.
     *
     * @description 执行时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:08 10:10:56.
     */
    private Date adat;

    /**
     * The Stage.
     *
     * @description 任务的等级
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 06:10:51.
     */
    private Integer stag;

    /**
     * The Perc.
     *
     * @description 完成率
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 03:10:30.
     */
    @Readonly
    private String perc;

    /**
     * The Eye.
     *
     * @description 是否关注
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 03:10:41.
     */
    @Readonly
    private String eye;

    /**
     * The Fahh.
     *
     * @description 花费工时
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 03:10:05.
     */
    private Float fahh;

    @One(target = Cbase011VO.class,field = "ptyp")
    private Cbase011VO cbase011VO;

    @Many(target = Cbase015VO.class,field = "djid")
    private List<Cbase015VO> cbase015VOS;

    @Many(target = Tbuss010VO.class,field = "taid")
    private List<Tbuss010VO> tbuss010VOS;

    public Tbuss003VO() {
    }

    /**
     * Instantiates a new Tbuss 003 vo.
     *
     * @description 绩效管理任务时使用
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 11:10:17.
     */
    public Tbuss003VO(String taid, String titl, Date cdat, String cnam, String sta1nam, Date pdat, String knam, String synonam, String punonam, Date fdat, Date tdat, Date adat) {
        this.taid = taid;
        this.titl = titl;
        this.cdat = cdat;
        this.cnam = cnam;
        this.sta1nam = sta1nam;
        this.pdat = pdat;
        this.knam = knam;
        this.synonam = synonam;
        this.punonam = punonam;
        this.fdat = fdat;
        this.tdat = tdat;
        this.adat = adat;
    }

    /**
     * Instantiates a new Tbuss 003 vo.
     *
     * @description 任务管理，看板管理时使用
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 11:10:32.
     */
    public Tbuss003VO(String taid,String titl, Date cdat,String unam, String cnam, String sta1nam, String ptypnam, String sta2nam, Date pdat, String knam, String sta3nam, String synonam, String punonam, Date fdat, Date tdat, Date adat) {
        this.taid = taid;
        this.titl = titl;
        this.cdat = cdat;
        this.unam = unam;
        this.cnam = cnam;
        this.sta1nam = sta1nam;
        this.ptypnam = ptypnam;
        this.sta2nam = sta2nam;
        this.pdat = pdat;
        this.knam = knam;
        this.sta3nam = sta3nam;
        this.synonam = synonam;
        this.punonam = punonam;
        this.fdat = fdat;
        this.tdat = tdat;
        this.adat = adat;
    }

    public List<Tbuss010VO> getTbuss010VOS() {
        return tbuss010VOS;
    }

    public void setTbuss010VOS(List<Tbuss010VO> tbuss010VOS) {
        this.tbuss010VOS = tbuss010VOS;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(Integer coun) {
        if(coun == 1) {
            this.eye = "<a href='javascript:' class='layui-btn layui-btn-xs layui-btn-danger' lay-event='out'>取消</a>";
        }else{
            this.eye = "<a href='javascript:' class='layui-btn layui-btn-xs layui-bg-black' lay-event='in'>关注</a>";
        }
    }

    public Float getFahh() {
        return fahh;
    }

    public void setFahh(Float fahh) {
        this.fahh = fahh;
    }

    public Integer getStag() {
        return stag;
    }

    public void setStag(Integer stag) {
        this.stag = stag;
    }

    public List<Cbase015VO> getCbase015VOS() {
        return cbase015VOS;
    }

    public void setCbase015VOS(List<Cbase015VO> cbase015VOS) {
        this.cbase015VOS = cbase015VOS;
    }

    public String getPerc() {
        return perc;
    }

    public void setPerc(Double perc) {
        String res = "%";
        if(perc == null || perc <= 0){
            res = 0+res;
        }else if(perc>1){
            res = 100 + res;
        }else if(perc>0){
            String rose = (perc*100+"");
            if(rose.length() == 3){
                res = rose.substring(0,1)+res;
            }else{
                res = rose.substring(0,2)+res;
            }
        }
        this.perc = res;
    }

    public Date getAdat() {
        return adat;
    }

    public void setAdat(Date adat) {
        this.adat = adat;
    }

    public String getKdnam() {
        return kdnam;
    }

    public void setKdnam(String kdnam) {
        this.kdnam = kdnam;
    }

    public Cbase011VO getCbase011VO() {
        return cbase011VO;
    }

    public void setCbase011VO(Cbase011VO cbase011VO) {
        this.cbase011VO = cbase011VO;
    }

    public String getAcconam() {
        return acconam;
    }

    public void setAcconam(String acconam) {
        this.acconam = acconam;
    }

    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public Tbuss003VO(String taid) {
        this.taid = taid;
    }

    public String getPtnonam() {
        return ptnonam;
    }

    public void setPtnonam(String ptnonam) {
        this.ptnonam = ptnonam;
    }

    public String getTnam() {
        return tnam;
    }

    public void setTnam(String tnam) {
        this.tnam = tnam;
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

    public String getSta1nam() {
        return sta1nam;
    }

    public void setSta1nam(String sta1nam) {
        this.sta1nam = sta1nam;
    }

    public String getSynonam() {
        return synonam;
    }

    public void setSynonam(String synonam) {
        this.synonam = synonam;
    }

    public String getRnam() {
        return rnam;
    }

    public void setRnam(String rnam) {
        this.rnam = rnam;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
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

    public String getCnam() {
        return cnam;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
    }

    public Integer getSta1() {
        return sta1;
    }

    public void setSta1(Integer sta1) {
        this.sta1 = sta1;
    }

    public String getPtyp() {
        return ptyp;
    }

    public void setPtyp(String ptyp) {
        this.ptyp = ptyp;
    }

    public Integer getSta2() {
        return sta2;
    }

    public void setSta2(Integer sta2) {
        this.sta2 = sta2;
    }

    public String getTepr() {
        return tepr;
    }

    public void setTepr(String tepr) {
        this.tepr = tepr;
    }

    public Date getPdat() {
        return pdat;
    }

    public void setPdat(Date pdat) {
        this.pdat = pdat;
    }

    public String getKnam() {
        return knam;
    }

    public void setKnam(String knam) {
        this.knam = knam;
    }

    public String getTadd() {
        return tadd;
    }

    public void setTadd(String tadd) {
        this.tadd = tadd;
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

    public String getPuno() {
        return puno;
    }

    public void setPuno(String puno) {
        this.puno = puno;
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

    public String getKsid() {
        return ksid;
    }

    public void setKsid(String ksid) {
        this.ksid = ksid;
    }

    public String getRsid() {
        return rsid;
    }

    public void setRsid(String rsid) {
        this.rsid = rsid;
    }

    public String getPtypnam() {
        return ptypnam;
    }

    public void setPtypnam(String ptypnam) {
        this.ptypnam = ptypnam;
    }

    public String getPunonam() {
        return punonam;
    }

    public void setPunonam(String punonam) {
        this.punonam = punonam;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getPtno() {
        return ptno;
    }

    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    public Date getXdat() {
        return xdat;
    }

    public void setXdat(Date xdat) {
        this.xdat = xdat;
    }

    public Date getFdat() {
        return fdat;
    }

    public void setFdat(Date fdat) {
        this.fdat = fdat;
    }

    public Date getTdat() {
        return tdat;
    }

    public void setTdat(Date tdat) {
        this.tdat = tdat;
    }
}
