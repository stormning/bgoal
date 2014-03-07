<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<div class="container">
	<header>
		<nav class="navbar">
			<div class="navbar-inner">
				<div class="row-fluid">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span><span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a class="brand" href="#">BGOAL</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li<slyak:requestMatch pattern="/"> class="active"</slyak:requestMatch>><a href="${ctx}/">首页</a></li>
							<li<slyak:requestMatch pattern="/search,/article/**"> class="active"</slyak:requestMatch>><a href="${ctx}/search?key=java">发现</a></li>
							<li<slyak:requestMatch pattern="/source/**"> class="active"</slyak:requestMatch>><a href="${ctx}/source/list">兴趣源</a></li>
						</ul>
						<ul class="nav pull-right">
						  <li<slyak:requestMatch pattern="/user/profile"> class="active"</slyak:requestMatch>><a href="${ctx}/user/profile">个人主页</a></li>	
						  <li<slyak:requestMatch pattern="/admin/**"> class="active"</slyak:requestMatch>><a href="${ctx}/admin">管理</a></li>
	                      <li><a href="${ctx}/user/login">登录</a></li>
	                      <li><a href="${ctx}/logout">注销</a></li>
	                      <li><a href="${ctx}/user/regist">注册</a></li>
	                    </ul>
					</div>
					<!-- <form action="" class="navbar-form pull-right">
						<div class="input-append" style="margin-bottom: 0px;">
							<input class="span10" id="appendedPrependedDropdownButton" type="text">
							<button class="btn" type="submit">
								<span class="icon icon-search"></span>
							</button>
						</div>
					</form> -->
				</div>
			</div>
		</nav>
	</header>
	<tiles:insertAttribute name="content" />
</div>