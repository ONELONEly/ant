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
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">统计</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">月度排名</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form layui-form-pane">
        <div class="layui-input-inline">
            <select name="pdat" id="pdat" lay-filter="pdat" lay-search="">
                <option value="" class="n-select" disabled selected>过滤月份</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="grop" id="grop" lay-filter="grop" lay-search="">
                <option value="" class="n-select" disabled selected>过滤团队</option>
            </select>
        </div>
    </form>
    <hr class="layui-bg-orange"/>
    <div id="main" style="width: 100%;height:100px;"></div>
</div>
<script language="JavaScript">
    layui.use(['form','jquery'],function () {
        var $ = layui.jquery,form = layui.form,myCharts;

        $.ajax({
            type:'GET',
            url:'${base}/util/findPdat',
            dataType:'json',
            success:function (res) {
                var pdat = res.pdat,grop = res.grop,pOption = "",gOption = "";
                for (var k = 0; k < pdat.length; k++) {
                    pOption += "<option value='" + pdat[k] + "'>" + pdat[k] + "</option>";
                }
                for (var g = 0; g < grop.length; g++) {
                    gOption += "<option value='" + grop[g].id + "'>" + grop[g].dsca + "</option>";
                }
                $('#pdat').append(pOption);
                $('#grop').append(gOption);
                form.render();
            },
            error:function (kellyj) {
                return layer.msg("发生错误,错误码:"+kellyj.status,{offset:'10px'});
            }
        });

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
                    label : {
                        normal : {
                            show : true,
                            color: 'black'
                        }
                    },
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
            getScore(data.value,$("select#grop option:selected").val());
        });
        form.on('select(grop)',function (data) {
            getScore($("select#pdat option:selected").val(),data.value);
        });

        function getScore(pdat,grop) {
            $.ajax({
                type:'POST',
                url:'${base}/grade/getRank',
                data:{
                    pdat:pdat,
                    grop:grop
                },
                dataType:'json',
                success:function (res) {
                    var user = res;
                    $("#main").height(user.length*150);
                    dispose();
                    myCharts = echarts.init(document.getElementById("main"));
                    option.yAxis.data = [];
                    option.series[0].data = [];
                    var name = option.yAxis.data;
                    var score = option.series[0].data;
                    for(var i = 0;i<user.length;i++){
                        name[i] =user[i].dsca;
                        score[i] =user[i].score;
                    }
                    myCharts.setOption(option);
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        }
        
        function dispose() {
            if(myCharts != null && myCharts != "" && myCharts !=undefined){
                myCharts.dispose();
            }
        }
    });
</script>
</body>
</html>

