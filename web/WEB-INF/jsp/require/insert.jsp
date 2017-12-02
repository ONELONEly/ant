<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/30
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加任务</title>
    <c:import url="../../static1.html"/>
</head>
<script language="JavaScript">
    layui.use(['form','jquery','element','layer','layedit','upload'],function () {
        var form = layui.form,$ = layui.jquery,element = layui.element,
            layer = layui.layer,layedit = layui.layedit,upload = layui.upload;
        var fileList = $("#fileList"),taid = $("#taid").val();

        var layEditOption = {
            uploadImage: {
                url:'${base}/task/insertImage',
                type:'POST'
            }
        };

        var files,fileUploadOption = {
            elem:'#uploadList',
            url:'${base}/task/uploadFiles',
            data:{
                taid:taid
            },
            accept:'file',
            multiple:true,
            auto:false,
            bindAction:'#upload',
            choose:function (obj) {
                files = obj.pushFile();//将每次选择的文件追加到文件队列
                obj.preview(function (index,file,result) {//读取本地文件
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
                        ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
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

        form.verify({
            syno:function (value) {
                if(value.length === 0){
                    return "请选择系统";
                }
            }
        });

        form.on("submit(set)",function (data) {
            var noteContent = layedit.getContent(note);
            var trans = data.field;
            $.ajax({
                type:'POST',
                url:'./insertRequire',
                data:{
                    titl:trans.titl,
                    syno:trans.syno,
                    sta2:trans.sta2,
                    sta3:trans.sta3,
                    edit:noteContent,
                    taid:taid
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg+"返回主页？",{offset:'100px'},function () {
                            location.replace("./index");
                        });
                    }else{
                        return layer.msg(data.msg);
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status);
                }
            });
            return false;
        });

        $.ajax({
            type:'GET',
            url:'${base}/util/findC13',
            dataType:'json',
            success:function (res) {
                var sys = res.sys,sOption = "";
                for(var i = 0;i<sys.length;i++){
                    sOption += "<option value='"+sys[i].syno+"'>"+sys[i].dsca+"</option>";
                }
                $("#syno").append(sOption);
                form.render();
            },
            error:function (kellyj) {
                return layer.msg("发生错误，错误码为:"+kellyj.status);
            }
        });

        var uploadList = upload.render(fileUploadOption);
        var note = layedit.build('note',layEditOption);
    });
</script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">我的</cite></a>
        <a href="./index"><cite style="cursor: pointer;">用户需求</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加任务</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" value="${obj}" id="taid"/>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="x-center">
                <input type="text" class="layui-input x-center" name="titl" id="titl" placeholder="请输入标题" lay-verify="required|titl" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <textarea id="note" class="n-display"></textarea>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="layui-form-label">系统:</label>
            </div>
            <div class="layui-input-inline">
                <select name="syno" id="syno" lay-filter="syno" lay-verify="syno" lay-search>
                    <option value="" class="n-display" disabled selected>请选择系统</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="layui-form-label">优先级:</label>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta2" value="0" class="layui-form-radio" title="低" checked/>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta2" value="1" class="layui-form-radio" title="中"/>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta2" value="2" class="layui-form-radio" title="高"/>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta2" value="3" class="layui-form-radio" title="紧急"/>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="layui-form-label">严重程度:</label>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta3" value="0" class="layui-form-radio" title="一般" checked/>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta3" value="1" class="layui-form-radio" title="严重"/>
            </div>
            <div class="layui-input-inline">
                <input type="radio" name="sta3" value="2" class="layui-form-radio" title="关键"/>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-upload">
                <button type="button" class="layui-btn layui-bg-black" id="uploadList">选择多文件</button>
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
            <button type="button" class="layui-btn layui-btn-radius layui-btn-danger" id="upload"  lay-submit>上传文件</button>
        </div>
        <hr class="layui-bg-green"/>
        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-btn-radius" id="set" lay-filter="set" lay-submit>添加任务</button>
        </div>
    </form>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>

