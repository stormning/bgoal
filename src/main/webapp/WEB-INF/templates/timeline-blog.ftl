<%@ taglib prefix="slyak" uri="http://slyak.com/tags"%>
<div class="media">
	<div class="pull-left">
		<a class="thumbnail" href="${ctx}/user/profile">
			<img class="media-object" src="${ctx}${filePath('100',sourceId,'small')}" alt="Maria">
		</a>
	</div>	
	<div class="media-body">
		<h2><a href="${ctx}/article/${id}">${title!}</a></h2>
		<p>
			<a href="${ctx}/">丫头潘潘</a>   <#if createAt??>发布于 ${createAt?number?number_to_datetime}</#if> - 87评
		</p>
		<p>
			${content!}
		</p>
	</div>
</div>
<#if !isLast>
<hr/>	
</#if>