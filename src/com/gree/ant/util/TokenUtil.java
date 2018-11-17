package com.gree.ant.util;

import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    private static TokenUtil tokenInstance = null;

    public static TokenUtil getInstance(){
        if(tokenInstance == null) {
            tokenInstance = new TokenUtil();
        }
        return tokenInstance;
    }

    public Map<String, String> makeToken(){
        Map<String,String> resultMap = new HashMap<>();
        String password = System.currentTimeMillis()+"";
        String slat = "JINYU";
        String token = EncryUtil.passwordEncode(password,slat);
        resultMap.put("token",token);
        resultMap.put("password",password);
        resultMap.put("slat",slat);
        return resultMap;
    }
}
