package com.gree.ant.vo.util;

import org.nutz.dao.entity.annotation.*;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 金蝶岗位表
 * @createTime 2019 -02-26 10:46:44
 */
@Comment("金蝶岗位表")
@Table("ButterFlyStaff")
public class ButterFlyStaff {

    /**
     * @description 岗位表ID
     * @createTime 2019 -02-26 10:46:44
     * @version 1.0
     */
    @Id
    @Comment("岗位ID")
    @ColDefine(type = ColType.INT,notNull = true)
    @Prev(@SQL("select nvl(max(staffId)+1,1) from ButterFlyStaff"))
    private Integer staffId;
    /**
     * @description 岗位名称
     * @createTime 2019 -02-26 10:46:44
     * @version 1.0
     */
    @Comment("岗位名称")
    @ColDefine(type = ColType.VARCHAR,notNull = true,width = 40)
    private String staffName;
    /**
     * @description 岗位编码
     * @createTime 2019 -02-26 10:46:44
     * @version 1.0
     */
    @Comment("岗位编码")
    @ColDefine(type = ColType.VARCHAR,notNull = true,width = 40)
    private String staffNumber;
    /**
     * @description 岗位定义名称
     * @createTime 2019 -02-26 10:46:44
     * @version 1.0
     */
    @Comment("岗位定义名称")
    @ColDefine(type = ColType.VARCHAR,notNull = true,width = 40)
    private String staff;

    public ButterFlyStaff() {
    }

    public ButterFlyStaff(String staffName, String staffNumber, String staff) {
        this.staffName = staffName;
        this.staffNumber = staffNumber;
        this.staff = staff;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}
