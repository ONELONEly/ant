<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/4
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <title>测试界面</title>
    <kellyj:import url="../../static1.html"/>
</head>
<body>
<div class="x-body">
    <div class="layui-form layui-form-panel">
        <table class="layui-table">
            <tr>
                <td colspan="11" class="x-center">OKR管理表</td>
            </tr>
            <tr>
                <td colspan="6">
                    <div class="layui-form-item">
                        <label for="asid" class="layui-form-label">管理对象：</label>
                        <div class="layui-input-block">
                            <input type="text" name="asid" id="asid" class="layui-input" style="border:none;">
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="boss" class="layui-form-label">直接上级：</label>
                        <div class="layui-input-block">
                            <input type="text" name="boss" id="boss" class="layui-input" style="border:none;">
                        </div>
                    </div>
                </td>
                <td colspan="3">
                    <div class="layui-form-item">
                        <label for="mdat" class="layui-form-label">管理周期：</label>
                        <div class="layui-input-block">
                            <input type="text" name="mdat" id="mdat" class="layui-input" style="border:none;">
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<script language="JavaScript">
    layui.use(['form', 'table'], function () {
        var form = layui.form;
    });
</script>
</body>
</html>
