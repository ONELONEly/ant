<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/8
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>任务展示</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-body">
    <div class="x-titleDiv">
        <span style="font-size: xx-large;">&nbsp;${obj.task.titl}</span>
        <a href="./taskFile?taid=${obj.task.taid}" class="layui-btn layui-btn-xs layui-bg-black">附件</a>
    </div>
    <span class=""></span>
    <blockquote class="layui-elem-quote layui-quote-nm">${obj.note}</blockquote>
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>绩效表：</strong>${obj.task.ptnonam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>类型：</strong>${obj.task.ptypnam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>派发给：</strong>${obj.task.cnam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>测试用户：</strong>${obj.task.tnam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>关键用户：</strong>${obj.task.knam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>验收人：</strong>${obj.task.rnam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>系统/项目：</strong>${obj.task.synonam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>阶段：</strong>${obj.jieddsca}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>任务类型：</strong>${obj.task.punonam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>优先级：</strong>${obj.task.sta2nam}</div>
            </div>
        </li>
        <li class="layui-timeline-item">
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"><strong>严重程度：</strong>${obj.task.sta3nam}</div>
            </div>
        </li>
    </ul>
    <div class="layui-layout-right x-margin">
        <button class="layui-btn layui-btn-radius layui-bg-black print">打印</button>
    </div>
</div>
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
</body>
</html>
