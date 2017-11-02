<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/21
  Time: 10:18
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
        layui.use(['layedit','element','jquery','form','upload','table'],function () {
            var layedit = layui.layedit,element = layui.element,$ = layui.jquery,
                form = layui.form,upload = layui.upload,table = layui.table;

            var fileList = $("#fileList"),doid = $("#doid").val();

            var fileUploadOption = {
                elem:'#uploadList',
                url:'${base}/doc/uploadFiles',
                data:{
                    doid:doid
                },
                accept:'file',
                multiple:true,
                auto:false,
                bindAction:'#upload',
                choose:function (obj) {
                    var files = obj.pushFile();//将每次选择的文件追加到文件队列
                    obj.preview(function (index,file,result) {//读取本地文件
                        var tr = $(['<tr id="upload-'+ index +'">'
                            ,'<td>'+ file.name +'</td>'
                            ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                            ,'<td>等待上传</td>'
                            ,'<td>'
                            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                            ,'</td>'
                            ,'</tr>'].join(''));

                        tr.find(".demo-reload").on('click',function () {//单个重传
                            obj.upload(index,file);
                        });

                        tr.find(".demo-delete").on('click',function () {//单个删除
                            delete files[index];
                            tr.remove();
                        });
                        fileList.append(tr);
                    });
                },
                done:function (res,index,upload) { //上传完毕
                    if(res.code === 1){//上传成功
                        var tr = fileList.find("tr#upload-"+index),tds = tr.children();
                        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                        tds.eq(3).html(''); // 清空操作
                        delete files[index];
                        return;
                    }
                    this.error(index,upload);
                },
                error:function (index,upload) {
                    var tr = fileList.find("tr#upload-"+index),tds = tr.children();
                    tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                    tds.eq(3).find(".demo-reload").removeClass('layui-hide');
                }
            };

            var uploadList = upload.render(fileUploadOption);

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
                url:'${base}/util/findC0C16',
                dataType:'json',
                success:function (data) {
                    var user = data.c0,doc = data.c16;
                    var option = "",dOprtion="";
                    for(var i = 0;i<user.length;i++){
                        option += "<option value='"+user[i].id+"' class='n-display'>"+user[i].dsca+"</option>";
                    }
                    for(var d = 0;d<doc.length;d++){
                        dOprtion += "<option value='"+doc[d].ctyp+"' class='n-display'>"+doc[d].dsca+"</option>";
                    }
                    $("#csid").append(option);
                    $("#ctyp").append(dOprtion);
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
                },
                ctyp:function (value) {
                    if(value === null){
                        return "请选择文档类型";
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
                        tilt:infor.tilt,
                        csid:infor.csid,
                        ctyp:infor.ctyp,
                        doid:doid,
                        note:content
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.code === 1){
                            layer.confirm("文档提交成功",{offset:'100px'},function () {
                                window.location.reload();
                            });
                        }
                    },
                    error:function (kj) {
                        layer.alert("发生错误:"+kj.status);
                    }
                });
            });

//            table.on('tool(file)',function (obj) {
//                var infor = obj.data;
//                if(obj.event === 'del'){
//                    $.ajax({
//                        type:'POST',
//                        url:'./deleteFile',
//                        data:{
//                            duta:infor.duta,
//                            ffil:infor.ffil
//                        },
//                        dataType:'json',
//                        success:function (res) {
//                            if(res.code === 1){
//                                obj.del();
//                            }
//                            return layer.msg(res.msg);
//                        },
//                        error:function (kellyj) {
//                            return layer.msg("发生错误，错误码为："+kellyj.status);
//                        }
//                    });
//                }
//            });
        });
    </script>
</head>
<body>
<div class="x-body">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">文档</cite></a>
        <a href="./manage"><cite style="cursor: pointer;">文档管理</cite></a>
        <a href="javascript:location.replace(location.href)"><cite style="cursor: pointer;">文档编辑</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
    <form class="layui-form layui-form-panel">
        <input type="hidden" value="${obj}" id="doid">
        <div class="layui-form-item">
            <div class="x-center">
                <input type="text" class="layui-input x-center" name="tilt" id="tilt" placeholder="请输入标题" lay-verify="required|tilt" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">文档类型</label>
            <div class="layui-input-inline">
                <select name="ctyp" id="ctyp" lay-verify="required|ctyp" lay-search="">
                    <option value="" class="n-display" disabled selected>请选择文档类型</option>
                </select>
            </div>

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
            <div class="layui-upload">
                <button type="button" class="layui-btn layui-btn-radius layui-bg-black" id="uploadList">选择多文件</button>
                <div class="layui-upload-list">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th>文件名</th>
                            <th>大小</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="fileList"></tbody>
                    </table>
                </div>
            </div>
            <button type="button" class="layui-btn layui-btn-radius layui-bg-red" id="upload" lay-submit="">上传文件</button>
        </div>
        <%--<table class="layui-table" lay-data="{height:'400',url:'./queryAllFile?doid=${obj.doc.doid}',page:false,id:'file'}" lay-filter="file">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th lay-data="{field:'ffil',width:1200}">文件名</th>--%>
                <%--<th lay-data="{field:'fsiz',width:300}">大小</th>--%>
                <%--<th lay-data="{fixed:'right',align:'center',width:200,templet:'#operate'}">操作</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
        <%--</table>--%>
        <%--<script type="text/html" id="operate">--%>
            <%--<a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>--%>
        <%--</script>--%>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-radius layui-bg-green" lay-filter="put" lay-submit="">提交</button>
            </div>
        </div>
        <br/><br/><br/><br/><br/><br/>
    </form>
</div>
</body>
</html>

