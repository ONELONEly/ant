package com.gree.ant.dao.daoImp.util;

public class Pager {

    private Integer start;
    private Integer end;

    public Pager(Integer pageNumber, Integer pageSize) {
        if(pageNumber <  1){
            pageNumber = 1;
        }
        if(pageSize < 1){
            pageSize = 10;
        }
        this.start = (pageNumber - 1)* pageSize;
        this.end = pageNumber * pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

}
