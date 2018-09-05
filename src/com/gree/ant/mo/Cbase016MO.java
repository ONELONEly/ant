package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.BaseDAOImp;
import com.gree.ant.mo.basic.Cbase016BasicMO;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@IocBean
public class Cbase016MO implements Cbase016BasicMO{

    @Inject("baseDAOImp")
    private BaseDAOImp baseDAOImp;

    /**
     * Query all by cnd pager cbase 016 vo.
     *
     * @param cnd   过滤字段
     * @param pager 分页字段
     * @return the cbase 016 vo
     * @description 通过Cnd, Pager查询多条记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:20 06:09:40.
     */
    @Override
    public List<Cbase016VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return formatC16(baseDAOImp.queryByCndPager(new Cbase016VO(),cnd,pager));
    }


    public Cbase016VO fetchByName(String ctyp) {
        return (Cbase016VO) baseDAOImp.fetchByName(new Cbase016VO(),ctyp);
    }

    private List<Cbase016VO> formatC16(List<ValueObject> voS){
        Iterator<ValueObject> iterator = voS.iterator();
        List<Cbase016VO> cbase016VOS = new ArrayList<>();
        while(iterator.hasNext()){
            cbase016VOS.add((Cbase016VO) iterator.next());
        }
        return  cbase016VOS;
    }
}
