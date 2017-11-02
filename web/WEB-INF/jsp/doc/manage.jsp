<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/21
  Time: 10:17
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
    <script language="JavaScript">
        layui.use(['form','jquery','table','layer','element'],function () {
            var $ = layui.jquery,form = layui.form,table = layui.table,
                layer = layui.layer,elemnt = layui.element,key='',ctyp='';

            form.on("submit(search)",function (data) {
                var infor = data.field;
                ctyp = $("input[name='ctyp']:checked").val();
                table.reload("manage",{
                    where:{
                        key:infor.msg,
                        ctyp:ctyp
                    }
                });
                return false;
            });

            form.on("select(ctyp)",function (data) {
                key = $("#msg").val();
                table.reload("manage",{
                    where:{
                        key:key,
                        ctyp:data.value
                    }
                })
            });

            table.on('tool(manage)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        $.ajax({
                            type:'POST',
                            url:'${base}/doc/deleteDoc',
                            data:{
                                doid:data.doid
                            },
                            dataType:'json',
                            success:function (res) {
                                if(res.code === 1){
                                    layer.confirm("删除成功",{btn:['确认'],offset:'100px'},function () {
                                        obj.del();
                                        layer.closeAll();
                                    });
                                }else{
                                    layer.alert(res.msg);
                                }
                            },
                            error:function (kj) {
                                layer.alert("发生错误:"+kj.status);
                            }
                        });
                    });
                }else if(obj.event === 'show'){
                    layer.open({
                        type:2,
                        content:['./showDoc?doid='+data.doid],
                        area: ['90%', '70%'],
                        title:'文档',
                        offset:'10px'
                    });
                }
            });

            $(".delete-btn").on("click",function () {
                var check = table.checkStatus('manage');
                var data = check.data;
                $.ajax({
                    type:'POST',
                    url:'${base}/doc/deleteDoc',
                    data:{
                        list:data
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            layer.confirm("删除成功",{btn:['确认'],offset:'100px'},function (index) {
                                table.reload("manage");
                                layer.close(index)
                            });
                        }else{
                            layer.alert(res.msg);
                        }
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
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">文档</cite></a>
        <a href="javascript:location.replace(location.href)" style="cursor: pointer;"><cite>文档管理</cite></a>
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
                    <a href="./insert" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
                </div>
                <div class="layui-input-inline">
                    <select name="ctyp" id="ctyp" lay-filter="ctyp" lay-search="">
                        <option value="" disabled selected>请选择文档类型</option>
                        <option value="1">周报</option>
                        <option value="2">智库</option>
                        <option value="3">公开文档</option>
                        <option value="4">学习文档</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{height:'600',url:'./queryAllDoc',initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'doid',width:150,sort:true}">编号</th>
            <th lay-data="{field:'tilt',width:400,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'cdat',width:150,sort:true}">创建时间</th>
            <th lay-data="{field:'unam',width:150}">创建用户</th>
            <th lay-data="{field:'cnam',width:150}">接收用户</th>
            <th lay-data="{field:'ctypnam',width:150}">类型</th>
            <th lay-data="{field:'statnam',width:150}">权限</th>
            <th lay-data="{field:'sta2nam',width:150}">状态</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
    </script>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-mini" href="./edit?doid={{d.doid}}">编辑</a>
        <a class="layui-btn layui-btn-mini layui-bg-black" lay-event="del">删除</a>
    </div>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
