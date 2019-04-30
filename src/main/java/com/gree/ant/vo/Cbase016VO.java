package com.gree.ant.vo;

import com.gree.ant.vo.response.DocTypeVO;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Cbase 016 vo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 文档类型表
 * @title Cbase016VO
 * @createTime 2017 :09:20 05:09:13.
 */
@Table("CBASE016")
public class Cbase016VO extends ValueObject{

    /**
     * The Ctyp.
     *
     * @description 文档类型编号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:13.
     */
    @Name
    private String ctyp;
    /**
     * The Dsca.
     *
     * @description 文档类型描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:13.
     */
    private String dsca;

    public Cbase016VO() {
    }

    public Cbase016VO(String ctyp, String dsca) {
        this.ctyp = ctyp;
        this.dsca = dsca;
    }

    public String getCtyp() {
        return ctyp;
    }

    public void setCtyp(String ctyp) {
        this.ctyp = ctyp;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public DocTypeVO builderBussiness(){
        return new DocTypeVO(Integer.parseInt(ctyp),dsca);
    }
}
