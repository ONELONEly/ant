package com.gree.ant;


import com.gree.ant.util.DateUtil;
import com.gree.ant.util.EncryUtil;
import com.gree.ant.util.TokenUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    @org.junit.Test
    public void test() throws Exception {
        String sys = System.currentTimeMillis()+"";
        EncryUtil.passwordEncode("","JINYU");
        System.out.print(sys);
    }
}
