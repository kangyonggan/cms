<#--表单相关组件-->

<#--表单-->
<#macro form action="" id="form" action="" method="post" class="" multipart=false token=false>
<form method="${method}" class="form-horizontal ${class}"
      <#if multipart>enctype="multipart/form-data"</#if>
      <#if action!=''>action="${action}"</#if>
      <#if id!=''>id="${id}"</#if>
>
    <div class="space-6"></div>
    <#if token>
        <input type="hidden" name="_token" value="${_token!''}"/>
    </#if>
    <#nested />
</form>
</#macro>

<#--输入框-->
<#macro input name label value="" inline=false class="" placeholder="" readonly=false type="text" required=false valid={}>
<div class="form-group <#if inline>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if inline>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label class="<#if required>required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls <#if inline>col-xs-12</#if>">
        <input type="${type}" id="${name}" <#if readonly>readonly</#if> name="${name}"
        <#list valid?keys as nm>
            ${nm}="${valid[nm]}"
        </#list>
        value="${value}" class="form-control ${class} <#if readonly>readonly</#if>"
        placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}" <#if required>required</#if>/>
    </div>
    <#if class?contains("date-picker")>
        <script>
            $('.date-picker').datepicker();
        </script>
    </#if>
</div>
</#macro>

<#--下拉选择框-->
<#macro select name label value="" inline=false class="" placeholder=""
readonly=false type="single" required=false enum_key="" dict_type="" show_code=true chosen=true>
<div class="form-group <#if inline>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if inline>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label class="<#if required>required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls <#if inline>col-xs-12</#if>">
        <#local id=(name?contains("."))???string('${name?substring(name?index_of(".") + 1)}', '${name}')/>
        <select id="${id}" name="${name}"
                class="form-control <#if chosen>chosen-select</#if> ${class} <#if readonly>readonly</#if>">

            <option value=""></option>

            <#if enum_key != "">
                <#local map=enum('map', enum_key)/>
                <#if map?? && map?size gt 0>
                    <#list map?keys as key>
                        <option value="${key}" <#if value==key>selected</#if>>
                            ${map[key]}<#if show_code>[${key}]</#if>
                        </option>
                    </#list>
                </#if>
            <#elseif dict_type != "">
                <#local list=dict('list', dict_type)/>
                <#if list?? && list?size gt 0>
                    <#list list as dict>
                        <option value="${dict.code}" <#if value==dict.code>selected</#if>>
                            ${dict.value}<#if show_code>[${dict.code}]</#if>
                        </option>
                    </#list>
                </#if>
            </#if>
            <#nested />
        </select>
        <#if chosen>
            <script>
                $('#${id}').chosen({
                    allow_single_deselect: true,
                    disable_search_threshold: 10,
                    no_results_text: "没有匹配的结果",
                    placeholder_text: "请选择一项"
                });
            </script>
        </#if>
    </div>
</div>
</#macro>

<#--表单按钮组-->
<#macro form_actions align="center" background=true>
    <#if background>
    <div class="clearfix form-actions">
    </#if>
    <div class="col-xs-12 align-${align}">
        <#nested />
        <#if !background>
            <div class="space-6"></div>
        </#if>
    </div>
    <#if background>
    </div>
    </#if>
</#macro>

<#--文本域-->
<#macro textarea name label value="" inline=false class="" placeholder="" readonly=false type="" required=false valid={} rows=8>
    <div class="form-group <#if inline>col-lg-4 col-md-6 col-xs-12</#if>">
        <div class="app-label nowrap <#if inline>col-md-5 col-xs-12<#else>col-md-3</#if>">
            <label class="<#if required>required</#if>">${label}</label>
        </div>
        <div class="col-md-7 controls <#if inline>col-xs-12</#if>">
            <textarea id="${name}" name="${name}" class="form-control ${class}" rows="${(type=='kindeditor')?string('${rows + 5}', '${rows}')}"
                      <#if readonly>readonly</#if>
                    <#list valid?keys as nm>
                        ${nm}="${valid[nm]}"
                    </#list>
                    placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}"
                      <#if required>required</#if>>${value}</textarea>
        </div>
    <#if type == "markdown">
        <script>
            $("#${name}").markdown({resize: 'vertical'});
        </script>
    <#elseif type == "kindeditor">
        <script>
            kedit('textarea[name="${name}"]');
        </script>
    </#if>
    </div>
</#macro>
