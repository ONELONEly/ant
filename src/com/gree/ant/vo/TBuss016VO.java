package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("TBuss016")
@PK({"taid","dsid"})
public class TBuss016VO {

    private String taid;
    private String dsid;

    public TBuss016VO() {
    }

    public TBuss016VO(String taid) {
        this.taid = taid;
    }

    public TBuss016VO(String taid, String dsid) {
        this.taid = taid;
        this.dsid = dsid;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public String getDsid() {
        return dsid;
    }

    public void setDsid(String dsid) {
        this.dsid = dsid;
    }
}
