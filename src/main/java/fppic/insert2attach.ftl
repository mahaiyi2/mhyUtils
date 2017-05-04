    <#list mapp as var>
	 INSERT INTO fpb_scshtj_attach (FILE_TYPE,SUFFIX,ZJHM,HID,XM,FILE_URL,FILE_NAME) VALUES ('${var.FILE_TYPE}','${var.SUFFIX}','${var.ZJHM}','${var.HID}','${var.XM}','${var.FILE_URL}','${var.FILE_NAME}');
    </#list>
