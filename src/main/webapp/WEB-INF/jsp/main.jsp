<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>绩效管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="./layui/css/layui.css" media="all">
    <link rel="stylesheet" href="./static/css/admin-1.css" media="all">
    <link rel="stylesheet" href="./css/admin.css" media="all">
    <link rel="shortcut icon" href="./static/images/logo/logo.ico" type="image/x-icon" />
    <script>
        ///^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <li class="layui-nav-item" lay-unselect>
                    <a lay-href="./tip.jsp" layadmin-event="message" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>

                        <!-- 如果有新消息，则显示小圆点 -->
                        <span class="layui-badge-dot"></span>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="note">
                        <i class="layui-icon layui-icon-note"></i>
                    </a>
                </li>

                <li class="layui-nav-item">
                    <a lay-href="./console.jsp" data-url="./console.jsp" data-icon="&#xe628;" data-title="看板"
                       kit-target data-id='看板'/>看板</a>
                </li>

                <li class="layui-nav-item">
                    <a lay-href="./doc/knowledgeDoc?type=4" data-url="./doc/knowledgeDoc?type=4" data-icon="&#xe628;"
                       data-title="学习文档" kit-target data-id='学习文档'/>学习文档</a>
                </li>

                <li class="layui-nav-item">
                    <a lay-href="./doc/knowledgeDoc?type=2" data-url="./doc/knowledgeDoc?type=2" data-icon="&#xe628;" data-title="会议纪要"
                       kit-target data-id='会议纪要'/>会议纪要</a>
                </li>

                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <img class="header-img" src="./user/getUserHeader?usid=${obj.user.USID}" id="head">
                        <cite>${obj.user.DSCA}</cite>
                        <span class="usid n-display">${obj.user.USID}</span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a lay-href="./user/modify" data-url="./user/modify" data-icon="&#xe628;" data-title="修改资料"
                               kit-target data-id='修改资料' class="x-center">修改资料</a>
                        </dd>
                        <hr class="layui-bg-green"/>
                        <dd layadmin-event="logout" style="text-align: center;"><a href="./loginOut">退出</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <i class="layui-icon layui-icon-more-vertical"></i>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <i class="layui-icon layui-icon-more-vertical"></i>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo">
                    <span>绩效管理系统</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <c:forEach var="second" items="${obj.secondLevel}">
                        <li class="layui-nav-item">
                            <a href="javascript:;"><i class="layui-icon layui-icon-app"></i><cite>${second.dsca}</cite></a>
                            <dl class="layui-nav-child">
                                <c:forEach var="third" items="${obj.thirdLevel}">
                                    <c:if test="${third.cbase003VO.pono == second.pono}">
                                        <dd>
                                            <a lay-href="${third.purl}" data-url="${third.purl}" data-icon="&#xe658;"
                                               data-title="${third.dsca}" kit-target
                                               data-id='${third.dsca}'>&nbsp;<span>${third.dsca}</span></a>
                                        </dd>
                                    </c:if>
                                </c:forEach>
                            </dl>
                        </li>
                    </c:forEach>
                </ul>


            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <!-- <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div> -->
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="./main" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>
<script src="./layui/layui.js"></script>
<script>
    layui.config({
        base: './' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'jquery'], function () {
        var $ = layui.jquery;
        sessionStorage.setItem("usid", $("#usid").val())
    });
</script>
</body>
</html>


