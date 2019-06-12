/**
 *  打开窗口
 * @param width 如:'450px'
 * @param height
 * @param content html代码、文本或url地址
 * @param type :可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    *
 *
 */
function openWin(width,height,content,type,callback){
    layer.open({
        type: type,
        area: [width, height],
        fixed: false, //不固定
        maxmin: true,
        content: content,
        yes: callback
    });
}

/**
 * 关闭当前窗口，适用于layer打开的所有窗口
 */
function closeCurrentWin(callback){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    if(callback){
        callback();
    }
}