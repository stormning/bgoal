<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<form role="search" action="${ctx}/search" method="get">
	<div class="col-lg-2">&nbsp;</div>
	<div class="col-lg-8">
		<div class="input-group">
			<input type="text" class="form-control" name="key"> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button">Go!</button>
			</span>
		</div>
	</div>
	<div class="col-lg-2">&nbsp;</div>
</form>

<c:forEach begin="0" end="20">
	<div>
		<div class="title"></div>
		<div></div>
	</div>
</c:forEach>

aa.login?callback=bb.com

bb.com?token=xxx

bb.com store token to cookie 





