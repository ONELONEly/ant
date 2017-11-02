<%--suppress ALL --%>
<%--suppress JSUnresolvedVariable --%>
<%--suppress ALL --%>
<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/8
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>首页</title>
    <script src="./static/js/echarts.js" charset="utf-8"></script>
    <c:import url="../static.html"/>
    <style>
        li{
            width: 12.5%;
        }
    </style>
</head>
<body>
<div class="x-body">
    <div>
        <div id="left" class="layui-side x-equal" style="margin-top:-20px;">
        </div>
        <div id="right" class="layui-layout-right layui-side x-equal">
        </div>
        <div class="x-titleDiv" style="height: 60px;border-left: none;">
            <ul class="layui-nav" style="background-color: #fff;">
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>未下发</span><span class="layui-badge" id="new"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>已下发</span><span class="layui-badge" id="in1"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>执行中</span><span class="layui-badge" id="in2"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>变更</span><span class="layui-badge" id="in3"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>交付不通过</span><span class="layui-badge" id="in4"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>测试中</span><span class="layui-badge" id="testS"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>验收中</span><span class="layui-badge" id="check"></span></a></li>
                <li class="layui-nav-item"><a href="javaScript:" style="color: #000;text-align: center;"><span>已完成</span><span class="layui-badge" id="done"></span></a></li>
            </ul>
        </div>
    </div>
    <br/><br/><br/><br/><br/>
</div>
</body>
<script language="JavaScript">
    layui.use('jquery',function () {
        var usid = sessionStorage.getItem("usid");
        var $ = layui.jquery;
        var leftEchart = echarts.init(document.getElementById("left"));
        var rightEchart = echarts.init(document.getElementById("right"));
        var leftOption = {
//            title: {
//                text: '成绩汇总'
//            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
//            toolbox: {
//                feature: {
//                    restore: {},
//                    saveAsImage: {}
//                }
//            },
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    detail: {formatter:'{value}%'},
                    data: [{value: 100, name: '本月团队完成统计'}]
                }
            ]
        };

        var rightOption ={
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
//            toolbox: {
//                feature: {
//                    restore: {},
//                    saveAsImage: {}
//                }
//            },
            max:100,
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    detail: {formatter:'{value}%'},
                    data: [{value: 100, name: '本月个人完成统计'}]
                }
            ]
        };

        $.ajax({
            type:'POST',
            url:'${base}/util/getScale',
            dataType:'json',
            success:function (res) {
                leftOption.series[0].data[0].value = res.data.grop;
                rightOption.series[0].data[0].value = res.data.user;

                leftEchart.setOption(leftOption, true);
                rightEchart.setOption(rightOption,true);
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status);
            }
        });

        $.ajax({
            type:'POST',
            url:'${base}/util/getCount',
            data:{
                usid:usid,
                user:"user"
            },
            dataType:'json',
            success:function (res) {
                $("#in1").text(res.put);
                $("#in2").text(res.carry);
                $("#in3").text(res.modify);
                $("#in4").text(res.back);
                $("#new").text(res.new);
                $("#testS").text(res.test);
                $("#check").text(+res.check);
                $("#done").text(res.done);
            },
            error:function (kellyj) {
                layer.alert("发生错误,错误码为:"+kellyj.status);
            }
        });
    });
</script>
</html>
