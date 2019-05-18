<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="shortcut icon" href="${base}/static/images/logo/logo.ico" type="image/x-icon" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>GDC|设计中心</title>
    <script src = "${base}/static/layui/layui.js"></script>
    <link rel = "stylesheet" href = "${base}/static/layui/css/layui.css"  media="all" >
    <link rel = "stylesheet" href = "${base}/static/css/Global.css"  media="all" >
    <script src = "${base}/static/js/admin.js"></script>
    <script src = "${base}/static/js/helper.js"></script>

</head>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${base}/static/images/logo/gdclogo.png"/>
        </div>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-demo-nav">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">MENU</a>
                    <dl class="layui-nav-child">
                        <dd> <a href="${base}/doc/knowledgeDoc?type=7" target="myiframe"><cite>行业</cite></a></dd>
                        <dd> <a href="${base}/doc/knowledgeDoc?type=8" target="myiframe"><cite>学习</cite></a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item" style="height: 30px; text-align: center"></li>
                <span class="layui-nav-bar" style="top: 67.5px; height: 0px; opacity: 0;"></span></ul>

        </div>
    </div>
    <div class="layui-body site-demo">
        <div class="layui-tab-item layui-show">
            <iframe src="${base}/doc/knowledgeDoc?type=7" frameborder="0" id="myiframe" name="myiframe" class="x-frame" style="padding:0px;width: 100%;height: 600px;"></iframe>
        </div>
    </div>
    <div class="layui-footer footer footer-demo">
        <div class="layui-main">
            <p>2018 &copy; <a href="#"> GDC </a></p>
        </div>
    </div>
    <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>
    <script src="${base}/static/layui/layui.all.js" type="text/javascript"></script>
    <script src="${base}/static/js/Comm.js" type="text/javascript"></script>
    <script>
        $(function () {
            $(".site-tree-mobile").click(function () {
                $("body").addClass("site-mobile");
                $(".site-mobile-shade").click(function () {
                    $("body").removeClass("site-mobile");
                });
            });
        });
    </script>
</div>
</body>
</html>
