package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss013VO;

import java.util.List;
import java.util.Map;

public interface Tbuss013BasicMO {

    List<Tbuss013VO> insertTask(List<Tbuss013VO> tbuss013VOS);

    Integer markTask(List<Map<String,Object>> scores);
}
