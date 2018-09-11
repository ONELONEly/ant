package com.gree.ant.dao;

import com.gree.ant.vo.Cbase000VO;
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
 * @title Cbase000DAO
 * @createTime 2017 :08:30 05:08:42.
 */
public interface Cbase000DAO {

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
     * @return the list
     * @description 通过月份查询所有人的成绩排名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:01.
     */
    List<GradeVO> queryAllGradeByPdat(Condition cnd,Condition condition);

    /**
     * Query all user list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过Cnd,Pager获得表格的用户.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:31 08:10:20.
     */
    List<Cbase000VO> queryAllUser(Condition cnd, Pager pager);
}
