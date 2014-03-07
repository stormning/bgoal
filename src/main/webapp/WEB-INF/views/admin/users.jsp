<%@page import="com.slyak.core.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<c:set var="js" scope="request" value="admin/users" />
<ul class="breadcrumb">
	<li><i class="icon-home"></i><a href="#">用户管理</a></li>
	<li><span class="icon-angle-right"></span> 所有用户</li>
</ul>
<div class="social-box">
	<div class="header">
		<div class="row-fluid">
			<div class="span6">
				<a class="btn btn-primary" id="addUser" href="${ctx}/admin/user/add">添加</a>
				<a class="btn btn-primary" id="addUser" href="${ctx}/admin/user/generate">生成马甲</a>
			</div>
			<div class="span6">
				<form action="${ctx}/admin/users" method="get">
					<label style="margin-bottom: 0px;" class="pull-right">
						<input type="text" style="margin-bottom: 0px !important;" name="key" value="${param.key}">
						<input type="submit" class="btn btn-primary" value="搜索">
					</label>
				</form>
			</div>
		</div>
	</div>
	<div class="body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>email</th>
					<th>昵称</th>
					<th>创建时间</th>
					<th>文章数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.email}</td>
						<td><a href="${ctx}/user/${user.id}/profile" target="_blank">${user.nickName}</a></td>
						<td><em class="muted"><fmt:formatDate value="${user.createAt}" pattern="yyyy/MM/dd HH:mm:ss"/></em></td>
						<td><a href="${ctx}/admin/articles?creator=${user.id}" target="_blank">${usersArticleCount[user.id]}</a></td>
						<td>
							<a class="edit" href="${ctx}/admin/user/${user.id}/edit">编辑</a> <a class="delete" href="${ctx}/admin/user/${user.id}/toggle">
								<c:choose>
									<c:when test="${user.status=='ENABLED'}">停用</c:when>
									<c:otherwise>启用</c:otherwise>
								</c:choose>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination pagination-centered">
			<%@include file="../pagination.jsp"%>
		</div>
	</div>
</div>