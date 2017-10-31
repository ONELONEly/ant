package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.dao.daoImp.Tbuss005DAOImp;
import com.gree.ant.mo.basic.Tbuss005BasicMO;
import com.gree.ant.vo.Tbuss005VO;
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
public class Tbuss005MO implements Tbuss005BasicMO{


    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

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
        return formatt05(baseDAOImp.queryByCndPager(new Tbuss005VO(),cnd,pager));
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
        return (Tbuss005VO)baseDAOImp.insert(tbuss005VO);
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
        return (Tbuss005VO)baseDAOImp.fetchLinks(tbuss005VO,primary,null);
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
        return baseDAOImp.queryByCndPager(new Tbuss005VO(),Cnd.where("ptno","=",tbuss005VO.getPtno()).and("pjno","=",tbuss005VO.getPjno()).and("csid","=",tbuss005VO.getCsid()),null).size() == 0;
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

    private List<Tbuss005VO> formatt05(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Tbuss005VO> tbuss005VOS = new ArrayList<>();
        while(iterator.hasNext()){
            tbuss005VOS.add((Tbuss005VO) iterator.next());
        }
        return tbuss005VOS;
    }
}
