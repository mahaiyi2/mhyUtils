<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8" />
	<title></title>
	<meta name="description" content="overview & stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="static/pkh/style/bootstrap.min.css" />
	<link rel="stylesheet" href="static/css/box.css" />
	<link rel="stylesheet" href="static/pkh/style/jquery.mloading.css" />
	<script type="text/javascript" src="static/pkh/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	
	<!--引入弹窗组件start-->
	<script type="text/javascript" src="plugins/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="plugins/attention/zDialog/zDialog.js"></script>
	<!--引入弹窗组件end-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script src="static/js/jquery.SuperSlide.js"></script>
	<script src="static/pkh/js/jquery.mloading.js"></script>
	<script src="static/pkh/js/bftab.js"></script>
	<script src="static/pkh/js/fpbPkhxq.js"></script>
	
	<script type="text/javascript">
		var huNum = "";
		$(function() {
			 pkh_jb();
			$(".tabox").slide({trigger:"click"});
			$(top.hangge());
		});

	</script>
</head>
<body>
	<input type="hidden" name="huNum" id="huNum" value="">
	<input type="hidden" name="scshtjIsShow" id="scshtjIsShow" value="0">
	<input type="hidden" name="srxxIsShow" id="srxxIsShow" value="0">
	<input type="hidden" name="ydfpbqIsShow" id="ydfpbqIsShow" value="0">
	<div class="tabox">
      <div class="hd">
        <ul>
          <li class="on" >详细信息</li>
	      <!-- <li class=" " onclick="fpbPkhxq.knh_scshtjShow()">二、生产生活条件</li>
	       <li class=" " onclick="fpbPkhxq.knh_srwsxxShow()">三、收入、卫生信息、上年度收入信</li>
	       <li class=" " onclick="fpbPkhxq.knh_ydfpbqxxShow()">四、易地扶贫搬迁信息</li>
	       <li class="" onclick="BfTab.bfTabShow()">五、帮扶信息</li> -->
        </ul>
      </div>
     	<div class="bd">
	      <div class="lh" style="display: none;" >
	      		<table border="0" width="100%" style=" border-collapse:collapse;" >
	      			<thead>
	      				<tr class="box_th">
	      					<th>家庭信息</th>
	      					<th></th>
	      					<th></th>
	      					<th></th>
	      					<th></th>
	      					<th></th>
	      					<th></th>
	      					<th></th>
	      				</tr>
	      			</thead>
	      			
	      			<tbody>
	      				<tr class="box_tr">
						<#assign  keys=mapp?keys/>
						<#assign mi=4-(keys?size)%4>
						<#list keys as key>
						<#assign i=key_index>
						<#if (i%4==0 && i!=0 &&i!=mi)>
						</tr>
						<tr class="box_tr">
						 <td>${key}:<label>${r"${var."}${mapp["${key}"]}${r"}"}</label></td>
						<#else>
						 <td>${key}:<label>${r"${var."}${mapp["${key}"]}${r"}"}</label></td>
						</#if>
						</#list>
						<#list 0..mi-1 as v>
						 <td></td>
						</#list>	
						</tr>
	      			</tbody>
	      		</table>	      		
	      </div>
	    </div>
    </div>

	
<script type="text/javascript">
/**
 * 人员切换用
 */
function pkh_jb_tr(that){
	$(that).find(":radio").attr("checked",true);
	pkh_jb();
}
function pkh_jb(){
	  var show_index;
	  $("[name='radio_jb_cy']").each(function(index,element){
		  if($(this).attr("checked")){
			  show_index=index;
		  }
	  });
	  
	  $("[name='table_jb_cy']").each(function(index,element){
		  if(index==show_index){
			  
			  $(this).css('display','block'); 
		  }else{
			  $(this).css('display','none'); 
		  }
		  });
  }
</script>
	
</body>
</html>