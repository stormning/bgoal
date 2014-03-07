<%@page import="com.slyak.core.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<c:set var="js" scope="request" value="admin/sources" />
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a
		href="#">资源管理</a></li>
	<li><span class="icon-angle-right"></span> 所有资源</li>
</ul>
<div class="social-box">
	<div class="header">
		<div class="row-fluid">
			<div class="span6">
				<a class="btn btn-primary" id="addSource" href="${ctx}/admin/source/add">添加</a>
			</div>
			<div class="span6">
				<label style="margin-bottom: 0px;" class="pull-right">Search:
					<input type="text" style="margin-bottom: 0px !important;">
					<a class="btn btn-primary" id="add-row" href="#">搜索</a>
				</label>
			</div>
		</div>
	</div>
	<div class="body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectAll"></th>
					<th>ID</th>
					<th>名称</th>
					<th>URL</th>
					<th>记录数</th>
					<th>最新更新</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sources}" var="source">
					<c:set var="sourceId" value="${source.id}" scope="request"/>
					<tr>
						<td><input type="checkbox" sourceId="${source.id}"></td>
						<td>${source.id}</td>
						<td>${source.name}</td>
						<td><a href="${source.url}" target="_blank">${source.url}</a></td>
						<td><a href="${ctx}/admin/articles?sourceId=${source.id}" target="_blank">${sourceArticleCount[source.id].count}</a></td>
						<td><em class="muted"><fmt:formatDate value="${sourceArticleCount[source.id].lastUpdate}" pattern="yyyy/MM/dd HH:mm:ss"/></em></td>
						<td>
							<a class="edit" href="${ctx}/admin/source/${source.id}/edit">编辑</a>
							<a class="delete" href="${ctx}/admin/source/${source.id}/toggle">
								<c:choose>
									<c:when test="${source.status=='ENABLED'}">停用</c:when>
									<c:otherwise>启用</c:otherwise>
								</c:choose>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@include file="../pagination.jsp"%>
	</div>
</div>