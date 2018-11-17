package com.gree.ant.util;

import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableUtil {

    /**
     * Make json map.
     *
     * @param code  返回码，默认0为成功
     * @param msg   返回，一般出现错误才设置
     * @param count 返回共有多少条记录
     * @param data  列字段
     * @return the map
     * @description 处理传来的数据，包装秤Table想要的数据格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:05 03:09:37.
     */
    public static Map<String,Object> makeJson(Integer code,String msg,Integer count,Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("count",count);
        map.put("data",data);
        return map;
    }

    /**
     * Format pager pager.
     *
     * @param pageSize    分页的大小
     * @param pagerNumber 当前的页数
     * @param recordCount 共有多少条记录
     * @return 返回被封装的Pager
     * @description 封装可用的Pager
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 09:09:50.
     */
    public static Pager formatPager(Integer pageSize,Integer pagerNumber,Integer recordCount){
        Pager pager = new Pager();
        pager.setPageNumber(pagerNumber);
        pager.setPageSize(pageSize);
        pager.setRecordCount(recordCount);
        return pager;
    }

    public static QueryResult formatQueryResult(Pager pager, List<?> classes){
        QueryResult queryResult = new QueryResult();
        queryResult.setPager(pager);
        queryResult.setList(classes);
        return queryResult;
    }
}
