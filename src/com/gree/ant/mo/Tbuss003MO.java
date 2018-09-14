package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss003DAOImp;
import com.gree.ant.mo.basic.Tbuss003BasicMO;
import com.gree.ant.util.StringUtil;
import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.util.TaskUtilVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

@IocBean
public class Tbuss003MO implements Tbuss003BasicMO{

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
        return tbuss003DAOImp.countByCnd(cnd);
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
        return tbuss003DAOImp.insert(tbuss003VO);
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
        return tbuss003DAOImp.update(tbuss003VO);
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
        return tbuss003DAOImp.fetchByName(taid);
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
        return tbuss003DAOImp.fetchTransByNameCnd(taid,primary,cnd);
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

    @Override
    public Integer countByTaskUtil(TaskUtilVO taskUtilVO) {
        Cnd cnd = Cnd.NEW();
        if(taskUtilVO.getCnam() != null){
            cnd.and("cnam","like","%"+taskUtilVO.getCnam()+"%");
        }
        if(taskUtilVO.getCsid() != null){
            cnd.and("csid","like","%"+taskUtilVO.getCsid()+"%");
        }
        if(taskUtilVO.getPdat() != null){
            cnd.and("pdat","like","%"+taskUtilVO.getPdat()+"%");
        }
        if(taskUtilVO.getTaid() != null){
            cnd.and("taid","like","%"+taskUtilVO.getTaid()+"%");
        }
        if(taskUtilVO.getTitl() != null){
            cnd.and("titl","like","%"+taskUtilVO.getTitl()+"%");
        }
        return tbuss003DAOImp.countTaskUtilByCnd(cnd);
    }

    @Override
    public List<TaskUtilVO> queryAllByPagerMsg(Pager pager, TaskUtilVO taskUtilVO,String sort,String order) {
        Cnd cnd = Cnd.NEW();
        if(taskUtilVO.getCnam() != null){
            cnd.and("cnam","like","%"+taskUtilVO.getCnam()+"%");
        }
        if(taskUtilVO.getCsid() != null){
            cnd.and("csid","like","%"+taskUtilVO.getCsid()+"%");
        }
        if(taskUtilVO.getPdat() != null){
            cnd.and("pdat","like","%"+taskUtilVO.getPdat()+"%");
        }
        if(taskUtilVO.getTaid() != null){
            cnd.and("taid","like","%"+taskUtilVO.getTaid()+"%");
        }
        if(taskUtilVO.getTitl() != null){
            cnd.and("titl","like","%"+taskUtilVO.getTitl()+"%");
        }
        if(StringUtil.checkString(sort,order,sort)) {
            cnd.orderBy(order, sort);
        }else{
            cnd.orderBy("pdat","desc");
        }
        return tbuss003DAOImp.queryAllTaskByPagerCnd(pager,cnd);
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
        final String taid_ = taid;
        final Integer[] r15 = {0};
        final Integer[] r03 = {1};
        Trans.exec(Connection.TRANSACTION_READ_COMMITTED, new Atom() {
            @Override
            public void run() {
                r15[0] = cbase015MO.deleteByTaid(taid_);
                r03[0] = tbuss003DAOImp.deleteByName(taid_);
            }
        });
        if(Objects.equals(r15[0], r03[0])){
            return r15[0];
        }
        return 0;
    }
}
