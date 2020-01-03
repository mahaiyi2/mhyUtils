package com.ytlk.${modelName}.mapper;



import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import com.ytlk.back.entity.${entityName};
import com.ytlk.back.mapper.sp.${SPName};
import com.ytlk.back.support.DbQuery;
/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
@Mapper
public interface ${mapperName} extends BaseMapper<${entityName}>{
	@Results(id = "${entityNameFl}ResultMap",value = {
            @Result(property="id",column="ID"),
            @Result(property="parentId",column="PARENT_ID"),
            <#list fieldList as field>
			@Result(property="${field.fieldName}",column="${field.column}"),
			</#list>
            @Result(property="createBy",column="CREATE_BY"),
            @Result(property="createTime",column="CREATE_TIME"),
            @Result(property="updateBy",column="UPDATE_BY"),
            @Result(property="orderBy",column="ORDER_BY"),
            @Result(property="updateTime",column="UPDATE_TIME")
            
			})
	@SelectProvider(type=${SPName}.class,method="queryById")
	public ${entityName} queryById(Long id);
	/**
	 * 创建
	 */
	@InsertProvider(type=${SPName}.class,method="create")
	void create(${entityName} obj);
	/**
	 * 查询列表
	 */
	@ResultMap("${entityNameFl}ResultMap")
	@SelectProvider(type=${SPName}.class,method="queryList")
	public List<${entityName}> queryList(DbQuery query);
	/**
	 * 查询总数
	 */
	@SelectProvider(type=${SPName}.class,method="queryCount")
	public int queryCount(DbQuery query);
	
	/**
	 * 删除
	 */
	@DeleteProvider(type=${SPName}.class,method="delById")
	public void delById(String id);
	/**
	 * 更新
	 */
	@UpdateProvider(type=${SPName}.class,method="update")
	public void update(${entityName} obj);
	
	
}
