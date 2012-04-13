<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Exchange List</title>
<script type="text/javascript">
	function resize2() {
		document.body.style.height = document.documentElement.clientHeight + "px";
		
		var tree = document.getElementById("exchange_tree");
		tree.style.width = "200px";
		
		var content1 = document.getElementById("exchange_content");
		content1.style.width = (document.documentElement.clientWidth - tree.offsetWidth) + "px"; 
		
		var contentDiv = document.getElementById("exchange_contentDiv");
		contentDiv.style.width = (document.documentElement.clientWidth - tree.offsetWidth) + "px";
	}
</script>
</head>
<body style="width:100%; height:100%;" onload="resize2();">
	<div id="exchange_tree" style="height: 100%;width: 200px; position: relative;float: left;">
		<a target="exchange_content" href="${path}/moneyexchange/findAppliedRecords.do">处理兑换</a><br/>
		<a target="exchange_content" href="${path}/moneyexchange/findProcessedRecords.do">充值完成</a><br/>
		<a target="exchange_content" href="${path}/moneyexchange/findCompletedRecords.do">查看充值记录</a><br/>
	</div>
	<div id = "exchange_contentDiv" style="height: 100%;position: relative;float: left;">
		<iframe id="exchange_content" name="exchange_content" style="height: 100%;" marginheight="0" marginwidth="0" frameborder="0"></iframe>
	</div>
	
</body>
</html>