<#--表单相关组件-->

<#--表单-->
<#macro form action="" id="form" action="" method="post" class="" enctype="multipart/form-data">
<form method="${method}" class="form-horizontal ${class}" enctype="${enctype}"
      <#if action!=''>action="${action}"</#if>
      <#if id!=''>id="${id}"</#if>
>
    <div class="space-6"></div>
    <#nested />
</form>
</#macro>

<#--输入框-->
<#macro input name label value="" inline=false class="" placeholder="" readonly=false type="text" required=false>
<div class="form-group <#if inline>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if inline>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label class="<#if required>required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls <#if inline>col-xs-12</#if>">
        <input type="${type}" id="${name}" <#if readonly>readonly</#if> name="${name}"
               value="${value}" class="form-control ${class} <#if readonly>readonly</#if>"
               placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}"/>
    </div>
    <#if class?contains("date-picker")>
        <script>
            $('.date-picker').datepicker();
        </script>
    </#if>
</div>
</#macro>

<#--下拉选择框-->
<#macro select name label value="" inline=false class="" placeholder="" readonly=false type="single" required=false map={}>
<div class="form-group <#if inline>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if inline>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label class="<#if required>required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls <#if inline>col-xs-12</#if>">
        <select id="${name}" name="${name}" class="form-control ${class} <#if readonly>readonly</#if>">
            <option value="">${(placeholder=='')?string('请输入${label}', placeholder)}</option>
            <#if map?? && map?size gt 0>
                <#list map?keys as key>
                    <option value="${key}">${map[key]}</option>
                </#list>
            </#if>
            <#nested />
        </select>
    </div>
</div>
</#macro>

<#--表单按钮组-->
<#macro form_actions align="center" background=true>
    <#if background>
    <div class="clearfix form-actions">
    </#if>
    <div class="col-xs-12 align-${align}">
        <#nested />
        <#if !background>
            <div class="space-6"></div>
        </#if>
    </div>
    <#if background>
    </div>
    </#if>
</#macro>

