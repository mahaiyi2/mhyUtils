<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | General Form Elements</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
      <!--layer-->
    <link rel="stylesheet" href="../../bower_components/layer3/theme/default/layer.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE_iframe.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="../dist/css/skins/skin-blue.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box box-primary">
    <!-- form start -->
    <form role="form"  id="form_${theName}_edit">
      <input type="hidden" name="id" id="id">
      <div class="box-body">
        <#list fieldList as field>
		<div class="form-group">
          <label >${field.fieldDes}</label>
          <input type="text" class="form-control" name="${field.fieldName}"  placeholder="${field.fieldDes}">
        </div>
			</#list>
      </div>

      <div class="box-footer">
        <div id="div_opts"></div>
      </div>
    </form>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- layer -->
<script src="../../bower_components/layer3/layer.js"></script>
<script src="../../bower_components/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="../../dist/js/ytlk.js"></script>
<script src="./js/${editPageName}.js"></script>
</body>
</html>
