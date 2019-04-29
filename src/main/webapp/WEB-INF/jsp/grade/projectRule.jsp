<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/14
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>项目规则</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="./index"><cite style="cursor: pointer;">绩效</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">项目规则</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="pjno" id="pjno" lay-verify="required|pjno" lay-filter="pjno" lay-search required>
                        <option value="" class="n-display" disabled selected>请选择添加的规则</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="add" lay-submit>添加</button>
                </div>
                <div class="layui-input-inline">
                    <input type="hidden" name="ptno" id="ptno" value="${obj}"/>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-btn-danger delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{url:'${base}/grade/queryAllProjectRule?ptno=${obj}',initSort: {field:'udat', type:'desc'},id:'rule'}" lay-filter="rule">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50}"></th>
            <th lay-data="{field:'pjno',align:'center',width:150}">编号</th>
            <th lay-data="{field:'dsca',align:'center',width:150}">描述</th>
            <th lay-data="{field:'plsu',align:'center',width:150}">评分占比</th>
            <th lay-data="{field:'pjjp',align:'center',width:150}">分项占比</th>
            <th lay-data="{field:'deti',align:'center',width:150}">评分细则</th>
            <th lay-data="{field:'cons',align:'center',width:150}">基础分数</th>
            <th lay-data="{field:'usid',align:'center',width:150}">创建用户</th>
            <th lay-data="{field:'udat',align:'center',width:150,sort:true}">创建时间</th>
            <th lay-data="{field:'stat',align:'center',width:150}">类型</th>
            <th lay-data="{field:'remk',align:'center',width:150}">备注</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
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

        var ptno = $("#ptno").val();

        form.on("submit(add)",function (data) {
            var infor = data.field;
            $.ajax({
                type:'POST',
                url:'${base}/grade/insertProjectRule',
                data:{
                    'ptno':infor.ptno,
                    "pjno":infor.pjno
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("rule");
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

        table.on('tool(rule)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'${base}/grade/deleteProjectRule',
                        data:{
                            "ptno":ptno,
                            'pjno':data.pjno
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
            var check = table.checkStatus('rule');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].pjno;
            }
            $.ajax({
                type:'POST',
                url:'${base}/grade/deleteProjectRule',
                data:{
                    ptno:ptno,
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("rule")
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
                url:'${base}/util/findC11',
                dataType:'json',
                success:function (res) {
                    var data = res.c11;
                    var option = "<option value='' class='n-display' disabled selected>请选择添加的规则</option>";
                    for(var i = 0;i<data.length;i++){
                        option += "<option value='"+data[i].pjno+"'>"+data[i].dsca+"   ("+data[i].cbase000VO.DSCA+")"+"</option>";
                    }
                    $("#pjno").html(option);
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



