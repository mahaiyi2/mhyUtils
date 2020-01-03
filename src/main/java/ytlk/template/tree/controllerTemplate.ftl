package com.ytlk.${modelName}.controller;


import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytlk.back.entity.${entityName};
import com.ytlk.back.service.${serviceName};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
@Api(value = "${theDes}")
@RestController
@RequestMapping(value="${entityNameFl}")
public class ${controllerName} extends BaseController{
	@Autowired
	private ${serviceName} service;
	/**
	 * 创建
	 * @author mhy
	 * @date:   ${nowDate?string("yyyy-MM-dd")}
	 * @return
	 */
	@ApiOperation(value = "创建")
    @PostMapping("/create")
    @RequiresPermissions("${entityNameFl}:insert")
	public Object create(@ApiParam(required = true, value = "基本信息") @RequestBody ${entityName} obj) {
		service.create(obj);
		return responseSuccess(obj);
	}
	@ApiOperation(value = "更新")
	@PostMapping("/edit")
	@RequiresPermissions("${entityNameFl}:update")
	public Object edit(@ApiParam(required = true, value = "基本信息") @RequestBody ${entityName} obj) {
		service.update(obj);
		return responseSuccess(obj);
	}
	@ApiOperation(value = "获取列表")
	@PostMapping("/queryList")
	@RequiresPermissions("${entityNameFl}:read")
	public Object queryList(@ApiParam(required = false, value = "基本信息") @RequestBody Map<String, Object> params ) {
		if(params.get("offset")==null) {//有分页参数
			List<${entityName}> theList = service.queryList(params);
			return responseSuccess(theList);
		}else {
			service.queryByPage(params);
			return responseSuccess(service.queryByPage(params));
		}
		
	}
	/**
	 * 
	 * @author 获取详情
	 * @date:   ${nowDate?string("yyyy-MM-dd")}
	 * @param parentId
	 * @return
	 */
	@ApiOperation(value = "获取详情")
    @PostMapping("/getById")
    @RequiresPermissions("${entityNameFl}:read")
	public Object getById(@ApiParam(name="id", required = true, value = "id") @RequestParam(value = "id",required = true)Long id){
		${entityName} obj = service.queryById(id);
		return responseSuccess(obj);
		
	}
	/**
	 * 
	 * @author 删除
	 * @date:   2019年2月21日 上午10:29:17
	 * @param parentId
	 * @return
	 */
	@ApiOperation(value = "删除")
	@PostMapping("/delById")
	@RequiresPermissions("${entityNameFl}:delete")
	public Object delById(@ApiParam(name="id", required = true, value = "id") @RequestParam(value = "id",required = true)Long id){
		service.delById(id);
		return responseSuccess();
		
	}
}
