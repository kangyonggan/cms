<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${user.username???string('编辑用户', '添加新用户')}" />

<@override name="modal-body">
    <@c.modal_form action="${ctx}/dashboard/system/user/${user.username???string('update', 'save')}">
    <input type="hidden" id="old-username" value="${user.username!''}"/>
        <@c.input name="user.username" label="用户名" readonly="${user.username???string('true', 'false')}" required="${user.username???string('false', 'true')}"/>
        <@c.input name="user.realname" label="真实姓名" required="true"/>

        <#if !user.username??>
            <@c.input name="password" type="password" label="密码" required="true"/>
            <@c.input name="rePassword" type="password" label="确认密码" required="true"/>
        </#if>

    </@c.modal_form>
</@override>

<@override name="modal-footer">
    <@c.modal_form_tool/>
<script src="${ctx}/static/app/js/dashboard/system/user/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>