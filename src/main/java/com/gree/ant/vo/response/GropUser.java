package com.gree.ant.vo.response;

public class GropUser {

    private String usid;
    private String dsca;
    private String boss;

    public GropUser(String usid, String dsca, String boss) {
        this.usid = usid;
        this.dsca = dsca;
        this.boss = boss;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }
}
