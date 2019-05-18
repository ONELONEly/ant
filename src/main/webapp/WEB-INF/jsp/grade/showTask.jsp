<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/8
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>评分显示</title>
    <script type="text/javascript" src="../static/js/startScore.js"></script>
    <link rel="stylesheet" href="../static/css/startScore.css" media="all">
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="./index"><cite style="cursor: pointer;">绩效</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">任务</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
<fieldset class="layui-elem-field layui-field-title">
    <legend>当月任务</legend>
</fieldset>
<form class="layui-form layui-form-panel">
    <div class="layui-inline">
        <button class="layui-btn mark-btn"><i class="layui-icon">&#xe6af;</i>评分</button>
    </div>
    <div class="layui-input-inline">
        <select name="user" lay-filter="user" id="user" lay-search>
            <option value="" style="display:none;" disabled selected>请筛选用户</option>
        </select>
    </div>
    <div class="layui-input-inline">
        <select name="status" lay-filter="status" id="status" lay-search>
            <option value="" style="display:none;" disabled selected>请筛选状态</option>
            <option value="0">未评分</option>
            <option value="1">已评分</option>
        </select>
    </div>
    <div class="layui-input-inline score">
    </div>
</form>
<table class="layui-table" id = "manage" lay-filter="manage">
</table>
<script type="text/html" id="noteTpl">
    <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
</script>
<script type="text/html" id="scoreShow">
    {{# if(d.score != undefined){ }}
        <div class="score-show x-shadow">
            <span class="title">总分:</span>
            <span class="content">{{ d.score }}</span>
        </div>
    {{# } }}
</script>
<script type="text/html" id="stagTpl">
    {{# if(d.stag == 4){ }}
    <span style="color:green;">五星</span>
    {{# }else if(d.stag == 3){ }}
    <span style="color:green;">四星</span>
    {{# }else if(d.stag == 2){ }}
    <span style="color:green;">三星</span>
    {{# }else if(d.stag == 1){ }}
    <span style="color:green;">二星</span>
    {{# }else{ }}
    <span style="color: #f00;">零星</span>
    {{# } }}
</script>
<script type="text/html" id="mark">
    {{# if(d.stag > 0){ }}
    <span style="color:green;">已评分</span>
    {{# }else{ }}
    <span style="color: #f00;">未评分</span>
    {{# } }}
</script>
<div id="score" class="block clearfix x-center n-display">
    <div class="star_score x-margin" style="margin: auto"></div>
    <p class="x-margin">您的评分：<span class="stage"></span> 级</p>
</div>
</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form","laytpl"],function () {
        var laypage = layui.laypage, element = layui.element, layer = layui.layer,
            table = layui.table, form = layui.form, $ = layui.jquery,curr_table,layTpl = layui.laytpl;

        var userScore = undefined

        curr_table = table.render({ //initSort:{field:'cdat',type:'desc'}
            elem:'#manage',
            url:'${base}/task/queryAllGradeTask?ptno=${obj.ptno}',
            cellMinWidth:100,
            page:true,
            limit:10,
            limits:[10,20,30,40,50],
            initSort:{field:'cdat',type:'desc'},
            cols:[[
                {fixed:true,checkbox:true,width:50},
                {fixed:'left',align:'center',title:'状态',width:150,templet:'#mark'},
                {fixed:'left',field:'perc',align:'center',title:'完成度',width:150,sort:true},
                {fixed:'left',field:'titl',align:'center',title:'标题',width:300,toolbar:'#noteTpl'},
                {field:'fahh',align:'center',title:'工时',width:150,sort:true},
                {field:'stag',align:'center',width:150,title:'等级',sort:true,templet:'#stagTpl'},
                {field:'lastCons',align:'center',width:150,title:'分数',sort:true},
                {field:'synonam',align:'center',width:350,title:'系统'},
                {field:'cnam',align:'center',width:150,title:'派发给'},
                {field:'sta1nam',align:'center',width:150,title:'状态'},
                {field:'sta2nam',align:'center',width:150,title:'紧急状态'},
                {field:'sta3nam',align:'center',width:150,title:'重要程度'},
                {field:'punonam',align:'center',width:150,title:'任务类型'},
                {field:'ptypnam',align:'center',width:150,title:'评分类型'},
                {field:'knam',align:'center',width:150,title:'关键用户'},
                {field:'adat',align:'center',width:150,title:'执行时间',sort:true},
                {field:'pdat',align:'center',width:150,title:'计划时间',sort:true},
                {field:'tdat',align:'center',width:150,title:'测试时间',sort:true},
                {field:'fdat',align:'center',width:150,title:'验收时间',sort:true},
                {field:'cdat',align:'center',width:150,title:'创建时间',sort:true},
                {field:'t1dsca',align:'center',width:300,title:'绩效表主题'},
                {fixed:'right',field:'eye',align:'center',width:200,title:'关注'}
            ]],
            response:{
                statusCode:0
            },
            done: function (res, curr, count) {
            }
        });

        scoreFun($("#score"),{
            fen_d:22,//每一个a的宽度
            ScoreGrade:5//a的个数 10或者
        });

        $.ajax({
            url: '${base}/util/findGropUser',
            data:{
              grop:"${obj.grop}"
            },
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var user = data.user;
                var option = "";
                for (var i = 0; i < user.length; i++) {
                    option += "<option value='" + user[i].usid + "'>" + user[i].dsca +'('+user[i].usid+')'+"</option>";
                }
                $("#user").append(option);
                form.render();
            },
            error: function (kj) {
                alert("发生错误,错误码为:" + kj.status);
            }
        });

        form.on("select(user)",function (data) {
            showUserTotal()
            curr_table = table.reload("manage",{
                where:{
                    key:data.value,
                    stag:$("#status option:selected").val()
                },
                page:{
                    curr:1
                }
            })
        });

        form.on("select(status)",function (data) {
            curr_table = table.reload("manage",{
                where:{
                    stag:data.value,
                    key:$("#user option:selected").val()
                },
                page:{
                    curr:1
                }
            })
        });
        $(".mark-btn").click(function () {
            layer.open({
                type:1,
                title:'请选择等级',
                area:'20%',
                content:$("#score"),
                btn:['确认'],
                anim:4,
                offset:'10px',
                yes:function () {
                    var value = $(".stage").text();
                    if (value === null || value === "") {
                        layer.tips('请选择等级','#score');
                    } else {
                        var check = table.checkStatus('manage');
                        var data = check.data;
                        var param = {};
                        for(var i = 0;i < data.length;i++){
                            param[i] = data[i].taid;
                        }
                        $.ajax({
                            type:'POST',
                            url:'${base}/task/markScore',
                            data:{
                                list:param,
                                stag:value-1
                            },
                            dataType:'json',
                            success:function (res) {
                                curr_table = table.reload("manage",{
                                    where:{
                                        stag:$("#status option:selected").val(),
                                        key:$("#user option:selected").val()
                                    },
                                    page:{
                                        curr:curr_table.config.page.curr
                                    }
                                });
                                layer.msg(res.msg,{offset:'10px'});
                            },
                            error:function (kj) {
                                layer.alert("发生错误:"+kj.status,{offset:'10px'});
                            }
                        });
                        layer.closeAll();
                    }
                }
            });
            return false;
        });


        table.on('tool(manage)', function(obj){
            var data = obj.data;
            if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:'../task/showTask?taid='+data.taid,
                    area:['90%','80%'],
                    title:'任务',
                    offset:'10px'
                });
            }else if(obj.event === 'in'){
                $.ajax({
                    type:'POST',
                    url:'${base}/task/eyeIn',
                    data:{
                        taid:data.taid
                    },
                    dataType:'json',
                    success:function (res) {
                        obj.update({
                            eye:"<a href='javascript:' class='layui-btn layui-btn-xs layui-btn-danger' lay-event='out'>取消</a>"
                        });
                        return layer.msg(res.msg,{offset:'10px'});
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status,{offset:'10px'});
                    }
                });
            }else if(obj.event === 'out'){
                $.ajax({
                    type:'POST',
                    url:'${base}/task/eyeOut',
                    data:{
                        taid:data.taid
                    },
                    dataType:'json',
                    success:function (res) {
                        obj.update({
                            eye:"<a href='javascript:' class='layui-btn layui-btn-xs layui-bg-black' lay-event='in'>关注</a>"
                        });
                        return layer.msg(res.msg,{offset:'10px'});
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status,{offset:'10px'});
                    }
                });
            }
        });

        function showUserTotal () {
            getUserScore().then((res) => {
                if(res >= 0){
                    var scoreTpl = scoreShow.innerHTML
                    var showData = {
                        score: res
                    }
                    layTpl(scoreTpl).render(showData, function (html) {
                        $(".score").html(html)
                    })
                }
            }).catch(err => {
                layer.msg(err)
            })
        }

        function getUserScore() {
            return new Promise((resolve, reject) => {
                var selectVal = $("#user option:selected").val()
                if (!checkForm(selectVal)) {
                    $.ajax({
                        url: '${base}/task/getUserScore',
                        data: {
                            ptno: '${obj.ptno}',
                            usid: $("#user option:selected").val()
                        },
                        type: 'POST',
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 1) {
                                resolve(res.data)
                            } else {
                                reject("发生错误,错误信息:" + res.msg);
                            }
                        },
                        error: function (kj) {
                            reject("发生错误,错误码为:" + kj.status);
                        }
                    });
                }else{
                    $('.score').html('')
                    resolve
                }
            })
        }
    });
</script>
</body>
</html>
