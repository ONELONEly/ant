<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/30
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>需求管理</title>
    <c:import url="../../static1.html"></c:import>
    <script language="JavaScript">
        layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
            var element = layui.element, layer = layui.layer, table = layui.table,
                form = layui.form, $ = layui.jquery,msg = "",ptno = "";

            form.on("submit(search)",function (data) {
                var infor = data.field;
                table.reload("require",{
                    where:{
                        key:infor.msg
                    }
                });
                return false;
            });

            table.on('tool(require)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么',{offset:'100px',anim:1,btn:['确定','再考虑一下']},function(index){
                        $.ajax({
                            type:'POST',
                            url:'./deleteRequire',
                            data:{
                                'taid':data.taid
                            },
                            dataType:'json',
                            success:function (res) {
                                if(res.code === 1){
                                    obj.del();
                                    layer.close(index);
                                }
                                return layer.msg(res.msg);
                            },
                            error:function (kj) {
                                layer.alert("发生错误:"+kj.status);
                            }
                        });
                    });
                }else if(obj.event === 'show'){
                    layer.open({
                        type:2,
                        content:'../task/showTask?taid='+data.taid,
                        area:['90%','80%'],
                        title:'任务',
                        offset:'10px'
                    });
                }
            });

            $(".delete-btn").on("click",function () {
                var check = table.checkStatus('require');
                var data = check.data;
                var param = {};
                for(var i = 0;i < data.length ;i++){
                    param[i] = data[i].taid;
                }
                $.ajax({
                    type:'POST',
                    url:'./deleteRequire',
                    data:{
                        list:param
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            table.reload("require");
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
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">需求管理</cite></a>
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
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{height:'full-400',url:'./queryAllRequire',initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'require'}" lay-filter="require">
        <thead>
        <tr>
            <th lay-data="{fixed:true,checkbox:true,width:50}"></th>
            <th lay-data="{field:'taid',width:150}">编号</th>
            <th lay-data="{field:'titl',width:1000,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'synonam',width:150}">系统</th>
            <th lay-data="{field:'cdat',width:150,sort:true}">创建时间</th>
            <th lay-data="{fixed:'right',width:200,align:'center',toolbar:'#operate'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>
    <div class="layui-hide" id="operate">
        <a href='../task/edit?taid={{d.taid}}' class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-mini  layui-bg-black" lay-event="del">删除</a>
    </div>
    <br/><br/><br/><br/><br/><br/><br/><br/>
</div>
</body>
</html>

