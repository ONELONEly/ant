package com.gree.ant.util;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * The type String util.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 字符串工具
 * @title StringUtil
 * @createTime 2017 :09:21 09:09:50.
 */
public class StringUtil {

    /**
     * Check string boolean.
     *
     * @param infor the infor
     * @return the boolean
     * @description 检查字符串是否为空,参数为一个
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 09:09:11.
     */
    public static Boolean checkString(String infor){
        Boolean check = false;
        if(infor!=null && !"".equals(infor)){
            check = true;
        }
        return check;
    }

    /**
     * Check string boolean.
     *
     * @param infor1 the 参数一
     * @param infor2 the 参数而
     * @param infor3 the 参数三
     * @return the boolean
     * @description 检查字符串是否为空,参数为三个
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 09:09:50.
     */
    public static Boolean checkString(String infor1,String infor2,String infor3){
        Boolean check = false;
        if(infor1!=null && !"".equals(infor1) && infor2!=null && !"".equals(infor2) && infor3!=null && !"".equals(infor3)){
            check = true;
        }
        return check;
    }

    public static Double doubleFormat(double count){
        DecimalFormat format = new DecimalFormat("######0.00");
        return Double.parseDouble(format.format(count));
    }

    public static String getUsid(HttpSession session){
        return session.getAttribute("usid") == null?null:session.getAttribute("usid").toString();
    }

    public static String ClobToString(Clob clob) throws SQLException, IOException {
        String reString = "";
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();

        if(br!=null){
            br.close();
        }
        if(is!=null){
            is.close();
        }
        return reString;
    }
}
