/**
 * 
 */

var adaptors =
{
    utils:{
      type : 'com.gree.ant.util.MyUtil',
      fields: {
          // 将 ServletContext 对象注入 MyUtils
          sc : {app:'$servlet'}
      }
    },
    tmpFilePool : {
        type : 'org.nutz.filepool.NutFilePool',
        // 临时文件最大个数为 1000 个,调用 MyUtils.getPath 函数
        args : [ {java:'$utils.getPath("/WEB-INF/tmp")'}, 1000 ]
},
    uploadFileContext : {
        type : 'org.nutz.mvc.upload.UploadingContext',
        singleton : false,
        args : [ { refer : 'tmpFilePool' } ],
        fields : {
        // 是否忽略空文件, 默认为 false
        ignoreNull : true,
        // 单个文件最大尺寸(大约的值，单位为字节，即 1048576 为 1M)
        maxFileSize : 2147483648,
        // 正则表达式匹配可以支持的文件名
        nameFilter : '^(.+[.])(gif|jpg|png|txt|rp|pdf|doc|xls|rar|zip|7z|docx|xlsx)$'
}
},
    myUpload : { //文件上传 ，交给容器传利
        type : 'org.nutz.mvc.upload.UploadAdaptor',
        singleton : false,
        args : [ { refer : 'uploadFileContext' } ]
}
};