<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/6/11
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加会议</title>
    <c:import url="../../static1.html"/>
    <style>
        .margin-50 {
            margin-top: 50px;
        }
        .margin-20 {
            margin-top: 20px;
        }
        .table-content {
            border: 1px solid #e5e5e5;
            border-bottom: 0;
        }
        .table-content-last {
            border: 1px solid #e5e5e5;
        }
        .table-label {
            padding: 10px 0;
        }
        .table-input {
            padding: 10px;
            margin-left: 0 !important;
        }
        .table-content-right {
            border-left: 1px solid #e5e5e5;
        }
        .table-label-content {
            display: block;
            padding: 9px 15px;
            font-weight: 400;
            line-height: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="./index"><cite style="cursor: pointer;">我的会议</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加会议</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body layui-container">
    <form class="layui-form layui-form-panel">
        <div class="layui-row layui-col-space8 margin-50" >
            <div class="layui-col-md4">
                <label class="layui-form-label">标题：</label>
                <div class="layui-input-block">
                    <input type="text" name="title" id="title" placeholder="请录入标题" class="layui-input" lay-verify="title"/>
                </div>
            </div>
            <div class="layui-col-md4">
                <div class="layui-input-block">
                    <select name="week" id="week" lay-verify="week">
                        <option value=""  disabled selected>请选择周数</option>
                        <option value="1" >第一周</option>
                        <option value="2" >第二周</option>
                        <option value="3" >第三周</option>
                        <option value="4" >第四周</option>
                    </select>
                </div>
            </div>
            <div class="layui-col-md4" style="float: right">
                <div class="layui-input-block">
                    <input type="text" name="month" id="date" placeholder="请选择日期" class="layui-input" lay-verify="month" readonly/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content margin-20">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目开始时间</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <input type="text" name="startDate" id="startDate" placeholder="请选择日期" class="layui-input" lay-verify="startDate" readonly/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目计划上线时间</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <input type="text" name="scheduleDate" id="scheduleDate" placeholder="请选择日期" class="layui-input" lay-verify="scheduleDate" readonly/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目跟进人</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <input type="text" name="follower" id="follower" placeholder="录入跟进人" class="layui-input" lay-verify="follower"/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目上周完成</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <textarea name="preWeekDone" id="preWeekDone" placeholder="请输入上周完成事项" class="layui-textarea" lay-verify="preWeekDone"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目本周计划</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <textarea name="nowWeekSchedule" id="nowWeekSchedule" placeholder="请输入本周计划" class="layui-textarea" lay-verify="nowWeekSchedule"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-row table-content-last">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">其它</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <textarea name="others" id="others" placeholder="备注" class="layui-textarea" lay-verify="others"></textarea>
                </div>
            </div>
        </div>

        <div class="layui-form-item x-center margin-50">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn layui-btn-radius" id="submit" value="提交" lay-filter="insert" lay-submit/>
                <input type="reset" class="layui-btn layui-btn-radius" value="重置"/>
            </div>
        </div>
    </form>
</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laydate = layui.laydate,
            laypage = layui.laypage,
            element = layui.element,
            layer = layui.layer,
            form = layui.form,
            $ = layui.jquery;

        var month = {
            elem:'#date',
            type:'month',
            choose: function (value) {
                console.log(value);
            }
        };

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


        form.verify({
            title:function (value) {
                if(checkForm(value)){
                    return "请录入标题";
                }
            },
            week:function (value) {
                if (checkForm(value)) {
                    return "请选择周数"
                }
            },
            month:function (value) {
                if(checkForm(value)){
                    return "请选择月份";
                }
            },
            startDate:function (value) {
                if(checkForm(value)){
                    return "请选择开始日期";
                }
            },
            follower:function (value) {
                if (checkForm(value)) {
                    return "请录入跟进人";
                }
            },
            scheduleDate:function (value) {
                if(checkForm(value)){
                    return "请选择计划日期";
                }
            },
            preWeekDone:function (value) {
                if(checkForm(value)){
                    return "请录入上周完成事项";
                }
            },
            nowWeekSchedule:function (value) {
                if(checkForm(value)){
                    return "请录入本周计划";
                }
            }
        });

        form.on("submit(insert)",function (data) {
            var postData = data.field;
            postData.preWeekDoneTxt = replaceAll(postData.preWeekDone,"\n","<br/>")
            postData.nowWeekScheduleTxt = replaceAll(postData.nowWeekSchedule,"\n","<br/>")
            postData.othersTxt = replaceAll(postData.others,"\n","<br/>")
            delete postData.preWeekDone
            delete postData.nowWeekSchedule
            delete postData.others
            $.ajax({
                type: 'POST',
                url: './insertData',
                data: postData,
                dataType: 'JSON',
                success:function (res) {
                    console.log(res);
                    if (res.code === 1) {
                        layer.confirm("创建会议成功,返回上一页？",{offset:'10px'},function(){
                            window.location.replace("./index");
                        },function () {
                            window.location.reload();
                        });
                    } else {
                        layer.alert("错误信息："+ res.msg)
                    }
                },
                error:function (err) {
                    layer.alert("发生异常：" + err);
                }
            });
            return false;
        });

        laydate.render(month);
        laydate.render(startDate);
        laydate.render(scheduleDate);
    });
</script>
</body>
</html>