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
    <title>蚂蚁啃骨头</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <c:import url="../static.html"/>
</head>
<body>
<div class="layui-layout-admin layui-layout">
    <%--style="border-bottom:3px solid forestgreen"--%>
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${base}/static/images/logo/logo.png"/>
        </div>
        <ul class="layui-nav layui-layout-right" lay-filter="side">
            <c:forEach var="one" items="${obj.oneLevel}">
                <li class="layui-nav-item">
                    <a href="javascript:" _href="${one.purl}"><cite>${one.dsca}</cite></a>
                </li>
            </c:forEach>
            <li class="layui-nav-item">
                <a href="javascript:" _href="./user/board"><cite class="n-display">个人看板</cite><span class="usid n-display">${obj.user.USID}</span>${obj.user.DSCA}<span class="layui-badge" id="newTask">5</span></a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:" _href="./user/modify">
                    <cite class="n-display">修改资料</cite>
                    <img class="header-img" src="./user/getUserHeader" id="head">
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="./loginOut">退出</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
                <c:forEach var="second" items="${obj.secondLevel}">
                    <li class="layui-nav-item">
                        <a href="javascript:">${second.dsca}</a>
                        <dl class="layui-nav-child">
                            <c:forEach var="third" items="${obj.thirdLevel}">
                                <c:if test="${third.cbase003VO.pono == second.pono}">
                                    <dd><a href="javascript:" _href="${third.purl}"><cite>${third.dsca}</cite></a></dd>
                                </c:if>
                            </c:forEach>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="layui-tab layui-tab-card site-demo-title x-main" lay-filter="bodyTab" lay-allowclose="true">
        <div class="x-slide_left"></div>
        <span class="n-display count">${count}</span>
        <ul class="layui-tab-title">
            <li class="layui-this"><cite>首页</cite><i class="layui-icon layui-unselect layui-tab-close">ဆ</i></li>
        </ul>
        <div class="layui-tab-content site-demo site-demo-body">
            <div class="layui-tab-item layui-show">
                <iframe src="./main" frameborder="0" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
</body>
<script language="JavaScript">
    layui.use(["element",'jquery','layer'],function () {
        var element = layui.element,$ = layui.jquery,layer = layui.layer,count = $(".count").text();
        sessionStorage.setItem("usid",$(".usid").text());
        if(count === 1){
            window.location.reload();
        }
        element.init();
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
</html>
