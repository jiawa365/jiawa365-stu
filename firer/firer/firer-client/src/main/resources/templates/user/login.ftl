<!DOCTYPE html>
<html lang="en">
<head>

    <!-- Title -->
    <title>Firer | 登录 - 注册</title>

    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta charset="UTF-8">
    <meta name="description" content="Admin Dashboard Template" />
    <meta name="keywords" content="admin,dashboard" />
    <meta name="author" content="Lalassu" />

    <#include "/common/common.ftl"/>

</head>
<body class="page-register">
<main class="page-content">
    <div class="page-inner">
        <div id="main-wrapper">
            <div class="row">
                <div class="col-md-3 center">
                    <div class="panel panel-white" id="js-alerts">
                        <div class="panel-body">
                            <div class="register-box">
                                <a href="index.html" class="logo-name text-lg text-center m-t-xs">Firer</a>
                                <p class="text-center m-t-md">登录账号</p>
                                <font color="red">${errMsg!}</font>
                                <form class="m-t-md" action="${ctx}/user/login" method="post">
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="邮箱/账户名" name="name" required>
                                    </div>

                                    <div class="form-group">
                                        <input type="password" name="userPass" class="password form-control" placeholder="密码" required>
                                    </div>
                                    <#--<label>
                                        <input type="checkbox"> Agree the terms and policy
                                    </label>-->

                                    <button type="submit" class="btn btn-success btn-block m-t-xs">登录</button>
                                    <p class="text-center m-t-xs text-sm">没有账号?</p>
                                    <a href="${ctx}/user/register/email" class="btn btn-default btn-block m-t-xs">注册</a>
                                </form>
                                <p class="text-center m-t-xs text-sm">2018 &copy; laotan</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- Row -->
        </div><!-- Main Wrapper -->
    </div><!-- Page Inner -->
</main><!-- Page Content -->


<!-- Javascripts -->
<script src="assets/plugins/jquery/jquery-3.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="assets/plugins/pace-master/pace.min.js"></script>
<script src="assets/plugins/jquery-blockui/jquery.blockui.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/plugins/switchery/switchery.min.js"></script>
<script src="assets/plugins/uniform/js/jquery.uniform.standalone.js"></script>
<script src="assets/plugins/offcanvasmenueffects/js/classie.js"></script>
<script src="assets/plugins/waves/waves.min.js"></script>
<script src="assets/js/layers.min.js"></script>

</body>
</html>