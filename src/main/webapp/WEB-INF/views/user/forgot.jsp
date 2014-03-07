<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<div class="container">
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