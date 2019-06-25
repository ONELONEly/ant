package com.gree.ant.controller;


import com.gree.ant.mo.BussMoFactory;
import com.gree.ant.mo.Cbase000MO;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.response.FahhVO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * The type Sum controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 统计控制器
 * @title SumController
 * @createTime 2017 :10:26 11:10:37.
 */
@At("/sum")
@IocBean
public class SumController {

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    @Inject
    private BussMoFactory bussMoFactory;

    /**
     * Task search string.
     *
     * @return the string
     * @description 统计查询报表入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 11:10:32.
     */
    @At
    @Ok("jsp:jsp.sum.taskSearch")
    public String taskSearch(){
        return "success";
    }

    /**
     * Score rank string.
     *
     * @return the string
     * @description 评分排名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 11:10:37.
     */
    @At
    @Ok("jsp:jsp.sum.scoreRank")
    public Integer scoreRank(){
        return 0;
    }

    @At
    @Ok("jsp:jsp.sum.fahhSearch")
    public Integer fahhSearch(){
        return 0;
    }

    @At
    @Ok("jsp:jsp.sum.gradeOkrSearch")
    public String gradeOkrSearch(){
        return "handsome";
    }

    @At
    @Ok("json")
    public Map<String,Object> queryAllProjectFahh(@Param("date")String date,@Param("acco")String acco){
        List<FahhVO> resultData = bussMoFactory.getTbuss003MO().queryAllProjectFahh(date,acco);
        return TableUtil.makeJson(0,"",resultData.size(),resultData);
    }

    @At
    @Ok("json")
    public Map<String,Object> queryAllNotProjectFahh(@Param("date")String date,@Param("acco")String acco){
        List<FahhVO> resultData = bussMoFactory.getTbuss003MO().queryAllNotProjectFahh(date,acco);
        return TableUtil.makeJson(0,"",resultData.size(),resultData);
    }

}
