<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 180365
  Date: 2017/8/30
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>评分</title>
    <c:import url="../../static1.html"/>
</head>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:"><cite style="cursor: pointer;">首页</cite></a>
        <a href="javascript:"><cite style="cursor: pointer;">项目</cite></a>
        <a href="./index"><cite style="cursor: pointer;">绩效</cite></a>
        <a href="javascript:location.replace(location.href);"><cite style="cursor: pointer;">评分</cite></a>
        <a class="layui-btn layui-btn-sm layui-btn-radius l-refresh" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon l-center layui-icon-refresh"></i></a>
    </span>
</div>
<div class="x-body">
    <hr/>
    <form class="layui-form">
        <input type="hidden" value="${obj.grop}" id="grop"/>
        <input type="hidden" value="${obj.ptno}" id="ptno"/>
        <div class="layui-collapse">
        </div>
        <button type="submit" class="layui-btn x-margin" lay-filter="put" lay-submit>提交</button>
        <button type="submit" class="layui-btn x-margin" lay-filter="save" lay-submit>保存</button>

    </form>
</div>
<script language="JavaScript">
    layui.use(['element','form','jquery'],function () {
        var element = layui.element,$ = layui.jquery,form = layui.form;
        var grop =$("#grop").val();
        var ptno = $('#ptno').val();
        $.ajax({
            url: '${base}/grade/markTemplate',
            data:{
                grop:grop,
                ptno:ptno
            },
            type:'GET',
            dataType:'json',
            success:function (data){
                var base= data.team,mark = data.mark,score = data.score;
                for(var i = 0;i<mark.length;i++){
                    var content = "<div class='layui-colla-item'>"+
                        "<h2 class='layui-colla-title'>"+mark[i].dsca+"(分项占比:"+mark[i].pjjp+"%;基础分:"+mark[i].cons+")</h2>"+
                        "<div class='layui-colla-content layui-show'>"+
                        "<span>"+mark[i].deti+"</span><hr>"+getContent(base,mark[i].cbase012VOS,score,ptno)+
                        "</div>"+
                        "</div>";
                    $(".layui-collapse").append(content);
                }
                for(var j = 0;j<base.length;j++){
                    var util = "<input type='hidden' value='"+base[j].USID+"' name='util'/>";
                    $(".layui-form").append(util);
                }
                form.render();
                element.init();
            },
            error:function (kellyJ) {
                alert("发生错误:"+kellyJ.status)
            }
        });
        /*remk_P03_180304*/
        form.on("submit(put)",function (res) {
            var result = operate(res,0);
            layer.confirm("提交完毕",{btn:['返回'],offset:'10px'},function(){
                window.location.replace("${base}/grade/index");
            });
            return false;
        });

        form.on("submit(save)",function (res) {
            var result = operate(res,2);
            layer.confirm("保存完毕",{btn:['返回'],offset:'10px'},function(){
                window.location.replace("${base}/grade/index");
            });
            return false;
        });


        function operate(res,sta1){
            var data = res.field;
            var result = 0;
            var util = document.getElementsByName("util");
            var postData = [];
            for(var j = 0;j<util.length;j++){
                for(var item in data){
                    var index = item.indexOf("_")
                    var lastIndex = item.lastIndexOf("_")
                    var type = item.substring(0,index);
                    var pjno = item.substring(index+1,lastIndex);
                    var usid = item.substring(lastIndex+1);
                    if(usid === util[j].value){
                        if(type === 'mark'){
                            var remk = "remk_"+pjno+"_"+usid;
                            var pick = {
                                csid:usid,
                                cons:data[item],
                                pjno:pjno,
                                ptno:ptno,
                                remk:data[remk]
                            };
                            postData.push(pick);
                        }
                    }
                }
            }
            $.ajax({
                type:'POST',
                url:'${base}/grade/insertScore',
                data:{
                    list:postData
                },
                dataType:'json',
                success:function (res) {
                    result = res.code;
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
            $.ajax({
                type:'POST',
                url:'${base}/grade/updateSta1',
                data:{
                    ptno:ptno,
                    sta1:sta1
                },
                dataType:'json',
                success:function (res) {
                    result = res.code;
                },
                error:function (kj) {
                    layer.alert("发生错误:"+kj.status,{offset:'10px'});
                }
            });
            return result;
        }
    });

    function getContent(base,option,score,ptno){
        if(option.length>0) {
            var name = "";
            for (var i = 0; i < base.length; i++) {
                var remk ="";
                for(var j = 0;j<score.length;j++) {
                    if(base[i].USID === score[j].csid && option[0].pjno === score[j].pjno && ptno === score[j].ptno && score[j].remk !== undefined) {
                        remk = score[j].remk;
                    }
                }
                name += "<div class='layui-input-inline x-left'>"+
                    "<label class='layui-form-label'>" + base[i].DSCA + "</label>" + getOption(option,base[i].USID,score,ptno)+
                    "<input type='text' value='"+remk+"' name='remk_" + option[0].pjno + "_" + base[i].USID + "'/><br/>" +
                    "</div><br/>";
            }
            return name;
        }
        return '';
    }

    function getOption(option,usid,score,ptno){
        var name = "";
        var count = 0;
        for(var m = 0;m<option.length;m++){
            for(var n = 0;n<score.length;n++) {
                if(option[m].opco === score[n].cons && usid === score[n].csid && option[m].pjno === score[n].pjno && ptno === score[n].ptno) {
                    count++;
                }
            }
        }
        for(var i = 0;i<option.length;i++){
            var status = true;
            for(var j = 0;j<score.length;j++) {
                if(option[i].opco === score[j].cons && usid === score[j].csid && option[i].pjno === score[j].pjno && ptno === score[j].ptno) {
                    name += "<input type='radio' name='mark_" + option[i].pjno + "_" + usid + "' value='" + option[i].opco + "' title='" + option[i].opco + "' checked/>";
                    status = false;
                }
            }
            if(status && count === 0 && i === 0) {
                name += "<input type='radio' name='mark_" + option[i].pjno + "_" + usid + "' value='" + option[i].opco + "' title='" + option[i].opco + "' checked/>";
            }else if(status){
                name += "<input type='radio' name='mark_" + option[i].pjno + "_" + usid + "' value='" + option[i].opco + "' title='" + option[i].opco + "'/>";

            }
        }
        return name;
    }
</script>
</body>
</html>