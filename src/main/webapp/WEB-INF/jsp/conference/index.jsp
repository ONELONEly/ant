<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/6/11
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会议管理</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">个人</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">会议管理</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
<%--    <form class="layui-form" style="width:50%;margin: 10px 0;">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-input-inline">
                <input type="text" name="month" id="month" placeholder="请选择日期" class="layui-input"/>
            </div>
            <div class="layui-input-inline">
                <select name="week" id="week" lay-verify="week">
                    <option value=""  disabled selected>请选择周数</option>
                    <option value="1" >第一周</option>
                    <option value="2" >第二周</option>
                    <option value="3" >第三周</option>
                    <option value="4" >第四周</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit="">查询</button>
            </div>
        </div>
    </form>--%>
    <div class="layui-inline" style="margin-top: 10px;">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <a href="./add" class="layui-btn"><i class="layui-icon">&#xe6af;</i>创建</a>
        <span>&nbsp;当前：第<span style="color: red;font-weight: bold;">${obj}</span>周</span>
    </div>
    <div class="layui-box">
        <table class="layui-table" id="eva" lay-filter="eva">
        </table>
    </div>
    <script type="text/html" id="titleTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.title}}</a>
    </script>
    <script type="text/html" id="operate">
        <a  href="./copy?conference={{d.conference}}" class="layui-btn layui-btn-xs">复制</a>
        <a  href="./update?conference={{d.conference}}" class="layui-btn layui-btn-xs">编辑</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="weekTpl">
        <span>第{{d.week}}周</span>
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



        table.render({
            elem:'#eva',
            url:'./loadTableData',
            method:'POST',
            cellMinWidth:200,
            page:true,
            limit:10,
            limits:[10,20,30,40,50],
            initSort:{field:'week',type:'desc'},
            cols:[[
                {fixed:true,checkbox:true,width:50},
                {field:'conference',align:'center',title:'编号',width:'25%'},
                {field:'title',align:'center',width:'25%',title:'标题',toolbar:'#titleTpl'},
                {field:'week',align:'center',width:'25%',title:'周数',sort:true,templet:'#weekTpl'},
                {fixed:'right',align:'center',width:'25%'-50,toolbar:'#operate'}
            ]],
            response:{
                statusCode:0
            }
        });

        var start = {
            elem:'#month',
            type:'month',
            choose: function (value) {
                console.log(value);
            }
        };

        form.on("submit(search)",function (data) {
            var infor = data.field;
            table.reload("eva",{
                where:infor,
                page:{
                    curr:1
                }
            });
            return false;
        });

        table.on('tool(eva)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'./deleteData',
                        data:{
                            conference:data.conference
                        },
                        dataType:'json',
                        success:function (res) {
                            if(res.code === 1){
                                layer.alert(res.msg,{offset:'10px'});
                                obj.del();
                                layer.close(index);
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
                    content:['./detail?conference='+data.conference],
                    area: ['90%', '70%'],
                    title:'详情',
                    offset:'10px'
                });
            }
        });

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus('eva');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].conference;
            }
            layer.confirm('真的删除所选行么',{offset:'10px'},function() {
                $.ajax({
                    type: 'POST',
                    url: './deleteData',
                    data: {
                        list: param
                    },
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 1) {
                            layer.confirm("删除成功", {btn: ['确认'], offset: '10px'}, function (index) {
                                table.reload("eva");
                                layer.close(index)
                            });
                        } else {
                            layer.alert(res.msg, {offset: '10px'});
                        }
                    },
                    error: function (kj) {
                        layer.alert("发生错误:" + kj.status, {offset: '10px'});
                    }
                });
            });
        });

        $(".copy-btn").on('click',function () {
            var check = table.checkStatus('eva');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].conference;
            }
            $.ajax({
                type:'POST',
                url:'./copyData',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm(res.msg,{offset:'10px'},function (index) {
                            layer.close(index);
                            table.reload("eva");
                        });
                    }else{
                        layer.alert(res.msg, {offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status, {offset:'10px'});
                }
            });
        });

        laydate.render(start);
    });
</script>
</body>
</html>
