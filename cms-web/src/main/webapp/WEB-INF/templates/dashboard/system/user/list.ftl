<#assign ctx="${(rca.contextPath)!''}">

<@c.inline_form>
    <@c.inline_fields>
        <@c.inline_text name="query.username" label="用户名"/>
        <@c.inline_text name="query.realname" label="真实姓名"/>
        <@c.inline_date name="query.beginDate" label="创建开始日期" placeholder="请选择创建开始日期"/>
        <@c.inline_date name="query.endDate" label="创建结束日期" placeholder="请选择创建结束日期"/>
    </@c.inline_fields>

    <@c.inline_actions>
        <@c.inline_btn name="查询" type="submit" class="btn-purple" icon="fa-search" table_id="user-table"/>
        <@c.inline_btn name="清除" type="reset" class="btn-danger" icon="fa-undo"/>
    </@c.inline_actions>
</@c.inline_form>

<@c.table id="user-table" url="${ctx}/dashboard/system/user/list">
    <@c.th field="id" title="ID" sortable="true" />
    <@c.th field="username" title="用户名" sortable="true" />
    <@c.th field="realname" title="真实姓名" sortable="true" />
    <@c.th field="isDeleted" title="逻辑删除" sortable="true" render="true">
        <#include "delete.ftl"/>
    </@c.th>
    <@c.th field="createdTime" title="创建时间" sortable="true" render="true">
    {{value | datetimeFormat}}
    </@c.th>
    <@c.th title="操作" render="true">
        <@c.btn_group>
            <@c.btn name="编辑" class="btn-inverse"/>
            <@c.drop_group>
                <@c.drop_btn name="设置角色"/>
                <@c.drop_btn name="修改密码"/>
                <@c.drop_btn name="彻底删除"/>
            </@c.drop_group>
        </@c.btn_group>
    </@c.th>
</@c.table>

<script src="${ctx}/static/app/js/dashboard/system/user/list.js"></script>
