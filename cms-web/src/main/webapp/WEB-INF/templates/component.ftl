<#--通用组件-->

<#--超链接-->
<#macro link name id="" href="javascript:" icon="" class="" type="" table_id="" modal="" backdrop="static">
<a href="${href}" <#if id!=''>id="${id}"</#if> class="btn btn-sm ${class}"
    <#if type!=''>
        data-type="${type}"
    </#if>
   <#if table_id != ''>
        data-table-id="${table_id}"
   </#if>
   <#if modal != ''>
   data-toggle="modal"
   data-target="#${modal}"
   </#if>
   data-backdrop="${backdrop}"
>
    <#if icon!=''>
        <i class="ace-icon fa ${icon}"></i>
    </#if>
    ${name}
</a>
</#macro>