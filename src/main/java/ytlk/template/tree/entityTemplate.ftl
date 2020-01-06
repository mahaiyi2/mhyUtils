package com.ytlk.${modelName}.entity;
import com.ytlk.core.entity.BaseEntity;

/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
public class ${entityName} extends BaseEntity{
 
 
 	private Long parentId;
 	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	<#list fieldList as field>
	private String ${field.fieldName};//${field.fieldDes}
	</#list>
	
	<#list fieldList as field>
	public String get${field.fieldNameFU}() {
		return ${field.fieldName};
	}
	public void set${field.fieldNameFU}(String ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	</#list>
}
