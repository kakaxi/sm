<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Simple Mobile Advertisement</title>
<script type="text/javascript">
	function resize1() {
		document.body.style.height = document.documentElement.clientHeight + "px";
		
		var tree = document.getElementById("tree");
		tree.style.width = "200px";
		
		var content1 = document.getElementById("content");
		content1.style.width = (document.documentElement.clientWidth - tree.offsetWidth) + "px"; 
		
		var contentDiv = document.getElementById("contentDiv");
		contentDiv.style.width = (document.documentElement.clientWidth - tree.offsetWidth) + "px";
	}
</script>
</head>
<body style="width:100%; height:100%;" onload="resize1();">
		<div id="tree" style="height: 100%;width: 200px; position: relative;float: left;">
		<a href="<%=request.getContextPath()%>/timer/findAll.do" target="content">时间表</a><br/>
			<a href="<%=request.getContextPath()%>/industry/findAll.do" target="content">行业</a><br/>
			<a href="<%=request.getContextPath()%>/area/findAll.do" target="content">省市</a><br/>
			<a href="<%=request.getContextPath()%>/advertiser/findAll.do" target="content">广告主</a><br/>
			<a href="<%=request.getContextPath()%>/adstandard/findAll.do" target="content">广告收费标准</a><br/>
			<a href="<%=request.getContextPath()%>/administrator/findAll.do" target="content">广告管理员</a><br/>
			<a href="<%=request.getContextPath()%>/admanage/listAll.do" target="content">广告列表</a><br/>
			<a href="<%=request.getContextPath()%>/moneyexchange/findRecordsList.do" target="content">积分兑换</a><br/>
			<a href="<%=request.getContextPath()%>/upgrade/upgrade_list.do" target="content">更新app</a><br/>
		</div>
		<div id = "contentDiv" style="height: 100%;position: relative;float: left;">
			<iframe id="content" name="content" style="height: 100%;" marginheight="0" marginwidth="0" frameborder="0"></iframe>
		</div>
	
</body>
</html>
