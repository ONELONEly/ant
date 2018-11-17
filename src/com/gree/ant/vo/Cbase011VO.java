package com.gree.ant.vo;
import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * The type Cbase 011 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 绩效评估表的实体
 * @title Cbase011VO
 * @createTime 2017 :08:30 01:08:26.
 */
@Table("CBASE011")
public class Cbase011VO extends ValueObject{

    /**
     * The Pjno.
     *
     * @description 绩效评估项目编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    @Name
//    @Prev(@SQL("select 'P'||(substr(MAX(t.pjno),2)+1) from CBASE011 t"))
    private String pjno;
    /**
     * The Dsca.
     *
     * @description 描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String dsca;
    /**
     * The Plsu.
     *
     * @description 评估占比
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String plsu;
    /**
     * The Pjjp.
     *
     * @description 分项占比
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String pjjp;
    /**
     * The Deti.
     *
     * @description 评估细则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String deti;
    /**
     * The Cons.
     *
     * @description 基础分数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String cons;
    /**
     * The Remk.
     *
     * @description 备注
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String remk;
    /**
     * The Usid.
     *
     * @description 创建用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private String usid;
    /**
     * The Udat.
     *
     * @description 创建时间
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:26.
     */
    private Date udat;

    /**
     * The Stat.
     *
     * @description 规则类型 1：自动，0：手动
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:12 04:09:35.
     */
    private Integer stat;

    /**
     * @description 规则属性 1：计划，0：临时
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer type;

    /**
     * The Cbase 012 vos.
     *
     * @description 评分项对应分值
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 01:08:39.
     */
    @Many(target = Cbase012VO.class,field = "pjno")
    private List<Cbase012VO> cbase012VOS;

    @One(target = Cbase000VO.class,field = "usid")
    private Cbase000VO cbase000VO;

    public Cbase011VO() {
    }

    public Cbase011VO(String pjno) {
        this.pjno = pjno;
    }

    public Cbase011VO(String pjno, String dsca, String plsu, String pjjp, String deti, String cons, String remk, String usid, Date udat,Integer stat) {
        this.pjno = pjno;
        this.dsca = dsca;
        this.plsu = plsu;
        this.pjjp = pjjp;
        this.deti = deti;
        this.cons = cons;
        this.remk = remk;
        this.usid = usid;
        this.udat = udat;
        this.stat = stat;
    }

    public Cbase000VO getCbase000VO() {
        return cbase000VO;
    }

    public void setCbase000VO(Cbase000VO cbase000VO) {
        this.cbase000VO = cbase000VO;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getPjno() {
        return pjno;
    }

    public void setPjno(String pjno) {
        this.pjno = pjno;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getPlsu() {
        return plsu;
    }

    public void setPlsu(String plsu) {
        this.plsu = plsu;
    }

    public String getPjjp() {
        return pjjp;
    }

    public void setPjjp(String pjjp) {
        this.pjjp = pjjp;
    }

    public String getDeti() {
        return deti;
    }

    public void setDeti(String deti) {
        this.deti = deti;
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

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Date getUdat() {
        return udat;
    }

    public void setUdat(Date udat) {
        this.udat = udat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Cbase012VO> getCbase012VOS() {
        return cbase012VOS;
    }

    public void setCbase012VOS(List<Cbase012VO> cbase012VOS) {
        this.cbase012VOS = cbase012VOS;
    }
}
