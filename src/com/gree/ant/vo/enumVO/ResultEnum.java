package com.gree.ant.vo.enumVO;

public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(1,"成功"),
    NOTPERMISSION(-1,"对不起你没有权限"),
    FILEUPLOAD_ERROR(110,"上传图片失败"),
    DONTLOGIN(111,"对不起，清先登录"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
