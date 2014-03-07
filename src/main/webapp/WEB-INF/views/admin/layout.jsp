<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>

<div class="row-fluid">
	<div class="span3">
		<div class="social-nav-list">
			<ul class="nav nav-list dividers">
				<li class="nav-header"><i class="icon-rss"></i> 资源管理</li>
				<li<slyak:requestMatch pattern="/admin/source*/**,/admin"> class="active"</slyak:requestMatch>><a href="${ctx}/admin/sources">&nbsp;&nbsp;&nbsp;兴趣源</a></li>
				<li class="nav-header"><i class="icon-user"></i> 用户管理</li>
				<li<slyak:requestMatch pattern="/admin/user*/**"> class="active"</slyak:requestMatch>><a href="${ctx}/admin/users">&nbsp;&nbsp;&nbsp;所有用户</a></li>
				<li class="nav-header"><i class="icon-file"></i> 内容管理</li>
				<li<slyak:requestMatch pattern="/admin/articles"> class="active"</slyak:requestMatch>><a href="${ctx}/admin/articles">&nbsp;&nbsp;&nbsp;文章列表</a></li>
			</ul>
		</div>
	</div>
	<div class="span9">
		<tiles:insertAttribute name="right" ignore="true"/>
	</div>
</div>