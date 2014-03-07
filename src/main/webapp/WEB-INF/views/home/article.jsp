<%@page import="com.slyak.bgoal.model.Source"%>
<%@page import="com.slyak.core.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>

<c:set var="css" value="" scope="request" />
<c:set var="js" value="home/article" scope="request" />

<div class="row-fluid">
	<div class="span9">
		<div class="social-box">
			<div class="body">
				<h3>${article.title }</h3>
				<p>
				<c:choose>
					<c:when test="${empty source}">
						<a href="${ctx}/user/${creator.id}/profile">${creator.name}</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/source/${article.sourceId}">${source.name}</a>
					</c:otherwise>
				</c:choose>
				更新于 <span class="muted"><fmt:formatDate value="${article.createAt}" pattern="yyyy-MM-dd"/></span>
				</p>
				<div>${article.content}</div>
			</div>
		</div>
	</div>	
	<div class="span3">
		<div class="social-box">
			<img src="http://static.oschina.net/uploads/ad/new_banner_one_news1_mopaas_0102_qgpZc.jpg"/>
		</div>
		<div class="social-box">
			
		</div>
	</div>
</div>