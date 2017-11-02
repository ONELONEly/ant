<%--suppress ALL --%>
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
    <script language="JavaScript">
        layui.use(['layedit','element','jquery','form','layer'],function () {
            var layedit = layui.layedit,element = layui.element,$ = layui.jquery,
            form = layui.form,layer = layui.layer,doid = $("doid").val();

            var layeditOption = {
                uploadImage:{
                    url:'${base}/task/insertImage',
                    type:'POST'
                },
                height:'800px'
            };
            var note = layedit.build('doc',layeditOption);

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
                    layer.alert("发生错误:"+kj.status);
                }
            });

            form.verify({
                csid:function (value) {
                    if(value === null){
                        return "请选择接收人";
                    }
                }
            });

            form.on('submit(put)',function (data) {
                var infor = data.field,content = layedit.getContent(note);
                console.log(infor);
                $.ajax({
                    type:'POST',
                    url:'${base}/doc/insertDoc',
                    data:{
                      doid:infor.doid,
                      tilt:infor.tilt,
                      csid:infor.csid,
                      note:content
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.code === 1){
                            layer.confirm("周报提交成功",{offset:'100px'},function () {
                                window.location.reload();
                            });
                        }else{
                            layer.msg(data.msg);
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
<div class="x-body">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">个人</cite></a>
        <a href="javascript:location.replace(location.href)"><cite style="cursor: pointer;">个人周报</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
    <form class="layui-form layui-form-panel">
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
        </div>
        <div class="layui-form-item">
            <textarea id="doc" class="n-display"></textarea>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <button type="button" class="button layui-btn layui-btn-radius layui-bg-green" lay-filter="put" lay-submit="">提交</button>
            </div>
        </div>
        <br/><br/><br/><br/><br/><br/>
    </form>
</div>
</body>
</html>
