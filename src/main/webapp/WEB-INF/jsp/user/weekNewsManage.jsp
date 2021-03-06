<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/22
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>文档管理</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
<span class="layui-breadcrumb">
    <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">我的</cite></a>
    <a href="javascript:location.replace(location.href)"><cite style="cursor: pointer;">个人周报</cite></a>
    <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
</span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="msg" id="msg" placeholder="请输入查询信息" lay-verify="msg" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit>查询</button>
                </div>
                <a href="./weekNews" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{url:'${base}/doc/queryAllUserWeekDoc',page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'doid',align:'center',width:150,sort:true}">编号</th>
            <th lay-data="{field:'tilt',align:'center',width:350,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'cdat',align:'center',width:150,sort:true}">创建时间</th>
            <th lay-data="{field:'unam',align:'center',width:150}">创建用户</th>
            <th lay-data="{field:'cnam',align:'center',width:150}">接收用户</th>
            <th lay-data="{field:'ctypnam',align:'center',width:150}">类型</th>
            <th lay-data="{field:'statnam',align:'center',width:150}">权限</th>
            <th lay-data="{field:'sta2nam',align:'center',width:150}">状态</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
    </script>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-xs" href="../doc/edit?doid={{d.doid}}&key=rose">编辑</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(['form','jquery','table','layer','element'],function () {
        var $ = layui.jquery,form = layui.form,table = layui.table,
            layer = layui.layer,elemnt = layui.element;

        form.on("submit(search)",function (data) {
            var infor = data.field;
            table.reload("manage",{
                where:{
                    key:infor.msg
                },
                page:{
                    curr:1
                }
            });
            return false;
        });

        table.on('tool(manage)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'${base}/doc/deleteDoc',
                        data:{
                            doid:data.doid
                        },
                        dataType:'json',
                        success:function (res) {
                            if(res.code === 1){
                                layer.confirm("删除成功",{btn:['确认'],offset:'10px'},function () {
                                    obj.del();
                                    layer.closeAll();
                                });
                            }else{
                                layer.alert(res.msg,{offset:'10px'});
                            }
                        },
                        error:function (kj) {
                            layer.alert("发生错误:"+kj.status,{offset:'10px'});
                        }
                    });
                });
            }else if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:['../doc/showDoc?doid='+data.doid],
                    area: ['90%', '70%'],
                    title:'文档',
                    offset:'10px'
                });
            }
        });

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus('manage');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].doid;
            }
            $.ajax({
                type:'POST',
                url:'${base}/doc/deleteDoc',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm("删除成功",{btn:['确认'],offset:'10px'},function (index) {
                            table.reload("manage");
                            layer.close(index)
                        });
                    }else{
                        layer.alert(res.msg,{offset:'10px'});
                    }
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

