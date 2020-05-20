$(function(){
	//获取按钮就权限
    var btnPerms = YTLK.getMenuBtnPerms();
    //获取页面参数
	var param = YTLK.myLayer.paramFromParent();
	var isUpdate = param && param.id;
    //添加按钮权限
	if(btnPerms && (btnPerms.insert||btnPerms.update)){//按钮 权限
	      if(isUpdate && btnPerms.update){//编辑
	      	$("#div_opts").append('<button type="submit" class="btn btn-primary" disabled>保存</button>');
	      }else if(!isUpdate && btnPerms.insert){//新增
	      	$("#div_opts").append('<button type="submit" class="btn btn-primary" disabled>保存</button>');
	      }
	}
	//添加关闭按钮
	$("#div_opts").append('<button type="button" id="btn_cancle"  class="btn btn-default" style="margin-left: 30px;">关闭</button>');
	$("#btn_cancle").click(function(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); 
	});
	//关闭按钮就结束
	var form=$("#form_${theName}_edit");
	if(isUpdate){
		form.attr("action",YTLK.sysUrl("/${entityNameFl}/edit"));
		YTLK.ajax({//获取表单信息
			url:YTLK.sysUrl("/${entityNameFl}/getById"),
			data:param
		},function(res){
			YTLK.fillForm(form,res.ytlk_data);
		});
	}else{
		form.attr("action",YTLK.sysUrl("/${entityNameFl}/create"));
		YTLK.fillForm(form,param);
	}
	
	//绑定事件提交事件与校验规则
 	YTLK.formEvents(form,function (res){
 		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); 
 	},{
 		//rules:{
 		// 	name: {
			// 		required: true,
			// 		maxlength: 10,
			// 	},
			// url:{
			// 	required: true,
			// }	
 		// },
 		// messages:{
 		// 	name:{
 		// 		required:"请输入用户名",
 		// 		maxlength:"长度不可超过10个字"
 		// 	},
 		// 	url:"必填项"
 		// }
 		
 	});

});