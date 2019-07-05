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
    <title>展示会议</title>
    <c:import url="../../static1.html"/>
    <style>
        .margin-50 {
            margin-top: 50px;
        }
        .margin-20 {
            margin-top: 20px;
        }
        .margin-5 {
            margin-top: 5px;
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
            padding: 0 10px;
            margin-left: 0 !important;
            line-height: 56px;
        }
        .table-label-content {
            display: block;
            padding: 9px 15px;
            font-weight: 400;
            line-height: 20px;
            text-align: center;
        }
        .content {
            line-height: 38px;
        }
        .item-count {
            font-size: 20px;
            font-weight: bold;
            margin-right: 5px;
        }
        .title {
            float: left;
            display: block;
            padding: 9px 15px;
            font-weight: bold;
            line-height: 20px;
            text-align: right;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">统计</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">工时查询</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="acco" id="acco" lay-filter="acco" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤科室</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <hr class="layui-bg-orange"/>
    <div class="layui-container layui-collapse">
    </div>
</div>

<script id="conference" type="text/html">
    {{# layui.each(d, function(index, item) { }}
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">{{ index + 1 }}.&nbsp;{{ item.title }}</h2>
        <div class="layui-container-item layui-colla-content">
            <div class="layui-row layui-col-space8" >
                <div class="layui-col-md12">
                    <label class="title"><span class="item-count">{{ index + 1 }}&nbsp;)</span>{{ item.title }}</label>
                </div>
            </div>
            <div class="layui-row table-content margin-5">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">项目开始时间</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.startDateTxt }}
                    </div>
                </div>
            </div>
            <div class="layui-row table-content">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">项目计划上线时间</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.scheduleDateTxt }}
                    </div>
                </div>
            </div>
            <div class="layui-row table-content">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">项目跟进人</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.follower }}
                    </div>
                </div>
            </div>
            <div class="layui-row table-content">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">项目上周计划</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.preWeekScheduleTxt === undefined ? '' : item.preWeekScheduleTxt }}
                    </div>
                </div>
            </div>
            <div class="layui-row table-content">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">项目上周完成</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.preWeekDoneTxt }}
                    </div>
                </div>
            </div>
            <div class="layui-row table-content">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">项目本周计划</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.nowWeekScheduleTxt }}
                    </div>
                </div>
            </div>
            <div class="layui-row table-content-last">
                <div class="layui-col-md2 table-label">
                    <label class="table-label-content">其它</label>
                </div>
                <div class="layui-col-md10 table-content-right">
                    <div class="layui-input-block table-input">
                        {{ item.othersTxt }}
                    </div>
                </div>
            </div>
        </div>
    </div>
    {{# }) }}
</script>
<script language="JavaScript">
    layui.use(["table","jquery","form",'laydate',"element"],function () {
        var table = layui.table, form = layui.form, $ = layui.jquery,
            laydate = layui.laydate,laytpl = layui.laytpl,element = layui.element

        $.ajax({
            type:'POST',
            url:'../util/findC17',
            dataType:'json',
            success:function (data) {
                var accos = data.acco;
                var uOption = "";
                for(var i = 0;i<accos.length;i++){
                    uOption += "<option value='"+accos[i].id+"'>"+accos[i].dsca+"</option>";
                }
                $("#acco").append(uOption);
                form.render();
            },
            error:function (kellyj) {
                layer.alert("发生错误，错误码为:"+kellyj.status,{offset:'10px',anim:1});
            }
        });

        form.on("select(acco)",function (data) {
            reload($("#month").val(), $("#week option:selected").val(),data.value)
        })

        function reload (month, week, acco) {
            var data = [],getTpl = conference.innerHTML;
            $.ajax({
                type:'POST',
                url:'./loadShowData',
                data: {
                    month: month,
                    week: week,
                    acco: acco
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        data = res.data;
                        laytpl(getTpl).render(data,function (html) {
                            $(".layui-container").html(html)
                            element.init();
                        });
                    }else{
                        layer.msg(res.msg,{offset:'10px'})
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status);
                }
            });
        }
    });
</script>
</body>
</html>
