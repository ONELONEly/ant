<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/26
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%><%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/5
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>任务管理</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">统计</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">查询报表</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="msg" id="msg" placeholder="请输入查询信息" lay-verify="msg" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="search" lay-submit>查询</button>
                </div>
                <div class="layui-input-inline">
                    <select name="pdat" id="pdat" lay-filter="pdat" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤日期</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="grop" id="grop" lay-filter="grop" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤团队</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="csid" id="csid" lay-filter="csid" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤个人</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn export-btn"><i class="layui-icon">&#xe6af;</i>导出DS</button>
    </div>
    <table class="layui-table" lay-data="{url:'${base}/task/queryAllTask',initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{field:'taid',align:'center',width:150,sort:true}">编号</th>
            <th lay-data="{field:'perc',align:'center',width:150,sort:true}">完成度</th>
            <th lay-data="{field:'titl',align:'center',width:350,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'synonam',align:'center',width:250}">系统</th>
            <th lay-data="{field:'unam',align:'center',width:150}">创建人</th>
            <th lay-data="{field:'cnam',align:'center',width:150}">派发给</th>
            <th lay-data="{field:'ptypnam',align:'center',width:150}">评分类型</th>
            <th lay-data="{field:'sta1nam',align:'center',width:150}">状态</th>
            <th lay-data="{field:'punonam',align:'center',width:150}">任务类型</th>
            <th lay-data="{field:'knam',align:'center',width:150}">关键用户</th>
            <th lay-data="{field:'sta2nam',align:'center',width:150}">优先级</th>
            <th lay-data="{field:'sta3nam',align:'center',width:150}">严重程度</th>
            <th lay-data="{field:'adat',align:'center',width:150,sort:true}">执行时间</th>
            <th lay-data="{field:'pdat',align:'center',width:150,sort:true}">计划时间</th>
            <th lay-data="{field:'tdat',align:'center',width:150,sort:true}">测试时间</th>
            <th lay-data="{field:'fdat',align:'center',width:150,sort:true}">验收时间</th>
            <th lay-data="{field:'cdat',align:'center',width:150,sort:true}">创建时间</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
    </script>

</div>
<script language="JavaScript">
    layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
        var laypage = layui.laypage, element = layui.element, layer = layui.layer,
            table = layui.table, form = layui.form, $ = layui.jquery,
            msg = "",pdat = "",grop = "",csid = "";

        $(document).ready(function () {
            $.ajax({
                type:'GET',
                url:'${base}/util/findC0C9Pdat',
                dataType:'json',
                success:function (res) {
                    var csid = res.c0,grop = res.c9,pdat = res.pdat,
                        cOption = "",gOption = "",pOption = "";
                    for (var m = 0; m < csid.length; m++) {
                        cOption += "<option value='" + csid[m].id + "'>" + csid[m].dsca + "</option>";
                    }
                    for (var n = 0; n < grop.length; n++) {
                        gOption += "<option value='" + grop[n].id + "'>" + grop[n].dsca + "</option>";
                    }
                    for (var k = 0; k < pdat.length; k++) {
                        pOption += "<option value='" + pdat[k] + "'>" + pdat[k] + "</option>";
                    }
                    $("#csid").append(cOption);
                    $('#grop').append(gOption);
                    $('#pdat').append(pOption);
                    form.render();
                },
                error:function (kellyj) {
                    return layer.msg("发生错误,错误码:"+kellyj.status,{offset:'10px'});
                }
            });
        });

        form.on("submit(search)",function (data) {
            var infor = data.field;
            pdat = $("select#pdat option:selected").val();
            grop = $('select#grop option:selected').val();
            csid = $('select#csid option:selected').val();
            table.reload("manage",{
                where:{
                    key:infor.msg,
                    pdat:pdat,
                    grop:grop,
                    csid:csid
                }
            });
            return false;
        });

        form.on("select(pdat)",function (data) {
            msg = $("#msg").val();
            grop = $('select#grop option:selected').val();
            csid = $('select#csid option:selected').val();
            table.reload('manage',{
                where:{
                    pdat:data.value,
                    key:msg,
                    grop:grop,
                    csid:csid
                }
            })
        });

        form.on("select(grop)",function (data) {
            msg = $("#msg").val();
            pdat = $('select#pdat option:selected').val();
            csid = $('select#csid option:selected').val();
            table.reload('manage',{
                where:{
                    grop:data.value,
                    key:msg,
                    pdat:pdat,
                    csid:csid
                }
            })
        });

        form.on("select(csid)",function (data) {
            msg = $("#msg").val();
            grop = $('select#grop option:selected').val();
            pdat = $('select#pdat option:selected').val();
            table.reload('manage',{
                where:{
                    csid:data.value,
                    key:msg,
                    grop:grop,
                    pdat:pdat
                }
            })
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
            }
        });

        $(".export-btn").on('click',function () {
            msg = $("#msg").val();
            grop = $('select#grop option:selected').val();
            pdat = $('select#pdat option:selected').val();
            csid = $('select#csid option:selected').val();
            var url = '${base}/task/printCndTask?grop=' + grop+'&pdat='+pdat+'&csid='+csid+'&key='+msg;
            $("<form action='"+url+"' method='post'></form>").appendTo("body").submit().remove();
        });

    });
</script>
</body>
</html>

