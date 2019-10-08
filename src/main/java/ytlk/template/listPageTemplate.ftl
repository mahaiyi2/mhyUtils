<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
	  <!-- Font Awesome -->
	  <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
	    <!--layer-->
	  <link rel="stylesheet" href="../bower_components/layer3/theme/default/layer.css">
	  <!-- Ionicons -->
	  <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
	  <!-- Theme style -->
	  <link rel="stylesheet" href="../dist/css/AdminLTE_iframe.min.css">
	  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
	        page. However, you can choose any other skin. Make sure you
	        apply the skin class to the body tag so the changes take effect. -->
	  <link rel="stylesheet" href="../dist/css/skins/skin-blue.min.css">
	  <!--bootstrap-table-->
	  <link rel="stylesheet" href="../bower_components/bootstrap-table/bootstrap-table.min.css">
	
</head >
<body >
	<div class="content-header">
		<h1>${theDes}</h1>
	</div>
	<div class="content">
    	<!--bootstrap-table搜索用参数form-->
    	<form action="" method="get" id="bstbl_searchForm" class="bstbl_searchForm" style="display: none;" accept-charset="utf-8">
    		<!--<input type="hidden" id="parentId" name="parentId" value="0">-->
    	</form>
    	<div class="box box-primary">
    		<table id="tbl_${theName}"></table>
    		<div id="div_opts"></div>
    	</div>
	</div>
	

    <!-- jQuery 3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- layer -->
<script src="../bower_components/layer3/layer.js"></script>
<!--bootstrap-table-->
<script src="../bower_components/bootstrap-table/bootstrap-table.js"></script>
<script src="../bower_components/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>

<!-- AdminLTE App -->
<script src="../dist/js/ytlk.js"></script>
<script src="../dist/js/pages/${listPageName}.js"></script>
</body>
</html>