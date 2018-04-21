<#--tab相关组件-->

<#--tab面板-->
<#macro tab_panel>
<div class="tabbable">
    <#nested />
</div>
</#macro>

<#--tab按钮组-->
<#macro tabs>
    <ul class="nav nav-tabs padding-16">
        <#nested/>
    </ul>
</#macro>

<#--tab按钮-->
<#macro tab ref name active="false" icon="">
    <li class="${(active=="true")?string('active', '')}">
        <a data-toggle="tab" href="#${ref}" aria-expanded="${active}">
            <#if icon!="">
            <i class="green ace-icon fa ${icon} bigger-125"></i>
            </#if>
            ${name}
        </a>
    </li>
</#macro>

<#--tab内容组-->
<#macro tab_contents>
    <div class="tab-content profile-edit-tab-content">
        <#nested/>
    </div>
</#macro>

<#--tab内容-->
<#macro tab_content id active="false">
    <div id="${id}" class="tab-pane ${(active=="true")?string('active', '')}">
        <div class="space-6"></div>
        <#nested/>
    </div>
</#macro>