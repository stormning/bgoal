<%@page import="com.slyak.core.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<c:set var="js" scope="request" value="admin/articles" />

<style>
	a.removeable {
		display: inline-block;
		border:1px solid #ddd;
		padding: 2px; 
		text-decoration: none;
	}
	
	a.removeable .from{
		color: #999;
		padding-right: 5px;
	}
</style>

<ul class="breadcrumb">
	<li><i class="icon-home"></i><a href="#">内容管理</a></li>
	<li><span class="icon-angle-right"></span> 所有文章</li>
</ul>
<div class="social-box">
	<div class="header">
		<div class="row-fluid">
			<div class="span6">
				<a class="btn btn-primary" id="addArticle" href="${ctx}/admin/article/add">添加</a>
				<a class="btn btn-primary" id="rebuildAllIndex" href="${ctx}/admin/index/rebuildAll">重建索引</a>
			</div>
			<div class="span6">
				<form action="${ctx}/admin/articles" method="get">
					<label style="margin-bottom: 0px;" class="pull-right">
						<c:if test="${not empty param.sourceId}">
							<input type="hidden" name="sourceId" value="${param.sourceId}">
						</c:if>
						<c:if test="${not empty param.creator}">
							<input type="hidden" name="creator" value="${param.creator}">
						</c:if>
						<input type="text" style="margin-bottom: 0px !important;" name="key" value="${param.key}">
						<input type="submit" class="btn btn-primary" value="搜索">
					</label>
				</form>
			</div>
		</div>
	</div>
	<div class="body">
		<div class="social-box">
			<div class="header">筛选</div>
			<div class="body">
				<c:if test="${not empty param.sourceId || not empty param.creator}">				
					<div class="row-fluid">
						<div class="span2 text-right">已选条件：</div>
						<div class="span10">
							<a class="pull-right muted" href="${ctx}/admin/articles">删除所有条件</a>							
							<c:if test="${not empty param.sourceId}">
								<a href="${ctx}/admin/articles?<c:if test="${not empty param.key}">&key=${param.key}</c:if><c:if test="${not empty param.creator}">&creator=${param.creator}</c:if>" class="removeable"><span class="from">来源:</span>${source.name} <i class="icon-remove"></i> </a>
							</c:if>
							<c:if test="${not empty param.creator}">
								<a href="${ctx}/admin/articles?<c:if test="${not empty param.key}">&key=${param.key}</c:if><c:if test="${not empty param.sourceId}">&sourceId=${param.sourceId}</c:if>" class="removeable"><span class="from">作者:</span>${creator.nickName} <i class="icon-remove"></i> </a>
							</c:if>
						</div>
					</div>
					<hr/>
				</c:if>
				<div class="row-fluid">
					<div class="span2 text-right">来源：</div>
					<div class="span10">
						<div class="row-fluid">
							<fmt:parseNumber integerOnly="true" value="${(fn:length(sources)/6)+0.5}" var="loopCount"/>
							<c:set var="begin" value="0"/>
							<c:forEach begin="0" end="${loopCount}">
								<div class="row-fluid">
									<c:forEach items="${sources}" begin="${begin}" end="${begin+5}" varStatus="st">
										<div class="span2 text-center"><a<slyak:requestMatch pattern="/admin/articles" arguments="sourceId=${sources[st.index].id}"> class="label label-info"</slyak:requestMatch> href="${ctx}/admin/articles?sourceId=${sources[st.index].id}<c:if test="${not empty param.creator}">&creator=${param.creator}</c:if><c:if test="${not empty param.key}">&key=${param.key}</c:if>">${sources[st.index].name}</a></div>
									</c:forEach>
								</div>
								<c:set var="begin" value="${begin+6}"/>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span2 text-right">作者：</div>
					<div class="span10">
						<input type="text" data-provide="typeahead" autocomplete="off" class="typeahead" ajax-url="${ctx}/admin/users/suggestion?limit=10" value="${creator.nickName}">
					</div>
				</div>
			</div>	
		</div>
	
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>标题</th>
					<th>来源</th>
					<th>作者</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="result">
					<tr>
						<td>${result.id}</td>
						<td><a href="${ctx}/article/${result.id}" target="_blank">${result.title}</a></td>
						<td><a href="${ctx}/admin/source/${result.sourceId}/edit">${result.sourceName}</a></td>
						<td><a href="${ctx}/admin/user/${result.creator}/edit">${result.creatorName}</a></td>
						<td><em class="muted"><slyak:formatDate date="${result.createAt}"/></em></td>
						<td>
							<a class="edit" href="${ctx}/admin/article/${result.id}/edit">编辑</a> <a class="delete" href="${ctx}/admin/article/${result.id}/toggle">
								<c:choose>
									<c:when test="${result.status=='NORMAL'}">停用</c:when>
									<c:when test="${result.status=='DELETED'}">启用</c:when>
									<c:when test="${result.status=='AUDIT'}">批准</c:when>
									<c:when test="${result.status=='REFUSE'}">通过</c:when>
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