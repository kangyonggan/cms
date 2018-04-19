<#assign ctx="${(rca.contextPath)!''}">

<@c.query_form>
    <@c.query_text name="username" label="用户名" placeholder="请输入用户名"/>
    <@c.query_text name="realname" label="真实姓名" placeholder="请输入真实姓名"/>
    <@c.query_text name="realname" label="创建开始时间" placeholder="请输入创建开始时间"/>
    <@c.query_text name="realname" label="创建结束时间" placeholder="请输入创建结束时间"/>
</@c.query_form>

<@c.table url="${ctx}/static/app/data/users.json">
    <@c.th field="username" title="用户名" sortable="true"/>
    <@c.th field="realname" title="真实姓名"/>
</@c.table>

<script src="${ctx}/static/app/js/dashboard/system/user/list.js"></script>
