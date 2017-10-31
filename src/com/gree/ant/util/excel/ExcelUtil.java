package com.gree.ant.util.excel;

import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Excel util.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description Excel的公用工具类
 * @title ExcelUtil
 * @createTime 2017 :09:26 03:09:23.
 */
public class ExcelUtil {

    /**
     * Get foramat writable cell format.
     *
     * @param style 字体的风格
     * @param size  字体的尺寸
     * @param color 字体的颜色
     * @return the writable cell format
     * @description 设置字体的格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:22 06:09:55.
     */
    public static WritableCellFormat getForamat(String style, Integer size, Colour color){
        WritableFont font = null;
        if("BOLD".equals(style)){
            font = new WritableFont(WritableFont.ARIAL,size,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        }else if("NOBOLD".equals(style)){
            font = new WritableFont(WritableFont.ARIAL,size,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        }
        return new WritableCellFormat(font);
    }

    public static void setHeader(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException{
        if(request.getHeader("User-Agent").contains("MSIE 5.5")){
            response.setHeader("Content-Disposition", "filename="
                    + new String((fileName+".xls").getBytes("gb2312"), "ISO8859_1"));
        }else{
            response.addHeader("Content-Disposition","filename="+new String((fileName+".xls").getBytes("gb2312"),"ISO8859_1"));
        }
    }
}
