package com.gree.ant;

import com.gree.MyNutTestRunner;
import com.gree.ant.mo.Cbase013MO;
import com.gree.ant.mo.Cbase016MO;
import com.gree.ant.vo.Cbase013VO;
import com.gree.ant.vo.Cbase016VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
@RunWith(MyNutTestRunner.class)
public class SystemMOTest {

    @Inject("refer:cbase013MO")
    private Cbase013MO cbase013MO;

    @Inject("refer:cbase016MO")
    private Cbase016MO cbase016MO;

   //cbase018MO

    @Test
    public void testSystem() throws Exception {
        SqlExpressionGroup e = null;
        String key="";
        if(key != null){
            e = Cnd.exps("dsca","like","%"+key+"%");
        }
        Pager pager = new Pager(1,10);
        Cnd cnd = Cnd.where(e);
        List<Cbase013VO> list=cbase013MO.queryAllByCnd(cnd,pager);
        for (int i = 0; i <list.size() ; i++) {
            Cbase013VO cbase013VO=list.get(i);
            System.out.println(cbase013VO.getDsca());
        }
        System.out.println(list.size());

    }


    @Test
    public void testC16() throws Exception {
        Cbase016VO cbase016VO=cbase016MO.fetchByName("7");
        System.out.println(cbase016VO.getCtyp());
        System.out.println(cbase016VO.getDsca());

    }

}