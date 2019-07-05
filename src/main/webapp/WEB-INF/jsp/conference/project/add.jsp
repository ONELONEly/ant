<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/6/26
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加项目</title>
    <c:import url="../../../static1.html"/>
    <link rel="stylesheet" type="text/css" href="../formSelect/formSelects-v4.css">
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="./index"><cite style="cursor: pointer;">项目管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加项目</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>

<div class="x-body layui-container">
    <form class="layui-form layui-form-pane" enctype="multipart/form-data">

        <div class="layui-form-item">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="title" lay-verify="title" placeholder="请录入项目名称"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
                <input type="text" name="startDate" id="startDate" placeholder="请选择日期" class="layui-input" lay-verify="startDate" readonly/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">上线时间</label>
            <div class="layui-input-block">
                <input type="text" name="onlineDate" id="scheduleDate" placeholder="请选择日期" class="layui-input" lay-verify="scheduleDate" readonly/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">项目跟进人</label>
            <div class="layui-input-block">
                <select name="follower" xm-select="follower" xm-select-direction="down" xm-select-show-count="10"
                        xm-select-search="" id="follower" lay-verify="follower">

                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <div class="layui-input-inline">
                    <input type="radio" name="state" value="0" title="关闭" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="state" value="1" title="激活"/>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">序号</label>
            <div class="layui-input-block">
                <input type="text" name="orderNumber" class="layui-input" placeholder="请录入排序序号" lay-verify="number|orderNumber"/>
            </div>
        </div>

        <div class="layui-form-item x-center">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn layui-btn-radius" id="submit" value="提交" lay-filter="insert" lay-submit/>
                <button type="reset" class="layui-btn layui-btn-radius">重置</button>
            </div>
        </div>
    </form>

</div>
<script language="JavaScript">
    layui.config({
        base: '../formSelect/'
    }).extend({
        formSelects: 'formSelects-v4'
    });
    layui.use(['element','form','jquery','laydate','formSelects'],function () {
        var element = layui.element,form = layui.form,$ = layui.jquery,laydate = layui.laydate,
        formSelects = layui.formSelects;

        $.ajax({
            type:'GET',
            url:'${base}/util/findC0',
            dataType:'json',
            success:function (res) {
                var data = res.c0;
                var option = "<option value='' class='n-display' disabled selected>请选择跟进人</option>";
                for(var i = 0;i<data.length;i++){
                    option += "<option value='"+data[i].id+"'>"+data[i].dsca+"</option>";
                }
                $("#follower").html(option);
                form.render();
                formSelects.render('follower')
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });

        form.verify({
            title:function (value) {
                if(checkForm(value)){
                    return "请录入项目名称";
                }
            },
            follower:function (value) {
                if(checkForm(value)){
                    return "请选择项目跟进人";
                }
            },
            orderNumber:function (value) {
                if(checkForm(value)){
                    return "请录入项目序号";
                }
            }
        });

        form.on("submit(insert)",function (data) {
            data.field.follower = formSelects.value('follower', 'val')
            $.ajax({
                type:'POST',
                url:'./insertData',
                data:data.field,
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm("成功添加项目，返回上一页?",{btn:['确定','返回'],offset:'10px',anim:4},function () {
                            window.location.reload();
                        },function () {
                            window.location.replace("./index");
                        });
                    }else{
                        layer.alert(res.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
            return false;
        });

        var startDate = {
            elem:'#startDate',
            type:'date',
            choose: function (value) {
                console.log(value);
            }
        };

        var scheduleDate = {
            elem:'#scheduleDate',
            type:'date',
            choose: function (value) {
                console.log(value);
            }
        };

        laydate.render(startDate);
        laydate.render(scheduleDate);
    });
</script>
<script type="text/javascript">
</script>
</body>
</html>
