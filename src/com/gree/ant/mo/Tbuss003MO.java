package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp;
import com.gree.ant.mo.basic.Tbuss003BasicMO;
import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@IocBean
public class Tbuss003MO implements Tbuss003BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:cbase015MO")
    private Cbase015MO cbase015MO;

    @Inject("refer:tbuss003DAOImp")
    private Tbuss003DAOImp tbuss003DAOImp;

    @Override
    public List<Tbuss003VO> queryAllByCnd(Condition cnd, Pager pager) {
        return tbuss003DAOImp.queryAllByCndPager(cnd, pager);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Tbuss003VO(),cnd);
    }

    /**
     * Insert by vo tbuss 003 vo.
     *
     * @param tbuss003VO the tbuss 003 vo
     * @return the tbuss 003 vo
     * @description 插入实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:50.
     */
    @Override
    public Tbuss003VO insertByVO(Tbuss003VO tbuss003VO) {
        return (Tbuss003VO) baseDAOImp.insert(tbuss003VO);
    }

    /**
     * Update by vo tbuss 003 vo.
     *
     * @param tbuss003VO the tbuss 003 vo
     * @return the tbuss 003 vo
     * @description 通过实体修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 11:09:29.
     */
    @Override
    public Integer updateByVO(Tbuss003VO tbuss003VO) {
        return baseDAOImp.update(tbuss003VO);
    }

    /**
     * Fetch by taid tbuss 003 vo.
     *
     * @param taid 任务编号
     * @return int 是否成功
     * @description 通过任务编号查询单条任务记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 11:09:29.
     */
    @Override
    public Tbuss003VO fetchByTaid(String taid) {
        return (Tbuss003VO)baseDAOImp.fetchByName(new Tbuss003VO(),taid);
    }

    /**
     * Fetch trans by vo tbuss 003 vo.
     *
     * @param taid    @ID主键
     * @param primary 关联字段
     * @param cnd     过滤器
     * @return the tbuss 003 vo
     * @description 通过ID ，primary,Cnd查询单个数据交互实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:26 06:09:21.
     */
    @Override
    public Tbuss003VO fetchTrans(String taid, String primary, Condition cnd) {
        return (Tbuss003VO)baseDAOImp.fetchTransByNameCnd(new Tbuss003VO(),taid,primary,cnd);
    }

    @Override
    public List<Tbuss003VO> queryAllGradeTask(String usid, Pager pager,Condition cnd) {
        return tbuss003DAOImp.queryAllGradeTask(usid,pager,cnd);
    }

    @Override
    public List<Tbuss003VO> queryGropAllTask(String usid, Pager pager, Condition condition) {
        return tbuss003DAOImp.queryGropAllTask(usid,pager,condition);
    }

    @Override
    public List<Tbuss003VO> queryAllTask(Condition cnd0, Condition cnd1, Pager pager) {
        return tbuss003DAOImp.queryAllTask(cnd0,cnd1,pager);
    }

    @Override
    public List<Tbuss003VO> queryGropAllTaskPrint(String usid, Pager pager, Condition condition) {
        return tbuss003DAOImp.queryGropAllTaskPrint(usid, pager, condition);
    }

    @Override
    public List<Tbuss003VO> queryAllTaskPrint(Condition cnd0, Condition cnd1, Pager pager) {
        return tbuss003DAOImp.queryAllTaskPrint(cnd0, cnd1, pager);
    }

    @Override
    public Integer countGropTask(String usid, Condition condition) {
        return tbuss003DAOImp.countGropTask(usid, condition);
    }

    @Override
    public Integer countAllTask(Condition cnd0, Condition cnd1) {
        return tbuss003DAOImp.countAllTask(cnd0,cnd1);
    }

    /**
     * Delete by taid integer.
     *
     * @param taid the taid
     * @return the integer
     * @description 通过任务ID删除任务
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:08 09:09:14.
     */
    @Override
    public Integer deleteByTaid(String taid) {
        Integer r15 = cbase015MO.deleteByTaid(taid);
        Integer r03 = baseDAOImp.deleteByName(new Tbuss003VO(),taid);
        if(Objects.equals(r15, r03)){
            return r15;
        }
        return 0;
    }

    private List<Tbuss003VO> formatt03(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Tbuss003VO> tbuss003VOS = new ArrayList<>();
        while(iterator.hasNext()){
            tbuss003VOS.add((Tbuss003VO) iterator.next());
        }
        return tbuss003VOS;
    }
}
