<#assign  keys=obj?keys/>
<#assign mi=4-(keys?size)%4>
<#list keys as key>
${key}	${obj["${key}"]}
</#list>
