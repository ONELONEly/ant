<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/4
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>OKR添加</title>
    <kellyj:import url="../../static1.html"/>
    <link rel="stylesheet" href="../static/css/okr.css" media="all">
    <%--<style>--%>
        <%--th {--%>
            <%--now--%>
        <%--}--%>
    <%--</style>--%>
</head>
<body>
<div class="x-body">
    <div class="layui-form layui-form-panel" style="padding-bottom:50px;">
        <table class="layui-table">
            <tr>
                <td colspan="10" class="x-center">OKR管理表</td>
                <td class="none_border">

                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <div class="layui-form-item">
                        <label for="asid" class="layui-form-label">管理对象：</label>
                        <div class="layui-input-block">
                            <span id="asid_0" style="float: right"></span>
                            <select name="asid" lay-filter="asid" id="asid" lay-search>
                                <option value="" style="display:none;" disabled selected>请选择管理对象</option>
                            </select>
                        </div>
                    </div>
                </td>
                <td colspan="3">
                    <div class="layui-form-item">
                        <label for="boss" class="layui-form-label">直接上级：</label>
                        <div class="layui-input-block">
                            <span id="boss_0" style="float: right"></span>
                            <select name="boss" lay-filter="boss" id="boss" lay-search>
                                <option value="" style="display:none;" disabled selected>请选择直接上级</option>
                            </select>
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="mdat" class="layui-form-label">管理周期：</label>
                        <div class="layui-input-block">
                            <input type="text" name="mdat" id="mdat" class="layui-input" style="border:none;">
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="boss" class="layui-form-label">提交类型：</label>
                        <div class="layui-input-block">
                            <span id="rule_0" style="float: right"></span>
                            <select name="rule" lay-filter="rule" id="rule" lay-search>
                                <option value="" style="display:none;" disabled selected>请选择提交类型</option>
                                <option value="1">计划</option>
                                <option value="2">完成</option>
                            </select>
                        </div>
                    </div>
                </td>
                <td class="none_border">

                </td>
            </tr>
            <tr id="okr_item_0_0">
                <th class="center" width="4%" nowrap="nowrap">
                    <label>序号</label>
                </th>
                <th class="center" width="6%" nowrap="nowrap">
                    <label>目标(O)</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>O周期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>O类型</label>
                </th>
                <th class="center" width="5%"  nowrap="nowrap">
                    <label>O权重(%)</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>O完成情况</label>
                </th>
                <th class="center" width="20%" nowrap="nowrap">
                    <label>关键成果(KRS)</label>
                </th>
                <th class="center" width="7%" nowrap="nowrap">
                    <label>KR权重(%)</label>
                </th>
                <th class="center" width="18%" nowrap="nowrap">
                    <label>KRS完成情况</label>
                </th>
                <th class="center" width="8%" nowrap="nowrap">
                    <label>自评分</label>
                </th>
                <th class="none_border" width="2%" nowrap="nowrap">
                </th>
            </tr>
            <tr id="okr_item_1_0">
                <td class="none_pdding" zIndex="0" rowspan="">
                    <strong>1</strong>
                </td>
                <td class="none_pdding" zIndex="0" rowspan="">
                    <textarea type="text" name="goal_1" id="goal_1" placeholder="请输入目标" onfocus="jinyu($(this))" onblur="rose($(this))" autocomplete="off" class="excel_input"></textarea>
                </td>
                <td class="none_pdding" zIndex="0" rowspan="">
                    <span id="ndat_1_0"></span>
                    <select name="ndat_1" id="ndat_1" lay-search="">
                        <option value="" class="n-display" disabled selected>请选择周期</option>
                        <option value="1" class="n-display">月度</option>
                        <option value="2" class="n-display">季度</option>
                        <option value="3" class="n-display">半年度</option>
                        <option value="4" class="n-display">年度</option>
                    </select>
                </td>
                <td class="none_pdding" zIndex="0" rowspan="">
                    <span id="type_1_0"></span>
                    <select name="type_1" lay-filter="type" id="type_1" lay-search>
                        <option value="" style="display:none;" disabled selected>请选择类型</option>
                        <option value="1" class="n-display">项目类</option>
                        <option value="2" class="n-display">质量类</option>
                        <option value="3" class="n-display">管理类</option>
                        <option value="4" class="n-display">创新类</option>
                    </select>
                </td>
                <td class="none_pdding" zIndex="0"  rowspan="">
                    <input type="text" name="prop_1" id="prop_1" placeholder="请输入权重" autocomplete="off" class="excel_input">
                </td>
                <td class="none_pdding" zIndex="0"  rowspan="">
                    <input type="text" name="perf_1" id="perf_1" placeholder="请输入完成情况" autocomplete="off" class="excel_input">
                </td>
                <td class="none_pdding" zIndex="1" >
                    <input type="text" name="achi_1_0" id="achi_1_0" placeholder="请输入关键成果" onfocus="jinyu($(this))" onblur="rose($(this))" autocomplete="off"  class="excel_input">
                    <a href="javascript:" class="task_choose" onclick="task_choose($(this))"><i class="layui-icon layui-icon-search"></i></a>
                </td>
                <td class="none_pdding" zIndex="1" >
                    <input type="text" name="krprop_1_0" id="krprop_1_0" placeholder="请输入KR权重" autocomplete="off" class="excel_input">
                    <a href="javascript:" class="task_add" onclick="task_add($(this))"><i class="layui-icon layui-icon-add-1 layui-bg-green"></i></a>
                </td>
                <td class="none_pdding" zIndex="1">
                    <input type="text" name="krperf_1_0" id="krperf_1_0" placeholder="请输入KR完成情况" autocomplete="off" class="excel_input">
                </td>
                <td class="none_pdding" zIndex="1">
                    <input type="text" name="zgrad_1_0" id="zgrad_1_0" placeholder="请输入自评分" autocomplete="off" class="excel_input">
                </td>
                <td class="none_pdding none_border" zIndex="0" rowspan="">
                    <div class="layui-form-item" style="margin: 0 auto;">
<%--                        <div>
                            <a href="javascript:" onclick="goal_del($(this))"><i class="layui-icon layui-icon-close layui-bg-red"></i></a>
                        </div>--%>
                        <div>
                            <a href="javascript:" onclick="goal_add($(this))"><i class="layui-icon layui-icon-add-1 layui-bg-green"></i></a>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <a class="layui-hide form_render"></a>
    </div>
</div>
<div class="x-center x-body" style="bottom: 0;position: fixed;width: 100%;height:50px;background-color:#fff;z-index: 1;">
    <div class="layui-input-inline">
        <button class="layui-btn layui-btn-radius" id="insert">保存</button>
    </div>
    <kellyj:if test="${obj.isManager}">
        <div class="layui-input-inline">
            <a class="layui-btn layui-btn-radius layui-bg-gray back" href="./manage">返回</a>
        </div>
    </kellyj:if>
    <kellyj:if test="${!obj.isManager}">
        <div class="layui-input-inline">
            <a class="layui-btn layui-btn-radius layui-bg-gray back" href="./index">返回</a>
        </div>
    </kellyj:if>
</div>
<script language="JavaScript">
layui.use(['form', 'table','jquery','layer',"laydate"], function () {
    var form = layui.form,$ = layui.jquery,postDataItem,
        laydate = layui.laydate,isManager = ${obj.isManager};
    var start = {
        elem:'#mdat',
        type:'month',
        choose: function (value) {
        }
    };

    laydate.render(start);

    $.ajax({
        type:'POST',
        url:'../util/findC0',
        dataType:'json',
        success:function (data) {
            var user = data.c0,id = "${obj.user.USID}",dsca = "${obj.user.DSCA}";
            var uOption = "";
            for(var i = 0;i<user.length;i++){
                uOption += "<option value='"+user[i].id+"'>"+user[i].dsca+"</option>";
            }
            $("#boss").append(uOption);
            if(isManager){
                $("#asid").append(uOption);
            }else{
                $("#asid").append("<option value='"+id+"'>"+dsca+"</option>")
            }
            form.render();
        },
        error:function (kellyj) {
            layer.alert("发生错误，错误码为:"+kellyj.status,{offset:'10px',anim:1});
        }
    });

    $(".form_render").click(function () {
        form.render();
    });

    $("#insert").click(function () {
        postDataItem = {
            asid:'',
            boss:'',
            mdat:'',
            type:0,
            goals:[]
        };
        var formAll = $(".layui-form").children();
        var inputs = formAll.find("input");
        var propInput = $("input[name^='prop']");
        var krPropInput = $("input[name^='krprop_']");
        var zgradInput = $("input[name^='zgrad_']");
        var texts = formAll.find("textarea");
        var selects = formAll.find("select");
        var option = $("#rule");
        var param = addToParam(addToParam(null,inputs),selects);
        var goalParam = addToParam(null,texts);
        var propParam = addToParam(null,propInput);
        var krPropParam = addToParam(null,krPropInput);
        var zgradParam = addToParam(null,zgradInput);
        var key = checkManagerData(param);
        if((isManager && key) || (key && checkFormData(param,option) && checkFormData(goalParam,null))) {
            if ((isManager && key) || checkProp(propParam,krPropParam,zgradParam)) {
                for (var j = 0; j < param.length; j++) {
                    if (param[j].name === "asid") {
                        postDataItem.asid = param[j].value;
                    } else if (param[j].name === "boss") {
                        postDataItem.boss = param[j].value;
                    } else if (param[j].name === "mdat") {
                        postDataItem.mdat = param[j].value;
                    }else if (param[j].name === "rule") {
                        postDataItem.type = param[j].value;
                    }
                }
                for (var i = 0; i < goalParam.length; i++) {
                    var goal = goalParam[i];
                    var goalItem = {
                        goal: '',//目标
                        ndat: '',//周期
                        type: 0,//类型
                        prop: 0,//比重
                        perf: '',//完成情况
                        tasks: []
                    };
                    goalItem.goal = goal.value;
                    var row = goal.name.substring(5), name, value;
                    for (var k = 0; k < param.length; k++) {
                        name = param[k].name;
                        value = param[k].value;
                        if (name.match(/\bprop/) !== null) {
                            if (name.substring(5) === row) {
                                goalItem.prop = value;
                            }
                        } else if (name.match(/\bperf/) !== null) {
                            if (name.substring(5) === row) {
                                goalItem.perf = value;
                            }
                        } else if (name.match(/\bndat/) !== null) {
                            if (name.substring(5) === row) {
                                goalItem.ndat = value;
                            }
                        } else if (name.match(/\btype/) !== null) {
                            if (name.substring(5) === row) {
                                goalItem.type = value;
                            }
                        }
                    }
                    var taskCount = $("[id^=okr_item_" + row + "_]").length;

                    for (var n = 0; n < taskCount; n++) {
                        var taskItem = {
                            achi: '',//关键成果
                            krprop: 0, //KR权重
                            krperf: '',//KR完成情况
                            zgrad: 0 //自评成绩
                        };
                        for (var m = 0; m < param.length; m++) {
                            name = param[m].name;
                            value = param[m].value;
                            if (name === "achi_" + row + "_" + n) {
                                taskItem.achi = value
                            } else if (name === "krprop_" + row + "_" + n) {
                                taskItem.krprop = value;
                            } else if (name === "krperf_" + row + "_" + n) {
                                taskItem.krperf = value;
                            } else if (name === "zgrad_" + row + "_" + n) {
                                taskItem.zgrad = value;
                            }
                        }
                        goalItem.tasks.push(taskItem);
                    }
                    postDataItem.goals.push(goalItem);
                }
                console.log(postDataItem)
                $.ajax({
                    type: 'POST',
                    url: './insert',
                    data: postDataItem,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code === 1) {
                            layer.confirm("成功录入，返回主界面？", {btn: ['确定返回', "刷新"], offset: '10px', anim: 4}, function () {
                                window.location.replace($(".back").attr("href"))
                            }, function () {
                                window.location.reload();
                            });
                        } else {
                            layer.alert(data.msg, {offset: '10px', anim: 4});
                        }
                    },
                    error: function (kj) {
                        layer.alert("发生错误:" + kj.status, {offset: '10px'});
                    }
                });
            }
        }
    });
});
</script>
<script src="../static/js/okrInsert.js"></script>
</body>
</html>
