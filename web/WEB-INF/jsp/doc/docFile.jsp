<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/24
  Time: 10:15
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
        <a href="./showDoc?doid=${obj}" style="line-height: 40px;"><cite style="cursor: pointer;">文档详情</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">文档附件</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <table class="layui-table" lay-data="{url:'./queryAllFile?doid=${obj}',page:false,id:'file'}" lay-filter="file">
        <thead>
        <tr>
            <th lay-data="{field:'ffil',align:'center',width:'50%'}">文件名</th>
            <th lay-data="{field:'fsiz',align:'center',width:'20%'}">大小</th>
            <th lay-data="{fixed:'right',align:'center',width:'30%',templet:'#operate'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="operate">
        <a class="layui-btn layui-btn-xs layui-bg-black" href="../task/downloadFile?duta={{d.duta}}&ffil={{d.ffil}}">下载</a>
    </script>
</div>
<script language="JavaScript">
    layui.use(['jquery','table'],function () {
        var $ = layui.jquery,table = layui.table;
    });
</script>
</body>
</html>

