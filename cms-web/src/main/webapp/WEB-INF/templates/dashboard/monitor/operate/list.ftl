<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/monitor/operate"/>

<@c.form class="col-xs-12 fa-border radius-base">
    <@c.input name="query.app" label="应用名称" inline=true/>
    <@c.select name="query.type" label="操作类型" inline=true>
        <option value="CREATE">新增</option>
        <option value="UPDATE">更新</option>
        <option value="DELETE">删除</option>
    </@c.select>
    <@c.input name="query.beginDate" label="调用开始日期" inline=true class="date-picker" readonly=true/>
    <@c.input name="query.endDate" label="调用结束日期" inline=true class="date-picker" readonly=true/>

    <@c.form_actions background=false>
        <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
        <@c.link name="清除" class="btn btn-sm btn-danger" icon="fa-undo" type="reset"/>
    </@c.form_actions>
</@c.form>

<@c.table url="${baseUrl}/list">
    <@c.th field="app" title="应用名称"/>
    <@c.th field="type" title="操作类型" render=true class="hidden-sm hidden-xs">
        {{if value=="CREATE"}}
            新增
        {{else if value=="UPDATE"}}
            更新
        {{else if value=="DELETED"}}
            删除
        {{else}}
            value
        {{/if}}
    </@c.th>
    <@c.th field="description" title="描述" sortable=true />
    <@c.th field="hasReturnValue" title="有无返回值" sortable=true render=true class="hidden-sm hidden-xs">
        <@c.yesNo/>
    </@c.th>
    <@c.th field="username" title="操作用户" sortable=true/>
    <@c.th field="beginTime" title="调用开始时间" render=true class="hidden-sm hidden-xs">
        <@c.datetime/>
    </@c.th>
    <@c.th field="endTime" title="调用结束时间" render=true class="hidden-sm hidden-xs">
        <@c.datetime/>
    </@c.th>
</@c.table>