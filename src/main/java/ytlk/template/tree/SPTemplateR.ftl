package com.ytlk.${modelName}.mapper.sp;

import java.text.MessageFormat;
import java.util.Map;
import com.ytlk.back.entity.${entityName};
import java.util.List;


public class ${SPName} {
	public static final String TABLE_NAME="${tblName}";
	
		public String createBatch(Map<String,List<${entityName}>> map) {
		List<${entityName}> list = map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ")
		.append(TABLE_NAME)
		.append(" ( <#list fieldList as field>${field.column},</#list>CREATE_BY,CREATE_TIME ) VALUES ");
		 MessageFormat messageFormat = new MessageFormat("(" +
		            <#list fieldList as field>
		            "${r"#"}'${r"{"}'list[{0}].${field.fieldName} ${r"}"}," +
		            </#list>
		            "${r"#"}'${r"{"}'list[{0}].createBy ${r"}"}," +
		            "${r"#"}'${r"{"}'list[{0}].createTime ${r"}"}" +
		            ")");
		 int size = list.size();
		 for (int i = 0; i < size; i++)
		    {
		        sb.append(messageFormat.format(new Object[]{i+""}));   
		        if (i < size - 1) sb.append(",");
		    }
		return sb.toString();
	}
	
	public String create(Object obj) {
		
		return "insert into "+TABLE_NAME+"("+
		<#list fieldList as field>
		"${field.column},"+
		</#list>
		"CREATE_BY,CREATE_TIME"+
		" ) values ( " +
		<#list fieldList as field>
		"${r"#{"}${field.fieldName}${r"}"}," + 
		</#list>
		"${r"#{"}createBy${r"}"},"+
		"${r"#{"}createTime$${r"}"}"+
		")";
	}
}
