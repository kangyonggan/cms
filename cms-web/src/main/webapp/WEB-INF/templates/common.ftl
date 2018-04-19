<#assign ctx="${(rca.contextPath)!''}">

<#--查询表单-->
<#macro query_form id="query-form" table_id="table">
    <form class="form fa-border radius-base col-xs-12">
        <div class="space-10"></div>
        <fieldset>
            <#nested/>
        </fieldset>

        <div class="col-xs-12 align-center">
            <button class="btn btn-sm btn-purple" data-toggle="query-submit" data-table-id="${table_id}">
                查询
                <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
            </button>

            <button class="btn btn-sm btn-danger" data-toggle="form-reset">
                清空
                <span class="ace-icon fa fa-undo icon-on-right bigger-110"></span>
            </button>
            <div class="space-6"></div>
        </div>
    </form>
</#macro>

<#--查询表单的输入框-->
<#macro query_text name label placeholder="">
    <div class="form-group col-lg-4 col-md-6 col-xs-12">
        <label class="col-md-4 col-xs-12 align-right app-label nowrap control-label">${label}</label>
        <div class="col-md-8 col-xs-12">
            <input class="form-control" name="${name}" placeholder="${placeholder}">
        </div>
    </div>
</#macro>

<#--表格-->
<#macro table url id="table">
<div class="form-table-space"></div>
<table id="${id}" data-toggle="table" data-url="${url}" data-pagination="true"
       data-side-pagination="server" data-undefined-text="" data-striped="true">
    <thead>
    <tr>
        <#nested/>
    </tr>
    </thead>
</table>
</#macro>

<#--表格的行-->
<#macro th title field="" sortable="false" render="false" datetime="false">
<th data-sortable="${sortable}" data-field="${field}"
    <#if render=="true">
    data-formatter="${field}Format"
    </#if>
>
    ${title}
    <#if render=="true">
        <div id="${field}Template" class="hidden">
            <#nested/>
        </div>
        <script>
            function ${field}Format(value, row, index) {
                var data = {"value": value, "row": row, "index": index};
                return template('${field}Template', data);
            }
        </script>
    </#if>
</th>
</#macro>