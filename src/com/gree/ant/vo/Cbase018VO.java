package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * The type Cbase 018 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 邮箱组的实体
 * @title Cbase018VO
 * @createTime 2017 :10:21 06:10:48.
 */
@Table("CBASE018")
public class Cbase018VO extends ValueObject{

    /**
     * The Emid.
     *
     * @description 邮箱组的ID
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 06:10:06.
     */
    @Id
    private Integer emid;
    /**
     * The Dsca.
     *
     * @description 邮箱组的描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:21 06:10:09.
     */
    private String dsca;

    @ManyMany(target = Cbase000VO.class,relation = "CBASE019",from = "emid",to = "usid")
    private List<Cbase000VO> cbase000VOS;

    public Cbase018VO() {
    }

    public Integer getEmid() {
        return emid;
    }

    public void setEmid(Integer emid) {
        this.emid = emid;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public List<Cbase000VO> getCbase000VOS() {
        return cbase000VOS;
    }

    public void setCbase000VOS(List<Cbase000VO> cbase000VOS) {
        this.cbase000VOS = cbase000VOS;
    }
}
