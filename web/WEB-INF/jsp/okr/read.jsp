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
</head>
<body>
<div class="x-body">
    <div class="layui-form layui-form-panel" style="padding-bottom:50px;">
        <table class="layui-table">
            <tr>
                <td colspan="10" class="x-center">OKR管理表</td>
                <td class="none_border">
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">管理对象：</label>
                        <div class="layui-input-block">
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">直接上级：</label>
                        <div class="layui-input-block">
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label class="layui-form-label">管理周期：</label>
                    </div>
                </td>
                <td class="none_border">

                </td>
            </tr>
            <tr id="okr_item_0_0">
                <th class="center" width="4%">
                    <label>序号</label>
                </th>
                <th class="center" width="6%">
                    <label>目标（O）</label>
                </th>
                <th class="center" width="10%">
                    <label>周期</label>
                </th>
                <th class="center" width="10%">
                    <label>类型</label>
                </th>
                <th class="center" width="5%">
                    <label>权重</label>
                </th>
                <th class="center" width="10%">
                    <label>完成情况</label>
                </th>
                <th class="center" width="20%">
                    <label>关键成果</label>
                </th>
                <th class="center" width="7%">
                    <label>KR权重</label>
                </th>
                <th class="center" width="18%">
                    <label>KR完成情况</label>
                </th>
                <th class="center" width="8%">
                    <label>自评分</label>
                </th>
                <th class="none_border" width="2%">
                </th>
            </tr>
        </table>
        <a class="layui-hide form_render"></a>
    </div>
</div>
<script language="JavaScript">
    layui.use(['form', 'table','jquery','laytpl'], function () {
        var form = layui.form, $ = layui.jquery, postDataItem, laydate = layui.laydate;
    }
</script>
</body>
</html>
