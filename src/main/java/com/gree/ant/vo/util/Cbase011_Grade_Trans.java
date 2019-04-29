package com.gree.ant.vo.util;

import com.gree.ant.vo.Cbase011VO;

public class Cbase011_Grade_Trans {

    private Cbase011VO cbase011VO;
    private Double cons;

    public Cbase011_Grade_Trans() {
    }

    public Cbase011_Grade_Trans(Cbase011VO cbase011VO, Double cons) {
        this.cbase011VO = cbase011VO;
        this.cons = cons;
    }

    public Cbase011VO getCbase011VO() {
        return cbase011VO;
    }

    public void setCbase011VO(Cbase011VO cbase011VO) {
        this.cbase011VO = cbase011VO;
    }

    public Double getCons() {
        return cons;
    }

    public void setCons(Double cons) {
        this.cons = cons;
    }
}
