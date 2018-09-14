<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/28
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人任务</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">任务管理</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
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
                <div class="layui-input-inline">
                    <a href="${base}/task/insert?type=in" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
                </div>
                <div class="layui-input-inline">
                    <select name="ptno" id="ptno" lay-filter="ptno" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤绩效表</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <button class="layui-btn layui-bg-black copy-btn"><i class="layui-icon">&#xe6af;</i>复制</button>
        <button class="layui-btn export-btn"><i class="layui-icon">&#xe6af;</i>导出Excel</button>
    </div>
    <table class="layui-table" lay-data="{url:'${base}/task/userQueryAllTask?ksid=usid',initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50}"></th><%--,fixed:true--%>
            <th lay-data="{field:'taid',align:'center',width:150,sort:true,toolbar:'#taskTpl'}">编号</th>
            <th lay-data="{field:'titl',align:'center',width:350,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'ptnonam',align:'center',width:350}">绩效表主题</th>
            <th lay-data="{field:'synonam',align:'center',width:250}">系统</th>
            <th lay-data="{field:'unam',align:'center',width:150}">创建人</th>
            <th lay-data="{field:'cnam',align:'center',width:150}">派发给</th>
            <th lay-data="{field:'ptypnam',align:'center',width:150}">评分类型</th>
            <th lay-data="{field:'sta1nam',align:'center',width:150}">状态</th>
            <th lay-data="{field:'punonam',align:'center',width:150}">任务类型</th>
            <th lay-data="{field:'knam',align:'center',width:150}">关键用户</th>
            <th lay-data="{field:'sta2nam',align:'center',width:150}">优先级</th>
            <th lay-data="{field:'sta3nam',align:'center',width:150}">严重程度</th>
            <th lay-data="{field:'adat',align:'center',width:150,sort:true}">执行时间</th>
            <th lay-data="{field:'pdat',align:'center',width:150,sort:true}">计划时间</th>
            <th lay-data="{field:'tdat',align:'center',width:150,sort:true}">测试时间</th>
            <th lay-data="{field:'fdat',align:'center',width:150,sort:true}">验收时间</th>
            <th lay-data="{field:'cdat',align:'center',width:150,sort:true}">创建时间</th>
            <th lay-data="{field:'fahh',align:'center',width:150,sort:true}">工时</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>
    <script type="text/html" id="taskTpl">
        <a href="javascript:" class="layui-table-link" lay-event="showTask">{{d.taid}}</a>
    </script>
    <div class="layui-hide" id="operate">
        <a href='../task/edit?taid={{d.taid}}&type=in' class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laypage = layui.laypage, element = layui.element, layer = layui.layer,
            table = layui.table, form = layui.form, $ = layui.jquery,msg = "",ptno ="";

        $(document).ready(function () {
            $.ajax({
                type:'GET',
                url:'${base}/util/findT1',
                dataType:'json',
                success:function (res) {
                    var type = res.t1,tOption = "";
                    for (var m = 0; m < type.length; m++) {
                        tOption += "<option value='" + type[m].ptno + "'>" + type[m].dsca + "</option>";
                    }
                    $("#ptno").append(tOption);
                    form.render();
                },
                error:function (kellyj) {
                    return layer.msg("发生错误,错误码:"+kellyj.status,{offset:'10px'});
                }
            });
        });

        form.on("submit(search)",function (data) {
            var infor = data.field;
            ptno = $("select#ptno option:selected").val();
            table.reload("manage",{
                where:{
                    key:infor.msg,
                    ptno:ptno
                }
            });
            return false;
        });

        form.on("select(ptno)",function (data) {
            msg = $("#msg").val();
            table.reload('manage',{
                where:{
                    ptno:data.value,
                    key:msg
                }
            })
        });

        table.on('tool(manage)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        type:'POST',
                        url:'${base}/task/deleteTask',
                        data:{
                            'taid':data.taid
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
            }else if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:'../task/showTask?taid='+data.taid,
                    area:['90%','80%'],
                    title:'任务',
                    offset:'10px'
                });
            }else if(obj.event === 'showTask'){
                layer.open({
                    type:2,
                    content:'../task/showTask_all?taid='+data.taid,
                    area:['90%','80%'],
                    title:'任务',
                    offset:'10px'
                });
            }
        });

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus('manage');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].taid;
            }
            $.ajax({
                type:'POST',
                url:'${base}/task/deleteTask',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.alert(res.msg);
                        table.reload("manage")
                    }else{
                        layer.alert(res.msg);
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status);
                }
            });
        });

        $(".copy-btn").on('click',function () {
            var check = table.checkStatus('manage');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].taid;
            }
            $.ajax({
                type:'POST',
                url:'${base}/task/copyTask',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm(res.msg,function (index) {
                            layer.close(index);
                            table.reload("manage");
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

        $(".export-btn").on('click',function () {
            var check = table.checkStatus('manage');
            var data = check.data;
            var param = [];
            for(var i = 0;i<data.length;i++){
                param[i] = data[i].taid;
            }
            console.log(param);
            var url = '${base}/task/printTask?list=' + param;
            $("<form action='"+url+"' method='post'></form>").appendTo("body").submit().remove();
        });
    });
</script>
</body>
</html>

