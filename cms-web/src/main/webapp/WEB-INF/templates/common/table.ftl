<#--表格相关组件-->

<#--表格-->
<#macro table url id="table" pagination=true undefined_text="" form_id="form">
<div class="form-table-space"></div>
<table id="${id}" data-toggle="table" data-url="${url}" data-pagination="${pagination?string}"
       data-side-pagination="server" data-undefined-text="${undefined_text}" data-striped="true"
       data-form-id="${form_id}" data-click-to-select="true">
    <thead>
    <tr>
        <#nested/>
    </tr>
    </thead>
</table>
<script>$('#${id}').bootstrapTable();</script>
</#macro>

<#--表格的行-->
<#macro th title="" field="" class="" sortable=true render=false checkbox=false>
<th data-field="${field}" class="${class}"
    <#if field!=''>
    data-sortable="${sortable?c}"
    </#if>
    <#if checkbox>
        data-checkbox="true"
    </#if>
    <#if render>
        <#local uuid=app('uuid', 'th')/>
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

<#--表格的格式化-->
<#macro thFormat type="" enum_key="" dict_type="" show_code=true>
    <#if type=="enum">
        <#local uuid=app('uuid', 'func')/>
    <script>
        var obj = {};
            <#local map=enum('map', enum_key)/>
            <#if map?? && map?size gt 0>
                <#list map?keys as key>
                obj["${key}"] = "${map[key]}";
                </#list>
            </#if>
        template.helper('${uuid}', function (value) {
            for (var key in obj) {
                if (key == value) {
                    return obj[key]<#if show_code> + "[" + key + "]"</#if>;
                }
            }
            return value;
        });
    </script>
    {{value | ${uuid}}}
    <#elseif type=="dict">
        <#local uuid=app('uuid', 'func')/>
    <script>
        var arr = [];
            <#local list=dict('list', dict_type)/>
            <#if list?? && list?size gt 0>
                <#list list as dict>
                arr.push({"code": "${dict.code}", "value": "${dict.value}"});
                </#list>
            </#if>
        template.helper('${uuid}', function (code) {
            for (var i in arr) {
                if (arr[i].code == code) {
                    return arr[i].value<#if show_code> + "[" + code + "]"</#if>;
                }
            }
            return code;
        });
    </script>
    {{value | ${uuid}}}
    <#elseif type=="datetime">
    {{value | datetimeFormat}}
    <#elseif type=="date">
    {{value | dateFormat}}
    <#elseif type=="time">
    {{value | timeFormat}}
    <#elseif type=="yesNo">
    {{if value==1}}
    是
    {{else}}
    否
    {{/if}}
    <#else>
    {{value}}
    </#if>
</#macro>