<#--表格相关组件-->

<#--表格-->
<#macro table url id="table" pagination="true" undefined_text="" form_id="form">
<div class="form-table-space"></div>
<table id="${id}" data-toggle="table" data-url="${url}" data-pagination="${pagination}"
       data-side-pagination="server" data-undefined-text="${undefined_text}" data-striped="true" data-form-id="${form_id}">
    <thead>
    <tr>
        <#nested/>
    </tr>
    </thead>
</table>
<script>$('#${id}').bootstrapTable();</script>
</#macro>

<#--表格的行-->
<#macro th title field="" class="" sortable="true" render="false" datetime="false">
<th data-field="${field}" class="${class}"
    <#if field!=''>
         data-sortable="${sortable}"
    </#if>
    <#if render=="true">
        <#--<#assign dataFormatter='<@random property="xxx"/>'/>-->
        <#local dataFormatter=random/>
    data-formatter="${dataFormatter?string}"
    </#if>
>
${title}
    <#if render=="true">
        <div id="${dataFormatter}" class="hidden">
            <#nested/>
        </div>
        <script>
            function ${dataFormatter}(value, row, index) {
                var data = {"value": value, "row": row, "index": index};
                return template('${dataFormatter}', data);
            }
        </script>
    </#if>
</th>
</#macro>
