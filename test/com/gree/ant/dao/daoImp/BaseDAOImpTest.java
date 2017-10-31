package com.gree.ant.dao.daoImp;

import com.gree.MyNutTestRunner;
import com.gree.ant.mo.Cbase015MO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.HTMLUtil;
import com.gree.ant.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MyNutTestRunner.class)
@IocBean
public class BaseDAOImpTest {

    @Inject("refer:$ioc")
    private Ioc ioc;

    @Inject
    private BaseDAOImp baseDAOImp;

    @Inject
    private Cbase015MO cbase015MO;

    @Test
    public void fetchByName() throws Exception {
        System.out.println(((Cbase009VO)baseDAOImp.fetchByName(new Cbase009VO(),"S00001")));
    }

    @Test
    public void queryByCndPager() throws Exception {
        Iterator<ValueObject> iterator= baseDAOImp.queryByCndPager(new Cbase000VO(),null,null).iterator();
        while(iterator.hasNext()){
            Cbase000VO cbase000VO = (Cbase000VO)iterator.next();
            System.out.println(cbase000VO.getPAWD());
        }
        System.out.println();
    }

    @Test
    public void insert() throws Exception {
        Cbase008VO cbase008VO = new Cbase008VO("S00002","测试");
        System.out.println(baseDAOImp.insert(cbase008VO));
    }

    @Test
    public void insert1() throws Exception {
        List<ValueObject> cbase008VOS = new ArrayList<>();
        Cbase008VO cbase008VO = new Cbase008VO("S00002","测试");
        Cbase008VO cbase008VO1 = new Cbase008VO("S00003","测试1");
        cbase008VOS.add(cbase008VO);
        cbase008VOS.add(cbase008VO1);
        System.out.println(baseDAOImp.insert(cbase008VOS));
    }

    @Test
    public void fastInsert() throws Exception {
        Cbase008VO cbase008VO = new Cbase008VO("S00005","测试");
        System.out.println(baseDAOImp.fastInsert(cbase008VO));
    }

    @Test
    public void fastInsert1() throws Exception {
        List<ValueObject> cbase008VOS = new ArrayList<>();
        Cbase008VO cbase008VO = new Cbase008VO("S00006","测试");
        Cbase008VO cbase008VO1 = new Cbase008VO("S00007","测试1");
        cbase008VOS.add(cbase008VO);
        cbase008VOS.add(cbase008VO1);
        System.out.println(baseDAOImp.fastInsert(cbase008VOS));
    }

    @Test
    public void insertWith() throws Exception {
        Cbase007VO cbase007VO = new Cbase007VO("R00001","测试","400");
        List<Cbase002VO> cbase002VOS = new ArrayList<>();
        cbase002VOS.add(new Cbase002VO("700","测试",1,""));
        cbase002VOS.add(new Cbase002VO("701","测试",1,""));
        cbase007VO.setCbase002VOS(cbase002VOS);
        System.out.println(baseDAOImp.deleteWith(cbase007VO,"cbase002VOS"));
    }

    @Test
    public void insertLinks() throws Exception {
    }

    @Test
    public void delete() throws Exception {
        String like = "测试";
        System.out.println(baseDAOImp.clearByCnd(new Cbase008VO(), Cnd.where("dsca","like","%"+like+"%")));

    }

    @Test
    public void update() throws Exception {
        System.out.print(HTMLUtil.delHTMLTag(FileUtil.convertClob(((Tbuss009VO) baseDAOImp.fetchByID(new Tbuss009VO(), Long.parseLong("1506173364859"))).getNote())));
    }

    @Test
    public void update1() throws Exception {
        Cbase012VO cbase012VO = new Cbase012VO();
        cbase012VO.setPjno("P08");
        cbase012VO.setOpco("+2");
        System.out.println();
    }

    @Test
    public void fetchByID() throws Exception {
    }

    @Test
    public void clear() throws Exception {

        System.out.println();
    }

    @Test
    public void create() throws Exception {

        System.out.println();
    }

    @Test
    public void drop() throws Exception {

        System.out.println();
    }

    @Test
    public void func() throws Exception {

        System.out.println();
    }

}