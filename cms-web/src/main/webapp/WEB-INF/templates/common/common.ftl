<#assign ctx="${(rca.contextPath)!''}">

<#include "form.ftl"/>
<#include "table.ftl"/>
<#include "tab.ftl"/>
<#include "component.ftl"/>
<#include "functions.ftl"/>

<#macro datetime>
{{value | datetimeFormat}}
</#macro>

<#macro date>
{{value | dateFormat}}
</#macro>

<#macro time>
{{value | timeFormat}}
</#macro>