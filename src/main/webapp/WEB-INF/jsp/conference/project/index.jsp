<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/6/26
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>項目管理</title>
    <c:import url="../../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">項目管理</cite></a>
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
                <a href="./add" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
            </div>
        </div>
    </form>
    <div class="layui-box">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <table class="layui-table" id="project" lay-filter="project">
        </table>
    </div>
    <script type="text/html" id="operate">
        <a  href="./update?project_guid={{d.projectGuid}}" class="layui-btn layui-btn-xs">编辑</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
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
            elem:'#project',
            url:'./loadTableData',
            method:'POST',
            cellMinWidth:200,
            page:true,
            limit:10,
            limits:[10,20,30,40,50],
            initSort:{field:'orderNumber',type:'desc'},
            cols:[[
                {fixed:true,checkbox:true,width:50},
                {field:'title',align:'center',width:'25%',title:'项目标题'},
                {field:'startDateTxt',align:'center',title:'开始时间'},
                {field:'onlineDateTxt',align:'center',title:'上线时间'},
                {field:'stateTxt',align:'center',title:'状态'},
                {field:'orderNumber',align:'center',title:'序列',sort:true},
                {field:'creator',align:'center',title:'创建人'},
                {fixed:'right',align:'center',toolbar:'#operate',title:'操作'}
            ]],
            response:{
                statusCode:0
            }
        });

        table.on('tool(project)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'./deleteData',
                        data:{
                            projectGuid:data.projectGuid
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
            }
        });

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus('project');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].projectGuid;
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
                                table.reload("project");
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
    });
</script>
</body>
</html>
