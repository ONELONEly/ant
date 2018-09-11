<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/3
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>OKR管理</title>
    <kellyj:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite>设置</cite></a>
        <a href="javascript:location.replace(location.href)"><cite>OKR管理</cite></a>
        <a href="javascript:location.replace(location.href)" class="layui-btn layui-btn-radius layui-btn-sm l-refresh" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入查询信息" name="msg" id="msg"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit>查询</button>
                </div>
                <div class="layui-input-inline">
                    <a href="./insert" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
                    <a href="./test" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
                </div>
            </div>
        </div>
    </form>

    <table class="layui-hide" id="okr" lay-filter="okr">

    </table>
</div>
<script language="JavaScript">
    layui.use(['table','element','form'],function () {
        var table = layui.table,element = layui.element,form = layui.form;
        table.render({
            elem:'#okr',
            url:'./queryAllOKR',
            cellMinWidth:100,
            page:true,
            cols:[[
                {field:'ASID',title:'管理对象',align:'center'},
                {field:'BOSS',title:'直接领导',align:'center'},
                {field:'MDAT',title:'管理周期',align:'center'},
                {field:'GOAL',title:'目标',align:'center'},
                {field:'NDAT',title:'周期',align:'center'},
                {field:'TYPE',title:'类型',align:'center'},
                {field:'PROP',title:'权重',align:'center'},
                {field:'PERF',title:'完成情况',align:'center'},
                {field:'ACHI',title:'关键成果',align:'center'},
                {field:'KRPROP',title:'KR权重',align:'center'},
                {field:'KRPERF',title:'KR完成情况',align:'center'},
                {field:'ZGRAD',title:'自评分',align:'center'}
                ]],
            response:{
                statusCode:1
            }
        });
    });
</script>
</body>
</html>
