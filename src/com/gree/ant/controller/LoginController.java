package com.gree.ant.controller;
import com.gree.ant.mo.Cbase000MO;
import com.gree.ant.util.LDAPLogin;
import com.gree.ant.util.ResultUtil;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Login controller.
 *
 * @author Created by ${user}
 * @version V1.0
 * @description 登陆的Controller
 * @title LoginController
 * @createTime 2017 :09:06 06:09:31.
 */
@IocBean
@Filters
public class LoginController {

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    /**
     * Index map.
     *
     * @return the map
     * @description 登录界面的入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:30.
     */
    @At("/login")
    @Ok("jsp:jsp.login")
    public String index(){
        return "success!";
    }

    @At("/")
    @Ok("jsp:jsp.portal")
    public Map<String,Object> portal(){
        return ResultUtil.getResult(1,"",null);
    }

    /**
     * Login out string.
     *
     * @param request the request
     * @return the string
     * @description 登出
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:12 03:09:53.
     */
    @At
    @Ok(">>:/")
    public String loginOut(HttpServletRequest request){
        request.getSession().setAttribute("usid",null);
        return "success!";
    }

    /**
     * Login check string.
     *
     * @param usid   用户编号
     * @param pawd   用户密码
     * @param session the session
     * @return the string
     * @description 校验用户的登陆信息，成功重定向至主页，否则页面刷新
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:47.
     */
    @POST
    @At("/login")
    @Ok("re:>>:/index")
    public String loginCheck(@Param("usid") String usid, @Param("pawd")String pawd, HttpSession session){
        if(usid != null && pawd !=null) {
            if (cbase000MO.loginCheck(usid, pawd)) {
                session.setAttribute("count",0);
                session.setAttribute("usid", usid);
                return null;
            } else if (LDAPLogin.authenticate(usid, pawd) != null) {
                session.setAttribute("usid", usid);
                session.setAttribute("count",0);
                return null;
            }
        }
        return ">>:/login";
    }
}
