/**
 * @author Created by
 * @date 2017/8/25.
 */
layui.use(['element',"jquery"], function(){
    var $ = layui.jquery,element = layui.element;
    var li = $('.layui-tab-title li');
    li.eq(0).find('i').remove();
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

/*    //导航的hover效果、二级菜单等功能，需要依赖element模块
    // 侧边栏点击隐藏兄弟元素
    $('.layui-nav-item').click(function(event) {
        $(this).siblings().removeClass('layui-nav-itemed');
    });

    height = $('.layui-layout-admin .site-demo').height();
    $('.layui-layout-admin .site-demo').height(height-100);

    if($(window).width()<750){
        trun = 0;
        $('.x-slide_left').css('background-position','0px -61px');
    }else{
        trun = 1;
    }
    $('.x-slide_left').click(function(event) {
        if(trun){
            $('.x-side').animate({left: '-200px'},200).siblings('.x-main').animate({left: '0px'},200);
            $(this).css('background-position','0px -61px');
            trun=0;
        }else{
            $('.x-side').animate({left: '0px'},200).siblings('.x-main').animate({left: '200px'},200);
            $(this).css('background-position','0px 0px');
            trun=1;
        }
    });*/



    // var iframe = $('.x-iframe');
    // var li = $('.layui-tab-title li');
    // for (var i = 0; i <iframe.length; i++) {
    //     if(iframe.eq(i).attr('src')===url){
    //         element.tabChange('bodyTab', i);
    //         // element.tabDelete("bodyTab",i);
    //         return;
    //     }
    // }
    // element.tabAdd('bodyTab', {
    //     title: title//用于演示
    //     ,content: '<iframe frameborder="0" src="'+url+'" class="x-iframe"></iframe>'
    //     ,id: li.length
    // });
    //
    // element.tabChange('bodyTab', li.length);
    // li.eq(0).find('i').remove();




});