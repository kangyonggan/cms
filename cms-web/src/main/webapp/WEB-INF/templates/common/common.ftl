<#assign ctx="${(rca.contextPath)!''}">

<#include "form.ftl"/>
<#include "table.ftl"/>
<#include "tab.ftl"/>
<#include "component.ftl"/>

<#macro datetime>
{{value | datetimeFormat}}
</#macro>

<#macro date>
{{value | dateFormat}}
</#macro>

<#macro time>
{{value | timeFormat}}
</#macro>

<#macro yesNo>
{{if value==1}}
是
{{else}}
否
{{/if}}
</#macro>