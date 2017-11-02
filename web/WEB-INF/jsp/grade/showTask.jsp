<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/8
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>评分显示</title>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
                var laypage = layui.laypage, element = layui.element, layer = layui.layer,
                    table = layui.table, form = layui.form, $ = layui.jquery;

            table.on('tool(manage)', function(obj){
                var data = obj.data;
                if(obj.event === 'show'){
                    layer.open({
                        type:2,
                        content:'../task/showTask?taid='+data.taid,
                        area:['90%','80%'],
                        title:'任务',
                        offset:'10px'
                    });
                }else if(obj.event === 'in'){
                    $.ajax({
                        type:'POST',
                        url:'${base}/task/eyeIn',
                        data:{
                            taid:data.taid
                        },
                        dataType:'json',
                        success:function (res) {
                            obj.update({
                                eye:"<a href='javascript:' class='layui-btn layui-btn-mini layui-btn-danger' lay-event='out'>取消</a>"
                            });
                            return layer.msg(res.msg);
                        },
                        error:function (kj) {
                            layer.alert("发生错误:"+kj.status);
                        }
                    });
                }else if(obj.event === 'out'){
                    $.ajax({
                        type:'POST',
                        url:'${base}/task/eyeOut',
                        data:{
                            taid:data.taid
                        },
                        dataType:'json',
                        success:function (res) {
                            obj.update({
                                eye:"<a href='javascript:' class='layui-btn layui-btn-mini layui-bg-black' lay-event='in'>关注</a>"
                            });
                            return layer.msg(res.msg);
                        },
                        error:function (kj) {
                            layer.alert("发生错误:"+kj.status);
                        }
                    });
                }
            });
            });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="./index"><cite style="cursor: pointer;">绩效</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">任务</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
<fieldset class="layui-elem-field layui-field-title">
    <legend>当月任务</legend>
</fieldset>
<table class="layui-table" lay-data="{height:'600',url:'${base}/task/queryAllGradeTask?ptno=${obj}',initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
    <thead>
    <tr>
        <th lay-data="{fixed:'left',field:'perc',width:150,sort:true}">完成度</th>
        <th lay-data="{fixed:'left',field:'titl',width:400,toolbar:'#noteTpl'}">标题</th>
        <th lay-data="{field:'synonam',width:150}">系统</th>
        <th lay-data="{field:'cnam',width:150}">派发给</th>
        <th lay-data="{field:'sta1nam',width:150}">状态</th>
        <th lay-data="{field:'punonam',width:150}">任务类型</th>
        <th lay-data="{field:'knam',width:150}">关键用户</th>
        <th lay-data="{field:'adat',width:150,sort:true}">执行时间</th>
        <th lay-data="{field:'pdat',width:150,sort:true}">计划时间</th>
        <th lay-data="{field:'tdat',width:150,sort:true}">测试时间</th>
        <th lay-data="{field:'fdat',width:150,sort:true}">验收时间</th>
        <th lay-data="{field:'cdat',width:150,sort:true}">创建时间</th>
        <th lay-data="{field:'eye',fixed:'right',align:'center',width:200}">关注</th>
    </tr>
    </thead>
</table>
<script type="text/html" id="noteTpl">
    <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
</script>
</div>
</body>
</html>
