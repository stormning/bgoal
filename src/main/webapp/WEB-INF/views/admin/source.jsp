<%@page import="com.slyak.core.util.StringUtils"%>
<%@page import="com.slyak.bgoal.model.RuleType"%>
<%@page import="com.slyak.bgoal.enums.SourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp"%>
<c:set var="js" value="ace/ace|kindeditor/kindeditor-min|kindeditor/lang/zh_CN|kindeditor/plugins/code/prettify|jquery.validate|admin/source" scope="request"/>
<c:set var="css" value="admin/source" scope="request"/>
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="${ctx}/admin">资源管理</a></li>
	<li><span class="icon-angle-right"></span> <a href="${ctx}/admin/sources">所有资源</a></li>
	<li><span class="icon-angle-right"></span> 
		<c:choose>
			<c:when test="${empty source }">
			添加资源	
			</c:when>
			<c:otherwise>
			修改资源
			</c:otherwise>
		</c:choose>
	</li>
</ul>
<div class="social-box">
	<div class="header">
		<h4>
			<c:choose>
				<c:when test="${empty source}">
					填写信息
				</c:when>
				<c:otherwise>
					修改信息
				</c:otherwise>
			</c:choose>
		</h4>
	</div>
	<div class="body">
		<form action="${ctx}/admin/source/save" class="form-horizontal" id="resourceForm" method="post">
			<div class="control-group">
				<label class="control-label">URL</label>
				<div class="controls">
					<c:if test="${not empty source}">
						<input type="hidden" name="id" value="${source.id}">
					</c:if>
					<input type="text" class="span6" name="url"  value="${source.url}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">名称</label>
				<div class="controls">
					<input type="text" class="span6" name="name" value="${source.name}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">图片</label>
				<div class="controls">
					<div class="fileupload fileupload-new" data-provides="fileupload">
						<div id="chooseImg" class="thumbnail" style="width: 150px; height: 150px;">
							<c:set value="${source.id}" var="sourceId" scope="request"/>
							<img alt="选择图片" src="<c:choose><c:when test="${empty source}">${rs}/img/no-image.jpg</c:when><c:otherwise>${ctx}/file/100/<%=StringUtils.devidePath(String.valueOf(request.getAttribute("sourceId")), "/") %>/big.jpg</c:otherwise></c:choose>"/>
						</div>
					</div>
					<input id="hiddenUrl" type="hidden" name="imgUrl" value="${ctx}/img/source/${source.id}">
					<br/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="controls">
					<textarea class="span6" rows="5" id="info" name="info"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">类型</label>
				<div class="controls">
					<select class="span6" name="type">
						<option value="<%=SourceType.NORMAL%>">自定义</option>
						<%-- <option value="<%=SourceType.RSS%>">RSS</option> --%>
					</select>
				</div>
				<br/>
				<div class="controls">
					<select name="ruleType" class="span6">
						<option value="<%=RuleType.GROOVY%>" <c:if test="${sourceRule.type=='GROOVY'}">selected="selected"</c:if>>GROOVY</option>
						<option value="<%=RuleType.JAVASCRIPT%>" <c:if test="${sourceRule.type=='JAVASCRIPT'}">selected="selected"</c:if>>JAVASCRIPT</option>
					</select>
				</div>
				<br/>
				<div class="controls">
					<textarea class="span6" rows="10" id="script" style="display: none;" name="script"></textarea>
					<div id="scriptEditor"></div>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">提交</button>
				<button type="button" class="btn btn-danger" onclick="javascript:history.back()">取消</button>
			</div>
		</form>
	</div>

<pre id="groovyTemplate"><c:choose><c:when test="${empty sourceRule.rule || sourceRule.type!='GROOVY'}">
/**************以下必须实现,否则实现选择实现*******************/
@Override
protected String getDetailUrlQuery() {
	return "";
}

@Override
protected String getDetailTitleQuery() {
	return "";
}

@Override
protected String getDetailContentQuery() {
	return "";
}

@Override
protected String getNextPageUrlQuery() {
	return "";
}

/******************以下选择实现********************/
@Override
public void handle(Source source) {
	super.handle(source);
}

@Override
protected Document getDocument(String url) {
	return super.getDocument(url);
}

@Override
protected String getNextPageUrl(Element element) {
	return super.getNextPageUrl(element);
}

@Override
protected void buildArticle(Source source, Element element) {
	super.buildArticle(source, element);
}

@Override
protected String getDetailTitle(Element element) {
	return super.getDetailTitle(element);
}

@Override
protected String getDetailContent(Element element) {
	return super.getDetailContent(element);
}
</c:when>
<c:otherwise>
${config.content}
</c:otherwise>
</c:choose>
</pre>

<pre id="javascriptTemplate"><c:choose><c:when test="${empty sourceRule.rule || sourceRule.type!='JAVASCRIPT'}">
function getDetailUrls(el){
	return $(el);
}
	
function getDetailTitle(el){
	return $(el);
}
	
function getDetailContent(el){
	return $(el);
}
	
function getNextPageUrl(el){
	return $(el);
}</c:when><c:otherwise>${config.detailUrlsFunction}
	
${config.detailTitleFunction}
	
${config.detailContentFunction}
	
${config.nextPageUrlFunction}</c:otherwise></c:choose></pre>

<script>
var param = {};
param.cssPath = '${rs}/js/kindeditor/plugins/code/prettify.css';
param.uploadJson = '${ctx}/file/textEditor/upload';
param.fileManagerJson = '${ctx}/file/textEditor';
<c:choose>
	<c:when test="${not empty sourceRule}">
		param.ruleType = '${sourceRule.type}';
	</c:when>
	<c:otherwise>
		param.ruleType = '0';
	</c:otherwise>
</c:choose>
</script>

</div>