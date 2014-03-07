<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>

<c:set var="css" value="nivo-slider/themes/default/default|nivo-slider/nivo-slider" scope="request" />
<c:set var="js" value="nivo-slider/jquery.nivo.slider|home/index" scope="request" />

<style>
<!--
.thumbnail{
	background-color: #fff;
}

.img-small{
	width: 34px;
}

.img-middle{
	width: 50px;
}
-->
</style>

<div class="row-fluid">
	<div class="span9">
		<div class="row-fluid">
			<div class="span6">
				<div class="slider-wrapper theme-default">
					<div id="slider" class="nivoSlider">
						<a href=''><img src='${rs}/img/1.jpg' data-thumb='${rs}/img/1.jpg' alt='' title='圣诞快乐' /></a>
						<a href=''><img src='${rs}/img/2.jpg' data-thumb='${rs}/img/2.jpg' alt='' title='信息触手可及' /></a>
					</div>
				</div>
			</div>	
			<div class="span6">
				<div class="social-box">
					<ul class="body">
						<li>
							<h4><a href="/news/47661/git-osc-prize" title="马年献礼，git@osc 抽奖活动火热进行中！ (2014/01/09)" target="_blank" style="color:#A00;">马年献礼，git@osc 抽奖活动火热进行中！</a></h4>
                        	<p>git@osc马年献礼抽奖活动火热进行中，您今天试过手气了吗？ &nbsp; 活动期间（2014年1月2日--2014年1月19日）...</p>
						</li>
	   					<li class="today"><small class="pull-right muted">12/27</small><a href="${ctx}/article/2266" title="HeidiSQL 8.2 已发布(支持64位)" target="_blank">HeidiSQL 8.2 已发布(支持64位)</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="${ctx}/article/2267" title="Event and Task Manager 2.3.19 发布" target="_blank">Event and Task Manager 2.3.19 发布</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="${ctx}/article/2268" title="Mpg123 1.17.0 发布，MPEG 播放器和解码器" target="_blank">Mpg123 1.17.0 发布，MPEG 播放器和解码器</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47268/pride-1-7-2" title="Pride 1.7.2 发布，Android 开发脚本" target="_blank">Pride 1.7.2 发布，Android 开发脚本</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47261/pride-1-7-1" title="Pride 1.7.1 发布，Android 开发脚本" target="_blank">Pride 1.7.1 发布，Android 开发脚本</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47259/4mlinux-8-core" title="4MLinux 8.0 核心发布，迷你 Linux 发行版" target="_blank">4MLinux 8.0 核心发布，迷你 Linux 发行版</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47257/roosevelt-0-4-11" title="Roosevelt 0.4.11 发布， Node.js 的 Web 框架" target="_blank">Roosevelt 0.4.11 发布， Node.js 的 Web 框架</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47253/lynis-1-3-8" title="Lynis 1.3.8 发布，Linux 系统审计工具" target="_blank">Lynis 1.3.8 发布，Linux 系统审计工具</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47252/zxtune-2880" title="ZXTune b2880 发布，芯片曲调播放器" target="_blank">ZXTune b2880 发布，芯片曲调播放器</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47251/peazip-5-2-1" title="PeaZip 5.2.1 发布，压缩管理工具" target="_blank">PeaZip 5.2.1 发布，压缩管理工具</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="${ctx}/article/123" title="HeidiSQL 8.2 已发布(支持64位)" target="_blank">HeidiSQL 8.2 已发布(支持64位)</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47270/event-and-task-manager-2-3-19" title="Event and Task Manager 2.3.19 发布" target="_blank">Event and Task Manager 2.3.19 发布</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47269/mpg123-1-17-0" title="Mpg123 1.17.0 发布，MPEG 播放器和解码器" target="_blank">Mpg123 1.17.0 发布，MPEG 播放器和解码器</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47268/pride-1-7-2" title="Pride 1.7.2 发布，Android 开发脚本" target="_blank">Pride 1.7.2 发布，Android 开发脚本</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47261/pride-1-7-1" title="Pride 1.7.1 发布，Android 开发脚本" target="_blank">Pride 1.7.1 发布，Android 开发脚本</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47259/4mlinux-8-core" title="4MLinux 8.0 核心发布，迷你 Linux 发行版" target="_blank">4MLinux 8.0 核心发布，迷你 Linux 发行版</a></li>
			    		<li class="today"><small class="pull-right muted">12/27</small><a href="/news/47257/roosevelt-0-4-11" title="Roosevelt 0.4.11 发布， Node.js 的 Web 框架" target="_blank">Roosevelt 0.4.11 发布， Node.js 的 Web 框架</a></li>
				    </ul>
			    </div>
			</div>
		</div>
		
		<div>
			<img src="http://static.oschina.net/uploads/ad/index_banner_one_708_50_SlmMR.jpg" width="100%">
		</div>
		
		<div class="row-fluid">
			<div class="span3">
				<section class="aside-tags">
					<h2>Feature Tags</h2>
					<ul class="aside-tags__list cf">
						<li><a href="http://www.36kr.com/pages/feedback">#36氪新版反馈#</a></li>
						<li><a href="/tag/氪星年货">#氪星年货#</a></li>
						<li><a href="/tag/startup-x">#Startup X#</a></li>
						<li><a href="/tag/k815">#8点1氪 晚间版#</a></li>
						<li><a href="/tag/wise">#36氪WISE峰会#</a></li>
						<li><a href="/topic/smart-hardware">#智能硬件#</a><sup>HOT!</sup></li>
						<li><a href="/tag/技术合伙人">#技术合伙人#</a><sup>NEW!</sup></li>
					</ul>
				</section>
			</div>
			<div class="span9">
				
			</div>
		</div>
	</div>
	<div class="span3">
		<div class="social-box">
			<a href="http://cnrdn.com/HoqC" target="_blank" title="三星hub " class="external"><img alt="三星hub" src="http://a.36krcnd.com/poster/0b20f9586090e14d3ca496f0d37b6c34.jpg"></a>
		</div>
		<div>
			<ul class="nav nav-tabs">
				<li class="active"><a href="#pots" data-toggle="tab">所有</a></li>
				<li><a href="#info" data-toggle="tab">动静</a></li>
				<li><a href="#info" data-toggle="tab">博客</a></li>
				<li><a href="#profile" data-toggle="tab">评论</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade in active" id="info">
					<ul class="media-list">
						<!-- recentRegist -->
						<c:forEach begin="0" end="4">
							<li class="media">
								<a class="pull-left thumbnail" href="${ctx}/user/1/profile"> <img src="${rs}/img/user3_55.jpg" class="media-object img-small" /></a>
								<div class="media-body">
									<h5 class="media-heading">
										<a href="${ctx}/user/1/profile">杨过</a> <span class="muted">刚刚</span> 有了动静
									</h5>
									<small>人逢喜事精神爽,哦也!</small>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
