<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>

<c:set var="css" value="facebook-timeline" scope="request"/>

<div class="row-fluid">
	<div class="span9">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#pots" data-toggle="tab">所有</a></li>
			<li><a href="#info" data-toggle="tab">动弹</a></li>
			<li><a href="#profile" data-toggle="tab">博客</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="info">${timelines}</div>
		</div>
		<div class="pagination pagination-large pagination-centered">
			<%@include file="../pagination.jsp" %>
		</div>
	</div>
	
	<div class="span3">
		<div class="social-nav-list" style="max-width: 340px; padding: 0;">
			<ul class="nav nav-list dividers">
				<li class="nav-header"><i class="icon-rss"></i> 热门兴趣源</li>
				<li class="active"><a href="#"><i class="icon-time"></i>osc(100+)</a></li>
				<li><a href="#"><i class="icon-group"></i> csdn(80+)</a></li>
				<li><a href="#"><i class="icon-picture"></i> slyak(50+)</a></li>
			</ul>
			
			<ul class="nav nav-list dividers">
				<li class="nav-header"><i class="icon-rss"></i> 热门词汇</li>
				<li class="active"><a href="#"><i class="icon-time"></i>osc(100+)</a></li>
				<li><a href="#"><i class="icon-group"></i> csdn(80+)</a></li>
				<li><a href="#"><i class="icon-picture"></i> slyak(50+)</a></li>
			</ul>
			
			<ul class="nav nav-list dividers">
				<li class="nav-header"><i class="icon-rss"></i> 热搜词汇</li>
				<li class="active"><a href="#"><i class="icon-time"></i>osc(100+)</a></li>
				<li><a href="#"><i class="icon-group"></i> csdn(80+)</a></li>
				<li><a href="#"><i class="icon-picture"></i> slyak(50+)</a></li>
			</ul>
			<!-- <ul id="collapsible-nav" class="nav nav-list dividers collapse">
				<li><a href="#"><i class="icon-rss"></i> Following</a></li>
				<li><a href="#"><i class="icon-play-circle"></i> Games</a></li>
				<li><a href="#"><i class="icon-suitcase"></i> Groups</a></li>
			</ul>

			<ul class="nav nav-list dividers">
				<li class="nav-collapser text-center" data-toggle="collapse" data-target="#collapsible-nav">
					<a href="#"><i class="icon-caret-down icon-2x"></i></a>
				</li>
			</ul> -->
		</div>
	</div>
</div>