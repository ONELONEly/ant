package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

import java.awt.*;
import java.util.List;


/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理表对应实体
 */
@Table("TBUSS011")
@View("V_TBUSS011")
public class Tbuss011VO extends ValueObject{


    /**
     * @description OKR管理表索引
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Id
    @Prev(@SQL("select nvl(max(OKID)+1,1) from Tbuss011"))
    private Integer OKID;

    /**
     * @description 管理对象
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String ASID;

    /**
     * @description 管理对象名称
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Readonly
    private String ANAM;
    /**
     * @description 直接领导
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String BOSS;

    /**
     * @description 直系领导名称
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Readonly
    private String BNAM;
    /**
     * @description 管理周期 （默认当月）
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private String MDAT;

    /**
     * @description 提交类型
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer TYPE;

    @Readonly
    private String TYPENAM;

    @Readonly
    private String ACCONAM;

    /**
     * @description 是否是可编辑状态
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Integer stat;

    @Readonly
    private Float GRADE = Float.valueOf("0");
    /**
     * @description 目标
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */

    @Many(target = Tbuss012VO.class,field = "okid",key = "OKID")
    private List<Tbuss012VO> tbuss012VOS;

    public Tbuss011VO() {

    }

    public Tbuss011VO(String ASID, String BOSS, String MDAT,Integer TYPE) {
        this.ASID = ASID;
        this.BOSS = BOSS;
        this.MDAT = MDAT;
        this.TYPE = TYPE;
    }

    public Tbuss011VO(String ASID,String ANAM,String BOSS,String BNAM,String MDAT,Integer OKID,Integer stat,Float grade,Integer TYPE,String TYPENAM,String ACCONAM) {
        this.ASID = ASID;
        this.ANAM = ANAM;
        this.BOSS = BOSS;
        this.BNAM = BNAM;
        this.MDAT = MDAT;
        this.OKID = OKID;
        this.stat = stat;
        this.GRADE = grade;
        this.TYPE = TYPE;
        this.TYPENAM = TYPENAM;
        this.ACCONAM = ACCONAM;
    }

    public Integer getOKID() {
        return OKID;
    }

    public void setOKID(Integer OKID) {
        this.OKID = OKID;
    }

    public String getASID() {
        return ASID;
    }

    public void setASID(String ASID) {
        this.ASID = ASID;
    }

    public String getANAM() {
        return ANAM;
    }

    public void setANAM(String ANAM) {
        this.ANAM = ANAM;
    }

    public String getBOSS() {
        return BOSS;
    }

    public void setBOSS(String BOSS) {
        this.BOSS = BOSS;
    }

    public String getBNAM() {
        return BNAM;
    }

    public void setBNAM(String BNAM) {
        this.BNAM = BNAM;
    }

    public String getMDAT() {
        return MDAT;
    }

    public void setMDAT(String MDAT) {
        this.MDAT = MDAT;
    }

    public List<Tbuss012VO> getTbuss012VOS() {
        return tbuss012VOS;
    }

    public void setTbuss012VOS(List<Tbuss012VO> tbuss012VOS) {
        this.tbuss012VOS = tbuss012VOS;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Float getGRADE() {
        return GRADE;
    }

    public void setGRADE(Float GRADE) {
        this.GRADE = GRADE;
    }

    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPENAM() {
        return TYPENAM;
    }

    public void setTYPENAM(String TYPENAM) {
        this.TYPENAM = TYPENAM;
    }

    public String getACCONAM() {
        return ACCONAM;
    }

    public void setACCONAM(String ACCONAM) {
        this.ACCONAM = ACCONAM;
    }
}
