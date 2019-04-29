package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase012DAOImp;
import com.gree.ant.mo.basic.Cbase012BasicMO;
import com.gree.ant.vo.Cbase012VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase012MO implements Cbase012BasicMO{

    @Inject("refer:cbase012DAOImp")
    private Cbase012DAOImp cbase012DAOImp;

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有评分项通过Cnd, Pager
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:15.
     */
    @Override
    public List<Cbase012VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase012DAOImp.queryByCndPager(cnd,pager);
    }

    /**
     * Insert cbase 012 vo.
     *
     * @param cbase012VO the cbase 012 vo
     * @return the cbase 012 vo
     * @description 通过实体插入单条评分项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:29.
     */
    @Override
    public Cbase012VO insert(Cbase012VO cbase012VO) {
        return cbase012DAOImp.insert(cbase012VO);
    }

    /**
     * Delete by vo integer.
     *
     * @param cbase012VO the cbase 012 vo
     * @return the integer
     * @description 通过实体删除单条评分项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:29.
     */
    @Override
    public Integer deleteByVO(Cbase012VO cbase012VO) {
        Condition cnd = Cnd.where("pjno","=",cbase012VO.getPjno()).and("opco","=",cbase012VO.getOpco());
        return cbase012DAOImp.clearByCnd(cnd);
    }

    /**
     * Insert check boolean.
     *
     * @param pjno 评分项
     * @param opco 分值
     * @return the boolean
     * @description 检查分值是否存在,true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 05:09:44.
     */
    @Override
    public Boolean insertCheck(String pjno, String opco) {
        return cbase012DAOImp.queryByCndPager(Cnd.where("pjno","=",pjno).and("opco","=",opco),null).size() == 0;
    }
}
