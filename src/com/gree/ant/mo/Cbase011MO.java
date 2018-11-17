package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase011DAOImp;
import com.gree.ant.mo.basic.Cbase011BasicMO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Cbase011VO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class Cbase011MO implements Cbase011BasicMO{

    @Inject("refer:cbase011DAOImp")
    private Cbase011DAOImp cbase011DAOImp;

    @Override
    public List<Cbase011VO> queryAllByCnd(Condition cnd, Pager pager){
        return cbase011DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Cbase011VO fetchByPjno(String pjno){
        return cbase011DAOImp.fetchByName(pjno);
    }

    @Override
    public Cbase011VO fetchTransByPjno(String pjno) {
        return cbase011DAOImp.fetchTransByNameCnd(pjno,"cbase012VOS",null);
    }

    @Override
    public Cbase011VO fetchTransByVO(Cbase011VO vo,String primary,Condition cnd) {
        return cbase011DAOImp.fetchLinks(vo,primary,cnd);
    }

    @Override
    public List<Cbase011VO> queryAllTrans(Condition cnd, Pager pager){
        List<Cbase011VO> cbase011VOS = queryAllByCnd(cnd,pager);
        List<Cbase011VO> cbase011VOList = new ArrayList<>();
        for (Cbase011VO cbase011VO:cbase011VOS){
            cbase011VO = fetchTransByVO(cbase011VO,"cbase012VOS",null);
            cbase011VOList.add(cbase011VO);
        }
        return cbase011VOList;
    }

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤字段
     * @return the integer
     * @description 通过Cnd进行计数
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:49.
     */
    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase011DAOImp.countByCnd(cnd);
    }

    /**
     * Insert cbase 011 vo.
     *
     * @param cbase011VO the cbase 011 vo
     * @return the cbase 011 vo
     * @description 通过实体插入单条绩效评估项
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:26.
     */
    @Override
    public Cbase011VO insert(Cbase011VO cbase011VO) {
        return cbase011DAOImp.insert(cbase011VO);
    }

    @Override
    public Integer updateByVO(Cbase011VO cbase011VO) {
        return cbase011DAOImp.update(cbase011VO);
    }

    /**
     * Delete by pjno integer.
     *
     * @param pjno the pjno
     * @return the integer
     * @description 通过规则编号删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:08.
     */
    @Override
    public Integer deleteByPjno(String pjno) {
        return cbase011DAOImp.deleteWith(fetchByPjno(pjno),"cbase012VOS");
    }

    /**
     * Delete by vo integer.
     *
     * @param cbase011VO the cbase 011 vo
     * @return the integer
     * @description 通过规则实体删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 05:09:08.
     */
    @Override
    public Integer deleteByVO(Cbase011VO cbase011VO) {
        return cbase011DAOImp.delete(cbase011VO);
    }

    @Override
    public Integer copyByPjno(String pjno,String usid) {
        int code = 0;
        Cbase011VO cbase011VO = fetchTransByPjno(pjno);
        cbase011VO.setUsid(usid);
        cbase011VO.setPjno("P"+ FileUtil.getRandomName());
        cbase011VO = cbase011DAOImp.insertWith(cbase011VO,"cbase012VOS");
        if (cbase011VO != null){
            code = 1;
        }
        return code;
    }
}
