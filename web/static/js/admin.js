/**
 * @author Created by
 * @date 2017/8/25.
 */
layui.use(['element',"jquery"], function(){
    var $ = layui.jquery,element = layui.element;
    var li = $('.layui-tab-title li');
    //监听导航点击
    element.on('nav(side)', function(elem){
        var title = elem.find('cite').text(),url = elem.find('a').attr('_href');
        if(title !== undefined && url !== undefined) {
            var iframe = $('.x-iframe');
            for (var i = 0; i < iframe.length; i++) {
                if (iframe.eq(i).attr('src') === url) {
                    element.tabDelete("bodyTab", title);
                }
            }
            element.tabAdd('bodyTab', {
                title: title//用于演示
                , content: '<iframe frameborder="0" src="' + url + '" class="x-iframe"></iframe>'
                , id: title
            });

            element.tabChange('bodyTab', title);
            li.eq(0).find('i').remove();
        }
    });
});