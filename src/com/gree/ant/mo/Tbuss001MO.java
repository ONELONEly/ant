package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss001DAOImp;
import com.gree.ant.mo.basic.Tbuss001BasicMO;
import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Tbuss001MO implements Tbuss001BasicMO{

    @Inject("refer:tbuss001DAOImp")
    private Tbuss001DAOImp tbuss001DAOImp;


    @Override
    public List<Tbuss001VO> queryAllByCnd(Condition cnd, Pager pager) {
        return tbuss001DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return tbuss001DAOImp.countByCnd(cnd);
    }

    /**
     * Delete by vo integer.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the integer
     * @description 通过实体删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:31.
     */
    @Override
    public Integer deleteByVO(Tbuss001VO tbuss001VO) {
        return tbuss001DAOImp.delete(tbuss001VO);
    }

    /**
     * Delete by name integer.
     *
     * @param ptno the ptno
     * @return the integer
     * @description 通过@Name删除单条记录, 0 -失败,1-成功
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 03:09:58.
     */
    @Override
    public Integer deleteByName(String ptno) {
        tbuss001DAOImp.clearLinks(fectchByName(ptno),"cbase011VOS");
        return tbuss001DAOImp.deleteByName(ptno);
    }

    /**
     * Detele relation integer.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the integer
     * @description 通过实体删除关联表的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:57.
     */
    @Override
    public Tbuss001VO deteleRelation(Tbuss001VO tbuss001VO) {
        return tbuss001DAOImp.clearLinks(tbuss001VO,"cbase011VOS");
    }

    /**
     * Fetch links by vo tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return 返回该记录和其关联的规则
     * @description 通过实体查询该记录的规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 11:09:23.
     */
    @Override
    public Tbuss001VO fetchLinksByVO(Tbuss001VO tbuss001VO,String primary,Condition cnd) {
        return tbuss001DAOImp.fetchLinks(tbuss001VO,primary,cnd);
    }

    /**
     * Fetch trans by name tbuss 001 vo.
     *
     * @param ptno   绩效编号
     * @param primary 关联映射关键词
     * @param cnd     过滤条件
     * @return 返回该记录和其关联的规则
     * @description 通过@Name主键查询该实体和规则
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 11:09:23.
     */
    @Override
    public Tbuss001VO fetchTransByNameCnd(String ptno,String primary,Condition cnd) {
        return tbuss001DAOImp.fetchTransByNameCnd(ptno,primary,cnd);
    }

    /**
     * Fectch by name tbuss 001 vo.
     *
     * @param ptno the ptno
     * @return the tbuss 001 vo
     * @description 通过 @Name主键查询单条信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 02:09:50.
     */
    @Override
    public Tbuss001VO fectchByName(String ptno) {
        return tbuss001DAOImp.fetchByName(ptno);
    }

    /**
     * Insert relation tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体插入Relation
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:14 01:09:42.
     */
    @Override
    public Tbuss001VO insertRelation(Tbuss001VO tbuss001VO) {
        return tbuss001DAOImp.insertRelation(tbuss001VO,"cbase011VOS");
    }

    /**
     * Insert tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体插入单条信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 04:09:41.
     */
    @Override
    public Tbuss001VO insert(Tbuss001VO tbuss001VO) {
        return tbuss001DAOImp.insert(tbuss001VO);
    }

    /**
     * Insert with tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体插入单条记录和他关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:28 09:09:58.
     */
    @Override
    public Tbuss001VO insertWith(Tbuss001VO tbuss001VO) {
        return tbuss001DAOImp.insertWith(tbuss001VO,"cbase011VOS");
    }

    /**
     * Insert check boolean.
     *
     * @param pdat the 创建日期
     * @param grop the 团队
     * @return the boolean
     * @description 通过创建时间判断是否存在, ，true-不存在,false-存在
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 03:09:40.
     */
    @Override
    public Boolean insertCheck(String pdat,String grop) {
        Condition cnd = Cnd.where("pdat","=",pdat).and("grop","=",grop);
        return tbuss001DAOImp.queryByCndPager(cnd,null).size() == 0;
    }

    /**
     * Update by vo tbuss 001 vo.
     *
     * @param tbuss001VO the tbuss 001 vo
     * @return the tbuss 001 vo
     * @description 通过实体修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:18 07:09:37.
     */
    @Override
    public Integer updateByVO(Tbuss001VO tbuss001VO) {
        return tbuss001DAOImp.update(tbuss001VO);
    }

    @Override
    public String fetchNameByUsidPdat(String usid, String pdat) {
        return tbuss001DAOImp.fetchNameByUsidPdat(usid, pdat);
    }

    @Override
    public List<Tbuss001VO> queryAllByAcco(String acco,String pdat,Pager pager) {
        return tbuss001DAOImp.queryAllByAcco(acco,pdat,pager);
    }

    @Override
    public List<Tbuss001VO> queryAllByDept(String dept,String pdat,Pager pager) {
        return tbuss001DAOImp.queryAllByDept(dept,pdat,pager);
    }

    @Override
    public List<Tbuss001VO> queryAllByComp(String comp,String pdat,Pager pager) {
        return tbuss001DAOImp.queryAllByComp(comp,pdat,pager);
    }

    @Override
    public Integer countByAcco(String acco,String pdat) {
        return tbuss001DAOImp.countByAcco(acco,pdat);
    }

    @Override
    public Integer countByDept(String dept,String pdat) {
        return tbuss001DAOImp.countByDept(dept,pdat);
    }

    @Override
    public Integer countByComp(String comp,String pdat) {
        return tbuss001DAOImp.countByComp(comp,pdat);
    }

    @Override
    public List<String> queryAllPdat() {
        return tbuss001DAOImp.queryAllPdat();
    }

    @Override
    public List<ResultVO> queryAllPD(Condition cnd) {
        return tbuss001DAOImp.queryAllPD(cnd);
    }
}
