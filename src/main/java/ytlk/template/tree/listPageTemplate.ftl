<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
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
	  <link rel="stylesheet" href="../../dist/css/skins/skin-blue.min.css">
	   <!--zTree-->
	  <link rel="stylesheet" href="../../bower_components/ztree/css/metroStyle/metroStyle.css">
	  <!--bootstrap-table-->
	  <link rel="stylesheet" href="../../bower_components/bootstrap-table/bootstrap-table.min.css">
	
</head >
<body >
	<div class="content-header">
		<h1>${theDes}</h1>
	</div>
	<div class="content">
    	<div class="row">
			<!--左侧树-->
	        <div class="col-md-2">
	        	<div>
	        		<ul id="ul_ztree" class="ztree"></ul>
	        	</div>
	        </div>
	        <!--右侧内容-->
	        <div class="col-md-10">
	        	<!--bootstrap-table搜索用参数form-->
		    	  <div id="toolbar" >
		    		<form action="" method="get" id="bstbl_searchForm" class="bstbl_searchForm form-inline"  accept-charset="utf-8">
						<div class="form-group " style="margin-left: 20px;text-align: right">
					     <label ></label>
					     <div class="input-group ">
					      <input type="text" class="form-control input-sm" name="search" id="searchText" placeholder="请输入名称">
					     </div>
					     </div>
					     <button type="button" class="btn btn-primary queryButton">查询</button>
		    		</form>
		    	</div>
	        	<div class="box box-primary">
	        		<table id="tbl_${theName}"></table>
	        		<div id="div_opts"></div>
	        	</div>
	        </div>
    	</div>
    	
	</div>
	

    <!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- layer -->
<script src="../../bower_components/layer3/layer.js"></script>
<!--ztree-->
<script src="../../bower_components/ztree/js/jquery.ztree.all.min.js"></script>
<!--bootstrap-table-->
<script src="../../bower_components/bootstrap-table/bootstrap-table.js"></script>
<script src="../../bower_components/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>

<!-- AdminLTE App -->
<script src="../../dist/js/ytlk.js"></script>
<script src="./js/${listPageName}.js"></script>
</body>
</html>