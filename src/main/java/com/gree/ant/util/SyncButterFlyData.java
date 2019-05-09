package com.gree.ant.util;

import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.vo.util.ButterFlyOrganization;
import com.gree.ant.vo.util.ButterFlyStaff;
import com.gree.ant.vo.util.ButterFlyVO;
import org.nutz.dao.Dao;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.*;

public class SyncButterFlyData {

    private static SyncButterFlyData syncButterFlyData = null;

    public static SyncButterFlyData createSyncDataUtil() {
        if(syncButterFlyData == null){
            syncButterFlyData = new SyncButterFlyData();
        }
        return syncButterFlyData;
    }

    public void carrySync(Dao dao, ButterFlyDAOImp butterFlyDAOImp){
        dao.create(ButterFlyStaff.class,true);
        syncUserStaff(dao);
        dao.create(ButterFlyOrganization.class,true);
        syncStructureData(dao);
        dao.create(ButterFlyVO.class,true);
        syncUserData(butterFlyDAOImp);
//        butterFlyDAOImp.updateUserPostsNormal();
    }

    private void syncStructureData(Dao dao){
        int pageSize = 10000;
        structureMakePost(1,pageSize,combineCompanys(getCompany(100,148,new Integer[]{},new Integer[]{})),true,dao);
    }

    private void syncUserData(ButterFlyDAOImp butterFlyDAOImp){
        int pageSize = 10000;
        userMakePost(1,pageSize,combineCompanys(getCompany(100,148,new Integer[]{},new Integer[]{})),true,butterFlyDAOImp);
    }

    public void syncUserStaff(Dao dao){
        int pageSize = 10000;
        String []queryCons = {"公司领导","中层干部","主管"};
        for(String queryCon:queryCons) {
            staffMakePost(1, pageSize, combineCompanys(getCompany(100, 148, new Integer[]{}, new Integer[]{})), queryCon, true, dao);
        }
    }

    private void userMakePost(Integer page, Integer pageSize, String company, Boolean first, ButterFlyDAOImp butterFlyDAOImp){
        Map responseMap =  makePost(page,pageSize,company,"员工编号","员工","");
        if(responseMap.get("ReturnCode") != null && responseMap.get("ReturnCode").toString().equals("0")) {
            int total = Integer.parseInt(responseMap.get("Total").toString());
            if (total / pageSize >= 1 && first) {
                int count = 0;
                if (total % pageSize == 0) {
                    count = total / pageSize;
                } else {
                    count = (total / pageSize) + 1;
                }
                for (int i = 2; i <= count; i++) {
                    userMakePost(i,pageSize,company,false,butterFlyDAOImp);
                }
            }
            List<Map<String, String>> resultMap = (List<Map<String, String>>) responseMap.get("Items");
            List<ButterFlyVO> flyVOS = new ArrayList<>();
            for (Map<String, String> result : resultMap) {
                String isOrganizer = result.get("是否占编");
                if(isOrganizer.equals("是")) {
                    String organization = result.get("所属组织长编码");
                    if (StringUtil.checkString(organization)) {
                        String comp = organization.substring(3, 6);
                        String dept = organization.length() >= 15 ? organization.substring(7, 15) : comp;
                        String card = result.get("身份证号码") == null ? "" : result.get("身份证号码");
                        String postsNumber = StringUtil.checkString(result.get("所属岗位编码")) ? result.get("所属岗位编码") : "0";
                        String postsName = StringUtil.checkString(result.get("所属岗位")) ? result.get("所属岗位") : "离职员工岗位";
                        String posts = butterFlyDAOImp.fetchPostsByStaff(postsName, postsNumber);
                        String department = organization.length() >= 15 ? butterFlyDAOImp.fetchDeptByNumber(dept) : "";
                        String email = "";
                        String sex = StringUtil.checkString(result.get("性别")) ? result.get("性别") : "未知性别";
                        String flag = StringUtil.checkString(result.get("员工编号")) ? result.get("员工编号") : "未知员工编号";
                        String userName = StringUtil.checkString(result.get("员工姓名")) ? result.get("员工姓名") : "未知员工姓名";
                        if (StringUtil.checkString(card)) {
                            email = butterFlyDAOImp.fetchEmailByCard(card);
                        }
                        if (!StringUtil.checkString(posts)) {
                            if (result.get("所属岗位").equals("董事长")) {
                                posts = "董事长";
                            } else {
                                posts = "员工";
                            }
                        }
                        if (!StringUtil.checkString(department)) {
                            department = result.get("所属组织");
                        }

                        flyVOS.add(new ButterFlyVO(comp, getCompnayName(Integer.parseInt(comp)), dept,
                                department, posts, postsName, Integer.parseInt(postsNumber), flag,
                                userName, sex, card, email, isOrganizer, organization));
                    }
                }
            }
            butterFlyDAOImp.insert(flyVOS);
        }
    }

    private void staffMakePost(Integer page, Integer pageSize,String company, String queryCon, Boolean first, Dao dao){
        String queryConSql = "职层='"+queryCon+"'";
        Map responseMap =  makePost(page,pageSize,company,"岗位编码","岗位",queryConSql);
        System.out.println(responseMap);
        if(responseMap.get("ReturnCode") != null && responseMap.get("ReturnCode").toString().equals("0")) {
            int total = Integer.parseInt(responseMap.get("Total").toString());
            if (total / pageSize >= 1 && first) {
                int count = 0;
                if (total % pageSize == 0) {
                    count = total / pageSize;
                } else {
                    count = (total / pageSize) + 1;
                }
                for (int i = 2; i <= count; i++) {
                    staffMakePost(i,pageSize,company,queryCon,false,dao);
                }
            }
            List<Map<String, String>> resultMap = (List<Map<String, String>>) responseMap.get("Items");
            List<ButterFlyStaff> flyStaffs = new ArrayList<>();
            for (Map<String, String> result : resultMap) {
                flyStaffs.add(new ButterFlyStaff(result.get("岗位名称"),result.get("岗位编码"),queryCon));
            }
            dao.insert(flyStaffs);
        }
    }

    private void structureMakePost(Integer page, Integer pageSize,String company, Boolean first, Dao dao){
        Map responseMap =  makePost(page,pageSize,company,"组织编码","组织架构","");
        System.out.println(responseMap);
        if(responseMap.get("ReturnCode") != null && responseMap.get("ReturnCode").toString().equals("0")) {
            int total = Integer.parseInt(responseMap.get("Total").toString());
            if (total / pageSize >= 1 && first) {
                int count = 0;
                if (total % pageSize == 0) {
                    count = total / pageSize;
                } else {
                    count = (total / pageSize) + 1;
                }
                for (int i = 2; i <= count; i++) {
                    structureMakePost(i,pageSize,company,false,dao);
                }
            }
            List<Map<String, String>> resultMap = (List<Map<String, String>>) responseMap.get("Items");
            List<ButterFlyOrganization> flyOrganizations = new ArrayList<>();
            for (Map<String, String> result : resultMap) {
                flyOrganizations.add(new ButterFlyOrganization(result.get("组织编码"),result.get("组织长编码"),result.get("组织名称"),result.get("组织层级")));
            }
            dao.insert(flyOrganizations);
        }
    }

    private Map makePost(Integer page,Integer count,String company,String orderKey,String aimType,String queryCon){
        String url = "http://hr.api.gree.com:8506/kingdee/kingdeeApi"; //职层='中层干部'
        String params1 = "{\"username\":\"zhgl002\",\"pwd\":\"B0F3E6C1-8950-455E-89B1-AE03DB8798A2\",\"orderKey\":\""+orderKey+"\",\"busObj\":\""+aimType+"\"," +
                "\"pageNo\":\""+page+"\",\"pageSize\":\""+count+"\",\"orgRangeInfo\":{"+company+"}," +
                "\"fieldList\":\"*\",\"queryCon\":\""+queryCon+"\"" +
                "}";
        System.out.println(params1);
        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);
        Header headers = Header.create().set("Content-Type", "application/json");
        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 200000, 2000);
        return Json.fromJson(Map.class, response.getContent());
    }

    private String combineCompanys(Integer[] companys){
        StringBuilder tLine = new StringBuilder();
        int count = 0;
        for (Integer company : companys){
            int companyLength = companys.length;
            if(companyLength == 1){
                tLine = new StringBuilder(company + ":1");
            }else{
                if(count == companyLength - 1) {
                    tLine.append("\"").append(companys[companys.length - 1]).append("\"").append(":1");
                }else{
                    tLine.append("\"").append(companys[count]).append("\"").append(":1,");
                }
            }
            count ++;
        }
        return tLine.toString();
    }

    private Integer[] getCompany(Integer from,Integer end,Integer[] otherIn,Integer[] otherOut){
        List<Integer> params = new ArrayList<>();
        while(from < end){
            params.add(from);
            from++;
        }
        List<Integer> addParams = new ArrayList<>(Arrays.asList(otherIn));
        for (Integer param : params){
            for(Integer addParam : addParams){
                if(param.equals(addParam)){
                    addParams.remove(addParam);
                }
            }
        }
        params.addAll(addParams);
        List<Integer> removeParams = new ArrayList<>(Arrays.asList(otherOut));
        for (Integer param : params){
            for (Integer removeParam : removeParams){
                if (removeParam.equals(param)){
                    params.remove(param);
                }
            }
        }
        Integer []result = new Integer[params.size()];
        return params.toArray(result);
    }

    private String getCompnayName(Integer range){
        Map<Integer,String> rangeMap = new HashMap<>();
        rangeMap.put(100,"珠海格力电器股份有限公司");
        rangeMap.put(101,"格力电器（重庆）有限公司");
        rangeMap.put(102,"格力电器（合肥）有限公司");
        rangeMap.put(103,"格力电器（郑州）有限公司");
        rangeMap.put(104,"格力电器（武汉）有限公司");
        rangeMap.put(105,"格力电器（石家庄）有限公司");
        rangeMap.put(106,"格力电器（芜湖）有限公司");
        rangeMap.put(107,"长沙格力暖通制冷设备有限公司");
        rangeMap.put(108,"格力电器（杭州）有限公司");
        rangeMap.put(109,"格力电器（南京）有限公司");
        rangeMap.put(110,"格力电器（洛阳）有限公司");
        rangeMap.put(111,"格力电器（中山）小家电制造有限公司");
        rangeMap.put(112,"石家庄格力电器小家电有限公司");
        rangeMap.put(113,"格力大松（宿迁）生活电器有限公司");
        rangeMap.put(114,"珠海凌达压缩机有限公司");
        rangeMap.put(115,"合肥凌达压缩机有限公司");
        rangeMap.put(116,"郑州凌达压缩机有限公司");
        rangeMap.put(117,"重庆凌达压缩机有限公司");
        rangeMap.put(118,"武汉凌达压缩机有限公司");
        rangeMap.put(119,"珠海凯邦电机制造有限公司");
        rangeMap.put(120,"合肥凯邦电机有限公司");
        rangeMap.put(121,"河南凯邦电机有限公司");
        rangeMap.put(122,"重庆凯邦电机有限公司");
        rangeMap.put(123,"珠海格力电工有限公司");
        rangeMap.put(124,"格力电工（马鞍山）有限公司");
        rangeMap.put(125,"格力电工（眉山）有限公司");
        rangeMap.put(126,"格力电工（南京）有限公司");
        rangeMap.put(127,"珠海格力新元电子有限公司");
        rangeMap.put(128,"珠海格力智能装备有限公司");
        rangeMap.put(129,"格力智能装备（武汉）有限公司");
        rangeMap.put(130,"珠海格力机器人有限公司");
        rangeMap.put(131,"珠海格力智能装备技术研究院有限公司");
        rangeMap.put(132,"珠海格力精密模具有限公司");
        rangeMap.put(133,"格力精密模具（武汉）有限公司");
        rangeMap.put(134,"芜湖格力精密制造有限公司");
        rangeMap.put(135,"珠海励高精工制造有限公司");
        rangeMap.put(136,"珠海艾维普信息技术有限公司");
        rangeMap.put(137,"珠海联云科技有限公司");
        rangeMap.put(138,"珠海格力绿色再生资源有限公司");
        rangeMap.put(139,"郑州格力绿色再生资源有限公司");
        rangeMap.put(140,"芜湖绿色再生资源有限公司");
        rangeMap.put(141,"湖南绿色再生资源有限公司");
        rangeMap.put(142,"石家庄绿色再生资源有限公司");
        rangeMap.put(143,"天津绿色再生资源利用有限公司");
        rangeMap.put(144,"珠海格力新材料有限公司");
        rangeMap.put(145,"珠海格力能源环境技术有限公司");
        rangeMap.put(146,"珠海格力运输有限公司");
        rangeMap.put(147,"珠海格力集团财务有限责任公司");
        rangeMap.put(148,"格力（武安）精密制造有限公司");
        rangeMap.put(149,"格力电器（成都）有限公司");
        rangeMap.put(150,"合肥晶弘电器有限公司");
        rangeMap.put(151,"珠海横琴格力商业保理有限公司");
        rangeMap.put(152,"珠海格力节能环保制冷技术研究中心有限公司");
        rangeMap.put(153,"珠海格力机电工程有限公司");
        rangeMap.put(154,"珠海零边界集成电路有限公司");
        return rangeMap.get(range);
    }

}
