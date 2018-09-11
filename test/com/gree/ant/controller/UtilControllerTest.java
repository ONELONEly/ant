package com.gree.ant.controller;

import com.gree.MyNutTestRunner;
import com.gree.ant.vo.util.TaskUtilVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class UtilControllerTest {

    @Inject
    private UtilController utilController;

    @Test
    public void findTaskVO() {
//        System.out.println(utilController.findTaskVO(1,2,"pdat","desc",new TaskUtilVO()));
    }
}