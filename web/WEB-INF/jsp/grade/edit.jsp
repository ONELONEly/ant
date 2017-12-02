<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/28
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>绩效修改</title>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(['element','layer','form','jquery','laydate'],function () {
            var $ = layui.jquery,element = layui.element,form = layui.form,
                layer = layui.layer,laydate = layui.laydate;

            laydate.render({
                elem:'#date',
                type:'month',
                choose: function (value) {
                    console.log(value);
                }
            });

            $.ajax({
                url:'${base}/util/findC9',
                type:'GET',
                dataType:'json',
                success:function (data) {
                    var c9s = data.c9;
                    var option = "";
                    for(var i = 0;i<c9s.length;i++) {
                        option += "<option value='"+c9s[i].id+"'>"+c9s[i].dsca+"</option>";
                    }
                    $("#team").append(option);
                    form.render();
                },
                error:function (kj) {
                    alert("发生错误,错误码为:"+kj.status);
                }
            });

            form.on("submit(modify)",function (res) {
                var data = res.field;
                $.ajax({
                    url:'${base}/grade/updateProject',
                    type:'POST',
                    data:{
                        ptno:data.ptno,
                        pdat:data.date,
                        grop:data.team,
                        dsca:data.theme
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.code === 1){
                            layer.confirm(data.msg,{btn:['确定'],offset:'100px',anim:4},function (index) {
                                layer.close(index);
                                window.location.replace('./index');
                            });
                        }else{
                            layer.alert(data.msg);
                        }
                    },
                    error:function (kj) {
                        alert("发生错误,错误码为:"+kj.status);
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="./index"><cite style="cursor: pointer;">绩效</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">绩效修改</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body layui-container">
        <form class="layui-form layui-form-panel">
            <input type="hidden" name="ptno" id="ptno" value="${obj.ptno}">
            <div class="layui-form-item">
                <input type="text" name="date" id="date" placeholder="请选择日期" lay-verify="date" value="${obj.pdat}" autocomplete="off" class="layui-input" required/>
            </div>

            <div class="layui-form-item">
                <input type="text" name="theme" id="theme" placeholder="请输入主题" lay-verify="theme" value="${obj.dsca}" autocomplete="off" class="layui-input" required/>
            </div>

            <div class="layui-form-item">
                <select name="team" lay-filter="team" id="team" lay-search>
                    <option value="${obj.grop}" style="display:none;" disabled selected>${obj.gropnam}</option>
                </select>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn layui-btn-radius" lay-filter="modify" lay-submit="">修改</button>
            </div>
        </form>
</div>
</body>
</html>
