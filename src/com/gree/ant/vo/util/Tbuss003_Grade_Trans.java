package com.gree.ant.vo.util;

import com.gree.ant.vo.Tbuss003VO;

public class Tbuss003_Grade_Trans {

    private Tbuss003VO tbuss003VO;
    private Integer count;

    public Tbuss003_Grade_Trans() {
    }

    public Tbuss003_Grade_Trans(Tbuss003VO tbuss003VO, Integer count) {
        this.tbuss003VO = tbuss003VO;
        this.count = count;
    }

    public Tbuss003VO getTbuss003VO() {
        return tbuss003VO;
    }

    public void setTbuss003VO(Tbuss003VO tbuss003VO) {
        this.tbuss003VO = tbuss003VO;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
