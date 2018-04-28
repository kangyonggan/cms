<#--通用组件-->

<#--超链接组-->
<#macro link_group>
<div class="btn-group">
    <#nested/>
</div>
</#macro>

<#--超链接-->
<#macro link name id="" href="javascript:" icon="" class="" drop=false type="" table_id="" modal="" backdrop="static" type="" title="" click="">
<#if drop>
<li>
</#if>
<a href="${href}" <#if id!=''>id="${id}"</#if> class="${class}" title="${title}"
    <#if type!=''>
        data-type="${type}"
    </#if>
   <#if table_id != ''>
        data-table-id="${table_id}"
   </#if>
   <#if click != ''>
        onclick="${click}(this)"
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
<#if drop>
</li>
</#if>
</#macro>

<#--下拉按钮组-->
<#macro drop_group>
<button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle" aria-haspopup="true"
        aria-expanded="false">
    <span class="ace-icon fa fa-caret-down icon-only"></span>
</button>

<ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
    <#nested/>
</ul>
</#macro>

<#--按钮-->
<#macro button name id="" class="" icon="" dismiss=false type="">
<button class="btn ${class}" data-loading-text="正在${name}..."
    <#if id!="">
        id="${id}"
    </#if>
    <#if dismiss>
         data-dismiss="modal"
    </#if>
    <#if type!=''>
        data-type="${type}"
    </#if>
>
    <#if icon != ''>
    <i class="ace-icon fa ${icon}"></i>
    </#if>
    ${name}
</button>
</#macro>
