package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase011DAOImp;
import com.gree.ant.mo.basic.Cbase011BasicMO;
import com.gree.ant.vo.Cbase010VO;
import com.gree.ant.vo.Cbase011VO;
import com.gree.ant.vo.Cbase012VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase011MO implements Cbase011BasicMO{

    @Inject("refer:cbase011DAOImp")
    private Cbase011DAOImp cbase011DAOImp;

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Override
    public List<Cbase011VO> queryAllByCnd(Condition cnd, Pager pager){
        return formatC11(baseDAOImp.queryByCndPager(new Cbase011VO(),cnd,pager));
    }

    @Override
    public Cbase011VO fetchByPjno(String pjno){
        return (Cbase011VO)baseDAOImp.fetchByName(new Cbase011VO(),pjno);
    }

    @Override
    public Cbase011VO fetchTransByPjno(String pjno) {
        return (Cbase011VO) baseDAOImp.fetchTransByNameCnd(new Cbase011VO(),pjno,"cbase012VOS",null);
    }

    @Override
    public Cbase011VO fetchTransByVO(Cbase011VO vo,String primary,Condition cnd) {
        return (Cbase011VO) baseDAOImp.fetchLinks(vo,primary,cnd);
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
        return baseDAOImp.countByCnd(new Cbase011VO(),cnd);
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
        return (Cbase011VO)baseDAOImp.insert(cbase011VO);
    }

    @Override
    public Integer updateByVO(Cbase011VO cbase011VO) {
        return baseDAOImp.update(cbase011VO);
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
        return baseDAOImp.deleteWith(fetchByPjno(pjno),"cbase012VOS");
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
        return baseDAOImp.delete(cbase011VO);
    }

    private List<Cbase011VO> formatC11(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase011VO> cbase011VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase011VOS.add((Cbase011VO) iterator.next());
        }
        return  cbase011VOS;
    }
}
