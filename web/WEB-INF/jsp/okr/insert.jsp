<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/3
  Time: 14:24
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
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite>设置</cite></a>
        <a href="javascript:location.replace('./index')"><cite>OKR管理</cite></a>
        <a href="javascript:location.replace(location.href)"><cite>OKR添加</cite></a>
        <a href="javascript:location.replace(location.href)" class="layui-btn layui-btn-radius layui-btn-sm l-refresh" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body layui-container">
    <form class="layui-form layui-form-panel">
        <div class="layui-form-item">
            <label for="asid" class="layui-form-label">管理对象</label>
            <div class="layui-input-block" style="width: 86%;">
                <select name="ASID" lay-filter="asid" lay-verify="asid" id="asid" lay-search>
                    <option value="" style="display:none;" disabled selected>请选择管理对象</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="boss" class="layui-form-label">直接上级</label>
            <div class="layui-input-block" style="width: 86%;">
                <select name="BOSS" lay-filter="boss" id="boss" lay-verify="boss" lay-search>
                    <option value="" style="display:none;" disabled selected>请选择直接上级</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">管理周期</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="text" name="MDAT" id="mdat" placeholder="请选择管理周期" lay-verify="mdat" autocomplete="off" class="layui-input" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="goal" class="layui-form-label">目标</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="text" name="GOAL" id="goal" placeholder="请输入目标" lay-verify="goal" autocomplete="off" class="layui-input" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="ndat" class="layui-form-label">周期</label>
            <div class="layui-input-block" style="width: 86%;">
                <select name="NDAT" id="ndat" lay-verify="ndat" lay-search="">
                    <option value="" class="n-display" disabled selected>请选择周期</option>
                    <option value="1" class="n-display">月度</option>
                    <option value="2" class="n-display">季度</option>
                    <option value="3" class="n-display">半年度</option>
                    <option value="4" class="n-display">年度</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="type" class="layui-form-label">类型</label>
            <div class="layui-input-block" style="width: 86%;">
                <select name="TYPE" lay-filter="type" id="type" lay-search>
                    <option value="" style="display:none;" disabled selected>请选择类型</option>
                    <option value="1" class="n-display">项目类</option>
                    <option value="2" class="n-display">质量类</option>
                    <option value="3" class="n-display">管理类</option>
                    <option value="4" class="n-display">创新类</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="prop" class="layui-form-label">权重</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="number" min="0" max="100" name="PROP" id="prop" placeholder="请输入权重" lay-verify="prop|number|grad" autocomplete="off" class="layui-input" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="perf" class="layui-form-label">完成情况</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="text" name="PERF" id="perf" placeholder="请输入完成情况" lay-verify="perf" autocomplete="off" class="layui-input" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="achi" class="layui-form-label">关键成果</label>
            <div class="layui-input-inline" style="width: 86%;">
                <input type="text" name="ACHI" id="achi" placeholder="请输入关键成果" lay-verify="achi" autocomplete="off" class="layui-input" required/>
            </div>
            <div class="layui-input-inline" style="float: right;width: 2%;">
                <a href="javascript:" id="search" style="text-align: center;line-height:50px;"><i class="layui-icon">&#xe615;</i></a>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="krprop" class="layui-form-label">KR权重</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="number" min="0" max="100" name="KRPROP" id="krprop" placeholder="请输入KR权重" lay-verify="krprop|number|grad" autocomplete="off" class="layui-input" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="krperf" class="layui-form-label">KR完成情况</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="text" name="KRPERF" id="krperf" placeholder="请输入KR完成情况" lay-verify="krperf" autocomplete="off" class="layui-input" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="zgrad" class="layui-form-label">自评分</label>
            <div class="layui-input-block" style="width: 86%;">
                <input type="number" min="0" max="100" name="ZGRAD" id="zgrad" placeholder="请输入自评分" lay-verify="zgrad|number|grad" autocomplete="off" class="layui-input" required/>
            </div>
        </div>

        <div class="layui-form-item x-center">
            <div class="layui-input-block" style="width: 86%;">
                <input type="submit" class="layui-btn layui-btn-radius" id="submit" value="提交" lay-filter="insert" lay-submit/>
                <input type="reset" class="layui-btn layui-btn-radius" value="重置" lay-submit/>
            </div>
        </div>

    </form>
</div>
<script language="JavaScript">
    layui.use(['form','element','jquery','layer','laydate'],function () {
        var $ = layui.jquery,form = layui.form,
            element = layui.element,layer = layui.layer
            ,laydate = layui.laydate;

        var start = {
            elem:'#mdat',
            type:'month',
            choose: function (value) {
                console.log(value);
            }
        };

        laydate.render(start);

        $.ajax({
            type:'GET',
            url:'${base}/util/findC0',
            dataType:'json',
            success:function (data) {
                var user = data.c0;
                var option = "";
                for(var i = 0;i<user.length;i++){
                    option += "<option value='"+user[i].id+"' class='n-display'>"+user[i].dsca+"</option>";
                }
                $("#asid").append(option);
                $("#boss").append(option);
                form.render();
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status);
            }
        });

        $("#search").click(
            function () {
                layer.open({
                    type:2,
                    id:'taskChoose',
                    content:['./taskChoose'],
                    area: ['60%', '70%'],
                    title:'查询',
                    offset:'10px'
                })
            }
        );

        form.verify({
            asid:function (value) {
                console.log(value);
                if(value === null || value.length === 0){
                    return "请选择管理对象";
                }
            },
            boss:function (value) {
                if(value === null || value.length === 0){
                    return "请选择直接上级";
                }
            },
            mdat:function (value) {
                if(value === null || value.length === 0){
                    return "请选择管理周期";
                }
            },
            goal:function (value) {
                if(value.length === 0){
                    return "请输入目标";
                }
            },
            ndat:function (value) {
                if(value === null || value.length === 0){
                    return "请选择周期";
                }
            },
            type:function (value) {
                if(value === null || value.length === 0){
                    return "请选择类型";
                }
            },
            prop:function (value) {
                if(value.length === 0){
                    return "请输入比重";
                }
            },
            perf:function (value) {
                if(value.length === 0){
                    return "请输入完成情况";
                }
            },
            achi:function (value) {
                if(value.length === 0){
                    return "请输入关键成果";
                }
            },
            krprop:function (value) {
                if(value.length === 0){
                    return "请输入KR比重";
                }
            },
            krperf:function (value) {
                if(value.length === 0){
                    return "请输入KR完成情况";
                }
            },
            zgrad:function (value) {
                if(value.length === 0){
                    return "请输入自评分";
                }
            },
            grad:function (value) {
                if(value > 100 || value < 0){
                    return "请输入0--100的评分";
                }
            }
        });

        form.on("submit(insert)",function (data) {
            console.log(data.field);
            $.ajax({
                type:'POST',
                url:'./insert',
                data:data.field,
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm(res.msg,{btn:['确定','返回'],offset:'100px',anim:4},function () {
                            window.location.reload();
                        },function () {
                            window.location.replace("./index");
                        });
                    }else{
                        layer.alert(res.msg);
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
