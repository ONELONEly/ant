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
    <table class="layui-table" lay-skin="nob" lay-data="{url:'./queryAllMessage?ctyp=${obj.c16.ctyp}',id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{field:'tilt',align:'center',width:'50%',toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'unam',align:'center',width:'20%'}">创建人</th>
            <th lay-data="{field:'cdat',align:'center',width:'30%'}">创建时间</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
    </script>
</div>
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
</body>
</html>
