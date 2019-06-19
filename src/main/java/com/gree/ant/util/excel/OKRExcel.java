package com.gree.ant.util.excel;

import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;
import com.gree.ant.vo.Tbuss013VO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR管理表生成
 * @createTime 2018:09:04 02:09:59.
 */
public class OKRExcel {

    public static void export(Tbuss011VO tbuss011VO, HttpServletRequest request, HttpServletResponse response) throws IOException, WriteException {
        if(tbuss011VO == null){
            tbuss011VO = new Tbuss011VO();
        }
        response.setContentType("APPLICATION/OCTET-STREAM");
        String title = (tbuss011VO.getANAM() == null ? "" : tbuss011VO.getANAM())+"OKR管理表";
        ExcelUtil.setHeader(request,response,title);
        printExcel(title,tbuss011VO,response.getOutputStream());
        response.setStatus(200);
        response.flushBuffer();
    }

    private static void printExcel(String title, Tbuss011VO tbuss011VO, OutputStream os) throws IOException,WriteException{

        WritableWorkbook workBook = Workbook.createWorkbook(os);
        WritableSheet sheet = workBook.createSheet(title,0);
        WritableCellFormat format = ExcelUtil.getForamat("BOLD",20, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);

        WritableCell cell = new Label(0,1,title,format);
        sheet.addCell(cell);
        sheet.mergeCells(0,1,10,1);

        format = ExcelUtil.getForamat("BOLD", 11, Colour.BLACK);
        format.setAlignment(Alignment.CENTRE);
        format.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
        cell = new Label(0,2,"管理对象："+ (tbuss011VO.getANAM() == null ? "" : tbuss011VO.getANAM()),format);
        sheet.addCell(cell);
        sheet.mergeCells(0,2,5,2);
        cell = new Label(6,2,"直接上级："+ (tbuss011VO.getBNAM() == null ? "" : tbuss011VO.getBNAM()),format);
        sheet.addCell(cell);
        sheet.mergeCells(6,2,7,2);
        cell = new Label(8,2,"管理周期："+ (tbuss011VO.getMDAT() == null ? "" : tbuss011VO.getMDAT()),format);
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
        cell = new Label(2, col, "O周期", format);
        sheet.addCell(cell);
        cell = new Label(3, col, "O类型", format);
        sheet.addCell(cell);
        cell = new Label(4, col, "O权重", format);
        sheet.addCell(cell);
        cell = new Label(5, col, "O完成情况", format);
        sheet.addCell(cell);
        cell = new Label(6, col, "关键成果（KRS）", format);
        sheet.addCell(cell);
        cell = new Label(7, col, "KR权重", format);
        sheet.addCell(cell);
        cell = new Label(8, col, "KRS完成情况", format);
        sheet.addCell(cell);
        cell = new Label(9, col, "自评分", format);
        sheet.addCell(cell);
        cell = new Label(10, col, "上级评分", format);
        sheet.addCell(cell);

        int count = 1;
        col ++;
        format = ExcelUtil.getForamat("NOBOLD", 10, Colour.BLACK);
        format.setBorder(Border.ALL,BorderLineStyle.THIN);
        List<Tbuss012VO> tbuss012VOS = tbuss011VO.getTbuss012VOS();
        if(tbuss012VOS != null) {
            for (Tbuss012VO tbuss012VO : tbuss012VOS) {
                int t13Count = 0;
                List<Tbuss013VO> tbuss013VOList;
                tbuss013VOList = tbuss012VO.getTbuss013VOS();
                if (tbuss013VOList != null){
                    t13Count = tbuss013VOList.size();
                    t13Count--;
                }else{
                    tbuss013VOList = new ArrayList<>();
                }
                cell = new Label(0, col, count + "", format);
                sheet.addCell(cell);
                sheet.mergeCells(0,col,0,col+t13Count);
                cell = new Label(1, col, tbuss012VO.getGoal() != null ? tbuss012VO.getGoal():"", format);
                sheet.addCell(cell);
                sheet.mergeCells(1,col,1,col+t13Count);
                cell = new Label(2, col,tbuss012VO.getNdat() == null ? "" : (tbuss012VO.getNdat().equals("4") ? "年度":(tbuss012VO.getNdat().equals("3") ?  "半年度":(tbuss012VO.getNdat().equals("2") ? "季度" : (tbuss012VO.getNdat().equals("1") ? "月度" : "")))), format);
                sheet.addCell(cell);
                sheet.mergeCells(2,col,2,col+t13Count);
                cell = new Label(3, col, tbuss012VO.getType() == null ? "": (tbuss012VO.getType() == 4 ? "创新类":(tbuss012VO.getType() == 3 ?  "管理类":(tbuss012VO.getType() == 2 ? "质量类" : (tbuss012VO.getType() == 1 ? "项目类" : "")))), format);
                sheet.addCell(cell);
                sheet.mergeCells(3,col,3,col+t13Count);
                cell = new Label(4, col, tbuss012VO.getProp() != null ? (tbuss012VO.getProp()+"%"):"", format);
                sheet.addCell(cell);
                sheet.mergeCells(4,col,4,col+t13Count);
                cell = new Label(5, col, tbuss012VO.getPerf() !=null ? tbuss012VO.getPerf():"", format);
                sheet.addCell(cell);
                sheet.mergeCells(5,col,5,col+t13Count);
                for (Tbuss013VO tbuss013VO:tbuss013VOList) {
                    cell = new Label(6, col, tbuss013VO.getAchi() != null ? tbuss013VO.getAchi() : "", format);
                    sheet.addCell(cell);
                    cell = new Label(7, col, tbuss013VO.getKrprop() != null ? (tbuss013VO.getKrprop() + "%") : "", format);
                    sheet.addCell(cell);
                    cell = new Label(8, col, tbuss013VO.getKrperf() != null ? tbuss013VO.getKrperf() : "", format);
                    sheet.addCell(cell);
                    cell = new Label(9, col, tbuss013VO.getZgrad() != null ? (tbuss013VO.getZgrad() + "") : "", format);
                    sheet.addCell(cell);
                    cell = new Label(10, col,  tbuss013VO.getMgrad() != null ? (tbuss013VO.getMgrad() + "") : "", format);
                    sheet.addCell(cell);
                    col++;
                }
                count++;
            }
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
