<#--内联表单-->
<#macro inline_form id="form" table_id="table">
<form class="form fa-border radius-base col-xs-12">
    <div class="space-10"></div>
    <#nested/>
</form>
</#macro>

<#--内联域-->
<#macro inline_fields>
    <#nested/>
</#macro>

<#--内联文本框-->
<#macro inline_text name label placeholder="">
<div class="form-group col-lg-4 col-md-6 col-xs-12">
    <label class="col-md-4 col-xs-12 align-right app-label nowrap control-label">${label}</label>
    <div class="col-md-8 col-xs-12">
        <input class="form-control" name="${name}"
               placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}">
    </div>
</div>
</#macro>

<#--内联日期框-->
<#macro inline_date name label placeholder="">
<div class="form-group col-lg-4 col-md-6 col-xs-12">
    <label class="col-md-4 col-xs-12 align-right app-label nowrap control-label">${label}</label>
    <div class="col-md-8 col-xs-12">
        <input class="form-control date-picker readonly" readonly name="${name}" placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}">
    </div>
</div>
</#macro>

<#--查询表单的按钮-->
<#macro inline_actions>
<div class="col-xs-12 align-center">
    <#nested/>
    <div class="space-6"></div>
</div>
</#macro>

<#macro inline_btn name type="" class="btn-primary" table_id="" icon="">
<button class="btn btn-sm ${class}"
    <#if table_id != ''>
        data-table-id="${table_id}"
    </#if>
    <#if type!=''>
        data-toggle="form-${type}"
    </#if>
>
${name}
    <#if icon != ''>
        <span class="ace-icon fa ${icon} icon-on-right bigger-110"></span>
    </#if>
</button>
</#macro>
