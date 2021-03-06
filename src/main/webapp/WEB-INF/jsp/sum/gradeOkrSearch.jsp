<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/1/7
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>科室员工绩效反馈</title>
    <c:import url="../../static1.html"/>
    <style>
        .top_container{
            display: flex;
            display: -webkit-flex;
            display: -moz-flex;
            flex-wrap: nowrap;
            align-items: flex-start;
        }
        .left_container{
            flex: 0 1 79%;
            margin: 1%;
            padding: 20px;
        }
        .right_container{
            flex: 0 1 19%;
            margin: 1%;
            padding: 20px;
        }
    </style>
</head>

<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>

<div class="x-body top_container">
    <div class="left_container">
        <hr class="layui-bg-green">
        <form action="../grade/printGradeOkr" class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <label class="layui-form-label">考评科室</label>
                <span id="accoTip"></span>
                <div class="layui-input-block">
                    <select name="officeNumber" id="acco" lay-filter="acco" lay-verify="acco" lay-search>
                        <option value="" disabled selected>选择科室</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">考评月份</label>
                <div class="layui-input-block">
                    <input type="text" name="pdat" id="pdat" placeholder="考评月份" lay-verify="mdat" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">S绩效</label>
                <div class="layui-input-block">
                    <input type="text" name="S" id="S" placeholder="请录入S绩效的数量" lay-verify="S|number" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">A绩效</label>
                <div class="layui-input-block">
                    <input type="text" name="A" id="A" placeholder="请录入A绩效的数量" lay-verify="A|number" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">B+绩效</label>
                <div class="layui-input-block">
                    <input type="text" name="BPlus" id="BPlus" placeholder="请录入B+绩效的数量" lay-verify="BPlus|number" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">C绩效</label>
                <div class="layui-input-block">
                    <input type="text" name="C" id="C" placeholder="请录入C绩效的数量" lay-verify="C|number" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item x-center">
                <div class="layui-input-block">
                    <input type="submit" class="layui-btn layui-btn-radius layui-bg-red" id="table" value="生成绩效" lay-filter="table" lay-submit/>
                    <input type="submit" class="layui-btn layui-btn-radius" id="export" value="导出绩效" lay-filter="export" lay-submit/>
                    <input type="reset" class="layui-btn layui-btn-radius" value="重置" lay-submit/>
                </div>
            </div>
        </form>
    </div>
    <div class="right_container">
        <hr class="layui-bg-green">
        <div id="printOldGrade">
            
        </div>
    </div>
</div>

<div class="x-body">
    <table class="layui-hide" id="gradeOkr" lay-filter="gradeOkr">

    </table>
</div>
<script language="JavaScript">
    layui.use(['element','form','jquery','laydate','table'],function () {
        var element = layui.element,form = layui.form,$ = layui.jquery,
            laydate = layui.laydate,table = layui.table;

        var start = {
            elem: '#pdat',
            type: 'month',
            max:0,
            done: function (value,obj) {
            }
        };
        var printOldGrade = {
            elem:'#printOldGrade',
            type:'month',
            position:'static',
            max:0,
            btns:['now','confirm'],
            done: function (value,obj) {
                let officeNumber = $("#acco option:selected").val();
                if(!checkForm(officeNumber)){
                    var url = "../grade/printOldGradeOkr?pdat="+value+"&officeNumber="+officeNumber;
                    $("<form action='"+url+"' method='post'></form>").appendTo("body").submit().remove();
                }else{
                    layer.tips("请选择科室","#accoTip",{tips:[1,'#dd414c']})
                }
            }
        };
        laydate.render(start);
        laydate.render(printOldGrade);

        form.on("submit(table)",function () {
            table.render({
                elem:'#gradeOkr',
                url:'../grade/gradeOkrData',
                cellMinWidth:200,
                page:false,
                where:{
                    pdat:$("#pdat").val(),
                    officeNumber: $("#acco option:selected").val(),
                    S:$("#S").val(),
                    A:$("#A").val(),
                    BPlus:$("#BPlus").val(),
                    C:$("#C").val()
                },
                cols:[[
                    {field:'cpid',title:'员工编号',align:'center'},
                    {field:'dsca',title:'员工姓名',align:'center'},
                    {field:'score',title:'分数',align:'center'},
                    {field:'stage',title:'等级',align:'center'}
                ]],
                response:{
                    statusCode:1
                }
            });
           return false;
        });

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



        form.verify({
            mdat:function (value) {
                if(checkForm(value)){
                    return "请选择月份";
                }
            },
            acco:function (value) {
                if(checkForm(value)){
                    return "请选择科室";
                }
            },
            S:function (value) {
                if(checkForm(value)){
                    return "请录入绩效为S的数量";
                }
            },
            A:function (value) {
                if(checkForm(value)){
                    return "请录入绩效为A的数量";
                }
            },
            BPlus:function (value) {
                if(checkForm(value)){
                    return "请录入绩效为B+的数量";
                }
            },
            C:function (value) {
                if(checkForm(value)){
                    return "请录入绩效为C的数量";
                }
            }
        });
    });
</script>
</body>
</html>
