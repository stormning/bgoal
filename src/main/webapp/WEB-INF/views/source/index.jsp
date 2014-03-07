<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>

<c:set var="css" value="nivo-slider/themes/default/default|nivo-slider/nivo-slider" scope="request" />
<c:set var="js" value="nivo-slider/jquery.nivo.slider|home/index" scope="request" />

<div id="user-profile" class="social-box">
<div class="body">
<div class="row-fluid">
<div class="span2">
<div class="row-fluid">
<img src="/templates/social/assets/img/pages/user-profiles/avatar.png" class="img-polaroid avatar span12">
</div>
<p>
<a class="btn btn-block btn-success"><i class="icon-envelope-alt"></i> Send message</a>
</p>
 
<p id="social-icons" class="text-center">
<a href="#"><i class="icon-facebook-sign icon-2x"></i></a>
<a href="#"><i class="icon-twitter-sign icon-2x"></i></a>
<a href="#"><i class="icon-linkedin-sign icon-2x"></i></a>
<a href="#"><i class="icon-google-plus-sign icon-2x"></i></a>
</p>
 
</div>
<div class="span10">
<div class="row-fluid">
 
<div id="user-status" class="span10 text-left">
<h3>Julio Marquez</h3>
<h5>Geophysical Engineer, Company Inc., USA</h5>
</div>
 
<div class="span2">
<a href="#edit" class="btn btn-block btn-primary" id="edit-profile-button">Edit Profile</a>
</div>
</div>
 
<p id="panoramic" class="row-fluid hidden-phone">
	<img src="${rs}/img/1.jpg" class="img-rounded span12" height="120px" style="height: 120px;"/>
</p>
 
</div>
</div>
<div class="row-fluid">
<div class="span2">
 
<div id="friends-list" class="social-box">
<div class="header">
<h4>Friends</h4>
</div>
<div class="body">
<div class="row-fluid">
<div class="span4">
<a href="#" data-toggle="tooltip" title="Yadra Abels">
<img src="/templates/social/assets/img/people-face/user1_55.jpg" alt="User" class="img-rounded">
</a>
</div>
<div class="span4">
<a href="#" data-toggle="tooltip" title="Cesar Mendoza">
<img src="/templates/social/assets/img/people-face/user2_55.jpg" alt="User" class="img-rounded">
</a>
</div>
<div class="span4">
<a href="#" data-toggle="tooltip" title="John Doe">
<img src="/templates/social/assets/img/people-face/user3_55.jpg" alt="User" class="img-rounded">
</a>
</div>
</div>
<br>
<div class="row-fluid">
<div class="span4">
<a href="#" data-toggle="tooltip" title="Tobei Tsumura">
<img src="/templates/social/assets/img/people-face/user4_55.jpg" alt="User" class="img-rounded">
</a>
</div>
<div class="span4">
<a href="#" data-toggle="tooltip" title="Jack Smith">
<img src="/templates/social/assets/img/people-face/user5_55.jpg" alt="User" class="img-rounded">
</a>
</div>
<div class="span4">
<a href="#" data-toggle="tooltip" title="David Street">
<img src="/templates/social/assets/img/people-face/user6_55.jpg" alt="User" class="img-rounded">
</a>
</div>
</div>
</div>
</div>
 
</div>
<div class="span10">
 
<div class="row-fluid">
<ul id="profileTab" class="nav nav-tabs">
<li>
<a href="#pots">Posts</a>
</li>
<li class="active">
<a href="#info" data-toggle="tab">Info</a>
</li>
<li>
<a href="#profile">Photos</a>
</li>
<li>
<a href="#profile">Videos</a>
</li>
</ul>
</div>
 
<div class="row-fluid">
 
<div id="profileTabContent" class="tab-content span9">
<div class="tab-pane fade in active" id="info">
<dl class="dl-horizontal">
<dt>Introduction</dt>
<dd>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex, magnam voluptates cum non doloremque tenetur minima voluptas est ipsam delectus.</dd>
<dd class="divider"></dd>
<dt>Occupation</dt>
<dd>Geophysical Engineer</dd>
<dd class="divider"></dd>
<dt>Employment</dt>
<dd>Geophysical Engineer</dd>
<dd>Company Inc.</dd>
<dd class="divider"></dd>
<dt>Education</dt>
<dd>College Name</dd>
<dd>Geophisics</dd>
<dd class="divider"></dd>
<dd>School Name</dd>
<dd>High School</dd>
<dd class="divider"></dd>
<dt>Places Lived</dt>
<dd>
<div id="places-lived" style="height: 150px;"></div>
</dd>
</dl>
</div>
</div>
 
<div class="span3" id="user-links">
<h4>Other Profiles</h4>
<ul class="unstyled">
<li><a href="#"><i class="icon-github-sign"></i> Gihub</a></li>
<li><a href="#"><i class="icon-pinterest-sign"></i> Pinterest</a></li>
</ul>
<h4>Recomended Links</h4>
<ul class="unstyled">
<li><a href="#"><i class="icon-css3"></i> CS3 Documentation</a></li>
<li><a href="#"><i class="icon-hospital"></i> Local Hospital</a></li>
<li><a href="#"><i class="icon-html5"></i> HTML5 Documentation</a></li>
</ul>
</div>
</div>
</div>
</div>
</div>
</div>