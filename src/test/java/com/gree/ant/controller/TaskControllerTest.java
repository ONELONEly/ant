package com.gree.ant.controller;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}