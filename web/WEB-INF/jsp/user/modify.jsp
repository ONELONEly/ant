<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/4
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改个人资料</title>
    <c:import url="../../static1.html"/>
    <script language="JavaScript">
        layui.use(['element','form','jquery','upload'],function () {
            var element = layui.element,form = layui.form,$ = layui.jquery,upload = layui.upload;

            var uploadInsert = upload.render({
                elem:"#modify",
                url:'${base}/user/modifyHeader?usid=${obj.user.USID}',
                method:"POST",
                size:1000,
                accept:'file',
                choose:function (obj) {
                    obj.preview(function (index,file,result) {
                        $("#head").attr("src",result);
                    });
                },
                done:function (res) {
                    if(res.code === 1){
                        window.location.reload();
                        return layer.msg(res.msg);
                    }else{
                        return layer.msg(res.msg);
                    }
                },
                error:function () {
                    var operate = $('#operate');
                    operate.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    operate.find('demo-reload').on('click',function () {
                        uploadInsert.upload();
                    });
                }
            });

            form.verify({
                dsca:function (value) {
                    if(value.length === 0){
                        return "请输入名称"
                    }
                },
                rePawd:function (value) {
                    if(value.length === 0){
                        return "请确认密码"
                    }
                },
                cpid:function (value) {
                    if(value.length === 0){
                        return "请输入厂牌号码"
                    }
                },
                tel1:function (value) {
                    if(value.length === 0){
                        return "请输入电话号码"
                    }
                },
                mail:function (value) {
                    if(value.length === 0){
                        return "请输入邮箱号码"
                    }
                },
                jwwj:function (value) {
                    if(value.length === 0){
                        return "请输入岗位"
                    }
                }
            });

            form.on("submit(modify)",function (data){
                console.log(data.field);
                $.ajax({
                    type:'POST',
                    url:'${base}/user/modify?',
                    data:data.field,
                    dataType:'json',
                    success:function (data) {
                        if(data.code === 1){
                            layer.confirm(data.msg,{btn:['确定'],offset:'100px',anim:4},function () {
                                window.location.reload();
                            });
                        }else{
                            return layer.msg(data.msg);
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
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">设置</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">修改资料</cite></a>
        <a class="layui-btn layui-btn-small layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center">ဂ</i></a>
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
                <input type="button" class="layui-btn layui-btn-small" id="modify" value="上传"/>
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
                <input type="password" class="layui-input" name="rePawd" id="rePawd" lay-verify="rePawd" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">厂牌号码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="CPID" value="${obj.user.CPID}" lay-verify="cpid" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="TEL1" value="${obj.user.TEL1}" lay-verify="tel1" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="MAIL" value="${obj.user.MAIL}" lay-verify="mail" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">岗位</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="JWWJ" value="${obj.user.JWWJ}" lay-verify="jwwj" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">是否短信通知</label>
            <div class="layui-input-block">
                <c:if test="${obj.user.STA3 == 0}">
                    <div class="layui-input-inline">
                        <input type="radio" class="layui-form-radio" name="STA3" value="0" title="是" checked/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" class="layui-form-radio" name="STA3" value="1" title="否"/>
                    </div>
                </c:if>
                <c:if test="${obj.user.STA3 == 1}">
                    <div class="layui-input-inline">
                        <input type="radio" class="layui-form-radio" name="STA3" value="0" title="是"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" class="layui-form-radio" name="STA3" value="1" title="否" checked/>
                    </div>
                </c:if>
                <c:if test="${obj.user.STA3 ==null}">
                    <div class="layui-input-inline">
                        <input type="radio" class="layui-form-radio" name="STA3" value="0" title="是"/>
                    </div>
                    <div class="layui-input-inline">
                        <input type="radio" class="layui-form-radio" name="STA3" value="1" title="否"/>
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
    <br><br><br><br><br><br><br><br><br>
</div>
</body>
</html>
