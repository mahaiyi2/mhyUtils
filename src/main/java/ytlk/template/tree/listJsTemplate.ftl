 /*
 *列表
  */
 $(function () {
//获取按钮就权限
    var btnPerms = YTLK.getMenuBtnPerms();
    var url_queryList = '/${entityNameFl}/queryList';
	var page_table=$("#tbl_${theName}");
    var ${theName}_root_id = "0";
    var setting = {
    async:{
          enable: true,
          url:url_queryList,
          // autoParam:["id=parentId"],
          contentType:'application/json;charset=UTF-8',
          otherParam: ztreeParam,
          dataFilter: ztreeFilter
        },
    view:{
      nameIsHTML: true,
      showTitle: false,
      expandSpeed: "fast"
    },
    callback: {
      onClick: zTreeOnClick,
      // onExpand:onExpand
    },
    data: {
      simpleData: {
        enable: true
      }
    }
  };
  //ztree请求传参数
  function ztreeParam(treeObj,currentNode){
    var param={}
    param.parentId= currentNode?currentNode.id:"0";
    return param;
  }
  //ztree异步数据处理
  function ztreeFilter(treeId, parentNode, res){
    if(res && res.ytlk_data){
      var nodesData = res.ytlk_data;
      var newNodes = [];
      $.each(nodesData, function (index,newNode){
        newNodes.push({id:newNode.id,pId:newNode.pId||null,name:newNode.name,isParent:true});
      });  
      return newNodes;   
    }else{
      return null;
    }
    
           
  }
//ztree
  var ztreeObj = $.fn.zTree.init($("#ul_ztree"), setting, null);
  
  //点击事件
  function zTreeOnClick(event,treeId,treeNode,clickFlag){
    $("#parentId").val(treeNode.id);
    page_table.bootstrapTable('refresh');
  }

//bootStrapTable 初始化
var bstblPageParams={
  url_list:url_queryList,
  url_del:'/${entityNameFl}/delById',
  url_edit:'./${editPageName}.html',
  btnPerms:btnPerms,
  afterEdit:function(value,row,index){
    //刷新列表
    page_table.bootstrapTable('refresh');
    //刷新并展开父节点
    var pNode=ztreeObj.getNodeByParam("id", row.parentId);
    ztreeObj.reAsyncChildNodes(pNode||null,"refresh",true);
  },
  afterDel:function(value,row,index){//删除后
    //刷新列表
    // page_table.bootstrapTable('refresh'); 
    page_table.bootstrapTable('refresh',{"ytlk_opt":"del"}); 
    //刷新并展开父节点
    var pNode=ztreeObj.getNodeByParam("id", row.parentId);
    ztreeObj.reAsyncChildNodes(pNode||null,"refresh",true);
  }
};
//初始化表格
var bstbl = new YTLK.bsTable(page_table,bstblPageParams);
bstbl.init({
  toolbar:'#toolbar',
  search:false,
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
      },{parentId:$("#parentId").val()},function(){
        //刷新列表
        page_table.bootstrapTable('refresh',{"ytlk_opt":"add"});
         //刷新并展开父节点
        var pNode=ztreeObj.getNodeByParam("id", $("#parentId").val());
        ztreeObj.reAsyncChildNodes(pNode||null,"refresh",true);
      });
    });
 }
 
   //点击查询按钮
 $(document).on('click', ".queryButton",function(){
     page_table.bootstrapTable('refresh');
  });

  });


