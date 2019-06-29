package com.gree.ant.util;

import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;
import org.nutz.mvc.upload.TempFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class FileUtil {

    private FileInputStream fis;
    private FileOutputStream fos;
    private InputStream is;
    private OutputStream os;
    private static Integer BUFFER_SIZE = 1024;

    //1.本地文件存储地址
/*    private static String IMAGE_SAVEPATH = "E:\\filePath\\";
    private static String FILE_SAVEPATH = "E:\\filePath\\";
    private static String BASIC_SAVEPATH = "E:\\filePath\\";
   private static String NORMALIMAGE = "static\\images\\header.jpg";*/

    //2.本地访问服务器文件存储地址
//    private static String IMAGE_SAVEPATH = "\\\\10.1.18.83\\ant\\image\\";
//    private static String FILE_SAVEPATH = "\\\\10.1.18.83\\ant\\file\\";
//    private static String BASIC_SAVEPATH = "\\\\10.1.18.83\\ant\\";
//    private static String NORMALIMAGE = "static\\images\\header.jpg";

    //3.服务器文件存储地址

    private static String IMAGE_SAVEPATH = "\\\\192.13.183.83\\ant\\image\\";
    private static String FILE_SAVEPATH = "\\\\192.13.183.83\\ant\\file\\";
    private static String BASIC_SAVEPATH = "\\\\192.13.183.83\\ant\\";
    private static String NORMALIMAGE = "static\\images\\";


    public static FileUtil createFileUtil() {
        return new FileUtil();
    }

    /**
     * Copy integer.
     *
     * @param fis 输入流
     * @param fos 输出流
     * @return 返回文件的字节数，-1上传失败
     * @description 使用文件输入输出流进行文件复制
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:04 02:09:25.
     */
    private Integer copy(FileInputStream fis,FileOutputStream fos){
        Integer byteCount = 0;
        Integer bytesRead;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            while((bytesRead = fis.read(buffer,0,BUFFER_SIZE))!=-1){
                fos.write(buffer,0,bytesRead);
                byteCount += bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return byteCount;
    }

    /**
     * Format is blob byte [ ].
     *
     * @param file the file
     * @return the byte [ ]
     * @description 将文件格式的文件转成byte[]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:09.
     */
    public byte[] formatByteByFile(TempFile file){
        byte[] buffer = null;
        try {
            is = file.getInputStream();
            buffer = new byte[is.available()];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * Format is blob byte [ ].
     *
     * @param blob the blob
     * @return the byte [ ]
     * @description 将Blob格式的文件转成byte[]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:09.
     */
    public byte[] formatByteByBlob(Blob blob){
        byte[] buffer = null;
        try {
            is = blob.getBinaryStream();
            buffer = new byte[is.available()];
            is.read(buffer);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * Format by string clob.
     *
     * @param note 字符串
     * @return 返回Clob格式
     * @description 将字符串转成Clob格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 11:09:18.
     */
    public Clob formatClobByString(String note){
        Clob clob = null;
        if(note!=null) {
            try {
                clob = new SerialClob(note.toCharArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clob;
    }

    /**
     * Convert clob string.
     *
     * @param clob the clob
     * @return the string
     * @description 将Clob
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 01:09:26.
     */
    public String convertClob(Clob clob){
        String result = "";
        if(clob != null) {
            try {
                Reader reader = clob.getCharacterStream();
                BufferedReader br = new BufferedReader(reader);
                String s = br.readLine();
                StringBuilder sb = new StringBuilder();
                while (s != null) {
                    sb.append(s);
                    s = br.readLine();
                }
                result = sb.toString();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Format blob by byte blob.
     *
     * @param buffer the buffer
     * @return the blob
     * @description 将buffer包装成可用的Blob格式的文本
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 01:09:44.
     */
    public Blob formatBlobByByte(byte[] buffer){
        Blob blob = null;
        if(buffer!=null) {
            try {
                blob = new SerialBlob(buffer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blob;
    }
    public String getRandomName5(){
        return   ""+(int)(10000+Math.random()*(99999-10000));
    }
    /**
     * Get os by byte output stream.
     *
     * @param buffer   the buffer
     * @param response the response
     * @return the output stream
     * @description 得到输出流通过传进的byte
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:12.
     */
    public OutputStream getOsByByte(byte[] buffer, HttpServletResponse response){
        try {
            os = response.getOutputStream();
            os.write(buffer);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return os;
    }

    /**
     * Get normal header byte [ ].
     *
     * @return the byte [ ]
     * @description 得到普通头像的byte[]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:47.
     */
    public byte[] getNormalHeader(HttpServletRequest request,String IMAGE_NAME){
        String path = request.getSession().getServletContext().getRealPath(NORMALIMAGE+IMAGE_NAME);
        byte[] buffer = null;
        try {
            fis = new FileInputStream(new File(path));
            buffer = new byte[fis.available()];
            fis.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return buffer;
    }

    /**
     * Upload map.
     *
     * @param file the file
     * @return the map
     * @description 文件上传的共用代码
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:38.
     */
    public Map<String,Object> upload(TempFile file){
        String oldFileName = file.getSubmittedFileName().replaceAll(" ","");
        String suffix = getFileSuffix(oldFileName);
        String fileName = getRandomName13()+suffix;
        String savePath = getSavePath(suffix);
        Integer fileSize = 0;
        checkSavePath(savePath);
        Date date = null;
        try {
            fis = new FileInputStream(file.getFile());
            fos = new FileOutputStream(new File(savePath+fileName));
            fileSize = copy(fis,fos);
            date = new Date();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return ResultUtil.getUploadResult(getAcceptPath(savePath+fileName),fileName,oldFileName,fileSize,date);
    }

    public void download(HttpServletResponse response,String duta,String fileName){
        String suffix = getFileSuffix(fileName);
        String realName = duta.substring(2);
        String path = getSavePath(suffix);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        try {
            response.setHeader("Content-Disposition", "attachment;fileName="+ new String((fileName).getBytes("gb2312"),"ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            fis = new FileInputStream(new File(path+realName+suffix));
            os = response.getOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int byteRead;
            while((byteRead = fis.read(buffer,0,BUFFER_SIZE))!=-1){
                os.write(buffer,0,byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFileByFileName(String fileName){
        if(StringUtil.checkString(fileName)) {
            String suffix = getFileSuffix(fileName);
            String savePath = getSavePath(suffix);
            File file = new File(savePath + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Delete file by duta integer.
     *
     * @param duta     流水编号
     * @param fileName 文件原本的名称
     * @description 通过duta和fileName删除文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 11:10:01.
     */
    public void deleteFileByDuta(String duta,String fileName){
        if(StringUtil.checkString(duta) && StringUtil.checkString(fileName)) {
            String suffix = getFileSuffix(fileName);
            String savePath = getSavePath(suffix);
            String realName = duta.substring(2);
            File file = new File(savePath+realName+suffix);
            if (file.exists()) {
                file.delete() ;
            }
        }
    }

    /**
     * Check save path.
     *
     * @param savePath the save path
     * @description 检查储存文件的地址是否存在，不存在便创建一个.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:05.
     */
    private static void checkSavePath(String savePath){
        File path = new File(savePath);
        if(!path.exists()){
            path.mkdirs();
        }
    }

    /**
     * Get save path string.
     *
     * @param suffix the suffix
     * @return the string
     * @description 根据后缀名获得储存地址
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:21.
     */
    private static String getSavePath(String suffix){
        if(".jpg".equals(suffix)){
            return IMAGE_SAVEPATH;
        }else if(".doc".equals(suffix) || ".xlsx".equals(suffix) || ".docx".equals(suffix) || ".xls".equals(suffix)){
            return FILE_SAVEPATH;
        }
        return BASIC_SAVEPATH;
    }

    private static String getAcceptPath(String path){
        return "http:"+path;
    }

    /**
     * Get file name string.
     *
     * @param fileName 文件的名称加尾椎
     * @return 文件的名称
     * @description 截取文件的名称.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 09:10:45.
     */
    public String getFileName(String fileName){
        int postion = fileName.indexOf(".");
        return fileName.substring(0,postion);
    }

    /**
     * Get file suffix string.
     *
     * @param fileName 文件名
     * @return the string
     * @description 获得文件的扩展名.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:20 03:10:18.
     */
    public String getFileSuffix(String fileName){
        int position = fileName.indexOf(".");
        return fileName.substring(position);
    }

    /**
     * Get file origion string.
     *
     * @param filePath 文件的地址
     * @return 文件的名称和尾缀
     * @description 获得文件的全称（名称和尾缀）.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:12 09:12:37.
     */
     public String getFileOrigion(String filePath){
        int postion = filePath.lastIndexOf("\\");
        return filePath.substring(postion+1);
    }

    /**
     * Get sy file name string.
     *
     * @return 系统毫秒数格式的文件名
     * @description 生成系统毫秒数格式的文件名-13位格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:04 02:09:00.
     */
    public Long getSyFileName(){
        return System.currentTimeMillis();
    }

    /**
     * Get random name string.
     *
     * @return the string
     * @description 获得一个8位随机数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:28.
     */
    public Integer getRandomName(){
        return (int)(10000000+Math.random()*(99999999-10000000));
    }

    /**
     * Get random name 13 string.
     *
     * @return the string
     * @description 获得13位的随机数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:24 02:10:15.
     */
    public String getRandomName13(){
        return getRandomName()+""+(int)(10000+Math.random()*(99999-10000));
    }

    /**
     * Get uuid name string.
     *
     * @return 37位随机数的文件名
     * @description 生成37位随机数的文件名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:04 02:09:34.
     */
     public String getUUIDName(){
        return UUID.randomUUID().toString()+"";
    }

    /**
     * Get date name string.
     *
     * @return 当前时间格式的文件名
     * @description 生成当前时间格式的文件名-14位
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:04 02:09:00.
     */
     public String getDateName(){
        return new SimpleDateFormat("yyyyMMddHHmmdd").format(new Date());
    }

    /**
     * Gen http resp header content disposition string.
     *
     * @param fnm 文件名
     * @param ua  user-agent
     * @return the string
     * @description 设置文件的respHeader,解决返回文件名乱码问题
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:06 09:12:31.
     */
     public String genHttpRespHeaderContentDisposition(String fnm, String ua) {
        try {
            // Safari 狗屎
            if (null != ua && ua.contains(" Safari/")) {
                String e_fnm = new String(fnm.getBytes("UTF-8"), "ISO8859-1");
                return "attachment; filename=\"" + e_fnm + "\"";
            }
            // 其他用标准
            else {
                String e_fnm = URLEncoder.encode(fnm, Encoding.UTF8);
                return "attachment; filename*=utf-8'zh_cn'" + e_fnm;
            }
        }
        catch (UnsupportedEncodingException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * Close.
     *
     * @description 关闭所有流
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:34.
     */
    private void close(){
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
