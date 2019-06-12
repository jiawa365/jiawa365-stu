<!DOCTYPE html>
<html lang="en">
<head>

    <!-- Title -->
    <title>Layers | Forms - Form Elements</title>
    <#include "/common/common.ftl"/>
    <link href="assets/plugins/summernote-master/summernote.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-white">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title">上传模块</h4>
                </div>
                <div class="panel-body">
                    <form id="form-upload" class="form-horizontal m-t-xs" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">模块标题:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="title" name="title" placeholder="标题">
                                <p class="help-block">标题不建议过长</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="file" class="col-sm-2 control-label">模块文件:</label>
                                <input type="file" name="file">
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">描述:</label>
                            <div class="col-sm-10">
                                <input type="text" id="desc" name="description">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success m-t-xxs">上传</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>

</div>

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
<script src="assets/plugins/3d-bold-navigation/js/main.js"></script>
<script src="assets/plugins/summernote-master/summernote.min.js"></script>

<script>

    $(function(){
        $('#desc').summernote({height: 400});

        $('#form-upload').submit(function(){
            $('#desc').val($('.note-editable').html());
            return true;
        });
    });

</script>


</body>
</html>