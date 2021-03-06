<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/12
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色权限</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="./index"><cite style="cursor: pointer;">角色管理</cite></a>
        <a href="javascript:location.replace(location.href);" style="cursor: pointer;"><cite>角色权限</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="pono" id="pono" lay-verify="pono" lay-filter="pono" lay-search>
                        <option value="" class="n-display" disabled selected>请选择添加的权限</option>
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
    <table class="layui-table" lay-data="{url:'./queryAllRolePermission?roid=${obj}',id:'permission'}" lay-filter="permission">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'pono',align:'center',width:'20%',sort:true}">菜单编号</th>
            <th lay-data="{field:'dsca',align:'center',width:'20%'}">描述</th>
            <th lay-data="{field:'stypnam',align:'center',width:'20%'}">类型</th>
            <th lay-data="{field:'purl',align:'center',width:'20%'}">地址</th>
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

        var roid = $("#roid").val();

        form.verify({
            pono:function (value) {
                if(checkForm(value)){
                    return "请选择要添加的权限！";
                }
            }
        });

        form.on("submit(add)",function (data) {
            var infor = data.field;
            $.ajax({
                type:'POST',
                url:'./insertRolePermission',
                data:{
                    pono:infor.pono,
                    roid:infor.roid
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("permission");
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

        table.on('tool(permission)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'./deleteRolePermssion',
                        data:{
                            roid:roid,
                            pono:data.pono
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
            var check = table.checkStatus('permission');
            var data = check.data;
            var param = [];
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].pono;
            }
            $.ajax({
                type:'POST',
                url:'./deleteRolePermssion',
                data:{
                    roid:roid,
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("permission")
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
                url:'${base}/util/findC02',
                data:{
                    roid:roid
                },
                dataType:'json',
                success:function (res) {
                    var data = res.c2;
                    var option = "<option value='' class='n-display' disabled selected>请选择添加的权限</option>";
                    for(var i = 0;i<data.length;i++){
                        option += "<option value='"+data[i].pono+"'>"+data[i].dsca+"("+data[i].stypnam+")"+"</option>";
                    }
                    $("#pono").html(option);
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




