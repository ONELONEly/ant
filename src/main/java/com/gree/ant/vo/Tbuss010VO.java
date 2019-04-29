package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("TBUSS010")
@PK({"taid","usid"})
public class Tbuss010VO extends ValueObject{

    private String usid;
    private String taid;

    public Tbuss010VO() {
    }

    public Tbuss010VO(String usid, String taid) {
        this.usid = usid;
        this.taid = taid;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }
}
