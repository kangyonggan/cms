<#--表格相关组件-->

<#--表格-->
<#macro table url id="table" pagination=true undefined_text="" form_id="form">
<div class="form-table-space"></div>
<table id="${id}" data-toggle="table" data-url="${url}" data-pagination="${pagination?string}"
       data-side-pagination="server" data-undefined-text="${undefined_text}" data-striped="true"
       data-form-id="${form_id}">
    <thead>
    <tr>
        <#nested/>
    </tr>
    </thead>
</table>
<script>$('#${id}').bootstrapTable();</script>
</#macro>

<#--表格的行-->
<#macro th title field="" class="" sortable=true render=false>
<th data-field="${field}" class="${class}"
    <#if field!=''>
    data-sortable="${sortable?c}"
    </#if>
    <#if render>
        <#local uuid=app('uuid')/>
        <#local formatter=uuid + "Format"/>
    data-formatter="${formatter}"
    </#if>
>
${title}
    <#if render>
        <div id="${uuid}" class="hidden">
            <#nested/>
        </div>
        <script>
            function ${formatter}(value, row, index) {
                var data = {"value": value, "row": row, "index": index};
                return template('${uuid}', data);
            }
        </script>
    </#if>
</th>
</#macro>
