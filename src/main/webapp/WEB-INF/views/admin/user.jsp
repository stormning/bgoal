<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<c:set var="js" value="kindeditor/kindeditor-min|kindeditor/lang/zh_CN|kindeditor/plugins/code/prettify|jquery.validate|admin/user" scope="request"/>
<c:set var="css" value="" scope="request"/>
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="${ctx}/admin">用户管理</a></li>
	<li><span class="icon-angle-right"></span> <a href="${ctx}/admin/users">所有用户</a></li>
	<li><span class="icon-angle-right"></span> 
		<c:choose>
			<c:when test="${empty user }">
			添加用户	
			</c:when>
			<c:otherwise>
			修改用户
			</c:otherwise>
		</c:choose>
	</li>
</ul>
<div class="social-box">
	<div class="header">
		<h4>
			<c:choose>
				<c:when test="${empty user}">
					填写信息
				</c:when>
				<c:otherwise>
					修改信息
				</c:otherwise>
			</c:choose>
		</h4>
	</div>
	<div class="body">
		<form action="${ctx}/admin/user/save" class="form-horizontal" id="userForm" method="post">
			<div class="control-group">
				<label class="control-label">email</label>
				<div class="controls">
					<c:if test="${not empty user}">
						<input type="hidden" name="id" value="${user.id}">
					</c:if>
					<input type="text" class="span6" name="email" value="${user.email}" <c:if test="${not empty user}">disabled="disabled"</c:if>>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">昵称</label>
				<div class="controls">
					<input type="text" class="span6" name="nickName" value="${user.nickName}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">密码</label>
				<div class="controls">
					<input type="text" class="span6" name="password" <c:if test="${not empty user}">placeholder="若不填写则不更新"</c:if>>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="controls">
					<textarea class="span6" rows="5" id="info" name="info">${user.info}</textarea>
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