<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/8/29
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>绩效</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">绩效</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form" action="">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="date" id="date" placeholder="请选择日期" lay-verify="date" autocomplete="off"
                           class="layui-input" required/>
                </div>

                <div class="layui-input-inline">
                    <input type="text" name="theme" id="theme" placeholder="请选择主题" lay-verify="theme" autocomplete="off"
                           class="layui-input" required/>
                </div>

                <div class="layui-input-inline">
                    <select name="team" lay-filter="team" id="team" lay-search>
                        <option value="" style="display:none;" disabled selected>请选择Team</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="add" lay-submit="">增加</button>
                </div>
            </div>
        </div>
    </form>

    <form class="layui-form">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="verify" id="verify" placeholder="请选择日期" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit="">查询</button>
                </div>
                <div class="layui-input-inline">
                    <select name="group" lay-filter="group" id="group" lay-search>
                        <option value="" style="display:none;" disabled selected>团队过滤</option>
                    </select>
                </div>
            </div>
        </div>
    </form>

    <div class="layui-box">
        <div class="layui-inline" style="width: 80%">
            <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
            <button class="layui-btn layui-bg-black copy-btn"><i class="layui-icon">&#xe6af;</i>复制</button>
        </div>
        <table class="layui-table"
               lay-data="{url:'${base}/grade/queryAll',initSort:{field:'pdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'grade'}"
               lay-filter="grade">
            <thead>
            <tr>
                <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
                <th lay-data="{field:'ptno',align:'center',width:170,sort:true}">编号</th>
                <th lay-data="{field:'pdat',align:'center',width:170,sort:true}">月份</th>
                <th lay-data="{align:'center',width:170,templet:'#taskTpl'}">任务评分</th>
                <th lay-data="{field:'dsca',align:'center',width:'20%',templet:'#themeTpl'}">日常评分</th>
                <th lay-data="{field:'count',align:'center',width:170,templet:'#countTpl'}">统计</th>
                <th lay-data="{field:'gropnam',align:'center',width:170,sort:true}">团队</th>
                <th lay-data="{align:'center',width:170,templet:'#ruleTpl'}">规则</th>
                <th lay-data="{fixed:'right',toolbar:'#operate',width:400,align:'center'}">操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <script type="text/html" id="themeTpl">
        <a href="./mark?grop={{d.grop}}&ptno={{d.ptno}}" class="layui-table-link">{{d.dsca}}</a>
    </script>
    <script type="text/html" id="countTpl">
        <a href="./count?grop={{d.grop}}&ptno={{d.ptno}}" class="layui-table-link">查看</a>
    </script>
    <script type="text/html" id="taskTpl">
        <a href="./showTask?ptno={{d.ptno}}" class="layui-table-link">查看</a>
    </script>
    <script type="text/html" id="ruleTpl">
        <a href="${base}/grade/projectRule?ptno={{d.ptno}}" class="layui-table-link">修改</a>
    </script>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-xs" href="./edit?ptno={{d.ptno}}">编辑</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
        <a class="layui-btn layui-btn-xs" lay-event="count">结算</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="push">推送领导</a>
        <a class="layui-btn layui-btn-xs" href="./printGrade?ptno={{d.ptno}}">生成绩效表</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(["laydate", "laypage", "element", "layer", "table", "jquery", "form"], function () {
        var laydate = layui.laydate,
            laypage = layui.laypage,
            element = layui.element,
            layer = layui.layer,
            table = layui.table,
            form = layui.form,
            $ = layui.jquery,
            grade = {
                group:'',
                key:''
            },
            allField = JSON.parse(sessionStorage.getItem("moduleAllField"));

        if (allField != null){

            $("#verify").val(allField.grade.grade.key)
            $("#group").val(allField.grade.grade.group)

            table.reload("grade",{
                where:allField.grade.grade,
                page:{
                    curr:1
                }
            });
        }



        form.on("select(group)",function (data) {
            grade = {
                group:data.value,
                key:$("#verify").val()
            }
            table.reload("grade",{
                where:grade,
                page:{
                    curr:1
                }
            });
            syncField()
        });

        var start = {
            elem: '#date',
            type: 'month',
            choose: function (value) {
                console.log(value);
            }
        };

        var filter = {
            elem: '#verify',
            type: 'month',
            choose: function (value) {
                console.log(value);
            }
        };

        laydate.render(start);
        laydate.render(filter);

        form.on("submit(add)", function (res) {
            var data = res.field;
            $.ajax({
                url: '${base}/grade/insertProject',
                type: 'POST',
                data: {
                    pdat: data.date,
                    grop: data.team,
                    dsca: data.theme
                },
                dataType: 'json',
                success: function (data) {
                    if (data.code === 1) {
                        layer.confirm(data.msg, {offset: '10px'},function (index) {
                            table.reload("grade");
                            layer.close(index);
                        });
                    } else {
                        layer.alert(data.msg, {offset: '10px'});
                    }
                },
                error: function (kj) {
                    alert("发生错误,错误码为:" + kj.status);
                }
            });
            return false;
        });

        form.on("submit(search)", function (data) {
            var infor = data.field;
            grade = {
                key: infor.verify,
                group:$("#group option:selected").val()
            }
            table.reload("grade", {
                where: grade,
                page:{
                    curr:1
                }
            });
            syncField()
            return false;
        });

        $.ajax({
            url: '${base}/util/findC9',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var c9s = data.c9;
                var option = "";
                for (var i = 0; i < c9s.length; i++) {
                    option += "<option value='" + c9s[i].id + "'>" + c9s[i].dsca + "</option>";
                }
                $("#team").append(option);
                $("#group").append(option);
                form.render();
            },
            error: function (kj) {
                alert("发生错误,错误码为:" + kj.status);
            }
        });

        table.on("tool(grade)", function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么',{offset: '10px'}, function (index) {
                    $.ajax({
                        type: 'POST',
                        url: '${base}/grade/deleteGrade',
                        data: {
                            ptno: data.ptno
                        },
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 1) {
                                obj.del();
                                layer.close(index);
                            }
                            return layer.msg(res.msg, {offset: '10px'});
                        },
                        error: function (kj) {
                            layer.alert("发生错误:" + kj.status);
                        }
                    });
                });
            } else if (obj.event === 'push') {
                $.ajax({
                    type: 'POST',
                    url: '${base}/grade/pushGrade',
                    data: {
                        ptno: data.ptno
                    },
                    dataType: 'json',
                    success: function (res) {
                        return layer.msg(res.msg, {offset: '10px'});
                    },
                    error: function (kj) {
                        layer.alert("发生错误:" + kj.status, {offset: '10px'});
                    }
                });
            } else if (obj.event === 'count') {
                $.ajax({
                    type: 'POST',
                    url: '${base}/grade/insertAutoScore',
                    data: {
                        ptno: data.ptno
                    },
                    dataType: 'json',
                    success: function (res) {
                        layer.alert(res.msg, {offset: '10px'});
                    },
                    error: function (kj) {
                        layer.alert("发生错误:" + kj.status, {offset: '10px'});
                    }
                });
            }
        });

        $(".delete-btn").on("click", function () {
            var check = table.checkStatus('grade');
            var data = check.data;
            var param = {};
            for (var i = 0; i < data.length; i++) {
                param[i] = data[i].ptno;
            }
            $.ajax({
                type: 'POST',
                url: '${base}/grade/deleteGrade',
                data: {
                    list: param
                },
                dataType: 'json',
                success: function (res) {
                    if (res.code === 1) {
                        layer.alert(res.msg, {offset: '10px'});
                        table.reload("grade")
                    } else {
                        layer.alert(res.msg, {offset: '10px'});
                    }
                },
                error: function (kj) {
                    layer.alert("发生错误:" + kj.status, {offset: '10px'});
                }
            });
        });

        $(".copy-btn").on('click', function () {
            var check = table.checkStatus("grade");
            var data = check.data;
            var param = [];
            for (var i = 0; i < data.length; i++) {
                param[i] = data[i].ptno;
            }
            $.ajax({
                type: 'POST',
                url: '${base}/grade/copyProject',
                data: {
                    list: param
                },
                dataType: 'json',
                success: function (res) {
                    if (res.code === 1) {
                        table.reload("grade")
                    }
                    return layer.msg(res.msg, {offset: '10px'});
                },
                error: function (kj) {
                    layer.alert("发生错误:" + kj.status, {offset: '10px'});
                }
            });
        });

        function syncField(){
            if(allField == null){
                allField = moduleAllField;
            }
            allField.grade.grade = grade;
            sessionStorage.setItem("moduleAllField",JSON.stringify(allField));
        }
    });
</script>
</body>
</html>
