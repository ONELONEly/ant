package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss005DAOImp;
import com.gree.ant.mo.basic.Tbuss005BasicMO;
import com.gree.ant.vo.Tbuss005VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Tbuss005MO implements Tbuss005BasicMO{

    @Inject("refer:tbuss005DAOImp")
    private Tbuss005DAOImp tbuss005DAOImp;


    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the list
     * @description 通过Cnd, Pager查询全部成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 02:09:55.
     */
    @Override
    public List<Tbuss005VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return tbuss005DAOImp.queryByCndPager(cnd,pager);
    }

    /**
     * Insert tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description 插入单条成绩
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:13 09:09:44.
     */
    @Override
    public Tbuss005VO insert(Tbuss005VO tbuss005VO) {
        return tbuss005DAOImp.insert(tbuss005VO);
    }

    /**
     * Fectch link by vo tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description 通过实体查询相关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:15 06:09:57.
     */
    @Override
    public Tbuss005VO fectchLinkByVO(Tbuss005VO tbuss005VO,String primary) {
        return tbuss005DAOImp.fetchLinks(tbuss005VO,primary,null);
    }

    /**
     * Fetch by vo tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description true-为空，false-不为空
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 04:09:58.
     */
    @Override
    public Boolean insertCheck(Tbuss005VO tbuss005VO) {
        return tbuss005DAOImp.queryByCndPager(Cnd.where("ptno","=",tbuss005VO.getPtno()).and("pjno","=",tbuss005VO.getPjno()).and("csid","=",tbuss005VO.getCsid()),null).size() == 0;
    }

    /**
     * Update by vo tbuss 005 vo.
     *
     * @param tbuss005VO the tbuss 005 vo
     * @return the tbuss 005 vo
     * @description 通过实体更新
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:19 04:09:53.
     */
    @Override
    public Integer updateByVO(Tbuss005VO tbuss005VO) {
        return tbuss005DAOImp.updateByVO(tbuss005VO);
    }

    /**
     * Delete by ptno integer.
     *
     * @param ptno the ptno
     * @return the integer
     * @description 通过绩效表编号删除多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 02:09:45.
     */
    @Override
    public Integer deleteByPtno(String ptno) {
        return tbuss005DAOImp.deleteByPtno(ptno);
    }

    @Override
    public Integer deleteByPtnoRule(String ptno, String pjno) {
        return tbuss005DAOImp.deleteByPtnoPjno(ptno, pjno);
    }
}
