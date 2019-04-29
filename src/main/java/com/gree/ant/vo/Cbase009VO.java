package com.gree.ant.vo;


import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * The type Cbase 009 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 团队的实体.
 * @title Cbase009VO
 * @createTime 2017 :08:30 10:08:33.
 */
@Table("CBASE009")
public class Cbase009VO extends ValueObject{

    /**
     * The Grop.
     *
     * @description 组ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 10:08:33.
     */
    @Name
    private String grop;

    /**
     * The Dsca.
     *
     * @description 描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 10:08:33.
     */
    private String dsca;

    /**
     * The Cbase 009 vos.
     *
     * @description 组员的集合
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 10:08:33.
     */
    @ManyMany(target = Cbase000VO.class,relation = "CBASE010",from="grop",to="usid")
    private List<Cbase000VO> cbase000VOS;

    public Cbase009VO() {
    }

    public Cbase009VO(String grop) {
        this.grop = grop;
    }

    public Cbase009VO(String grop, String dsca) {
        this.grop = grop;
        this.dsca = dsca;
    }

    public List<Cbase000VO> getCbase000VOS() {
        return cbase000VOS;
    }

    public void setCbase000VOS(List<Cbase000VO> cbase000VOS) {
        this.cbase000VOS = cbase000VOS;
    }

    public String getGrop() {
        return grop;
    }

    public void setGrop(String grop) {
        this.grop = grop;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }
}
