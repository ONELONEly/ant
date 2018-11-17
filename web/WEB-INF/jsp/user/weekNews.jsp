<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/20
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人周报</title>
    <c:import url="../../static1.html"/>
    <style>
        #LAY_layedit_1 {
            background-color: #fff;
        }
    </style>
</head>
<body>
<div>
    <div class="x-nav">
        <span class="layui-breadcrumb">
            <a href="javascript:"><cite style="cursor: pointer;">个人</cite></a>
            <a href="javascript:location.replace('./weekNewsManage')"><cite style="cursor: pointer;">个人周报</cite></a>
            <a href="javascript:location.replace(location.href)"><cite style="cursor: pointer;">创建周报</cite></a>
            <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
        </span>
    </div>
    <div class="x-body">
        <form class="layui-form layui-form-panel">
            <div style="padding-bottom: 40px;">
                <input type="hidden" value="${obj}" name="doid" id="doid"/>
                <div class="layui-form-item">
                    <div class="x-center">
                        <input type="text" class="layui-input x-center" name="tilt" id="tilt" placeholder="请输入标题" lay-verify="required|tilt" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">接收人</label>
                    <div class="layui-input-inline">
                        <select name="csid" id="csid" lay-verify="required|csid" lay-search="">
                            <option value="" class="n-display" disabled selected>请选择接收人</option>
                        </select>
                    </div>

                    <div class="layui-input-inline">
                        <input type="text" name="sdat" id="sdat" placeholder="请选择日期" lay-verify="required|sdat" autocomplete="off" class="layui-input" required/>
                    </div>

                    <div class="layui-input-inline">
                        <select name="week" id="week" lay-verify="required|week" lay-search="">
                            <option value="" class="n-display" disabled selected>请选择周数</option>
                            <option value="1" class="n-display" >第一周</option>
                            <option value="2" class="n-display" >第二周</option>
                            <option value="3" class="n-display" >第三周</option>
                            <option value="4" class="n-display" >第四周</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="grop" id="grop" lay-verify="required|grop" lay-filter="grop">
                            <option value="" class="n-display" disabled selected>请选择团队</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <textarea id="doc" class="n-display"></textarea>
                </div>
            </div>

            <div class="x-center x-body bottom-buttom" style="margin-left:-20px;">
                <div class="layui-form-item">
                    <button type="button" class="button layui-btn layui-btn-radius layui-bg-green" lay-filter="put" lay-submit="">提交</button>
                </div>
            </div>

        </form>
    </div>
</div>
<script language="JavaScript">
    layui.use(['layedit','element','jquery','form','layer','laydate'],function () {
        var layedit = layui.layedit,element = layui.element,$ = layui.jquery,
            form = layui.form,layer = layui.layer,laydate=layui.laydate,doid = $("doid").val();

        var layeditOption = {
            uploadImage:{
                url:'${base}/task/insertImage',
                type:'POST'
            },
            height:'500px'
        };
        var note = layedit.build('doc',layeditOption);

        var start = {
            elem:'#sdat',
            type:'month',
            choose: function (value) {
                console.log(value);
            }
        }



        laydate.render(start);


        $.ajax({
            type:'GET',
            url:'${base}/util/findC0',
            dataType:'json',
            success:function (data) {
                var user = data.c0;
                var option = "";
                for(var i = 0;i<user.length;i++){
                    option += "<option value='"+user[i].id+"' class='n-display'>"+user[i].dsca+"</option>";
                }
                $("#csid").append(option);
                form.render();
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });

        $.ajax({
            type:'GET',
            url:'${base}/util/findC9',
            dataType:'json',
            success:function (data) {
                var grops = data.c9;
                console.log(data);
                var option = "";
                for(var i = 0;i<grops.length;i++){
                    option += "<option value='"+grops[i].id+"' class='n-display'>"+grops[i].dsca+"</option>";
                }
                $("#grop").append(option);
                form.render();
            },
            error:function (kj) {
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });

        form.verify({
            csid:function (value) {
                if(checkForm(value)){
                    return "请选择接收人";
                }
            },
            week:function (value) {
                if(checkForm(value)){
                    return "请选择周数";
                }
            },
            sdat:function (value) {
                if(checkForm(value)){
                    return "请选择日期";
                }
            },
            grop:function (value) {
                if(checkForm(value)){
                    return "请选择团队";
                }
            }
        });


        form.on('submit(put)',function (data) {
            var infor = data.field;
            var content = layedit.getContent(note);
            $.ajax({
                type:'POST',
                url:'${base}/doc/insertDoc',
                data:{
                    doid:infor.doid,
                    tilt:infor.tilt,
                    csid:infor.csid,
                    sdat:infor.sdat,
                    week:infor.week,
                    grop:infor.grop,
                    note:content
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm("文档提交成功,返回上一页？",{offset:'10px'},function(){
                            window.location.replace("./weekNewsManage");
                        },function () {
                            window.location.reload();
                        });
                    }else{
                        layer.msg(data.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        });
    });
</script>
</body>
</html>
