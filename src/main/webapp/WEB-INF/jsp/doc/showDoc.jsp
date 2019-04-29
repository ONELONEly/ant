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
    <c:import url="../../static1.html"/>
    <script src="../static/js/jquery/jquery-1.8.2.min.js"></script>
    <script src="../static/js/jquery/jquery.jqprint-0.3.js"></script>
</head>
<body>
<div class="x-body">
    <div class="x-titleDiv">
        <span class="tilt" style="font-size: xx-large;">&nbsp;${obj.doc.tilt}</span>
        <a href="./docFile?doid=${obj.doc.doid}" class="layui-btn layui-btn-xs layui-bg-black">附件</a>
    </div>
    <div>${obj.note}</div>
    <div class="layui-layout-right x-margin">
        <button class="layui-btn layui-btn-radius layui-bg-black print">打印</button>
        <button class="layui-btn layui-btn-radius layui-bg-green share">分享</button>
    </div>
    <span class="n-display doid">${obj.doc.doid}</span>
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
                    formType: 3,
                    anim: 4,
                    offset: '50px'
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
                layer.alert("请先登录！",{offset:'10px'});
            }
        });
    });
</script>
</body>
</html>
