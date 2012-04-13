<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Advertisement</title>
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/admanage.findSubmit.do";
	}
	
	function setupValidate() {
		$("#audit_publish_form").validate({
			rules:{
				'status': {
					required: true
				}
			}, 
			messages:{
				'status': {
					required: "请选择审核结果"
				}
			}
		});
	}
	
	$(document).ready(function() {
		setupValidate();
	});
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<form id="audit_publish_form" action="${path}/admanage/audit_publish.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="adId" value="${ad.id}"/>
		<fieldset>
			<legend>审核广告:${ad.name}</legend>
			<label>审核结果</label>
			<select name="status">
				<option value="">请选择</option>
				<c:forEach items="${auditList}" var="auditStatus">
					<c:choose>
						<c:when test="${ad.status == auditStatus}">
							<option selected="selected" value="${auditStatus}">${auditStatus.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${auditStatus}">${auditStatus.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</fieldset>
		<input type="submit" value="发布审核"/>
	</form>
</body>
</html>