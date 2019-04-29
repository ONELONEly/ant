<%--suppress ALL --%>
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
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">需求管理</cite></a>
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
            </div>
        </div>
    </form>
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="stat" id="stat" lay-filter="stat" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤状态</option>
                        <option value="0">下发</option>
                        <option value="1">执行</option>
                        <option value="2">驳回</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" id="require" lay-filter="require">
    </table>
    <script type="text/html" id="statTpl">
        {{# if(d.statnam === '下发'){ }}
        <span style="color:#00f;">{{d.statnam}}</span>
        {{# }else if(d.statnam === '执行'){ }}
        <span style="color: #008000">{{d.statnam}}</span>
        {{# }else if(d.statnam === '驳回'){ }}
        <span style="color: #f00;">{{d.statnam}}</span>
        {{# } }}
    </script>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>
    <div class="layui-hide" id="operate">
        {{# if(d.statnam !== '执行'){ }}
            <a href='../task/edit?taid={{d.raid}}&state=require' class="layui-btn layui-btn-xs" lay-event="edit">转成任务</a>
            <a class="layui-btn layui-btn-xs  layui-bg-black" lay-event="del">删除</a>
        {{# } }}
    </div>

</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var element = layui.element, layer = layui.layer, table = layui.table,
            form = layui.form, $ = layui.jquery,msg = "",ptno = "";



        table.render({
            elem:'#require',
            url:'./queryAllRequire',
            cellMinWidth:100,
            page:true,
            limit:10,
            limits:[10,20,30,40,50],
            cols:[[
                {fixed:true,checkbox:true,width:50,},
                {field:'raid',align:'center',title:'编号',width:150,},
                {field:'titl',align:'center',width:350,title:'标题',toolbar:'#noteTpl'},
                {field:'synonam',align:'center',width:150,title:'系统'},
                {field:'cnam',align:'center',width:150,title:'接收人'},
                {field:'sta2nam',align:'center',width:150,title:'紧急状态'},
                {field:'sta3nam',align:'center',width:150,title:'重要程度'},
                {field:'statnam',align:'center',width:150,title:'状态',templet:'#statTpl'},
                {field:'cdat',align:'center',width:300,title:'创建时间'},
                {field:'ydat',align:'center',width:300,title:'期望时间'},
                {fixed:'right',align:'center',width:200,toolbar:'#operate'}
            ]],
            response:{
                statusCode:0
            },
            done: function (res, curr, count) {
            var data = res.data;
            var allck = true;
            for (var item in data) {
                if(data[item].statnam === '执行') {
                    allck = false;
                }
            }
            if (!allck) {
                $(".layui-table-header").find("input[name = 'layTableCheckbox'][lay-filter='layTableAllChoose']").each(function () {
                    $(this).attr("disabled", 'disabled').next().removeClass("layui-form-checked");
                    form.render('checkbox');
                });
            }
            var i = 0;
            $(".layui-table-body.layui-table-main").find("input[name='layTableCheckbox']").each(function () {
                if(data[i].statnam === '执行') {
                    $(this).attr("disabled", 'disabled').removeAttr("checked");
                    form.render('checkbox');
                }
                i++;
            });
            i = 0;
            $(".layui-table-fixed.layui-table-fixed-l").find(".layui-table-body").find("input[name='layTableCheckbox']").each(function () {
                if(data[i].statnam === '执行') {
                    $(this).attr("disabled", 'disabled').removeAttr("checked");
                    form.render('checkbox');
                }
                i++;
            });
        }
        });

        form.on("submit(search)",function (data) {
            var infor = data.field;
            table.reload("require",{
                where:{
                    key:infor.msg,
                    stat:$("#stat option:selected").val()
                },
                page:{
                    curr:1
                }
            });
            return false;
        });

        form.on("select(stat)",function (data) {
            table.reload("require",{
                where:{
                    stat:data.value,
                    key:$("#msg").val()
                },
                page:{
                    curr:1
                }
            })
        });

        table.on('tool(require)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么',{offset:'10px',anim:1,btn:['确定','再考虑一下']},function(index){
                    $.ajax({
                        type:'POST',
                        url:'./deleteRequire',
                        data:{
                            raid:data.raid
                        },
                        dataType:'json',
                        success:function (res) {
                            if(res.code === 1){
                                obj.del();
                                layer.close(index);
                            }
                            return layer.msg(res.msg,{offset:'10px'});
                        },
                        error:function (kj) {
                            layer.alert("发生错误:"+kj.status,{offset:'10px'});
                        }
                    });
                });
            }else if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:'../task/showTask?taid='+data.raid+"&state=require",
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
                param[i] = data[i].raid;
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
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });
    });
</script>
</body>
</html>
