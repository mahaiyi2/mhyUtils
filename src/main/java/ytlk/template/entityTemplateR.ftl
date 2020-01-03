package com.ytlk.${modelName}.entity;
import java.util.Date;

/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
public class ${entityName} {
 	private Long createBy;
	private Date createTime;
	<#list fieldList as field>
	private Long ${field.fieldName};//${field.fieldDes}
	</#list>
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	<#list fieldList as field>
	public Long get${field.fieldNameFU}() {
		return ${field.fieldName};
	}
	public void set${field.fieldNameFU}(Long ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	</#list>
	
}
