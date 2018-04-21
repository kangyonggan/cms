<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/system/user"/>

<@c.form class="col-xs-12 fa-border radius-base">
    <@c.input name="query.username" label="用户名" inline="true"/>
    <@c.input name="query.realname" label="真实姓名" inline="true"/>
    <@c.input name="query.beginDate" label="创建开始日期" inline="true" class="date-picker" readonly="true"/>
    <@c.input name="query.endDate" label="创建结束日期" inline="true" class="date-picker" readonly="true"/>

    <@c.form_actions background="false">
        <@c.link name="查询" class="btn-purple" icon="fa-search" type="submit" table_id="table"/>
        <@c.link name="清除" class="btn-danger" icon="fa-undo" type="reset"/>
        <@c.link name="新增用户" class="btn-pink" href="${baseUrl}/create" modal="myModal"/>
    </@c.form_actions>
</@c.form>

<@c.table url="${baseUrl}/list">
    <@c.th field="id" title="ID" sortable="true" class="hidden-sm hidden-xs"/>
    <@c.th field="username" title="用户名" sortable="true" />
    <@c.th field="realname" title="真实姓名" sortable="true"/>
    <@c.th field="isDeleted" title="逻辑删除" sortable="true" render="true">
        <#include "delete.ftl"/>
    </@c.th>
    <@c.th field="createdTime" title="创建时间" sortable="true" render="true" class="hidden-sm hidden-xs">
    {{value | datetimeFormat}}
    </@c.th>
    <@c.th title="操作" render="true">
        <@c.btn_group>
            <@c.btn name="编辑" href="${baseUrl}/{{row.username}}/edit" class="btn-inverse"/>
            <@c.drop_group>
                <@c.drop_btn name="设置角色"/>
                <@c.drop_btn name="修改密码"/>
                <@c.drop_btn name="彻底删除"/>
            </@c.drop_group>
        </@c.btn_group>
    </@c.th>
</@c.table>

<script src="${ctx}/static/app/js/dashboard/system/user/list.js"></script>
