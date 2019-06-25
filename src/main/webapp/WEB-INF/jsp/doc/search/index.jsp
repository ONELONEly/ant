<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2019/4/29
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>文档搜索</title>
    <c:import url="../../../static2.html"/>
    <style>
        .layui-bg-white{
            background-color: #FFFFFF !important ;
            color: #b3b6b8 !important ;
        }
        .line-back{
            background: linear-gradient(to right,#789eef,#5bc9d0);
        }
        .search{
            width: 100%;
            margin: 0;
            padding: 0;
            box-shadow: 3px 3px 2px #f6f6f6 ;
        }
        .x-center{
            text-align: center;
            margin: 0 auto;
        }
        .search_header{
            width: 100%;
            padding: 20px 0;
        }
        .search_header dl{
            position: relative;
            text-align: left;
            font-size: 12px;
            line-height: 30px;
            top: 5px;
            left: 10px;
            display: inline-block;
        }
        .search_header dl dt{
            display: inline-block;
            color:#99A0A4;
        }
        .search_header dl dd{
            display: inline-block;
            padding: 0 5px;
            border-radius: 5px;
            cursor: pointer;
        }
        .search_header_input{
            vertical-align: middle;
            width: 500px;
            height: 40px;
            background: #eff4f5;
            border: none;
            outline: none;
            padding-left: 10px;
            border-top-left-radius: 2px;
            border-bottom-left-radius: 2px;
        }
        .content{
            margin-top: 20px;
        }
        .card-item{
            font-size: 16px;
            line-height: 26px;
            padding: 15px 0 0;
            vertical-align: top;
        }
        .category ul{
            position: relative;
            text-align: left;
            font-size: 16px;
            line-height: 30px;
            vertical-align: middle;
            display: inline-block;
        }
        .category span{
            font-size: 16px;
            color: #555555;
        }
        .category ul li{
            display: inline-block;
            padding: 0 20px;
            border-radius: 20px;
            cursor: pointer;
        }
        body{
            background-color: #ffffff;
        }
        .x-nav{
            padding: 10px 20px;
            position: relative;
            border-bottom: 1px solid #f6f6f6;
            line-height: 39px;
            height:39px;
            overflow: hidden;
            background-color: #ffffff;
        }
        cite{
            cursor: pointer;
        }
        .filterResult{
            width: 100%;
            font-size: 14px;
            padding: 20px 10px;
        }
        .filterResult span{
            font-size: 16px;
            color: #00F7DE;
            margin: 0 2px;
        }
        .x-docList{
            width: 100%;
            margin: 20px;
            text-align: left;
        }
        .doc-search-page{
            width: 100%;
            margin: 0 0 20px;
            text-align: center;
            background-color: #ffffff;
        }
        .doc-search-item{
            width:100%;
            font-size: 16px;
            color:#555555;
            display: inline-block;
            padding: 10px 0;
            border-bottom: 1px solid #f6f6f6;
        }
        .doc-search-item-title{
            display: inline-block;
            width:50%;
            padding: 10px 0;
        }
        .doc-search-item-title span{
            color: #009688;
            cursor: pointer;
        }
        .doc-search-item-title span:hover{
            color: #00cbba;
        }
        .doc-search-item-time{
            display: inline-block;
            width:40%;
            padding: 10px 0;
        }
        .doc-search-item-author{
            display: inline-block;
            width: 30%;
            padding: 10px 0;
        }
    </style>
</head>
<body>
<div class="layui-bg-white">
    <div class="search">
        <div class="search_header x-center">
            <input type="text" class="search_header_input" placeholder="搜索您想要的文案"/>
            <button class="layui-btn search-btn line-back">
                <i class="layui-icon layui-icon-search"></i>
            </button>
            <dl class="search_type search_type_dl">
            </dl>
        </div>
    </div>
</div>

<div class="layui-container content">
    <div class="x-nav">
        <span class="layui-breadcrumb">
            <a href="javascript:"><cite>文档查找</cite></a>
            <a href="javascript:"><cite>多媒体</cite></a>
        </span>
    </div>

    <div class="layui-card-body layui-bg-white category">
        <div class="card-item">
            <span><i class="layui-icon layui-icon-star"></i>文档类型：</span>
            <ul class="search_type search_type_ul">
            </ul>
        </div>
        <p class="filterResult"></p>
    </div>

    <div class="x-docList layui-bg-white" id="x-docList">
    </div>
    <div class="doc-search-page" id="doc-search-page">

    </div>
</div>
<script id="docItem" type="text/html">
    {{# layui.each(d,function(index,item){ }}
    <div class="doc-search-item" itemref="{{item.doid}}">
        <div class="doc-search-item-title">
            <a href="javascript:" lay-event="show"><span>{{item.title}}</span></a>
        </div>
        <div class="doc-search-item-time">
            <span>{{item.time}}&nbsp;&nbsp;&nbsp;&nbsp;{{item.author}}</span>
        </div>
        <%--<div class="doc-search-item-author">--%>
            <%--<span></span>--%>
        <%--</div>--%>
    </div>
    {{# }); }}
</script>
<script id="filterCount" type="text/html">
    筛选出<span>{{d}}</span>个
</script>
<script id="dlType" type="text/html">
    <dt>热搜:</dt>
    {{# layui.each(d,function(index,item){ }}
    <dd itemref="{{item.type}}">{{item.name}}</dd>
    {{# }); }}
</script>
<script id="ulType" type="text/html">
    {{# layui.each(d,function(index,item){ }}
    <li itemref="{{item.type}}">{{item.name}}</li>
    {{# }); }}
</script>
<script language="JavaScript">
    layui.use(['form','jquery','layer','element','laypage','laytpl'],function () {
        var from = layui.form,$ = layui.jquery,layer = layui.layer,
            element = layui.element,layPage = layui.laypage,layTpl = layui.laytpl;

        var typeList = [],docData = [],type = 0;

        makeTypePost($);

        $(".search-btn").click(function () {
            makeDataLoad(type,$)
        });

        function decideShow(data){
            data.length > 10 ? page(data,$) : setHtml(data,$);
        }
        //调用分页
        function page(data,$){
            layPage.render({
                elem: 'doc-search-page'
                ,count: data.length
                ,theme:'#787876'
                ,layout: ['prev', 'page', 'next', 'refresh', 'skip']
                ,jump: function(obj,first){
                    var showData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                    setHtml(showData,$);
                }
            });
        }

        //模拟渲染
        function setHtml(showData,$) {
            if (showData.length < 1){
                $(".doc-search-page").hide();
            }else{
                $(".doc-search-page").show();
            }
            var getTpl = docItem.innerHTML;
            layTpl(getTpl).render(showData,function (html) {
                $(".x-docList").html(html);
            });
            setSingle(showData.length);
            titleClickEvent($)
        }

        function setSingle(showData) {
            showData = showData === 0 ? "0" : showData;
            var getTpl = filterCount.innerHTML;
            layTpl(getTpl).render(showData,function (html) {
                $(".filterResult").html(html)
            })
        }

        function makeTypePost($) {
            $.post("./getAllDocType",function (result) {
                if(result.code === 200){
                    var typeData = result.data;
                    typeData.push({
                       type:0,
                       name:'全部'
                    });
                    setType(typeData,$)
                }else{
                    layer.msg(result.msg);
                }
            });
        }

        function makeDataLoad(value,$) {
            type = value;
            console.log(type)
            $.post("./getAllDocByType",JSON.stringify({
                type : value,
                key : $(".search_header_input").val()
            }),function (result) {
                if(result.code === 200){
                    docData = result.data;
                    decideShow(docData)
                }else{
                    layer.msg(result.msg);
                }
            });
        }

        function setType(typeData,$) {
            var getDlTpl = dlType.innerHTML;
            var getUlTpl = ulType.innerHTML;
            layTpl(getDlTpl).render(typeData,function (html) {
                $(".search_type_dl").html(html);
            });
            layTpl(getUlTpl).render(typeData,function (html) {
                $(".search_type_ul").html(html);
            });
            clickEvent($)
        }

        function clickEvent($) {
            $(".search_type li,dd").click(function () {
                var localName = $(this)[0].localName;
                var type = $(this).attr("itemref");
                $(this).css("background", '#787876').css("color", "#ffffff");
                $(this).siblings().each(function () {
                    $(this).css("backgroundColor", '#ffffff').css("color", "#b3b6b8");
                });
                if (localName === "li") {
                    $(".search_type dd").each(function () {
                        $(this).css("backgroundColor", '#ffffff').css("color", "#b3b6b8");
                    });
                } else {
                    $(".search_type li").each(function () {
                        $(this).css("backgroundColor", '#ffffff').css("color", "#b3b6b8");
                    });
                }
                makeDataLoad(type,$)
            });
        }

        function titleClickEvent($) {
            $(".doc-search-item").click(function () {
                var doid = $(this).attr("itemref");
                layer.open({
                    type:2,
                    content:['../../doc/showDoc?doid='+doid],
                    area: ['90%', '70%'],
                    title:'文档',
                    offset:'10px'
                });
            });
        }
    });
</script>
</body>
</html>

