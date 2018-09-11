package com.gree.ant.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description Double类型的工具类
 */
public class DoubleUtil {

    public static Double format(Double input){
        double result = 0.00;
        if(input != null) {
            BigDecimal decimal = new BigDecimal(input);
            result = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return result;
    }

    public static Double format_nice(Double input){
        double result = 0.00;
        if (input != null){
            DecimalFormat format = new DecimalFormat("#.00");
            result = Double.valueOf(format.format(input));
        }
        return result;
    }

    public static Double format_low(Double input){
        double result = 0.00;
        if (input != null) {
            result = Double.valueOf(String.format("%.2f", input));
        }
        return result;
    }
    public static Double format_middle(Double input){
        double result = 0.00;
        if (input != null) {
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            result = Double.valueOf(nf.format(input));
        }
        return result;
    }
}
