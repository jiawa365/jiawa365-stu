function openWin(width,height,content){
    layer.open({
        type: 2,
        area: ['800px', '450px'],
        fixed: false, //不固定
        maxmin: true,
        content: content
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