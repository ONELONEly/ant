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
    <title>任务日志</title>
    <c:import url="../../static1.html"/>
</head>
<script language="JavaScript">
    layui.use(['layer','jquery'],function () {
       var layer = layui.layer,$ = layui.jquery;
       $.ajax({
           type:'POST',
           url:'${base}/board/queryAllLog',
           data:{
               taid:$("#taid").val()
           },
           dataType:'json',
           success:function (res) {
               if(res.code === 1){
                   var data = res.data;
                   for(var i = 0;i<data.length;i++){
                       var option = "<li class='layui-timeline-item'>"+
                           "<i class='layui-icon layui-timeline-axis'>&#xe63f;</i>"+
                           "<div class='layui-timeline-content layui-text'>"+
                           "<div class='layui-timeline-title'>"+data[i].edat+','+data[i].remk+"</div>"+
                           "</div>"+
                           "</li>";
                       var end = "<li class='layui-timeline-item'>"+
                       "<i class='layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis'></i>"+
                       "<div class='layui-timeline-content layui-text'>"+
                       "<div class='layui-timeline-title'>"+'.'+"</div>"+
                       "</div>"+
                       "</li>";
                       $(".layui-timeline").append(option);
                       $(".layui-timeline").append(end);
                   }
               }else {
                   layer.alert(res.msg);
               }
           },
           error:function (kellyj) {
               layer.alert("发生错误,错误码为:"+kellyj.status);
           }
       });
    });
</script>
<body class="x-body">
<input type="hidden" id="taid" value="${obj.taid}"/>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="${base}/board/user"><cite style="cursor: pointer;">用户面板</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">任务日志</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title"></div>
            </div>
        </li>
    </ul>
</div>
<br/><br/><br/><br/><br/><br/>
</body>
</html>
