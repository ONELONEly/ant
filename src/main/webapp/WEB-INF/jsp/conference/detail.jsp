<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/6/11
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会议详情</title>
    <c:import url="../../static1.html"/>
    <style>
        .margin-50 {
            margin-top: 50px;
        }
        .margin-20 {
            margin-top: 20px;
        }
        .table-content {
            border: 1px solid #e5e5e5;
            border-bottom: 0;
        }
        .table-content-last {
            border: 1px solid #e5e5e5;
        }
        .table-label {
            padding: 10px 0;
        }
        .table-content-right {
            border-left: 1px solid #e5e5e5;
        }
        .table-input {
            padding: 0 10px;
            margin-left: 0 !important;
            line-height: 56px;
        }
        .table-label-content {
            display: block;
            padding: 9px 15px;
            font-weight: 400;
            line-height: 20px;
            text-align: center;
        }
        .content {
            line-height: 38px;
        }
        .title {
            float: left;
            display: block;
            padding: 9px 15px;
            font-weight: bold;
            line-height: 20px;
            text-align: right;
            font-size: 20px;
        }
        .title-content {

            float: left;
            display: block;
            padding: 9px 0;
            font-weight: bold;
            line-height: 20px;
            text-align: right;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="./index"><cite style="cursor: pointer;">我的会议</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">会议详情</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body layui-container">
    <form class="layui-form layui-form-panel" style="padding-bottom: 50px;">
        <div class="layui-row layui-col-space8 margin-50" >
            <div class="layui-col-md12">
                <label class="title">标题：</label>
                <div class="layui-input-block">
                    <span class="title-content">${obj.title}</span>
                </div>
            </div>
        </div>
        <div class="layui-row layui-col-space8" >
            <div class="layui-col-md6">
                <label class="layui-form-label">周数：</label>
                <div class="layui-input-block">
                    <span class="content">第${obj.week}周</span>
                </div>
            </div>
            <div class="layui-col-md6">
            </div>
        </div>
        <div class="layui-row table-content margin-20">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目开始时间</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    ${obj.startDateTxt}
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目计划上线时间</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    ${obj.scheduleDateTxt}
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目跟进人</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    ${obj.follower}
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目上周完成</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    ${obj.preWeekDoneTxt}
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目本周计划</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    ${obj.nowWeekScheduleTxt}
                </div>
            </div>
        </div>
        <div class="layui-row table-content-last">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">其它</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                   ${obj.othersTxt}
                </div>
            </div>
        </div>
    </form>
</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laydate = layui.laydate,
            laypage = layui.laypage,
            element = layui.element,
            layer = layui.layer,
            form = layui.form,
            $ = layui.jquery;
    });
</script>
</body>
</html>
