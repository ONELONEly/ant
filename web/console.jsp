<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>看板</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="./layui/css/layui.css" media="all">
  <link rel="stylesheet" href="./css/admin.css" media="all">

</head>
<body>
  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md8">
        <div class="layui-row layui-col-space15">


          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">已办事项</div>
              <div class="layui-card-body" style="height: 40px;">

            0

              </div>
            </div>
          </div>

          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">待办事项</div>
              <div class="layui-card-body" style="height: 40px;">

                0

              </div>
            </div>
          </div>


          <div class="layui-col-md12">
            <div class="layui-card">
              <div class="layui-card-header">系统监控</div>
              <div class="layui-card-body">
                
                <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-dataview">
                  <div carousel-item id="LAY-index-dataview">
                    <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>


                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">版本信息</div>
          <div class="layui-card-body layui-text">
            <table class="layui-table">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>当前版本</td>
                  <td>
                   V1.0
                  </td>
                </tr>
                <tr>
                  <td>更新时间</td>
                  <td>
                    2018年7月25
                 </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">实时监控</div>
          <div class="layui-card-body layadmin-takerates">
            <div class="layui-progress" lay-showPercent="yes">
              <h3>CPU使用率</h3>
              <div class="layui-progress-bar" lay-percent="58%"></div>
            </div>
            <div class="layui-progress" lay-showPercent="yes">
              <h3>内存占用率</h3>
              <div class="layui-progress-bar layui-bg-red" lay-percent="20%"></div>
            </div>
          </div>
        </div>

        <div class="layui-card">
          <div class="layui-card-header">
            开发团队
            <i class="layui-icon layui-icon-tips" lay-tips="要支持的噢" lay-offset="5"></i>
          </div>
          <div class="layui-card-body layui-text layadmin-text">
            <p>—— 软件应用一室 蚂蚁啃骨头</p>
          </div>
        </div>
      </div>
      
    </div>


  </div>

  <script src="./layui/layui.js?t=1"></script>  
  <script>
  layui.config({
    base: './' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index']);
  </script>
</body>
</html>

