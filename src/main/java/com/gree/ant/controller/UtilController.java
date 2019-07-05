package com.gree.ant.controller;

import com.gree.ant.dao.daoImp.*;
import com.gree.ant.mo.*;
import com.gree.ant.util.*;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase010VO;
import com.gree.ant.vo.Cbase011VO;
import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.response.GropUser;
import com.gree.ant.vo.util.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.impl.AdaptorErrorContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

/**
 * The type Util controller.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 处理界面select的选择请求
 * @title UtilController
 * @createTime 2017 :09:05 03:09:13.
 */
@At("/util")
@IocBean
public class UtilController {
    @Inject("refer:cbase001MO")
    private Cbase001MO cbase001MO;

    @Inject("refer:cbase002MO")
    private Cbase002MO cbase002MO;

    @Inject("refer:cbase006MO")
    private Cbase006MO cbase006MO;

    @Inject("refer:cbase007MO")
    private Cbase007MO cbase007MO;

    @Inject("refer:cbase009MO")
    private Cbase009MO cbase009MO;

    @Inject("refer:cbase000MO")
    private Cbase000MO cbase000MO;

    @Inject("refer:cbase011MO")
    private Cbase011MO cbase011MO;

    @Inject("refer:cbase013MO")
    private Cbase013MO cbase013MO;

    @Inject("refer:cbase014MO")
    private Cbase014MO cbase014MO;

    @Inject("refer:cbase016MO")
    private Cbase016MO cbase016MO;

    @Inject("refer:cbase017MO")
    private Cbase017MO cbase017MO;

    @Inject("refer:tbuss001MO")
    private Tbuss001MO tbuss001MO;

    @Inject("refer:tbuss003MO")
    private Tbuss003MO tbuss003MO;

    @Inject
    private BaseMoFactory baseMoFactory;

    @Inject
    private BussMoFactory bussMoFactory;

    @Inject
    private ConferenceProjectMO projectMO;

    @Inject("refer:tbuss003MO_Ds")
    private Tbuss003MO_Ds tbuss003MO_Ds;

    @Inject
    private ContractDAOImp contractDAOImp;

    @Inject
    private ContractPayPlanDAOImp contractPayPlanDAOImp;

    @Inject
    private BaanTfgld010400DAOImp baanTfgld010400DAOImp;

    @Inject
    private BaanTfgld008400DAOImp baanTfgld008400DAOImp;

    @Inject
    private BaanTcmcs008400DAOImp baanTcmcs008400DAOImp;

    @At
    @Ok("json")
    public Map<String,Object> findT3DS_jied(String syno){
        Map<String,Object> map = new HashMap<>();
       map.put("jieds",tbuss003MO_Ds.findT3DS_jied(syno));
        return map;
    }

    @At
    @POST
    @Ok("json")
    @AdaptBy(type = JsonAdaptor.class)
    @Filters
    public Map<String, Object> getBaan197Infor() {
        int code = 200;
        int count = 1;
        String msg = "";
        List<BaanTcmcs008400VO> baanTcmcs008400VOList = baanTcmcs008400DAOImp.queryBaanTcmcs008400Infor();
        /*List<BaanTfgld008400VO> baanTfgld008400VOList = baanTfgld008400DAOImp.queryBaanTfgld008400Infor();
        List<BaanTfgld010400VO> baanTfgld010400VOList = baanTfgld010400DAOImp.queryBaanTfgld010400Infor();*/
        Baan197InforVO baan197InforVO = new Baan197InforVO();
        baan197InforVO.setRate(baanTcmcs008400VOList);
        /*baan197InforVO.setSubjectTable(baanTfgld008400VOList);
        baan197InforVO.setSubAccount(baanTfgld010400VOList);*/
        msg = code == 200 ? "查询成功" : msg;
        return TableUtil.makeJson(code, msg, count, baan197InforVO);
    }

    /**
     * 飞云调用合同系统接口
     * @param htCoid 合同编号
     * @param errorContext
     * @return 返回合同编号以及付款信息
     */
    @At
    @POST
    @Ok("json")
    @AdaptBy(type = JsonAdaptor.class)
    @Filters
        public Map<String, Object> getContractInfor(@Param("htCoid") String htCoid, AdaptorErrorContext errorContext){
        QueryResult queryResult = null;
        int code = 200;
        int count = 1;
        String comp = "400";
        List<ContractVO> contractVOList = new ArrayList<>();
        List<ContractPayPlanVO> contractPayPlanVOList = new ArrayList<>();
        ContractInforVO contractInforVO = new ContractInforVO();
        String msg = "请求合同错误，请检查合同编号!";
        htCoid = "WW-19032";
        if(null == errorContext){
            if(null != htCoid){
                contractVOList = contractDAOImp.queryContractInfor(htCoid, comp);
                contractPayPlanVOList = contractPayPlanDAOImp.queryContractPayPlanInfor(htCoid);
                contractInforVO.setContractVOList(contractVOList);
                contractInforVO.setContractPayPlanVOList(contractPayPlanVOList);
            }else {
                code = 101;
                msg = "合同编号不能为空，请检查合同编号再请求！";
            }
        }else{
            code = 100;
        }
        msg = code == 200 ? "查询成功" : msg;
        return TableUtil.makeJson(code, msg, count, contractInforVO);
    }

    @Inject
    private ButterFlyDAOImp butterFlyDAOImp;

    @At
    @POST
    @Ok("json")
    @AdaptBy(type = JsonAdaptor.class)
    @Filters
    public Map<String,Object> getUserInfo(@Param("pageNumber")int pageNumber, @Param("pageSize")int pageSize, @Param("order")String order,AdaptorErrorContext error){
        QueryResult queryResult = null;
        int code = 200;
        int count = 0;
        List<ButterFlyFeiyun> flyFeiyuns = new ArrayList<>();
        String msg = "请求参数格式错误,请检查请求参数后请求！";
        if(error == null) {
            if (pageNumber > 0 && pageSize >= 0) {
//                Condition cnd = Cnd.where("department", "like", "%空调四分厂%").or("department", "like", "%工会%").
//                        or("department", "like", "%商用空调经营部%").or("posts", "like", "%公司领导%");
                Condition cnd = Cnd.where("companyNumber","=",100);
                if (order != null && (order.equals("company") || order.equals("department") || order.equals("posts") || order.equals("name") || order.equals("sex") || order.equals("isInStaff"))) {
                    cnd = ((Cnd) cnd).desc(order);
                }
                count = butterFlyDAOImp.countByCnd(cnd);
                Pager pager = TableUtil.formatPager(pageSize, pageNumber, count);
                queryResult = butterFlyDAOImp.feiyunQueryAllUser(cnd, pager);
            } else {
                code = 101;
                msg = "请求页码和页数不规范,请检查请求参数后请求！";
            }
        }else{
            code = 100;
        }
        msg = code == 200 ? "查询成功" : msg;
        if(queryResult != null){
            count = queryResult.getPager().getRecordCount();
            flyFeiyuns = queryResult.getList(ButterFlyFeiyun.class);
        }
        return TableUtil.makeJson(code,msg,count,flyFeiyuns);
    }

    /**
     * Find c 6 c 9 map.
     *
     * @return the map
     * @description C6-部门表,C9-团队表的数据请求【,user.insert user.manageModify】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:41.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC1C6C9C17(){

        Map<String,Object> map = new HashMap<>();
        map.put("dept",cbase006MO.queryAllDD());
        map.put("grop",cbase009MO.queryAllGD());
        map.put("acco",cbase017MO.queryAllAD());
        map.put("comp",cbase001MO.queryAllCD());
        return map;
    }

    /**
     * @return
     * @description C17-科室表的数据请求【okr.manager】
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At
    @Ok("json")
    public Map<String,Object> findC17(){
        Map<String,Object> map = new HashMap<>();
        map.put("acco",cbase017MO.queryAllAD());
        return map;
    }

    /**
     * @return
     * @description ConferenceProject-会议项目表的数据请求【okr.manager】
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @At
    @Ok("json")
    public Map<String,Object> findConferenceProject(){
        Map<String,Object> map = new HashMap<>();
        map.put("project",projectMO.queryAllAD());
        return map;
    }

    /**
     * Find c 0 c 13 map.
     *
     * @return the map
     * @description C0 -用户表,C13-系统表,C11-绩效评分项表的查询【task.insert;task.edit】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 11:09:19.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC0C13C14(@Attr("usid")String usid){
        Condition c=Cnd.where("puno","!=","PU0007").and("puno","!=","PU0006");
        Map<String,Object> map = new HashMap<>();
        List<Cbase010VO> cbase010VOS = baseMoFactory.getCbase010MO().queryByCnd(Cnd.where("usid","=",usid));
        String[] grops = new String[cbase010VOS.size()];
        int i = 0;
        for(Cbase010VO cbase010VO:cbase010VOS){
            grops[i] = cbase010VO.getGrop();
            i++;
        }
        map.put("user",cbase000MO.queryAllUD());
        map.put("sys",cbase013MO.queryAllByCnd(null,null));
        map.put("stage",cbase014MO.queryAllByCnd(c,null));
        map.put("project",tbuss001MO.queryAllPD(Cnd.where("grop","in",grops)));
        return map;
    }

    /**
     * Find c 14 map.
     *
     * @return the map
     * @description c13-系统表测查询 [require.insert  require.modify]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:30 02:10:26.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC13(){
        Map<String,Object> map = new HashMap<>();
        map.put("sys",cbase013MO.queryAllByCnd(null,null));
        map.put("user",cbase000MO.queryAllUD());
        return map;
    }

    /**
     * Find c 7 map.
     *
     * @return the map
     * @description C7-角色表的查询 [user.userRole]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 10:10:59.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC7(){
        Map<String,Object> map = new HashMap<>();
        map.put("c7",cbase007MO.queryAllByPagerCnd(null,null));
        return map;
    }

    /**
     * Find c 9 map.
     *
     * @return the map
     * @description C9-团队表的数据请求【grade.edit,grade.grade，user.modify】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:35.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC9(){
        Map<String,Object> map = new HashMap<>();
        map.put("c9",cbase009MO.queryAllGD());
        return map;
    }

    /**
     * Find c 0 map.
     *
     * @return the map
     * @description C0-用户表的数据请求 [jsp.user.weekNews   jsp.role.roleUser  jsp.group.gropUser]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 05:09:29.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC0(){
        Map<String,Object> map = new HashMap<>();
        map.put("c0",cbase000MO.queryAllUD());
        return map;
    }

    @At
    @Ok("json")
    public Map<String,Object> findGropUser(@Param("grop")String grop){
        Map<String,Object> map = new HashMap<>();
        map.put("user",cbase009MO.fetchC9Tran(grop,null));
        return map;
    }

    /**
     * Find c 1 map.
     *
     * @return the map
     * @description C1-公司表的数据请求 [jsp.department.manage  role.manage]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 10:10:29.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC1(){
        Map<String,Object> map = new HashMap<>();
        map.put("c1",cbase001MO.queryAllCD());
        return map;
    }

    /**
     * Find c 2 map.
     *
     * @return the map
     * @description C2-菜单表的数据请求 [jsp.menu.manage]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:11 04:10:10.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC2(){
        Map<String,Object> map = new HashMap<>();
        map.put("c2",cbase002MO.queryAllByCndPager(Cnd.where("styp","=","1"),null));
        return map;
    }

    /**
     * Find c 02 map.
     *
     * @return the map
     * @description C2-菜单表的数据请求 [jsp.role.rolePermission]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:12 10:10:41.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC02(@Param("roid")String roid){
        Map<String,Object> map = new HashMap<>();
        map.put("c2",cbase002MO.queryAllMenuByROID(roid));
        return map;
    }

    /**
     * Find t 1 map.
     *
     * @return the map
     * @description 查询Tbuss001-绩效表.['user.board','task.manage', board.user , board.manage , user.task]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:30 09:09:21.
     */
    @At
    @Ok("json")
    public Map<String,Object> findT1(@Attr("usid")String usid){
        Map<String,Object> map = new HashMap<>();
        List<Cbase010VO> cbase010VOS = baseMoFactory.getCbase010MO().queryByCnd(Cnd.where("usid","=",usid));
        String[] grops = new String[cbase010VOS.size()];
        int i = 0;
        for(Cbase010VO cbase010VO:cbase010VOS){
            grops[i] = cbase010VO.getGrop();
            i++;
        }
        map.put("t1",tbuss001MO.queryAllByCnd(Cnd.where("grop","in",grops).desc("pdat"),null));
        return map;
    }

    /**
     * Find c 11 map.
     *
     * @return the map
     * @description 查询c11-规则表的全部内容 【grade.projectRule】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:18.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC11(){
        Map<String,Object> map = new HashMap<>();
        List<Cbase011VO> cbase011VOS = cbase011MO.queryAllByCnd(null,null);
        List<Cbase011VO> cbase011VOList = new ArrayList<>();
        for(Cbase011VO cbase011VO:cbase011VOS){
            Cbase000VO cbase000VO = cbase000MO.fetchUser(cbase011VO.getUsid());
            cbase011VO.setCbase000VO(cbase000VO);
            cbase011VOList.add(cbase011VO);
        }
        map.put("c11",cbase011VOList);
        return map;
    }

    /**
     * Find c 0 c 16 map.
     *
     * @return the map
     * @description 查询C0-用户表,C16-文档类型表【doc.insert,doc.edit】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 10:09:45.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC0C16(){
        Map<String,Object> map = new HashMap<>();
        map.put("c0", cbase000MO.queryAllUD());
        map.put("c16",cbase016MO.queryAllByCndPager(null,null));
        return map;
    }

    /**
     * Find c 0 c 9 t 1 map.
     *
     * @return the map
     * @description 用户表，团队表，可选日期的查询【sum.taskSearch】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:20.
     */
    @At
    @Ok("json")
    public Map<String,Object> findC0C9Pdat(){
        Map<String,Object> map = new HashMap<>();
        map.put("c0",cbase000MO.queryAllUD());
        map.put("c9",cbase009MO.queryAllGD());
        map.put("pdat",tbuss001MO.queryAllPdat());
        return map;
    }

    /**
     * Find pdat map.
     *
     * @return the map
     * @description 获得可选的日期【sum.scoreRank】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 05:10:46.
     */
    @At
    @Ok("json")
    public Map<String,Object> findPdat(){
        Map<String,Object> map = new HashMap<>();
        map.put("pdat",tbuss001MO.queryAllPdat());
        map.put("grop",cbase009MO.queryAllGD());
        map.put("acco",cbase017MO.queryAllAD());
        return map;
    }

    /**
     * Findtask insert c 11 map.
     *
     * @param ptno 绩效表编号
     * @return the map
     * @description 绩效表编号对应的规则查询 【task.insert】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 06:10:07.
     */
    @At
    @Ok("json")
    public Map<String,Object> findtaskInsertC11(@Param("ptno")String ptno){
        Map<String,Object> resultMap = new HashMap<>();
        List<Cbase011VO> cbase011VOS = new ArrayList<>();
        List<GropUser> gropUsers = new ArrayList<>();
/*        if(ptno !=null && !"".equals(ptno)) {
            Tbuss001VO tbuss001VO = tbuss001MO.fectchByName(ptno);
            String pdat = tbuss001VO.getPdat();
            Map<String,String> startEnd = DateUtil.getStartEndDate(pdat);
            Date nowDate = new Date();
            Date prevDate = DateUtil.formatYMDDate(startEnd.get("prevDate"));
            Date startDate = DateUtil.formatYMDDate(startEnd.get("startDate"));
            Date endDate = DateUtil.formatYMDDate(startEnd.get("endDate"));
            Cnd cnd = Cnd.NEW();
            cnd.and("stat", "=", "1");
            if (nowDate.after(prevDate) && nowDate.before(endDate)) {
                if (nowDate.after(prevDate) && nowDate.before(startDate)) {
                    cnd = cnd.and("type", "=", 1);
                } else if (nowDate.after(startDate) && nowDate.before(endDate)) {
                    cnd = cnd.and("type", "=", 0);
                }
                tbuss001VO = tbuss001MO.fetchLinksByVO(tbuss001VO,"cbase011VOS",cnd);
                cbase011VOS = tbuss001VO.getCbase011VOS();
            }
            gropUsers = baseMoFactory.getCbase009MO().fetchC9Tran(tbuss001VO.getGrop(),null);
        }*/
        if(ptno !=null && !"".equals(ptno)) {
            Condition cnd = Cnd.where("stat", "=", "1");
            Tbuss001VO tbuss001VO = tbuss001MO.fetchTransByNameCnd(ptno,"cbase011VOS",cnd);
            cbase011VOS = tbuss001VO.getCbase011VOS();
            gropUsers = baseMoFactory.getCbase009MO().fetchC9Tran(tbuss001VO.getGrop(),null);
        }
        resultMap.put("rule",cbase011VOS);
        resultMap.put("user",gropUsers);
        return resultMap;
    }

    @At
    @Ok("json")
    public Map<String,Object> findTaskUtil(@Param("page")Integer pageNumber, @Param("limit")Integer pageSize,
                                           @Param("order")String order, @Param("sort")String sort,
                                           @Param("..")TaskUtilVO taskUtilVO, @Attr("usid")String usid){
        taskUtilVO.setCsid(usid);
        Integer count = tbuss003MO.countByTaskUtil(taskUtilVO);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        return TableUtil.makeJson(0,"",count,tbuss003MO.queryAllByPagerMsg(pager,taskUtilVO,sort,order));
    }

    /**
     * Normal output stream.
     *
     * @param response the response
     * @return the output stream
     * @description 得到默认普通用户的头像，以流的形式输出
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:31.
     */
    @At
    @Ok("raw:jpg")
    public OutputStream normal(HttpServletRequest request,HttpServletResponse response){
//        response.setHeader("Content-Type"," text/html");
        return FileUtil.createFileUtil().getOsByByte(FileUtil.createFileUtil().getNormalHeader(request,"header.jpg"),response);
    }

    /**
     * Get scale map.
     *
     * @return the map
     * @description 得到主页完成任务的百分比
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 04:09:59.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> getScale(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        double grop = 1;
        double user = 1;
        String usid = request.getSession().getAttribute("usid").toString();
        String ptno = tbuss001MO.fetchNameByUsidPdat(usid,DateUtil.formatYMDate(new Date()));
        SqlExpressionGroup e1 = Cnd.exps("csid","=",usid);
        SqlExpressionGroup e2 = Cnd.exps("sta1","=",11);
        SqlExpressionGroup e3 = Cnd.exps("ptno","=",ptno);
        double all = tbuss003MO.countByCnd(Cnd.where(e3));
        double userAll = tbuss003MO.countByCnd(Cnd.where(e1).and(e3));
        if(all != 0) {
            grop = tbuss003MO.countByCnd(Cnd.where(e2).and(e3)) / all;
            if(userAll != 0) {
                user = tbuss003MO.countByCnd(Cnd.where(e1).and(e2).and(e3)) / userAll;
            }
        }
        map.put("grop", StringUtil.doubleFormat(grop*100));
        map.put("user",StringUtil.doubleFormat(user*100));
        return ResultUtil.getResult(1,"",map);
    }

    /**
     * Get new task count integer.
     *
     * @param request the request
     * @return the integer
     * @description 得到当前用户共有多少条新任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:25 05:09:50.
     */
    @At
    @POST
    @Ok("json")
    public Integer getNewTaskCount(HttpServletRequest request){
        String usid = request.getSession().getAttribute("usid").toString();
        return tbuss003MO.countByCnd(Cnd.where("csid","=",usid).and("sta1","=",0));
    }

    /**
     * Get count map.
     *
     * @param user    是否为用户看板，是:‘user’
     * @param key     过滤字段
     * @param ptno    绩效表编号
     * @param request the request
     * @return the map
     * @description 获取任务看板的计数 【board.manage 】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 09:10:19.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> getCount(@Param("user")String user,@Param("key") String key,@Param("ptno") String ptno,@Param("usid")String unam,HttpServletRequest request){
        String usid = request.getSession().getAttribute("usid").toString();
        SqlExpressionGroup e1 = null;
        SqlExpressionGroup e2 = Cnd.exps("sta1","=","0").or("sta1","=","3");
        SqlExpressionGroup e3 = Cnd.exps("sta1","=","8").or("sta1","=","10");
        SqlExpressionGroup e4 = null;
        SqlExpressionGroup e5 = null;
        if(StringUtil.checkString(user)){
           // e1 = Cnd.exps("csid","=",usid).and("puno","!=","PU0007");
            e1 = Cnd.exps("csid","=",usid);
        }

        if(StringUtil.checkString(unam)){
            ptno = tbuss001MO.fetchNameByUsidPdat(unam,DateUtil.formatYMDate(new Date()));
        }

        if(StringUtil.checkString(key)){
            e4 = Cnd.exps("cnam","like","%" + key + "%").or("synonam","like","%" + key + "%")
                    .or("sta2nam","like","%" + key + "%").or("sta3nam","like","%" + key + "%")
                    .or("cnam","like","%"+key+"%").or("titl","like","%" + key + "%");
        }

        if(StringUtil.checkString(ptno)){
            e5 = Cnd.exps("ptno","like","%"+ptno+"%");
        }
        Map<String,Object> map = new HashMap<>();
        Condition cndNew = Cnd.where(e2).and(e1).and(e4).and(e5);
        Condition cndPut = Cnd.where("sta1","=","1").and(e1).and(e4).and(e5);
        Condition cndCarry= Cnd.where("sta1","=","2").and(e1).and(e4).and(e5);
        Condition cndModify = Cnd.where("sta1","=","4").and(e1).and(e4).and(e5);
        Condition cndBack = Cnd.where(e3).and(e1).and(e4).and(e5);
        Condition cndTest = Cnd.where("sta1","=","5").and(e1).and(e4).and(e5);
        Condition cndCheck = Cnd.where("sta1","=","6").and(e1).and(e4).and(e5);
        Condition cndDone = Cnd.where("sta1","=","11").and(e1).and(e4).and(e5);
        Integer countNew = tbuss003MO.countGropTask(usid,cndNew);
        Integer countPut = tbuss003MO.countGropTask(usid,cndPut);
        Integer countCarry = tbuss003MO.countGropTask(usid,cndCarry);
        Integer countModify = tbuss003MO.countGropTask(usid,cndModify);
        Integer countBack = tbuss003MO.countGropTask(usid,cndBack);
        Integer countTest = tbuss003MO.countGropTask(usid,cndTest);
        Integer countCheck = tbuss003MO.countGropTask(usid,cndCheck);
        Integer countDone = tbuss003MO.countGropTask(usid,cndDone);
        map.put("new",countNew);
        map.put("put",countPut);
        map.put("carry",countCarry);
        map.put("modify",countModify);
        map.put("back",countBack);
        map.put("test",countTest);
        map.put("check",countCheck);
        map.put("done",countDone);
        return map;
    }

    /**
     * Get count map.
     *
     * @param key     过滤字段
     * @param ptno    绩效表编号
     * @param request the request
     * @return the map
     * @description 获取关键用户任务看板的计数 【board.user user.board】
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:19 09:10:19.
     */
    @At
    @POST
    @Ok("json")
    public Map<String,Object> getKSIDCount(@Param("key") String key,@Param("ptno") String ptno,HttpServletRequest request){
        String usid = request.getSession().getAttribute("usid").toString();
        SqlExpressionGroup e1 = Cnd.exps("ksid","=",usid).and("puno","!=","PU0007");
        SqlExpressionGroup e4 = null;
        SqlExpressionGroup e5 = null;
        if(key != null){
            e4 = Cnd.exps("cnam","like","%" + key + "%").or("synonam","like","%" + key + "%")
                    .or("sta2nam","like","%" + key + "%").or("sta3nam","like","%" + key + "%")
                    .or("cnam","like","%"+key+"%").or("titl","like","%" + key + "%");
        }
        if(ptno != null){
            e5 = Cnd.exps("ptno","like","%"+ptno+"%");
        }
        Map<String,Object> map = new HashMap<>();
        Condition cndTest = Cnd.where("sta1","=","5").and(e1).and(e4).and(e5);
        Condition cndCheck = Cnd.where("sta1","=","6").and(e1).and(e4).and(e5);
        Condition cndDone = Cnd.where("sta1","=","11").and(e1).and(e4).and(e5);
        Condition cndNot = Cnd.where("sta1","!=","11").and(e1).and(e4).and(e5);
        Integer countTest = tbuss003MO.countByCnd(cndTest);
        Integer countCheck = tbuss003MO.countByCnd(cndCheck);
        Integer countDone = tbuss003MO.countByCnd(cndDone);
        Integer countNot = tbuss003MO.countByCnd(cndNot);
        map.put("test",countTest);
        map.put("check",countCheck);
        map.put("done",countDone);
        map.put("not",countNot);
        return map;
    }
}
