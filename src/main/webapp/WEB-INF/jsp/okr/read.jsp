<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/17
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>OKR预览</title>
    <kellyj:import url="../../static1.html"/>
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
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">管理对象：</label>${obj.ANAM}
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">直接上级：</label>${obj.BNAM}
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">管理周期：</label>${obj.MDAT}
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">提交类型：</label>${obj.TYPENAM}
                    </div>
                </td>
                <td colspan="3">
                    <div class="layui-form-item">
                        <label class="layui-form-label">提交身份：</label>${obj.AUTHNAM}
                    </div>
                </td>
            </tr>
            <tr id="okr_item_0_0">
                <th class="center" width="4%" nowrap="nowrap">
                    <label>序号</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>目标(O)</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>O周期</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>O类型</label>
                </th>
                <th class="center" width="5%" nowrap="nowrap">
                    <label>O权重(%)</label>
                </th>
                <th class="center" width="10%" nowrap="nowrap">
                    <label>O完成情况</label>
                </th>
                <th class="center" width="16%" nowrap="nowrap">
                    <label>关键成果(KRS)</label>
                </th>
                <th class="center" width="7%" nowrap="nowrap">
                    <label>KR权重(%)</label>
                </th>
                <th class="center" width="16%" nowrap="nowrap">
                    <label>KRS完成情况</label>
                </th>
                <th class="center" width="5%" nowrap="nowrap">
                    <label>自评分</label>
                </th>
                <th class="center" width="7%" nowrap="nowrap">
                    <label>领导评分</label>
                </th>
            </tr>
        </table>
        <a class="layui-hide form_render"></a>
    </div>
</div>
<script id="okr" type="text/html">
{{# layui.each(d,function(index,item){ }}
    {{# layui.each(item.tasks,function(num,task){ }}
        {{# if(num === 0){ }}
            <tr>
                <td class="center" rowspan="{{item.tasks.length}}">{{index+1}}</td>
                <td class="center" rowspan="{{item.tasks.length}}"><a href="javascript:" class="layui-table-link" onclick="showGoal($(this))">{{item.goal === undefined ? '': item.goal}}</a></td>
                <td class="center" rowspan="{{item.tasks.length}}">
                    {{ item.ndat === '4' ? '年度':(item.ndat === '3' ?  '半年度':(item.ndat === '2' ? '季度' : (item.ndat === '1' ? '月度' : ''))) }}
                </td>
                <td class="center" rowspan="{{item.tasks.length}}">
                    {{ item.type === 4 ? '创新类':(item.type === 3 ?  '管理类':(item.type === 2 ? '质量类' : (item.type === 1 ? '项目类' : ''))) }}
                </td>
                <td class="center" rowspan="{{item.tasks.length}}">{{item.prop === undefined ? '': item.prop}}</td>
                <td class="center" rowspan="{{item.tasks.length}}">{{item.perf === undefined ? '': item.perf}}</td>
                <td class="center"><a href="javascript:" class="layui-table-link" onclick="showGoal($(this))">{{task.achi === undefined ? '': task.achi}}</a></td>
                <td class="center">{{task.krprop === undefined ? '': task.krprop}}</td>
                <td class="center">{{task.krperf === undefined ? '': task.krperf}}</td>
                <td class="center">{{task.zgrad === undefined ? '': task.zgrad}}</td>
                <td class="center">{{task.mgrad === undefined ? '':task.mgrad}}</td>
            </tr>
        {{# }else{ }}
        <tr>
            <td class="center"><a href="javascript:" class="layui-table-link" onclick="showGoal($(this))">{{task.achi === undefined ? '': task.achi}}</a></td>
            <td class="center">{{task.krprop === undefined ? '': task.krprop}}</td>
            <td class="center">{{task.krperf === undefined ? '': task.krperf}}</td>
            <td class="center">{{task.zgrad === undefined ? '': task.zgrad}}</td>
            <td class="center">{{task.mgrad === undefined ? '':task.mgrad}}</td>
        </tr>
        {{# } }}
    {{# }); }}
{{# }); }}
</script>
<script language="JavaScript">
    layui.use(['form', 'table','jquery','laytpl'], function () {
        var form = layui.form, $ = layui.jquery, laytpl = layui.laytpl,getTpl = okr.innerHTML;
        var data = [];

        $.ajax({
            type:'POST',
            url:'./getSingleOkr?okid='+${obj.OKID},
            dataType:'json',
            success:function (res) {
                if(res.code === 1){
                    data = res.data.goals;
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
    });

    function showGoal(present) {
        layer.open({
           type:1,
           content:present.html(),
           area:['50%','50%'],
           offset:'10px',
            title:'内容'
        });
    }
</script>
</body>
</html>
