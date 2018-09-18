var width,height;

function task_del(present) {
    var parent = present.parent().parent();
    var item_id = parent.attr("id");
    var secondDot = item_id.indexOf("_",5);
    var lastDot = item_id.lastIndexOf("_");
    var item_pre = item_id.substring(0,lastDot+1); //获得当前行的前缀
    var item_row = item_id.substring(secondDot+1,lastDot); //获得当前行的行号
    var count =item_id.substring(lastDot+1); //获得当前行的任务号
    count--;
    var krgrop = 'krprop_'+item_row+'_'+count;
    $("#"+item_pre+"0").find('td').each(function () {
        var zIndex = $(this).attr("zIndex");
        if(zIndex === '0'){
            $(this).attr("rowspan",count+1);
        }
    });
    parent.remove();
    var item = "";
    if(count === 0){
        item = " <a href='javascript:' class='task_add' onclick='task_add($(this))'><i class='layui-icon layui-icon-add-1 layui-bg-green'></i></a>\n";
    }else{
        item = "<a href='javascript:' class='task_del' onclick=\'task_del($(this))\'><i class='layui-icon layui-icon-close layui-bg-red'></i></a>\n" +
            "<a href='javascript:' class='task_add' onclick=\'task_add($(this))\'><i class='layui-icon layui-icon-add-1 layui-bg-green'></i></a>\n";
    }
    $(item).insertAfter("#"+krgrop);
}

function task_add(present) {
    var parent = present.parent().parent(); //获得当前行
    var item_id = parent.attr("id"); //获得当前行的ID
    var secondDot = item_id.indexOf("_",5);
    var lastDot = item_id.lastIndexOf("_");
    var item_pre = item_id.substring(0,lastDot+1); //获得当前行的前缀
    var item_row = item_id.substring(secondDot+1,lastDot); //获得当前行的行号
    var count =item_id.substring(lastDot+1); //获得当前行的任务号
    count++;
    $("#"+item_pre+"0").find('td').each(function () {
        var zIndex = $(this).attr("zIndex");
        if(zIndex === '0'){
            $(this).attr("rowspan",count+1);
        }
    });
    var item = "<tr id='okr_item_"+item_row+"_"+count+"'><td class='none_pdding'>\n" +
        "                    <input type='text' name='achi_"+item_row+"_"+count+"' id='achi_"+item_row+"_"+count+"' placeholder='请输入关键成果' autocomplete='off' class='excel_input'>\n" +
        "                    <a href='javascript:' class='task_choose' onclick=\"task_choose($(this))\" data='achi_"+item_row+"_"+count+"'><i class='layui-icon layui-icon-search'></i></a>\n" +
        "                </td>\n" +
        "                <td class='none_pdding'>\n" +
        "                    <input type='text' name='krprop_"+item_row+"_"+count+"' id='krprop_"+item_row+"_"+count+"' placeholder='请输入KR权重' autocomplete='off' class='excel_input'>\n" +
        "                    <a href='javascript:' class='task_del' onclick=\"task_del($(this))\"><i class='layui-icon layui-icon-close layui-bg-red'></i></a>\n" +
        "                    <a href='javascript:' class='task_add' onclick=\"task_add($(this))\"><i class='layui-icon layui-icon-add-1 layui-bg-green'></i></a>\n" +
        "                </td></tr>";
    present.prev("a").remove();
    present.remove();
    $(item).insertAfter("#"+item_id);
}

function goal_add(present) {
    var parent = present.parent().parent().parent().parent();
    var item_id = parent.attr("id"); //获得当前行的ID
    var firstDot,secondDot,lastDot;
    secondDot = item_id.indexOf("_",5);
    lastDot = item_id.lastIndexOf("_");
    var item_row = item_id.substring(secondDot+1, lastDot); //获得当前行的行号
    var id_name = "okr_item_"+item_row+"_";
    var same = $("tr[id^='"+id_name+"']");
    item_row++;
    var nexts = parent.nextAll();
    if (nexts.length !== 0) {
        for (var i = 0; i < nexts.length; i++) {
            var next = $(nexts[i]);
            var children_count = next.children().length;
            var name, name_row, name_pre, name_last,
                inputs, next_id, next_row, count, now_row, input;
            if (children_count === 2) {
                next_id = next.attr("id");
                secondDot = next_id.indexOf("_",5);
                lastDot = next_id.lastIndexOf("_");
                next_row = next_id.substring(secondDot+1, lastDot);
                if(item_row-next_row !== 1) {
                    count = next_id.substring(lastDot+1);
                    now_row = ++next_row;
                    next_row--;
                    next.attr("id", "okr_item_" + now_row + "_" + count);
                    inputs = next.find("input");
                    for (var j = 0; j < inputs.length; j++) {
                        input = $(inputs[j]);
                        name = input.attr("name");
                        if (!checkForm(name)) {
                            if (name.length === 8) {
                                firstDot = name.indexOf("_");
                                lastDot = name.lastIndexOf("_");
                                name_pre = name.substring(0, firstDot+1);
                                name_row = name.substring(firstDot+1, lastDot);
                                name_last = name.substring(lastDot+1);
                                name_row++;
                                input.attr("name", name_pre + name_row + "_" + name_last);
                                input.attr("id", name_pre + name_row + "_" + name_last);
                            } else if (name.length === 10) {
                                firstDot = name.indexOf("_");
                                lastDot = name.lastIndexOf("_");
                                name_pre = name.substring(0, firstDot+1);
                                name_row = name.substring(firstDot+1, lastDot);
                                name_last = name.substring(lastDot+1);
                                name_row++;
                                input.attr("name", name_pre + name_row + "_" + name_last);
                                input.attr("id", name_pre + name_row + "_" + name_last);
                            }
                        }
                    }
                }
            } else {
                next_id = next.attr("id");
                secondDot = next_id.indexOf("_",5);
                lastDot = next_id.lastIndexOf("_");
                next_row = next_id.substring(secondDot+1, lastDot);
                count = next_id.substring(lastDot+1);
                now_row = ++next_row;
                next_row--;
                next.attr("id", "okr_item_" + now_row + "_" + count);
                next.find("strong").text(now_row);
                inputs = next.find("input");
                for (var k = 0; k < inputs.length; k++) {
                    input = $(inputs[k]);
                    name = input.attr("name");
                    if (!checkForm(name)) {
                        if (name.length === 6) {
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0,lastDot+1);
                            name_row = name.substring(lastDot+1);
                            name_row++;
                            input.attr("name", name_pre + name_row);
                            input.attr("id", name_pre + name_row);
                        } else if (name.length === 7) {
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0,lastDot+1);
                            name_row = name.substring(lastDot+1);
                            name_row++;
                            input.attr("name", name_pre + name_row);
                            input.attr("id", name_pre + name_row);
                        }else if (name.length === 8) {
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0, lastDot);
                            name_row = name.substring(lastDot+1);
                            if (name_pre === 'krperf') {
                                name_row++;
                                input.attr("name", 'krperf_' + name_row);
                                input.attr("id", 'krperf_' + name_row);
                            } else {
                                secondDot = name.indexOf("_",4);
                                lastDot = name.lastIndexOf("_");
                                name_pre = name.substring(0, secondDot+1);
                                name_row = name.substring(secondDot+1, lastDot);
                                name_last = name.substring(lastDot+1);
                                name_row++;
                                input.attr("name", name_pre + name_row + "_" + name_last);
                                input.attr("id", name_pre + name_row + "_" + name_last);
                            }
                        } else if (name.length === 10) {
                            secondDot = name.indexOf("_",5);
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0, secondDot+1);
                            name_row = name.substring(secondDot+1, lastDot);
                            name_last = name.substring(lastDot+1);
                            name_row++;
                            input.attr("name", name_pre + name_row + "_" + name_last);
                            input.attr("id", name_pre + name_row + "_" + name_last);
                        }
                    }
                }

                var selects = next.find("select");
                for (var m = 0; m < selects.length; m++) {
                    var select = $(selects[m]);
                    name = select.attr("name");
                    if (!checkForm(name)) {
                        lastDot = name.lastIndexOf("_");
                        name_pre = name.substring(0, lastDot+1);
                        name_row = name.substring(lastDot+1);
                        name_row++;
                        $(select).prev().attr("id", name_pre+name_row+"_0");
                        select.attr("name", name_pre + name_row);
                        select.attr("id", name_pre + name_row);
                    }
                }

                var texts = next.find("textarea");
                for (var o = 0; o < texts.length; o++) {
                    var text = $(texts[0]);
                    name = text.attr("name");
                    if (!checkForm(name)) {
                        lastDot = name.lastIndexOf("_");
                        name_pre = name.substring(0, lastDot+1);
                        name_row = name.substring(lastDot+1);
                        name_row++;
                        text.attr("name", name_pre + name_row);
                        text.attr("id", name_pre + name_row);
                    }
                }
            }
        }
    }
    var goal = "\n" +
        "            <tr id='okr_item_" + item_row + "_0'>\n" +
        "               <td class='none_pdding' zIndex='0' rowspan=''>\n" +
        "                   <strong>" + item_row + "</strong>\n" +
        "               </td>\n" +
        "                <td class='none_pdding' zIndex='0' rowspan=''>\n" +
        "                    <textarea type='text' name='goal_" + item_row + "' id='goal_" + item_row + "' placeholder='请输入目标' onfocus=\"jinyu($(this))\" onblur=\"rose($(this))\" autocomplete='off' class='excel_input'></textarea>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='0' rowspan=''>\n" +
        "                    <span id='ndat_" + item_row + "_0'></span>"+
        "                    <select name='ndat_" + item_row + "' id='ndat_" + item_row + "' lay-search=''>\n" +
        "                        <option value='' class='n-display' disabled selected>请选择周期</option>\n" +
        "                        <option value='1' class='n-display'>月度</option>\n" +
        "                        <option value='2' class='n-display'>季度</option>\n" +
        "                        <option value='3' class='n-display'>半年度</option>\n" +
        "                        <option value='4' class='n-display'>年度</option>\n" +
        "                    </select>" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='0' rowspan=''>\n" +
        "                    <span id='type_" + item_row + "_0'></span>"+
        "                    <select name='type_" + item_row + "' lay-filter='type' id='type_" + item_row + "' lay-search>\n" +
        "                        <option value='' style='display:none;' disabled selected>请选择类型</option>\n" +
        "                        <option value='1' class='n-display'>项目类</option>\n" +
        "                        <option value='2' class='n-display'>质量类</option>\n" +
        "                        <option value='3' class='n-display'>管理类</option>\n" +
        "                        <option value='4' class='n-display'>创新类</option>\n" +
        "                    </select>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='0'  rowspan=''>\n" +
        "                    <input type='text' name='prop_" + item_row + "' id='prop_" + item_row + "' placeholder='请输入权重' autocomplete='off' class='excel_input'>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='0'  rowspan=''>\n" +
        "                    <input type='text' name='perf_" + item_row + "' id='perf_" + item_row + "' placeholder='请输入完成情况' autocomplete='off' class='excel_input'>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='1' >\n" +
        "                    <input type='text' name='achi_" + item_row + "_0' id='achi_" + item_row + "_0' placeholder='请输入关键成果' autocomplete='off' class='excel_input'>\n" +
        "                    <a href='javascript:' class='task_choose' onclick='task_choose($(this))'><i class='layui-icon layui-icon-search'></i></a>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='1' >\n" +
        "                    <input type='text' name='krprop_" + item_row + "_0' id='krprop_" + item_row + "_0' placeholder='请输入KR权重' autocomplete='off' class='excel_input'>\n" +
        "                    <a href='javascript:' class='task_add' onclick='task_add($(this))'><i class='layui-icon layui-icon-add-1 layui-bg-green'></i></a>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='0' rowspan=''>\n" +
        "                    <input type='text' name='krperf_" + item_row + "' id='krperf_" + item_row + "' placeholder='请输入KR完成情况' autocomplete='off' class='excel_input'>\n" +
        "                </td>\n" +
        "                <td class='none_pdding' zIndex='0' rowspan=''>\n" +
        "                    <input type='text' name='zgrad_" + item_row + "' id='zgrad_" + item_row + "' placeholder='请输入自评分' autocomplete='off' class='excel_input'>\n" +
        "                </td>\n" +
        "                <td class='none_pdding none_border' zIndex='0' rowspan=''>\n" +
        "                    <div class='layui-form-item' style='margin: 0 auto;'>\n" +
        "                        <div>\n" +
        "                            <a href='javascript:' onclick=\"goal_del($(this))\"><i class='layui-icon layui-icon-close layui-bg-red'></i></a>\n" +
        "                        </div>\n" +
        "                        <div>\n" +
        "                            <a href='javascript:' onclick=\"goal_add($(this))\"><i class='layui-icon layui-icon-add-1 layui-bg-green'></i></a>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </td>\n" +
        "            </tr>";
    $(goal).insertAfter(same[same.length-1]);
    $(".form_render").trigger("click");
}

function goal_del(present) {
    var parent = present.parent().parent().parent().parent();
    var item_id = parent.attr("id"); //获得当前行的ID
    var firstDot,secondDot,lastDot;
    secondDot = item_id.indexOf("_",5);
    lastDot = item_id.lastIndexOf("_");
    var item_row = item_id.substring(secondDot+1,lastDot); //获得当前行的行号
    var id_name = "okr_item_"+item_row+"_";
    var same = $("tr[id^='"+id_name+"']");
    var nexts = parent.nextAll();
    for(var y = 0;y< same.length;y++){
        $(same[y]).remove();

    }
    if (nexts.length !== 0) {
        for (var i = 0; i < nexts.length; i++) {
            var next = $(nexts[i]);
            var children_count = next.children().length;
            var name, name_row, name_pre, name_last,
                inputs, next_id, next_row, count, now_row, input;
            if (children_count === 2) {
                next_id = next.attr("id");
                secondDot = next_id.indexOf("_",5);
                lastDot = next_id.lastIndexOf("_");
                next_row = next_id.substring(secondDot+1, lastDot);
                if(item_row-next_row !== 1) {
                    count = next_id.substring(lastDot+1);
                    now_row = --next_row;
                    next_row++;
                    next.attr("id", "okr_item_" + now_row + "_" + count);
                    inputs = next.find("input");
                    for (var j = 0; j < inputs.length; j++) {
                        input = $(inputs[j]);
                        name = input.attr("name");
                        if (!checkForm(name)) {
                            if (name.length === 8) {
                                secondDot = name.indexOf("_",4);
                                lastDot = name.lastIndexOf("_");
                                name_pre = name.substring(0, secondDot+1);
                                name_row = name.substring(secondDot+1, lastDot);
                                name_last = name.substring(lastDot+1);
                                name_row--;
                                input.attr("name", name_pre + name_row + "_" + name_last);
                                input.attr("id", name_pre + name_row + "_" + name_last);
                            } else if (name.length === 10) {
                                secondDot = name.indexOf("_",6);
                                lastDot = name.lastIndexOf("_");
                                name_pre = name.substring(0, secondDot+1);
                                name_row = name.substring(secondDot+1, lastDot);
                                name_last = name.substring(lastDot+1);
                                name_row--;
                                input.attr("name", name_pre + name_row + "_" + name_last);
                                input.attr("id", name_pre + name_row + "_" + name_last);
                            }
                        }
                    }
                }
            } else {
                next_id = next.attr("id");
                secondDot = next_id.indexOf("_",5);
                lastDot = next_id.lastIndexOf("_");
                next_row = next_id.substring(secondDot+1, lastDot);
                count = next_id.substring(lastDot+1);
                now_row = --next_row;
                next_row++;
                next.attr("id", "okr_item_" + now_row + "_" + count);
                next.find("strong").text(now_row);
                inputs = next.find("input");
                for (var k = 0; k < inputs.length; k++) {
                    input = $(inputs[k]);
                    name = input.attr("name");
                    if (!checkForm(name)) {
                        if (name.length === 6) {
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0, lastDot+1);
                            name_row = name.substring(lastDot+1);
                            name_row--;
                            input.attr("name", name_pre + name_row);
                            input.attr("id", name_pre + name_row);
                        }else if (name.length === 7) {
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0, lastDot+1);
                            name_row = name.substring(lastDot+1);
                            name_row--;
                            input.attr("name", name_pre + name_row);
                            input.attr("id", name_pre + name_row);
                        } else if (name.length === 8) {
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0, lastDot);
                            name_row = name.substring(lastDot+1);
                            if (name_pre === 'krperf') {
                                name_row--;
                                input.attr("name", 'krperf_' + name_row);
                                input.attr("id", 'krperf_' + name_row);
                            } else {
                                secondDot = name.indexOf("_",4);
                                lastDot = name.lastIndexOf("_");
                                name_pre = name.substring(0, secondDot+1);
                                name_row = name.substring(secondDot+1, lastDot);
                                name_last = name.substring(lastDot+1);
                                name_row--;
                                input.attr("name", name_pre + name_row + "_" + name_last);
                                input.attr("id", name_pre + name_row + "_" + name_last);
                            }
                        } else if (name.length === 10) {
                            secondDot = name.indexOf("_",4);
                            lastDot = name.lastIndexOf("_");
                            name_pre = name.substring(0, secondDot+1);
                            name_row = name.substring(secondDot+1, lastDot);
                            name_last = name.substring(lastDot+1);
                            name_row--;
                            input.attr("name", name_pre + name_row + "_" + name_last);
                            input.attr("id", name_pre + name_row + "_" + name_last);
                        }
                    }
                }

                var selects = next.find("select");
                for (var m = 0; m < selects.length; m++) {
                    var select = $(selects[m]);
                    name = select.attr("name");
                    if (!checkForm(name)) {
                        lastDot = name.lastIndexOf("_");
                        name_pre = name.substring(0, lastDot+1);
                        name_row = name.substring(lastDot+1);
                        name_row--;
                        $(select).prev().attr("id", name_pre+name_row+"_0");
                        select.attr("name", name_pre + name_row);
                        select.attr("id", name_pre + name_row);
                    }
                }

                var texts = next.find("textarea");
                for (var o = 0; o < texts.length; o++) {
                    var text = $(texts[o]);
                    name = text.attr("name");
                    if (!checkForm(name)) {
                        lastDot = name.lastIndexOf("_");
                        name_pre = name.substring(0, lastDot+1);
                        name_row = name.substring(lastDot+1);
                        name_row--;
                        text.attr("name", name_pre + name_row);
                        text.attr("id", name_pre + name_row);
                    }
                }
            }
        }
    }
}

function addToParam(param,formData) {
    if(param === undefined || param === null){
        param = [];
    }
    for(var o = 0;o < formData.length;o++){
        var single = formData[o];
        if(!checkForm($(single).attr("name"))) {
            var item = {
                name:'',
                value:''
            };
            item.name = $(single).attr("name");
            item.value = $(single).attr("value");
            param.push(item);
        }
    }
    return param;
}

function checkFormData(param) {
    for (var i = 0; i < param.length; i++) {
        if (checkForm(param[i].value)) {
            if ((param[i].name).match(/\b(ndat|type|asid|boss)/) !== null) {
                layer.tips("请选择数据", "#" + param[i].name + "_0");
            } else {
                layer.tips("请录入数据", "#" + param[i].name);
            }
            return false;
        }else if ((param[i].name).match(/(\bprop|\Bprop|\bzgrad|\bmgrad)/) !== null) {
            if((param[i].value).match(/\d+(\.\d+)?$/) === null){
                layer.tips("请输入数字","#"+param[i].name);
                return false;
            }
        }
    }
    return true;
}

function task_choose(present) {
    var id = present.prev().attr("id");
    layer.open({
        type:2,
        content:'./taskChoose?id='+id,
        area:['99%','90%'],
        offset:'10px',
        btn:['关闭']
    },function (layer) {
        layer.close(layer);
    });
}

function rose(present){
    present.css("height","100%").css("width",width).css("background-color","#fff").css("zIndex",0).css("top","-1px").css("position","absolute").css("transition","0.5s");
    present.parent().css("text-align","left");
}
function jinyu(present){
    width = present.css("width");
    height = present.css("height");
    present.css("zIndex",1).css("width","400px").css("background-color","#d3d3d3").css("height","400px").css("position","absolute").css("transition","0.4s");
}