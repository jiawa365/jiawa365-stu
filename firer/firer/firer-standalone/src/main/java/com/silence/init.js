load("nashorn:mozilla_compat.js");
importPackage(java.lang);
importPackage(org.openqa.selenium);
importPackage(org.openqa.selenium.chrome);
importPackage(com.silence);
var wb=Chromes.open();

var $=function(expression){
    var targetElement=null;
    this.byId=function(targetWb,expression){
        var expression=expression.substr(1,expression.length);
        return targetWb.findElement(By.id(expression));
    }

    this.byClass=function(targetWb,expression){
        var expression=expression.substr(1,expression.length);
        return targetWb.findElements(By.className(expression));
    }

    this.byTagName=function(targetWb,expression){
        return targetWb.findElements(By.tagName(expression));
    }

    /**
     * 根据表达式获取对应的节点对象
     * @param targetElement 父节点或webDriver对象
     * @param expression 表达式
     */
    this.getTag=function (targetWb,expression){
        if(expression.startsWith('#')){
            return this.byId(targetWb,expression);
        }else if(expression.startsWith(".")){
            return this.byClass(targetWb,expression);
        }else{
            return this.byTagName(targetWb,expression);
        }
    }

    if(expression){
        if(typeof expression=="string"){
            if(expression.startsWith('#')){
                targetElement=this.getTag(wb,expression);
            }else {
                return this.getTag(wb,expression);
            }
        }else{
            targetElement=expression;
        }
    }

    /**
     * 适用与获取单个子对象
     * @param express
     * @returns {jQuery|HTMLElement}
     */
    this.child=function(express){
        var tmp=this.getTag(this.targetElement,express);
        if(tmp.length){
            //如果多个节点则取第一个
            return $(tmp[0]);
        }
        return $(tmp);
        //return $(this.getTag(this.targetElement,express));
    }

    /**
     * 获取子对象，返回对象数组
     * @param express
     */
    this.children=function(express){return this.getTag(this.targetElement,express);}
    /**
     * 触发点击事件
     */
    this.click=function(){targetElement.click();}
    /**
     * 拖动目标节点
     * @param x 
     * @param y
     */
    this.drag=function(x,y){Chromes.dragAndDrop(wb,targetElement,x,y);}

    /**
     * 赋值函数
     * @param value 值
     */
    this.val=function(value){targetElement.sendKeys(value);}
    /**
     * 上传文件
     * @param filePath
     */
    this.upload=function(filePath){Chromes.upload(this.targetElement,filePath);}
    this.targetElement=targetElement;
    return this;
}

/**
 * 执行js代码
 * @param jsScript js脚本
 */
$.doJs=function(jsScript){Chromes.execScript(wb,jsScript);}

/**
 * 发送get请求
 * @param url
 */
$.doGet=function(url){return HttpClientUtil.doGet(url);}

/**
 * 发送post请求
 * @param url
 * @param data
 */
$.doPost=function(url,data){return HttpClientUtil.doPost(url,data);}
/**
 * 等价于Thread.sleep()
 * @param time
 */
$.sleep=function(time){Thread.sleep(time);}
/**
 * 跳转
 * @param url
 */
$.go=function(url){wb.get(url);}
var location=function(){

}

/**所有窗口
 * 获取
 * @param webDriver
 */
$.getWindows=function(webDriver){
    if(webDriver){
        return webDriver.getWindowHandles().toArray();
    }else{
        return wb.getWindowHandles().toArray();
    }
}

/**
 * 跳转到指定窗口
 * @param winHandle 窗口字符串
 * @param webDriver
 * @returns {*}
 */
$.goWin=function(winHandle,webDriver){
    print(winHandle);
    if(webDriver){
        return webDriver.switchTo().window(winHandle);
    }else{
        return wb.switchTo().window(winHandle);
    }
}

/**
 * 获取iframe对象
 * @param i
 * @returns {*}
 */
$.goFrame=function(i){
    return wb.switchTo().frame(i);
}

/**
 * QQ登录
 *    &nbsp;&nbsp;&nbsp;&nbsp;要求调用该方法前已进入qq登录界面
 * @param u 帐户名
 * @param p 密码
 */
$.loginQQ=function(u,p){
    var loginQQ_tmp1=wb;//临时变量
    wb=$.goFrame(0);//qq登录开始
    $('#switcher_plogin').click();
    $('#u').val(u);
    $('#p').val(p);
    $('#login_button').click();
    $.sleep(5000);//qq登录结束
    wb=loginQQ_tmp1;
}



//监听属性变化
Object.defineProperty(location, 'href', {
      set:function(value){
            wb.get(value);
      }
 });

