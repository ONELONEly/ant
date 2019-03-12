<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/10/9
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>个人需求</title>
    <kellyj:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor:pointer;">我的</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor:pointer;">个人需求</cite></a>
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
        </div>
    </form>
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="stat" id="stat" lay-filter="stat" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤状态</option>
                        <option value="0">下发</option>
                        <option value="1">执行</option>
                        <option value="2">驳回</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black back-btn"><i class="layui-icon">&#xe640;</i>驳回</button>
    </div>
    <table class="layui-table" id="require" lay-filter="require">
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>
    <script type="text/html" id="statTpl">
        {{# if(d.statnam === '下发'){ }}
        <span style="color:#00f;">{{d.statnam}}</span>
        {{# }else if(d.statnam === '执行'){ }}
        <span style="color: #008000">{{d.statnam}}</span>
        {{# }else if(d.statnam === '驳回'){ }}
        <span style="color: #f00;">{{d.statnam}}</span>
        {{# } }}
    </script>
    <script type="text/html" id="operate">
        <a href="../task/edit?taid={{d.raid}}&state=require&type=in" class="layui-btn layui-btn-xs" lay-event="edit">转成任务</a>
    </script>
</div>
<script language="JavaScript">
    layui.use(['jquery','form','table','layer'],function () {
        var $ = layui.jquery,form = layui.form,table = layui.table,
            layer = layui.layer;

        table.render({
            elem:'#require',
            url:'./queryUserRequire',
            cellMinWidth:100,
            page:true,
            limit:10,
            limits:[10,20,30,40,50],
            initSort:{field:'cdat',type:'desc'},
            cols:[[
                {fixed:true,checkbox:true,width:50,},
                {field:'raid',align:'center',title:'编号',width:150,},
                {field:'titl',align:'center',width:350,title:'标题',toolbar:'#noteTpl'},
                {field:'synonam',align:'center',width:150,title:'系统'},
                {field:'cnam',align:'center',width:150,title:'接收人'},
                {field:'sta2nam',align:'center',width:150,title:'紧急状态'},
                {field:'sta3nam',align:'center',width:150,title:'重要程度'},
                {field:'statnam',align:'center',width:150,title:'状态',templet:'#statTpl'},
                {field:'cdat',align:'center',width:300,title:'创建时间'},
                {field:'ydat',align:'center',width:300,title:'期望时间'},
                {fixed:'right',align:'center',width:200,toolbar:'#operate'}
            ]],
            response:{
                statusCode:0
            }
        });

        form.on("submit(search)",function (obj) {
            table.reload('require',{
                where:{
                    key:obj.field.msg,
                    stat:$("#stat option:selected").val()
                },
                page:{
                    curr:1
                }
            });
            return false;
        });

        table.on('tool(require)',function (obj) {
            if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:'../task/showTask?taid='+obj.data.raid+"&state=require",
                    area:['90%','80%'],
                    title:'任务',
                    offset:'10px'
                });
            }
        });

        form.on("select(stat)",function (data) {
            table.reload("require",{
                where:{
                    stat:data.value,
                    key:$("#msg").val()
                },
                page:{
                    curr:1
                }
            })
        });

        $(".back-btn").on('click',function () {
            var check = table.checkStatus('require');
            var data = check.data;
            var param ={};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].raid;
            }
            $.ajax({
                type:'POST',
                url:'./backRequire',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("require");
                    }
                    return layer.msg("驳回成功",{offset:'10px'});
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
