<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/18
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>OKR编辑</title>
    <kellyj:import url="../../static1.html"/>
    <link rel="stylesheet" href="../static/css/okr.css" media="all">
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
                <td colspan="6">
                    <div class="layui-form-item">
                        <label for="asid" class="layui-form-label">管理对象：</label>
                        <div class="layui-input-block">
                            <span id="asid_0" style="float: right"></span>
                            <select name="asid" lay-filter="asid" id="asid" lay-search>
                                <option value="" style="display:none;" disabled>请选择管理对象</option>
                            </select>
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="boss" class="layui-form-label">直接上级：</label>
                        <div class="layui-input-block">
                            <span id="boss_0" style="float: right"></span>
                            <select name="boss" lay-filter="boss" id="boss" lay-search>
                                <option value="" style="display:none;" disabled>请选择直接上级</option>
                            </select>
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="mdat" class="layui-form-label">管理周期：</label>
                        <div class="layui-input-block">
                            <input type="text" name="mdat" id="mdat" class="layui-input" style="border:none;"
                                   value="${obj.okr.MDAT}">
                        </div>
                    </div>
                </td>
                <td class="none_border">

                </td>
            </tr>
            <tr id="okr_item_0_0">
                <th class="center" width="4%">
                    <label>序号</label>
                </th>
                <th class="center" width="6%">
                    <label>目标(O)</label>
                </th>
                <th class="center" width="10%">
                    <label>O周期</label>
                </th>
                <th class="center" width="10%">
                    <label>O类型</label>
                </th>
                <th class="center" width="5%">
                    <label>O权重</label>
                </th>
                <th class="center" width="10%">
                    <label>O完成情况</label>
                </th>
                <th class="center" width="20%">
                    <label>关键成果(KRS)</label>
                </th>
                <th class="center" width="7%">
                    <label>KR权重</label>
                </th>
                <th class="center" width="18%">
                    <label>KRS完成情况</label>
                </th>
                <th class="center" width="8%">
                    <label>自评分</label>
                </th>
                <th class="none_border" width="2%">
                </th>
            </tr>
        </table>
        <a class="layui-hide form_render"></a>
    </div>
</div>
<div class="x-center x-body"
     style="bottom: 0;position: fixed;width: 100%;height:50px;background-color:#fff;z-index: 1;">
    <div class="layui-input-inline">
        <button class="layui-btn layui-btn-radius" id="update">修改</button>
    </div>
    <kellyj:if test="${obj.isManager}">
        <div class="layui-input-inline">
            <a class="layui-btn layui-btn-radius layui-bg-gray" href="./manage">返回</a>
        </div>
    </kellyj:if>
    <kellyj:if test="${!obj.isManager}">
        <div class="layui-input-inline">
            <a class="layui-btn layui-btn-radius layui-bg-gray" href="./index">返回</a>
        </div>
    </kellyj:if>
</div>
<script id="okr" type="text/html">
    {{# layui.each(d,function(index,item){ }}
    {{# layui.each(item.tasks,function(num,task){ }}
    {{# if(num === 0){ }}
    <tr id="okr_item_{{index+1}}_{{num}}">
        <td class="none_pdding" zIndex="0" rowspan="{{item.tasks.length}}">
            <strong>{{index+1}}</strong>
        </td>
        <td class="none_pdding" zIndex="0" rowspan="{{item.tasks.length}}">
            <textarea type="text" name="goal_{{index+1}}" id="goal_{{index+1}}" placeholder="请输入目标"
                      onfocus="jinyu($(this))" onblur="rose($(this))" autocomplete="off" class="excel_input">{{item.goal === undefined ? '': item.goal}}</textarea>
        </td>
        <td class="none_pdding" zIndex="0" rowspan="{{item.tasks.length}}">
            <span id="ndat_{{index+1}}_0"></span>
            <select name="ndat_{{index+1}}" id="ndat_{{index+1}}" lay-search="">
                <option value="" class="n-display" disabled>请选择周期</option>
                <option value="1" class="n-display" {{item.ndat===
                '1' ? 'selected':''}}>月度</option>
                <option value="2" class="n-display" {{item.ndat===
                '2' ? 'selected':''}}>季度</option>
                <option value="3" class="n-display" {{item.ndat===
                '3' ? 'selected':''}}>半年度</option>
                <option value="4" class="n-display" {{item.ndat===
                '4' ? 'selected':''}}>年度</option>
            </select>
        </td>
        <td class="none_pdding" zIndex="0" rowspan="{{item.tasks.length}}">
            <span id="type_{{index+1}}_0"></span>
            <select name="type_{{index+1}}" lay-filter="type" id="type_{{index+1}}" lay-search>
                <option value="" style="display:none;" disabled>请选择类型</option>
                <option value="1" class="n-display" {{item.type=== 1 ?
                'selected':''}}>项目类</option>
                <option value="2" class="n-display" {{item.type=== 2 ?
                'selected':''}}>质量类</option>
                <option value="3" class="n-display" {{item.type=== 3 ?
                'selected':''}}>管理类</option>
                <option value="4" class="n-display" {{item.type=== 4 ?
                'selected':''}}>创新类</option>
            </select>
        </td>
        <td class="none_pdding" zIndex="0" rowspan="{{item.tasks.length}}">
            <input type="text" name="prop_{{index+1}}" id="prop_{{index+1}}" placeholder="请输入权重" autocomplete="off"
                   class="excel_input" value="{{item.prop === undefined ? '': item.prop}}">
        </td>
        <td class="none_pdding" zIndex="0" rowspan="{{item.tasks.length}}">
            <input type="text" name="perf_{{index+1}}" id="perf_{{index+1}}" placeholder="请输入完成情况" autocomplete="off"
                   class="excel_input" value="{{item.perf === undefined ? '': item.perf}}">
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="achi_{{index+1}}_0" id="achi_{{index+1}}_0" placeholder="请输入关键成果"
                   autocomplete="off" class="excel_input" value="{{task.achi === undefined ? '': task.achi}}">
            <a href="javascript:" class="task_choose" onclick="task_choose($(this))"><i
                    class="layui-icon layui-icon-search"></i></a>
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="krprop_{{index+1}}_0" id="krprop_{{index+1}}_0" placeholder="请输入KR权重"
                   autocomplete="off" class="excel_input" value="{{task.krprop === undefined ? '': task.krprop}}">
            {{# if(item.tasks.length === 1){ }}
            <a href="javascript:" class="task_add" onclick="task_add($(this))"><i
                    class="layui-icon layui-icon-add-1 layui-bg-green"></i></a>
            {{# } }}
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="krperf_{{index+1}}_0" id="krperf_{{index+1}}_0" placeholder="请输入KR完成情况"
                   autocomplete="off" class="excel_input" value="{{task.krperf === undefined ? '': task.krperf}}">
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="zgrad_{{index+1}}_0" id="zgrad_{{index+1}}_0" placeholder="请输入自评分" autocomplete="off"
                   class="excel_input" value="{{task.zgrad === undefined ? '': task.zgrad}}">
        </td>
        <td class="none_pdding none_border" zIndex="0" rowspan="{{item.tasks.length}}">
            <div class="layui-form-item" style="margin: 0 auto;">
                <div>
                    <a href="javascript:" onclick="goal_del($(this))"><i
                            class="layui-icon layui-icon-close layui-bg-red"></i></a>
                </div>
                <div>
                    <a href="javascript:" onclick="goal_add($(this))"><i
                            class="layui-icon layui-icon-add-1 layui-bg-green"></i></a>
                </div>
            </div>
        </td>
    </tr>
    {{# }else{ }}
    <tr id="okr_item_{{index+1}}_{{num}}">
        <td class="none_pdding" zIndex="1">
            <input type="text" name="achi_{{index+1}}_{{num}}" id="achi_{{index+1}}_{{num}}" placeholder="请输入关键成果"
                   autocomplete="off" class="excel_input" value="{{task.achi === undefined ? '': task.achi}}">
            <a href="javascript:" class="task_choose" onclick="task_choose($(this))"><i
                    class="layui-icon layui-icon-search"></i></a>
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="krprop_{{index+1}}_{{num}}" id="krprop_{{index+1}}_{{num}}" placeholder="请输入KR权重"
                   autocomplete="off" class="excel_input" value="{{task.krprop === undefined ? '': task.krprop}}">
            {{# if(num === item.tasks.length - 1){ }}
            <a href='javascript:' class='task_del' onclick=task_del($(this))><i
                    class='layui-icon layui-icon-close layui-bg-red'></i></a>
            <a href='javascript:' class='task_add' onclick=task_add($(this))><i
                    class='layui-icon layui-icon-add-1 layui-bg-green'></i></a>
            {{# } }}
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="krperf_{{index+1}}_{{num}}" id="krperf_{{index+1}}_{{num}}" placeholder="请输入KR完成情况"
                   autocomplete="off" class="excel_input" value="{{task.krperf === undefined ? '': task.krperf}}">
        </td>
        <td class="none_pdding" zIndex="1">
            <input type="text" name="zgrad_{{index+1}}_{{num}}" id="zgrad_{{index+1}}_{{num}}" placeholder="请输入自评分" autocomplete="off"
                   class="excel_input" value="{{task.zgrad === undefined ? '': task.zgrad}}">
        </td>
    </tr>
    {{# } }}
    {{# }); }}
    {{# }); }}
</script>
<script language="JavaScript">
    layui.use(['form', 'table', 'jquery', 'layer', "laydate", "laytpl"], function () {
        var form = layui.form, $ = layui.jquery, postDataItem, laydate = layui.laydate,
            laytpl = layui.laytpl, getTpl = okr.innerHTML,isManager = ${obj.isManager};

        var start = {
            elem: '#mdat',
            type: 'month',
            choose: function (value) {
            }
        };

        laydate.render(start);

        $.ajax({
            type: 'POST',
            url: '../util/findC0',
            dataType: 'json',
            success: function (data) {
                var user = data.c0, boss = $("#boss"),asid = $("#asid"),
                    id = "${obj.okr.ASID}",dsca = "${obj.okr.ANAM}";
                var uOption = "";
                for (var i = 0; i < user.length; i++) {
                    uOption += "<option value='" + user[i].id + "'>" + user[i].dsca + "</option>";
                }
                boss.append(uOption);
                boss.val(${obj.okr.BOSS});
                if(isManager){
                    asid.append(uOption);
                    asid.val(${obj.okr.ASID});
                }else{
                    asid.append("<option value='"+id+"'>"+dsca+"</option>")
                }
                form.render();
            },
            error: function (kellyj) {
                layer.alert("发生错误，错误码为:" + kellyj.status, {offset: '10px', anim: 1});
            }
        });

        $.ajax({
            type: 'POST',
            url: './getSingleOkr?okid=' +${obj.okr.OKID},
            dataType: 'json',
            success: function (res) {
                if (res.code === 1) {
                    var data = [];
                    data = res.data.goals;
                    laytpl(getTpl).render(data, function (html) {
                        $(".layui-table").append(html);
                        form.render();
                    });
                } else {
                    layer.msg(res.msg, {offset: '10px'})
                }
            },
            error: function (kellyj) {
                layer.alert("发生错误，错误码为:" + kellyj.status);
            }
        });

        $(".form_render").click(function () {
            form.render();
        });

        $("#update").click(function () {
            postDataItem = {
                asid: '',
                boss: '',
                mdat: '',
                goals: []
            };
            var formAll = $(".layui-form").children();
            var inputs = formAll.find("input");
            var texts = formAll.find("textarea");
            var selects = formAll.find("select");
            var param = addToParam(addToParam(null, inputs), selects);
            var goalParam = addToParam(null, texts);
            var key = checkManagerData(param);
            if ((isManager && key) || (key && checkFormData(param) && checkFormData(goalParam))) {
                for (var j = 0; j < param.length; j++) {
                    if (param[j].name === "asid") {
                        postDataItem.asid = param[j].value;
                    } else if (param[j].name === "boss") {
                        postDataItem.boss = param[j].value;
                    } else if (param[j].name === "mdat") {
                        postDataItem.mdat = param[j].value;
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
                    var taskCount = $("[id^=okr_item_" + row + "]").length;

                    for (var n = 0; n < taskCount; n++) {
                        var taskItem = {
                            achi: '',//关键成果
                            krprop: 0, //KR权重
                            krperf:'',//KR完成情况
                            zgrad:0 //自评成绩
                        };
                        for (var m = 0; m < param.length; m++) {
                            name = param[m].name;
                            value = param[m].value;
                            if (name === "achi_" + row + "_" + n) {
                                taskItem.achi = value
                            } else if (name === "krprop_" + row + "_" + n) {
                                taskItem.krprop = value;
                            } else if(name === "krperf_" + row + "_" + n){
                                taskItem.krperf = value;
                            } else if(name === "zgrad_" + row + "_" + n){
                                taskItem.zgrad = value;
                            }
                        }
                        goalItem.tasks.push(taskItem);
                    }
                    postDataItem.goals.push(goalItem);
                }
                $.ajax({
                    type: 'POST',
                    url: './update?okid='+${obj.okr.OKID},
                    data: postDataItem,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code === 1) {
                            layer.confirm("成功修改，返回主界面？", {btn: ['确定返回', "刷新"], offset: '10px', anim: 4}, function () {
                                window.location.replace("./index");
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
        });
    });
</script>
<script src="../static/js/okrInsert.js"></script>
</body>
</html>
