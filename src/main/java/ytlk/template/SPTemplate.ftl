package com.ytlk.${modelName}.mapper.sp;

import java.util.HashMap;
import java.util.Map;
import com.ytlk.core.mapper.sp.SqlProvider;
import com.ytlk.core.util.StringUtil;
import org.apache.ibatis.jdbc.SQL;
import com.ytlk.core.mapper.sp.SqlHelper;



public class ${SPName} extends SqlProvider{
	public static final String TABLE_NAME="${tblName}";
	public static final String TABLE_ALIAS="";
	public static final Map<String,String> TB_FV_MAP;
	static {
		Map<String,String> basic = new HashMap<String,String>();
		basic.put("ID","${r"#{"}id${r"}"}");
		<#list fieldList as field>
		basic.put("${field.column}","${r"#{"}${field.fieldName}${r"}"}");
		</#list>
		basic.put("ORDER_BY","${r"#{"}orderBy${r"}"}");
		TB_FV_MAP = SqlHelper.getFV(basic, null, null,TABLE_ALIAS);
	}
	public ${SPName}(){
		TB_NAME=TABLE_NAME;
		TB_ALIAS=TABLE_ALIAS;
		FIELD_PREFIX = StringUtil.isNotBlank(this.TB_ALIAS)?TB_ALIAS+".":"";
		TB_FIELDS_BASIC = TB_FV_MAP.get(SqlHelper.MAP_KEY_BASIC_FIELDS);
		TB_VALUES_BASIC = TB_FV_MAP.get(SqlHelper.MAP_KEY_BASIC_VALUES);
		TB_FV_UPDATE = TB_FV_MAP.get(SqlHelper.MAP_KEY_UPDATE_FV);
		TB_FIELDS_CREATE = TB_FV_MAP.get(SqlHelper.MAP_KEY_CREATE_FIELDS);
		TB_VALUES_CREATE = TB_FV_MAP.get(SqlHelper.MAP_KEY_CREATE_VALUES);
		TB_FIELDS_ALL = TB_FV_MAP.get(SqlHelper.MAP_KEY_ALL_FIELDS);
	}
	
	protected SQL listCondition(SQL sql,Map<String,Object> params) {
					if(StringUtil.isNotBlankString(params.get("search"))) {
						sql.WHERE("NAME like CONCAT('%',${r"#{"}params.search${r"}"},'%')");
					}
			return sql;
		}
	@Override
	protected String joinString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String extendField() {
		// TODO Auto-generated method stub
		return null;
	}
}
