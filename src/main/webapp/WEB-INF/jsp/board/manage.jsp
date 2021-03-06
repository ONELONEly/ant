<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/8
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>看板</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">看板</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="8" class="layui-form-radio" title="所有任务" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="0" class="layui-form-radio" title="未下发"/><span class="layui-badge" id="new"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="1" class="layui-form-radio" title="已下发"/><span class="layui-badge" id="in1"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="2" class="layui-form-radio" title="执行中"/><span class="layui-badge" id="in2"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="3" class="layui-form-radio" title="变更"/><span class="layui-badge" id="in3"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="4" class="layui-form-radio" title="交付不通过"/><span class="layui-badge" id="in4"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="5" class="layui-form-radio" title="测试中"/><span class="layui-badge" id="testS"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="6" class="layui-form-radio" title="验收中"/><span class="layui-badge" id="check"></span>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="choose" value="7" class="layui-form-radio" title="已完成"/><span class="layui-badge" id="done"></span>
                </div>
            </div>
            <hr class="layui-bg-cyan"/>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="msg" id="msg" placeholder="请输入查询信息" lay-verify="msg" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn" lay-filter="search" lay-submit>查询</button>
                </div>
                <div class="layui-input-inline">
                    <select name="ptno" id="ptno" lay-filter="ptno" lay-search="">
                        <option value="" class="n-select" disabled selected>过滤绩效表</option>
                    </select>
                </div>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="put" id="put" lay-submit>下发</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="carry" id="carry" lay-submit>执行</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="return" id="return" lay-submit>驳回</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="modify" id="modify" lay-submit>更改</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="test" id="test" lay-submit>交付测试</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="get" id="get" lay-submit>交付验收</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="tPass" id="tPass" lay-submit>测试通过</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="tReturn" id="tReturn" lay-submit>测试不通过</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="rPass" id="rPass" lay-submit>验收通过</button>
                <button type="button" class="layui-btn layui-bg-black n-display" lay-filter="rReturn" id="rReturn" lay-submit>验收不通过</button>
            </div>
        </div>
    </form>
    <div class="layui-box">
        <table class="layui-table" lay-data="{url:'${base}/task/queryAllGropTask',initSort:{field:'cdat',type:'desc'},initSort:{field:'cdat',type:'desc'},page:true,limit:10,limits:[10,15,20,25,30,50],id:'manage'}" lay-filter="manage">
            <thead>
            <tr>
                <th lay-data="{checkbox:true,width:50,fixed:true}"></th>
                <th lay-data="{field:'perc',align:'center',width:150,sort:true}">完成度</th>
                <th lay-data="{field:'titl',align:'center',width:350,toolbar:'#noteTpl'}">标题</th>
                <th lay-data="{field:'ptnonam',align:'center',width:350}">绩效表主题</th>
                <th lay-data="{field:'synonam',align:'center',width:250}">系统</th>
                <th lay-data="{field:'unam',align:'center',width:150}">创建人</th>
                <th lay-data="{field:'cnam',align:'center',width:150}">派发给</th>
                <th lay-data="{field:'ptypnam',align:'center',width:150}">评分类型</th>
                <th lay-data="{field:'sta1nam',align:'center',width:150}">状态</th>
                <th lay-data="{field:'punonam',align:'center',width:150}">任务类型</th>
                <th lay-data="{field:'knam',align:'center',width:150}">关键用户</th>
                <th lay-data="{field:'tnam',align:'center',width:150}">测试用户</th>
                <th lay-data="{field:'sta2nam',align:'center',width:150}">优先级</th>
                <th lay-data="{field:'sta3nam',align:'center',width:150}">严重程度</th>
                <th lay-data="{field:'adat',align:'center',width:150,sort:true}">执行时间</th>
                <th lay-data="{field:'pdat',align:'center',width:150,sort:true}">计划时间</th>
                <th lay-data="{field:'tdat',align:'center',width:150,sort:true}">测试时间</th>
                <th lay-data="{field:'fdat',align:'center',width:150,sort:true}">验收时间</th>
                <th lay-data="{field:'cdat',align:'center',width:150,sort:true}">创建时间</th>
                <th lay-data="{field:'fahh',align:'center',width:150,sort:true}">工时</th>
            </tr>
            </thead>
        </table>
        <script type="text/html" id="noteTpl">
            <a href="javascript:" class="layui-table-link" lay-event="show">{{d.titl}}</a>
        </script>
    </div>
</div>
<div class="layui-form-item carryUtil n-display">
    <input type="text" name="finishTime" id="finishTime" placeholder="请选择开始时间" class="layui-input x-center"/>
    <input type="text" name="date" id="dateUtil" placeholder="请选择完成时间" class="layui-input x-center"/>
    <input type="text" name="fahh" id="fahhUtil" placeholder="请输入工时(如:2.5)" class="layui-input x-center"/>
</div>
<script language="JavaScript">
    layui.use(['jquery','element','table','laydate','layer','form'],function () {
        var $ = layui.jquery,element = layui.element,table = layui.table,
            laydate = layui.laydate,layer = layui.layer,form = layui.form,
            msg = "",choose = "",ptno = "";

        sessionStorage.setItem("token","${obj}")

        form.on("submit(search)",function (data) {
            var infor = data.field;
            ptno = $("select#ptno option:selected").val();
            choose = $("input[name='choose']:checked").val();
            getCount();
            table.reload("manage",{
                where:{
                    key:infor.msg,
                    ptno:ptno,
                    sta:choose
                },
                page:{
                    curr:1
                }
            });
            return false;
        });

        form.on("select(ptno)",function (data) {
            msg = $("#msg").val();
            choose = $("input[name='choose']:checked").val();
            getCount();
            table.reload('manage',{
                where:{
                    ptno:data.value,
                    key:msg,
                    sta:choose
                },
                page:{
                    curr:1
                }
            })
        });

        laydate.render({
            elem:'#dateUtil',
            type:'datetime',
            theme:'#393D49',
            trigger: 'click'
        });

        form.on('radio',function (data) {
            var value = data.value;
            if(value === '0') {
                $("#put").show();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").hide();
                $("#test").hide();
                $("#get").hide();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '1'){
                $("#put").hide();
                $("#carry").show();
                $("#return").show();
                $("#modify").hide();
                $("#test").hide();
                $("#get").hide();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '2'){
                $("#put").hide();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").hide();
                $("#test").show();
                $("#get").show();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '3'){
                $("#put").hide();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").hide();
                $("#test").show();
                $("#get").show();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '4'){
                $("#put").hide();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").show();
                $("#test").hide();
                $("#get").hide();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '5'){
                $("#put").hide();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").hide();
                $("#test").hide();
                $("#get").hide();
                $("#tPass").show();
                $("#tReturn").show();
                $("#rPass").hide();
                $("#rReturn").hide();
            }else if(value === '6'){
                $("#put").hide();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").hide();
                $("#test").hide();
                $("#get").hide();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").show();
                $("#rReturn").show();
            }else{
                $("#put").hide();
                $("#carry").hide();
                $("#return").hide();
                $("#modify").hide();
                $("#test").hide();
                $("#get").hide();
                $("#tPass").hide();
                $("#tReturn").hide();
                $("#rPass").hide();
                $("#rReturn").hide();
            }
            msg = $("#msg").val();
            ptno = $("select#ptno option:selected").val();
            table.reload("manage",{
                where:{
                    sta:value,
                    key:msg,
                    ptno:ptno
                },
                page:{
                    curr:1
                }
            });
        });

        table.on('tool(manage)',function (obj) {
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

        form.on('submit(put)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,1,"执行下发行为",null,null,null,null);
        });

        form.on('submit(carry)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.open({
                type:1,
                title:'请选择完成时间',
                area:'20%',
                content:$(".carryUtil"),
                btn:['确认'],
                anim:4,
                offset:'10px',
                yes:function () {
                    var value = $("#dateUtil").val(),fahh = $("#fahhUtil").val(),finish = $("#finishTime").val();;
                    if (checkForm(finish)) {
                        layer.tips('请选择开始时间','#finishTime');
                    } else if (checkForm(value)) {
                        layer.tips('请选择完成时间','#dateUtil');
                    } else if(checkForm(fahh)){
                        layer.tips("请输入工时","#fahhUtil");
                    } else {
                        layer.prompt({title: '请输入备注信息！', formType: 2, offset: '50px'}, function (remk) {
                            operate(data,2,remk,value,null,fahh,finish);
                            layer.closeAll();
                        });
                    }
                }
            });
        });

        form.on('submit(return)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,0,"执行驳回行为",null,null,null,null);
        });

        form.on('submit(modify)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.prompt({title:'请输入备注信息！',formType:2,offset:'10px'},function (remk,index) {
                operate(data,4,remk,null,null,null,null);
                layer.close(index);
            });
        });

        form.on('submit(test)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.prompt({title:'请输入备注信息！',formType:2,offset:'10px'},function (remk,index) {
                operate(data,5,remk,null,null,null,null);
                layer.close(index);
            });
        });

        form.on('submit(get)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,6,"执行交付校验行为",null,null,null,null);
        });

        form.on('submit(tPass)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,6,"执行测试通过行为",null,null,null,null);
        });

        form.on('submit(tReturn)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.prompt({title:'请输入反馈信息！',formType:2,offset:'10px'},function (remk,index) {
                operate(data,8,remk,null,null,null,null);
                layer.close(index);
            });
        });

        form.on('submit(rPass)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            operate(data,11,"执行校验通过行为",null,null,null,null);
        });

        form.on('submit(rReturn)',function () {
            var choose = table.checkStatus('manage');
            var data = choose.data;
            layer.prompt({title:'请输入反馈信息！',formType:2,offset:'10px'},function (remk,index) {
                operate(data,10,remk,null,null,null,null);
                layer.close(index);
            });
        });



        function operate(data,code,remk,date,stage,fahh,fini){
            var param = {};
            for(var i = 0;i < data.length;i++){
                param[i] = data[i].taid;
            }
            $.ajax({
                type:'POST',
                url:'${base}/task/updateSta1',
                data:{
                    list:param,
                    operate:code,
                    remk:remk,
                    date:date,
                    fahh:fahh,
                    fini:fini,
                    token:sessionStorage.getItem("token")
                },
                dataType:'json',
                success:function (res) {
                    if(res.code !== 506){
                        getCount();
                        table.reload("manage");
                    }
                    sessionStorage.setItem("token",res.data)
                    return layer.msg(res.msg,{offset:'10px'});
                },
                error:function (kellyj) {
                    layer.alert("发生错误,错误码为:"+kellyj.status);
                }
            });
        }

        function  getCount() {
            msg = $("#msg").val();
            ptno = $("select#ptno option:selected").val();
            $.ajax({
                type:'POST',
                url:'${base}/util/getCount',
                data:{
                    key:msg,
                    ptno:ptno
                },
                dataType:'json',
                success:function (res) {
                    $("#in1").text(res.put);
                    $("#in2").text(res.carry);
                    $("#in3").text(res.modify);
                    $("#in4").text(res.back);
                    $("#new").text(res.new);
                    $("#testS").text(res.test);
                    $("#check").text(+res.check);
                    $("#done").text(res.done);
                },
                error:function (kellyj) {
                    layer.alert("发生错误,错误码为:"+kellyj.status);
                }
            });
        }

        $(document).ready(function () {
            getCount();
            $.ajax({
                type:'GET',
                url:'${base}/util/findT1',
                dataType:'json',
                success:function (res) {
                    var type = res.t1,tOption = "";
                    for (var m = 0; m < type.length; m++) {
                        tOption += "<option value='" + type[m].ptno + "'>" + type[m].dsca + "</option>";
                    }
                    $("#ptno").append(tOption);
                    form.render();
                },
                error:function (kellyj) {
                    return layer.msg("发生错误,错误码:"+kellyj.status,{offset:'10px'});
                }
            });
        });

    });
</script>
</body>
</html>