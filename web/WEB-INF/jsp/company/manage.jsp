<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/29
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>公司管理</title>
    <c:import url="../../static1.html"></c:import>
    <script language="JavaScript">
        layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
            var laypage = layui.laypage,
                element = layui.element,
                layer = layui.layer,
                table = layui.table,
                form = layui.form,
                $ = layui.jquery;

            form.on("submit(search)",function (data) {
                var infor = data.field;
                table.reload("company",{
                    where:{
                        key:infor.msg
                    }
                });
                return false;
            });

            form.verify({
                dsca:function (value) {
                    if(value.length === 0){
                        return "请输入公司描述";
                    }
                },
                comp:function (value) {
                    if(value.length === 0){
                        return "请输入公司编号";
                    }
                }
            });

            form.on("submit(set)",function (data) {
                $.ajax({
                    type:'POST',
                    url:'${base}/comp/insertComp',
                    data:data.field,
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            table.reload("company");
                        }
                        return layer.msg(res.msg);
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status);
                    }
                });
                return false;
            });

            table.on('edit(company)',function (obj) {
                $.ajax({
                    type:'POST',
                    url:'${base}/comp/updateComp',
                    data:obj.data,
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 0){
                            table.reload("company");
                        }
                        return layer.msg(res.msg);
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status);
                    }
                });
            });

            table.on('tool(company)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么',{offset:'100px'},function(index){
                        $.ajax({
                            type:'POST',
                            url:'${base}/comp/deleteComp',
                            data:{
                                comp:data.comp
                            },
                            dataType:'json',
                            success:function (res) {
                                if(res.code === 1){
                                    obj.del();
                                    layer.close(index);
                                }
                                layer.msg(res.msg);
                            },
                            error:function (kj) {
                                layer.alert("发生错误:"+kj.status);
                            }
                        });
                    });
                }
            });

            $(".delete-btn").on("click",function () {
                var check = table.checkStatus('company');
                var data = check.data;
                var param = [];
                for(var i = 0;i<data.length;i++){
                    param[i] = data[i].comp;
                }
                $.ajax({
                    type:'POST',
                    url:'${base}/comp/deleteComp',
                    data:{
                        list:param
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            table.reload("company")
                        }
                        layer.alert(res.msg);
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
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">公司管理</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="comp" id="comp" placeholder="请输入公司编号" lay-verify="comp" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="dsca" id="dsca" placeholder="请输入公司描述" lay-verify="dsca" class="layui-input"/>
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
    <table class="layui-table" lay-data="{height:'full-400',url:'${base}/comp/queryAllComp',page:true,limit:10,limits:[10,15,20,25,30,50],id:'company'}" lay-filter="company">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'comp',width:150,sort:true}">公司编号</th>
            <th lay-data="{field:'dsca',edit:true,width:150}">描述</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-mini layui-bg-black" lay-event="del">删除</a>
    </div>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
