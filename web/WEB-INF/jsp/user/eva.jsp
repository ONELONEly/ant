<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/5
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>我的评价</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">个人</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">我的评价</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form" style="width:50%">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="date" id="date" placeholder="请选择日期" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit="">查询</button>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-box">
        <table class="layui-table" lay-data="{url:'${base}/grade/userEva',initSort:{field:'pdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'eva'}" lay-filter="eva">
            <thead>
            <tr>
                <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
                <th lay-data="{field:'pdat',align:'center',width:'30%',sort:true}">月份</th>
                <th lay-data="{field:'dsca',align:'center',width:'50%',templet:'#themeTpl'}">主题</th>
                <th lay-data="{field:'count',align:'center',width:'20%'-50,templet:'#countTpl'}">统计</th>
            </tr>
            </thead>
        </table>
    </div>
    <script type="text/html" id="themeTpl">
        <a href="${base}/user/showGrade?ptno={{d.ptno}}"  class="layui-table-link">{{d.dsca}}</a>
    </script>
    <script type="text/html" id="countTpl">
        <a href="${base}/user/count?ptno={{d.ptno}}" class="layui-table-link">查看</a>
    </script>
</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laydate = layui.laydate,
            laypage = layui.laypage,
            element = layui.element,
            layer = layui.layer,
            table = layui.table,
            form = layui.form,
            $ = layui.jquery;

        var start = {
            elem:'#date',
            type:'month',
            choose: function (value) {
                console.log(value);
            }
        };

        form.on("submit(search)",function (data) {
            var infor = data.field;
            table.reload("eva",{
                where:{
                    key:infor.date
                }
            });
            return false;
        });

        laydate.render(start);
    });
</script>
</body>
</html>

