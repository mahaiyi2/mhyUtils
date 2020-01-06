package com.ytlk.${modelName}.serviceImpl;
import org.springframework.stereotype.Service;
import com.ytlk.core.serviceImpl.BaseServiceImpl;
import com.ytlk.${modelName}.entity.${entityName};
import com.ytlk.${modelName}.mapper.${mapperName};
import com.ytlk.${modelName}.service.${serviceName};
/**
 * 
 * @author mhy
 * @date:   ${nowDate?string("yyyy-MM-dd")}
 */
@Service
public class ${serviceImplName} extends BaseServiceImpl<${entityName}, ${mapperName}> implements ${serviceName}{

}
