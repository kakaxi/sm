<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Advertiser</title>
<script type="text/javascript">
	var adstandardId = null;
	
	function change_id(standardId) {
		adstandardId = standardId;
	}
	
	function create_standard() {
		window.location = "${path}/adstandard/create_start.do";
	}
	
	function update_standard() {
		if(adstandardId == null) {
			alert("请选择标准");
			return;
		}
		window.location = "${path}/adstandard/update_start.do?adstandardId=" + adstandardId;
	}
	
	function backto() {
		window.location = "${path}";
	}
</script>
</head>
<body>
	<input type="button" value="创建标准" onclick="create_standard()"/>
	<input type="button" value="修改标准" onclick="update_standard()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<table>
		<thead style="background-color: gray;">
			<tr>
				<td>单选</td>
				<td>标准</td>
				<td>广告类型</td>
				<td>定向条件数</td>
				<td>用户单价</td>
				<td>广告主单价</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adstandardList}" var="adstandard">
				<tr>
					<td><input type="radio" onclick="change_id('${adstandard.id}')"/></td>
					<td>${adstandard.standard.name}</td>
					<td>${adstandard.standard.adType.chineseName}</td>
					<td>${adstandard.conditionCount}</td>
					<td>${adstandard.userMoney}</td>
					<td>${adstandard.clientMoney}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>