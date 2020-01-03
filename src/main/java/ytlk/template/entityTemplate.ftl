package com.ytlk.${modelName}.entity;


/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
public class ${entityName} extends BaseEntity{
 
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
