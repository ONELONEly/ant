package com.gree.ant.processor;

import com.gree.ant.exception.KellyException;
import com.gree.ant.util.HttpRequest;
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
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        error.printStackTrace();
        if("GET".equals(method)) {
            response.setContentType("text/html;charset=UTF-8");
            resultVO = getResultVO(error);
            writer.write("错误码为 : (" + resultVO.getCode() + ")<br/>错误信息 : " + resultVO.getMsg());
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
            String msg = HttpRequest.getThrowableInformation(error);
            if (StringUtil.checkString(msg)) {
                String regEx = "Caused by:(.*)";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(msg);
                boolean rs = matcher.find();
                msg = rs ? "服务器错误("+matcher.group(1)+")" : "服务器错误";
            }
            resultVO = new MVCResultVO(-1,msg);
        }
        return resultVO;
    }
}
