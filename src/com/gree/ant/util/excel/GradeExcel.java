package com.gree.ant.util.excel;

import com.gree.ant.util.DateUtil;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.HTMLUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase011VO;
import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.Tbuss003VO;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * The type Grade excel.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 绩效表
 * @title GradeExcel
 * @createTime 2017 :09:26 04:09:59.
 */
public class GradeExcel {

    /**
     * Export.
     *
     * @param cbase000VOS 用户集合
     * @param tbuss001Map 封装了的绩效集合
     * @param request     request请求
     * @param response    response
     * @throws SQLException   the sql exception
     * @throws IOException    the io exception
     * @throws WriteException the write exception
     * @description 分别打印小组的每个人的任务绩效
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:16 02:10:37.
     */
    public static void export(List<Cbase000VO> cbase000VOS,Map<String,Tbuss001VO> tbuss001Map, HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException,WriteException{
        response.setContentType("APPLICATION/OCTET-STREAM");
        String title = tbuss001Map.get(cbase000VOS.get(0).getUSID()).getDsca();
        ExcelUtil.setHeader(request,response,title);
        printExcel(title,tbuss001Map,cbase000VOS,response.getOutputStream());
        response.setStatus(200);
        response.flushBuffer();
    }

    /**
     * Export.
     *
     * @param tbuss001VO 组的绩效集合
     * @param request    the request
     * @param response   the response
     * @throws SQLException   the sql exception
     * @throws IOException    the io exception
     * @throws WriteException the write exception
     * @description 将小组当月的任务一起打印出来
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:16 02:10:40.
     */
    public static void export(Tbuss001VO tbuss001VO, HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException,WriteException {
        response.setContentType("APPLICATION/OCTET-STREAM");
        String title = tbuss001VO.getDsca();
        ExcelUtil.setHeader(request,response,title);
        printExcel(title,tbuss001VO,response.getOutputStream());
        response.setStatus(200);
        response.flushBuffer();
    }

    /**
     * Print excel.
     *
     * @param title         标题
     * @param tbuss001VOMap 被封装成String（用户ID）和其对应Tbuss001（绩效）的Map
     * @param users         用户集合
     * @param os            the os
     * @throws IOException    the io exception
     * @throws WriteException the write exception
     * @description 分别打印团队内每个人的任务内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:20 09:10:59.
     */
    private static void printExcel(String title,Map<String,Tbuss001VO> tbuss001VOMap,List<Cbase000VO> users,OutputStream os) throws IOException,WriteException{
        WritableWorkbook workBook = Workbook.createWorkbook(os);
        WritableSheet sheet = workBook.createSheet(title,0);
        WritableCellFormat format = ExcelUtil.getForamat("BOLD",20, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);

        WritableCell cell = new Label(0,1,title,format);
        sheet.addCell(cell);
        sheet.mergeCells(0,1,9,1);

        setRowView(sheet);


        Integer col = 2;
        for (Cbase000VO user:users) {
            Tbuss001VO tbuss001VO = tbuss001VOMap.get(user.getUSID());
            format = ExcelUtil.getForamat("BOLD", 11, Colour.BLACK);
            format.setAlignment(Alignment.CENTRE);
            format.setBorder(Border.ALL,BorderLineStyle.THIN);
            cell = new Label(0, col, "考评对象：" + user.getDSCA(), format);
            sheet.addCell(cell);
            sheet.mergeCells(0, col, 2, col);
            cell = new Label(3, col, "直接上级：吴志伟、彭才能", format);
            sheet.addCell(cell);
            sheet.mergeCells(3, col, 4, col);
            cell = new Label(5, col, "考评时间：" + tbuss001VO.getPdat(), format);
            sheet.addCell(cell);
            sheet.mergeCells(5, col, 6, col);
            cell = new Label(7, col, "提交日期：" + DateUtil.formatYMDDate(new Date()), format);
            sheet.addCell(cell);
            sheet.mergeCells(7, col, 8, col);
            cell = new Label(9, col, "评定日期：", format);
            sheet.addCell(cell);
            col = printTask(col,tbuss001VO.getTbuss003VOS(),tbuss001VO.getCbase011VOS(),sheet);
            col = col+3;
        }
        workBook.write();
        workBook.close();
        os.close();
    }

    /**
     * Print excel.
     *
     * @param title      标题
     * @param tbuss001VO 封装了任务集合、任务规则集合的绩效实体
     * @param os         the os
     * @throws IOException    the io exception
     * @throws WriteException the write exception
     * @description 打印所有任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:20 09:10:19.
     */
    private static void printExcel(String title,Tbuss001VO tbuss001VO, OutputStream os) throws IOException,WriteException {
        WritableWorkbook workBook = Workbook.createWorkbook(os);
        WritableSheet sheet = workBook.createSheet(title,0);
        WritableCellFormat format = ExcelUtil.getForamat("BOLD",20, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        WritableCell cell = new Label(0,1,title,format);
        sheet.addCell(cell);
        sheet.mergeCells(0,1,9,1);

        setRowView(sheet);

        Integer col = 2;
        format = ExcelUtil.getForamat("BOLD", 11, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        cell = new Label(0, col, "", format);
        sheet.addCell(cell);
        sheet.mergeCells(0, col, 2, col);
        cell = new Label(3, col, "直接上级：吴志伟、彭才能", format);
        sheet.addCell(cell);
        sheet.mergeCells(3, col, 4, col);
        cell = new Label(5, col, "考评时间：" + tbuss001VO.getPdat(), format);
        sheet.addCell(cell);
        sheet.mergeCells(5, col, 6, col);
        cell = new Label(7, col, "提交日期：" + DateUtil.formatYMDDate(new Date()), format);
        sheet.addCell(cell);
        sheet.mergeCells(7, col, 8, col);
        cell = new Label(9, col, "评定日期：", format);
        sheet.addCell(cell);
        printTask(col,tbuss001VO.getTbuss003VOS(),tbuss001VO.getCbase011VOS(),sheet);
        workBook.write();
        workBook.close();
        os.close();
    }

    /**
     * Print task integer.
     *
     * @param col         行号
     * @param tbuss003VOS 任务集合
     * @param cbase011VOS 任务规则集合
     * @param sheet       the sheet
     * @return the integer
     * @throws WriteException the write exception
     * @description 打印任务的具体内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:20 09:10:52.
     */
    private static Integer printTask(Integer col,List<Tbuss003VO> tbuss003VOS,List<Cbase011VO> cbase011VOS,WritableSheet sheet) throws WriteException{
        col++;
        WritableCellFormat format = ExcelUtil.getForamat("BOLD", 12, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBackground(Colour.GREY_25_PERCENT);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        WritableCell cell = new Label(0, col, "主要绩效", format);
        sheet.addCell(cell);
        sheet.mergeCells(0, col, 1, col);
        cell = new Label(2, col, "考评项目", format);
        sheet.addCell(cell);
        cell = new Label(3, col, "权重", format);
        sheet.addCell(cell);
        cell = new Label(4, col, "目标要求", format);
        sheet.addCell(cell);
        cell = new Label(5, col, "评分规则", format);
        sheet.addCell(cell);
        cell = new Label(6, col, "数据提供", format);
        sheet.addCell(cell);
        cell = new Label(7, col, "完成情况", format);
        sheet.addCell(cell);
        cell = new Label(8, col, "评价说明", format);
        sheet.addCell(cell);
        cell = new Label(9, col, "上级评分", format);
        sheet.addCell(cell);

        Integer count = 1;
        col ++;
        format = ExcelUtil.getForamat("NOBOLD", 10, Colour.BLACK);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);

        if(tbuss003VOS!=null) {
            for (Tbuss003VO tbuss003VO : tbuss003VOS) {
                Cbase011VO cbase011VO = tbuss003VO.getCbase011VO();
                cell = new Label(0, col, tbuss003VO.getPtyp() == null ? "" : tbuss003VO.getPtypnam(), format);
                sheet.addCell(cell);
                cell = new Label(1, col, count + "", format);
                sheet.addCell(cell);
                cell = new Label(2, col, tbuss003VO.getSynonam() == null ? "" : tbuss003VO.getSynonam(), format);
                sheet.addCell(cell);
                cell = new Label(3, col, cbase011VO.getPjjp() == null?"":cbase011VO.getPjjp(), format);
                sheet.addCell(cell);
                cell = new Label(4, col, tbuss003VO.getTitl() == null ? "" : tbuss003VO.getTitl(), format);
                sheet.addCell(cell);
                cell = new Label(5, col, tbuss003VO.getNote() == null ? "" : HTMLUtil.delHTMLTag(FileUtil.convertClob(tbuss003VO.getNote())), format);
                sheet.addCell(cell);
                cell = new Label(6, col, tbuss003VO.getKsid() == null ? "" : tbuss003VO.getKdnam(), format);
                sheet.addCell(cell);
                cell = new Label(7, col, tbuss003VO.getSta1() == null ? "" : (tbuss003VO.getSta1() == 11 ? "完成" : "未完成"), format);
                sheet.addCell(cell);
                cell = new Label(8, col, "", format);
                sheet.addCell(cell);
                cell = new Label(9, col, "", format);
                sheet.addCell(cell);
                count++;
                col++;
            }
        }


        format = ExcelUtil.getForamat("BOLD", 12, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBackground(Colour.GREY_25_PERCENT);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        cell = new Label(0, col, "基础绩效", format);
        sheet.addCell(cell);
        sheet.mergeCells(0, col, 1, col);
        cell = new Label(2, col, "考评项目", format);
        sheet.addCell(cell);
        cell = new Label(3, col, "工作要求/优差标准", format);
        sheet.addCell(cell);
        sheet.mergeCells(3,col,5,col);
        cell = new Label(6, col, "数据提供", format);
        sheet.addCell(cell);
        cell = new Label(7, col, "加、扣分标准", format);
        sheet.addCell(cell);
        sheet.mergeCells(7,col,8,col);
        cell = new Label(9, col, "上级评分", format);
        sheet.addCell(cell);

        format = ExcelUtil.getForamat("NOBOLD", 10, Colour.BLACK);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        List<Cbase011VO> cbase011VOList = new ArrayList<>();
        if(cbase011VOS!=null) {
            count = 1;
            col++;
            cbase011VOList.addAll(cbase011VOS);
        }
        for (Cbase011VO cbase011VO:cbase011VOList){
            cell = new Label(0,col,cbase011VO.getDsca() == null?"":cbase011VO.getDsca(),format);
            sheet.addCell(cell);
            cell = new Label(1,col,""+count,format);
            sheet.addCell(cell);
            cell = new Label(2,col,cbase011VO.getDeti() == null?"":cbase011VO.getDeti(),format);
            sheet.addCell(cell);
            cell = new Label(3,col,"",format);
            sheet.addCell(cell);
            sheet.mergeCells(3,col,5,col);
            cell = new Label(6,col,"",format);
            sheet.addCell(cell);
            cell = new Label(7,col,"",format);
            sheet.addCell(cell);
            sheet.mergeCells(7,col,8,col);
            cell = new Label(9,col,"",format);
            sheet.addCell(cell);
            count++;
            col++;
        }
        return col;
    }

    /**
     * Set row view.
     *
     * @param sheet the sheet
     * @description 设置列宽，i->列的列号
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:20 09:10:57.
     */
    private static void setRowView(WritableSheet sheet){
        CellView cellView = new CellView();
        for(int j = 0;j < 10;j++){
            if(j == 1 || j == 3){
                cellView.setSize(2000);
            }else if(j == 0 || j == 6 || j == 7){
                cellView.setSize(5000);
            }else{
                cellView.setSize(10000);
            }
            sheet.setColumnView(j, cellView);
        }
    }
}
