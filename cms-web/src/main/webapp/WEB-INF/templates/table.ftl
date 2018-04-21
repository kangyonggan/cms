<#--表格相关组件-->

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
<#macro th title field="" class="" sortable="false" render="false" datetime="false">
<th data-sortable="${sortable}" data-field="${field}" class="${class}"
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

<#--操作按钮组-->
<#macro btn_group>
<div class="btn-group">
    <#nested/>
</div>
</#macro>

<#--操作按钮-->
<#macro btn name href class="" target="myModal" backdrop="true">
<a class="btn btn-xs ${class}" href="${href}" data-toggle="modal" data-target="#${target}" data-backdrop="${backdrop}">${name}</a>
</#macro>

<#--下拉按钮组-->
<#macro drop_group>
<button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle" aria-haspopup="true"
        aria-expanded="false">
    <span class="ace-icon fa fa-caret-down icon-only"></span>
</button>

<ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
    <#nested/>
</ul>
</#macro>

<#--下拉按钮-->
<#macro drop_btn name href="">
<li>
    <a href="${(href=='')?string('javascript:', href)}">${name}</a>
</li>
</#macro>
