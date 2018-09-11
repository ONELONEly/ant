<%--
  Created by IntelliJ IDEA.
  User: 180484
  Date: 2018/9/4
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="kellyj" %>
<html>
<head>
    <title>测试界面</title>
    <kellyj:import url="../../static1.html"/>
    <style>
        .task_choose {
            position: absolute;
            right:5px;
            top:10px;
        }
        .task_add{
            position: absolute;
            opacity: 0;
            right:0;
            bottom: 0;
            margin-right: -5px;
        }
        .task_add:hover{
            opacity: 1;
        }
        .task_add{
            position: absolute;
            right:5px;
            top:20px;
        }
        .excel_input{
            outline:0;
            width:99%;
            height:40px;
            font-weight: lighter;
            border-width: 0;
            -webkit-appearance:none;
            transition:all .3s;
            -webkit-transition:all .3s;
            box-sizing:border-box;
        }
        .excel-input:focus{border-color:#C9C9C9!important}
        th.center {
            text-align: center;
            margin: 0 auto;
        }
        td.none_pdding{
            padding: 0;
            text-align: center;
            margin: 0 auto;
        }
        td.none_border{
            border:none;
        }

        th.none_border{
            border:none;
        }

        ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
            color:    #909;
            opacity:  0.3;
            text-align: center;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    #909;
            opacity:  0.3;
            text-align: center;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #909;
            opacity:  0.3;
            text-align: center;
        }
        :-ms-input-placeholder { /* Internet Explorer 10-11 */
            color:    #909;
            opacity:  0.3;
            text-align: center;
        }
        ::-ms-input-placeholder { /* Microsoft Edge */
            color:    #909;
            opacity:  0.3;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="x-body">
    <div class="layui-form layui-form-panel">
        <table class="layui-table">
            <tr>
                <td colspan="10" class="x-center">OKR管理表</td>
                <td class="none_border">

                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <div class="layui-form-item">
                        <label for="asid" class="layui-form-label">管理对象：</label>
                        <div class="layui-input-block">
                            <input type="text" name="asid" id="asid" class="layui-input" style="border:none;">
                            <a href="javascript:" class="task_choose"><i class="layui-icon layui-icon-search"></i></a>
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="boss" class="layui-form-label">直接上级：</label>
                        <div class="layui-input-block">
                            <input type="text" name="boss" id="boss" class="layui-input" style="border:none;">
                            <a href="javascript:" class="task_choose"><i class="layui-icon layui-icon-search"></i></a>
                        </div>
                    </div>
                </td>
                <td colspan="2">
                    <div class="layui-form-item">
                        <label for="mdat" class="layui-form-label">管理周期：</label>
                        <div class="layui-input-block">
                            <input type="text" name="mdat" id="mdat" class="layui-input" style="border:none;">
                        </div>
                    </div>
                </td>
                <td class="none_border">

                </td>
            </tr>
            <tr id="okr_item_0">
                <th class="center" width="4%">
                    <label>序号</label>
                </th>
                <th class="center" width="6%">
                    <label>目标（O）</label>
                </th>
                <th class="center" width="5%">
                    <label>周期</label>
                </th>
                <th class="center" width="5%">
                    <label>类型</label>
                </th>
                <th class="center" width="5%">
                    <label>权重</label>
                </th>
                <th class="center" width="5%">
                    <label>完成情况</label>
                </th>
                <th class="center" width="20%">
                    <label>关键成果</label>
                </th>
                <th class="center" width="5%">
                    <label>KR权重</label>
                </th>
                <th class="center" width="20%">
                    <label>KR完成情况</label>
                </th>
                <th class="center" width="5%">
                    <label>自评分</label>
                </th>
                <th class="none_border" width="5%">
                </th>
            </tr>
            <tr id="okr_item_1_0">
               <td class="none_pdding" rowspan="">
                   <strong>1</strong>
               </td>
                <td class="none_pdding" rowspan="">
                    <input type="text" name="goal" id="goal" placeholder="请输入目标" autocomplete="off" lay-verify="" class="excel_input">
                </td>
                <td class="none_pdding" rowspan="">
                    <select name="ndat" id="ndat" lay-verify="ndat"  lay-search="">
                        <option value="" class="n-display" disabled selected>请选择周期</option>
                        <option value="1" class="n-display">月度</option>
                        <option value="2" class="n-display">季度</option>
                        <option value="3" class="n-display">半年度</option>
                        <option value="4" class="n-display">年度</option>
                    </select>
                </td>
                <td class="none_pdding" rowspan="">
                    <select name="type" lay-filter="type" id="type" lay-search>
                        <option value="" style="display:none;" disabled selected>请选择类型</option>
                        <option value="1" class="n-display">项目类</option>
                        <option value="2" class="n-display">质量类</option>
                        <option value="3" class="n-display">管理类</option>
                        <option value="4" class="n-display">创新类</option>
                    </select>
                </td>
                <td class="none_pdding" rowspan="">
                    <input type="text" name="prop" id="prop" placeholder="请输入权重" autocomplete="off" lay-verify="number|require" class="excel_input">
                </td>
                <td class="none_pdding" rowspan="">
                    <input type="text" name="perf" id="perf" placeholder="请输入完成情况" autocomplete="off" lay-verify="" class="excel_input">
                </td>
                <td class="none_pdding">
                    <input type="text" name="achi" id="achi" placeholder="请输入关键成果" autocomplete="off" lay-verify="" class="excel_input">
                    <a href="javascript:" class="task_choose"><i class="layui-icon layui-icon-search"></i></a>
                </td>
                <td class="none_pdding">
                    <input type="text" name="krprop" id="krprop" placeholder="请输入KR权重" autocomplete="off" lay-verify="number|require" class="excel_input">
                    <a href="javascript:" class="task_add" onclick="task_add()"><i class="layui-icon layui-icon-add-1 layui-bg-green"></i></a>
                </td>
                <td class="none_pdding" rowspan="">
                    <input type="text" name="krperf" id="krperf" placeholder="请输入KR完成情况" autocomplete="off" lay-verify="" class="excel_input">
                </td>
                <td class="none_pdding" rowspan="">
                    <input type="text" name="zgrad" id="zgrad" placeholder="请输入自评分" autocomplete="off" lay-verify="number|require" class="excel_input">
                </td>
                <td class="none_pdding none_border" rowspan="">
                    <div class="layui-form-item" style="margin: 0 auto;">
                        <div class="layui-input-inline">
                            <button class="layui-btn layui-btn-radius layui-btn-xs">增加</button>
                            <button class="layui-btn layui-btn-radius layui-btn-xs layui-btn-danger">删除</button>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<script language="JavaScript">
    layui.use(['form', 'table','jquery','layer'], function () {
        var form = layui.form,$ = layui.jquery;

        $(".task_choose").click(function () {
            layer.open({
                type:1,
                content:'请选择任务',
                area:'300px',
                offset:'10px',
                btn:['预览','选择']
            });
        });

        var item = "\n" +
            "            <tr>\n" +
            "                <td class=\"none_pdding\">\n" +
            "                    <input type=\"text\" name=\"achi\" id=\"achi\" placeholder=\"请输入关键成果\" autocomplete=\"off\" lay-verify=\"\" class=\"excel_input\">\n" +
            "                    <a href=\"javascript:\" class=\"task_choose\"><i class=\"layui-icon layui-icon-search\"></i></a>\n" +
            "                </td>\n" +
            "                <td class=\"none_pdding\">\n" +
            "                    <input type=\"text\" name=\"krprop\" id=\"krprop\" placeholder=\"请输入KR权重\" autocomplete=\"off\" lay-verify=\"number|require\" class=\"excel_input\">\n" +
            "                </td>\n" +
            "            </tr>";
        // $(item).insertAfter("#okr_item_0");
    });

    function task_add() {

    }
</script>
</body>
</html>
