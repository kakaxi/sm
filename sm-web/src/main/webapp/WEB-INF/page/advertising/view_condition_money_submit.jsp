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
	function setupValidate() {
		$("#submit_form").validate({
			rules:{
				'publishNum':{
					required: true,
					number: true,
					min: 0,
					max: parseInt('${avaiableUser}')
				}
			},
			messages:{
				'publishNum': {
					required: "请输入发布量",
					number: "发布量须为整数",
					min: "发布量不能小于0",
					max: "发布量不能大于可发布最大值"
				}
			}
		});
	}
	
	$(document).ready(function(){
		setupValidate();
	});
</script>
</head>
<body>
	<fieldset>
	<legend>你所选择的条件</legend>
	<c:forEach items="${conditionViews}" var="conditionView">
		<label>${conditionView}</label><br/>
	</c:forEach>
	</fieldset>
	<form id="submit_form" action="${path}/advertising/submit.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="adId" value="${ad.id}">
		<label>准备发布:${ad.name}</label><br/>
		<label>请输入发布量:</label><input type="text" name="publishNum" value="${avaiableUser}"><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>