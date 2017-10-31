package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Cbase000VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.sql.rowset.serial.SerialBlob;

@RunWith(MyNutTestRunner.class)
@IocBean
public class Cbase000MOTest {

    @Inject
    private Cbase000MO cbase000MO;

    @Test
    public void queryAllByCnd() throws Exception {
        System.out.println(cbase000MO.fetchTranByUsidPRI("180365","cbase007VOS",null).getCbase007VOS().size());
    }

    @Test
    public void loginCheck() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        Cbase000VO cbase000VO = new Cbase000VO();
        cbase000VO.setUSID("123456");
        cbase000VO.setDSCA("测试用户");
        cbase000VO.setPAWD("123456");
        cbase000VO.setDEPT("D00024");
        cbase000VO.setJWWJ("测试用户");
        cbase000VO.setGROP("ANT001");
        cbase000VO.setACCO("K00003");
        cbase000VO.setCOMM("400");
        cbase000MO.insert(cbase000VO);
    }

}