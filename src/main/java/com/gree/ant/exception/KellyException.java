package com.gree.ant.exception;


import com.gree.ant.vo.enumVO.ResultEnum;

public class KellyException extends RuntimeException{

    private Integer code;

    public KellyException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public KellyException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
