<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/13
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>任务管理</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">任务规则</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="msg" id="msg" placeholder="请输入规则描述" lay-verify="msg" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit>查询</button>
                </div>
                <div class="layui-input-inline">
                    <select name="usid" id="usid" lay-verify="usid" lay-filter="usid" lay-search>
                        <option value="" class="n-display" disabled selected>请选择过滤用户</option>
                    </select>
                </div>
                <a href="${base}/task/insertRule" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <button class="layui-btn copy-btn"><i class="layui-icon">&#xe640;</i>批量复制</button>
    </div>
    <table class="layui-table" lay-data="{url:'${base}/task/queryAllRule',initSort:{field:'udat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'rule'}" lay-filter="rule">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50}"></th>
            <th lay-data="{field:'pjno',align:'center',width:150}">编号</th>
            <th lay-data="{field:'dsca',edit:'true',align:'center',width:150}">描述</th>
            <th lay-data="{field:'plsu',edit:'true',align:'center',width:150}">评分占比</th>
            <th lay-data="{field:'pjjp',edit:'true',align:'center',width:150}">分项占比</th>
            <th lay-data="{field:'deti',edit:'true',align:'center',width:150}">评分细则</th>
            <th lay-data="{field:'cons',edit:'true',align:'center',width:150}">基础分数</th>
            <th lay-data="{field:'usid',align:'center',width:150}">创建用户</th>
            <th lay-data="{field:'udat',align:'center',width:150,sort:true}">创建时间</th>
            <th lay-data="{field:'stat',edit:'true',align:'center',width:150}">类型</th>
            <th lay-data="{field:'stat',edit:'true',align:'center',width:150,toolbar:'#statType'}">类型</th>
            <th lay-data="{field:'type',edit:'true',align:'center',width:150}">属性</th>
            <th lay-data="{field:'type',edit:'true',align:'center',width:150,toolbar:'#typeType'}">属性</th>
            <th lay-data="{field:'remk',edit:'true',align:'center',width:150}">备注</th>
            <th lay-data="{field:'usid',edit:'true',align:'center',width:150}">创建用户</th>
            <th lay-data="{fixed:'right',align:'center',width:150,templet:'#opcoTpl',align:'center'}">评分细节</th>
            <th lay-data="{fixed:'right',align:'center',width:150,toolbar:'#operate',align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="opcoTpl">
        <a href="${base}/task/ruleScore?pjno={{d.pjno}}" class="layui-table-link">查看</a>
    </script>
    <script type="text/html" id="statType">
        {{# if(d.stat == 0){ }}
        <span>手动任务</span>
        {{# }else if( d.stat == 1){ }}
        <span>自动任务</span>
        {{# }else{ }}
        <span>错误数据</span>
        {{# } }}
    </script>
    <script type="text/html" id="typeType">
        {{# if(d.type == 0){ }}
        <span>临时任务</span>
        {{# }else if( d.type == 1){ }}
        <span>计划任务</span>
        {{# }else{ }}
        <span>错误数据</span>
        {{# } }}
    </script>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laypage = layui.laypage,
            element = layui.element,
            layer = layui.layer,
            table = layui.table,
            form = layui.form,
            $ = layui.jquery,
            rule = {
                key:'',
                usid:''
            },
            allField = JSON.parse(sessionStorage.getItem("moduleAllField"));
        console.log(allField)

        if (allField != null){

            $("#msg").val(allField.task.rule.key)
            $("#usid").val(allField.task.rule.usid)

            table.reload("rule",{
                where:allField.task.rule,
                page:{
                    curr:1
                }
            });
        }

        form.on("submit(search)",function (data) {
            var infor = data.field;
            rule = {
                key:infor.msg,
                usid:infor.usid
            }
            table.reload("rule",{
                where:rule,
                page:{
                    curr:1
                }
            });
            syncField()
            return false;
        });

        form.on("select(usid)",function (data) {
            rule = {
                key:$("#msg").val(),
                usid:data.value
            }
            table.reload("rule",{
                where:rule,
                page:{
                    curr:1
                }
            });
            syncField()
            return false;
        })

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
                if (allField != null) {
                    $("#usid").val(allField.task.rule.usid);
                }
                form.render();
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });

        table.on('edit(rule)',function (obj) {
            var infor = obj.data;
            $.ajax({
                type:'POST',
                url:'${base}/task/updateRule',
                data:infor,
                dataType:'json',
                success:function (res) {
                    if(res.code === 0){
                        table.reload('rule');
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });

        table.on('tool(rule)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px'},function(index){
                    $.ajax({
                        type:'POST',
                        url:'${base}/task/deleteRule',
                        data:{
                            'pjno':data.pjno
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
            } else if(obj.event === 'edit'){
                layer.alert('编辑行：<br>'+ JSON.stringify(data),{offset:'10px'})
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
                url:'${base}/task/deleteRule',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.alert(res.msg,{offset:'10px'});
                        table.reload("rule")
                    }else{
                        layer.alert(res.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });

        $(".copy-btn").on("click",function () {
            var check = table.checkStatus('rule');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].pjno;
            }
            $.ajax({
                type:'POST',
                url:'${base}/task/copyRule',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.alert(res.msg,{offset:'10px'});
                        table.reload("rule")
                    }else{
                        layer.alert(res.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });

        function syncField(){
            if(allField == null){
                allField = moduleAllField;
            }
            allField.task.rule = rule;
            sessionStorage.setItem("moduleAllField",JSON.stringify(allField));
        }

    });
</script>
</body>
</html>

