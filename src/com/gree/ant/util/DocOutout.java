package com.gree.ant.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The type Doc outout.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 多线程内部类
 * 读取转换时cmd进程的标准输出流和错误输出流,这样做的原因是如果不读取流，进程将死锁
 * @title DocOutout
 * @createTime 2017 :12:08 11:12:25.
 */
public class DocOutout extends Thread{

    private InputStream is;

    public DocOutout(InputStream is){
        this.is = is;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str;
        try {
            //将流读取一遍
            while((str = br.readLine())!=null){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
