package com.gree.ant.controller;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.http.HttpServletRequest;

@IocBean
@RunWith(MyNutTestRunner.class)
public class GradeControllerTest {

    @Inject
    private GradeController gradeController;

    @Test
    public void printGrade() {


    }
}