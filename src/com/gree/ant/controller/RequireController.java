package com.gree.ant.controller;

import com.gree.ant.mo.Tbuss003MO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Tbuss003VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Require controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 需求的控制器.
 * @title RequireController
 * @createTime 2017 :10:30 10:10:41.
 */
@At("/require")
@IocBean
public class RequireController {


    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    /**
     * Index string.
     *
     * @return the string
     * @description 用户需求界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.index")
    public String index(){
        return "success!";
    }

    /**
     * Insert string.
     *
     * @return the string
     * @description 添加用户需求界面
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.insert")
    public String insert(){
        return "JK"+ FileUtil.getRandomName();
    }

    /**
     * Modify map.
     *
     * @param taid the taid
     * @return the map
     * @description 修改用户需求界面
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.modify")
    public Map<String,Object> modify(@Param("taid")String taid){
        HashMap<String,Object> map = new HashMap<>();
        Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(taid);
        map.put("note",FileUtil.convertClob(tbuss003VO.getNote()));
        tbuss003VO.setNote(null);
        map.put("task",tbuss003VO);
        return map;
    }

    /**
     * Manage string.
     *
     * @return the string
     * @description 需求管理界面入口
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("jsp:jsp.require.manage")
    public String manage(){
        return "success!";
    }

    /**
     * Query user require map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @param key        过滤管检测
     * @param session    the session
     * @return 标准的表格请求结果集合
     * @description 获得个人用户的所有需求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryUserRequire(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,@Param("key")String key,HttpSession session){
        SqlExpressionGroup e = null;
        if(key != null){
            e = Cnd.exps("taid","like","%"+key+"%").or("synonam","like","%"+key+"%").or("titl","like","%"+key+"%");
        }
        String usid = StringUtil.getUsid(session);
        Pager pager = new Pager(pageNumber,pageSize);
        Cnd cnd = Cnd.where("puno","=","PU0007").and("usid","=",usid).and(e);
        return TableUtil.makeJson(0,"",tbuss003MO.countByCnd(cnd),tbuss003MO.queryAllByCnd(cnd,pager));
    }

    /**
     * Query all require map.
     *
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @param key        the key
     * @return 标准的表格请求结果集合
     * @description 获得所有用户需求的结果集
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:44.
     */
    @At
    @Ok("json")
    public Map<String,Object> queryAllRequire(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,@Param("key")String key){
        SqlExpressionGroup e = null;
        if(key != null){
            e = Cnd.exps("taid","like","%"+key+"%").or("synonam","like","%"+key+"%").or("titl","like","%"+key+"%");
        }
        Pager pager = new Pager(pageNumber,pageSize);
        Cnd cnd = Cnd.where("puno","=","PU0007").and(e);
        return TableUtil.makeJson(0,"",tbuss003MO.countByCnd(cnd),tbuss003MO.queryAllByCnd(cnd,pager));
    }

    /**
     * Insert require map.
     *
     * @param tbuss003VO 任务实体
     * @param edit       用户需求的内容
     * @param session    the session
     * @return 标准的数据请求结果集合
     * @description 插入单条用户需求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> insertRequire(@Param("..")Tbuss003VO tbuss003VO,@Param("edit")String edit,HttpSession session){
        String msg = "";
        Integer code = 0;
        String usid = StringUtil.getUsid(session);
        String taid = tbuss003VO.getTaid();
        if(StringUtil.checkString(taid) && StringUtil.checkString(edit,tbuss003VO.getTitl(),tbuss003VO.getSyno())){
            tbuss003VO.setNote(FileUtil.formatClobByString(edit));
            tbuss003VO.setUsid(usid);
            tbuss003VO.setStag(0);
            tbuss003VO.setSta1(0);
            tbuss003VO.setCdat(new Date());
            tbuss003VO.setPuno("PU0007");
            tbuss003VO.setFahh(0f);
            tbuss003VO = tbuss003MO.insertByVO(tbuss003VO);
            if(tbuss003VO != null){
                code = 1;
            }
        }else{
            msg = "请确认您已经输入了所有信息！";
        }
        msg = code == 1?"添加成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Delete require map.
     *
     * @param taid  任务ID
     * @param taids 任务ID集合
     * @return 标准的数据请求结果集合
     * @description 删除用户需求.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> deleteRequire(@Param("taid")String taid,@Param("::list")String[] taids){
        String msg = "";
        Integer code = 0;
        if(StringUtil.checkString(taid)){
            code = tbuss003MO.deleteByTaid(taid);
        }else if(taids != null){
            for(String TAID:taids){
                code = tbuss003MO.deleteByTaid(TAID);
            }
        }else{
            msg = "请求参数为空！";
        }
        msg = code == 1?"删除成功":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Edit require map.
     *
     * @param tbuss003VO 任务实体
     * @param edit       用户需求的内容
     * @return 标准的数据请求结果集合
     * @description 修改用户需求
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> editRequire(@Param("..")Tbuss003VO tbuss003VO,@Param("edit")String edit){
        String msg = "";
        Integer code = 0;
        String taid = tbuss003VO.getTaid();
        if(StringUtil.checkString(taid) && StringUtil.checkString(edit,tbuss003VO.getTitl(),tbuss003VO.getSyno())) {
            Tbuss003VO tbuss003VO1 = tbuss003MO.fetchByTaid(taid);
            tbuss003VO1.setNote(FileUtil.formatClobByString(edit));
            tbuss003VO1.setSyno(tbuss003VO.getSyno());
            tbuss003VO1.setTitl(tbuss003VO.getTitl());
            tbuss003VO1.setSta2(tbuss003VO.getSta2());
            tbuss003VO1.setSta3(tbuss003VO.getSta3());
            code = tbuss003MO.updateByVO(tbuss003VO1);
        }else{
            msg = "请确认您已经输入了所有信息！";
        }
        msg = code ==1?"修改成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

    /**
     * Copy task map.
     *
     * @param taids 任务ID集合
     * @return 标准的数据请求结果集合
     * @description 复制用户需求.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 04:10:45.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> copyTask(@Param("::list")String[] taids){
        String msg = "";
        Integer code = 0;
        if(taids!=null){
            for(String taid:taids){
                Tbuss003VO tbuss003VO = tbuss003MO.fetchByTaid(taid);
                tbuss003VO.setTaid("JK"+ FileUtil.getRandomName());
                tbuss003VO.setCdat(new Date());
                tbuss003VO.setFahh(0f);
                tbuss003VO = tbuss003MO.insertByVO(tbuss003VO);
                if(tbuss003VO != null){
                    code = 1;
                }
            }
        }else{
            msg = "请求参数为空！请选中任务！";
        }
        msg = code == 1?"复制成功！":msg;
        return ResultUtil.getResult(code,msg,null);
    }

}
