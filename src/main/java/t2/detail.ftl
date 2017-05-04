    <#assign col_num=3/>
    <tr class="box_tr">
    <#assign  keys=mapp?keys/>
    <#assign mi=col_num-(keys?size)%col_num/>
    <#list keys as key>
    <#assign i=key_index>
    <#if (i%3==0 && i!=0)>
    </tr>
    <tr class="box_tr">
        <td>${key}:<label>${r"${var."}${mapp["${key}"]}${r"}"}</label></td>
    <#else>
        <td>${key}:<label>${r"${var."}${mapp["${key}"]}${r"}"}</label></td>
    </#if>
    </#list>
    <#if (mi!=col_num)>
    <#list 0..mi-1 as v>
        <td></td>
    </#list>
    </#if>	
    </tr>