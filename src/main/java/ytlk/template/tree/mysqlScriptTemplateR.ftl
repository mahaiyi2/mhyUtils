CREATE TABLE `${tblName}` (

	<#list fieldList as field>
	`${field.column}` bigint(20) DEFAULT NULL,
	</#list>  
	 `create_by` bigint(20) DEFAULT NULL,
  	`create_time` datetime DEFAULT NULL
  	-- PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='${theDes}';
