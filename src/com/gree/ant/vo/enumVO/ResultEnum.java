package com.gree.ant.vo.enumVO;

public enum ResultEnum {
    PRINCIPAL_NULL(999,"PrincipalCollection method argument cannot be null"),
    CONNECT_REFUSE(998,"connection failed: socket,host=localhost,port=8100,tcpNoDelay=1: java.net.ConnectException: Connection refused: connect"),
    RUNTIME_ERROR(500,"运行时错误"),
    EXCEL_ERROR(501,"打印Excel错误"),
    CONVERT_ERROR(502,"转换异常错误"),
    DS_ERROR(503,"同步DS异常,请再努力尝试一次好么，(*╹▽╹*)！"),
    DS_NONE_ID(504,"DS没有该用户的账号信息，请联系相关人员添加账号，(*╹▽╹*)！"),
    POST_DOUBLE(505,"请勿重复提交"),
    POST_delay(506,"请等待上次请求完成")
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
