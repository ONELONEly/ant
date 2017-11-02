<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/6
  Time: 11:16
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

         var fileUploadOption = {
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

        form.verify({
            ptno:function (value) {
                if(value === null){
                    return "请选择项目";
                }
            },
            ptyp:function (value) {
                if(value === null){
                    return "请选择类型";
                }
            },
            csid:function (value) {
                if(value === null){
                    return "请选择接收人";
                }
            },
            tepr:function (value) {
                if(value === null){
                    return "请选择测试用户";
                }
            },
            ksid:function (value) {
                if(value === null){
                    return "请选择关键用户";
                }
            },
            rsid:function (value) {
                if(value === null){
                    return "请选择验收人";
                }
            },
            syno:function (value) {
                if(value === null){
                    return "请选择系统";
                }
            },
            puno:function (value) {
                if(value === null){
                    return "请选择任务阶段";
                }
            }
        });
        
        form.on('select(ptno)',function (data) {
            var tOption ="";
            $.ajax({
                type:'GET',
                url:'${base}/util/findtaskInsertC11',
                data:{
                    ptno:data.value
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1) {
                        var type = data.data;
                        for (var m = 0; m < type.length; m++) {
                            tOption += "<option value='" + type[m].pjno + "'>" + type[m].dsca + "</option>";
                        }
                        $("#ptyp").html("");
                        $("#ptyp").append(tOption);
                        form.render();
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status);
                }
            });
        });

        form.on("submit(set)",function (data) {
            var noteContent = layedit.getContent(note);
            var trans = data.field;
            $.ajax({
                type:'POST',
                url:'${base}/task/insertTask',
                data:{
                    taid:taid,
                    titl:trans.titl,
                    csid:trans.csid,
                    ksid:trans.ksid,
                    ptyp:trans.ptyp,
                    rsid:trans.rsid,
                    sta2:trans.sta2,
                    sta3:trans.sta3,
                    syno:trans.syno,
                    tepr:trans.tepr,
                    puno:trans.puno,
                    ptno:trans.ptno,
                    edit:noteContent
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg,function () {
                            window.location.reload();
                        });
                    }else{
                        layer.alert(data.msg);
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status);
                }
            });
            return false;
         });

        $.ajax({
            type:'POST',
            url:'${base}/util/findC0C13C14',
            dataType:'json',
            success:function (data) {
                var user = data.user,sys = data.sys,stage = data.stage,project = data.project;
                var uOption = "",sOption = "",pOption = "",jOption = "";
                for(var i = 0;i<user.length;i++){
                    uOption += "<option value='"+user[i].id+"'>"+user[i].dsca+"</option>";
                }
                for(var j = 0;j<sys.length;j++){
                    sOption += "<option value='"+sys[j].syno+"'>"+sys[j].dsca+"</option>";
                }
                for(var n = 0;n<stage.length;n++){
                    pOption += "<option value='"+stage[n].puno+"'>"+stage[n].dsca+"</option>";
                }
                for(var k = 0;k<project.length;k++){
                    jOption += "<option value='"+project[k].id+"'>"+project[k].dsca+"</option>";
                }
                $("#csid").append(uOption);
                $("#ksid").append(uOption);
                $("#tepr").append(uOption);
                $("#rsid").append(uOption);
                $("#syno").append(sOption);
                $("#puno").append(pOption);
                $("#ptno").append(jOption);
                form.render();
            },
            error:function (kellyj) {
                layer.alert("发生错误，错误码为:"+kellyj.status);
            }
        });

        var uploadList = upload.render(fileUploadOption);
        var note = layedit.build('note',layEditOption);
     });
</script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">首页</cite></a>
        <a href="./manage"><cite style="cursor: pointer;">任务管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加任务</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
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
            <div class="layui-input-inline">
                <label class="layui-form-label">请选择项目:</label>
            </div>
            <div class="layui-input-inline">
                <select name="ptno" id="ptno" lay-filter="ptno" lay-verify="ptno" lay-filter="ptno" lay-search>
                    <option value="" class="n-display" disabled selected>请选择所属项目</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="layui-form-label">评分类型:</label>
            </div>
            <div class="layui-input-inline">
                <select name="ptyp" id="ptyp" lay-filter="ptyp" lay-verify="ptyp" lay-search>
                    <option value="" class="n-display" disabled selected>请选择评分类型</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
        <textarea id="note" class="n-display"></textarea>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="layui-form-label">派发给:</label>
            </div>
            <div class="layui-input-inline">
                <select name="csid" id="csid" lay-filter="csid" lay-verify="csid" lay-search>
                    <option value="" class="n-display" disabled selected>请选择接收人</option>
                </select>
            </div>

            <div class="layui-input-inline">
                <label class="layui-form-label">测试用户:</label>
            </div>
            <div class="layui-input-inline">
                <select name="tepr" id="tepr" lay-filter="tepr" lay-verify="tepr" lay-search>
                    <option value="" class="n-display" disabled selected>请选择测试用户</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="layui-form-label">关键用户:</label>
            </div>
            <div class="layui-input-inline">
                <select name="ksid" id="ksid" lay-filter="ksid" lay-verify="ksid" lay-search>
                    <option value="" class="n-display" disabled selected>请选择关键用户</option>
                </select>
            </div>

            <div class="layui-input-inline">
                <label class="layui-form-label">验收人:</label>
            </div>
            <div class="layui-input-inline">
                <select name="rsid" id="rsid" lay-filter="rsid" lay-verify="rsid" lay-search>
                    <option value="" class="n-display" disabled selected>请选择验收人</option>
                </select>
            </div>
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

            <div class="layui-input-inline">
                <label class="layui-form-label">任务类型:</label>
            </div>
            <div class="layui-input-inline">
                <select name="puno" id="puno" lay-filter="puno" lay-verify="puno" lay-search>
                    <option value="" class="n-display" disabled selected>请选择任务类型</option>
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
        <%--<table class="layui-table" lay-data="{height:'400',url:'./queryAllFile?taid=${obj}',page:false,id:'file'}" lay-filter="file">--%>
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
        <hr class="layui-bg-green"/>
        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-btn-radius" id="set" lay-filter="set" lay-submit>添加任务</button>
        </div>
    </form>
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
