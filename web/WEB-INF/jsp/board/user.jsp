<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/8
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>看板</title>
    <script type="text/javascript" src="../static/js/startScore.js"></script>
    <link rel="stylesheet" href="../static/css/startScore.css" media="all">
    <c:import url="../../static1.html"/>
</head>
<script language="JavaScript">
    layui.use(['jquery','element','table','laydate','layer','form'],function () {
        var $ = layui.jquery,element = layui.element,table = layui.table,laydate = layui.laydate,
            layer = layui.layer,form = layui.form,msg = "",choose = "",ptno = "";

        scoreFun($("#score"),{
            fen_d:22,//每一个a的宽度
            ScoreGrade:5//a的个数 10或者
        });

        form.on("submit(search)",function (data) {
            var infor = data.field;
            ptno = $("select#ptno option:selected").val();
            choose = $("input[name='choose']:checked").val();
            getCount();
            table.reload("manage",{
                where:{
                    key:infor.msg,
                    ptno:ptno,
                    sta:choose
                }
            });
            return false;
        });

        form.on("select(ptno)",function (data) {
            msg = $("#msg").val();
            choose = $("input[name='choose']:checked").val();
            getCount();
            table.reload('manage',{
                where:{
                    ptno:data.value,
                    key:msg,
                    sta:choose
                }
            });

        });

        table.reload("manage",{
            where:{
                sta:7
            }
        });

        form.on('radio',function (data) {
            var value = data.value;
            if(value === '5'){
                $("#tPass").show();
                $("#tReturn").show();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '6'){
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").show();
                $("#rReturn").show();
            }else if(value === '7'){
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '9'){
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }
            msg = $("#msg").val();
            ptno = $("select#ptno option:selected").val();
            table.reload("manage",{
                where:{
                    sta:value,
                    key:msg,
                    ptno:ptno
                }
            });
        });

        form.on('submit(tPass)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,6,"执行测试通过行为",null,null);
        });

        form.on('submit(tReturn)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,8,"执行测试不通过行为",null);
        });

        form.on('submit(rPass)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.open({
                type:1,
                title:'请选择等级',
                area:'20%',
                content:$("#score"),
                btn:['确认'],
                anim:4,
                offset:'100px',
                yes:function () {
                    var value = $(".stage").text();
                    if (value === null || value === "") {
                        layer.tips('请选择等级','#score');
                    } else {
                        operate(data,11,"执行校验通过行为",value-1);
                        layer.closeAll();
                    }
                }
            });
        });

        form.on('submit(rReturn)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.prompt({title:'请输入备注信息！',formType:2,offset:'100px'},function (remk,index) {
                operate(data,10,remk,null);
                layer.close(index);
            });
        });

        function operate(data,code,remk,stage){
            $.ajax({
                type:'POST',
                url:'${base}/task/updateSta1',
                data:{
                    list:data,
                    operate:code,
                    remk:remk,
                    stag:stage
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        getCount();
                        table.reload("manage");
                    }
                    return layer.msg(res.msg);
                },
                error:function (kellyj) {
                    layer.alert("发生错误,错误码为:"+kellyj.status);
                }
            });
        }

        table.on('tool(manage)',function (obj) {
            var data = obj.data;
            if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:'../task/showTask?taid='+data.taid,
                    area:['90%','80%'],
                    title:'任务',
                    offset:'10px'
                });
            }
        });

        $(document).ready(function () {
            getCount();

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
                    return layer.msg("发生错误,错误码:"+kellyj.status);
                }
            });
        });

        function  getCount() {
            msg = $("#msg").val();
            ptno = $("select#ptno option:selected").val();
            $.ajax({
                type:'POST',
                url:'${base}/util/getKSIDCount',
                data:{
                    key:msg,
                    ptno:ptno
                },
                dataType:'json',
                success:function (res) {
                    $("#test").text(res.test);
                    $("#check").text(+res.check);
                    $("#done").text(res.done);
                    $("#not").text(res.not);
                },
                error:function (kellyj) {
                    layer.alert("发生错误,错误码为:"+kellyj.status);
                }
            });
        }

    });
</script>
<body class="x-body">
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">用户看板</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="5" class="layui-form-radio" title="测试中"/><span class="layui-badge" id="test"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="6" class="layui-form-radio" title="验收中"/><span class="layui-badge" id="check"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="7" class="layui-form-radio" title="已完成" checked/><span class="layui-badge" id="done"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="9" class="layui-form-radio" title="未完成"/><span class="layui-badge" id="not"></span>
                </div>
            </div>

            <hr class="layui-bg-cyan"/>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="msg" id="msg" placeholder="请输入查询信息" lay-verify="msg" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-filter="search" lay-submit>查询</button>
                </div>
                <div class="layui-input-inline">
                    <select name="ptno" id="ptno" lay-filter="ptno" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤绩效表</option>
                    </select>
                </div>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="tPass" id="tPass" lay-submit>测试通过</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="tReturn" id="tReturn" lay-submit>测试不通过</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="rPass" id="rPass" lay-submit>验收通过</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="rReturn" id="rReturn" lay-submit>验收不通过</button>
            </div>
        </div>
    </form>
    <div class="layui-box">
        <table class="layui-table" lay-data="{height:'600',url:'${base}/task/userQueryAllTask?ksid=ksid',page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
            <thead>
            <tr>
                <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
                <th lay-data="{field:'perc',width:150,sort:true}">完成度</th>
                <th lay-data="{field:'titl',width:400,toolbar:'#noteTpl'}">标题</th>
                <th lay-data="{field:'synonam',width:200}">系统</th>
                <th lay-data="{field:'ptypnam',width:200}">评分类型</th>
                <th lay-data="{field:'sta1nam',width:200}">状态</th>
                <th lay-data="{field:'punonam',width:200}">任务类型</th>
                <th lay-data="{field:'sta2nam',width:150}">优先级</th>
                <th lay-data="{field:'sta3nam',width:150}">严重程度</th>
                <th lay-data="{field:'adat',width:150,sort:true}">执行时间</th>
                <th lay-data="{field:'pdat',width:150,sort:true}">计划时间</th>
                <th lay-data="{field:'tdat',width:150,sort:true}">测试时间</th>
                <th lay-data="{field:'fdat',width:150,sort:true}">验收时间</th>
                <th lay-data="{field:'cdat',width:150,sort:true}">创建时间</th>
                <th lay-data="{field:'knam',width:200,templet:'#logTpl'}">查看详情</th>
            </tr>
            </thead>
        </table>
    </div>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>
    <script type="text/html" id="logTpl">
        <a href="./log?taid={{d.taid}}" class="layui-table-link">任务日志</a>
    </script>
    <div id="score" class="block clearfix x-center n-display">
        <div class="star_score x-margin" style="margin: auto"></div>
        <p class="x-margin">您的评分：<span class="stage"></span> 级</p>
    </div>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>

