<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/5
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户管理</title>
    <c:import url="../../static1.html"></c:import>
    <script language="JavaScript">
        layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
            var laypage = layui.laypage,
                element = layui.element,
                layer = layui.layer,
                table = layui.table,
                form = layui.form,
                $ = layui.jquery;

            $.ajax({
                url:'${base}/util/findC9',
                type:'GET',
                dataType:'json',
                success:function (data) {
                    var c9s = data.c9;
                    var option = "";
                    for(var i = 0;i<c9s.length;i++) {
                        option += "<option value='"+c9s[i].grop+"'>"+c9s[i].dsca+"</option>";
                    }
                    $("#team").append(option);
                    form.render();
                },
                error:function (kj) {
                    alert("发生错误,错误码为:"+kj.status);
                }
            });

            form.on("select(team)",function (data) {
                console.log(data);
            });

            form.on("submit(search)",function (data) {
                var infor = data.field;
                console.log(infor);
                table.reload("manage",{
                    where:{
                        key:infor.msg
                    }
                });
                return false;
            });

            table.on('tool(manage)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么',{offset:'100px'},function(index){
                        $.ajax({
                           type:'POST',
                            url:'${base}/user/deleteUser',
                            data:{
                                'usid':data.USID
                            },
                            dataType:'json',
                            success:function (res) {
                                if(res.code === 1){
                                    layer.alert(res.msg);
                                    obj.del();
                                    layer.close(index);
                                }else{
                                    layer.alert(res.msg);
                                }
                            },
                            error:function (kj) {
                                layer.alert("发生错误:"+kj.status);
                            }
                        });
                    });
                }
            });

            $(".delete-btn").on("click",function () {
                var check = table.checkStatus('manage');
                var data = check.data;
                $.ajax({
                    type:'POST',
                    url:'${base}/user/deleteUser',
                    data:{
                        list:data
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            table.reload("manage")
                        }
                        return layer.msg(res.msg);
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">用户管理</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
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
                <a href="${base}/user/insert" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{height:'full-400',url:'${base}/user/queryAllUser',page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'USID',width:150,sort:true}">用户编号</th>
            <th lay-data="{field:'DSCA',width:150}">名称</th>
            <th lay-data="{field:'PAWD',width:150}">密码</th>
            <th lay-data="{field:'DEPTNAM',width:150}">部门</th>
            <th lay-data="{field:'ACCONAM',width:150}">科室</th>
            <th lay-data="{field:'JWWJ',width:150}">岗位</th>
            <th lay-data="{field:'GROPNAM',width:150}">团队</th>
            <th lay-data="{field:'COMPNAM',width:150}">公司</th>
            <th lay-data="{width:200,templet:'#roleTpl'}">角色</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:300, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="roleTpl">
        <a href="./userRole?usid={{d.USID}}" class="layui-table-link">编辑</a>
    </script>
    <div class="layui-hide" id="operate">
        <a href="./manageModify?usid={{d.USID}}" class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-mini layui-bg-black" lay-event="del">删除</a>
    </div>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
