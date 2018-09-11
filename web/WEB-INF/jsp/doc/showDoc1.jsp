<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/22
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>文档展示</title>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <c:import url="../../static1.html"/>
    <script src="../static/js/jquery/jquery-1.8.2.min.js"></script>
    <script src="../static/js/jquery/jquery.jqprint-0.3.js"></script>
    <style>
        img{-ms-interpolation-mode:bicubic;}
        img{width:100%;}
    </style>
</head>
<body>
<div class="x-body">
    <div>
        <b>&nbsp;${obj.doc.tilt}</b>
    </div>
    <hr>
    <div>${obj.note}</div>
    <span class="n-display doid">${obj.doc.doid}</span>
    <br>
    <a href="./docFile?doid=${obj.doc.doid}" class="layui-btn layui-btn-xs layui-bg-black">附件</a>
</div>
<script language="JavaScript">
    layui.use(['jquery','table','layer'],function () {
        var $ = layui.jquery,table = layui.table,layer = layui.layer,usid = sessionStorage.getItem("usid"),
            doid = $(".doid").text(),tilt=$(".tilt").text();
        $(".print").on('click',function () {
            $(".x-body").jqprint();
        });

        $(".share").on('click',function () {
            if(usid !== null) {
                layer.prompt({
                    title: '请输入分享的邮箱号',
                    btn: ['确认'],
                    formType: 2,
                    anim: 4,
                    offset: '100px'
                }, function (res, index) {
                    $.ajax({
                        type: 'POST',
                        url: './shareDoc',
                        data: {
                            doid: doid,
                            tilt: tilt,
                            csid: res
                        },
                        dataType: 'json',
                        success: function (obj) {
                            layer.close(index);
                            return layer.msg(obj.msg,{offset:'10px'});
                        },
                        error: function (kellyj) {
                            return layer.msg("发生错误,错误码为：" + kellyj.status,{offset:'10px'});
                        }
                    });
                });
            }else{
                layer.alert("请先登录！");
            }
        });
    });
</script>
</body>
</html>
