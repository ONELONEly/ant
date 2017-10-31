/**
 * @author Created by
 * @date 2017/8/26.
 */

function popupUI(title,url,width,height){
    layer.open({
        type:2,
        area:[width+'px',height+'px'],
        title:title,
        content:url,
        fix:false,
        shadeClose: true,
        shade:0.4,
        maxmin: true,
        skin:'layui-layer-lan' /*内置的skin有：layui-layer-lanlayui-layer-molv*/
        // btnAlign:'c'   /*弹出层的按钮居中 'c',左'l',右'r'*/
    });
}