<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/15
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>评分显示</title>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(['element','form','jquery'],function () {
            var element = layui.element,$ = layui.jquery,form = layui.form;
            var ptno = $('#ptno').val();
            $.ajax({
                url: '${base}/user/queryUserMarkGrade',
                data:{
                    ptno:ptno
                },
                type:'POST',
                dataType:'json',
                success:function (res) {
                    console.log(res);
                    var data = res.data;
                    for(var i = 0;i<data.length;i++){
                        var rule = data[i].cbase011VO;
                        var content = "<div class='layui-colla-item'>"+
                            "<h2 class='layui-colla-title'>"+rule.dsca+"(分项占比:"+rule.pjjp+"%;基础分:"+rule.cons+")</h2>"+
                            "<div class='layui-colla-content layui-show'>"+
                            "<span>"+rule.deti+"</span><hr>"+
                            "<div class='layui-form-item'>"+
                            "<div class='layui-input-inline'>" +
                            "<label class='layui-form-label'>"+data[i].cbase000VO.DSCA+':'+"</lable>"+
                            "</div>"+
                            "<div class='layui-input-inline'>" +
                            "<input type='text' value='"+data[i].cons+"' readonly>"+
                            "</div>"+
                            "<div class='layui-input-inline'>" +
                            "<input type='text' value='"+checkString(data[i].remk)+"' readonly>"+
                            "</div>"+
                            "</div>"+
                            "</div>"+
                            "</div>";
                        $(".layui-collapse").append(content);
                    }
                    form.render();
                    element.init();
                },
                error:function (kellyJ) {
                    alert("发生错误:"+kellyJ.status)
                }
            });
        });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">我的</cite></a>
        <a href="./eva"><cite style="cursor: pointer;">个人评分管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">个人评分</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <span>月份:2017-8</span>
    <hr/>
    <form class="layui-form">
        <input type="hidden" value="${obj}" id="ptno"/>
        <div class="layui-collapse">
        </div>
        <br><br><br><br><br><br><br><br>
    </form>
</div>
</body>
</html>
