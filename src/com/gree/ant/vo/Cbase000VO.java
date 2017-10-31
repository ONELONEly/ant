package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.*;

import java.sql.Blob;
import java.util.List;

/**
 * Created by 180365 on 2017/7/28/10:07.
 *
 * @author create by jinyuk@foxmail.com.
 * @description 用户表
 */
@Table("CBASE000")
@View("v_CBASE000")
public class Cbase000VO extends ValueObject{

    /**
     * The Usid.
     *
     * @description 用户编号
     * @author create by jinyuk@foxmail.com.
     */
    @Name
    private String USID;

    /**
     * The Dsca.
     *
     * @description 用户名称
     * @author create by jinyuk@foxmail.com.
     */
    @Column("DSCA")
    private String DSCA;

    /**
     * The Pawd.
     *
     * @description 密码
     * @author create by jinyuk@foxmail.com.
     */
    @Column("PAWD")
    private String PAWD;

    /**
     * The Group.
     *
     * @description 组
     * @author create by jinyuk@foxmail.com.
     */
    @Column("GROP")
    private String GROP;

    /**
     * The Geopnam.
     *
     * @description 团队名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 10:09:06.
     */
    @Readonly
    @Column("GROPNAM")
    private String GROPNAM;

    /**
     * The Dept.
     *
     * @description 部门
     * @author create by jinyuk@foxmail.com.
     */
    @Column("DEPT")
    private String DEPT;

    /**
     * The Deptnam.
     *
     * @description 部门名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 10:09:32.
     */
    @Readonly
    @Column("DEPTNAM")
    private String DEPTNAM;

    /**
     * The Flag.
     *
     * @description 状态,1-后门用户，0-前台用户
     * @author create by jinyuk@foxmail.com.
     */
    @Column("FLAG")
    private Integer FLAG;

    /**
     * The Comp.
     *
     * @description 公司编号
     * @author create by jinyuk@foxmail.com.
     */
    @Column("COMP")
    private String COMP;

    /**
     * The Compnam.
     *
     * @description 公司名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 10:09:03.
     */
    @Readonly
    @Column("COMPNAM")
    private String COMPNAM;

    /**
     * The Cpid.
     *
     * @description 厂牌编号
     * @author create by jinyuk@foxmail.com.
     */
    @Column("CPID")
    private String CPID;

    /**
     * The Mail.
     *
     * @description 邮箱
     * @author create by jinyuk@foxmail.com.
     */
    @Column("MAIL")
    private String MAIL;

    /**
     * The Acco.
     *
     * @description 科室-Cbase017
     * @author create by jinyuk@foxmail.com.
     */
    @Column("ACCO")
    private String ACCO;

    /**
     * The Acconam.
     *
     * @description 科室名称
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 04:10:02.
     */
    @Readonly
    @Column("ACCONAM")
    private String ACCONAM;

    /**
     * The Bank.
     *
     * @description 银行账号
     * @author create by jinyuk@foxmail.com.
     */
    @Column("BANK")
    private String BANK;

    /**
     * The Tel 1.
     *
     * @description 手机号码
     * @author create by jinyuk@foxmail.com.
     */
    @Column("TEL1")
    private String TEL1;

    /**
     * The Remk.
     *
     * @description 备注
     * @author create by jinyuk@foxmail.com.
     */
    @Column("REMK")
    private String REMK;

    /**
     * The Jwwj.
     *
     * @description 岗位
     * @author create by jinyuk@foxmail.com.
     */
    @Column("JWWJ")
    private String JWWJ;

    /**
     * The Sta 1.
     *
     * @description 是否委托
     * @author create by jinyuk@foxmail.com.
     */
    @Column("STA1")
    private Integer STA1;

    /**
     * The To id.
     *
     * @description KEYID
     * @author create by jinyuk@foxmail.com.
     */
    @Column("TOID")
    private String TOID;

    /**
     * The Ip id.
     *
     * @description IP地址
     * @author create by jinyuk@foxmail.com.
     */
    @Column("IPID")
    private String IPID;

    /**
     * The Comm.
     *
     * @description 计算机名
     * @author create by jinyuk@foxmail.com.
     */
    @Column("COMM")
    private String COMM;

    /**
     * The Jdep.
     *
     * @description 借入部门
     * @author create by jinyuk@foxmail.com.
     */
    @Column("JDEP")
    private String JDEP;

    /**
     * The Sta 2.
     *
     * @description 权限级别
     * @author create by jinyuk@foxmail.com.
     */
    @Column("STA2")
    private Integer STA2;

    /**
     * The Sta 3.
     *
     * @description 是否开短信通知0,1;是
     * @author create by jinyuk@foxmail.com.
     */
    @Column("STA3")
    private Integer STA3;

    /**
     * The Yy no.
     *
     * @description 团队
     * @author create by jinyuk@foxmail.com.
     */
    @Column("YYNO")
    private String YYNO;

    /**
     * The Blob.
     *
     * @description 头像
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 01:09:16.
     */
    @Column("PHOT")
    private Blob BLOB;

    @ManyMany(target = Cbase007VO.class,relation = "CBASE005",from="usid",to="roid")
    private List<Cbase007VO> cbase007VOS;

    public Cbase000VO() {
    }

    public Cbase000VO(String USID, String PAWD) {
        this.USID = USID;
        this.PAWD = PAWD;
    }

    /**
     * Instantiates a new Cbase 000 vo.
     *
     * @description 查询所有用户时使用
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 08:10:32.
     */
    public Cbase000VO(String USID, String DSCA, String PAWD, String GROPNAM, String DEPTNAM, String COMPNAM, String ACCONAM, String JWWJ) {
        this.USID = USID;
        this.DSCA = DSCA;
        this.PAWD = PAWD;
        this.GROPNAM = GROPNAM;
        this.DEPTNAM = DEPTNAM;
        this.COMPNAM = COMPNAM;
        this.ACCONAM = ACCONAM;
        this.JWWJ = JWWJ;
    }

    public String getACCONAM() {
        return ACCONAM;
    }

    public void setACCONAM(String ACCONAM) {
        this.ACCONAM = ACCONAM;
    }

    public List<Cbase007VO> getCbase007VOS() {
        return cbase007VOS;
    }

    public void setCbase007VOS(List<Cbase007VO> cbase007VOS) {
        this.cbase007VOS = cbase007VOS;
    }

    public Blob getBLOB() {
        return BLOB;
    }

    public void setBLOB(Blob BLOB) {
        this.BLOB = BLOB;
    }

    public String getUSID() {
        return USID;
    }

    public void setUSID(String USID) {
        this.USID = USID;
    }

    public String getDSCA() {
        return DSCA;
    }

    public void setDSCA(String DSCA) {
        this.DSCA = DSCA;
    }

    public String getPAWD() {
        return PAWD;
    }

    public void setPAWD(String PAWD) {
        this.PAWD = PAWD;
    }

    public String getGROP() {
        return GROP;
    }

    public void setGROP(String GROP) {
        this.GROP = GROP;
    }

    public String getDEPT() {
        return DEPT;
    }

    public void setDEPT(String DEPT) {
        this.DEPT = DEPT;
    }

    public Integer getFLAG() {
        return FLAG;
    }

    public void setFLAG(Integer FLAG) {
        this.FLAG = FLAG;
    }

    public String getCOMP() {
        return COMP;
    }

    public void setCOMP(String COMP) {
        this.COMP = COMP;
    }

    public String getCPID() {
        return CPID;
    }

    public void setCPID(String CPID) {
        this.CPID = CPID;
    }

    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }

    public String getACCO() {
        return ACCO;
    }

    public void setACCO(String ACCO) {
        this.ACCO = ACCO;
    }

    public String getBANK() {
        return BANK;
    }

    public void setBANK(String BANK) {
        this.BANK = BANK;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getREMK() {
        return REMK;
    }

    public void setREMK(String REMK) {
        this.REMK = REMK;
    }

    public String getJWWJ() {
        return JWWJ;
    }

    public void setJWWJ(String JWWJ) {
        this.JWWJ = JWWJ;
    }

    public Integer getSTA1() {
        return STA1;
    }

    public void setSTA1(Integer STA1) {
        this.STA1 = STA1;
    }

    public String getTOID() {
        return TOID;
    }

    public void setTOID(String TOID) {
        this.TOID = TOID;
    }

    public String getIPID() {
        return IPID;
    }

    public void setIPID(String IPID) {
        this.IPID = IPID;
    }

    public String getCOMM() {
        return COMM;
    }

    public void setCOMM(String COMM) {
        this.COMM = COMM;
    }

    public String getJDEP() {
        return JDEP;
    }

    public void setJDEP(String JDEP) {
        this.JDEP = JDEP;
    }

    public Integer getSTA2() {
        return STA2;
    }

    public void setSTA2(Integer STA2) {
        this.STA2 = STA2;
    }

    public Integer getSTA3() {
        return STA3;
    }

    public void setSTA3(Integer STA3) {
        this.STA3 = STA3;
    }

    public String getYYNO() {
        return YYNO;
    }

    public void setYYNO(String YYNO) {
        this.YYNO = YYNO;
    }

    public String getGROPNAM() {
        return GROPNAM;
    }

    public void setGROPNAM(String GROPNAM) {
        this.GROPNAM = GROPNAM;
    }

    public String getDEPTNAM() {
        return DEPTNAM;
    }

    public void setDEPTNAM(String DEPTNAM) {
        this.DEPTNAM = DEPTNAM;
    }

    public String getCOMPNAM() {
        return COMPNAM;
    }

    public void setCOMPNAM(String COMPNAM) {
        this.COMPNAM = COMPNAM;
    }
}
