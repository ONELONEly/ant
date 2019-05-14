package com.gree.ant.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.gree.ant.exception.KellyException;
import com.gree.ant.vo.enumVO.ResultEnum;
import com.gree.ant.vo.util.MVCResultVO;

import java.io.*;
import java.net.ConnectException;

public class DocToPdfUtil extends Thread{

    private File docFile;
    private File pdfFile;
    private File swfFile;
    private String returnPath;
    private Integer returnCode;
    private String returnMsg;

    /**
     * Instantiates a new Doc to pdf util.
     *
     * @param fileName 文件的全称（名称和尾缀).
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:12 09:12:39.
     */
    public DocToPdfUtil(String fileName) {
        this.docFile = new File(Constants.docFilePath+fileName);
        this.pdfFile = new File(Constants.pdfFilePath+FileUtil.createFileUtil().getFileName(fileName)+".pdf");
        this.swfFile = new File(Constants.swfFilePath+FileUtil.createFileUtil().getFileName(fileName)+".swf");
    }

    @Override
    public void run() {
        try {
            MVCResultVO resultVO = toPdf();
            if (resultVO.getCode() == 1) {
                PdfToSwfUtil toSwf = new PdfToSwfUtil(pdfFile, swfFile);
                resultVO = toSwf.toSwf();
                if (pdfFile.exists()) {
                    pdfFile.delete();
                }
                this.returnPath = swfFile.getPath();
            }
            this.returnCode = resultVO.getCode();
            this.returnMsg = resultVO.getMsg();
        } catch (InterruptedException|IOException e) {
            e.printStackTrace();
        }
    }

    private MVCResultVO toPdf(){
        String msg = "";
        Integer code = 0;
        if(docFile.exists()){
            if(!pdfFile.exists()){
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
                try {
                    connection.connect();
                } catch (ConnectException e) {
                    throw new KellyException(ResultEnum.CONNECT_REFUSE);
                }
                DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                converter.convert(docFile,pdfFile);
                connection.disconnect();
                code = 1;
            }else{
                msg = "目标文件名称已存在,请更换文件名称！";
            }
        }else{
            msg = "转换的目标文件不存在！";
        }
        return new MVCResultVO(code,msg);
    }

    public String getReturnPath() {
        return returnPath;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }
}
