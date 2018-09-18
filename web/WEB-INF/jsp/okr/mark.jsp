<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/18
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>OKR领导评分</title>
    <kellyj:import url="../../static1.html"/>
    <link rel="stylesheet" href="../static/css/okr.css" media="all">
    <style>
        .layui-form-label{
            padding:0;
        }
    </style>
</head>
<body>
<div class="x-body">
    <div class="layui-form layui-form-panel" style="padding-bottom:50px;">
        <table class="layui-table">
            <tr>
                <td colspan="11" class="x-center">OKR管理表</td>
            </tr>
            <tr>
                <td colspan="6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">管理对象：</label>${obj.ANAM}
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">直接上级：</label>${obj.BNAM}
                    </div>
                </td>
                <td colspan="3">
                    <div class="layui-form-item">
                        <label class="layui-form-label">管理周期：</label>${obj.MDAT}
                    </div>
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
                <th class="center" width="18%">
                    <label>关键成果(KRS)</label>
                </th>
                <th class="center" width="7%">
                    <label>KR权重</label>
                </th>
                <th class="center" width="18%">
                    <label>KRS完成情况</label>
                </th>
                <th class="center" width="5%">
                    <label>自评分</label>
                </th>
                <th class="center" width="7%">
                    <label>领导评分</label>
                </th>
            </tr>
        </table>
        <a class="layui-hide form_render"></a>
    </div>
</div>
<div class="x-center x-body" style="bottom: 0;position: fixed;width: 100%;height:50px;background-color:#fff;z-index: 1;">
    <div class="layui-input-inline">
        <button class="layui-btn layui-btn-radius" id="mark">评分</button>
    </div>
    <div class="layui-input-inline">
        <a class="layui-btn layui-btn-radius layui-bg-gray" href="./index">返回</a>
    </div>
</div>
<script id="okr" type="text/html">
    {{# layui.each(d,function(index,item){ }}
    {{# layui.each(item.tasks,function(num,task){ }}
    {{# if(num === 0){ }}
    <tr>
        <td class="center" rowspan="{{item.tasks.length}}">{{index+1}}</td>
        <td class="center" rowspan="{{item.tasks.length}}"><a href="javascript:" class="layui-table-link" onclick="showGoal($(this))">{{item.goal}}</a></td>
        <td class="center" rowspan="{{item.tasks.length}}">
            {{ item.ndat === '4' ? '年度':(item.ndat === '3' ?  '季度':(item.ndat === '2' ? '半季度' : (item.ndat === '1' ? '月度' : ''))) }}
        </td>
        <td class="center" rowspan="{{item.tasks.length}}">
            {{ item.type === 4 ? '创新类':(item.type === 3 ?  '管理类':(item.type === 2 ? '质量类' : (item.type === 1 ? '项目类' : ''))) }}
        </td>
        <td class="center" rowspan="{{item.tasks.length}}">{{item.prop}}</td>
        <td class="center" rowspan="{{item.tasks.length}}">{{item.perf}}</td>
        <td class="center"><a href="javascript:" class="layui-table-link" onclick="showGoal($(this))">{{task.achi}}</a></td>
        <td class="center">{{task.krprop}}</td>
        <td class="center" rowspan="{{item.tasks.length}}">{{item.krperf}}</td>
        <td class="center" rowspan="{{item.tasks.length}}">{{item.zgrad}}</td>
        <td class="center" rowspan="{{item.tasks.length}}">
            <input type="text" name="mgrad_{{item.goal_id}}" id="mgrad_{{item.goal_id}}" placeholder="请输入自评分" autocomplete="off" class="excel_input">
        </td>
    </tr>
    {{# }else{ }}
    <tr>
        <td class="center" onclick="showGoal($(this))">{{task.achi}}</td>
        <td class="center">{{task.krprop}}</td>
    </tr>
    {{# } }}
    {{# }); }}
    {{# }); }}
</script>
<script language="JavaScript">
    layui.use(['form', 'table','jquery','layer',"laytpl"], function () {
        var form = layui.form,$ = layui.jquery,layer = layui.layer,
            laytpl = layui.laytpl,getTpl = okr.innerHTML;

        $.ajax({
            type:'POST',
            url:'./getSingleOkr?okid='+${obj.OKID},
            dataType:'json',
            success:function (res) {
                if(res.code === 1){
                    var data = res.data.goals;
                    laytpl(getTpl).render(data,function (html) {
                        $(".layui-table").append(html)
                    });
                }else{
                    layer.msg(res.msg,{offset:'10px'})
                }
            },
            error:function (kellyj) {
                layer.alert("发生错误，错误码为:"+kellyj.status);
            }
        });

        $("#mark").click(function () {
            var formAll = $(".layui-form").children();
            var inputs = formAll.find("input");
            var param = addToParam(null,inputs);
            if(checkFormData(param)){
                var postParam = [];
                for(var item in param){
                    var data = {
                        id:'',
                        value:''
                    };
                    var paramItem = param[item];
                    data.id = paramItem.name.substring(paramItem.name.indexOf("_")+1);
                    data.value = paramItem.value;
                    postParam.push(data);
                }
                $.ajax({
                    type:'POST',
                    url:'./mark',
                    data:{
                        list:postParam
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.code === 1){
                            layer.confirm("成功评分，返回主界面？",{btn:['确定返回',"刷新"],offset:'10px',anim:4},function () {
                                window.location.replace("./index");
                            },function () {
                                window.location.reload();
                            });
                        }else{
                            layer.alert(data.msg,{offset:'10px',anim:4});
                        }
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status,{offset:'10px'});
                    }
                });
            }
        });
    });
</script>
<script src="../static/js/okrInsert.js"></script>
</body>
</html>
