<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/8/31
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>统计</title>
    <script src="../static/js/echarts.js" charset="utf-8"></script>
    <c:import url="../../static1.html"></c:import>
    <script language="JavaScript">
        layui.use(['form','jquery'],function () {
            var $ = layui.jquery,form = layui.form;
            var ptno = $('#ptno').val();

            var myCharts = echarts.init(document.getElementById("main"));
            var option = {
                title: {
                    text: '成绩汇总'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['月成绩']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data:[]
                },
                series: [
                    {
                        name: '月成绩',
                        type: 'bar',
                        itemStyle:{
                            normal:{
                                color: function (params) {
                                    var colorList = ['#FFFFCC','#CCFFFF','#FFCCCC','#CCCCCC','#99CCFF'];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        data: []
                    }
                ]
            };

            $.ajax({
                type:'POST',
                url:'${base}/user/getTotalScore',
                data:{
                    ptno:ptno
                },
                dataType:'json',
                success:function (res) {
                    var user = res.data.user;
                    var name = option.yAxis.data;
                    var score = option.series[0].data;
                    name[0] = user.USID;
                    score[0] = res.data[user.USID];
                    myCharts.setOption(option);
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status);
                }
            });
        });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite>首页</cite></a>
        <a href="javascript:"><cite>个人</cite></a>
        <a href="./eva"><cite>个人评分管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite>统计</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <span>月份:2017-8</span>
    <hr class="layui-bg-orange"/>
    <form class="layui-form">
        <input type="hidden" value="${obj.ptno}" id="ptno"/>
    </form>
<div id="main" style="width: 100%;height:400px;"></div>
</div>
</body>
</html>
