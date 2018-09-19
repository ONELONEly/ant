package com.gree.ant.mo;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class BussMoFactory {

    @Inject
    private Tbuss001MO tbuss001MO;
    @Inject
    private Tbuss002MO tbuss002MO;
    @Inject
    private Tbuss003MO tbuss003MO;
    @Inject
    private Tbuss004MO tbuss004MO;
    @Inject
    private Tbuss005MO tbuss005MO;
    @Inject
    private Tbuss009MO tbuss009MO;
    @Inject
    private Tbuss010MO tbuss010MO;
    @Inject
    private Tbuss011MO tbuss011MO;
    @Inject
    private Tbuss012MO tbuss012MO;
    @Inject
    private Tbuss013MO tbuss013MO;

    public Tbuss001MO getTbuss001MO() {
        return tbuss001MO;
    }

    public Tbuss002MO getTbuss002MO() {
        return tbuss002MO;
    }

    public Tbuss003MO getTbuss003MO() {
        return tbuss003MO;
    }

    public Tbuss004MO getTbuss004MO() {
        return tbuss004MO;
    }

    public Tbuss005MO getTbuss005MO() {
        return tbuss005MO;
    }

    public Tbuss009MO getTbuss009MO() {
        return tbuss009MO;
    }

    public Tbuss010MO getTbuss010MO() {
        return tbuss010MO;
    }

    public Tbuss011MO getTbuss011MO() {
        return tbuss011MO;
    }

    public Tbuss012MO getTbuss012MO() {
        return tbuss012MO;
    }

    public Tbuss013MO getTbuss013MO() {
        return tbuss013MO;
    }
}
