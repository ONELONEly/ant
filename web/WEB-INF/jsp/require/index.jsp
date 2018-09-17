<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/30
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>用户需求</title>
    <kellyj:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor:pointer;">我的</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor:pointer;">用户需求</cite></a>
        <a href="javascript:location.reload();" class="layui-btn layui-btn-radius layui-btn-sm l-refresh" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form-pane layui-form">
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <input type="text" id="msg" name="msg" class="layui-input" lay-filter="msg" placeholder="请输入过滤信息"/>
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit>查询</button>
            </div>
            <div class="layui-input-inline">
                <a href="./insert" class="layui-btn layui-btn-radius">创建</a>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <button class="layui-btn layui-bg-black copy-btn"><i class="layui-icon">&#xe6af;</i>复制</button>
    </div>
    <table class="layui-table" lay-data="{url:'./queryUserRequire',page:true,limit:10,limits:[10,20,30,40,50],initSort:{field:'cdat',type:'desc'},id:'require'}" lay-filter="require">
        <thead>
            <tr>
                <th lay-data="{fixed:true,checkbox:true,width:50}"></th>
                <th lay-data="{field:'taid',align:'center',width:'10%'}">编号</th>
                <th lay-data="{field:'titl',align:'center',width:'40%',toolbar:'#noteTpl'}">标题</th>
                <th lay-data="{field:'synonam',align:'center',width:'15%'}">系统</th>
                <th lay-data="{field:'cdat',align:'center',width:'15%'-50,sort:true}">创建时间</th>
                <th lay-data="{fixed:'right',align:'center',width:'20%',align:'center',toolbar:'#operate'}">操作</th>
            </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>
    <script type="text/html" id="operate">
        <a href="./modify?taid={{d.taid}}" class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a href="javascript:" class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </script>
</div>
<script language="JavaScript">
    layui.use(['jquery','form','table','layer'],function () {
        var $ = layui.jquery,form = layui.form,table = layui.table,
            layer = layui.layer;

        form.on("submit(search)",function (obj) {
            table.reload('require',{
                where:{
                    key:obj.field.msg
                }
            });
            return false;
        });

        table.on('tool(require)',function (obj) {
            if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:'../task/showTask?taid='+obj.data.taid,
                    area:['90%','80%'],
                    title:'任务',
                    offset:'10px'
                });
            }else if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px',anim:1,btn:['确定','再考虑一下']},function(index){
                    $.ajax({
                        type:'POST',
                        url:'./deleteRequire',
                        data:{
                            taid:obj.data.taid
                        },
                        dataType:'json',
                        success:function (res) {
                            if(res.code === 1){
                                obj.del();
                            }
                            return layer.msg(res.msg,{offset:'10px'});
                        },
                        error:function (kj) {
                            layer.alert("发生错误:"+kj.status,{offset:'10px'});
                        }
                    });
                });
            }
        });

        $(".copy-btn").on('click',function () {
            var check = table.checkStatus('require');
            var data = check.data;
            var param ={};
            for(var i = 0;i < data.length;i++){
                param[i] = data[0].taid;
            }
            $.ajax({
                type:'POST',
                url:'./copyTask',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("require");
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus("require");
            var data = check.data;
            var param ={};
            for(var i = 0;i < data.length ;i++){
                param[i] = data[i].taid;
            }
            $.ajax({
                type:'POST',
                url:'./deleteRequire',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("require");
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });
    });
</script>
</body>
</html>
