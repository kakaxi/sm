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
		window.location = "${path}/admanage/findUpdate.do";
	}
	
	function setupValidate() {
		$("#audit_form").validate({
			rules:{
				'status':{
					required: true
				},
				'as':{
					required: true
				}
			},
			messages:{
				'status':{
					required: "请选择审核结果"
				},
				'as':{
					required: "请选择广告收费标准"
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
	<form id="audit_form" action="${path}/admanage/audit.do" enctype="multipart/form-data" method="post">
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
			<br/>
			<label>广告收费标准</label>
			<select name="as">
				<option value="">请选择</option>
				<c:forEach items="${standardList}" var="standard">
					<c:choose>
						<c:when test="${ad.standard == standard}">
							<option selected="selected" value="${standard}">${standard.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${standard}">${standard.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</fieldset>
		<input type="submit" value="审核"/>
	</form>
</body>
</html>