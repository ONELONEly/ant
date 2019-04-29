<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/9/5
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加用户</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="./manage"><cite style="cursor: pointer;">用户管理</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">添加用户</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>

<div class="x-body layui-container">
    <div class="layui-form-item x-center">
        <div class="layui-input-block">
            <img class="modify-img" src="${base}/util/normal" id="head">
        </div>
    </div>
    <hr>
    <form class="layui-form layui-form-pane" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label">用户编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="USID" lay-verify="usid" autocomplete="off" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="DSCA" lay-verify="required|dsca" autocomplete="off" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="PAWD" lay-verify="pawd" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-block">
                <select name="DEPT" id="dept" lay-filter="dept" lay-verify="dept" lay-search>
                    <option value="" class="n-select" disabled selected>请选择部门</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">岗位</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="JWWJ" lay-verify="jwwj" required/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">团队</label>
            <div class="layui-input-block">
                <select name="GROP" id="grop" lay-filter="grop" lay-verify="grop" lay-search>
                    <option value="" class="n-select" disabled selected>请选择团队</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">科室</label>
            <div class="layui-input-block">
                <select name="ACCO" id="acco" lay-filter="acco" lay-verify="acco" lay-search>
                    <option value="" class="n-select" disabled selected>请选择科室</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">公司</label>
            <div class="layui-input-block">
                <select name="COMP" id="comp" lay-filter="comp" lay-verify="comp" lay-search>
                    <option value="" class="n-select" disabled selected>请选择公司</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">权限级别</label>
            <div class="layui-input-block">
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
            </div>
        </div>

        <div class="layui-form-item x-center">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn layui-btn-radius" id="submit" value="提交" lay-filter="insert" lay-submit/>
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
                for(var n = 0;n<comp.length;n++){
                    cOption += "<option value='"+comp[n].id+"'>"+comp[n].dsca+"</option>";
                }
                $("#dept").append(dOption);
                $("#grop").append(gOption);
                $("#acco").append(aOption);
                $("#comp").append(cOption);
                form.render();
            },
            error:function (kellyj) {
                layer.msg("发生错误:"+kellyj.status,{offset:'10px'});
            }
        });

        form.verify({
            usid:function (value) {
                if(checkForm(value)){
                    return "请输入用户ID";
                }
            },
            dsca:function (value) {
                if(checkForm(value)){
                    return "请输入用户名称";
                }
            },
            pawd:function (value) {
                if(checkForm(value)){
                    return "请输入用户密码";
                }
            },
            dept:function (value) {
                if(checkForm(value)){
                    return "请选择用户部门";
                }
            },
            jwwj:function (value) {
                if(checkForm(value)){
                    return "请输入用户岗位";
                }
            },
            grop:function (value) {
                if(checkForm(value)){
                    return "请选择用户所属团队";
                }
            },
            acco:function (value) {
                if(checkForm(value)){
                    return "请选择用户科室";
                }
            },
            comp:function (value) {
                if(checkForm(value)){
                    return "请选择用户公司";
                }
            }
        });

        form.on("submit(insert)",function (data) {
            console.log(data.field);
            $.ajax({
                type:'POST',
                url:'${base}/user/insert',
                data:data.field,
                dataType:'json',
                success:function (res) {
                    if(res.code === 1){
                        layer.confirm(res.msg,{btn:['确定','返回'],offset:'10px',anim:4},function () {
                            window.location.reload();
                        },function () {
                            window.location.replace("./manage");
                        });
                    }else{
                        layer.alert(res.msg,{offset:'10px'});
                    }
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
