<tr class="box_tr">
<#assign  keys=mapp?keys/>
<#assign mi=4-(keys?size)%4>
<#list keys as key>
<#assign i=key_index>
<#if (i%4==0 && i!=0 &&i!=mi)>
</tr>
<tr class="box_tr">
 <td>${key}:<label>${r"${var."}${mapp["${key}"]}${r"}"}</label></td>
<#else>
 <td>${key}:<label>${r"${var."}${mapp["${key}"]}${r"}"}</label></td>
</#if>
</#list>
<#list 0..mi-1 as v>
 <td></td>
</#list>	
</tr>									