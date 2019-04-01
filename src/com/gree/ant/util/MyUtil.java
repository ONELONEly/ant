package com.gree.ant.util;

import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import javax.servlet.ServletContext;

public class MyUtil {

    private ServletContext sc;

    public String getPath(String path){
        return sc.getRealPath(path);
    }

    private String makePost(){
        String url = "http://10.2.8.188/GreeBIWebapi/api/ticket"; //职层='中层干部'
        String params1 = "{\"serverip\":\"10.2.4.88\",\"username\":\"erpuser\",\"password\":\"erp*951\",\"sitename\":\"ComputerBI\"}";
        System.out.println(params1);
        Map<String, Object> params2 = (Map<String, Object>) Json.fromJson(params1);
        Header headers = Header.create().set("Content-Type", "application/json");
        Response response = Http.post3(url, Json.toJson(params2, JsonFormat.compact()), headers, 200000, 2000);
        return response.getContent();
    }
}
