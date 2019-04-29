package com.gree.ant.vo;

import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Tbuss 002 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 关联表,绩效评分表
 * @title Tbuss002VO
 * @createTime 2017 :09:06 10:09:50.
 */
@Table("TBUSS002")
@PK({"pjno","ptno"})
public class Tbuss002VO extends ValueObject{

    /**
     * The Pjno.
     *
     * @description 评分表字段
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:50.
     */
    private String pjno;
    /**
     * The Ptno.
     *
     * @description 绩效表字段
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 10:09:50.
     */
    private String ptno;

    public Tbuss002VO() {
    }

    public Tbuss002VO(String pjno, String ptno) {
        this.pjno = pjno;
        this.ptno = ptno;
    }

    public String getPjno() {
        return pjno;
    }

    public void setPjno(String pjno) {
        this.pjno = pjno;
    }

    public String getPtno() {
        return ptno;
    }

    public void setPtno(String ptno) {
        this.ptno = ptno;
    }
}
