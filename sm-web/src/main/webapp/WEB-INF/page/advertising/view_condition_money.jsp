<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Advertisement</title>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/json.js"></script>
<script type="text/javascript">
	function publish() {
		$.post("${path}/advertising/publish.do", {adId: '${ad.id}'}, function(result) {
			alert(result);
			window.location = "${path}/admanage/findPublish.do";
		});
	}
	
	function backto() {
		window.location = "${path}/admanage/findPublish.do";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/><br/>
	<fieldset>
	<legend>你所选择的条件</legend>
	<c:forEach items="${conditionViews}" var="conditionView">
		<label>${conditionView}</label><br/>
	</c:forEach>
	</fieldset>
	<input type="hidden" name="adId" value="${ad.id}">
	<input type="button" onclick="publish()" value="发布"/>
</body>
</html>