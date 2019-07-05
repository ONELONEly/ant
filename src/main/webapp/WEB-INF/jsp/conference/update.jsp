<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/6/11
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改会议</title>
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
        .table-content-right {
            border-left: 1px solid #e5e5e5;
        }
        .table-input {
            padding: 10px;
            margin-left: 0 !important;
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
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">修改会议</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body layui-container">
    <form class="layui-form layui-form-panel">
        <div class="layui-row layui-col-space8 margin-50" >
            <input type="hidden" name="conference" value="${obj.conference}">
            <div class="layui-col-md4">
                <label class="layui-form-label">标题：</label>
                <div class="layui-input-block">
                    <select name="title" id="title" lay-filter="title" lay-verify="title">

                    </select>
                </div>
            </div>
            <div class="layui-col-md8">
            </div>
        </div>
        <div class="layui-row table-content margin-20">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目开始时间</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <input type="text" name="startDate" id="startDate" placeholder="请选择日期" value="${obj.startDateTxt}" class="layui-input" lay-verify="startDate" readonly/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目计划上线时间</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <input type="text" name="scheduleDate" id="scheduleDate" placeholder="请选择日期" value="${obj.scheduleDateTxt}" class="layui-input" lay-verify="scheduleDate" readonly/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目跟进人</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <input type="text" name="follower" id="follower" placeholder="录入跟进人" value="${obj.follower}" class="layui-input" lay-verify="follower"/>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目上周完成</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <textarea name="preWeekDone" id="preWeekDone" placeholder="请输入上周完成事项" class="layui-textarea" lay-verify="preWeekDone">${obj.preWeekDoneTxt}</textarea>
                </div>
            </div>
        </div>
        <div class="layui-row table-content">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">项目本周计划</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <textarea name="nowWeekSchedule" id="nowWeekSchedule" placeholder="请输入本周计划" class="layui-textarea" lay-verify="nowWeekSchedule">${obj.nowWeekScheduleTxt}</textarea>
                </div>
            </div>
        </div>
        <div class="layui-row table-content-last">
            <div class="layui-col-md2 table-label">
                <label class="table-label-content">其它</label>
            </div>
            <div class="layui-col-md10 table-content-right">
                <div class="layui-input-block table-input">
                    <textarea name="others" id="others" placeholder="备注" class="layui-textarea" lay-verify="others">${obj.othersTxt}</textarea>
                </div>
            </div>
        </div>

        <div class="layui-form-item x-center margin-50">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn layui-btn-radius" id="submit" value="提交" lay-filter="update" lay-submit/>
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

        $("#week").val(${obj.week})
        form.render()

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

        form.on("submit(update)",function (data) {
            var postData = data.field;
            postData.projectGuid = $('#title').val()
            postData.title = $('#title').find("option:selected").text()
            postData.preWeekDoneTxt = replaceAll(postData.preWeekDone,"\n","<br/>")
            postData.nowWeekScheduleTxt = replaceAll(postData.nowWeekSchedule,"\n","<br/>")
            postData.othersTxt = replaceAll(postData.others,"\n","<br/>")
            delete postData.preWeekDone
            delete postData.nowWeekSchedule
            delete postData.others
            $.ajax({
                type: 'POST',
                url: './modifyData',
                data: postData,
                dataType: 'JSON',
                success:function (res) {
                    console.log(res);
                    if (res.code === 1) {
                        layer.confirm("成功修改会议,返回上一页？",{offset:'10px'},function(){
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

        form.on("select(title)",function (data) {
            fetchProject(data.value)
        });

        function fetchProject (projectGuid) {
            $.ajax({
                type:'POST',
                url:'../conferenceProject/fetchData',
                data: {
                    projectGuid
                },
                dataType:'json',
                success:function (res) {
                    if (res.code === 1) {
                        $("#startDate").val(res.data.startDateTxt)
                        $("#scheduleDate").val(res.data.onlineDateTxt)
                        var users = res.data.projectUsers,user = '';
                        for(var i = 0;i < users.length;i++) {
                            if(i === users.length - 1) {
                                user += users[i].userName;
                            }else {
                                user += users[i].userName + ',';
                            }
                        }
                        $("#follower").val(user)
                    } else {
                        layer.alert("错误信息："+ res.msg)
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        }

        $.ajax({
            type:'GET',
            url:'${base}/util/findConferenceProject',
            dataType:'json',
            success:function (res) {
                var data = res.project;
                var option = "<option value='' class='n-display' disabled selected>请选择标题</option>";
                for(var i = 0;i<data.length;i++){
                    option += "<option value='"+data[i].id+"'>"+data[i].dsca+"</option>";
                }
                $("#title").html(option);
                $("#title").val('${obj.projectGuid}')
                form.render();
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });


        laydate.render(month);
        laydate.render(startDate);
        laydate.render(scheduleDate);
    });
</script>
</body>
</html>
