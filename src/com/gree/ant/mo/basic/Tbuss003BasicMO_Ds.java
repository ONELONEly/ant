package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;

import java.util.List;
import java.util.Map;

/**
 * The interface Tbuss 003 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务单表实体逻辑操作的MO todo 直接操作DS的关键接口
 * @title Tbuss003BasicMO
 * @createTime 2017 :09:06 06:09:54.
 */
public interface Tbuss003BasicMO_Ds {
     List<Map> findT3DS_jied(String syno);
     Integer insertBug (Tbuss003VO tbuss003VO, Cbase000VO cbase000VO)throws Exception;
     Integer deleteBug(Integer BugID);
     String insertBugJieKou (Tbuss003VO tbuss003VO, Cbase000VO cbase000VO)throws Exception;
     List<Cbase013VO> synchronizationDSSystem();
}
