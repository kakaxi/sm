<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Advertisement List</title>
<script type="text/javascript">
	function resize2() {
		document.body.style.height = document.documentElement.clientHeight + "px";
		
		var tree = document.getElementById("ad_tree");
		tree.style.width = "200px";
		
		var content1 = document.getElementById("ad_content");
		content1.style.width = (document.documentElement.clientWidth - tree.offsetWidth) + "px"; 
		
		var contentDiv = document.getElementById("ad_contentDiv");
		contentDiv.style.width = (document.documentElement.clientWidth - tree.offsetWidth) + "px";
	}
</script>
</head>
<body style="width:100%; height:100%;" onload="resize2();">
	<div id="ad_tree" style="height: 100%;width: 200px; position: relative;float: left;">
		<a target="ad_content" href="${path}/admanage/findAll.do">所有广告列表</a><br/>
		<a target="ad_content" href="${path}/admanage/findNew.do">所有新增的广告</a><br/>
		<a target="ad_content" href="${path}/admanage/findUpdate.do">待审核标准的广告</a><br/>
		<a target="ad_content" href="${path}/admanage/findAuditPassed.do">可选择发布条件的广告</a><br/>
		<a target="ad_content" href="${path}/admanage/findSubmit.do">待发布审核的广告</a><br/>
		<a target="ad_content" href="${path}/admanage/findPublish.do">可发布的广告</a><br/>
		<a target="ad_content" href="${path}/admanage/viewPublished.do">查看已发布广告</a><br/>
		<a target="ad_content" href="${path}">返回</a>
	</div>
	<div id = "ad_contentDiv" style="height: 100%;position: relative;float: left;">
		<iframe id="ad_content" name="ad_content" style="height: 100%;" marginheight="0" marginwidth="0" frameborder="0"></iframe>
	</div>
	
</body>
</html>