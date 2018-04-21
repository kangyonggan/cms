<#--内联表单-->
<#macro inline_form id="form" table_id="table">
<form class="form fa-border radius-base col-xs-12" id="form">
    <div class="space-10"></div>
    <#nested/>
</form>
</#macro>

<#--表单-->
<#macro form action="" id="form" action="" method="post" class="form-horizontal" enctype="multipart/form-data">
    <form id="${id}" method="${method}" <#if action!=''>action="${action}"</#if> class="${class}"
          enctype="${enctype}">
        <#nested />
    </form>
</#macro>

<#--表单按钮组-->
<#macro form_actions align="center">
<div class="clearfix form-actions">
    <div class="align-${align}">
        <#nested />
    </div>
</div>
</#macro>

<#--表单按钮-->
<#macro form_action name id="" class="" icon="">
<button id="${id}" class="btn ${class}" data-loading-text="正在${name}...">
    <#if icon != ''>
    <i class="ace-icon fa ${icon}"></i>
    </#if>
    ${name}
</button>
</#macro>

<#--内联域-->
<#macro inline_fields>
    <#nested/>
</#macro>

<#--内联文本框-->
<#macro inline_text name label placeholder="">
<div class="form-group col-lg-4 col-md-6 col-xs-12">
    <label class="col-md-4 col-xs-12 align-right app-label nowrap">${label}</label>
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

<#macro inline_btn name type="" class="btn-primary" table_id="" icon="" href="" modal="">
<a class="btn btn-sm ${class}" href="${href}"
    <#if table_id != ''>
        data-table-id="${table_id}"
    </#if>
    <#if type=='true'>
        data-toggle="form-${type}"
    </#if>
   <#if modal!=''>
   data-toggle="modal"
   data-target="#myModal"
   data-backdrop="static"
   </#if>
>
${name}
    <#if icon != ''>
        <span class="ace-icon fa ${icon} icon-on-right bigger-110"></span>
    </#if>
</a>
</#macro>

<#macro input name label placeholder="" readonly="" type="text" required="false">
<#assign ph="${(placeholder=='')?string('请输入${label}', placeholder)}"/>
<div class="form-group">
    <div class="col-md-3 app-label">
        <label class="<#if required=="true">required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls">
        <#if type=="password">
            <input type="password" id="${name}" name="${name}" class="form-control" placeholder="${ph}"/>
        <#else>
            <#if readonly=='true'>
                <@s.formInput "${name}" 'class="form-control readonly" readonly placeholder="${ph}"'/>
            <#else>
                <@s.formInput "${name}" 'class="form-control" placeholder="${ph}"'/>
            </#if>
        </#if>
    </div>
</div>
</#macro>

<#macro modal_form action>
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${action}">
    <#nested/>
</form>
</#macro>
