package com.gree.ant.util;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 算数的工具类
 * @createTime 2019 -03-18 10:36:43
 */
public class CountUtil {

    /**
     * @param average
     * @param stage
     * @return 该条任务的分数
     * @description 通过任务的平均分和评定等级给予评分
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -03-18 10:44:04
     */
    public static double getConsByAverageAndStage(Double average,Integer stage){
        double result = 0;
        if(stage == 0){
            result = average*0.6;
        }else if(stage == 1){
            result = average*0.7;
        }else if(stage == 2){
            result = average*0.8;
        }else if(stage == 3){
            result = average*0.9;
        }else if(stage == 4){
            result = average;
        }
        return result;
    }
}
