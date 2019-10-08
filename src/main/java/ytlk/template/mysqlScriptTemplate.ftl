CREATE TABLE `${tblName}` (
  `id` bigint(20) NOT NULL,
	<#list fieldList as field>
	`${field.column}` varchar(200) DEFAULT NULL COMMENT '${field.fieldDes}',
	</#list>  
  `update_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable_` int(1) DEFAULT '1',
  `order_by` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='${theDes}';
