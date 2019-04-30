package com.gree.ant.vo.response;

import java.util.Date;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 文档展示类
 * @createTime 2019 -04-30 11:31:16
 */
public class DocVO {

    /**
     * @description 文档编号
     * @createTime 2019 -04-30 11:31:16
     * @version 1.0
     */
    private Long doid;
    /**
     * @description 文档标题
     * @createTime 2019 -04-30 11:31:16
     * @version 1.0
     */
    private String title;
    /**
     * @description 创建时间
     * @createTime 2019 -04-30 11:31:16
     * @version 1.0
     */
    private Date time;
    /**
     * @description 作者
     * @createTime 2019 -04-30 11:31:16
     * @version 1.0
     */
    private String author;

    public DocVO() {
    }

    public DocVO(Long doid, String title, Date time, String author) {
        this.doid = doid;
        this.title = title;
        this.time = time;
        this.author = author;
    }

    public Long getDoid() {
        return doid;
    }

    public void setDoid(Long doid) {
        this.doid = doid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
