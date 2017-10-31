package com.gree.ant.controller;

import com.gree.ant.mo.Cbase000MO;
import com.gree.ant.mo.Cbase002MO;
import com.gree.ant.mo.Cbase003MO;
import com.gree.ant.vo.Cbase002VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Main controller.
 *
 * @author Created by ${user}
 * @version V1.0
 * @description 主界面的Controller
 * @title MainController
 * @createTime 2017 :09:06 06:09:06.
 */
@IocBean
public class MainController {

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    @Inject("refer:cbase002MO")
    private Cbase002MO cbase002MO;

    @Inject("refer:cbase003MO")
    private Cbase003MO cbase003MO;

    /**
     * Index string.
     *
     * @param session the session
     * @return the string
     * @description 主页界面的入口.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:02.
     */
    @At("/index")
    @Ok("jsp:jsp.main")
    public Map<String,Object> index(HttpSession session){
        Map<String,Object> resultMap = new HashMap<>();
        String usid = session.getAttribute("usid").toString();
        Integer count = Integer.parseInt(session.getAttribute("count").toString());
        session.setAttribute("count",count+1);
        List<Cbase002VO> oneLevel = new ArrayList<>();
        List<Cbase002VO> secondLevel = new ArrayList<>();
        List<Cbase002VO> thirdLevel = new ArrayList<>();
        List<Cbase002VO> cbase002VOS = cbase002MO.queryAllMenuByUSID(usid);
        for (Cbase002VO cbase002VO:cbase002VOS){
            if(cbase002VO.getStyp() == 0){
                oneLevel.add(cbase002VO);
            }
            if(cbase002VO.getStyp() == 1){
                secondLevel.add(cbase002VO);
            }
            if(cbase002VO.getStyp() == 2){
                cbase002VO.setCbase003VO(cbase003MO.fetchByFLNO(cbase002VO.getPono()));
                thirdLevel.add(cbase002VO);
            }
        }
        resultMap.put("user",cbase000MO.fetchByUsid(usid));
        resultMap.put("oneLevel",oneLevel);
        resultMap.put("secondLevel",secondLevel);
        resultMap.put("thirdLevel",thirdLevel);
        resultMap.put("count",count);
        return resultMap;
    }

    @At("/main")
    @Ok("jsp:jsp.index")
    public String main(){
        return "success";
    }
}
