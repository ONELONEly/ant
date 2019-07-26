<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/21
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>工时查询</title>
    <c:import url="../../static1.html"/>
    <style>
        .laytable-cell-group span{
            font-size:large;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">统计</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">工时查询</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="mdat" id="mdat" placeholder="日期过滤" lay-verify="mdat" autocomplete="off"
                               class="layui-input"/>
                </div>

                <div class="layui-input-inline">
                    <select name="acco" id="acco" lay-filter="acco" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤科室</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <hr class="layui-bg-orange"/>
    <div id="left" class="x-equal" style="float:left;">
        <table class="layui-table" lay-data="{url:'./queryAllProjectFahh',id:'project'}" lay-filter="project">
            <thead>
            <tr>
                <th lay-data="{align:'center'}" colspan="3">项目工时</th>
            </tr>
            <tr>
                <th lay-data="{field:'csid',align:'center',width:'40%',sort:true,templet:'#user'}">用户</th>
                <th lay-data="{field:'number',align:'center',width:'30%',sort:true}">数量</th>
                <th lay-data="{field:'fahh',align:'center',width:'30%',sort:true}">工时</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="right" class="x-equal" style="float: right;">
        <table class="layui-table" lay-data="{url:'./queryAllNotProjectFahh',id:'notProject'}" lay-filter="notProject">
            <thead>
            <tr>
                <th lay-data="{align:'center',}" colspan="3">非项目工时</th>
            </tr>
            <tr>
                <th lay-data="{field:'csid',align:'center',width:'40%',sort:true,templet:'#user'}">用户</th>
                <th lay-data="{field:'number',align:'center',width:'30%',sort:true}">数量</th>
                <th lay-data="{field:'fahh',align:'center',width:'30%',sort:true}">工时</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/html" id="user">
    <span>{{d.dsca}} ( {{d.csid}} )</span>
</script>
</body>

<script language="JavaScript">
    layui.use(["table","jquery","form",'laydate'],function () {
        var table = layui.table, form = layui.form, $ = layui.jquery,laydate = layui.laydate;

        $.ajax({
            type:'POST',
            url:'../util/findC17',
            dataType:'json',
            success:function (data) {
                var accos = data.acco;
                var uOption = "";
                for(var i = 0;i<accos.length;i++){
                    uOption += "<option value='"+accos[i].id+"'>"+accos[i].dsca+"</option>";
                }
                $("#acco").append(uOption);
                form.render();
            },
            error:function (kellyj) {
                layer.alert("发生错误，错误码为:"+kellyj.status,{offset:'10px',anim:1});
            }
        });

        var start = {
            elem: '#mdat',
            type: 'month',
            done: function (value,obj) {
                reload(value,$("#acco option:selected").val())
            }
        };
        laydate.render(start);

        form.on("select(acco)",function (data) {
            reload($("#date").val(), data.value)
        });

        function reload (date, acco) {
            let where = {
                date:date,
                acco:acco
            };
            table.reload('project',{
                where:where,
                page:{
                    curr:1
                }
            });
            table.reload('notProject',{
                where:where,
                page:{
                    curr:1
                }
            });
        }
    });
</script>
</html>
