package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Tbuss009DAOImp;
import com.gree.ant.mo.basic.Tbuss009BasicMO;
import com.gree.ant.vo.Tbuss009VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Tbuss009MO implements Tbuss009BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    @Inject("refer:tbuss009DAOImp")
    private Tbuss009DAOImp tbuss009DAOImp;


    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过Cnd, Pager查询多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    @Override
    public List<Tbuss009VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return formatt09(baseDAOImp.queryByCndPager(new Tbuss009VO(),cnd,pager));
    }

    /**
     * Fetch by id tbuss 009 vo.
     *
     * @param doid the 文档编号
     * @return the tbuss 009 vo
     * @description 通过@ID主键查询单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    @Override
    public Tbuss009VO fetchByID(Long doid) {
        return (Tbuss009VO) baseDAOImp.fetchByID(new Tbuss009VO(),doid);
    }

    /**
     * Fetch trans by id primary tbuss 009 vo.
     *
     * @param doid    文档编号
     * @param primary 关联字段
     * @param cnd    过滤字段
     * @return the tbuss 009 vo
     * @description 查询实体和它关联的实体通过@ID主键和关联字段
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    @Override
    public Tbuss009VO fetchTransByIDPrimary(Long doid, String primary,Condition cnd) {
        return (Tbuss009VO)baseDAOImp.fetchTransByIdCnd(new Tbuss009VO(),doid,primary,cnd);
    }

    /**
     * Insert tbuss 009 vo.
     *
     * @param tbuss009VO the tbuss 009 vo
     * @return the tbuss 009 vo
     * @description 通过实体插入单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 05:09:55.
     */
    @Override
    public Tbuss009VO insert(Tbuss009VO tbuss009VO) {
        return (Tbuss009VO)baseDAOImp.insert(tbuss009VO);
    }

    /**
     * Count integer.
     *
     * @return the integer
     * @description 统计共有多少条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 10:09:33.
     */
    @Override
    public Integer countByCnd(Condition cnd) {
        return baseDAOImp.countByCnd(new Tbuss009VO(),cnd);
    }

    /**
     * Delete by doid integer.
     *
     * @param doid the doid
     * @return the integer
     * @description 通过@ID删除单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 01:09:36.
     */
    @Override
    public Integer deleteByDoid(Long doid) {
        return baseDAOImp.deleteByID(new Tbuss009VO(),doid);
    }

    /**
     * Update by vo integer.
     *
     * @param tbuss009VO the tbuss 009 vo
     * @return the integer
     * @description 通过实体修改单条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 04:09:55.
     */
    @Override
    public Integer updateByVO(Tbuss009VO tbuss009VO) {
        return baseDAOImp.update(tbuss009VO);
    }

    @Override
    public List<Tbuss009VO> queryAllGropDoc(String usid, Condition cnd, Pager pager) {
        return tbuss009DAOImp.queryAllGropDoc(usid,cnd,pager);
    }

    private List<Tbuss009VO> formatt09(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Tbuss009VO> tbuss009VOS = new ArrayList<>();
        while(iterator.hasNext()){
            tbuss009VOS.add((Tbuss009VO) iterator.next());
        }
        return tbuss009VOS;
    }
}
