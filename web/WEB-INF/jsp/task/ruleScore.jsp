<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/14
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>评分细节</title>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(["laydate","laypage","element","layer","table","jquery","form"],function () {
            var laypage = layui.laypage,
                element = layui.element,
                layer = layui.layer,
                table = layui.table,
                form = layui.form,
                $ = layui.jquery;

            form.on("submit(set)",function (data) {
                var infor = data.field;
                $.ajax({
                    type:'POST',
                    url:'${base}/task/insertRuleScore',
                    data:{
                        'pjno':infor.pjno,
                        "opco":infor.score,
                        "remk":infor.remk
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            layer.alert(res.msg);
                            table.reload("score");
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

            table.on('tool(score)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        $.ajax({
                            type:'POST',
                            url:'${base}/task/deleteRuleScore',
                            data:{
                                'pjno':data.pjno,
                                "opco":data.opco
                            },
                            dataType:'json',
                            success:function (res) {
                                if(res.code === 1){
                                    layer.alert(res.msg);
                                    obj.del();
                                    layer.close(index);
                                }else{
                                    layer.alert(res.msg);
                                }
                            },
                            error:function (kj) {
                                layer.alert("发生错误:"+kj.status);
                            }
                        });
                    });
                }
            });

            $(".delete-btn").on("click",function () {
                var check = table.checkStatus('score');
                var data = check.data;
                $.ajax({
                    type:'POST',
                    url:'${base}/task/deleteRuleScore',
                    data:{
                        list:data
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            layer.alert(res.msg);
                            table.reload("score")
                        }else{
                            layer.alert(res.msg);
                        }
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status);
                    }
                });
            });

        });
    </script>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="./rule"><cite style="cursor: pointer;">任务规则</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">评分细节</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="score" id="score" placeholder="请输入创建分值" lay-verify="required|score" class="layui-input" required/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="remk" id="remk" placeholder="请输入备注信息" lay-verify="remk" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-radius" lay-filter="set" lay-submit>创建</button>
                </div>
                <div class="layui-input-inline">
                    <input type="hidden" name="pjno" value="${obj}"/>
                </div>
            </div>
        </div>
    </form>
    <div class="layui-inline">
        <button class="layui-btn layui-btn-danger delete-btn"><i class="layui-icon">&#xe640;</i>批量删除</button>
    </div>
    <table class="layui-table" lay-data="{height:'full-400',url:'${base}/task/queryAllRuleScore?pjno=${obj}',id:'score'}" lay-filter="score">
        <thead>
        <tr>
            <th lay-data="{checkbox:true,width:50}"></th>
            <th lay-data="{field:'pjno',width:150}">编号</th>
            <th lay-data="{field:'opco',edit:true,width:150}">分值</th>
            <th lay-data="{field:'remk',width:150}">备注</th>
            <th lay-data="{fixed: 'right', toolbar: '#operate', width:150, align:'center'}">操作</th>
        </tr>
        </thead>
    </table>
    <div class="layui-hide" id="operate">
        <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>
    </div>
    <br/><br/><br/><br/><br/><br/><br/><br/>
</div>
</body>
</html>


