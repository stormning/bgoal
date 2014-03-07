<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="slyak" uri="http://slyak.com/tags"%>
<div class="media">
	<div class="pull-left">
		<a class="thumbnail" href="${ctx}/user/profile">
			<img class="media-object" src="" alt="Maria">
		</a>
	</div>	
	<div class="media-body">
		<h2><a href="${ctx}/article/${id}">${title}</a></h2>
		<p>
			<a href="${ctx}/">丫头潘潘</a>   发布于 <slyak:formatDate date="${createAt}"/> - 87评
		</p>
		<p>
			${content}
		</p>
	</div>
</div>