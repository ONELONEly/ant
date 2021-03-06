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
    <title>修改任务</title>
    <c:import url="../../static1.html"/>
    <style>
        #LAY_layedit_1 {
            background-color: #fff;
        }
    </style>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">我的</cite></a>
        <a href="./index"><cite style="cursor: pointer;">需求指派</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">修改需求</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form layui-form-pane">
        <div style="padding-bottom: 40px;">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="raid" value="${obj.task.raid}" id="raid"/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="x-center">
                    <input type="text" class="layui-input x-center" name="titl" id="titl" placeholder="请输入标题" lay-verify="required|titl" value="${obj.task.titl}" required/>
                </div>
            </div>

            <div class="layui-form-item">
                <textarea id="note" class="n-display">${obj.note}</textarea>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">系统:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="syno" id="syno" lay-filter="syno" lay-verify="syno" lay-search>
                        <option value="${obj.task.syno}" class="n-display" disabled selected>${obj.task.synonam}</option>
                    </select>
                </div>

                <div class="layui-input-inline">
                    <label class="layui-form-label">指派给:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="csid" id="csid" lay-filter="csid" lay-verify="csid" lay-search>
                        <option value="${obj.task.csid}" class="n-display" disabled selected>${obj.task.cnam}</option>
                    </select>
                </div>
            </div>



            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">期望完成时间:</label>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="ydat" id="ydat" placeholder="请选择日期" class="layui-input" value="${obj.ydat}" required/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">优先级:</label>
                </div>
                <c:if test="${obj.task.sta2 == 33 || obj.task.sta2 == null}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="33" class="layui-form-radio" title="低" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="32" class="layui-form-radio" title="中"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="36" class="layui-form-radio" title="高"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="37" class="layui-form-radio" title="紧急"/>
                    </div>
                </c:if>
                <c:if test="${obj.task.sta2 == 32}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="33" class="layui-form-radio" title="低"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="32" class="layui-form-radio" title="中" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="36" class="layui-form-radio" title="高"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="37" class="layui-form-radio" title="紧急"/>
                    </div>
                </c:if>
                <c:if test="${obj.task.sta2 == 36}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="33" class="layui-form-radio" title="低"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="32" class="layui-form-radio" title="中"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="36" class="layui-form-radio" title="高" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="37" class="layui-form-radio" title="紧急"/>
                    </div>
                </c:if>
                <c:if test="${obj.task.sta2 == 37}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="33" class="layui-form-radio" title="低"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="32" class="layui-form-radio" title="中"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="36" class="layui-form-radio" title="高"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta2" value="37" class="layui-form-radio" title="紧急" checked/>
                    </div>
                </c:if>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">严重程度:</label>
                </div>
                <c:if test="${obj.task.sta3 == 3 || obj.task.sta3 == null}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="3" class="layui-form-radio" title="一般" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="2" class="layui-form-radio" title="严重"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="1" class="layui-form-radio" title="关键"/>
                    </div>
                </c:if>
                <c:if test="${obj.task.sta3 == 2}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="3" class="layui-form-radio" title="一般"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="2" class="layui-form-radio" title="严重" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="1" class="layui-form-radio" title="关键"/>
                    </div>
                </c:if>
                <c:if test="${obj.task.sta3 == 1}">
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="3" class="layui-form-radio" title="一般"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="2" class="layui-form-radio" title="严重"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="sta3" value="1" class="layui-form-radio" title="关键" checked/>
                    </div>
                </c:if>
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
            <table class="layui-table" lay-data="{,url:'../task/queryAllFile?taid=${obj.task.raid}',page:false,id:'file'}" lay-filter="file">
                <thead>
                <tr>
                    <th lay-data="{field:'ffil',align:'center',width:1200}">文件名</th>
                    <th lay-data="{field:'fsiz',align:'center',width:100}">大小</th>
                    <th lay-data="{fixed:'right',align:'center',align:'center',width:100,templet:'#operate'}">操作</th>
                </tr>
                </thead>
            </table>
            <script type="text/html" id="operate">
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
            </script>
        </div>
        <hr class="layui-bg-green"/>
        <div class="x-center x-body bottom-buttom" style="margin-left:-20px;">
            <div class="layui-form-item">
                <button type="button" class="layui-btn layui-btn-radius" id="modify" lay-filter="modify" lay-submit>保存</button>
            </div>
        </div>
    </form>

</div>
<script language="JavaScript">
    layui.use(['form','jquery','element','layer','layedit','upload','table','laydate'],function () {
        var form = layui.form,$ = layui.jquery,element = layui.element,
            layer = layui.layer,layedit = layui.layedit,upload = layui.upload,
            table = layui.table,laydate = layui.laydate;
        var fileList = $("#fileList"),raid = $("#raid").val();

        var layEditOption = {
            uploadImage: {
                url:'${base}/task/insertImage',
                type:'POST'
            },
            height:'300px'
        };

        laydate.render({
            elem: '#ydat'
            ,type: 'datetime'
        });

        var files,fileUploadOption = {
            elem:'#uploadList',
            url:'${base}/task/uploadFiles',
            data:{
                taid:raid
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
                        ,'<td>'+ (file.size/1024).toFixed(1) +'kb</td>'
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
                    table.reload("file");
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
                if(checkForm(value)){
                    return "请选择系统";
                }
            }
        });

        form.on("submit(modify)",function (data) {
            var noteContent = layedit.getContent(note);
            var trans = data.field;
            $.ajax({
                type:'POST',
                url:'./editRequire',
                data:{
                    titl:trans.titl,
                    syno:trans.syno,
                    sta2:trans.sta2,
                    sta3:trans.sta3,
                    csid:trans.csid,
                    ydat:trans.ydat,
                    edit:noteContent,
                    raid:trans.raid
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg+"返回主页？",{offset:'10px'},function () {
                            location.replace("./index");
                        });
                    }else{
                        return layer.msg(data.msg,{offset:'10px'});
                    }
                },
                error:function (kellyj) {
                    layer.alert("发生错误，错误码为:"+kellyj.status,{offset:'10px'});
                }
            });
            return false;
        });

        table.on('tool(file)',function (obj) {
            var infor = obj.data;
            if(obj.event === 'del'){
                $.ajax({
                    type:'POST',
                    url:'../task/deleteFile',
                    data:{
                        duta:infor.duta,
                        ffil:infor.ffil
                    },
                    dataType:'json',
                    success:function (res) {
                        if(res.code === 1){
                            obj.del();
                        }
                        return layer.msg(res.msg,{offset:'10px'});
                    },
                    error:function (kellyj) {
                        return layer.msg("发生错误，错误码为："+kellyj.status,{offset:'10px'});
                    }
                });
            }
        });

        $.ajax({
            type:'GET',
            url:'${base}/util/findC13',
            dataType:'json',
            success:function (res) {
                var sys = res.sys,user = res.user,sOption = "",uOption = "";
                for(var i = 0;i<sys.length;i++){
                    sOption += "<option value='"+sys[i].syno+"'>"+sys[i].dsca+"</option>";
                }
                for (var j = 0; j < user.length; j++) {
                    uOption += "<option value='" + user[j].id + "'>" + user[j].dsca + '(' + user[j].id + ')' + "</option>";
                }
                $("#syno").append(sOption);
                $("#csid").append(uOption);
                form.render();
            },
            error:function (kellyj) {
                return layer.msg("发生错误，错误码为:"+kellyj.status,{offset:'10px'});
            }
        });

        var uploadList = upload.render(fileUploadOption);
        var note = layedit.build('note',layEditOption);
    });
</script>
</body>
</html>

