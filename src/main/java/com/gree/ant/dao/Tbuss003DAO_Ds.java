package com.gree.ant.dao;

import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Tbuss003VO;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The interface Tbuss 003 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 任务表的数据层操作
 * @title Tbuss003DAO
 * @createTime 2017 :10:21 05:10:14.
 */
public interface Tbuss003DAO_Ds {

   int insertRuleCustomerFieldTrackExt(Tbuss003VO tbuss003VO, int BugID, Cbase000VO cbase000VO);
   int inserRuleBug(Tbuss003VO tbuss003VO, int BugID, int PersonID, String note_ds, String CrntVersionID)throws SQLException;
   Integer deleterBug(Integer BugID);
   Integer deleteCustomer(Integer BugID);
   String StringChange(Clob note)throws Exception;
   int findPersonIDByLogin(String usid, String unam);
   int selectMAX();
   Map selctBug(String bugID);
   List<Cbase013VO> findAllSystemByDs();
   void delerefromCbase013();


}
