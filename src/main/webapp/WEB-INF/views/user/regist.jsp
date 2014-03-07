<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<div class="container">
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

	<div class="form-footer-copyright">
		2013 © <small>Social - Premium Responsive Admin Template</small>
	</div>
	
	
</div>