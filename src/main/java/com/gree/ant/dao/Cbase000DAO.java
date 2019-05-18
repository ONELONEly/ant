package com.gree.ant.dao;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.util.ExportGradeOkrVO;
import com.gree.ant.vo.util.GradeVO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 000 dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用户表的操作
 * @createTime 2017 :08:30 05:08:42.
 * @title Cbase000DAO
 */
public interface Cbase000DAO extends BaseDAO<Cbase000VO> {


    /**
     * Login check boolean.
     *
     * @param usid the 用户ID
     * @param pawd the 用户密码
     * @return the boolean
     * @description 登陆校验
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :08:30 05:08:35.
     */
    Boolean loginCheck(String usid,String pawd);

    /**
     * Query all usiddsca list.
     *
     * @return the list
     * @description 查询所有的用户ID和描述
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:26 04:10:40.
     */
    List<ResultVO> queryAllUD();

    /**
     * Query all grade by pdat list.
     *
     * @param cnd
     * @param condition
     * @return the list
     * @description 通过月份查询所有人的成绩排名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:01.
     */
    List<GradeVO> queryAllGradeByPdat(Condition cnd,Condition condition,String officeNumber);


    /**
     * @param cnd
     * @return TODO
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 15:47:57
     */
    List<ExportGradeOkrVO> queryALlGradeOkrByPdat(Condition cnd,String officeNumber);

    /**
     * Query all user list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过Cnd, Pager获得表格的用户.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 08:10:20.
     */
    List<Cbase000VO> queryAllUser(Condition cnd, Pager pager);

    /**
     * @param usid
     * @return TODO
     * @description 用于存储的全局用户信息
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 11:15:34
     */
    Cbase000VO findUser(String usid);

    /**
     * @param usid
     * @return 用户实体
     * @description 查询单条用户的邮箱号和名称
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 11:15:34
     */
    Cbase000VO findUserDC(String usid);

    /**
     * @param grop 团队编号
     * @param cnd 过滤信息
     * @return 用户编号和描述的集合
     * @description 通过团队编号获得该团队的集合
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -01-09 15:59:59
     */
    List<Cbase000VO> queryAllUDByGropCnd(String grop,Condition cnd);
}
