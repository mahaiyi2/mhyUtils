package com.ytlk.${modelName}.mapper;



import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import com.ytlk.${modelName}.entity.${entityName};
import com.ytlk.${modelName}.mapper.sp.${SPName};
import com.ytlk.core.mapper.BaseMapper;

/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
@Mapper
public interface ${mapperName} {
	/**
	 * 创建
	 */
	@InsertProvider(type=${SPName}.class,method="create")
	void create(${entityName} obj);
	
	/**
	 * 创建
	 */
	@InsertProvider(type=${SPName}.class,method="createBatch")
	void createBatch(List<${entityName}> list);
	
	/**
	 * 删除
	 */
	//@DeleteProvider(type=${SPName}.class,method="delById")
	//public void delById(String id);
	
}
