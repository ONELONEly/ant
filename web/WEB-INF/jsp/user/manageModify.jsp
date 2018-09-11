<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/10/17
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改用户资料</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="./manage"><cite style="cursor: pointer;">用户管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">修改资料</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>

<div class="x-body layui-container">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item x-center">
            <div class="layui-input-block">
                <img class="modify-img" src="${base}/user/getUserHeader?usid=${obj.user.USID}" id="head">
            </div>
        </div>
        <hr>
        <div class="layui-form-item x-center">
            <div class="layui-input-block">
                <input type="button" class="layui-btn layui-btn-sm" id="modify" value="上传"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">用户ID</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="USID" value="${obj.user.USID}" disabled/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="DSCA" value="${obj.user.DSCA}" lay-verify="dsca" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input" name="PAWD" id="pawd" value="${obj.user.PAWD}" lay-verify="pawd" disabled/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input" name="rePawd" id="rePawd" value="${obj.user.PAWD}" lay-verify="rePawd" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">岗位</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="JWWJ" value="${obj.user.JWWJ}" lay-verify="jwwj" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">团队</label>
            <div class="layui-input-block">
                <select name="GROP" id="grop" lay-filter="grop" lay-verify="grop" lay-search>
                    <option value="${obj.user.GROP}" class="n-select" disabled selected>${obj.user.GROPNAM}</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">科室</label>
            <div class="layui-input-block">
                <select name="ACCO" id="acco" lay-filter="acco" lay-verify="acco" lay-search>
                    <option value="${obj.user.ACCO}" class="n-select" disabled selected>${obj.user.ACCONAM}</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-block">
                <select name="DEPT" id="dept" lay-filter="dept" lay-verify="dept" lay-search>
                    <option value="${obj.user.DEPT}" class="n-display" disabled selected>${obj.user.DEPTNAM}</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">权限级别</label>
            <div class="layui-input-block">
                <c:if test="${obj.user.STA2 == 0 || obj.user.STA2 == null}">
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="0" title="个人" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="1" title="组"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="2" title="科室"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="3" title="部门"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="4" title="公司"/>
                    </div>
                </c:if>
                <c:if test="${obj.user.STA2 == 1}">
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="0" title="个人"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="1" title="组" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="2" title="科室"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="3" title="部门"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="4" title="公司"/>
                    </div>
                </c:if>
                <c:if test="${obj.user.STA2 == 2}">
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="0" title="个人"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="1" title="组"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="2" title="科室" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="3" title="部门"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="4" title="公司"/>
                    </div>
                </c:if>
                <c:if test="${obj.user.STA2 == 3}">
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="0" title="个人"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="1" title="组"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="2" title="科室"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="3" title="部门" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="4" title="公司"/>
                    </div>
                </c:if>
                <c:if test="${obj.user.STA2 == 4}">
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="0" title="个人"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="1" title="组"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="2" title="科室"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="3" title="部门"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" name="STA2" value="4" title="公司" checked/>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="layui-form-item x-center">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn layui-btn-radius" id="submit" value="提交" lay-filter="modify" lay-submit/>
                <input type="reset" class="layui-btn layui-btn-radius" value="重置" lay-submit/>
            </div>
        </div>
    </form>
</div>
<script language="JavaScript">
    layui.use(['element','form','jquery','upload'],function () {
        var element = layui.element,form = layui.form,$ = layui.jquery,upload = layui.upload;

        $.ajax({
            type:'POST',
            url:'${base}/util/findC1C6C9C17',
            dataType:'json',
            success:function (data) {
                var dept = data.dept,grop = data.grop,acco = data.acco,comp = data.comp;
                var dOption = "",gOption = "",aOption = "",cOption = "'";
                for(var i = 0;i<dept.length;i++){
                    dOption += "<option value='"+dept[i].id+"'>"+dept[i].dsca+"</option>";
                }
                for(var j = 0;j<grop.length;j++){
                    gOption += "<option value='"+grop[j].id+"'>"+grop[j].dsca+"</option>";
                }
                for(var m = 0;m<acco.length;m++){
                    aOption += "<option value='"+acco[m].id+"'>"+acco[m].dsca+"</option>";
                }
//                    for(var n = 0;n<comp.length;n++){
//                        cOption += "<option value='"+comp[n].comp+"'>"+comp[n].dsca+"</option>";
//                    }
                $("#dept").append(dOption);
                $("#grop").append(gOption);
                $("#acco").append(aOption);
//                    $("#comp").append(cOption);
                form.render();
            },
            error:function (kellyj) {
                layer.msg("发生错误:"+kellyj.status,{offset:'10px'});
            }
        });

        var uploadInsert = upload.render({
            elem:"#modify",
            url:'${base}/user/modifyHeader?usid=${obj.user.USID}',
            method:"POST",
            size:1000,
            accept:'file',
            before:function (obj) {
                obj.preview(function (index,file,result) {
                    $("#head").attr("src",result);
                    $("#head",window.parent.document).attr("src",result);
                });
            },
            done:function (res) {
                if(res.code === 1){
                    return layer.msg(res.msg,{offset:'10px'});
                }else{
                    return layer.msg(res.msg,{offset:'10px'});
                }
            },
            error:function () {
                var operate = $('#operate');
                operate.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                operate.find('demo-reload').on('click',function () {
                    uploadInsert.upload();
                });
            }
        });

        form.verify({
            dsca:function (value) {
                if(checkForm(value)){
                    return "请输入名称";
                }
            },
            rePawd:function (value) {
                if(checkForm(value)){
                    return "请确认密码";
                }
            },
            jwwj:function (value) {
                if(checkForm(value)){
                    return "请输入岗位";
                }
            },
            dept:function (value) {
                if(checkForm(value)){
                    return "请选择部门";
                }
            },
            grop:function (value) {
                console.log(value.length);
                if(checkForm(value)){
                    return "请选择团队";
                }
            },
            acco:function (value) {
                if(checkForm(value)){
                    return "请选择科室";
                }
            }
        });

        form.on("submit(modify)",function (data){
            $.ajax({
                type:'POST',
                url:'${base}/user/modify?',
                data:data.field,
                dataType:'json',
                success:function (data) {
                    if(data.code === 1){
                        layer.confirm(data.msg+",返回上一页？",{btn:['确定','刷新'],offset:'100px',anim:4},function () {
                            window.location.replace("./manage");
                        },function () {
                            window.location.reload();
                        });
                    }else{
                        return layer.msg(data.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>

