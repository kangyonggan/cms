{{if value==1}}
<@c.link name="<span class='label label-danger arrowed-in'>已删除</span>"
href="${baseUrl}/{{row.username}}/undelete" type="confirm" title="恢复用户"/>
{{else}}
<@c.link name="<span class='label label-success arrowed-in'>未删除</span>"
href="${baseUrl}/{{row.username}}/delete" type="confirm" title="删除用户"/>
{{/if}}