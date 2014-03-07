<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<div class="container">
	<form class="form-login" method="get" action="${ctx}/">
		<h2 class="form-heading">请登录</h2>
		<input type="text" class="input-block-level" placeholder="邮箱地址">
		<input type="password" class="input-block-level" placeholder="密码">
		<div class="row-fluid">
			<label class="checkbox span6"> <input type="checkbox" value="remember-me"> 记住我
			</label>
			<button class="btn btn-primary pull-right span6" type="submit">登录</button>
		</div>
		<div class="forget-password">
			<p class="text-center">
				忘记密码? <a href="#" id="link-forgot">点击这里重置</a>
			</p>
		</div>
		<div class="row-fluid">
			<button id="btn-register" class="btn btn-success pull-right span12" type="button">注册</button>
		</div>
	</form>

	<form class="form-register hide" method="get" action="/templates/social/demo/dashboard.html">
		<h2 class="form-heading">Register</h2>
		<div class="alert alert-info hide">
			We sent you an <strong>activation link</strong> to your email
		</div>
		<div id="register-container">
			<p class="text-center">Who are you?</p>
			<div class="input-prepend input-fullwidth">
				<span class="add-on"><i class="icon-user"></i></span>
				<div class="input-wrapper">
					<input type="text" placeholder="Username">
				</div>
			</div>
			<div class="input-prepend input-fullwidth">
				<span class="add-on"><i class="icon-envelope-alt"></i></span>
				<div class="input-wrapper">
					<input type="text" placeholder="Email">
				</div>
			</div>
			<div class="input-prepend input-fullwidth">
				<span class="add-on"><i class="icon-lock"></i></span>
				<div class="input-wrapper">
					<input type="text" placeholder="Password">
				</div>
			</div>
			<div class="input-prepend input-fullwidth">
				<span class="add-on"><i class="icon-ok-sign"></i></span>
				<div class="input-wrapper">
					<input type="text" placeholder="Repeat Password">
				</div>
			</div>
		</div>
		<div class="form-actions">
			<button class="btn btn-primary btn-back" type="button">
				<i class="icon-angle-left"></i> Back
			</button>
			<button class="btn btn-success pull-right" type="button"
				id="btn-register-user">Register</button>
		</div>
	</form>


	<form class="form-forgot hide" method="get"
		action="/templates/social/demo/dashboard.html">
		<h2 class="form-heading">Forgot password</h2>
		<p>Enter your email address to reset your password</p>
		<div class="input-prepend input-fullwidth">
			<span class="add-on"><i class="icon-envelope-alt"></i></span>
			<div class="input-wrapper">
				<input type="text" placeholder="Email">
			</div>
		</div>
		<div class="form-actions">
			<button class="btn btn-primary btn-back" type="button">
				<i class="icon-angle-left"></i> Back
			</button>
			<button class="btn btn-success pull-right" type="button">Send</button>
		</div>
	</form>


	<div class="form-footer-copyright">
		2013 © <small>Social - Premium Responsive Admin Template</small>
	</div>

</div>