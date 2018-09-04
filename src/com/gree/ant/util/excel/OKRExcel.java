package com.gree.ant.util.excel;

import com.gree.ant.vo.Tbuss011VO;
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
import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理表生成
 * @createTime 2018:09:04 02:09:59.
 */
public class OKRExcel {

    public static void export(List<Tbuss011VO> tbuss011VOS, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, WriteException {
        response.setContentType("APPLICATION/OCTET-STREAM");
        String title = tbuss011VOS.get(0).getANAM()+"OKR管理表";
        ExcelUtil.setHeader(request,response,title);
        printExcel(title,tbuss011VOS,response.getOutputStream());
        response.setStatus(200);
        response.flushBuffer();
    }

    private static void printExcel(String title, List<Tbuss011VO> tbuss011VOS, OutputStream os) throws IOException,WriteException{

        WritableWorkbook workBook = Workbook.createWorkbook(os);
        WritableSheet sheet = workBook.createSheet(title,0);
        WritableCellFormat format = ExcelUtil.getForamat("BOLD",20, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);

        WritableCell cell = new Label(0,1,title,format);
        sheet.addCell(cell);
        sheet.mergeCells(0,1,10,1);

        Tbuss011VO t11 = tbuss011VOS.get(0);
        format = ExcelUtil.getForamat("BOLD", 11, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
        cell = new Label(0,2,"管理对象："+t11.getANAM(),format);
        sheet.addCell(cell);
        sheet.mergeCells(0,2,5,2);
        cell = new Label(6,2,"直接上级："+t11.getBNAM(),format);
        sheet.addCell(cell);
        sheet.mergeCells(6,2,7,2);
        cell = new Label(6,2,"管理周期："+t11.getMDAT(),format);
        sheet.addCell(cell);
        sheet.mergeCells(8,2,10,2);

        setRowView(sheet);
        int col = 3;

        format = ExcelUtil.getForamat("BOLD", 12, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBackground(Colour.GREY_25_PERCENT);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        cell = new Label(0, col, "序号", format);
        sheet.addCell(cell);
        cell = new Label(1, col, "目标(O)", format);
        sheet.addCell(cell);
        cell = new Label(2, col, "周期", format);
        sheet.addCell(cell);
        cell = new Label(3, col, "类型", format);
        sheet.addCell(cell);
        cell = new Label(4, col, "权重", format);
        sheet.addCell(cell);
        cell = new Label(5, col, "完成情况", format);
        sheet.addCell(cell);
        cell = new Label(6, col, "关键成果（KRS）", format);
        sheet.addCell(cell);
        cell = new Label(7, col, "KR权重", format);
        sheet.addCell(cell);
        cell = new Label(8, col, "KR完成情况", format);
        sheet.addCell(cell);
        cell = new Label(9, col, "自评分", format);
        sheet.addCell(cell);
        cell = new Label(10, col, "上级评分", format);
        sheet.addCell(cell);

        int count = 1;
        col ++;
        format = ExcelUtil.getForamat("NOBOLD", 10, Colour.BLACK);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);

        for(Tbuss011VO tbuss011VO:tbuss011VOS){
            cell = new Label(0, col, count+"", format);
            sheet.addCell(cell);
            cell = new Label(1, col, tbuss011VO.getGOAL() != null ? tbuss011VO.getGOAL():"", format);
            sheet.addCell(cell);
            cell = new Label(2, col,tbuss011VO.getNNDAT() != null ? tbuss011VO.getNNDAT():"", format);
            sheet.addCell(cell);
            cell = new Label(3, col, tbuss011VO.getNTYPE() != null ? tbuss011VO.getNTYPE():"", format);
            sheet.addCell(cell);
            cell = new Label(4, col, tbuss011VO.getPROP() != null ? (tbuss011VO.getPROP()+"%"):"", format);
            sheet.addCell(cell);
            cell = new Label(5, col, tbuss011VO.getPERF() !=null ? tbuss011VO.getPERF():"", format);
            sheet.addCell(cell);
            cell = new Label(6, col, tbuss011VO.getACHI() != null ? tbuss011VO.getACHI():"", format);
            sheet.addCell(cell);
            cell = new Label(7, col, tbuss011VO.getKRPROP() != null ? (tbuss011VO.getPROP()+"%"):"", format);
            sheet.addCell(cell);
            cell = new Label(8, col, tbuss011VO.getKRPERF() != null ? tbuss011VO.getKRPERF():"", format);
            sheet.addCell(cell);
            cell = new Label(9, col, tbuss011VO.getZGRAD() != null ? (tbuss011VO.getZGRAD()+""):"", format);
            sheet.addCell(cell);
            cell = new Label(10, col, "", format);
            sheet.addCell(cell);
            col++;
            count++;
        }
        workBook.write();
        workBook.close();
        os.close();
    }

    private static void setRowView(WritableSheet sheet){
        CellView cellView = new CellView();
        for(int j = 0;j < 11;j++){
            if(j == 0 || j==2 || j == 3 || j==4 || j == 7 || j == 9 || j == 10){
                cellView.setSize(2000);
            }else{
                cellView.setSize(10000);
            }
            sheet.setColumnView(j, cellView);
        }
    }


}
