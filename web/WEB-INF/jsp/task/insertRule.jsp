<%--suppress ALL --%>
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
    <title>添加规则</title>
    <c:import url="../../static1.html"/>
</head>
<script language="JavaScript">
    layui.use(['form','jquery','element','layer','layedit','upload'],function () {
        var form = layui.form,$ = layui.jquery,element = layui.element,
            layer = layui.layer;


        form.verify({
            dsca:function (value) {
                if(value.length === 0){
                    return "请输入规则描述";
                }
            },
            plsu:function (value) {
                if(value.length === 0){
                    return "请输入评估占比";
                }
            },
            pjjp:function (value) {
                if(value.length === 0){
                    return "请输入分项占比";
                }
            },
            deti:function (value) {
                if(value.length === 0){
                    return "请输入评估细则";
                }
            },
            cons:function (value) {
                if(value.length === 0){
                    return "请输入基础分数";
                }
            },
            remk:function (value) {
                if(value.length === 0){
                    return "请输入规则备注";
                }
            }
        });

        form.on("submit(set)",function (data) {
            var trans = data.field;
            $.ajax({
                type:'POST',
                url:'${base}/task/insertRule',
                data:{
                    dsca:trans.dsca,
                    plsu:trans.plsu,
                    pjjp:trans.pjjp,
                    deti:trans.deti,
                    cons:trans.cons,
                    remk:trans.remk,
                    stat:trans.stat
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg,{btn:['确认','返回','取消'],offset:'100px',anim:4},function () {
                            window.location.reload();
                        },function () {
                            window.location.replace("${base}/task/rule");
                        });
                    }else{
                        layer.alert(data.msg);
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status);
                }
            });
            return false;
        });
    });
</script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">设置</cite></a>
        <a href="${base}/task/rule"><cite style="cursor: pointer;">任务规则</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加规则</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body layui-container">
    <form class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label">描述:</label>
            <div class="layui-input-block">
                <input type="text" name="dsca" id="dsca" class="layui-input" lay-filter="dsca" autocomplete="off" lay-verify="dsca" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">评估占比:</label>
            <div class="layui-input-block">
                <input type="text" name="plsu" id="plsu" class="layui-input" lay-filter="plsu" autocomplete="off" lay-verify="plsu" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分项占比:</label>
            <div class="layui-input-block">
                <input type="text" name="pjjp" id="pjjp" class="layui-input" lay-filter="pjjp" autocomplete="off" lay-verify="pjjp" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">评估细则:</label>
            <div class="layui-input-block">
                <input type="text" name="deti" id="deti" class="layui-input" lay-filter="deti" autocomplete="off" lay-verify="deti" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">基础分数:</label>
            <div class="layui-input-block">
                <input type="text" name="cons" id="cons" class="layui-input" lay-filter="cons" autocomplete="off" lay-verify="cons" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">备注:</label>
            <div class="layui-input-block">
                <input type="text" name="remk" id="remk" class="layui-input" lay-filter="remk" autocomplete="off" lay-verify="remk" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">类型:</label>
            <div class="layui-input-block">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" class="layui-form-radio" value="0" title="手动" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" class="layui-form-radio" value="1" title="自动" />
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-btn-radius" id="set" lay-filter="set" lay-submit>添加规则</button>
            <button type="reset" class="layui-btn layui-btn-radius">重置输入</button>
        </div>
    </form>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
