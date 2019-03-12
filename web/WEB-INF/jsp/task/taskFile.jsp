<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/19
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>任务附件</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="./showTask?taid=${obj.taid}&state=${obj.state}" style="line-height: 40px;"><cite style="cursor: pointer;">任务详情</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">任务附件</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <table class="layui-table" lay-data="{url:'./queryAllFile?taid=${obj.taid}',page:false,id:'file'}" lay-filter="file">
        <thead>
        <tr>
            <th lay-data="{field:'ffil',align:'center',width:'40%'}">文件名</th>
            <th lay-data="{field:'fsiz',align:'center',width:'25%'}">大小</th>
            <th lay-data="{field:'cdat',align:'center',width:'25%'}">上传日期</th>
            <th lay-data="{fixed:'right',align:'center',width:'10%',templet:'#operate'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="operate">
        <a class="layui-btn layui-btn-xs layui-bg-black" href="./downloadFile?duta={{d.duta}}&ffil={{d.ffil}}">下载</a>
    </script>
</div>
<script language="JavaScript">
    layui.use(['jquery','table','element'],function () {
        var $ = layui.jquery,table = layui.table,element = layui.element;
        element.render();
    });
</script>
</body>
</html>
