<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/22
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>任务展示</title>
    <c:import url="../../static1.html"></c:import>
    <script src="../static/js/jquery/jquery-1.8.2.min.js"></script>
    <script src="../static/js/jquery/jquery.jqprint-0.3.js"></script>
    <script language="JavaScript">
        layui.use(['jquery','table'],function () {
            var $ = layui.jquery,table = layui.table;
            $(".print").on('click',function () {
                $(".x-body").jqprint();
            });
        });
    </script>
</head>
<body>
<div class="x-body">
    <div class="x-titleDiv">
        <span style="font-size: xx-large;">&nbsp;${obj.task.titl}</span>
        <a href="./taskFile?taid=${obj.task.taid}" class="layui-btn layui-btn-mini layui-bg-black">附件</a>
    </div>
    <div>${obj.note}</div>
    <div class="layui-layout-right x-margin">
        <button class="layui-btn layui-btn-radius layui-bg-black print">打印</button>
        <%--<button class="layui-btn layui-btn-radius layui-bg-green share">分享</button>--%>
    </div>
</div>
</body>
</html>