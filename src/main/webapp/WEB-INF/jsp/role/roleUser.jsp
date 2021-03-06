<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/13
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户角色</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="./index"><cite style="cursor: pointer;">角色管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">角色用户</cite></a>
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
                    <button class="layui-btn layui-btn-radius" lay-filter="add" lay-submit>添加</button>
                </div>
                <div class="layui-input-inline">
                    <input type="hidden" name="roid" id="roid" value="${obj}"/>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-btn-danger delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{url:'./queryAllRoleUser?roid=${obj}',id:'user'}" lay-filter="user">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'USID',align:'center',width:'20%',sort:true}">用户编号</th>
            <th lay-data="{field:'DSCA',align:'center',width:'30%'}">描述</th>
            <th lay-data="{field:'DEPTNAM',align:'center',width:'30%'-50}">部门</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:'20%', align:'center'}">操作</th>
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

        var roid = $("#roid").val();

        form.verify({
            usid:function (value) {
                if(checkForm(value)){
                    return "请选择要添加的用户！";
                }
            }
        });

        form.on("submit(add)",function (data) {
            var infor = data.field;
            $.ajax({
                type:'POST',
                url:'../user/insertUserRole',
                data:{
                    usid:infor.usid,
                    roid:infor.roid
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
                        url:'./deleteRoleUser',
                        data:{
                            usid:data.USID,
                            roid:roid
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
                param[i] = data[i].USID;
            }
            $.ajax({
                type:'POST',
                url:'./deleteRoleUser',
                data:{
                    roid:roid,
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
                    var option = " <option value='' class='n-display' disabled selected>请选择添加的用户</option>";
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






