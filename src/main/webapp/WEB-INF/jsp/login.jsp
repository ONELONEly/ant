<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/8/28
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>绩效管理系统</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <c:import url="../static.html"/>
</head>
<body bgcolor="#16181d">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">
      <img src="${base}/static/images/logo/logo.png"/>
    </div>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="./search/doc/index">文档搜索</a>
      </li>
      <li class="layui-nav-item">
        <a href="./">门户</a>
      </li>
    </ul>
  </div>
  <div  style="width:500px;vertical-align:middle;margin:auto; margin-top: 150px;">

      <form class="layui-form layui-form-panel" method="post">
        <div class="layui-form-item">
          <div class="layui-input-inline">
            <img src="${base}/static/images/logo/login.png"/>
          </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline">
            <input type="text" name="usid" lay-verify="required|title" placeholder="请输入邮箱账号" autocomplete="off" class="layui-input" style="width:400px;" required/>
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-inline">
            <input type="password" name="pawd" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input" style="width:400px;" required/>
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-inline">
            <button type="submit" class="layui-btn layui-btn-radius" lay-filter="*">登陆</button>
            <button type="reset" class="layui-btn layui-btn-radius layui-btn-primary">重置</button>
          </div>
        </div>
      </form>

  </div>
</div>
</body>
</html>
