package com.gree.ant.mo.basic;

import com.gree.ant.vo.TBuss017VO;
import com.gree.ant.vo.util.ExportGradeOkrVO;

import java.util.List;

public interface TBuss017BasicMO {

    Integer countByPdatAcco(String pdat,String acco);
    List<ExportGradeOkrVO> queryAllByPdatAcco(String pdat, String acco);
    TBuss017VO fetchByUsidPdat(String usid,String pdat);
}
