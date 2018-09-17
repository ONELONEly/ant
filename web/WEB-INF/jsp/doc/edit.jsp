<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/21
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人周报</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <c:if test="${obj.key == true}">
            <a href="javascript:"><cite style="cursor: pointer;">我的</cite></a>
            <a href="../user/weekNewsManage" id="choose"><cite style="cursor: pointer;">周报管理</cite></a>
        </c:if>
        <c:if test="${obj.key == false}">
            <a href="javascript:"><cite style="cursor: pointer;">文档</cite></a>
            <a href="./manage" id="choose"><cite style="cursor: pointer;">文档管理</cite></a>
        </c:if>
        <a href="javascript:location.replace(location.href)"><cite style="cursor: pointer;">文档编辑</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <form class="layui-form layui-form-panel">
        <input type="hidden" value="${obj.doc.doid}" id="doid"/>
        <input type="hidden" value="${obj.doc.cdat}" id="cdat"/>
        <input type="hidden" value="${obj.doc.usid}" id="usid"/>

        <div class="layui-form-item">
            <div class="x-center">
                <input type="text" class="layui-input x-center" value="${obj.doc.tilt}" name="tilt" id="tilt" placeholder="请输入标题" lay-verify="required|tilt" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">文档类型</label>
            <div class="layui-input-inline">
                <select name="ctyp" id="ctyp" lay-verify="required|ctyp" lay-search="" disabled>
                    <option value="${obj.doc.ctyp}" class="n-display" disabled selected>${obj.doc.ctypnam}</option>
                </select>
            </div>

            <label class="layui-form-label">接收人</label>
            <div class="layui-input-inline">
                <select name="csid" id="csid" lay-verify="required|csid" lay-search="" disabled>
                    <option value="${obj.doc.csid}" class="n-display" disabled selected>${obj.doc.cnam}</option>
                </select>
            </div>
        </div>

        <c:if test="${obj.doc.ctyp==1}">

        <div class="layui-form-item">
            <label class="layui-form-label">日期</label>
            <div class="layui-input-inline">
                <select name="sdat" id="sdat" lay-verify="required|sdat" lay-search="" disabled>
                    <option value="${obj.doc.sdat}" class="n-display" disabled selected>${obj.doc.sdat}</option>
                </select>
            </div>

            <label class="layui-form-label">周数</label>
            <div class="layui-input-inline">
                <select name="csid" id="csid" lay-verify="required|csid" lay-search="" disabled>
                    <option value="${obj.doc.csid}" class="n-display" disabled selected>${obj.doc.week}</option>
                </select>
            </div>

            <label class="layui-form-label">团队</label>
            <div class="layui-input-inline">
                <select name="grop" id="grop" lay-verify="required|grop" lay-search="" disabled>
                    <option value="${obj.doc.grop}" class="n-display" disabled selected>${obj.doc.grop}</option>
                </select>
            </div>
        </div>
        </c:if>
        <div class="layui-form-item">
            <textarea id="doc" class="n-display">${obj.note}</textarea>
        </div>
        <div class="layui-form-item">
            <c:if test="${obj.doc.stat == null}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.stat == 0}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.stat == 1}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.stat == 2}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.stat == 3}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.stat == 4}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.stat == 5}">
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="0" class="layui-form-radio" title="私有"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="1" class="layui-form-radio" title="组内"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="2" class="layui-form-radio" title="科室"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="3" class="layui-form-radio" title="部门"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="4" class="layui-form-radio" title="公司"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="stat" value="5" class="layui-form-radio" title="完全公开" checked/>
                </div>
            </c:if>
        </div>
        <div class="layui-form-item">
            <c:if test="${obj.doc.sta2 == null}">
                <div class="layui-input-inline">
                    <input type="radio" name="sta2" value="0" class="layui-form-radio" title="显示"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="sta2" value="1" class="layui-form-radio" title="隐藏"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.sta2 == 0}">
                <div class="layui-input-inline">
                    <input type="radio" name="sta2" value="0" class="layui-form-radio" title="显示" checked/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="sta2" value="1" class="layui-form-radio" title="隐藏"/>
                </div>
            </c:if>
            <c:if test="${obj.doc.sta2 == 1}">
                <div class="layui-input-inline">
                    <input type="radio" name="sta2" value="0" class="layui-form-radio" title="显示"/>
                </div>
                <div class="layui-input-inline">
                    <input type="radio" name="sta2" value="1" class="layui-form-radio" title="隐藏" checked/>
                </div>
            </c:if>
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
        <table class="layui-table" lay-data="{url:'./queryAllFile?doid=${obj.doc.doid}',page:false,id:'file'}" lay-filter="file">
            <thead>
            <tr>
                <th lay-data="{field:'ffil',align:'center',width:'50%'}">文件名</th>
                <th lay-data="{field:'fsiz',align:'center',width:'20%'}">大小</th>
                <th lay-data="{fixed:'right',align:'center',width:'30%',templet:'#operate'}">操作</th>
            </tr>
            </thead>
        </table>
        <script type="text/html" id="operate">
            <a class="layui-btn layui-btn-xs layui-bg-black" lay-event="del">删除</a>
        </script>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <button type="button" class="button layui-btn layui-btn-radius layui-bg-green" lay-filter="put" lay-submit="">修改</button>
            </div>
        </div>

    </form>
</div>
<script language="JavaScript">
    layui.use(['layedit','element','jquery','form','upload','table','layer'],function () {
        var layedit = layui.layedit,element = layui.element,$ = layui.jquery,
            form = layui.form,upload = layui.upload,table = layui.table,layer = layui.layer;

        var fileList = $("#fileList"),doid = $("#doid").val();

        var files,fileUploadOption = {
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
                files = obj.pushFile();//将每次选择的文件追加到文件队列
                obj.preview(function (index,file,result) {//读取本地文件
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
//                            ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

//                        tr.find(".demo-reload").on('click',function () {//单个重传
//                            obj.upload(index,file);
//                        });

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

        var uploadList = upload.render(fileUploadOption);

        var layeditOption = {
            uploadImage:{
                url:'${base}/task/insertImage',
                type:'POST'
            },
            height:'600px'
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
                layer.alert("发生错误:"+kj.status,{offset:'10px'});
            }
        });

        form.on('submit(put)',function (data) {
            var infor = data.field,content = layedit.getContent(note),
                cdat = $("#cdat").val(),usid = $("#usid").val();
            $.ajax({
                type:'POST',
                url:'${base}/doc/updateDoc',
                data:{
                    tilt:infor.tilt,
                    csid:infor.csid,
                    ctyp:infor.ctyp,
                    stat:infor.stat,
                    sta2:infor.sta2,
                    sdat:infor.sdat,
                    week:infor.week,
                    grop:infor.grop,
                    date:cdat,
                    usid:usid,
                    doid:doid,
                    edit:content
                },
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm("文档修改成功,返回上一页？",{offset:'10px'},function(){
                            window.location.replace($("#choose").attr("href"))
                        },function () {
                            window.location.reload();
                        });
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
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
    });
</script>
</body>
</html>

