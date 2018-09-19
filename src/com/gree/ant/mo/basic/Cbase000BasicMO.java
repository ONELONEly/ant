package com.gree.ant.mo.basic;

import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.util.GradeVO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The interface Cbase 000 basic mo.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用户实体逻辑操作的MO
 * @title Cbase000BasicMO
 * @createTime 2017 :09:06 06:09:27.
 */
public interface Cbase000BasicMO {

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有用户实体通过Condition
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    List<Cbase000VO> queryAllByCnd(Condition cnd, Pager pager);

    /**
     * Login check boolean.
     *
     * @param usid 用户名
     * @param pawd 密码
     * @return the boolean
     * @description 登陆校验
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    Boolean loginCheck(String usid,String pawd);

    /**
     * Insert cbase 000 vo.
     *
     * @param cbase000VO the cbase 000 vo
     * @return the cbase 000 vo
     * @description 通过用户实体插入单条用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    Cbase000VO insert(Cbase000VO cbase000VO);

    /**
     * Fetch by usid cbase 000 vo.
     *
     * @param usid the usid
     * @return the cbase 000 vo
     * @description 通过用户编号查询单条用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    Cbase000VO fetchByUsid(String usid);

    /**
     * Fetch tran by usid pri cbase 000 vo.
     *
     * @param usid    用户ID
     * @param primary 关联字段
     * @param cnd     过滤字段
     * @return 包含关联实体的用户VO
     * @description 通过usid，primary,cnd查询一条用户记录和他对应的关联实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:13 08:10:41.
     */
    Cbase000VO fetchTranByUsidPRI(String usid,String primary,Condition cnd);

    /**
     * Update by vo integer.
     *
     * @param cbase000VO the cbase 000 vo
     * @return the integer
     * @description 通过用户实体修改用户信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    Integer updateByVO(Cbase000VO cbase000VO);

    /**
     * Count by cnd integer.
     *
     * @param cnd the cnd
     * @return the integer
     * @description 通过Cnd查询共有多少条用户记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    Integer countByCnd(Condition cnd);

    /**
     * Delete by usid integer.
     *
     * @param usid the usid
     * @return the integer
     * @description 通过用户编号删除单条用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 06:09:27.
     */
    Integer deleteByUsid(String usid);

    /**
     * Delete by vo integer.
     *
     * @param cbase000VO the cbase 000 vo
     * @return the integer
     * @description 通过用户实体删除用户
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:08 09:09:07.
     */
    Integer deleteByVO(Cbase000VO cbase000VO);

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
     * @param pdat 月份
     * @return the list
     * @description 通过月份查询所有人的成绩排名
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:27 04:10:01.
     */
    List<GradeVO> queryAllGradeByPdat(String pdat,String grop);

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

    Cbase000VO fetchUser(String usid);
}
