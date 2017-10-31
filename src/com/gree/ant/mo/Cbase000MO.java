package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Cbase000DAOImp;
import com.gree.ant.mo.basic.Cbase000BasicMO;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.ValueObject;
import com.gree.ant.vo.util.GradeVO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase000MO implements Cbase000BasicMO{

    @Inject("cbase000DAOImp")
    private Cbase000DAOImp cbase000DAOImp;

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;


    @Override
    public List<Cbase000VO> queryAllByCnd(Condition cnd, Pager pager){
        return formatC0(baseDAOImp.queryByCndPager(new Cbase000VO(),cnd,pager));
    }

    @Override
    public Boolean loginCheck(String usid,String pawd){
        return cbase000DAOImp.loginCheck(usid,pawd);
    }

    @Override
    public Cbase000VO insert(Cbase000VO cbase000VO) {
        return (Cbase000VO) baseDAOImp.insert(cbase000VO);
    }

    @Override
    public Cbase000VO fetchByUsid(String usid) {
        return (Cbase000VO)baseDAOImp.fetchByName(new Cbase000VO(),usid);
    }

    @Override
    public Cbase000VO fetchTranByUsidPRI(String usid, String primary, Condition cnd) {
        return (Cbase000VO)baseDAOImp.fetchTransByNameCnd(new Cbase000VO(),usid,primary,cnd);
    }

    @Override
    public Integer updateByVO(Cbase000VO cbase000VO) {
        return baseDAOImp.update(cbase000VO);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Cbase000VO(),cnd);
    }

    @Override
    public Integer deleteByUsid(String usid) {
        return baseDAOImp.deleteByName(new Cbase000VO(),usid);
    }

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
    @Override
    public Integer deleteByVO(Cbase000VO cbase000VO) {
        return baseDAOImp.delete(cbase000VO);
    }

    @Override
    public List<ResultVO> queryAllUD() {
        return cbase000DAOImp.queryAllUD();
    }

    @Override
    public List<GradeVO> queryAllGradeByPdat(String pdat) {
        return cbase000DAOImp.queryAllGradeByPdat(pdat);
    }

    @Override
    public List<Cbase000VO> queryAllUser(Condition cnd, Pager pager) {
        return cbase000DAOImp.queryAllUser(cnd,pager);
    }

    private List<Cbase000VO> formatC0(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase000VO> cbase000VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase000VOS.add((Cbase000VO) iterator.next());
        }
        return  cbase000VOS;
    }

}
