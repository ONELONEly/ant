package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * The type Tbuss 005 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 评分结果表的实体
 * @title Tbuss005VO
 * @createTime 2017 :09:13 09:09:37.
 */
@Table("TBUSS005")
public class Tbuss005VO extends ValueObject{

    /**
     * The Ptno.
     *
     * @description 绩效表的编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private String ptno;
    /**
     * The Csid.
     *
     * @description 接收人的ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private String csid;
    /**
     * The Pjno.
     *
     * @description 绩效评分项的ID,--类型
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private String pjno;
    /**
     * The Cons.
     *
     * @description 本条任务的成绩---绩效评分项的成绩/本类型任务的总条数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private String cons;
    /**
     * The Remk.
     *
     * @description 备注
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private String remk;
    /**
     * The Cdat.
     *
     * @description 创建日期
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private Date cdat;
    /**
     * The Pdat.
     *
     * @description 考评时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:37.
     */
    private Date pdat;

    @One(target = Cbase011VO.class,field = "pjno")
    private Cbase011VO cbase011VO;

    @One(target = Cbase000VO.class,field = "csid")
    private Cbase000VO cbase000VO;

    public Tbuss005VO() {
    }

    public Tbuss005VO(String ptno, String csid, String pjno, String cons, String remk, Date cdat, Date pdat) {
        this.ptno = ptno;
        this.csid = csid;
        this.pjno = pjno;
        this.cons = cons;
        this.remk = remk;
        this.cdat = cdat;
        this.pdat = pdat;
    }

    public Cbase000VO getCbase000VO() {
        return cbase000VO;
    }

    public void setCbase000VO(Cbase000VO cbase000VO) {
        this.cbase000VO = cbase000VO;
    }

    public Cbase011VO getCbase011VO() {
        return cbase011VO;
    }

    public void setCbase011VO(Cbase011VO cbase011VO) {
        this.cbase011VO = cbase011VO;
    }

    public String getPtno() {
        return ptno;
    }

    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getPjno() {
        return pjno;
    }

    public void setPjno(String pjno) {
        this.pjno = pjno;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getRemk() {
        return remk;
    }

    public void setRemk(String remk) {
        this.remk = remk;
    }

    public Date getCdat() {
        return cdat;
    }

    public void setCdat(Date cdat) {
        this.cdat = cdat;
    }

    public Date getPdat() {
        return pdat;
    }

    public void setPdat(Date pdat) {
        this.pdat = pdat;
    }
}
