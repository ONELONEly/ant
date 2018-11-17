<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/3
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>OKR管理</title>
    <kellyj:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite>我的</cite></a>
        <a href="javascript:location.replace(location.href)"><cite>个人OKR</cite></a>
        <a href="javascript:location.replace(location.href)" class="layui-btn layui-btn-radius layui-btn-sm l-refresh" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入查询信息" name="msg" id="msg"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" id="search">查询</button>
                </div>
                <div class="layui-input-inline">
                    <a href="./insert?isManager=false" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
                </div>
            </div>
        </div>
    </form>
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="mdat" id="mdat" placeholder="日期过滤" lay-verify="mdat" autocomplete="off"
                               class="layui-input"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <button class="layui-btn post-btn"><i class="layui-icon">&#xe6af;</i>提交</button>
    </div>
    <table class="layui-hide" id="okr" lay-filter="okr">
    </table>

    <script type="text/html" id="read">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.ANAM}}({{d.ACCONAM}})</a>
    </script>

    <script type="text/html" id="status">
        {{# if(d.stat === 0){ }}
        <span style="color: #000;">未提交</span>
        {{# }else if(d.stat === 1){ }}
        <span style="color: #008000;">已提交</span>
        {{# }else{ }}
        <span style="color: #f00;">驳回</span>
        {{# } }}
    </script>

    <script type="text/html" id="grade">
        {{# if(d.GRADE == undefined){ }}
        <span style="color: #000;">未评分</span>
        {{# }else{ }}
        <span style="color: #008000;">{{d.GRADE}}</span>
        {{# } }}
    </script>

    <script type="text/html" id="operate">
        {{# if(d.stat !== 1){ }}
        {{# } }}
        <a href="./edit?okid={{d.OKID}}&isManager=false" class="layui-btn layui-btn-xs">编辑</a>
        <a href="./outPutOkr?okid={{d.OKID}}" class="layui-btn layui-btn-xs">导出报表</a>
    </script>
    <script type="text/html" id="mdatTpl">
        <span>{{d.MDAT}}({{d.TYPENAM}})</span>
    </script>
</div>
<script language="JavaScript">
    layui.use(['table','element','form','laydate'],function () {
        var table = layui.table,element = layui.element,form = layui.form,laydate = layui.laydate;

        var start = {
            elem: '#mdat',
            type: 'month',
            done: function (value,obj) {
                table.reload('okr',{
                    where:{
                        mdat:value,
                        msg:$("#msg").val()
                    }
                });
            }
        };

        laydate.render(start);

        table.render({
            elem:'#okr',
            url:'./uQueryAllOKR',
            cellMinWidth:100,
            page:true,
            limit:10,
            limits:[10,20,30,40,50],
            cols:[[
                {fixed:true,checkbox:true},
                {field:'ANAM',title:'管理对象',align:'center',toolbar:'#read'},
                {field:'BNAM',title:'直接领导',align:'center'},
                {field:'MDAT',title:'管理周期',align:'center',templet:'#mdatTpl'},
                {field:'GRADE',title:'成绩',align:'center',templet:'#grade'},
                {field:'stat',title:'状态',align:'center',templet:'#status'},
                {fixed:'right',title:'操作',align:'center',toolbar:'#operate'}
                ]],
            response:{
                statusCode:1
            },
            done: function (res, curr, count) {
                var data = res.data;
                var allck = true;
                for (var item in data) {
                    if (data[item].stat === 1) {//关键点如果data中score包含57那么就不能全选
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
                    if (res.data[i].stat === 1) {//关键点如果当前行数据中score包含57那么就不可选
                        $(this).attr("disabled", 'disabled').removeAttr("checked");
                        form.render('checkbox');
                    }
                    i++;
                });
                i = 0;
                $(".layui-table-fixed.layui-table-fixed-l").find(".layui-table-body").find("input[name='layTableCheckbox']").each(function () {
                    if (res.data[i].stat === 1) {//关键点如果当前行数据中score包含57那么就不可选
                        $(this).attr("disabled", 'disabled').removeAttr("checked");
                        form.render('checkbox');
                    }
                    i++;
                });
            }
        });

        table.on("tool(okr)",function (obj) {
            if(obj.event === "show"){
                layer.open({
                   type:2,
                   content:'./read?okid='+obj.data.OKID,
                   area:['90%','80%'],
                    title:'OKR管理表',
                    offset:'10px'
                });
            }
        });

        $("#search").click(function () {
            table.reload('okr',{
                where:{
                    mdat:$('#mdat').val(),
                    msg:$("#msg").val()
                }
            });
            return false;
        });

        $(".delete-btn").click(function () {
            var choose = table.checkStatus("okr");
            var data = choose.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].OKID;
            }
            $.ajax({
                type:'POST',
                url:'./delete',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("okr");
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kellyj) {
                    layer.alert("发生错误,错误码为:"+kellyj.status);
                }
            });
        });

        $(".post-btn").click(function () {
            var choose = table.checkStatus("okr");
            var data = choose.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].OKID;
            }
            $.ajax({
                type:'POST',
                url:'./pushToLeader',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        table.reload("okr");
                    }
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kellyj) {
                    layer.alert("发生错误,错误码为:"+kellyj.status);
                }
            });
        });
    });
</script>
</body>
</html>
