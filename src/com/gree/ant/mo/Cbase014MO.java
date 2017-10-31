package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase014BasicMO;
import com.gree.ant.vo.Cbase014VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Cbase014MO implements Cbase014BasicMO{

    @Inject("refer:baseDAOImp")
    private BaseDAOImp baseDAOImp;

    /**
     * Query all by cns list.
     *
     * @param cnd   过滤条件
     * @param pager 分页条件
     * @return the list
     * @description 通过cnd、pager查询所有信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:11 03:09:53.
     */
    @Override
    public List<Cbase014VO> queryAllByCnd(Condition cnd, Pager pager) {
        return formatC14(baseDAOImp.queryByCndPager(new Cbase014VO(),cnd,pager));
    }

    private List<Cbase014VO> formatC14(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase014VO> cbase014VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase014VOS.add((Cbase014VO) iterator.next());
        }
        return  cbase014VOS;
    }
}
