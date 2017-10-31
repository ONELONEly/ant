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
    <c:import url="../../static1.html"></c:import>
    <script language="JavaScript">
        layui.use(['form','jquery','table','layer','element'],function () {
            var $ = layui.jquery, form = layui.form, table = layui.table,
                layer = layui.layer, elemnt = layui.element;

            table.on('tool(manage)',function (obj) {
                var data = obj.data;
                layer.open({
                    type:2,
                    content:['./showDoc?doid='+data.doid],
                    area: ['90%', '80%'],
                    title:'文档',
                    offset:'10px'
                });
            });
        });
    </script>
</head>
<body>
<div class="x-body" style="width: 100%;height:800px;">
    <div class="x-titleDiv">
        <span style="font-size: xx-large;">&nbsp;学习文档</span>
    </div>
    <table class="layui-table" lay-skin="nob" lay-data="{height:'full-200',url:'./queryAllDoc?ctyp=4&auth=1',id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{field:'tilt',width:400,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'unam',width:150}">创建人</th>
            <th lay-data="{field:'cdat',width:150}">创建时间</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
    </script>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
