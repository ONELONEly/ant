package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase000DAOImp;
import com.gree.ant.mo.basic.Cbase000BasicMO;
import com.gree.ant.util.StringUtil;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.util.GradeVO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase000MO implements Cbase000BasicMO{

    @Inject("cbase000DAOImp")
    private Cbase000DAOImp cbase000DAOImp;


    @Override
    public List<Cbase000VO> queryAllByCnd(Condition cnd, Pager pager){
        return cbase000DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Boolean loginCheck(String usid,String pawd){
        return cbase000DAOImp.loginCheck(usid,pawd);
    }

    @Override
    public Cbase000VO insert(Cbase000VO cbase000VO) {
        return cbase000DAOImp.insert(cbase000VO);
    }

    @Override
    public Cbase000VO fetchByUsid(String usid) {
        return cbase000DAOImp.fetchByName(usid);
    }

    @Override
    public Cbase000VO fetchTranByUsidPRI(String usid, String primary, Condition cnd) {
        return cbase000DAOImp.fetchTransByNameCnd(usid,primary,cnd);
    }

    @Override
    public Integer updateByVO(Cbase000VO cbase000VO) {
        return cbase000DAOImp.update(cbase000VO);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase000DAOImp.countByCnd(cnd);
    }

    @Override
    public Integer deleteByUsid(String usid) {
        return cbase000DAOImp.deleteByName(usid);
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
        return cbase000DAOImp.delete(cbase000VO);
    }

    @Override
    public List<ResultVO> queryAllUD() {
        return cbase000DAOImp.queryAllUD();
    }

    @Override
    public List<GradeVO> queryAllGradeByPdat(String pdat,String grop) {
        Cnd cnd = Cnd.NEW();
        Cnd cnd2 = Cnd.NEW();
        if(StringUtil.checkString(pdat)){
            cnd = cnd.and("pdat","like","%"+pdat+"%");
        }
        if(StringUtil.checkString(grop)){
            cnd = cnd.and("grop","=",grop);
            cnd2 = cnd2.and("grop","=",grop);
        }
        return cbase000DAOImp.queryAllGradeByPdat(cnd,cnd2);
    }

    @Override
    public List<Cbase000VO> queryAllUser(Condition cnd, Pager pager) {
        return cbase000DAOImp.queryAllUser(cnd,pager);
    }

}
