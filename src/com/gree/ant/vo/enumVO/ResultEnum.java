package com.gree.ant.vo.enumVO;

public enum ResultEnum {
    PRINCIPAL_NULL(999,"PrincipalCollection method argument cannot be null"),
    CONNECT_REFUSE(998,"connection failed: socket,host=localhost,port=8100,tcpNoDelay=1: java.net.ConnectException: Connection refused: connect"),
    RUNTIME_ERROR(500,"运行时错误")
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
