<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/4
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>选择任务</title>
    <kellyj:import url="../../static1.html"/>
</head>
<body>
<table class="layui-hide" id="okr" lay-filter="okr">
</table>
<script type="text/html" id="noteTpl">
    <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
</script>
<script>
    layui.use(['table','jquery'],function () {
        var table = layui.table,$ = layui.jquery;
        table.render({
            elem:'#okr',
            url:'../util/findTaskUtil',
            cellMinWidth:100,
            page:true,
            cols:[[
                {field:'titl',title:'任务标题',align:'center',toolbar:'#noteTpl'},
                {field:'cnam',title:'接收用户',align:'center'},
                {field:'pdat',title:'考核月份',align:'center'}
            ]],
            response:{
                statusCode:0
            }
        });

        table.on("row(okr)",function (obj) {
            layer.open({
                type: 1
                ,title:'目标'
                ,area: '300px'
                ,shade: 0
                ,maxmin: true
                ,offset: '10px'
                ,id:'taskShow'
                ,content: '<div style="padding: 50px; line-height: 22px; background-color: cadetblue; color: #fff; font-weight: 300;">请选择预览或者录入</div>'
                ,btn: ['预览', '录入']
                ,yes: function(layer_or){
                    layer.close(layer_or);
                    layer.open({
                        type:2,
                        content:'../task/showTask?taid='+obj.data.taid,
                        area:['90%','80%'],
                        title:'任务',
                        offset:'10px'
                    });
                }
                ,btn2: function(){
                    $(window.parent.document.getElementById("${obj}")).val(obj.data.note);
                }
            });
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });
    });
</script>
</body>
</html>
