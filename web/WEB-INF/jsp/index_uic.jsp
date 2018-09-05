<%--
  Created by IntelliJ IDEA.
  User: jinyuk@foxmail(180365)
  Date: 2017/11/2
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kelly"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>蚂蚁啃骨头</title>
    <script src = "${base}/static/layui/layui.js"></script>
    <link rel = "stylesheet" href = "${base}/static/layui/css/layui.css">
    <link rel = "stylesheet" href = "${base}/static/css/admin.css">
    <script src = "${base}/static/js/admin.js"></script>
    <script src = "${base}/static/js/helper.js"></script>
    <script language="JavaScript">
        layui.use(['element','jquery'],function () {
            var element  = layui.element,$ = layui.jquery;
            element.init();
        });
        var ifm=document.getElementById("myiframe");
        ifm.height=document.documentElement.clientHeight;
    </script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${base}/static/images/logo/logo.png"/>
        </div>

        <ul class="layui-nav layui-layout-right" lay-filter="side">
<%--            <kelly:forEach var="one" items="${obj.oneLevel}">
                <li class="layui-nav-item">
                    <a href="javascript:"><cite>${one.dsca}</cite></a>
                    <dl class="layui-nav-child">
                        <kelly:forEach var="second" items="${obj.secondLevel}">
                            <kelly:if test="${second.cbase003VO.pono == one.pono}">
                                <dd><a href="javascript:" _href="${second.purl}"><cite>${second.dsca}</cite></a></dd>
                            </kelly:if>
                        </kelly:forEach>
                    </dl>
                </li>
            </kelly:forEach>--%>
    <li class="layui-nav-item">
        <a href="javascript:;">发现</a>
        <dl class="layui-nav-child"> <!-- 二级菜单 -->

            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=7"><cite>行业</cite></a></dd>
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=8"><cite>学习</cite></a></dd>
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=9"><cite>案例</cite></a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">标准</a>
        <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=17"><cite>移动端</cite></a></dd>
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=18"><cite>WEB端</cite></a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">素材库</a>
        <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=11"><cite>格力图库</cite></a></dd>
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=12"><cite>ICO</cite></a></dd>
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=13"><cite>素材</cite></a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">资源</a>
        <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=15"><cite>PPT模板</cite></a></dd>
            <dd> <a href="javascript:" _href="${base}/doc/knowledgeDoc?type=16"><cite>图库</cite></a></dd>
        </dl>
    </li>
        </ul>
    </div>


    <div>
        <div class="layui-tab layui-tab-card x-main" lay-filter="bodyTab" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this">
                    <cite>首页</cite><i class="layui-icon layui-unselect layui-tab-close">ဆ</i>
                </li>
            </ul>

            <div class="layui-tab-content site-demo site-demo-body" style="height: 800px;">
                <div class="layui-tab-item layui-show">

                    <iframe src="${base}/doc/knowledgeDoc?type=7" frameborder="0" id="myiframe" class="x-frame" style="padding:0px;width: 100%;height: 800px"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
