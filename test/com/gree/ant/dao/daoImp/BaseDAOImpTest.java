package com.gree.ant.dao.daoImp;

import com.gree.MyNutTestRunner;
import com.gree.ant.mo.Cbase015MO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.HTMLUtil;
import com.gree.ant.vo.*;
import com.gree.ant.vo.util.ButterFlyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MyNutTestRunner.class)
@IocBean
public class BaseDAOImpTest {

    @Inject("refer:$ioc")
    private Ioc ioc;

    @Inject("refer:daoFX")
    private Dao dao;

    @Inject
    private Cbase015MO cbase015MO;

    @Test
    public void fetchByNam() throws Exception {
    }

    @Test
    public void fetchByName() throws Exception {
    }

    @Test
    public void queryByCndPager() throws Exception {
        int range = 101;
        while(range < 110) {
            carryInsert(range,1,true);
            range++;
        }
    }

    @Test
    public void insert() throws Exception {
        int range = 100;
        while(range < 148) {
            carryInsert(range,1,true);
            range++;
        }
    }

    private void carryInsert(Integer range,Integer page,Boolean first){
        List<ButterFlyVO> butterFlyVOS = new ArrayList<>();
        String url = "http://hr.api.gree.com:8506/kingdee/kingdeeApi"; //职层='中层干部'
        String params1 = "{\"username\":\"zhgl002\",\"pwd\":\"B0F3E6C1-8950-455E-89B1-AE03DB8798A2\",\"orderKey\":\"岗位编码\",\"busObj\":\"岗位\"," +
                "\"pageNo\":\""+page+"\",\"pageSize\":\"10000\",\"orgRangeInfo\":{\""+range+"\":1}," +
                "\"fieldList\":\"*\",\"queryCon\":\"职层='主管'\"" +
                "}";
        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);
        Header headers = Header.create().set("Content-Type", "application/json");
        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 200000, 2000);
        Map responseMap = Json.fromJson(Map.class, response.getContent());
        System.out.print(responseMap);
        if(responseMap.get("ReturnCode") != null && responseMap.get("ReturnCode").toString().equals("0")) {
            int total = Integer.parseInt(responseMap.get("Total").toString());
            if (total / 10000 >= 1 && first) {
                int count = 0;
                if (total % 10000 == 0) {
                    count = total / 10000;
                } else {
                    count = (total / 10000) + 1;
                }
                for (int i = 6; i <= count; i++) {
                    carryInsert(range, i, false);
                }
            }
            List<Map<String, String>> resultMap = (List<Map<String, String>>) responseMap.get("Items");
            for (Map<String, String> result : resultMap) {
                    List<ButterFlyVO> flyVOS = dao.query(ButterFlyVO.class,Cnd.where("postsNumber","=",result.get("岗位编码")).and("posts","=",result.get("岗位名称")));
                    if(flyVOS.size() > 0) {
                        for(ButterFlyVO butterFlyVO:flyVOS) {
                            butterFlyVO.setPosts("主管");
//                    butterFlyVOS.add(new ButterFlyVO(getCompnayName(range), result.get("所属组织"), result.get("所属岗位"), result.get("员工编号").substring(3), result.get("员工姓名"), result.get("性别"), result.get("身份证号码"), result.get("是否占编"),result.get("所属组织长编码")));
                            dao.update(butterFlyVO);
                        }
                    }
                }
            }
//            dao.insert(butterFlyVOS);
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

    @Test
    public void insert1() throws Exception {
        List<ValueObject> cbase008VOS = new ArrayList<>();
        Cbase008VO cbase008VO = new Cbase008VO("S00002","测试");
        Cbase008VO cbase008VO1 = new Cbase008VO("S00003","测试1");
        cbase008VOS.add(cbase008VO);
        cbase008VOS.add(cbase008VO1);
    }

    @Test
    public void fastInsert() throws Exception {
        Cbase008VO cbase008VO = new Cbase008VO("S00005","测试");
    }

    @Test
    public void fastInsert1() throws Exception {
        List<ValueObject> cbase008VOS = new ArrayList<>();
        Cbase008VO cbase008VO = new Cbase008VO("S00006","测试");
        Cbase008VO cbase008VO1 = new Cbase008VO("S00007","测试1");
    }

    @Test
    public void insertWith() throws Exception {
        Cbase007VO cbase007VO = new Cbase007VO("R00001","测试","400");
        List<Cbase002VO> cbase002VOS = new ArrayList<>();
        cbase002VOS.add(new Cbase002VO("700","测试",1,""));
        cbase002VOS.add(new Cbase002VO("701","测试",1,""));
        cbase007VO.setCbase002VOS(cbase002VOS);
    }

    @Test
    public void insertLinks() throws Exception {
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {
            }

    @Test
    public void fetchByID() throws Exception {
    }

    @Test
    public void clear() throws Exception {
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void drop() throws Exception {
    }

    @Test
    public void func() throws Exception {

    }

}