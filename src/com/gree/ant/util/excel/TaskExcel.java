package com.gree.ant.util.excel;

import com.gree.ant.util.FileUtil;
import com.gree.ant.util.HTMLUtil;
import com.gree.ant.vo.Tbuss003VO;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * The type Task excel.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务表
 * @title TaskExcel
 * @createTime 2017 :09:26 03:09:05.
 */
public class TaskExcel {


    public static void export(List<Tbuss003VO> tbuss003VOS,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,WriteException {
        response.setContentType("APPLICATION/OCTET-STREAM");
        ExcelUtil.setHeader(request,response,"任务表");
        if(tbuss003VOS!=null) {
            printExcel(tbuss003VOS, response.getOutputStream());
        }
        response.setStatus(200);
        response.flushBuffer();
    }

    private static void printExcel(List<Tbuss003VO> tbuss003VOS, OutputStream os) throws IOException,WriteException {

        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet  = workbook.createSheet("任务表",0);

        WritableCell cell;
        WritableCellFormat format = ExcelUtil.getForamat("BOLD",14,Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);

        cell = new Label(0,1,"任务表",format);
        sheet.addCell(cell);
        sheet.mergeCells(0,1,7,1);

        setRowView(sheet);

        format = ExcelUtil.getForamat("BOLD",10,Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBackground(Colour.GREY_25_PERCENT);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        cell = new Label(0,2,"标题",format);
        sheet.addCell(cell);
        cell = new Label(1,2,"描述",format);
        sheet.addCell(cell);
        cell = new Label(2,2,"任务类型",format);
        sheet.addCell(cell);
        cell = new Label(3,2,"优先级",format);
        sheet.addCell(cell);
        cell = new Label(4,2,"任务状态",format);
        sheet.addCell(cell);
        cell = new Label(5,2,"软件科室",format);
        sheet.addCell(cell);
        cell = new Label(6,2,"所属项目",format);
        sheet.addCell(cell);
        cell = new Label(7,2,"派发给",format);
        sheet.addCell(cell);

        Integer cut = 3;
        format = ExcelUtil.getForamat("NOBOLD",9,Colour.BLACK);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        for(Tbuss003VO tbuss003VO:tbuss003VOS){
            cell = new Label(0,cut,tbuss003VO.getTitl() == null?"":tbuss003VO.getTitl(),format);
            sheet.addCell(cell);
            cell = new Label(1,cut,tbuss003VO.getNote() == null?"": HTMLUtil.delHTMLTag(FileUtil.convertClob(tbuss003VO.getNote())),format);
            sheet.addCell(cell);
            cell = new Label(2,cut,tbuss003VO.getPtnonam() == null?"":tbuss003VO.getPunonam(),format);
            sheet.addCell(cell);
            cell = new Label(3,cut,tbuss003VO.getSta3nam() == null?"":tbuss003VO.getSta3nam(),format);
            sheet.addCell(cell);
            cell = new Label(4,cut,"1-新研发任务",format);
            sheet.addCell(cell);
            cell = new Label(5,cut,tbuss003VO.getAcconam() == null?"":tbuss003VO.getAcconam(),format);
            sheet.addCell(cell);
            cell = new Label(6,cut,tbuss003VO.getPtnonam() == null?"":tbuss003VO.getSynonam(),format);
            sheet.addCell(cell);
            cell = new Label(7,cut,tbuss003VO.getCnam() == null?"":tbuss003VO.getCnam()+" "+tbuss003VO.getCsid(),format);
            sheet.addCell(cell);
            cut++;
        }
        workbook.write();
        workbook.close();
        os.close();
    }

    private static void setRowView(WritableSheet sheet){
        CellView cellView = new CellView();
        for(int j = 0;j < 8;j++){
            if(j == 1){
                cellView.setSize(15000);
            }else if(j == 3){
                cellView.setSize(2000);
            }else if(j == 0 || j == 6){
                cellView.setSize(10000);
            } else{
                cellView.setSize(5000);
            }
            sheet.setColumnView(j, cellView);
        }
    }
}
