<#--选项卡相关组件-->

<#--选项卡面板-->
<#macro tab_panel>
<div class="tabbable">
    <#nested />
</div>
</#macro>

<#--选项卡按钮组-->
<#macro tabs>
    <ul class="nav nav-tabs padding-16">
        <#nested/>
    </ul>
</#macro>

<#--选项卡按钮-->
<#macro tab ref name active=false icon="">
    <li class="${active?string('active', '')}">
        <a data-toggle="tab" href="#${ref}" aria-expanded="${active?string}">
            <#if icon!="">
            <i class="ace-icon fa ${icon} bigger-125"></i>
            </#if>
            ${name}
        </a>
    </li>
</#macro>

<#--选项卡内容组-->
<#macro tab_contents>
    <div class="tab-content profile-edit-tab-content">
        <#nested/>
    </div>
</#macro>

<#--选项卡内容-->
<#macro tab_content id active=false>
    <div id="${id}" class="tab-pane ${active?string('active', '')}">
        <div class="space-6"></div>
        <#nested/>
    </div>
</#macro>