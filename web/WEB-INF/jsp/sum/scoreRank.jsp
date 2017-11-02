<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/26
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>成绩排名</title>
    <script src="../static/js/echarts.js" charset="utf-8"></script>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(['form','jquery'],function () {
            var $ = layui.jquery,form = layui.form;

            $.ajax({
                type:'GET',
                url:'${base}/util/findPdat',
                dataType:'json',
                success:function (res) {
                    var pdat = res.pdat,pOption = "";
                    for (var k = 0; k < pdat.length; k++) {
                        pOption += "<option value='" + pdat[k] + "'>" + pdat[k] + "</option>";
                    }
                    $('#pdat').append(pOption);
                    form.render();
                },
                error:function (kellyj) {
                    return layer.msg("发生错误,错误码:"+kellyj.status);
                }
            });

            var myCharts = echarts.init(document.getElementById("main"));
            var option = {
                title: {
                    text: '成绩排名'
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
                                    return colorList[params.dataIndex%4];
                                }
                            }
                        },
                        data: []
                    }
                ]
            };
            getScore(null);

            form.on('select(pdat)',function (data) {
               getScore(data.value);
            });

            function getScore(pdat) {
                $.ajax({
                    type:'POST',
                    url:'${base}/grade/getRank',
                    data:{
                        pdat:pdat
                    },
                    dataType:'json',
                    success:function (res) {
                        var user = res;
                        var name = option.yAxis.data;
                        var score = option.series[0].data;
                        for(var i = 0;i<user.length;i++){
                            name[i] =user[i].dsca;
                            score[i] =user[i].score;
                        }
                        myCharts.setOption(option);
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status);
                    }
                });
            }
        });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">统计</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">月度排名</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form layui-form-pane">
        <div class="layui-input-inline">
            <select name="pdat" id="pdat" lay-filter="pdat" lay-search="">
                <option value="" class="n-select" disabled selected>过滤月份</option>
            </select>
        </div>
    </form>
    <hr class="layui-bg-orange"/>
    <div id="main" style="width: 100%;height:${obj*150}px;"></div>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>

