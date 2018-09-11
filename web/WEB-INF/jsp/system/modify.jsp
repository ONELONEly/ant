<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/6
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加系统</title>
    <c:import url="../../static1.html"></c:import>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">设置</cite></a>
        <a href="${base}/system/manage"><cite style="cursor: pointer;">系统管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加系统</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body layui-container">
    <form class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="syno" value="${obj.sys.syno}" id="syno"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">系统描述:</label>
            <div class="layui-input-block">
                <input type="text" name="dsca" id="dsca" class="layui-input"  value="${obj.sys.dsca}" lay-verify="dsca" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">正式地址:</label>
            <div class="layui-input-block">
                <input type="text" name="sadd" id="sadd" class="layui-input"  value="${obj.sys.sadd}" lay-verify="sadd" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">测试地址:</label>
            <div class="layui-input-block">
                <input type="text" name="tadd" id="tadd" class="layui-input"  value="${obj.sys.tadd}" lay-verify="tadd" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-btn-radius" id="update" lay-filter="update" lay-submit>修改系统</button>
            <button type="reset" class="layui-btn layui-btn-radius">重置输入</button>
        </div>
    </form>

</div>
<script language="JavaScript">
    layui.use(['form','jquery','element','layer','layedit','upload'],function () {
        var form = layui.form,$ = layui.jquery,element = layui.element,
            layer = layui.layer;

        form.verify({
            dsca:function (value) {
                if(checkForm(value)){
                    return "请输入系统名称";
                }
            }
        });

        form.on("submit(set)",function (data) {

            var trans = data.field;
            $.ajax({

                type:'POST',
                url:'${base}/system/insertSystem',
                data:{
                    dsca:trans.dsca,
                    sadd:trans.sadd,
                    tadd:trans.tadd
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg,{btn:['确认','返回','取消'],offset:'100px',anim:4},function () {
                            window.location.reload();
                        },function () {
                            window.location.replace("${base}/system/manage");
                        });
                    }else{
                        layer.alert(data.msg,{offset:'10px'});
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status,{offset:'10px'});
                }
            });
            return false;
        });

        form.on("submit(update)",function (data) {

            var trans = data.field;
            $.ajax({
                type:'POST',
                url:'${base}/system/editSystem',
                data:{
                    syno:trans.syno,
                    dsca:trans.dsca,
                    sadd:trans.sadd,
                    tadd:trans.tadd
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg+"返回主页？",{offset:'100px'},function () {
                            window.location.replace("${base}/system/manage");
                        });
                    }else{
                        return layer.msg(data.msg,{offset:'10px'});
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status,{offset:'10px'});
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
