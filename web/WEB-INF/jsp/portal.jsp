<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/20
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>绩效管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <c:import url="../static.html"/>
    <script language="JavaScript">
        layui.use(['element','carousel','jquery','table'],function () {
            var $ = layui.jquery,element = layui.element,carousel = layui.carousel,table = layui.table;
            carousel.render({
                elem: '#carousel',
                width: '100%',
                height:'400px',
                arrow: 'always',
                autoplay:true,
                interval:2000
            });

            table.on('tool(manage)',function (obj) {
                var data = obj.data;
                layer.open({
                    type:2,
                    content:['./doc/showDoc?doid='+data.doid],
                    area: ['90%', '90%'],
                    title:'文档',
                    offset:'10px'
                });
            });
        });
    </script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${base}/static/images/logo/logo.png"/>
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="./login">登陆</a>
            </li>
        </ul>
    </div>

    <div>
        <div class="layui-carousel" id="carousel">
            <div class="x-center" style="background: #23262e;" carousel-item>
                <div style="background: #23262e;line-height: 400px;"><img src="${base}/static/images/allS-s.jpg"/></div>
            </div>
        </div>
    </div>

    <div class="x-body">
        <div class="x-titleDiv">
            <span>&nbsp;公开文档</span>
        </div>
        <table class="layui-table" lay-skin="nob" lay-data="{height:'200',url:'${base}/doc/queryAllMessage?ctyp=3',id:'manage'}"  lay-filter="manage">
            <thead>
            <tr>
                <th lay-data="{field:'tilt',toolbar:'#noteTpl'}">标题</th>
            </tr>
            </thead>
        </table>
        <script type="text/html" id="noteTpl">
            <a href="javascript:" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
        </script>
        <%--<br><br><br><br><br><br><br><br><br>--%>
    </div>
</div>
</body>
</html>

