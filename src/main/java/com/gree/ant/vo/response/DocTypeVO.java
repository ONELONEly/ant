package com.gree.ant.vo.response;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 文档类型业务类
 * @createTime 2019 -04-30 11:03:14
 */
public class DocTypeVO {

    /**
     * @description 文档类型
     * @createTime 2019 -04-30 11:03:14
     * @version 1.0
     */
    private Integer type;
    /**
     * @description 文档名称
     * @createTime 2019 -04-30 11:03:14
     * @version 1.0
     */
    private String name;

    public DocTypeVO() {
    }

    public DocTypeVO(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
