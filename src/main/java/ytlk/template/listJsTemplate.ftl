 /*
 *列表
  */
 $(function () {
  //获取按钮就权限
    var btnPerms = YTLK.getMenuBtnPerms();
    var url_queryList = '/${entityNameFl}/queryList';
	var page_table=$("#tbl_${theName}");
//bootStrapTable 初始化
var bstblPageParams={
  url_list:url_queryList,
  url_del:'/${entityNameFl}/delById',
  url_edit:'./${editPageName}.html',
  btnPerms:btnPerms,
  afterEdit:function(value,row,index){
    //刷新列表
    page_table.bootstrapTable('refresh');
  },
  afterDel:function(value,row,index){//删除后
    //刷新列表
    // page_table.bootstrapTable('refresh'); 
    page_table.bootstrapTable('refresh',{"ytlk_opt":"del"}); 
    //刷新并展开父节点
  }
};
var bstbl = new YTLK.bsTable(page_table,bstblPageParams);
bstbl.init({
  columns: [{
    field: 'id',
    title: 'ID'
  },
	<#list fieldList as field>
	{
    field: '${field.fieldName}',
    title: '${field.fieldDes}',
    align: 'left', // 左对齐
    valign: 'middle' // 上下居中    
  },
	</#list>
   {
    field: 'operate',
    title: '操作',
    formatter:bstbl.formatter,
    events:bstbl.events
  }
  ],
  sidePagination: 'server',
  pagination:true


});//表格初始化 END
 
 if(btnPerms && btnPerms.insert){//添加权限
	 //添加按钮 
	 $("#div_opts").append('<button id="btn_add" class="btn btn-primary">添加</button>');
	 //点击添加
	$("#btn_add").click(function(){
	  YTLK.myLayer.layerFrame({
	    content:"./${editPageName}.html"
	  },{},function(){
	    //刷新列表
	    page_table.bootstrapTable('refresh',{"ytlk_opt":"add"});
	  });
 
});

  }
  

  });
