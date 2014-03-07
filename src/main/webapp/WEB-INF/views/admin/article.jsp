<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<c:set var="js" value="kindeditor/kindeditor-min|kindeditor/lang/zh_CN|kindeditor/plugins/code/prettify|jquery.validate|admin/article" scope="request"/>
<c:set var="css" value="" scope="request"/>
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="${ctx}/admin">文章管理</a></li>
	<li><span class="icon-angle-right"></span> <a href="${ctx}/admin/articles">所有文章</a></li>
	<li><span class="icon-angle-right"></span> 
		<c:choose>
			<c:when test="${empty article }">
			添加文章	
			</c:when>
			<c:otherwise>
			修改文章
			</c:otherwise>
		</c:choose>
	</li>
</ul>
<div class="social-box">
	<div class="header">
		<h4>
			<c:choose>
				<c:when test="${empty article}">
					填写信息
				</c:when>
				<c:otherwise>
					修改信息
				</c:otherwise>
			</c:choose>
		</h4>
	</div>
	<div class="body">
		<form action="${ctx}/admin/article/save" class="form-horizontal" id="articleForm" method="post">
			<div class="control-group">
				<label class="control-label">标题</label>
				<div class="controls">
					<c:if test="${not empty article}">
						<input type="hidden" name="id" value="${article.id}">
					</c:if>
					<input type="text" class="span6" name="title" value="${article.title}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">创建者</label>
				<div class="controls">
					<label class="radio">
						<input type="radio" name="creator" value='<shiro:principal/>' autocomplete="off" id="current"> 当前用户
					</label>
					<label class="radio">
						<input type="radio" name="creator" value="${fake.id}" autocomplete="off" id="fake"> 随机马甲
					</label>
					<label class="radio">
						<input type="radio" name="creator" <c:if test="${not empty creator}">value="${creator.id}" checked="checked"</c:if> autocomplete="off" id="special"> 特定用户 
					</label>
					 <input type="text" <c:if test="${empty creator}">style="display:none"</c:if> data-provide="typeahead" autocomplete="off" class="typeahead" ajax-url="${ctx}/admin/users/suggestion?limit=10" value="${creator.nickName}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容</label>
				<div class="controls">
					<textarea class="span6" rows="5" id="info" name="content">${article.content }</textarea>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">提交</button>
				<button type="button" class="btn btn-danger" onclick="javascript:history.back()">取消</button>
			</div>
		</form>
	</div>
</div>
	
<script>
var param = {};
param.cssPath = '${rs}/js/kindeditor/plugins/code/prettify.css';
param.uploadJson = '${ctx}/file/textEditor/upload';
param.fileManagerJson = '${ctx}/file/textEditor';
</script>