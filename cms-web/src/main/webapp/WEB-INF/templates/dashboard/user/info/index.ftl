<#assign ctx="${(rca.contextPath)!''}">

<div class="space-10"></div>

<@c.form action="${ctx}/dashboard/user/info">
    <@c.tab_panel>
        <@c.tabs>
            <@c.tab ref="edit-basic" name="基础信息" icon="fa-pencil-square-o" active="true"/>
            <@c.tab ref="edit-password" name="修改密码" icon="fa-key"/>
        </@c.tabs>

        <@c.tab_contents>
            <@c.tab_content id="edit-basic" active="true">
                <@c.input name="user.username" label="用户名" readonly="true"/>
                <@c.input name="user.realname" label="真实姓名" required="true"/>
            </@c.tab_content>
            <@c.tab_content id="edit-password">
                <@c.input name="password" type="password" label="新密码" required="true"/>
                <@c.input name="rePassword" type="password" label="确认密码" required="true"/>
            </@c.tab_content>
        </@c.tab_contents>
    </@c.tab_panel>

    <@c.form_actions>
        <@c.form_action name="提交" class="btn-success" icon="fa-check"/>
    </@c.form_actions>
</@c.form>

<script src="${ctx}/static/app/js/dashboard/user/info/index.js"></script>
