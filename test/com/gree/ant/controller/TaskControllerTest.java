package com.gree.ant.controller;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(MyNutTestRunner.class)
public class TaskControllerTest {

    @Inject
    private TaskController taskController;

    @Test
    public void updateSta1() {
        String[] taid = new String[2];
        taid[0] = "JK44011224";

        try {
            taskController.updateSta1(11,4,taid,"执行校验通过行为","",(float)1,"",null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}