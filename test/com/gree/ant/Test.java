package com.gree.ant;


import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.Map;

public class Test {

    @org.junit.Test
    public void test() throws Exception {
//        String tLine = "01!100!10000004!10000005";
//        System.out.println(tLine.substring(3,6));
//        System.out.println(tLine.substring(7,15));
        String url = "http://hr.api.gree.com:8506/kingdee/kingdeeApi"; //职层='中层干部'
        String params1 = "{\"username\":\"zhgl002\",\"pwd\":\"B0F3E6C1-8950-455E-89B1-AE03DB8798A2\",\"orderKey\":\"岗位编码\",\"busObj\":\"岗位\"," +
                "\"pageNo\":\""+1+"\",\"pageSize\":\"100\",\"orgRangeInfo\":{\""+100+"\":1}," +
                "\"fieldList\":\"*\",\"queryCon\":\"\"" +
                "}";
        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);
        Header headers = Header.create().set("Content-Type", "application/json");
        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 200000, 2000);
        Map responseMap = Json.fromJson(Map.class, response.getContent());
        System.out.print(responseMap);
//        SyncDSTaskDaemon.taskList.add(new SyncDSTaskVO(new Tbuss003VO(),new Cbase000VO()));
//        SyncDSTaskDaemon.taskList.add(new SyncDSTaskVO(new Tbuss003VO(Math.random()+""),new Cbase000VO()));
//        int okay = SyncDSTaskDaemon.taskList.size();
//        System.out.println(okay);
//        for (int i = okay - 1;i >= 0;i--){
//            System.out.println("remove");
//            SyncDSTaskDaemon.taskList.remove(i);
//        }
//        SyncDSTaskDaemon.taskList.add(new SyncDSTaskVO(new Tbuss003VO(),new Cbase000VO()));
//        SyncDSTaskDaemon.taskList.add(new SyncDSTaskVO(new Tbuss003VO(Math.random()+""),new Cbase000VO()));
//        System.out.println(SyncDSTaskDaemon.taskList.size());
    }
}
