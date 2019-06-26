<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/19
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>编辑任务</title>
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
        <a href="javascript:" style="line-height: 40px;"><cite style="cursor: pointer;">首页</cite></a>
        <c:choose>
            <c:when test="${obj.key && obj.isManager}">
                <a href="../require/manage" class="reback"><cite style="cursor: pointer;">需求管理</cite></a>
            </c:when>
            <c:when test="${obj.key && !obj.isManager}">
                <a href="../require/user" class="reback"><cite style="cursor: pointer;">需求管理</cite></a>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${obj.isManager}">
                        <a href="./manage" class="reback"><cite style="cursor: pointer;">任务管理</cite></a>
                    </c:when>
                    <c:otherwise>
                        <a href="../user/task" class="reback"><cite style="cursor: pointer;">任务管理</cite></a>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">编辑任务</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form layui-form-pane">
        <div style="padding-bottom: 40px;">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" value="${obj.task.taid}" id="taid"/>
                    <input type="hidden" value="${obj.task.usid}" id="usid"/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="x-center">
                    <input type="text" class="layui-input x-center" value="${obj.task.titl}" name="titl" id="titl" placeholder="请输入标题" lay-verify="required|titl" required/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">请选择绩效:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="ptno" id="ptno" lay-filter="ptno" lay-verify="ptno" lay-search>
                        <option value="${obj.task.ptno}" class="n-display" disabled selected>${obj.task.ptnonam}</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">类型:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="ptyp" id="ptyp" lay-filter="ptyp" lay-verify="ptyp" lay-search>
                        <option value="${obj.task.ptyp}" class="n-display" disabled selected>${obj.task.ptypnam}</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <textarea id="note" class="n-display">${obj.note}</textarea>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">派发给:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="csid" id="csid" lay-filter="csid" lay-verify="csid" lay-search>
                        <option value="${obj.task.csid}" class="n-display" disabled selected>${obj.task.cnam}</option>
                    </select>
                </div>

                <div class="layui-input-inline">
                    <label class="layui-form-label">测试用户:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="tepr" id="tepr" lay-filter="tepr" lay-verify="tepr" lay-search>
                        <option value="${obj.task.tepr}" class="n-display" disabled selected>${obj.task.tnam}</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">关键用户:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="ksid" id="ksid" lay-filter="ksid" lay-verify="ksid" lay-search>
                        <option value="${obj.task.ksid}" class="n-display" disabled selected>${obj.task.knam}</option>
                    </select>
                </div>

                <div class="layui-input-inline">
                    <label class="layui-form-label">任务类型:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="puno" id="puno" lay-filter="puno" lay-verify="puno" lay-search>
                        <option value="${obj.task.puno}" class="n-display" disabled selected>${obj.task.punonam}</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label">系统/项目:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="syno" id="syno" lay-filter="syno" lay-verify="syno" lay-search>
                        <option value="${obj.task.syno}" class="n-display" disabled selected>${obj.task.synonam}</option>
                    </select>
                </div>

                <div class="layui-input-inline">
                    <label class="layui-form-label">阶段:</label>
                </div>
                <div class="layui-input-inline">
                    <select name="jied" id="jied" lay-filter="jied" lay-verify="jied" lay-search>
                        <option value="${obj.task.jied}" class="n-display" disabled selected>${obj.jieddsca}</option>
                    </select>
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
                        <input type="radio" name="sta2" value="33" cl0ass="layui-form-radio" title="低"/>
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
                    <button type="button" class="layui-btn layui-btn-radius layui-bg-black" id="uploadList">选择多文件</button>
                    <div id="layui-progress">

                    </div>
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
            <table class="layui-table" lay-data="{url:'./queryAllFile?taid=${obj.task.taid}',page:false,id:'file'}" lay-filter="file">
                <thead>
                <tr>
                    <th lay-data="{field:'ffil',align:'center',width:'50%'}">文件名</th>
                    <th lay-data="{field:'fsiz',align:'center',width:'20%'}">大小</th>
                    <th lay-data="{fixed:'right',align:'center',width:'30%',templet:'#operate'}">操作</th>
                </tr>
                </thead>
            </table>
            <script type="text/html" id="operate">
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
            </script>
        </div>
        <div class="x-center x-body bottom-buttom" style="margin-left:-20px;">
            <div class="layui-form-item">
                <button type="button" class="layui-btn layui-btn-radius" id="update" lay-filter="update" lay-submit>修改任务</button>
            </div>
        </div>
    </form>

</div>
<script language="JavaScript">
    layui.use(['form','jquery','element','layer','layedit','upload','table'],function () {
        var form = layui.form,$ = layui.jquery,element = layui.element,table=layui.table,
            layer = layui.layer,layedit = layui.layedit,upload = layui.upload;
        var fileList = $("#fileList"),taid = $("#taid").val(),usid = $("#usid").val(),ptno = "${obj.task.ptno}";
        var progressItem = {
            currentProgress: 0,
            maxProgressValue: 0,
        },itemCount = 0,valueCount = 0,totalValue = [];

        getPtnoAfter(ptno)

        var layEditOption = {
            height:'300px',
            uploadImage: {
                url:'${base}/task/insertImage',
                type:'POST'
            }
        };

        var files;
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
            xhr: xhrOnProgress,
            progress: function (value, loaded, total) {
                if( value > 0 && value < 100) {
                    if (valueCount === 0 && progressItem.currentProgress === 0) {
                        progressItem.maxProgressValue = loaded

                        progressItem.currentProgress = value
                    } else {
                        if (loaded > progressItem.maxProgressValue && value > progressItem.currentProgress) {
                            progressItem.maxProgressValue = loaded
                            progressItem.currentProgress = value
                        }
                    }
                    if (totalValue.indexOf(total) === -1) {
                        valueCount ++;
                    }
                }
                element.progress('upload',progressItem.currentProgress + '%')
            },
            choose:function (obj) {
                files = obj.pushFile();//将每次选择的文件追加到文件队列
                obj.preview(function (index,file) {//读取本地文件
                    itemCount++;
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ (file.size/1024).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
//                        ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

//                    tr.find(".demo-reload").on('click',function () {//单个重传
//                        obj.upload(index,file);
//                    });

                    tr.find(".demo-delete").on('click',function () {//单个删除
                        delete files[index];
                        tr.remove();
                    });
                    fileList.append(tr);
                });
                var progressStr = '<div class="layui-progress x-margin x-margin-20" lay-showPercent="true" lay-filter="upload">' +
                    '                    <div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>' +
                    '</div>';
                $("#layui-progress").html(progressStr)
                element.render()
                progressItem.currentProgress = 0
            },
            done:function (res,index,upload) { //上传完毕
                itemCount--
                if (itemCount === 0) {
                    progressItem.currentProgress = 100
                }
                if(res.code === 1){//上传成功
                    table.reload("file");
                    var tr = fileList.find("tr#upload-"+index),tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); // 清空操作
                    delete files[index];
                    element.progress('upload',progressItem.currentProgress + '%')
                    return;
                }
                this.error(index,upload);
                element.progress('upload',progressItem.currentProgress + '%')
            },
            error:function (index,upload) {
                itemCount--
                if (itemCount === 0) {
                    progressItem.currentProgress = 100
                }
                var tr = fileList.find("tr#upload-"+index),tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find(".demo-reload").removeClass('layui-hide');
                element.progress('upload',progressItem.currentProgress + '%')
            }
        };

        form.verify({
            ptno:function (value) {
                if(checkForm(value)){
                    return "请选择绩效表";
                }
            },
            ptyp:function (value) {
                if(checkForm(value)){
                    return "请选择类型";
                }
            },
            csid:function (value) {
                if(checkForm(value)){
                    return "请选择接收人";
                }
            },
            tepr:function (value) {
                if(checkForm(value)){
                    return "请选择测试用户";
                }
            },
            ksid:function (value) {
                if(checkForm(value)){
                    return "请选择关键用户";
                }
            },
            syno:function (value) {
                if(checkForm(value)){
                    return "请选择系统/项目";
                }
            },
            puno: function (value) {
                if (checkForm(value)) {
                    return "请选择任务类型";
                }
            },
            jied:function (value) {
                if (checkForm(value)) {
                    return "请选择任务阶段";
                }
            }
        });

        form.on("submit(update)",function (data) {
            var noteContent = layedit.getContent(note);
            var trans = data.field;
            $.ajax({
                type:'POST',
                url:'${base}/task/updateTask',
                data:{
                    taid:taid,
                    usid:usid,
                    titl:trans.titl,
                    csid:trans.csid,
                    ksid:trans.ksid,
                    ptyp:trans.ptyp,
                    sta2:trans.sta2,
                    sta3:trans.sta3,
                    syno:trans.syno,
                    jied:trans.jied,
                    tepr:trans.tepr,
                    puno:trans.puno,
                    ptno:trans.ptno,
                    require:${obj.key},
                    edit:noteContent
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg+"返回上一页？",{btn:['确定'],offset:'10px',anim:4},function () {
                            window.location.replace($(".reback").attr("href"));
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

        table.on('tool(file)',function (obj) {
            var infor = obj.data;
            if(obj.event === 'del'){
                $.ajax({
                    type:'POST',
                    url:'./deleteFile',
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

        form.on('select(syno)',function (data) {
            triggerJied(data.value);
        });

        triggerJied(${obj.task.syno});

        function triggerJied(syno){
            var tOption ="";
            $.ajax({
                type:'POST',
                url:'${base}/util/findT3DS_jied',
                data:{
                    syno:syno
                },
                dataType:'json',
                success:function (data) {

                    var jieds = data.jieds;
                    for (var m = 0; m < jieds.length; m++) {
                        tOption += "<option value='" + jieds[m].SubProjectID + "'>" + jieds[m].Title + "</option>";
                    }
                    $("#jied").html("");
                    $("#jied").append(tOption);
                    form.render();

                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        }

        form.on('select(ptno)',function (data) {
            getPtnoAfter(data.value)
        });

        $.ajax({
            type:'POST',
            url:'${base}/util/findC0C13C14',
            dataType:'json',
            success:function (data) {
                var user = data.user,sys = data.sys,stage = data.stage,project = data.project;
                var uOption = "",sOption = "",tOption ="",pOption = "",jOption = "";
                for(var i = 0;i<user.length;i++){
                    uOption += "<option value='"+user[i].id+"'>"+user[i].dsca+'('+user[i].id+')'+"</option>";
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
                $("#ksid").append(uOption);
                $("#tepr").append(uOption);
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

        function getPtnoAfter(ptno) {
            var tOption ="";
            $.ajax({
                type:'GET',
                url:'${base}/util/findtaskInsertC11',
                data:{
                    ptno:ptno
                },
                dataType:'json',
                success:function (data) {
                    var type = data.rule;
                    for (var m = 0; m < type.length; m++) {
                        tOption += "<option value='" + type[m].pjno + "'>" + type[m].dsca + "</option>";
                    }
                    $("#ptyp").html(tOption);
                    form.render();
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
        }
    });
</script>
</body>
</html>

