<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/17
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>团队用户</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="./index"><cite style="cursor: pointer;">团队管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">团队用户</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="usid" id="usid" lay-verify="usid" lay-filter="usid" lay-search>
                        <option value="" class="n-display" disabled selected>请选择添加的用户</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="checkbox" id="boss" name="boss" checked="" lay-skin="switch" lay-filter="switchTest" lay-text="组长|组员">
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="add" lay-submit>添加</button>
                </div>
                <div class="layui-input-inline">
                    <input type="hidden" name="grop" id="grop" value="${obj}"/>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-btn-danger delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{url:'./queryAllGropUser?grop=${obj}',id:'user'}" lay-filter="user">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'usid',align:'center',width:'25%',sort:true}">用户编号</th>
            <th lay-data="{field:'dsca',align:'center',width:'25%'}">描述</th>
            <th lay-data="{field:'boss',align:'center',width:'25%'}">职务</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:'20%'-50, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laypage = layui.laypage,
            element = layui.element,
            layer = layui.layer,
            table = layui.table,
            form = layui.form,
            $ = layui.jquery;

        var grop = $("#grop").val();

        form.verify({
            usid:function (value) {
                if(checkForm(value)){
                    return "请选择要添加的用户！";
                }
            }
        });

        form.on("submit(add)",function (data) {
            var infor = data.field;
            var boss = $("#boss").attr("checked") === 'checked' ? 1:0;
            $.ajax({
                type:'POST',
                url:'./insertGropUser',
                data:{
                    usid:infor.usid,
                    grop:infor.grop,
                    boss:boss
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("user");
                        init();
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
            return false;
        });

        table.on('tool(user)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'./deleteGropUser',
                        data:{
                            usid:data.usid,
                            grop:grop
                        },
                        dataType:'json',
                        success:function (res) {
                            if(res.code === 1){
                                obj.del();
                                layer.close(index);
                                init();
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

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus('user');
            var data = check.data;
            var param = [];
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].usid;
            }
            $.ajax({
                type:'POST',
                url:'./deleteGropUser',
                data:{
                    grop:grop,
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("user")
                        init();
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });

        init();
        function init() {
            $.ajax({
                type:'GET',
                url:'${base}/util/findC0',
                dataType:'json',
                success:function (res) {
                    var data = res.c0;
                    var option = "<option value='' class='n-display' disabled selected>请选择添加的用户</option>";
                    for(var i = 0;i<data.length;i++){
                        option += "<option value='"+data[i].id+"'>"+data[i].dsca+"</option>";
                    }
                    $("#usid").html(option);
                    form.render();
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        }
    });
</script>
</body>
</html>






