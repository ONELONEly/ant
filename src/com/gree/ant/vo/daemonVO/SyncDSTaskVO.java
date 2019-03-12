package com.gree.ant.vo.daemonVO;

import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Tbuss003VO;

public class SyncDSTaskVO {
    private Tbuss003VO tbuss003VO;
    private Cbase000VO cbase000VO;

    public SyncDSTaskVO(Tbuss003VO tbuss003VO, Cbase000VO cbase000VO) {
        this.tbuss003VO = tbuss003VO;
        this.cbase000VO = cbase000VO;
    }

    public Tbuss003VO getTbuss003VO() {
        return tbuss003VO;
    }

    public void setTbuss003VO(Tbuss003VO tbuss003VO) {
        this.tbuss003VO = tbuss003VO;
    }

    public Cbase000VO getCbase000VO() {
        return cbase000VO;
    }

    public void setCbase000VO(Cbase000VO cbase000VO) {
        this.cbase000VO = cbase000VO;
    }
}
