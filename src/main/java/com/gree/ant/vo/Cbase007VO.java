package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * The type Cbase 007 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 角色表实体
 * @title Cbase007VO
 * @createTime 2017 :09:01 10:09:38.
 */
@Table("CBASE007")
public class Cbase007VO extends ValueObject{

    /**
     * The Roid.
     *
     * @description 角色编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:38.
     */
    @Name
    private String roid;
    /**
     * The Dsca.
     *
     * @description 角色描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:38.
     */
    private String dsca;
    /**
     * The Comp.
     *
     * @description 公司编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 10:09:38.
     */
    private String comp;

    @ManyMany(target = Cbase002VO.class,relation = "CBASE004",from="roid",to="pono")
    private List<Cbase002VO> cbase002VOS;

    @ManyMany(target = Cbase000VO.class,relation = "CBASE005",from="roid",to="usid")
    private List<Cbase000VO> cbase000VOS;

    public Cbase007VO() {

    }

    public Cbase007VO(String roid) {
        this.roid = roid;
    }

    public Cbase007VO(String roid, String dsca, String comp) {
        this.roid = roid;
        this.dsca = dsca;
        this.comp = comp;
    }


    public List<Cbase000VO> getCbase000VOS() {
        return cbase000VOS;
    }

    public void setCbase000VOS(List<Cbase000VO> cbase000VOS) {
        this.cbase000VOS = cbase000VOS;
    }

    public List<Cbase002VO> getCbase002VOS() {
        return cbase002VOS;
    }

    public void setCbase002VOS(List<Cbase002VO> cbase002VOS) {
        this.cbase002VOS = cbase002VOS;
    }

    public String getRoid() {
        return roid;
    }

    public void setRoid(String roid) {
        this.roid = roid;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }
}
