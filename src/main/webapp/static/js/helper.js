/**
 Created by 180365 on 2017/9/19.
 */
function checkString(content) {
    if(content === undefined){
        return "";
    }
    return content;
}

function checkForm(content) {
    var status = true;
    if(content !== undefined && content !== null && content.length > 0){
        status = false;
    }
    return status;
}

function replaceAll(str,f,e) {
    var reg = new RegExp(f, "g")
    return str.replace(reg, e)
}

var xhrOnProgress = function (fun) {
    xhrOnProgress.onprogress = fun;
    return function () {
        var xhr = $.ajaxSettings.xhr();
        if(typeof xhrOnProgress.onprogress !== 'function')
            return xhr
        if(xhrOnProgress.onprogress && xhr.upload) {
            xhr.upload.onprogress = xhrOnProgress.onprogress
        }
        return xhr
    }
}