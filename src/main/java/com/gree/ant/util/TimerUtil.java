package com.gree.ant.util;

import com.gree.ant.mo.Cbase013MO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@IocBean
public class TimerUtil {

    public static void main(String[] args) {
//        timer1();
      TimerUtil t=new TimerUtil();
        /*  t.timer3();*/
      t.timer5();
  /*      timer3();
        timer4();*/
    }

    @Inject("refer:cbase013MO")
    private Cbase013MO cbase013MO;

    // 第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time)
    public static void timer1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒

    }

    // 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
    // schedule(TimerTask task, long delay, long period)
    public static void timer2() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
     /*           Cbase013MO c=new Cbase013MO();
                int code = c.tongbuDSSystem();*/
            }
        }, 1000, 5000);
        timer.cancel();
    }

    // 第三种方法：设定指定任务task在指定延迟delay后进行固定频率peroid的执行。
    // scheduleAtFixedRate(TimerTask task, long delay, long period)
    public void timer3() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                int code = cbase013MO.tongbuDSSystem();
            }
        }, 1000, 5000*10);
    }

    // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    public static void timer4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }


    public  void timer5(){

        Tasks.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int code = cbase013MO.tongbuDSSystem();
            }
        },10);
        Lang.quiteSleep(60*1000*3000);
    }
}
