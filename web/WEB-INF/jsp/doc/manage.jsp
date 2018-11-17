<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/21
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>文档管理</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">文档</cite></a>
        <a href="javascript:location.replace(location.href)" style="cursor: pointer;"><cite>文档管理</cite></a>
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
                    <a href="./insert" class="layui-btn layui-btn-radius" lay-filter="set">创建</a>
                </div>
            </div>
        </div>
    </form>
    <form class="layui-form">
        <div class="layui-form-panel">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select name="ctyp" id="ctyp" lay-filter="ctyp" lay-search="">
                        <option value="" disabled selected>请选择文档类型</option>
                        <option value="1">周报</option>
                        <option value="2">智库</option>
                        <option value="3">公开文档</option>
                        <option value="4">学习文档</option>
                    </select>
                </div>

                <div class="layui-input-inline">
                    <input type="text" name="sdat" id="sdat" placeholder="请选择日期" class="layui-input" required/>
                </div>

                <div class="layui-input-inline">
                    <select name="week" id="week" lay-filter="week" lay-search="">
                        <option value=""  disabled selected>请选择周数</option>
                        <option value="1" >第一周</option>
                        <option value="2" >第二周</option>
                        <option value="3" >第三周</option>
                        <option value="4" >第四周</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="grop" id="grop" lay-verify="grop" class="grop" lay-filter="grop" lay-search="">
                        <option value="" class="n-display" disabled selected>请选择团队</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-bg-black delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{url:'./queryAllDoc',initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
            <th lay-data="{field:'doid',align:'center',width:150,sort:true}">编号</th>
            <th lay-data="{field:'tilt',align:'center',width:350,toolbar:'#noteTpl'}">标题</th>
            <th lay-data="{field:'cdat',align:'center',width:250,sort:true}">创建时间</th>
            <th lay-data="{field:'unam',align:'center',width:150}">创建用户</th>
            <th lay-data="{field:'cnam',align:'center',width:150}">接收用户</th>
            <th lay-data="{field:'ctypnam',align:'center',width:150}">类型</th>
            <th lay-data="{field:'statnam',align:'center',width:150}">权限</th>
            <th lay-data="{field:'sta2nam',align:'center',width:150}">状态</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="noteTpl">
        <a href="javascript:" class="layui-table-link" lay-event="show">{{d.tilt}}</a>
    </script>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-xs" href="./edit?doid={{d.doid}}">编辑</a>
        <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
    </div>

</div>
<script language="JavaScript">
    layui.use(['form','jquery','table','layer','element','laydate'],function () {
        var $ = layui.jquery,form = layui.form,table = layui.table,
            layer = layui.layer,elemnt = layui.element,laydate=layui.laydate;

        form.on("submit(search)",function (data) {
            tableLoad();

            return false;
        });

        function tableLoad(value){
            var key = $("#msg").val();
            var ctyp = $("select[name='ctyp'] option:selected").val();
            var week = $("#week option:selected").val();
            var sdat = $("#sdat").val()
            if(!checkForm(value)){
                sdat = value;
            }
            var grop = $("#grop option:selected").val();
            table.reload("manage",{
                where:{
                    key:key,
                    ctyp:ctyp,
                    sdat:sdat,
                    week:week,
                    grop:grop
                }
            });
        }
        laydate.render({
            elem: '#sdat',
            type: 'month',
            done:function (value,obj) {
                tableLoad(value);
            }
        });

        form.on("select(ctyp)", function (data) {
            tableLoad();
        });

        form.on("select(week)", function (data) {
            tableLoad();
        });

        form.on("select(grop)", function (data) {
            tableLoad();
        });



        $.ajax({
            type:'GET',
            url:'${base}/util/findC9',
            dataType:'json',
            success:function (data) {
                var grops = data.c9;
                var option = "";
                for(var i = 0;i<grops.length;i++){


                    option += "<option value='"+grops[i].id+"' class='n-display'>"+grops[i].dsca+"</option>";
                }
                $("#grop").append(option);
                form.render();
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });

        table.on('tool(manage)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        type:'POST',
                        url:'${base}/doc/deleteDoc',
                        data:{
                            doid:data.doid
                        },
                        dataType:'json',
                        success:function (res) {
                            if(res.code === 1){
                                layer.confirm("删除成功",{btn:['确认'],offset:'10px'},function () {
                                    obj.del();
                                    layer.closeAll();
                                });
                            }else{
                                layer.alert(res.msg,{offset:'10px'});
                            }
                        },
                        error:function (kj) {
                            layer.alert("发生错误:"+kj.status,{offset:'10px'});
                        }
                    });
                });
            }else if(obj.event === 'show'){
                layer.open({
                    type:2,
                    content:['./showDoc?doid='+data.doid],
                    area: ['90%', '70%'],
                    title:'文档',
                    offset:'10px'
                });
            }
        });

        $(".delete-btn").on("click",function () {
            var check = table.checkStatus('manage');
            var data = check.data;
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].doid;
            }
            $.ajax({
                type:'POST',
                url:'${base}/doc/deleteDoc',
                data:{
                    list:param
                },
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm("删除成功",{btn:['确认'],offset:'10px'},function (index) {
                            table.reload("manage");
                            layer.close(index)
                        });
                    }else{
                        layer.alert(res.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });
    });
</script>
</body>
</html>
