package com.gree.ant.processor;

import com.gree.ant.exception.KellyException;
import com.gree.ant.vo.util.MVCResultVO;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.impl.processor.ViewProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@IocBean(singleton = false)
public class KellyFailProcessor extends ViewProcessor {

    private Logger logger = LoggerFactory.getLogger(KellyFailProcessor.class);

    @Override
    public void init(NutConfig config, ActionInfo ai) throws Throwable {
        view = evalView(config, ai, ai.getFailView());
    }

    @Override
    public void process(ActionContext ac) throws Throwable {
        HttpServletResponse response = ac.getResponse();
        HttpServletRequest request = ac.getRequest();
        KellyException kelly;
        MVCResultVO resultVO;
        Throwable error = ac.getError();
        PrintWriter writer = response.getWriter();
        String method = request.getMethod();
        logger.info(method);
        if("GET".equals(method)) {
            response.setContentType("text/html;charset=UTF-8");
            if (error instanceof KellyException) {
                kelly = (KellyException) error;
                resultVO = new MVCResultVO(kelly.getCode(),kelly.getMessage());
            }else{
                resultVO = new MVCResultVO(-1,error.getMessage());
            }
            writer.write("错误码为 : " + resultVO.getCode() + "<br/>错误信息 : " + resultVO.getMsg());
        }else{
            response.setContentType("application/json;charset=UTF-8");
            if(error instanceof KellyException){
                kelly = (KellyException)error;
                resultVO = new MVCResultVO(kelly.getCode(),kelly.getMessage());
            }else{
                resultVO = new MVCResultVO(-1,error.getMessage());
            }
            writer.append(Json.toJson(resultVO));
        }
    }
}
