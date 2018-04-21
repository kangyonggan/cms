<#assign ctx="${(rca.contextPath)!''}">

<#include "form.ftl"/>
<#include "table.ftl"/>
<#include "tab.ftl"/>
<#include "component.ftl"/>

<#--模态框底部按钮-->
<#macro modal_form_tool>
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@s.message "app.button.cancel"/>
</button>

<button class="btn btn-sm btn-success" id="submit"
        data-loading-text="正在<@s.message "app.button.save"/>..." data-toggle="form-submit" data-target="#modal-form">
    <i class="ace-icon fa fa-check"></i>
    <@s.message "app.button.save"/>
</button>
</#macro>

<#--查询表单的地步按钮-->
<#macro query_form_tool>
    <@c.inline_btn name="查询" type="submit" class="btn-purple" icon="fa-search" table_id="table"/>
    <@c.inline_btn name="清除" type="reset" class="btn-danger" icon="fa-undo"/>
</#macro>