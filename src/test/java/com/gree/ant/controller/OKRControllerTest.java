package com.gree.ant.controller;

import com.gree.MyNutTestRunner;
import com.gree.ant.vo.Tbuss011VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description TODO
 */
@RunWith(MyNutTestRunner.class)
@IocBean
public class OKRControllerTest {

    @Inject
    private OKRController okrController;

    @Test
    public void insert() {
        Tbuss011VO tbuss011VO = new Tbuss011VO();
        tbuss011VO.setASID("180366");
    }

    @Test
    public void queryAllOKR(){
    }
}