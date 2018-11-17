package com.gree.ant.processor;

import com.gree.ant.exception.KellyException;
import com.gree.ant.util.StringUtil;
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
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

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
        MVCResultVO resultVO;
        Throwable error = ac.getError();
        PrintWriter writer = response.getWriter();
        String method = request.getMethod();
        String errorString = StringUtil.getStackTraceText(error);
        logger.info(method);
        if("GET".equals(method)) {
            response.setContentType("text/html;charset=UTF-8");
            resultVO = getResultVO(error);
            writer.write("错误码为 : " + resultVO.getCode() + "<br/>错误信息 : " + resultVO.getMsg());
        }else{
            response.setContentType("application/json;charset=UTF-8");
            resultVO = getResultVO(error);
            writer.append(Json.toJson(resultVO));
        }
    }

    private MVCResultVO getResultVO(Throwable error){
        MVCResultVO resultVO;
        KellyException kelly;
        if(error instanceof KellyException){
            kelly = (KellyException)error;
            resultVO = new MVCResultVO(kelly.getCode(),kelly.getMessage());
        }else{
            String msg = error.getMessage();
            if (StringUtil.checkString(error.getMessage())) {
                msg = "服务器错误("+error.toString()+")";
            }
            resultVO = new MVCResultVO(-1,msg);
        }
        return resultVO;
    }
}
