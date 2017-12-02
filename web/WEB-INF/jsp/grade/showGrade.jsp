<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/15
  Time: 18:28
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
</head>
<script language="JavaScript">
    layui.use(['element','form','jquery'],function () {
        var element = layui.element,$ = layui.jquery,form = layui.form;
        var ptno = $('#ptno').val(),grop = $("#grop").val();
        $.ajax({
            url: '${base}/grade/queryGropMarkGrade',
            data:{
                grop:grop,
                ptno:ptno
            },
            type:'POST',
            dataType:'json',
            success:function (res) {
                var data = res.data,rule = data.rule;
                for(var i = 0;i<rule.length;i++){
                    var option = data[rule[i].pjno];
                    var content = "<div class='layui-colla-item'>"+
                        "<h2 class='layui-colla-title'>"+rule[i].dsca+"(分项占比:"+rule[i].pjjp+"%;基础分:"+rule[i].cons+")</h2>"+
                        "<div class='layui-colla-content layui-show'>"+
                        "<span>"+rule[i].deti+"</span><hr>"+ getContent(option)+
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

    function getContent(option){
        if(option.length>0) {
            var content = "";
            for (var i = 0; i < option.length; i++) {
                content +="<div class='layui-form-item'>"+
                    "<div class='layui-input-inline'>" +
                    "<label class='layui-form-label'>" + option[i].cbase000VO.DSCA + ':' + "</lable>" +
                    "</div>" +
                    "<div class='layui-input-inline'>" +
                    "<input type='text' value='" + option[i].cons + "' name='cons' readonly/>" +
                    "</div>" +
                    "<div class='layui-input-inline'>" +
                    "<input type='text' value='" + checkString(option[i].remk) + "' name='remk' readonly/>" +
                    "</div>" +
                    "</div>";
            }
            return content;
        }
        return "";
    }
</script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="./index"><cite style="cursor: pointer;">绩效</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">评分</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <hr/>
    <form class="layui-form">
        <input type="hidden" value="${obj.grop}" id="grop"/>
        <input type="hidden" value="${obj.ptno}" id="ptno"/>
        <div class="layui-collapse">
        </div>
        <br><br><br><br><br><br><br><br>
    </form>
</div>
</body>
</html>

