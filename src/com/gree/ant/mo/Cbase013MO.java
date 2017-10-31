package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase013BasicMO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase013MO implements Cbase013BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有系统信息.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:58.
     */
    @Override
    public List<Cbase013VO> queryAllByCnd(Condition cnd, Pager pager) {
        return formatC13(baseDAOImp.queryByCndPager(new Cbase013VO(),cnd,pager));
    }

    private List<Cbase013VO> formatC13(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase013VO> cbase013VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase013VOS.add((Cbase013VO) iterator.next());
        }
        return  cbase013VOS;
    }
}
