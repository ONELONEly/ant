package com.gree.ant.util.excel;

import com.gree.ant.util.DateUtil;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nutz.mvc.upload.TempFile;

import java.io.IOException;
import java.io.InputStream;

public class ReadGradeExcel {

    private static Sheet sheet;
    private static Workbook workbook;
    private static Boolean key = true;

    public static void readGrade(TempFile file) throws IOException{
        String suffix = FileUtil.getFileSuffix(file.getSubmittedFileName());
        InputStream is = file.getInputStream();

        if(suffix.equals(".xlsx")) {
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
        }else if(suffix.equals(".xls")){
            workbook = new HSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
        }
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                for (Cell cell : row) {
                    if(key) {
                        if(cell.getColumnIndex() == 0) {
                            if (!StringUtil.checkString(checkType(cell))) {
                                key = false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sheet.getFirstRowNum()+"============================="+sheet.getLastRowNum());
        is.close();
    }

    private static String checkType(Cell cell){
        String res = "";
        switch (cell.getCellTypeEnum()){
            case NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    Double dos = cell.getNumericCellValue();
                    res = dos.intValue() +"";
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString()) ||
                        "mm/dd/yy".equals(cell.getCellStyle().getDataFormatString()) ||
                        "yyyy/m/d".equals(cell.getCellStyle().getDataFormatString())){
                    res = DateUtil.formatYMDDate(cell.getDateCellValue());
                }else{
                    Double dos = cell.getNumericCellValue();
                    res = dos.intValue() +"";
                }
                break;
            case STRING:
                res = cell.getStringCellValue();
                break;
            case BOOLEAN:
                res = cell.getBooleanCellValue()+"";
                break;
            case FORMULA:
                res = cell.toString();
                break;
            case ERROR:
                res = cell.toString();
                break;
            case BLANK:
                res ="";
                break;
            case _NONE:
                res = "";
                break;
        }

        return res;
    }
}
