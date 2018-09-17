<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/8
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>菜单管理</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">菜单管理</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="styp" id="styp" lay-filter="styp" lay-verify="styp" lay-search>
                        <option value="" disabled selected>请选择菜单类型</option>
                        <option value="0">一级菜单</option>
                        <option value="1">二级菜单</option>
                        <option value="2">三级菜单</option>
                    </select>
                </div>
                <div class="layui-input-inline n-display flno">
                    <select name="pono" id="pono" lay-filter="pono" lay-verify="pono" lay-search>
                        <option value="" disabled selected>请选择父节点</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="flno" id="flno" placeholder="请输入菜单ID" lay-verify="flno" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="dsca" id="dsca" placeholder="请输入菜单描述" lay-verify="dsca" class="layui-input"/>
                </div>
                <div class="layui-input-inline purl n-display">
                    <input type="text" name="purl" id="purl" placeholder="请输入菜单地址" lay-verify="purl" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="set" lay-submit>创建</button>
                </div>
            </div>
        </div>
    </form>
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="msg" id="msg" placeholder="请输入查询信息" lay-verify="msg" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit>查询</button>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{url:'./queryAllMenu',initSort:{field:'pono',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'menu'}" lay-filter="menu">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'pono',align:'center',width:'20%',sort:true}">菜单编号</th>
            <th lay-data="{field:'dsca',edit:true,align:'center',width:'20%'}">描述</th>
            <th lay-data="{field:'stypnam',align:'center',width:'20%'-50}">类型</th>
            <th lay-data="{field:'purl',edit:true,align:'center',width:'20%'}">地址</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:'20%', align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
    var laypage = layui.laypage, element = layui.element, layer = layui.layer,
    table = layui.table, form = layui.form, $ = layui.jquery;
    var styp = "";
    $(document).ready(function () {
    });

    form.on("submit(search)",function (data) {
    var infor = data.field;
    console.log(infor);
    table.reload("menu",{
    where:{
    key:infor.msg
    }
    });
    return false;
    });

    form.verify({
    styp: function (value) {
    if (value === null) {
    return "请选择菜单类型";
    }
    }
    });

    function verify() {
    if(styp === '2') {
    form.verify({
    styp: function (value) {
    if (value === null) {
    return "请选择菜单类型";
    }
    },
    pono: function (value) {
    if (value === null) {
    return "请选择父节点";
    }
    },
    flno: function (value) {
    if (value.length === 0 || value.length !== 3) {
    return "请输入菜单编号且菜单编号长度为3";
    }
    },
    dsca: function (value) {
    if (value.length === 0) {
    return "请输入菜单描述";
    }
    },
    purl: function (value) {
    if (value === null) {
    return "请输入菜单链接地址";
    }
    }
    });
    }else{
    form.verify({
    styp: function (value) {
    if (value === null) {
    return "请选择菜单类型";
    }
    },
    pono: function (value) {

    },
    flno: function (value) {
    if (value.length === 0 || value.length !== 3) {
    return "请输入菜单编号且菜单编号长度为3";
    }
    },
    dsca: function (value) {
    if (value.length === 0) {
    return "请输入菜单描述";
    }
    },
    purl: function (value) {

    }
    });
    }
    }

    form.on("submit(set)",function (data) {
    $.ajax({
    type:'POST',
    url:'./insertMenu',
    data:data.field,
    dataType:'json',
    success:function (res) {
    if(res.code === 1){
    $("#flno").val('');
    $("#purl").val('');
    table.reload("menu");
    }
    return layer.msg(res.msg,{offset:'10px'});
    },
    error:function (kj) {
    layer.alert("发生错误:"+kj.status,{offset:'10px'});
    }
    });
    return false;
    });

    table.on('edit(menu)',function (obj) {
    $.ajax({
    type:'POST',
    url:'./updateMenu',
    data:obj.data,
    dataType:'json',
    success:function (res) {
    if(res.code === 0){
    table.reload("menu");
    }
    return layer.msg(res.msg,{offset:'10px'});
    },
    error:function (kj) {
    layer.alert("发生错误:"+kj.status,{offset:'10px'});
    }
    });
    });

    table.on('tool(menu)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
    layer.confirm('真的删除行么',{offset:'10px'},function(index){
    console.log(data);
    $.ajax({
    type:'POST',
    url:'./deleteMenu',
    data:{
    pono:data.pono
    },
    dataType:'json',
    success:function (res) {
    if(res.code === 1){
    layer.close(index);
    table.reload('menu');
    }
    layer.msg(res.msg,{offset:'10px'});
    },
    error:function (kj) {
    layer.alert("发生错误:"+kj.status,{offset:'10px'});
    }
    });
    });
    }
    });

    form.on("select(styp)",function (obj) {
    styp = obj.value;
    if(styp === '2'){
    $.ajax({
    type:'GET',
    url:'${base}/util/findC2',
    dataType:'json',
    success:function (data) {
    var pono = data.c2;
    var option = "<option value='' disabled selected>请选择父节点</option>";
    for(var i = 0;i<pono.length;i++){
    option += "<option value='"+pono[i].pono+"'>"+pono[i].dsca+"</option>";
    }
    $("#pono").html(option);
    form.render();
    },
    error:function (kj) {
    layer.alert("发生错误:"+kj.status,{offset:'10px'});
    }
    });
    $(".flno").show();
    $(".purl").show();
    }else if(styp === '0'){
    $("#pono").val('');
    $(".flno").hide();
    $(".purl").show();
    }else{
    $("#pono").val('');
    $(".flno").hide();
    $(".purl").hide();
    }
    verify();
    });

    $(".delete-btn").on("click",function () {
    var check = table.checkStatus('menu');
    var data = check.data;
    var param = [];
    for(var i = 0;i<data.length;i++){
    param[i] = data[i].pono;
    }
    $.ajax({
    type:'POST',
    url:'./deleteMenu',
    data:{
    list:param
    },
    dataType:'json',
    success:function (res) {
    table.reload('menu');
    return layer.msg(res.msg,{offset:'10px'});
    },
    error:function (kj) {
    return layer.msg("发生错误:"+kj.status,{offset:'10px'});
    }
    });
    });
    });
</script>
</body>
</html>




