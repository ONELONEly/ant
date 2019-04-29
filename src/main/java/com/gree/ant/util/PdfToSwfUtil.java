package com.gree.ant.util;

import com.gree.ant.vo.util.MVCResultVO;
import com.gree.ant.vo.util.ResultVO;

import java.io.File;
import java.io.IOException;

public class PdfToSwfUtil{


    private File pdfFile;
    private File swfFile;

    /**
     * Instantiates a new Pdf to swf util.
     *
     * @param pdfFile 要转化pdf的文件
     * @param swfFile 转化后的swf文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:11 03:12:37.
     */
    public PdfToSwfUtil(File pdfFile, File swfFile) {
        this.pdfFile = pdfFile;
        this.swfFile = swfFile;
    }

    /**
     * To swf result vo.
     *
     * @return the result vo
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     * @description 执行pdf转化swf
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:11 03:12:15.
     */
    public synchronized MVCResultVO toSwf() throws IOException,InterruptedException{
        String msg = "";
        Integer code = 0;
        Runtime r = Runtime.getRuntime();
        if(pdfFile.exists()){
            if(!swfFile.exists()){
                Process p;
                if(isWindowSys()) {
                    String cmd = Constants.swfExePath + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9";
                    p = r.exec(cmd); //Runtime执行后返回创建的进程对象
                }else{
                    String[] cmd = new String[3];
                    cmd[0] = Constants.swfExePath;
                    cmd[1] = pdfFile.getPath();
                    cmd[2] = swfFile.getPath();
                    p = Runtime.getRuntime().exec(cmd);
                }
                //非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
                new DocOutout(p.getInputStream()).start();
                new DocOutout(p.getErrorStream()).start();
                p.waitFor(); //调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
                code = 1;
            }else{
                msg = "目标文件名称已存在,请更换文件名称！";
            }
        }else{
            msg = "转换的目标文件不存在！";
        }
        return new MVCResultVO(code,msg);
    }

    private Boolean isWindowSys(){
        String p = System.getProperty("os.name");
        return p.toLowerCase().contains("windows");
    }
}
