<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/8/29
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>蚂蚁啃骨头</title>
    <c:import url="../static.html"/>
    <link rel="stylesheet" href="./static/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="./build/css/font-awesome.min.css">
    <link rel="stylesheet" href="./build/css/app.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${base}/static/images/logo/logo.png"/>
        </div>
        <ul class="layui-nav layui-layout-right kit-nav" lay-filter="kitNavbar" kit-navbar>
            <c:forEach var="one" items="${obj.oneLevel}">
                <li class="layui-nav-item">
                    <a href="javascript:;" data-url="${one.purl}" data-icon="&#xe628;" data-title="${one.dsca}" kit-target data-id='${one.dsca}'><i class="layui-icon">&#xe628;</i><span>${one.dsca}</span></a>
                </li>
            </c:forEach>
            <li class="layui-nav-item">
            <a href="javascript:" data-url="./user/board" data-icon="&#xe628;" data-title="个人看板" kit-target data-id='个人看板'><cite class="n-display">个人看板</cite><span>${obj.user.DSCA}</span><span class="usid n-display">${obj.user.USID}</span><span class="layui-badge" id="newTask"></span></a>
        </li>
            <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <dl class="layui-nav-child"> <!-- 二级菜单 -->
                    <dd><a href="${base}/uic/test">移动模块</a></dd>
                    <dd> <a href="javascript:;" data-url="${base}/uic/test" data-icon="&#xe628;" data-title="测试" kit-target data-id='测试'><i class="layui-icon">&#xe628;</i><span>测试</span></a></dd>
                    <dd><a href="">电商平台</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:" data-url="./user/modify" data-icon="&#xe628;" data-title="修改资料" kit-target data-id='修改资料'>
                    <cite class="n-display">修改资料</cite>
                    <img class="header-img" src="./user/getUserHeader" id="head">
                </a>

            </li>
            <li class="layui-nav-item">
                <a href="./loginOut">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="layui-icon">&#xe62a;</i></div>
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                <c:forEach var="second" items="${obj.secondLevel}">
                    <li class="layui-nav-item">
                        <a href="javascript:;"><i class="layui-icon">&#xe628;</i><span>${second.dsca}</span></a>
                        <dl class="layui-nav-child">
                            <c:forEach var="third" items="${obj.thirdLevel}">
                                <c:if test="${third.cbase003VO.pono == second.pono}">
                                    <dd>
                                        <a href="javascript:;" data-url="${third.purl}" data-icon="&#xe658;" data-title="${third.dsca}" kit-target data-id='${third.dsca}'><i class="layui-icon">&#xe658;</i>&nbsp;<span>${third.dsca}</span></a>
                                    </dd>
                                    </c:if>
                            </c:forEach>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-body" id="container">
    </div>

    <div class="layui-footer x-center">
        <span>技术开发团队：GREE-ANT GNAW BONE</span>
    </div>
</div>
<script src="./static/layui/layui.js"></script>
<script>
    var message;
    layui.config({
        base: './build/js/'
    }).use(['app', 'message'], function() {
        var app = layui.app, $ = layui.jquery, layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        sessionStorage.setItem("usid",$("#usid").val())

        //主入口
        app.set({
            type: 'iframe'
        }).init();

        $(".layui-this").html("<i class='layui-icon'>&#xe68e;</i>首页");
        $(".layui-tab-content div").eq(0).find("iframe").attr("src","./main");
        $(".kit-tab-tool").html("<i class='layui-icon' style='font-size:x-large;'>&#xe620;</i>");

        $.ajax({
            type:'POST',
            url:'${base}/util/getNewTaskCount',
            dataType:'json',
            success:function (data) {
                $("#newTask").text(data);
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status);
            }
        });
    });
</script>
</body>
</html>