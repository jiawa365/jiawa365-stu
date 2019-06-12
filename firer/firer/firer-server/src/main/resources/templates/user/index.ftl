<!DOCTYPE html>
<html lang="en">
<head>
    <title>Layers | Responsive Admin Dashboard Template</title>
    <#include "/common/common.ftl"/>

    <link href="${staticUrl}/assets/plugins/tab/css/style.css" rel="stylesheet" />
    <link href="${staticUrl}/assets/plugins/tab/css/tabstyle.css" rel="stylesheet" />

</head>

<body class="compact-menu">
    <#include "/common/msg.ftl"/>
    <main class="page-content content-wrap">
        <#include "/common/header.ftl"/>
        <#include "/common/menu.ftl"/>
            <!-- 内容区开始 -->
            <div class="page-inner">

                <div class="main-wrapper">

                    <!--菜单HTML Start-->
                    <div id="page-tab">
                        <button class="tab-btn" id="page-prev"></button>
                        <nav id="page-tab-content">
                            <div id="menu-list"></div>
                        </nav>
                        <button class="tab-btn" id="page-next"></button>
                        <div id="page-operation">
                            <div id="menu-all">
                                <ul id="menu-all-ul">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--页面内容开始-->
                    <!--iframe Start (根据页面顶部占用高度，自行调整高度数值)-->
                    <div id="page-content" style="height: calc(100% - 155px);"></div>
                    <!--页面内容结束-->

                </div>

            </div>
            <!-- 内容区结束 -->
    </main>


    <#include "/common/footer.ftl"/>
    <script src="${staticUrl}/assets/plugins/tab/tab.js"></script>
    <script>
        $(".menu").tab();
        $('#page-content').css("height",window.screen.availHeight-200+'px');
    </script>

</body>
</html>