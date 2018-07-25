<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/22
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>学习文档</title>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(['form','jquery','table','layer','element'],function () {
            var $ = layui.jquery, form = layui.form, table = layui.table,
                layer = layui.layer, elemnt = layui.element;
        });

    </script>
    <style>
        body{
            height: 100%;
        }

    </style>
</head>
<body style="padding:0px;width: 100%">
<div class="x-body">
    <div class="x-titleDiv">
        <span style="font-size: xx-large;">&nbsp;${obj.c16.dsca}</span>
    </div>${obj.data.doid}
    <table class="layui-table" lay-skin="nob" lay-data="{height:'full',url:'./queryAllMessage?ctyp=${obj.c16.ctyp}',id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{field:'tilt',width:400,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'unam',width:150}">创建人</th>
            <th lay-data="{field:'cdat',width:150}">创建时间</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="./showDoc1?doid={{d.doid}}" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
    </script>
</div>
</body>
</html>