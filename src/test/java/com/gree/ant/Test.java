package com.gree.ant;


import com.gree.ant.util.DateUtil;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void test() throws Exception {

        System.out.println(DateUtil.getWeek());
    }
}
